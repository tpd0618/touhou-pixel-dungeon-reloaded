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
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class LifeSpellcardChecker extends Item {

	private static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.LIFESPELLCARD_CHECKER;

		defaultAction = AC_DRINK;

		unique = true;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.remove(AC_DROP);
		actions.remove(AC_THROW);
		actions.remove(AC_DRINK);
		return actions;
	}

	@Override
	public String info() {

		String info = desc();

		info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats1", Statistics.power);
		info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats2", Statistics.life);
		info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats3", Statistics.lifefragment);
		info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats4", Statistics.spellcard);
		info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats5", Statistics.spellcardfragment);
		info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats6", Statistics.playercorruption);
		if (Dungeon.isChallenged(Challenges.TENSHI_PUNISHMENT)){
			info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats7", Statistics.tenshiEarthquake);
		}
		if (Dungeon.isChallenged(Challenges.TIME_EATER)){
			info += "\n\n" + Messages.get( LifeSpellcardChecker.class, "stats8", Statistics.timetrackbuff);
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
}