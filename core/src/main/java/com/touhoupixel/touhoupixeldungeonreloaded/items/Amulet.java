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

package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.Badges;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class Amulet extends Item {

	private static final String AC_END = "END";

	{
		image = ItemSpriteSheet.AMULET;

		unique = true;
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		//actions.remove(AC_DROP);
		//actions.remove(AC_THROW);
		return actions;
	}

	@Override
	public boolean doPickUp(Hero heroine, int pos) {
		Badges.validateChampion(Challenges.activeChallenges());
		Badges.lifeBind();
		Badges.bombBind();
		Badges.torchBind();
		Badges.livingAntiqueStore();
		return super.doPickUp(heroine, pos);
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}
}