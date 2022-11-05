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
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class SpellcardCaster extends Item {

	private static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.SPELLCARD_CASTER;

		defaultAction = AC_DRINK;

		unique = true;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.remove(AC_DROP);
		actions.remove(AC_THROW);
		actions.add(AC_DRINK);
		return actions;
	}

	@Override
	public void execute(final Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_DRINK)) {
			if (Statistics.spellcard < 1) {
				GLog.w(Messages.get(this, "nospellcard"));
			}
			if (Statistics.spellcard > 0) {
				Statistics.spellcard -= 1;
				GameScene.flash(0x80FFFFFF);
				Sample.INSTANCE.play(Assets.Sounds.BLAST);
				if (Dungeon.isChallenged(Challenges.KYOUEN_RED_VIOLET)) {
					Statistics.mood += 1;
				}
				if (Statistics.card46) {
					Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
				} else {
					Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
				}
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
}