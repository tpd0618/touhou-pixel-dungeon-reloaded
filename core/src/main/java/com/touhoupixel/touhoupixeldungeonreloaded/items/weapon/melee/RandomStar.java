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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class RandomStar extends MeleeWeapon {

	{
		image = ItemSpriteSheet.RANDOM_STAR;
		hitSound = Assets.Sounds.HIT_MAGIC;
		hitSoundPitch = 1f;

		tier = 5;
	}

	@Override
	public int YokaiFactor( Char owner ) {
		return 1;
	}

	@Override
	public int proc(Char attacker, Char defender, int damage ) {
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (mob.pos == attacker.pos + 1){
				mob.damage(damageRoll(attacker), attacker);
				mob.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
			}
		}
		for (Mob mob2 : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (mob2.pos == attacker.pos - 1){
				mob2.damage(damageRoll(attacker), attacker);
				mob2.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
			}
		}
		for (Mob mob3 : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (mob3.pos == attacker.pos - Dungeon.level.width()){
				mob3.damage(damageRoll(attacker), attacker);
				mob3.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
			}
		}
		for (Mob mob4 : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (mob4.pos == attacker.pos + Dungeon.level.width() + 1){
				mob4.damage(damageRoll(attacker), attacker);
				mob4.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
			}
		}
		for (Mob mob5 : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (mob5.pos == attacker.pos + Dungeon.level.width() - 1){
				mob5.damage(damageRoll(attacker), attacker);
				mob5.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
			}
		}
		return super.proc(attacker, defender, damage);
	}

	@Override
	public int max(int lvl) {
		return  3*(tier+1) +
				lvl*(tier+1);
	}
}