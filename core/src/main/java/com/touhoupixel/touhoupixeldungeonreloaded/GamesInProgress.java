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

package com.touhoupixel.touhoupixeldungeonreloaded;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class GamesInProgress {

	public static final int MAX_SLOTS = 4;

	//null means we have loaded info and it is empty, no entry means unknown.
	private static HashMap<Integer, Info> slotStates = new HashMap<>();
	public static int curSlot;

	public static HeroClass selectedClass;

	private static final String GAME_FOLDER = "game%d";
	private static final String GAME_FILE	= "game.dat";
	private static final String FLOOR_FILE = "depth%d.dat";
	private static final String FLOOR_BRANCH_FILE = "depth%d-branch%d.dat";

	public static boolean gameExists( int slot ){
		return FileUtils.dirExists(gameFolder(slot))
				&& FileUtils.fileLength(gameFile(slot)) > 1;
	}

	public static String gameFolder( int slot ){
		return Messages.format(GAME_FOLDER, slot);
	}

	public static String gameFile( int slot ){
		return gameFolder(slot) + "/" + GAME_FILE;
	}

	public static String floorFile(int slot, int floor, int branch ) {
		if (branch == 0) {
			return gameFolder(slot) + "/" + Messages.format(FLOOR_FILE, floor);
		} else {
			return gameFolder(slot) + "/" + Messages.format(FLOOR_BRANCH_FILE, floor, branch);
		}
	}

	public static int firstEmpty(){
		for (int i = 1; i <= MAX_SLOTS; i++){
			if (check(i) == null) return i;
		}
		return -1;
	}

	public static ArrayList<Info> checkAll(){
		ArrayList<Info> result = new ArrayList<>();
		for (int i = 1; i <= MAX_SLOTS; i++){
			Info curr = check(i);
			if (curr != null) result.add(curr);
		}
		Collections.sort(result, scoreComparator);
		return result;
	}

	public static Info check( int slot ) {

		if (slotStates.containsKey( slot )) {

			return slotStates.get( slot );

		} else if (!gameExists( slot )) {

			slotStates.put(slot, null);
			return null;

		} else {

			Info info;
			try {

				Bundle bundle = FileUtils.bundleFromFile(gameFile(slot));
				info = new Info();
				info.slot = slot;
				Dungeon.preview(info, bundle);

				//saves from before v0.9.3c are not supported
				if (info.version < ShatteredPixelDungeon.v0_9_3c) {
					info = null;
				}

			} catch (IOException e) {
				info = null;
			} catch (Exception e){
				ShatteredPixelDungeon.reportException( e );
				info = null;
			}

			slotStates.put( slot, info );
			return info;

		}
	}

	public static void set(int slot, int depth, int challenges, long seed, String customSeed, boolean daily,
						   Hero heroine) {
		Info info = new Info();
		info.slot = slot;

		info.floor = depth;
		info.challenges = challenges;

		info.seed = seed;
		info.customSeed = customSeed;
		info.daily = daily;

		info.level = heroine.lvl;
		info.str = heroine.STR;
		info.strBonus = heroine.STR() - heroine.STR;
		info.exp = heroine.exp;
		info.hp = heroine.HP;
		info.ht = heroine.HT;
		info.shld = heroine.shielding();
		info.heroClass = heroine.heroClass;
		info.armorTier = heroine.tier();

		info.goldCollected = Statistics.goldCollected;
		info.maxFloor = Statistics.highestFloor;

		slotStates.put( slot, info );
	}

	public static void setUnknown( int slot ) {
		slotStates.remove( slot );
	}

	public static void delete( int slot ) {
		slotStates.put( slot, null );
	}

	public static class Info {
		public int slot;

		public int floor;
		public int version;
		public int challenges;

		public long seed;
		public String customSeed;
		public boolean daily;

		public int level;
		public int str;
		public int strBonus;
		public int exp;
		public int hp;
		public int ht;
		public int shld;
		public HeroClass heroClass;
		public int armorTier;

		public int goldCollected;
		public int maxFloor;
	}

	public static final Comparator<GamesInProgress.Info> scoreComparator = new Comparator<GamesInProgress.Info>() {
		@Override
		public int compare(GamesInProgress.Info lhs, GamesInProgress.Info rhs ) {
			int lScore = (lhs.level * lhs.maxFloor * 100) + lhs.goldCollected;
			int rScore = (rhs.level * rhs.maxFloor * 100) + rhs.goldCollected;
			return (int)Math.signum( rScore - lScore );
		}
	};
}