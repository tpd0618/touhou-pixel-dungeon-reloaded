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

package com.touhoupixel.touhoupixeldungeonreloaded.levels.traps;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.KindofMisc;
import com.touhoupixel.touhoupixeldungeonreloaded.items.UpgradeCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class AntiRingTrap extends Trap {

	{
		color = ORANGE;
		shape = LARGE_DOT;

		avoidsHallways = false;
	}

	@Override
	public void activate() {
		Char c = Actor.findChar(pos);
		if (c != null && c == Dungeon.hero) {
			Ring ring = Dungeon.hero.belongings.ring;
			KindofMisc misc = Dungeon.hero.belongings.misc;
			if (ring != null) {
				Dungeon.hero.belongings.ring = null;
				Dungeon.quickslot.clearItem(ring);
				ring.updateQuickslot();
				GLog.w(Messages.get(this, "antiringfirst"));
			}
			if (misc != null && misc instanceof Ring) {
				Dungeon.hero.belongings.misc = null;
				Dungeon.quickslot.clearItem(misc);
				misc.updateQuickslot();
				GLog.w(Messages.get(this, "antiringsecond"));
			}
		}
		if (Dungeon.level.heroFOV[pos]) {
			GameScene.flash(0x80FFFFFF);
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
		}
	}
}