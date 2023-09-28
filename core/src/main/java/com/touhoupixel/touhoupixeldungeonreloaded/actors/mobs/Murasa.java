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

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Electricity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Freezing;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BlobImmunity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.MysteryMeat;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MurasaSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Murasa extends Mob {
	
	{
		spriteClass = MurasaSprite.class;

		baseSpeed = 2f;
		
		EXP = 0;

		properties.add(Property.WARP);
		
		loot = MysteryMeat.class;
		lootChance = 0.25f;
		
		SLEEPING = new Sleeping();
		WANDERING = new Wandering();
		HUNTING = new Hunting();
		
		state = SLEEPING;

	}
	
	public Murasa() {
		super();
		
		HP = HT = 10 + Dungeon.floor * 5;
		defenseSkill = 10 + Dungeon.floor * 2;
	}
	
	@Override
	protected boolean act() {
		
		if (!Dungeon.level.water[pos]) {
			die( null );
			return true;
		} else {
			return super.act();
		}
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( Dungeon.floor, 4 + Dungeon.floor * 2 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 20 + Dungeon.floor * 2;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, Dungeon.floor);
	}

	@Override
	public boolean surprisedBy(Char enemy, boolean attacking) {
		if (enemy == Dungeon.heroine && (!attacking || ((Hero)enemy).canSurpriseAttack())){
			if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
				fieldOfView = new boolean[Dungeon.level.length()];
				Dungeon.level.updateFieldOfView( this, fieldOfView );
			}
			return state == SLEEPING || !fieldOfView[enemy.pos] || enemy.invisible > 0;
		}
		return super.surprisedBy(enemy, attacking);
	}
	
	@Override
	public void die( Object cause ) {
		super.die( cause );
		
		Statistics.murasasKilled++;
	}

	@Override
	public float spawningWeight() {
		return 0;
	}

	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	protected boolean getCloser( int target ) {
		
		if (rooted) {
			return false;
		}
		
		int step = Dungeon.findStep( this, target, Dungeon.level.water, fieldOfView, true );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected boolean getFurther( int target ) {
		int step = Dungeon.flee( this, target, Dungeon.level.water, fieldOfView, true );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	{
		for (Class c : new BlobImmunity().immunities()){
			if (c != Electricity.class && c != Freezing.class){
				immunities.add(c);
			}
		}
		immunities.add( Burning.class );
	}
	
	//if there is not a path to the enemy, piranhas act as if they can't see them
	private class Sleeping extends Mob.Sleeping{
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (enemyInFOV) {
				PathFinder.buildDistanceMap(enemy.pos, Dungeon.level.water, viewDistance);
				enemyInFOV = PathFinder.distance[pos] != Integer.MAX_VALUE;
			}
			
			return super.act(enemyInFOV, justAlerted);
		}
	}
	
	private class Wandering extends Mob.Wandering{
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (enemyInFOV) {
				PathFinder.buildDistanceMap(enemy.pos, Dungeon.level.water, viewDistance);
				enemyInFOV = PathFinder.distance[pos] != Integer.MAX_VALUE;
			}
			
			return super.act(enemyInFOV, justAlerted);
		}
	}
	
	private class Hunting extends Mob.Hunting{
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (enemyInFOV) {
				PathFinder.buildDistanceMap(enemy.pos, Dungeon.level.water, viewDistance);
				enemyInFOV = PathFinder.distance[pos] != Integer.MAX_VALUE;
			}
			
			return super.act(enemyInFOV, justAlerted);
		}
	}

	@Override
	public int attackProc( Char hero, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
			Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
			if (Statistics.difficulty > 2) {
				Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
			}
			if (Statistics.difficulty > 4) {
				Buff.prolong(enemy, Slow.class, Slow.DURATION);
			}
		}
		return damage;
	}
}
