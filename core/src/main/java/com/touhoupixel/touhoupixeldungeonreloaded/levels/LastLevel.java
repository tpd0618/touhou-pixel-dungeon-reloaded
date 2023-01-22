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

package com.touhoupixel.touhoupixeldungeonreloaded.levels;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Amulet;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.watabou.noosa.audio.Music;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class LastLevel extends Level {

	{
		viewDistance = 100;

		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	public void playLevelMusic() {
		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.FLOOR_19, Assets.Music.FLOOR_19, Assets.Music.FLOOR_19},
				new float[]{1, 1, 0.5f},
				false);
	}

	private static int WIDTH = 13;
	private static int HEIGHT = 13;

	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_20;
	}

	@Override
	public String waterTex() {
		return Assets.Environment.WATER_20;
	}

	@Override
	protected boolean build() {
		setSize(WIDTH, HEIGHT);

		transitions.add(new LevelTransition(this, 32, LevelTransition.Type.SURFACE));
		transitions.add(new LevelTransition(this, 136, LevelTransition.Type.REGULAR_EXIT));

		buildLevel();

		return true;
	}

	private static final short n = -1;
	private static final short W = Terrain.WALL;
	private static final short d = Terrain.EMPTY;
	private static final short A = Terrain.EMPTY; //items and npcs
	private static final short S = Terrain.EXIT;
	private static final short E = Terrain.ENTRANCE;
	private static final short s = Terrain.PEDESTAL;
	private static final short c = Terrain.EMPTY_SP;
	private static final short e = Terrain.WATER;

	private static short[] level = {
			W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, e, e, e, e, S, e, e, e, e, W, W,
			W, W, e, d, d, d, d, d, d, d, e, W, W,
			W, W, e, d, c, c, c, c, c, d, e, W, W,
			W, W, e, d, c, s, s, s, c, d, e, W, W,
			W, W, e, d, c, s, A, s, c, d, e, W, W,
			W, W, e, d, c, s, s, s, c, d, e, W, W,
			W, W, e, d, c, c, c, c, c, d, e, W, W,
			W, W, e, d, d, d, d, d, d, d, e, W, W,
			W, W, e, e, e, e, E, e, e, e, e, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W
	};

	private void buildLevel(){
		int pos = 0 + 0*width();

		short[] levelTiles = level;
		for (int i = 0; i < levelTiles.length; i++){
			if (levelTiles[i] != n) map[pos] = levelTiles[i];

			pos++;
		}
	}

	@Override
	protected void createMobs() {
	}

	@Override
	protected void createItems() {
		drop( new Amulet(), 84 );
	}

	@Override
	public int randomRespawnCell( Char ch ) {
		int cell;
		do {
			cell = entrance() + PathFinder.NEIGHBOURS8[Random.Int(8)];
		} while (!passable[cell]
				|| Actor.findChar(cell) != null);
		return cell;
	}

	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HakureiShrineLevel.class, "water_name");
			case Terrain.WALL_DECO:
				return Messages.get(HakureiShrineLevel.class, "wall_deco_name");
			case Terrain.STATUE:
				return Messages.get(HakureiShrineLevel.class, "statue_name");
			case Terrain.LOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "locked_exit_name");
			case Terrain.UNLOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "unlocked_exit_name");
			default:
				return super.tileName( tile );
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.ENTRANCE:
				return Messages.get(HakureiShrineLevel.class, "entrance_desc");
			case Terrain.EXIT:
				return Messages.get(HakureiShrineLevel.class, "exit_desc");
			case Terrain.EMPTY_DECO:
				return Messages.get(HakureiShrineLevel.class, "empty_deco_desc");
			case Terrain.WALL_DECO:
				return Messages.get(HakureiShrineLevel.class, "wall_deco_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(HakureiShrineLevel.class, "bookshelf_desc");
			case Terrain.STATUE:
				return Messages.get(HakureiShrineLevel.class, "statue_desc");
			case Terrain.LOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "locked_exit_desc");
			case Terrain.UNLOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "unlocked_exit_desc");
			default:
				return super.tileDesc( tile );
		}
	}
}