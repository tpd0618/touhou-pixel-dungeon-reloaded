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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Corruption;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Murasa;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Splash;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AnchorTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ScrollOfAnchor extends Scroll {
	private static final float DELAY = 1f;

	{
		icon = ItemSpriteSheet.Icons.SCROLL_ANCHOR;
	}

	@Override
	public String desc() {
		if (Statistics.suwakorelic_active){
			return Messages.get(this, "desc2");
		}
		else{
			return Messages.get(this, "desc1");
		}
	}

	@Override
	public void doRead() {
		if (Statistics.suwakorelic_active){
			ArrayList<Integer> candidates = new ArrayList<>();

			PathFinder.buildDistanceMap( curUser.pos, BArray.or( Dungeon.level.solid, Dungeon.level.avoid, null ), 3 );
			for (int i = 0; i < PathFinder.distance.length; i++) {
				if (PathFinder.distance[i] < Integer.MAX_VALUE) { // create water
					Level.set( i, Terrain.WATER );
					GameScene.updateMap( i );
					if (Random.Int(10) == 0){
						Splash.at( DungeonTilemap.tileCenterToWorld( i ), -PointF.PI/2, PointF.PI/2, 0x5bc1e3, 100, 0.01f);
					}
					if (Actor.findChar(i) == null){
						candidates.add( i );
					}
				}
			}
			// summons Murasa

			int nMobs = 2;

			ArrayList<Integer> respawnPoints = new ArrayList<>();

			while (nMobs > 0 && candidates.size() > 0) {
				int index = Random.index( candidates );

				respawnPoints.add( candidates.remove( index ) );
				nMobs--;
			}

			ArrayList<Mob> mobs = new ArrayList<>();

			for (Integer point : respawnPoints) {
				Mob mob = new Murasa();
				Buff.affect(mob, Corruption.class);
				mob.state = mob.WANDERING;
				mob.pos = point;
				GameScene.add(mob, DELAY);
				mobs.add(mob);
				ScrollOfTeleportation.appear(mob, mob.pos);
				Dungeon.level.occupyCell(mob);
			}

			GLog.p(Messages.get(this, "anchor"));
		}
		else {
			new AnchorTrap().set(curUser.pos).activate();
			GLog.w(Messages.get(this, "anchor"));
		}

		identify();

		curUser.sprite.centerEmitter().start(Speck.factory(Speck.DISCOVER), 0.3f, 3);
		Sample.INSTANCE.play(Assets.Sounds.ALERT);

		readAnimation();
	}

	@Override
	public int value() {
		return isKnown() ? 40 * quantity : super.value();
	}
}
