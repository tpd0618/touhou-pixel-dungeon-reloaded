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
	public static final int TUTORIAL                   = 1;
	public static final int MAX_POWER_MODE             = 2;
	public static final int GIRLS_BLOSSOM_PROJECT      = 4;
	public static final int DISTORTION                 = 8;
	public static final int REINCARNATION_APPLE        = 16;
	public static final int RINGING_BLOOM              = 32;
	public static final int DREAM_LOGICAL_WORLD        = 64;
	public static final int TIME_EATER                 = 128;
	public static final int KEYSTONE_MISSILE           = 256;
	public static final int BUNBUN_DELIVERY            = 512;
	public static final int KETSUI_KISUNA_JIGOKU_TACHI = 1024;

	public static final int MAX_VALUE                  = 2047;

	public static final String[] NAME_IDS = {
			"tutorial",
			"max_power_mode",
			"girls_blossom_project",
			"distortion",
			"reincarnation_apple",
			"ringing_bloom",
			"dream_logical_world",
			"time_eater",
			"keystone_missile",
			"bunbun_delivery",
			"ketsui_kisuna_jigoku_tachi"
	};

	public static final int[] MASKS = {
			TUTORIAL, MAX_POWER_MODE, GIRLS_BLOSSOM_PROJECT, DISTORTION, REINCARNATION_APPLE, RINGING_BLOOM,
			DREAM_LOGICAL_WORLD, TIME_EATER, KEYSTONE_MISSILE, BUNBUN_DELIVERY, KETSUI_KISUNA_JIGOKU_TACHI
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