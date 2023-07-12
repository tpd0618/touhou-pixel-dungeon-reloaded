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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.CorrosiveGas;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.ToxicGas;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PrismaticGuard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.glyphs.AntiMagic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.glyphs.Brimstone;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.Chasm;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.PrismaticSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class PrismaticImage extends NPC {
	
	{
		spriteClass = PrismaticSprite.class;
		
		HP = HT = 10;
		defenseSkill = 1;
		
		alignment = Alignment.ALLY;
		intelligentAlly = true;
		state = HUNTING;
		
		WANDERING = new Wandering();
		
		//before other mobs
		actPriority = MOB_PRIO + 1;
	}
	
	private Hero heroine;
	private int heroID;
	public int armTier;
	
	private int deathTimer = -1;
	
	@Override
	protected boolean act() {
		
		if (!isAlive()){
			deathTimer--;
			
			if (deathTimer > 0) {
				sprite.alpha((deathTimer + 3) / 8f);
				spend(TICK);
			} else {
				destroy();
				sprite.die();
			}
			return true;
		}
		
		if (deathTimer != -1){
			if (paralysed == 0) sprite.remove(CharSprite.State.PARALYSED);
			deathTimer = -1;
			sprite.resetColor();
		}
		
		if ( heroine == null ){
			heroine = (Hero) Actor.findById(heroID);
			if ( heroine == null ){
				destroy();
				sprite.die();
				return true;
			}
		}
		
		if (heroine.tier() != armTier){
			armTier = heroine.tier();
			((PrismaticSprite)sprite).updateArmor( armTier );
		}
		
		return super.act();
	}
	
	@Override
	public void die(Object cause) {
		if (deathTimer == -1) {
			if (cause == Chasm.class){
				super.die( cause );
			} else {
				deathTimer = 5;
				sprite.add(CharSprite.State.PARALYSED);
			}
		}
	}
	
	private static final String HEROID	= "hero_id";
	private static final String TIMER	= "timer";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( HEROID, heroID );
		bundle.put( TIMER, deathTimer );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		heroID = bundle.getInt( HEROID );
		deathTimer = bundle.getInt( TIMER );
	}
	
	public void duplicate(Hero heroine, int HP ) {
		this.heroine = heroine;
		heroID = this.heroine.id();
		this.HP = HP;
		HT = PrismaticGuard.maxHP(heroine);
	}
	
	@Override
	public int damageRoll() {
		if (heroine != null) {
			return Random.NormalIntRange( 2 + heroine.lvl/4, 4 + heroine.lvl/2 );
		} else {
			return Random.NormalIntRange( 2, 4 );
		}
	}
	
	@Override
	public int attackSkill( Char target ) {
		if (heroine != null) {
			return heroine.attackSkill(target);
		} else {
			return 0;
		}
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
	public int drRoll() {
		if (heroine != null){
			return heroine.drRoll();
		} else {
			return 0;
		}
	}
	
	@Override
	public int defenseProc(Char enemy, int damage) {
		damage = super.defenseProc(enemy, damage);
		if (heroine != null && heroine.belongings.armor() != null){
			return heroine.belongings.armor().proc( enemy, this, damage );
		} else {
			return damage;
		}
	}
	
	@Override
	public void damage(int dmg, Object src) {
		
		//TODO improve this when I have proper damage source logic
		if (heroine != null && heroine.belongings.armor() != null && heroine.belongings.armor().hasGlyph(AntiMagic.class, this)
				&& AntiMagic.RESISTS.contains(src.getClass())){
			dmg -= AntiMagic.drRoll(heroine.belongings.armor().buffedLvl());
		}
		
		super.damage(dmg, src);
	}
	
	@Override
	public float speed() {
		if (heroine != null && heroine.belongings.armor() != null){
			return heroine.belongings.armor().speedFactor(this, super.speed());
		}
		return super.speed();
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		
		if (enemy instanceof Mob) {
			((Mob)enemy).aggro( this );
		}
		
		return super.attackProc( enemy, damage );
	}
	
	@Override
	public CharSprite sprite() {
		CharSprite s = super.sprite();
		
		heroine = (Hero)Actor.findById(heroID);
		if (heroine != null) {
			armTier = heroine.tier();
		}
		((PrismaticSprite)s).updateArmor( armTier );
		return s;
	}
	
	@Override
	public boolean isImmune(Class effect) {
		if (effect == Burning.class
				&& heroine != null
				&& heroine.belongings.armor() != null
				&& heroine.belongings.armor().hasGlyph(Brimstone.class, this)){
			return true;
		}
		return super.isImmune(effect);
	}
	
	{
		immunities.add( ToxicGas.class );
		immunities.add( CorrosiveGas.class );
		immunities.add( Burning.class );
		immunities.add( AllyBuff.class );
	}
	
	private class Wandering extends Mob.Wandering{
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (!enemyInFOV){
				Buff.affect(heroine, PrismaticGuard.class).set( HP );
				destroy();
				CellEmitter.get(pos).start( Speck.factory(Speck.LIGHT), 0.2f, 3 );
				sprite.die();
				Sample.INSTANCE.play( Assets.Sounds.TELEPORT );
				return true;
			} else {
				return super.act(enemyInFOV, justAlerted);
			}
		}
		
	}
	
}
