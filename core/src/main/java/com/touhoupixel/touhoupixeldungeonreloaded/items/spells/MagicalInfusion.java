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

package com.touhoupixel.touhoupixeldungeonreloaded.items.spells;

import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.UpgradeCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class MagicalInfusion extends InventorySpell {
	
	{
		image = ItemSpriteSheet.MAGIC_INFUSE;

		unique = true;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return item.isUpgradable();
	}

	@Override
	protected void onItemSelected( Item item ) {

		UpgradeCard.upgrade(curUser);

		Degrade.detach( curUser, Degrade.class );

		if (item instanceof Weapon && ((Weapon) item).enchantment != null) {
			((Weapon) item).upgrade(true);
		} else if (item instanceof Armor && ((Armor) item).glyph != null) {
			((Armor) item).upgrade(true);
		} else {
			item.upgrade();
		}
		
		GLog.p( Messages.get(this, "infuse", item.name()) );

		Statistics.upgradesUsed++;
	}
	
	@Override
	public int value() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((50 + 40) / 1f));
	}
	
	public static class Recipe extends com.touhoupixel.touhoupixeldungeonreloaded.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{UpgradeCard.class, ArcaneCatalyst.class};
			inQuantity = new int[]{1, 1};
			
			cost = 4;
			
			output = MagicalInfusion.class;
			outQuantity = 1;
		}
		
	}
}
