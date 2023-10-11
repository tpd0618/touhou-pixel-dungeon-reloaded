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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Hijiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Suika;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class EgoRock extends MissileWeapon {
	
	{
		image = ItemSpriteSheet.EGO_ROCK;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch = 1.2f;
		
		tier = 2;
	}

	@Override
	public int min(int lvl) {
		return 28; //deals 28 damage, same as ego rock's expert difficulty level in bang dream! girls band party
	}

	@Override
	public int max(int lvl) {
		return 28; //deals 28 damage, same as ego rock's expert difficulty level in bang dream! girls band party
	}
	
	@Override
	public int damageRoll(Char owner) {
		Hero heroine = (Hero)owner;
		Char enemy = heroine.enemy();
		if (enemy instanceof Hijiri || enemy instanceof Suika){
			enemy.die(this);
		}
		return 28; //deals 28 damage, same as ego rock's expert difficulty level in bang dream! girls band party
	}
}