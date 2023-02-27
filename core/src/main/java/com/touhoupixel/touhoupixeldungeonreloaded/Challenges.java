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
	public static final int TUTORIAL            = 1;
	public static final int DISTORTION          = 2;
	public static final int REBIRTH_DAY         = 4;
	public static final int RINGING_BLOOM       = 8;
	public static final int FUMO_ORDER_CLOSED   = 16;
	public static final int DREAM_LOGICAL_WORLD = 32;
	public static final int TIME_EATER          = 64;
	public static final int HISOUTEN_SWORD      = 128;
	public static final int STARRY_HOUSE        = 256;
	public static final int UNCONSCIOUS_ROSE    = 512;
	public static final int PATIENT_ROGUE       = 1024;

	public static final int MAX_VALUE           = 2047;

	public static final String[] NAME_IDS = {
			"tutorial",
			"distortion",
			"rebirth_day",
			"ringing_bloom",
			"fumo_order_closed",
			"dream_logical_world",
			"time_eater",
			"hisouten_sword",
			"starry_house",
			"unconscious_rose",
			"patient_rogue"
	};

	public static final int[] MASKS = {
			TUTORIAL, DISTORTION, REBIRTH_DAY, RINGING_BLOOM, FUMO_ORDER_CLOSED, DREAM_LOGICAL_WORLD,
			TIME_EATER, HISOUTEN_SWORD, STARRY_HOUSE, UNCONSCIOUS_ROSE, PATIENT_ROGUE
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