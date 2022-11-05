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

import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;

public class Challenges {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int TUTORIAL                 = 1;
	public static final int SAKURA_TRAP              = 2;
	public static final int MUENZUKA_FORCE           = 4;
	public static final int REBIRTH_DAY		         = 8;
	public static final int RINGING_BLOOM 		     = 16;
	public static final int KYOUEN_RED_VIOLET        = 32;
	public static final int EIENTEI_ILLUSION         = 64;
	public static final int SWORD_OF_HISOU           = 128;
	public static final int TIME_EATER               = 256;
	public static final int NIGHT_CHESS              = 512;
	public static final int SENSENFUKOKU             = 1024;

	public static final int MAX_VALUE                = 2047;

	public static final String[] NAME_IDS = {
			"tutorial",
			"sakura_trap",
			"muenzuka_force",
			"rebirth_day",
			"ringing_bloom",
			"kyouen_red_violet",
			"eientei_illusion",
			"sword_of_hisou",
			"time_eater",
			"night_chess",
			"sensenfukoku"
	};

	public static final int[] MASKS = {
			TUTORIAL, SAKURA_TRAP, MUENZUKA_FORCE, REBIRTH_DAY, RINGING_BLOOM, KYOUEN_RED_VIOLET,
			EIENTEI_ILLUSION, SWORD_OF_HISOU, TIME_EATER, NIGHT_CHESS, SENSENFUKOKU
	};

	public static int activeChallenges(){
		int chCount = 0;
		for (int ch : Challenges.MASKS){
			if ((Dungeon.challenges & ch) != 0) chCount++;
		}
		return chCount;
	}

	public static boolean isItemBlocked( Item item ){
		return false;
	}
}