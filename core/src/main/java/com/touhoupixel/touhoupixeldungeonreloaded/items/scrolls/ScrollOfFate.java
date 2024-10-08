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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls;

import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Identification;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.Talisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Plant;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class ScrollOfFate extends InventoryScroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_FATE;

		bones = true;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return !item.unique && !(item instanceof Plant.Seed) && !(item instanceof MissileWeapon) && !(item instanceof Talisman);
	}

	@Override
	protected void onItemSelected( Item item ) {
		if (item instanceof MeleeWeapon){
			curUser.sprite.parent.add(new Identification(curUser.sprite.center().offset(0, -16)));
			if (Random.Int(12) == 0) {
				item.upgrade();
				item.upgrade();
				item.upgrade();
				GLog.i(Messages.get(this, "super_fate"));
			} else {
				item.upgrade();
				GLog.i(Messages.get(this, "fate"));
			}
		} else {
			curUser.sprite.parent.add(new Identification(curUser.sprite.center().offset(0, -16)));
			GLog.w(Messages.get(this, "not_weapon"));
		}
		Statistics.fate_use = true;
		updateQuickslot();
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}
