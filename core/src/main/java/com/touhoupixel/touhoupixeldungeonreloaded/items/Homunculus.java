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

package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfExorcism;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class Homunculus extends Item {

	private static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.HOMUNCULUS;

		defaultAction = AC_DRINK;

		stackable = true;
		unique = true;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		//actions.add(AC_DRINK);
		return actions;
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	public static class Recipe extends com.touhoupixel.touhoupixeldungeonreloaded.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{ScrollOfExorcism.class, PotionOfHealing.class};
			inQuantity = new int[]{1, 1};

			cost = 4;

			output = Homunculus.class;
			outQuantity = 1;
		}
	}
}