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

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.GoldenMimic;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mimic;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeonreloaded.items.journal.GuidePage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.GoldenKey;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Document;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.builders.Builder;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.builders.FigureEightBuilder;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.builders.LoopBuilder;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.Painter;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.Room;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.secret.SecretRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special.CardShopRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special.PitRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special.ShopRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special.SpecialRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.standard.EntranceRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.standard.ExitRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.standard.StandardRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.InversionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BlazingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BurningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ChillingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DisintegrationTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FrostTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.Trap;
import com.watabou.utils.Bundle;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class RegularLevel extends Level {

	protected ArrayList<Room> rooms;

	protected Builder builder;

	protected Room roomEntrance;
	protected Room roomExit;

	@Override
	protected boolean build() {

		builder = builder();

		ArrayList<Room> initRooms = initRooms();
		Random.shuffle(initRooms);

		do {
			for (Room r : initRooms){
				r.neigbours.clear();
				r.connected.clear();
			}
			rooms = builder.build((ArrayList<Room>)initRooms.clone());
		} while (rooms == null);

		return painter().paint(this, rooms);

	}

	protected ArrayList<Room> initRooms() {
		ArrayList<Room> initRooms = new ArrayList<>();
		initRooms.add ( roomEntrance = new EntranceRoom());
		initRooms.add( roomExit = new ExitRoom());

		//force max standard rooms and multiple by 1.5x for large levels
		int standards = standardRooms(feeling == Feeling.LARGE);
		if (feeling == Feeling.LARGE){
			standards = (int)Math.ceil(standards * 1.5f);
		}
		for (int i = 0; i < standards; i++) {
			StandardRoom s;
			do {
				s = StandardRoom.createRoom();
			} while (!s.setSizeCat( standards-i ));
			i += s.sizeCat.roomValue-1;
			initRooms.add(s);
		}

		if (Dungeon.shopOnLevel())
			initRooms.add(new ShopRoom());

		if (Dungeon.cardShopOnLevel())
			initRooms.add(new CardShopRoom());

		//force max special rooms and add one more for large levels
		int specials = specialRooms(feeling == Feeling.LARGE);
		if (feeling == Feeling.LARGE){
			specials++;
		}
		SpecialRoom.initForFloor();
		for (int i = 0; i < specials; i++) {
			SpecialRoom s = SpecialRoom.createRoom();
			if (s instanceof PitRoom) specials++;
			initRooms.add(s);
		}

		int secrets = SecretRoom.secretsForFloor(Dungeon.floor);
		//one additional secret for secret levels
		if (feeling == Feeling.SECRETS) secrets++;
		for (int i = 0; i < secrets; i++) {
			initRooms.add(SecretRoom.createRoom());
		}

		return initRooms;
	}

	protected int standardRooms(boolean forceMax){
		return 0;
	}

	protected int specialRooms(boolean forceMax){
		return 0;
	}

	protected Builder builder(){
		if (Random.Int(2) == 0){
			return new LoopBuilder()
					.setLoopShape( 2 ,
							Random.Float(0f, 0.65f),
							Random.Float(0f, 0.50f));
		} else {
			return new FigureEightBuilder()
					.setLoopShape( 2 ,
							Random.Float(0.3f, 0.8f),
							0f);
		}

	}

	protected abstract Painter painter();

	protected int nTraps() {
		return Statistics.difficulty == 6 ? Random.NormalIntRange(6, 8) : Random.NormalIntRange(4, 6);
	}

	protected Class<?>[] trapClasses(){
		return new Class<?>[]{InversionTrap.class};
	}

	protected float[] trapChances() {
		return new float[]{1};
	}

	@Override
	public int mobLimit() {
		int mobs = 8 + Random.Int(3);
		if (feeling == Feeling.LARGE){
			mobs = (int)Math.ceil(mobs * 1.25f);
		}
		if (Statistics.card56){
			mobs = (int)Math.ceil(mobs * 1.25f);
		}
		if (Statistics.card5){
			mobs = (int)Math.ceil(mobs * 0.85f);
		}
		//if (Dungeon.pandemoniumLevel()){
			//mobs = 100;
		//}
		return mobs;
	}

	@Override
	protected void createMobs() {
		int mobsToSpawn = mobLimit();

		ArrayList<Room> stdRooms = new ArrayList<>();
		for (Room room : rooms) {
			if (room instanceof StandardRoom) {
				for (int i = 0; i < ((StandardRoom) room).sizeCat.roomValue; i++) {
					stdRooms.add(room);
				}
			}
		}
		Random.shuffle(stdRooms);
		Iterator<Room> stdRoomIter = stdRooms.iterator();

		while (mobsToSpawn > 0) {
			Mob mob = createMob();
			Room roomToSpawn;

			if (!stdRoomIter.hasNext()) {
				stdRoomIter = stdRooms.iterator();
			}
			roomToSpawn = stdRoomIter.next();

			int tries = 30;
			do {
				mob.pos = pointToCell(roomToSpawn.random());
				tries--;
			} while (tries >= 0 && (findMob(mob.pos) != null || !passable[mob.pos] || solid[mob.pos] || mob.pos == exit));

			if (tries >= 0) {
				mobsToSpawn--;
				mobs.add(mob);

				//chance to add a second mob to this room, except on floor 1
				if (mobsToSpawn > 0 && Random.Int(4) == 0){
					mob = createMob();

					tries = 30;
					do {
						mob.pos = pointToCell(roomToSpawn.random());
						tries--;
					} while (tries >= 0 && (findMob(mob.pos) != null || !passable[mob.pos] || solid[mob.pos] || mob.pos == exit));

					if (tries >= 0) {
						mobsToSpawn--;
						mobs.add(mob);
					}
				}
			}
		}

		for (Mob m : mobs){
			if (map[m.pos] == Terrain.HIGH_GRASS || map[m.pos] == Terrain.FURROWED_GRASS) {
				map[m.pos] = Terrain.GRASS;
				losBlocking[m.pos] = false;
			}

		}

	}

	@Override
	public int randomRespawnCell( Char ch ) {
		int count = 0;
		int cell = -1;

		while (true) {

			if (++count > 30) {
				return -1;
			}

			Room room = randomRoom( StandardRoom.class );
			if (room == null || room == roomEntrance) {
				continue;
			}

			cell = pointToCell(room.random(1));
			if (!heroFOV[cell]
					&& Actor.findChar( cell ) == null
					&& passable[cell]
					&& !solid[cell]
					&& (!Char.hasProp(ch, Char.Property.NONE) || openSpace[cell])
					&& room.canPlaceCharacter(cellToPoint(cell), this)
					&& cell != exit) {
				return cell;
			}

		}
	}

	@Override
	public int randomDestination( Char ch ) {

		int count = 0;
		int cell = -1;

		while (true) {

			if (++count > 30) {
				return -1;
			}

			Room room = Random.element( rooms );
			if (room == null) {
				continue;
			}

			ArrayList<Point> points = room.charPlaceablePoints(this);
			if (!points.isEmpty()){
				cell = pointToCell(Random.element(points));
				if (openSpace[cell]) {
					return cell;
				}
			}

		}
	}

	@Override
	protected void createItems() {
		int nItems = 8;

		if (Statistics.difficulty == 6){
			nItems -= 1;
		}

		if (Statistics.card65) {
			nItems += 1;
		}

		if (Dungeon.floor > 40) {
			nItems += 1;
		}

		for (int i=0; i < nItems; i++) {

			Item toDrop = Generator.random();
			if (toDrop == null) continue;

			int cell = randomDropCell();
			if (map[cell] == Terrain.HIGH_GRASS || map[cell] == Terrain.FURROWED_GRASS) {
				map[cell] = Terrain.GRASS;
				losBlocking[cell] = false;
			}

			Heap.Type type = null;
			switch (Random.Int( 20 )) {
			case 0:
				type = Heap.Type.SKELETON;
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				type = Heap.Type.CHEST;
				break;
			case 5:
				if (Dungeon.floor > 1 && findMob(cell) == null){
					mobs.add(Mimic.spawnAt(cell, toDrop));
					continue;
				}
				type = Heap.Type.CHEST;
				break;
			default:
				type = Heap.Type.HEAP;
				break;
			}

			if ((toDrop instanceof Artifact && Random.Int(2) == 0) ||
					(toDrop.isUpgradable() && Random.Int(4 - toDrop.level()) == 0)){

				if (Random.Int(10) == 0 && findMob(cell) == null){
					mobs.add(Mimic.spawnAt(cell, toDrop, GoldenMimic.class));
				} else {
					Heap dropped = drop(toDrop, cell);
					if (heaps.get(cell) == dropped) {
						dropped.type = Heap.Type.LOCKED_CHEST;
						addItemToSpawn(new GoldenKey(Dungeon.floor));
					}
				}
			} else {
				Heap dropped = drop( toDrop, cell );
				dropped.type = type;
				if (type == Heap.Type.SKELETON){
					dropped.setHauntedIfCursed();
				}
			}

		}

		for (Item item : itemsToSpawn) {
			int cell = randomDropCell();
			drop( item, cell ).type = Heap.Type.HEAP;
			if (map[cell] == Terrain.HIGH_GRASS || map[cell] == Terrain.FURROWED_GRASS) {
				map[cell] = Terrain.GRASS;
				losBlocking[cell] = false;
			}
		}

		//use a separate generator for this to prevent held items, meta progress, and talents from affecting levelgen
		//we can use a random long for the seed as it will be the same long every time
		Random.pushGenerator( Random.Long() );

		//guide pages
		Collection<String> allPages = Document.ADVENTURERS_GUIDE.pageNames();
		ArrayList<String> missingPages = new ArrayList<>();
		for ( String page : allPages){
			if (!Document.ADVENTURERS_GUIDE.isPageFound(page)){
				missingPages.add(page);
			}
		}

		//a total of 6 pages drop randomly, the rest are specially dropped or are given at the start
		missingPages.remove(Document.GUIDE_SEARCHING);

		//chance to find a page is 0/25/50/75/100% for floors 1/2/3/4/5+
		float dropChance = 0.25f*(Dungeon.floor -1);
		if (!missingPages.isEmpty() && Random.Float() < dropChance){
			GuidePage p = new GuidePage();
			p.page(missingPages.get(0));
			int cell = randomDropCell();
			if (map[cell] == Terrain.HIGH_GRASS || map[cell] == Terrain.FURROWED_GRASS) {
				map[cell] = Terrain.GRASS;
				losBlocking[cell] = false;
			}
			drop( p, cell );
		}

		Random.popGenerator();

	}

	public ArrayList<Room> rooms() {
		return new ArrayList<>(rooms);
	}

	//FIXME pit rooms shouldn't be problematic enough to warrant this
	public boolean hasPitRoom(){
		for (Room r : rooms) {
			if (r instanceof PitRoom) {
				return true;
			}
		}
		return false;
	}

	protected Room randomRoom( Class<?extends Room> type ) {
		Random.shuffle( rooms );
		for (Room r : rooms) {
			if (type.isInstance(r)) {
				return r;
			}
		}
		return null;
	}

	public Room room( int pos ) {
		for (Room room : rooms) {
			if (room.inside( cellToPoint(pos) )) {
				return room;
			}
		}

		return null;
	}

	protected int randomDropCell(){
		return randomDropCell(StandardRoom.class);
	}

	protected int randomDropCell( Class<?extends Room> roomType ) {
		int tries = 100;
		while (tries-- > 0) {
			Room room = randomRoom( roomType );
			if (room == null){
				return -1;
			}
			if (room != roomEntrance) {
				int pos = pointToCell(room.random());
				if (passable[pos] && !solid[pos]
						&& pos != exit
						&& heaps.get(pos) == null
						&& findMob(pos) == null) {

					Trap t = traps.get(pos);

					//items cannot spawn on traps which destroy items
					if (t == null ||
							! (t instanceof BurningTrap || t instanceof BlazingTrap
							|| t instanceof ChillingTrap || t instanceof FrostTrap
							|| t instanceof ExplosiveTrap || t instanceof DisintegrationTrap)) {

						return pos;
					}
				}
			}
		}
		return -1;
	}

	@Override
	public int fallCell( boolean fallIntoPit ) {
		if (fallIntoPit) {
			for (Room room : rooms) {
				if (room instanceof PitRoom) {
					int result;
					do {
						result = pointToCell(room.random());
					} while (traps.get(result) != null
							|| findMob(result) != null
							|| heaps.get(result) != null);
					return result;
				}
			}
		}

		return super.fallCell( false );
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( "rooms", rooms );
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );

		rooms = new ArrayList<>( (Collection<Room>) ((Collection<?>) bundle.getCollection( "rooms" )) );
		for (Room r : rooms) {
			r.onLevelLoad( this );
			if (r instanceof EntranceRoom ){
				roomEntrance = r;
			} else if (r instanceof ExitRoom ){
				roomExit = r;
			}
		}
	}

}
