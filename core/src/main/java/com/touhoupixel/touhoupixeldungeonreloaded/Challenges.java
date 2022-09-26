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
	public static final int YUYUKO_DEADLY_TRAP       = 1;
	public static final int REBIRTH_DAY		         = 2;
	public static final int EIKI_JUDGEMENT 	         = 4;
	public static final int REISEN_ILLUSION			 = 8;
	public static final int TENSHI_EARTHQUAKE		 = 16;
	public static final int KEIKI_BORDER             = 32;
	public static final int RINGING_BLOOM 		     = 64;
	public static final int NITORI_SHINY_KEY         = 128;
	public static final int SPRECHCHOR               = 256;
	public static final int TIME_EATER 	             = 512;
	public static final int EIRIN_UNHOLY_HEAL 	     = 1024;
	public static final int KAGUYA_FULL_MEAL 	     = 2048;
	public static final int FLANDRE_GAZE             = 4096;
	public static final int FATEFUL                  = 8192;
	public static final int JUNKO_PURE_HELL          = 16384;

	public static final int MAX_VALUE                = 32767;

	public static final String[] NAME_IDS = {
			"yuyuko_deadly_trap",
			"rebirth_day",
			"eiki_judgement",
			"reisen_illusion",
			"tenshi_earthquake",
			"keiki_border",
			"ringing_bloom",
			"nitori_shiny_key",
			"sprechchor",
			"time_eater",
			"eirin_unholy_heal",
			"kaguya_full_meal",
			"flandre_gaze",
			"fateful",
			"junko_pure_hell"
	};

	public static final int[] MASKS = {
			YUYUKO_DEADLY_TRAP, REBIRTH_DAY, EIKI_JUDGEMENT,
			REISEN_ILLUSION, TENSHI_EARTHQUAKE, KEIKI_BORDER,
			RINGING_BLOOM, NITORI_SHINY_KEY, SPRECHCHOR,
			TIME_EATER, EIRIN_UNHOLY_HEAL, KAGUYA_FULL_MEAL,
			FLANDRE_GAZE, FATEFUL, JUNKO_PURE_HELL
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