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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Gold;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MimicSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Mimic extends Mob {
	
	private int level;
	
	{
		spriteClass = MimicSprite.class;

		EXP = 0;

		properties.add(Property.ANIMAL);
		properties.add(Property.NOT_EXTERMINABLE);

		properties.add(Property.FUMO);
		//used for fumo lover buff
		
		//mimics are neutral when hidden
		alignment = Alignment.NEUTRAL;
		state = PASSIVE;
	}
	
	public ArrayList<Item> items;
	
	private static final String LEVEL	= "level";
	private static final String ITEMS	= "items";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		if (items != null) bundle.put( ITEMS, items );
		bundle.put( LEVEL, level );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		if (bundle.contains( ITEMS )) {
			items = new ArrayList<>((Collection<Item>) ((Collection<?>) bundle.getCollection(ITEMS)));
		}
		level = bundle.getInt( LEVEL );
		adjustStats(level);
		super.restoreFromBundle(bundle);
		if (state != PASSIVE && alignment == Alignment.NEUTRAL){
			alignment = Alignment.ENEMY;
		}
	}

	@Override
	public boolean add(Buff buff) {
		boolean added = super.add(buff);
		if (buff.type == Buff.buffType.NEGATIVE && alignment == Alignment.NEUTRAL){
			alignment = Alignment.ENEMY;
			stopHiding();
			if (sprite != null) sprite.idle();
		}
		return added;
	}

	@Override
	public String name() {
		if (alignment == Alignment.NEUTRAL){
			return Messages.get(Heap.class, "chest");
		} else {
			return super.name();
		}
	}

	@Override
	public String description() {
		if (alignment == Alignment.NEUTRAL){
			return Messages.get(Heap.class, "chest_desc") + "\n\n" + Messages.get(this, "hidden_hint");
		} else {
			return super.description();
		}
	}

	@Override
	protected boolean act() {
		if (alignment == Alignment.NEUTRAL && state != PASSIVE){
			alignment = Alignment.ENEMY;
			GLog.w(Messages.get(this, "reveal") );
			CellEmitter.get(pos).burst(Speck.factory(Speck.STAR), 10);
			Sample.INSTANCE.play(Assets.Sounds.MIMIC);
		}
		return super.act();
	}

	@Override
	public CharSprite sprite() {
		MimicSprite sprite = (MimicSprite) super.sprite();
		if (alignment == Alignment.NEUTRAL) sprite.hideMimic();
		return sprite;
	}

	@Override
	public boolean interact(Char c) {
		if (alignment != Alignment.NEUTRAL || c != Dungeon.heroine){
			return super.interact(c);
		}
		stopHiding();

		Dungeon.heroine.busy();
		Dungeon.heroine.sprite.operate(pos);
		if (Dungeon.heroine.invisible <= 0
				&& Dungeon.heroine.buff(Swiftthistle.TimeBubble.class) == null
				&& Dungeon.heroine.buff(TimekeepersHourglass.timeFreeze.class) == null){
			return doAttack(Dungeon.heroine);
		} else {
			sprite.idle();
			alignment = Alignment.ENEMY;
			Dungeon.heroine.spendAndNext(1f);
			return true;
		}
	}

	@Override
	public void onAttackComplete() {
		super.onAttackComplete();
		if (alignment == Alignment.NEUTRAL){
			alignment = Alignment.ENEMY;
			Dungeon.heroine.spendAndNext(1f);
		}
	}

	@Override
	public void damage(int dmg, Object src) {
		if (state == PASSIVE){
			alignment = Alignment.ENEMY;
			stopHiding();
		}
		super.damage(dmg, src);
	}

	public void stopHiding(){
		state = HUNTING;
		if (sprite != null) sprite.idle();
		if (Actor.chars().contains(this) && Dungeon.level.heroFOV[pos]) {
			enemy = Dungeon.heroine;
			target = Dungeon.heroine.pos;
			enemySeen = true;
			GLog.w(Messages.get(this, "reveal") );
			CellEmitter.get(pos).burst(Speck.factory(Speck.STAR), 10);
			Sample.INSTANCE.play(Assets.Sounds.MIMIC);
		}
	}

	@Override
	public int damageRoll() {
		if (alignment == Alignment.NEUTRAL){
			return (int)(1.2*(2.5*level + 3 + Math.min(5, ((level - 1)/4 + 1)*(1+level))));
		} else {
			return Random.NormalIntRange( (int)((2.5*level + 3 + Math.min(5, ((level - 1)/4 + 1)*(1+level)))), (int)(1.4*(2.5*level + 3 + Math.min(5, ((level - 1)/4 + 1)*(1+level)))));
		}
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 1 + level/2);
	}

	@Override
	public void beckon( int cell ) {
		// Do nothing
	}

	@Override
	public int attackSkill( Char target ) {
		if (target != null && alignment == Alignment.NEUTRAL && target.invisible <= 0){
			return INFINITE_ACCURACY;
		} else {
			return 6 + level;
		}
	}

	public void setLevel( int level ){
		this.level = level;
		adjustStats(level);
	}
	
	public void adjustStats( int level ) {
		HP = HT = 5*(Math.min((level-1)/4 + 1, 5) * (level + 3) + level + 3);
		defenseSkill = 2 + level/2;
		
		enemySeen = true;
	}
	
	@Override
	public void rollToDropLoot(){
		
		if (items != null) {
			for (Item item : items) {
				Dungeon.level.drop( item, pos ).sprite.drop();
			}
			items = null;
		}
		super.rollToDropLoot();
	}

	@Override
	public float spawningWeight() {
		return 0f;
	}

	@Override
	public boolean reset() {
		if (state != PASSIVE) state = WANDERING;
		return true;
	}

	public static Mimic spawnAt( int pos, Item item ){
		return spawnAt( pos, Arrays.asList(item), Mimic.class);
	}

	public static Mimic spawnAt( int pos, Item item, Class mimicType ){
		return spawnAt( pos, Arrays.asList(item), mimicType);
	}

	public static Mimic spawnAt( int pos, List<Item> items ) {
		return spawnAt( pos, items, Mimic.class);
	}

	public static Mimic spawnAt( int pos, List<Item> items, Class mimicType ) {

		Mimic m;
		if (mimicType == GoldenMimic.class){
			m = new GoldenMimic();
		} else if (mimicType == CrystalMimic.class) {
			m = new CrystalMimic();
		} else {
			m = new Mimic();
		}

		m.items = new ArrayList<>( items );
		m.setLevel( Dungeon.floor);
		m.pos = pos;

		//generate an extra reward for killing the mimic
		m.generatePrize();
		
		return m;
	}

	protected void generatePrize(){
		Item reward = null;
		do {
			switch (Random.Int(5)) {
				case 0:
					reward = new Gold().random();
					break;
				case 1:
					reward = Generator.randomMissile();
					break;
				case 2:
					reward = Generator.randomWeapon();
					break;
				case 3:
					reward = Generator.randomArmor();
					break;
				case 4:
					reward = Generator.random(Generator.Category.BRACELET);
					break;
			}
		} while (reward == null || Challenges.isItemBlocked(reward));
		items.add(reward);
	}

}
