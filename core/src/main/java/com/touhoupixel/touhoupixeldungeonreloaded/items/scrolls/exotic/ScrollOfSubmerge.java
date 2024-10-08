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

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfAnchor;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.InterlevelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.utils.Random;

public class ScrollOfSubmerge extends ExoticScroll {
	
	{
		icon = ItemSpriteSheet.Icons.SCROLL_SUBMERGE;
	}

	@Override
	public void doRead() {

		identify();
		readAnimation();

		if (!Dungeon.interfloorTeleportAllowed() && !(Dungeon.level.map[curUser.pos] == Terrain.WATER)) {
			GLog.w(Messages.get(ScrollOfTeleportation.class, "no_submerge"));
			return;
		}
		if (Dungeon.floor == 1){
			GLog.w(Messages.get(ScrollOfTeleportation.class, "floor_one"));
		} else {
			InterlevelScene.mode = InterlevelScene.Mode.RETURN;
			InterlevelScene.returnFloor = Dungeon.floor - 1;
			InterlevelScene.returnBranch = 0;
			InterlevelScene.returnPos = -1;
			Game.switchScene(InterlevelScene.class);
		}
	}
}