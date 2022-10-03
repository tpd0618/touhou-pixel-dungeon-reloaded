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

package com.touhoupixel.touhoupixeldungeonreloaded.items.potions;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiHeal;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AntiHealTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class PotionOfYingYang extends Potion {

	{
		icon = ItemSpriteSheet.Icons.POTION_DOUBLESPEED;
	}

	@Override
	public void apply(Hero hero) {
		identify();
		if (hero.HP % 2 == 1) {
			if (hero.buff(AntiHeal.class) != null && hero.HP % 2 == 1) {
				hero.damage(hero.HT / 2, hero);
				if (hero == Dungeon.hero && !hero.isAlive()) {
					Dungeon.fail(AntiHealTrap.class);
					GLog.n( Messages.get(AntiHeal.class, "ondeath") );
				}
			} else {
				hero.HP = Math.min(hero.HP + 150, hero.HT);
				GLog.p(Messages.get(this, "yingyangheal"));
			}
		} else Buff.affect( hero, Drowsy.class );
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}
