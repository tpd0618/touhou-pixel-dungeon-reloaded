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

package com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.standard;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.Painter;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.Room;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class EntranceRoom extends StandardRoom {

	@Override
	public int minWidth() {
		return Math.max(super.minWidth(), 5);
	}

	@Override
	public int minHeight() {
		return Math.max(super.minHeight(), 5);
	}

	@Override
	public boolean canMerge(Level l, Point p, int mergeTerrain) {
		return false;
	}

	public void paint( Level level ) {

		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY );

		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}

		int entrance;
		do {
			entrance = level.pointToCell(random(2));
		} while (level.findMob(entrance) != null);
		Painter.set( level, entrance, Terrain.ENTRANCE );

		if (Dungeon.floor == 1){
			level.transitions.add(new LevelTransition(level, entrance, LevelTransition.Type.SURFACE));
		} else {
			level.transitions.add(new LevelTransition(level, entrance, LevelTransition.Type.REGULAR_EXIT));
		}

		//use a separate generator here so meta progression doesn't affect levelgen
		Random.pushGenerator();
		Random.popGenerator();
	}

	@Override
	public boolean connect(Room room) {
		//cannot connect to exit, otherwise works normally
		if (room instanceof ExitRoom)   return false;
		else                            return super.connect(room);
	}

}