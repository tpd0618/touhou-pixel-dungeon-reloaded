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
	public static final int TOWER_OF_FORTUNE = 1;
	public static final int DISTORTION       = 2;
	public static final int RE_BIRTH_DAY     = 4;
	public static final int RINGING_BLOOM    = 8;
	public static final int BLESSING_CHORD   = 16;
	public static final int CALL_THE_SHOTS   = 32;
	public static final int RE_HOURAI_ELIXIR = 64;
	public static final int LAST_SURPRISE    = 128;
	public static final int INF_TIME_EATER   = 256;
	public static final int DOUBLE_SPOILER   = 512;
	public static final int LUNATIC_PERFECT  = 1024;

	public static final int MAX_VALUE        = 2047;

	public static final String[] NAME_IDS = {
			"tower_of_fortune",
			"distortion",
			"re_birth_day",
			"ringing_bloom",
			"blessing_chord",
			"call_the_shots",
			"re_hourai_elixir",
			"last_surprise",
			"inf_time_eater",
			"double_spoiler",
			"lunatic_perfect"
	};

	public static final int[] MASKS = {
			TOWER_OF_FORTUNE, DISTORTION, RE_BIRTH_DAY, RINGING_BLOOM, BLESSING_CHORD, CALL_THE_SHOTS,
			RE_HOURAI_ELIXIR, LAST_SURPRISE, INF_TIME_EATER, DOUBLE_SPOILER, LUNATIC_PERFECT
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