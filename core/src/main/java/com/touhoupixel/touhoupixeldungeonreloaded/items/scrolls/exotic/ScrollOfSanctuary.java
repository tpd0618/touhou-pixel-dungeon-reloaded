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

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class ScrollOfSanctuary extends ExoticScroll {
	
	{
		icon = ItemSpriteSheet.Icons.SCROLL_SANCTUARY;
	}
	
	@Override
	public void doRead() {
		if (!(Dungeon.level.map[curUser.pos] == Terrain.ENTRANCE) && !(Dungeon.level.map[curUser.pos] == Terrain.EXIT) && !(Dungeon.level.map[curUser.pos] == Terrain.UNLOCKED_EXIT) && !Dungeon.bossLevel()) {
			Level.set(curUser.pos, Terrain.PEDESTAL);
			GameScene.updateMap(curUser.pos);
			GLog.p(Messages.get(this, "sanctuary"));
		} else {
			GLog.p(Messages.get(this, "sanctuary_failed"));
		}

		if (!Dungeon.isChallenged(Challenges.UNIDENTIFIED_OBJECT)) {
			identify();
		}
		readAnimation();
	}
}