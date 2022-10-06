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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic;

import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HighStress;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SupernaturalBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class ScrollOfYuyukoRage extends ExoticScroll {
	
	{
		icon = ItemSpriteSheet.Icons.SCROLL_YUYUKORAGE;
	}
	
	@Override
	public void doRead() {
		Buff.prolong(curUser, HighStress.class, HighStress.DURATION);
		Buff.prolong(curUser, Hisou.class, Hisou.DURATION);
		Buff.prolong(curUser, MindVision.class, MindVision.DURATION);
		if (curUser.buff(SupernaturalBorder.class) == null) {
			Statistics.bordercount = 0;
			Buff.prolong(curUser, SupernaturalBorder.class, SupernaturalBorder.DURATION);
		}

		identify();
		readAnimation();
	}
}