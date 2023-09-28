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

package com.touhoupixel.touhoupixeldungeongaiden.items.herbs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.KeyHeal;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.InversionTrap;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;

public class PurityHerb extends Herb {

	{
		image = ItemSpriteSheet.HERB;
	}

	@Override
	public void execute(Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals( AC_EAT )) {
			if (heroine.buff(Inversion.class) != null) {
				heroine.damage(heroine.HT / 2, heroine);
				if (heroine == Dungeon.heroine && !heroine.isAlive()) {
					Dungeon.fail(InversionTrap.class);
					GLog.n( Messages.get(Inversion.class, "ondeath") );
				}
			} else {
				heroine.HP = Math.min(heroine.HP + 50, heroine.HT);
			}
			Buff.affect(curUser, KeyHeal.class, KeyHeal.DURATION);
		}
	}
}
