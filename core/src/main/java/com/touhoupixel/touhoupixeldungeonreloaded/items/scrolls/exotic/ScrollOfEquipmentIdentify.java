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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.EquipmentIdentify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Triplespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class ScrollOfEquipmentIdentify extends ExoticScroll {
	
	{
		icon = ItemSpriteSheet.Icons.SCROLL_EQUIPMENT_IDENTIFY;
	}
	
	@Override
	public void doRead() {
		Buff.prolong(curUser, EquipmentIdentify.class, EquipmentIdentify.DURATION);

		identify();
		readAnimation();
	}
}