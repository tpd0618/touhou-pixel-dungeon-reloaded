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
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class YoumuDanmaku extends MissileWeapon {
	
	{
		image = ItemSpriteSheet.YOUMU_DANMAKU;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 0.9f;
		
		tier = 3;
	}

	@Override
	public int max(int lvl) {
		return  (4+Dungeon.energy/5+Dungeon.heroine.lvl/3) * tier +                      //base
				(tier == 1 ? 2*lvl : tier*lvl); //level scaling
	}
}