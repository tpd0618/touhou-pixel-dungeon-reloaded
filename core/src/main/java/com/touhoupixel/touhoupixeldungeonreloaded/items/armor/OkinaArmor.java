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

package com.touhoupixel.touhoupixeldungeonreloaded.items.armor;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class OkinaArmor extends Armor {

	{
		image = ItemSpriteSheet.OKINA_ARMOR;
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		int limitHT = defender.HT/5;
		if (damage > limitHT){
			damage = limitHT + (damage-limitHT)/3; // if the damage is more than 20% hp, it deals 20% of hp + extra damage / 3
			ScrollOfTeleportation.randomTeleportChar(defender);
		}
		return super.proc(attacker, defender, damage);
	}

	public OkinaArmor() {
		super( 5 );
	}

}
