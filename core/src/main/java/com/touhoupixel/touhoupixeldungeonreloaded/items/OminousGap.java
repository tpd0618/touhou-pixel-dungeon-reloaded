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

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Identification;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class OminousGap extends Item {

	private static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.OMINOUS_GAP;

		defaultAction = AC_DRINK;

		stackable = true;
		unique = true;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add(AC_DRINK);
		return actions;
	}

	@Override
	public void execute(final Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals(AC_DRINK)) {
			if (Statistics.highestFloor > 10){
				GLog.p( Messages.get(this, "too_far") );
			} else if (Statistics.difficulty == 6){
				GLog.p( Messages.get(this, "rank_max") ); //just in case
			} else {
				Statistics.difficulty += 1;

				curUser.spendAndNext(1f);

				Sample.INSTANCE.play(Assets.Sounds.DRINK);

				curUser.sprite.parent.add(new Identification(curUser.sprite.center().offset(0, -16)));

				GLog.p(Messages.get(this, "difficulty_change"));

				curItem.detach(curUser.belongings.backpack);
			}
		}
	}

	@Override
	public String info() {

		String info = desc();

		if (Statistics.difficulty == 1) {
			info += "\n\n" + Messages.get(this, "easy");
			info += "\n\n" + Messages.get(this, "next_normal");
		}
		if (Statistics.difficulty == 2) {
			info += "\n\n" + Messages.get(this, "normal");
			info += "\n\n" + Messages.get(this, "next_hard");
		}
		if (Statistics.difficulty == 3) {
			info += "\n\n" + Messages.get(this, "hard");
			info += "\n\n" + Messages.get(this, "next_lunatic");
		}
		if (Statistics.difficulty == 4) {
			info += "\n\n" + Messages.get(this, "lunatic");
			info += "\n\n" + Messages.get(this, "next_overdrive");
		}
		if (Statistics.difficulty == 5) {
			info += "\n\n" + Messages.get(this, "overdrive");
			info += "\n\n" + Messages.get(this, "next_risky");
		}
		return info;
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
		return 30 * quantity;
	}
}
