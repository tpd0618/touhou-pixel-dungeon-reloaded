/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.ThreeStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YorihimeSprite;
import com.watabou.utils.Random;

public class Yorihime extends Mob {
	
	{
		spriteClass = YorihimeSprite.class;

		if (Dungeon.depth > 50){
			HP = HT = 1000;
		} else HP = HT = 300;

		if (Dungeon.depth > 50){
			defenseSkill = 100;
		} else defenseSkill = 50;

		if (Dungeon.depth > 50){
			EXP = 75;
		} else EXP = 25;

		if (Dungeon.depth > 50){
			maxLvl = 99;
		} else maxLvl = 50;

		flying = true;

		properties.add(Property.FLOAT);
		properties.add(Property.GOD);
		properties.add(Property.FIRE);

		baseSpeed = 0.8f;

		loot = new ThreeStarTicket();
		lootChance = 0.1f;
	}

	@Override
	public void die( Object cause ) {
		super.die( cause );

		Statistics.yorihimesKilled++;
	}

	@Override
	public int damageRoll() {
		if (Dungeon.depth > 50) {
			return Random.NormalIntRange(60, 76);
		} else return Random.NormalIntRange(30, 38);
	}

	@Override
	public int attackSkill(Char target) {
		if (Dungeon.depth > 50) {
			return 120;
		} else return 55;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	@Override
	public int attackProc( Char hero, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.prolong( enemy, Weakness.class, Weakness.DURATION );
			Buff.prolong( enemy, Vulnerable.class, Vulnerable.DURATION );
			Buff.prolong( enemy, Hex.class, Hex.DURATION );
			Buff.prolong( enemy, Blindness.class, Blindness.DURATION );
			Buff.affect( enemy, Burning.class ).reignite(enemy, 3f);
			Buff.prolong( enemy, Cripple.class, Cripple.DURATION );
			Buff.prolong( enemy, Slow.class, Slow.DURATION );
		}

		return damage;
	}
}
