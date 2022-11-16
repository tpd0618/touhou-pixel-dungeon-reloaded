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
	public static final int PITFALL_OF_LIFE          = 2;
	public static final int FANTASY_EXORCISM         = 4;
	public static final int REBIRTH_DAY		         = 8;
	public static final int ETERNAL_DREAM 		     = 16;
	public static final int UNIDENTIFIED_OBJECT      = 32;
	public static final int INVINCIBLE_GENSOKYO      = 64;
	public static final int SWORD_OF_HISOU           = 128;
	public static final int TIME_EATER               = 256;
	public static final int LUNAR_CAPITAL_CHESS      = 512;
	public static final int RINGING_BLOOM            = 1024;

	public static final int MAX_VALUE                = 2047;

	public static final String[] NAME_IDS = {
			"tutorial",
			"pitfall_of_life",
			"fantasy_exorcism",
			"rebirth_day",
			"eternal_dream",
			"unidentified_object",
			"invincible_gensokyo",
			"sword_of_hisou",
			"time_eater",
			"lunar_capital_chess",
			"ringing_bloom"
	};

	public static final int[] MASKS = {
			TUTORIAL, PITFALL_OF_LIFE, FANTASY_EXORCISM, REBIRTH_DAY, ETERNAL_DREAM, UNIDENTIFIED_OBJECT,
			INVINCIBLE_GENSOKYO, SWORD_OF_HISOU, TIME_EATER, LUNAR_CAPITAL_CHESS, RINGING_BLOOM
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