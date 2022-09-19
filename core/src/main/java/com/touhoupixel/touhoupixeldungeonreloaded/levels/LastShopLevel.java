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

package com.touhoupixel.touhoupixeldungeonreloaded.levels;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Bones;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.builders.Builder;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.builders.LineBuilder;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.CityPainter;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.Painter;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.Room;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.standard.EntranceRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.standard.ExitRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.standard.ImpShopRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.watabou.noosa.Group;

import java.util.ArrayList;

public class LastShopLevel extends RegularLevel {
	
	{
		color1 = 0x4b6636;
		color2 = 0xf2f2f2;
	}
	
	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_15;
	}
	
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_15;
	}
	
	@Override
	protected boolean build() {
		feeling = Feeling.CHASM;
		if (super.build()){
			
			for (int i=0; i < length(); i++) {
				if (map[i] == Terrain.SECRET_DOOR) {
					map[i] = Terrain.DOOR;
				}
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected ArrayList<Room> initRooms() {
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add ( roomEntrance = new EntranceRoom());
		rooms.add( new ImpShopRoom() );
		rooms.add( roomExit = new ExitRoom());
		
		return rooms;
	}
	
	@Override
	protected Builder builder() {
		return new LineBuilder()
				.setPathVariance(0f)
				.setPathLength(1f, new float[]{1})
				.setTunnelLength(new float[]{0, 0, 1}, new float[]{1});
	}
	
	@Override
	protected Painter painter() {
		return new CityPainter()
				.setWater( 0.10f, 4 )
				.setGrass( 0.10f, 3 );
	}
	
	@Override
	public Mob createMob() {
		return null;
	}
	
	@Override
	protected void createMobs() {
	}
	
	public Actor addRespawner() {
		return null;
	}
	
	@Override
	protected void createItems() {
		Item item = Bones.get();
		if (item != null) {
			int pos;
			do {
				pos = pointToCell(roomEntrance.random());
			} while (pos == entrance);
			drop( item, pos ).setHauntedIfCursed().type = Heap.Type.REMAINS;
		}
	}
	
	@Override
	public int randomRespawnCell( Char ch ) {
		int cell;
		do {
			cell = pointToCell( roomEntrance.random() );
		} while (!passable[cell]
				|| Actor.findChar(cell) != null);
		return cell;
	}
	
	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HakugyokurouLevel.class, "water_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(HakugyokurouLevel.class, "high_grass_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.ENTRANCE:
				return Messages.get(HakugyokurouLevel.class, "entrance_desc");
			case Terrain.EXIT:
				return Messages.get(HakugyokurouLevel.class, "exit_desc");
			case Terrain.WALL_DECO:
			case Terrain.EMPTY_DECO:
				return Messages.get(HakugyokurouLevel.class, "deco_desc");
			case Terrain.EMPTY_SP:
				return Messages.get(HakugyokurouLevel.class, "sp_desc");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(HakugyokurouLevel.class, "statue_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(HakugyokurouLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}

	@Override
	public Group addVisuals( ) {
		super.addVisuals();
		HakugyokurouLevel.addCityVisuals(this, visuals);
		return visuals;
	}
}
