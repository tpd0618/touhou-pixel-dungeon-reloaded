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
	public static final int TUTORIAL             = 1;
	public static final int DISTORTION           = 2;
	public static final int REBIRTH_DAY          = 4;
	public static final int RINGING_BLOOM        = 8;
	public static final int SCALES_OF_JUSTICE    = 16;
	public static final int DREAM_LOGICAL_WORLD  = 32;
	public static final int TIME_EATER           = 64;
	public static final int SWORD_OF_HISOU       = 128;
	public static final int BUNBUN_DELIVERY      = 256;
	public static final int UNCONSCIOUS_ROSE     = 512;
	public static final int KETSUI_KISUNA_JIGOKU = 1024;

	public static final int MAX_VALUE            = 2047;

	public static final String[] NAME_IDS = {
			"tutorial",
			"distortion",
			"rebirth_day",
			"ringing_bloom",
			"scales_of_justice",
			"dream_logical_world",
			"time_eater",
			"sword_of_hisou",
			"bunbun_delivery",
			"unconscious_rose",
			"ketsui_kisuna_jigoku"
	};

	public static final int[] MASKS = {
			TUTORIAL, DISTORTION, REBIRTH_DAY, RINGING_BLOOM, SCALES_OF_JUSTICE, DREAM_LOGICAL_WORLD,
			TIME_EATER, SWORD_OF_HISOU, BUNBUN_DELIVERY, UNCONSCIOUS_ROSE, KETSUI_KISUNA_JIGOKU
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