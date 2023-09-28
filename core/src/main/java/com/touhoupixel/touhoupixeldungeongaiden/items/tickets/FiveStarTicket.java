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

package com.touhoupixel.touhoupixeldungeongaiden.items.tickets;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class FiveStarTicket extends Item {

	private static final String AC_DRINK	= "DRINK";

	{
		image = ItemSpriteSheet.FIVE_STAR_TICKET;

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
			if (Random.Int(2) == 0) {
				GameScene.flash(0x80FFFFFF);
				detach(curUser.belongings.backpack);
				updateQuickslot();
				Dungeon.level.drop(Generator.random(Generator.Category.WEP_T5), curUser.pos).sprite.drop();
			} else {
				GameScene.flash(0x80FFFFFF);
				detach( curUser.belongings.backpack );
				updateQuickslot();
				Dungeon.level.drop(Generator.random(Generator.Category.ARMOR_T5), curUser.pos).sprite.drop();
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
		return 300 * quantity;
	}
}