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

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfMagicMapping extends ExoticScroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_MAGIC_MAPPING;
	}

	@Override
	public void doRead() {

		if (Dungeon.floor > 35) {
			GLog.w(Messages.get(this, "unable"));
		} else {
			int length = Dungeon.level.length();
			int[] map = Dungeon.level.map;
			boolean[] mapped = Dungeon.level.mapped;
			boolean[] discoverable = Dungeon.level.discoverable;

			boolean noticed = false;

			for (int i = 0; i < length; i++) {

				int terr = map[i];

				if (discoverable[i]) {

					mapped[i] = true;
					if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {

						Dungeon.level.discover(i);

						if (Dungeon.level.heroFOV[i]) {
							GameScene.discoverTile(i, terr);
							discover(i);

							noticed = true;
						}
					}
				}
			}
			GameScene.updateFog();

			GLog.i(Messages.get(this, "layout"));
			if (noticed) {
				Sample.INSTANCE.play(Assets.Sounds.SECRET);
			}

			SpellSprite.show(curUser, SpellSprite.MAP);

		}
		Sample.INSTANCE.play(Assets.Sounds.READ);
		identify();
		readAnimation();
	}
	
	public static void discover( int cell ) {
		CellEmitter.get( cell ).start( Speck.factory( Speck.DISCOVER ), 0.1f, 4 );
	}
}
