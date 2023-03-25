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
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.IronKey;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Notes;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.InversionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class PotionOfLightHealing extends Potion {

	{
		icon = ItemSpriteSheet.Icons.POTION_LIGHTHEALING;

		bones = true;
	}

	@Override
	public void apply(Hero hero) {
		identify();
		if (Statistics.card26){
			Buff.detach(curUser, Degrade.class);
		}
		if (hero.buff(Inversion.class) != null) {
			hero.damage(hero.HT / 2, hero);
			if (hero == Dungeon.hero && !hero.isAlive()) {
				Dungeon.fail(InversionTrap.class);
				GLog.n( Messages.get(Inversion.class, "ondeath") );
			}
		} else {
			hero.HP = Math.min(hero.HP + 50*(Notes.keyCount(new IronKey(Dungeon.depth))+1), hero.HT);
			GLog.p(Messages.get(this, "lightheal"));
		}
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}