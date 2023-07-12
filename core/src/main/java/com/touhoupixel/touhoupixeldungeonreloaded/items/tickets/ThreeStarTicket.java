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

package com.touhoupixel.touhoupixeldungeonreloaded.items.tickets;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ThreeStarTicket extends Item {

	private static final String AC_DRINK	= "DRINK";

	{
		image = ItemSpriteSheet.THREE_STAR_TICKET;

		defaultAction = AC_DRINK;

		stackable = true;
		unique = true;
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add( AC_DRINK );
		return actions;
	}

	@Override
	public void execute(final Hero heroine, String action ) {

		super.execute(heroine, action);

		if (action.equals(AC_DRINK)) {
			switch (Random.Int(50)) {
				case 0: default: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39: case 40: case 41: case 42: case 43:
				if (Random.Int(2) == 0) {
					GameScene.flash(0x80FFFFFF);
					detach(curUser.belongings.backpack);
					updateQuickslot();
					Dungeon.level.drop(Generator.random(Generator.Category.WEP_T3), curUser.pos).sprite.drop();
				} else {
					GameScene.flash(0x80FFFFFF);
					detach( curUser.belongings.backpack );
					updateQuickslot();
					Dungeon.level.drop(Generator.random(Generator.Category.ARMOR_T3), curUser.pos).sprite.drop();
				}
				break;
				case 44: case 45: case 46: case 47: case 48:
				if (Random.Int(2) == 0) {
					GameScene.flash(0x80FFFFFF);
					detach(curUser.belongings.backpack);
					updateQuickslot();
					Dungeon.level.drop(Generator.random(Generator.Category.WEP_T4), curUser.pos).sprite.drop();
				} else {
					GameScene.flash(0x80FFFFFF);
					detach(curUser.belongings.backpack);
					updateQuickslot();
					Dungeon.level.drop(Generator.random(Generator.Category.ARMOR_T4), curUser.pos).sprite.drop();
				}
				break;
				case 49:
				if (Random.Int(2) == 0) {
					GameScene.flash(0x80FFFFFF);
					detach(curUser.belongings.backpack);
					updateQuickslot();
					Dungeon.level.drop(Generator.random(Generator.Category.WEP_T5), curUser.pos).sprite.drop();
				} else {
					GameScene.flash(0x80FFFFFF);
					detach(curUser.belongings.backpack);
					updateQuickslot();
					Dungeon.level.drop(Generator.random(Generator.Category.ARMOR_T5), curUser.pos).sprite.drop();
				}
				break;
			}
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int value() {
		return 100 * quantity;
	}
}