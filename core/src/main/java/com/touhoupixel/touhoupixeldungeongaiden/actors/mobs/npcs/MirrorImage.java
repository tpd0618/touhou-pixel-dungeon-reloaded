/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.npcs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.CorrosiveGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.ToxicGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MirrorSprite;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BuffIndicator;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class MirrorImage extends NPC {
	
	{
		spriteClass = MirrorSprite.class;
		
		HP = HT = 1;
		defenseSkill = 1;
		
		alignment = Alignment.ALLY;
		state = HUNTING;
		
		//before other mobs
		actPriority = MOB_PRIO + 1;
	}
	
	private Hero heroine;
	private int heroID;
	public int armTier;
	
	@Override
	protected boolean act() {
		
		if ( heroine == null ){
			heroine = (Hero)Actor.findById(heroID);
			if ( heroine == null ){
				die(null);
				sprite.killAndErase();
				return true;
			}
		}
		
		if (heroine.tier() != armTier){
			armTier = heroine.tier();
			((MirrorSprite)sprite).updateArmor( armTier );
		}
		
		return super.act();
	}
	
	private static final String HEROID	= "hero_id";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( HEROID, heroID );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		heroID = bundle.getInt( HEROID );
	}
	
	public void duplicate( Hero heroine) {
		this.heroine = heroine;
		heroID = this.heroine.id();
		Buff.affect(this, MirrorInvis.class, Short.MAX_VALUE);
	}
	
	@Override
	public int damageRoll() {
		int damage;
		if (heroine.belongings.weapon() != null){
			damage = heroine.belongings.weapon().damageRoll(this);
		} else {
			damage = heroine.damageRoll(); //handles ring of force
		}
		return (damage+1)/2; //half hero damage, rounded up
	}
	
	@Override
	public int attackSkill( Char target ) {
		return heroine.attackSkill(target);
	}
	
	@Override
	public int defenseSkill(Char enemy) {
		if (heroine != null) {
			int baseEvasion = 4 + heroine.lvl;
			int heroEvasion = heroine.defenseSkill(enemy);
			
			//if the hero has more/less evasion, 50% of it is applied
			return super.defenseSkill(enemy) * (baseEvasion + heroEvasion) / 2;
		} else {
			return 0;
		}
	}
	
	@Override
	public float attackDelay() {
		return heroine.attackDelay(); //handles ring of furor
	}
	
	@Override
	protected boolean canAttack(Char enemy) {
		return super.canAttack(enemy) || (heroine.belongings.weapon() != null && heroine.belongings.weapon().canReach(this, enemy.pos));
	}
	
	@Override
	public int drRoll() {
		if (heroine != null && heroine.belongings.weapon() != null){
			return Random.NormalIntRange(0, heroine.belongings.weapon().defenseFactor(this)/2);
		} else {
			return 0;
		}
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		
		MirrorInvis buff = buff(MirrorInvis.class);
		if (buff != null){
			buff.detach();
		}
		
		if (enemy instanceof Mob) {
			((Mob)enemy).aggro( this );
		}
		if (heroine.belongings.weapon() != null){
			damage = heroine.belongings.weapon().proc( this, enemy, damage );
			if (!enemy.isAlive() && enemy == Dungeon.heroine){
				Dungeon.fail(getClass());
				GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );
			}
			return damage;
		} else {
			return damage;
		}
	}
	
	@Override
	public CharSprite sprite() {
		CharSprite s = super.sprite();
		
		heroine = (Hero)Actor.findById(heroID);
		if (heroine != null) {
			armTier = heroine.tier();
		}
		((MirrorSprite)s).updateArmor( armTier );
		return s;
	}
	
	{
		immunities.add( ToxicGas.class );
		immunities.add( CorrosiveGas.class );
		immunities.add( Burning.class );
		immunities.add( AllyBuff.class );
	}
	
	public static class MirrorInvis extends Invisibility {
		
		{
			announced = false;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.NONE;
		}
	}
}