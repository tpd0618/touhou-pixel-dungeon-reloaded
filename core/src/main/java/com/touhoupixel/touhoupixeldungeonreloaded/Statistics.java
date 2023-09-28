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

import com.watabou.utils.Bundle;
import com.watabou.utils.SparseArray;

public class Statistics {

	public static int goldCollected;
	public static int goldPickedup;
	public static int highestFloor;
	public static int highestAscent;
	public static int enemiesSlain;
	public static int foodEaten;
	public static int potionsCooked;
	public static int itemsCrafted;
	public static int murasasKilled;
	public static int toyohimesKilled;
	public static int yorihimesKilled;
	public static int kisumesKilled;
	public static int shopkeepersKilled;
	public static int moon_Count;
	public static int trap_act_count;
	public static int maga_count;

	//These are used for score calculation
	//some are built incrementally, most are assigned when full score is calculated
	public static int progressScore;
	public static int heldItemValue;
	public static int treasureScore;
	public static SparseArray<Boolean> floorsExplored = new SparseArray<>();
	public static int exploreScore;
	public static float chalMultiplier;
	public static int totalScore;

	//used for hero unlock badges
	public static int upgradesUsed;
	public static int sneakAttacks;
	public static int thrownAssists;

	public static int limitBreak;

	public static int hexcancel;
	public static int hourai_cycle;

	public static int cardDraw;
	public static int cardDrawalt;

	public static int timeReset;
	public static int tenshiEarthquake;
	public static int bordercount;

	public static int maxHP_down;
	public static int life_count;
	public static int bomb_count;
	public static int dismantle_count;

	public static int seija_roulette;

	public static float duration;

	public static int powerfulres;
	public static int coolres;
	public static int pureres;
	public static int happyres;

	public static int randomencountertrack;
	public static int timetrackbuff;
	public static int timetrackstrup;

	public static int power;
	public static int value;
	public static int life;
	public static int lifefragment;
	public static int spellcard;
	public static int spellcardfragment;

	public static int tenshiattackstep;
	public static int eirinelixircount;

	public static int nextvalue;

	public static int mood;
	public static int wand_power_up;

	public static int lifefragmentkill;
	public static int spellcardfragmentkill;
	public static int hitoricount;
	public static int hitorilefttime;

	public static int difficulty;

	public static int double_speed_upgrade;
	public static int extermination_number;

	public static boolean identify_use;
	public static boolean fate_use;
	public static boolean earth_use;
	public static boolean exorcism_use;
	public static boolean transmute_use;

	public static boolean elixir_trigger = false;

	public static boolean remi_countdown = false;

	public static boolean qualifiedForNoKilling = false;
	public static boolean completedWithNoKilling = false;

	public static boolean scorelife1 = false;
	public static boolean scorelife2 = false;
	public static boolean scorelife3 = false;
	public static boolean scorelife4 = false;
	public static boolean scorelife5 = false;
	public static boolean scorelife6 = false;
	public static boolean scorelife7 = false;
	public static boolean scorelife8 = false;

	public static boolean lifelose = false;
	public static boolean spellcarduse = false;
	public static boolean torch_use = false;

	//boss appear check
	public static boolean boss1 = false;
	public static boolean boss2 = false;
	public static boolean boss3 = false;
	public static boolean boss4 = false;
	public static boolean boss5 = false;
	public static boolean boss6 = false;
	public static boolean boss7 = false;
	public static boolean boss8 = false;
	public static boolean boss9 = false;

	public static boolean card1 = false;
	public static boolean card2 = false;
	public static boolean card3 = false;
	public static boolean card4 = false;
	public static boolean card5 = false;
	public static boolean card6 = false;
	public static boolean card7 = false;
	public static boolean card8 = false;
	public static boolean card9 = false;
	public static boolean card10 = false;
	public static boolean card11 = false;
	public static boolean card12 = false;
	public static boolean card13 = false;
	public static boolean card14 = false;
	public static boolean card15 = false;
	public static boolean card16 = false;
	public static boolean card17 = false;
	public static boolean card18 = false;
	public static boolean card19 = false;
	public static boolean card20 = false;
	public static boolean card21 = false;
	public static boolean card22 = false;
	public static boolean card23 = false;
	public static boolean card24 = false;
	public static boolean card25 = false;
	public static boolean card26 = false;
	public static boolean card27 = false;
	public static boolean card28 = false;
	public static boolean card29 = false;
	public static boolean card30 = false;
	public static boolean card31 = false;
	public static boolean card32 = false;
	public static boolean card33 = false;
	public static boolean card34 = false;
	public static boolean card35 = false;
	public static boolean card36 = false;
	public static boolean card37 = false;
	public static boolean card38 = false;
	public static boolean card39 = false;
	public static boolean card40 = false;
	public static boolean card41 = false;
	public static boolean card42 = false;
	public static boolean card43 = false;
	public static boolean card44 = false;
	public static boolean card45 = false;
	public static boolean card46 = false;
	public static boolean card47 = false;
	public static boolean card48 = false;
	public static boolean card49 = false;
	public static boolean card50 = false;
	public static boolean card51 = false;
	public static boolean card52 = false;
	public static boolean card53 = false;
	public static boolean card54 = false;
	public static boolean card55 = false;
	public static boolean card56 = false;
	public static boolean card57 = false;
	public static boolean card58 = false;
	public static boolean card59 = false;
	public static boolean card60 = false;
	public static boolean card61 = false;
	public static boolean card62 = false;
	public static boolean card63 = false;
	public static boolean card64 = false;
	public static boolean card65 = false;
	public static boolean card66 = false;
	public static boolean card67 = false;
	public static boolean card68 = false;
	public static boolean card69 = false;
	public static boolean card70 = false;

	public static void reset() {

		goldCollected	= 0;
		goldPickedup	= 0;
		highestFloor = 0;
		highestAscent	= 0;
		enemiesSlain	= 0;
		foodEaten		= 0;
		potionsCooked	= 0;
		itemsCrafted    = 0;
		murasasKilled = 0;
		toyohimesKilled = 0;
		yorihimesKilled = 0;
		kisumesKilled   = 0;
		shopkeepersKilled = 0;
		moon_Count = 0;
		trap_act_count = 0;
		maga_count = 0;

		progressScore   = 0;
		heldItemValue   = 0;
		treasureScore   = 0;
		floorsExplored  = new SparseArray<>();
		exploreScore    = 0;
		chalMultiplier  = 1;
		totalScore      = 0;

		upgradesUsed    = 0;
		sneakAttacks    = 0;
		thrownAssists   = 0;

		limitBreak   = 0;

		hexcancel = 0;
		hourai_cycle = 0;

		cardDraw   = 0;
		cardDrawalt   = 0;

		timeReset = 0;
		tenshiEarthquake = 0;
		bordercount = 0;

		maxHP_down = 0;
		life_count = 0;
		bomb_count = 0;
		dismantle_count = 0;

		seija_roulette = 5;

		duration	= 0;

		powerfulres = 0;
		coolres = 0;
		pureres = 0;
		happyres = 0;

		randomencountertrack = 0;
		timetrackbuff = 0;
		timetrackstrup = 0;

		power = 100;
		value = 0;
		life = 2;
		lifefragment = 0;
		spellcard = 3;
		spellcardfragment = 0;

		tenshiattackstep = 0;
		eirinelixircount = 0;

		nextvalue = 500;

		mood = 0;
		wand_power_up = 0;

		lifefragmentkill = 0;
		spellcardfragmentkill = 0;

		hitoricount = 0;
		hitorilefttime = 0;

		difficulty = 1; //easy

		double_speed_upgrade = 0;
		extermination_number = 0;

		identify_use = false;
		fate_use = false;
		earth_use = false;
		exorcism_use = false;
		transmute_use = false;

		elixir_trigger = false;

		remi_countdown = false;

		qualifiedForNoKilling = false;

		scorelife1 = false;
		scorelife2 = false;
		scorelife3 = false;
		scorelife4 = false;
		scorelife5 = false;
		scorelife6 = false;
		scorelife7 = false;
		scorelife8 = false;

		lifelose = false;
		spellcarduse = false;
		torch_use = false;

		boss1 = false;
		boss2 = false;
		boss3 = false;
		boss4 = false;
		boss5 = false;
		boss6 = false;
		boss7 = false;
		boss8 = false;
		boss9 = false;

		card1 = false;
		card2 = false;
		card3 = false;
		card4 = false;
		card5 = false;
		card6 = false;
		card7 = false;
		card8 = false;
		card9 = false;
		card10 = false;
		card11 = false;
		card12 = false;
		card13 = false;
		card14 = false;
		card15 = false;
		card16 = false;
		card17 = false;
		card18 = false;
		card19 = false;
		card20 = false;
		card21 = false;
		card22 = false;
		card23 = false;
		card24 = false;
		card25 = false;
		card26 = false;
		card27 = false;
		card28 = false;
		card29 = false;
		card30 = false;
		card31 = false;
		card32 = false;
		card33 = false;
		card34 = false;
		card35 = false;
		card36 = false;
		card37 = false;
		card38 = false;
		card39 = false;
		card40 = false;
		card41 = false;
		card42 = false;
		card43 = false;
		card44 = false;
		card45 = false;
		card46 = false;
		card47 = false;
		card48 = false;
		card49 = false;
		card50 = false;
		card51 = false;
		card52 = false;
		card53 = false;
		card54 = false;
		card55 = false;
		card56 = false;
		card57 = false;
		card58 = false;
		card59 = false;
		card60 = false;
		card61 = false;
		card62 = false;
		card63 = false;
		card64 = false;
		card65 = false;
		card66 = false;
		card67 = false;
		card68 = false;
		card69 = false;
		card70 = false;
	}

	private static final String GOLD		= "score";
	private static final String GOLDPICKEDUP		= "goldpickedup";
	private static final String DEEPEST		= "maxDepth";
	private static final String HIGHEST		= "maxAscent";
	private static final String SLAIN		= "enemiesSlain";
	private static final String FOOD		= "foodEaten";
	private static final String ALCHEMY		= "potionsCooked";
	private static final String TOYOHIMES	= "toyohimeskilled";
	private static final String YORIHIMES	= "yorihimeskilled";
	private static final String KISUMES	    = "kisumeskilled";
	private static final String SHOPKEEPERS	= "shopkeeperskilled";
	private static final String MURASAS = "priranhas";
	private static final String MOON_COUNT		= "moon_count";
	private static final String TRAP_ACT_COUNT		= "trap_act_count";
	private static final String MAGA_COUNT		= "maga_count";

	private static final String PROG_SCORE	    = "prog_score";
	private static final String ITEM_VAL	    = "item_val";
	private static final String TRES_SCORE      = "tres_score";
	private static final String FLR_EXPL        = "flr_expl";
	private static final String EXPL_SCORE      = "expl_score";
	private static final String CHAL_MULT		= "chal_mult";
	private static final String TOTAL_SCORE		= "total_score";

	private static final String UPGRADES	= "upgradesUsed";
	private static final String SNEAKS		= "sneakAttacks";
	private static final String THROWN		= "thrownAssists";

	private static final String LIMITBREAK		= "limitBreak";

	private static final String HEXCANCEL = "hexcancel";
	private static final String HOURAI_CYCLE = "hourai_cycle";

	private static final String CARDDRAW		= "cardDraw";
	private static final String CARDDRAWALT		= "cardDrawalt";

	private static final String TIMERESET		= "timeReset";
	private static final String TENSHIEARTHQUAKE		= "tenshiearthquake";
	private static final String BORDERCOUNT		= "bordercount";

	private static final String MAXHP_DOWN		= "maxhp_down";
	private static final String LIFE_COUNT = "life_count";
	private static final String BOMB_COUNT = "bomb_count";
	private static final String DISMANTLE_COUNT = "dismantle_count";

	private static final String SEIJA_ROULETTE = "seija_roulette";

	private static final String DURATION	= "duration";

	private static final String POWERFULRES	= "powerfulres";
	private static final String COOLRES	= "coolres";
	private static final String PURERES	= "pureres";
	private static final String HAPPYRES	= "happyres";

	private static final String RANDOMENCOUNTERTRACK	= "randomencountertrack";
	private static final String TIMETRACKBUFF	= "timetrackbuff";
	private static final String TIMETRACKSTRUP	= "timetrackstrup";

	private static final String POWER	= "power";
	private static final String VALUE	= "value";
	private static final String LIFE	= "life";
	private static final String LIFEFRAGMENT	= "lifefragment";
	private static final String SPELLCARD	= "spellcard";
	private static final String SPELLCARDFRAGMENT	= "spellcardfragment";

	private static final String TENSHIATTACKSTEP	= "tenshiattackstep";
	private static final String EIRINELIXIRCOUNT	= "eirinelixircount";

	private static final String NEXTVALUE	= "nextvalue";

	private static final String MOOD	= "mood";
	private static final String WANDPOWERUP	= "wandpowerup";

	private static final String LIFEFRAGMENTKILL	= "lifefragmentkill";
	private static final String SPELLCARDFRAGMENTKILL	= "spellcardfragmentkill";

	private static final String HITORICOUNT	= "hitoricount";
	private static final String HITORILEFTTIME	= "hitorilefttime";

	private static final String DIFFICULTY	= "difficulty";

	private static final String DOUBLE_SPEED_UPGRADE	= "double_speed_upgrade";
	private static final String EXTERMINATION_NUMBER	= "extermination_number";

	private static final String IDENTIFY_USE	= "identify_use";
	private static final String FATE_USE	= "fate_use";
	private static final String EARTH_USE	= "earth_use";
	private static final String EXORCISM_USE	= "exorcism_use";
	private static final String TRANSMUTE_USE	= "transmute_use";

	private static final String ELIXIRTRIGGER	= "elixirtrigger";

	private static final String REMI_COUNTDOWN	= "remi_countdown";

	private static final String NO_KILLING_QUALIFIED	= "qualifiedForNoKilling";

	private static final String SCORELIFE1		= "scorelife1";
	private static final String SCORELIFE2		= "scorelife2";
	private static final String SCORELIFE3		= "scorelife3";
	private static final String SCORELIFE4		= "scorelife4";
	private static final String SCORELIFE5		= "scorelife5";
	private static final String SCORELIFE6		= "scorelife6";
	private static final String SCORELIFE7		= "scorelife7";
	private static final String SCORELIFE8		= "scorelife8";

	private static final String LIFELOSE		= "lifelose";
	private static final String SPELLCARDUSE		= "spellcarduse";
	private static final String TORCH_USE		= "torch_use";

	private static final String BOSS1		= "boss1";
	private static final String BOSS2		= "boss2";
	private static final String BOSS3		= "boss3";
	private static final String BOSS4		= "boss4";
	private static final String BOSS5		= "boss5";
	private static final String BOSS6		= "boss6";
	private static final String BOSS7		= "boss7";
	private static final String BOSS8		= "boss8";
	private static final String BOSS9		= "boss9";

	private static final String CARD1		= "card1";
	private static final String CARD2		= "card2";
	private static final String CARD3		= "card3";
	private static final String CARD4		= "card4";
	private static final String CARD5		= "card5";
	private static final String CARD6		= "card6";
	private static final String CARD7		= "card7";
	private static final String CARD8		= "card8";
	private static final String CARD9		= "card9";
	private static final String CARD10		= "card10";
	private static final String CARD11		= "card11";
	private static final String CARD12		= "card12";
	private static final String CARD13		= "card13";
	private static final String CARD14		= "card14";
	private static final String CARD15		= "card15";
	private static final String CARD16		= "card16";
	private static final String CARD17		= "card17";
	private static final String CARD18		= "card18";
	private static final String CARD19		= "card19";
	private static final String CARD20		= "card20";
	private static final String CARD21		= "card21";
	private static final String CARD22		= "card22";
	private static final String CARD23		= "card23";
	private static final String CARD24		= "card24";
	private static final String CARD25		= "card25";
	private static final String CARD26		= "card26";
	private static final String CARD27		= "card27";
	private static final String CARD28		= "card28";
	private static final String CARD29		= "card29";
	private static final String CARD30		= "card30";
	private static final String CARD31		= "card31";
	private static final String CARD32		= "card32";
	private static final String CARD33		= "card33";
	private static final String CARD34		= "card34";
	private static final String CARD35	    = "card35";
	private static final String CARD36		= "card36";
	private static final String CARD37		= "card37";
	private static final String CARD38		= "card38";
	private static final String CARD39		= "card39";
	private static final String CARD40		= "card40";
	private static final String CARD41		= "card41";
	private static final String CARD42		= "card42";
	private static final String CARD43		= "card43";
	private static final String CARD44		= "card44";
	private static final String CARD45		= "card45";
	private static final String CARD46		= "card46";
	private static final String CARD47		= "card47";
	private static final String CARD48		= "card48";
	private static final String CARD49		= "card49";
	private static final String CARD50		= "card50";
	private static final String CARD51		= "card51";
	private static final String CARD52		= "card52";
	private static final String CARD53		= "card53";
	private static final String CARD54		= "card54";
	private static final String CARD55	    = "card55";
	private static final String CARD56	    = "card56";
	private static final String CARD57	    = "card57";
	private static final String CARD58		= "card58";
	private static final String CARD59		= "card59";
	private static final String CARD60		= "card60";
	private static final String CARD61		= "card61";
	private static final String CARD62		= "card62";
	private static final String CARD63		= "card63";
	private static final String CARD64		= "card64";
	private static final String CARD65		= "card65";
	private static final String CARD66		= "card66";
	private static final String CARD67		= "card67";
	private static final String CARD68		= "card68";
	private static final String CARD69		= "card69";
	private static final String CARD70		= "card70";

	public static void storeInBundle( Bundle bundle ) {
		bundle.put( GOLD,		goldCollected );
		bundle.put( GOLDPICKEDUP,		goldPickedup );
		bundle.put( DEEPEST, highestFloor);
		bundle.put( HIGHEST,	highestAscent );
		bundle.put( SLAIN,		enemiesSlain );
		bundle.put( FOOD,		foodEaten );
		bundle.put( ALCHEMY,    itemsCrafted );
		bundle.put( MURASAS, murasasKilled);
		bundle.put( TOYOHIMES,	toyohimesKilled );
		bundle.put( YORIHIMES,	yorihimesKilled );
		bundle.put( SHOPKEEPERS,shopkeepersKilled);
		bundle.put( KISUMES,	kisumesKilled );
		bundle.put( MOON_COUNT, moon_Count);
		bundle.put( TRAP_ACT_COUNT, trap_act_count);
		bundle.put( MAGA_COUNT, maga_count);

		bundle.put( PROG_SCORE,  progressScore );
		bundle.put( ITEM_VAL,    heldItemValue );
		bundle.put( TRES_SCORE,  treasureScore );
		for (int i = 1; i < 51; i++){
			if (floorsExplored.containsKey(i)){
				bundle.put( FLR_EXPL+i, floorsExplored.get(i) );
			}
		}
		bundle.put( EXPL_SCORE,  exploreScore );
		bundle.put( CHAL_MULT,   chalMultiplier );
		bundle.put( TOTAL_SCORE, totalScore );

		bundle.put( UPGRADES,   upgradesUsed );
		bundle.put( SNEAKS,		sneakAttacks );
		bundle.put( THROWN,		thrownAssists );

		bundle.put( LIMITBREAK,		limitBreak );

		bundle.put( HEXCANCEL, hexcancel );
		bundle.put( HOURAI_CYCLE, hourai_cycle );

		bundle.put( CARDDRAW,		cardDraw );
		bundle.put( CARDDRAWALT,		cardDrawalt );

		bundle.put( TIMERESET,		timeReset );
		bundle.put( TENSHIEARTHQUAKE,		tenshiEarthquake );
		bundle.put( BORDERCOUNT,		bordercount );

		bundle.put( MAXHP_DOWN, maxHP_down);
		bundle.put( LIFE_COUNT, life_count);
		bundle.put( BOMB_COUNT, bomb_count);
		bundle.put( DISMANTLE_COUNT, dismantle_count);

		bundle.put( SEIJA_ROULETTE, seija_roulette);

		bundle.put( DURATION,	duration );

		bundle.put( POWERFULRES,powerfulres );
		bundle.put( COOLRES,	coolres );
		bundle.put( PURERES,	pureres );
		bundle.put( HAPPYRES,	happyres );

		bundle.put( RANDOMENCOUNTERTRACK,	randomencountertrack );
		bundle.put( TIMETRACKBUFF,	timetrackbuff );
		bundle.put( TIMETRACKSTRUP,	timetrackstrup );

		bundle.put( POWER,	power );
		bundle.put( VALUE,	value );
		bundle.put( LIFE,	life );
		bundle.put( LIFEFRAGMENT,	lifefragment );
		bundle.put( SPELLCARD,	spellcard );
		bundle.put( SPELLCARDFRAGMENT,	spellcardfragment );

		bundle.put( TENSHIATTACKSTEP, tenshiattackstep);
		bundle.put( EIRINELIXIRCOUNT, eirinelixircount);

		bundle.put( NEXTVALUE,	nextvalue );

		bundle.put( MOOD, mood);
		bundle.put( WANDPOWERUP, wand_power_up);

		bundle.put( LIFEFRAGMENTKILL,	lifefragmentkill );
		bundle.put( SPELLCARDFRAGMENTKILL,	spellcardfragmentkill );

		bundle.put( HITORICOUNT,	hitoricount );
		bundle.put( HITORILEFTTIME,	hitorilefttime );

		bundle.put( DIFFICULTY,	difficulty );

		bundle.put( IDENTIFY_USE,	identify_use );
		bundle.put( FATE_USE,	fate_use );
		bundle.put( EARTH_USE,	earth_use );
		bundle.put( EXORCISM_USE,	exorcism_use );
		bundle.put( TRANSMUTE_USE,	transmute_use );

		bundle.put( DOUBLE_SPEED_UPGRADE,	double_speed_upgrade );
		bundle.put( EXTERMINATION_NUMBER,	extermination_number );

		bundle.put( ELIXIRTRIGGER, elixir_trigger);

		bundle.put( REMI_COUNTDOWN, remi_countdown);

		bundle.put( NO_KILLING_QUALIFIED, qualifiedForNoKilling );

		bundle.put( SCORELIFE1,	scorelife1 );
		bundle.put( SCORELIFE2,	scorelife2 );
		bundle.put( SCORELIFE3,	scorelife3 );
		bundle.put( SCORELIFE4,	scorelife4 );
		bundle.put( SCORELIFE5,	scorelife5 );
		bundle.put( SCORELIFE6,	scorelife6 );
		bundle.put( SCORELIFE7,	scorelife7 );
		bundle.put( SCORELIFE8,	scorelife8 );

		bundle.put( LIFELOSE,	lifelose );
		bundle.put( SPELLCARDUSE,	spellcarduse );
		bundle.put( TORCH_USE, torch_use);

		bundle.put( BOSS1,	boss1 );
		bundle.put( BOSS2,	boss2 );
		bundle.put( BOSS3,	boss3 );
		bundle.put( BOSS4,	boss4 );
		bundle.put( BOSS5,	boss5 );
		bundle.put( BOSS6,	boss6 );
		bundle.put( BOSS7,	boss7 );
		bundle.put( BOSS8,	boss8 );
		bundle.put( BOSS9,	boss9 );

		bundle.put( CARD1,	card1 );
		bundle.put( CARD2,	card2 );
		bundle.put( CARD3,	card3 );
		bundle.put( CARD4,	card4 );
		bundle.put( CARD5,	card5 );
		bundle.put( CARD6,	card6 );
		bundle.put( CARD7,	card7 );
		bundle.put( CARD8,	card8 );
		bundle.put( CARD9,	card9 );
		bundle.put( CARD10,	card10 );
		bundle.put( CARD11,	card11 );
		bundle.put( CARD12,	card12 );
		bundle.put( CARD13,	card13 );
		bundle.put( CARD14,	card14 );
		bundle.put( CARD15,	card15 );
		bundle.put( CARD16,	card16 );
		bundle.put( CARD17,	card17 );
		bundle.put( CARD18,	card18 );
		bundle.put( CARD19,	card19 );
		bundle.put( CARD20,	card20 );
		bundle.put( CARD21,	card21 );
		bundle.put( CARD22,	card22 );
		bundle.put( CARD23,	card23 );
		bundle.put( CARD24,	card24 );
		bundle.put( CARD25,	card25 );
		bundle.put( CARD26,	card26 );
		bundle.put( CARD27,	card27 );
		bundle.put( CARD28,	card28 );
		bundle.put( CARD29,	card29 );
		bundle.put( CARD30,	card30 );
		bundle.put( CARD31,	card31 );
		bundle.put( CARD32,	card32 );
		bundle.put( CARD33,	card33 );
		bundle.put( CARD34,	card34 );
		bundle.put( CARD35,	card35 );
		bundle.put( CARD36,	card36 );
		bundle.put( CARD37,	card37 );
		bundle.put( CARD38,	card38 );
		bundle.put( CARD39,	card39 );
		bundle.put( CARD40,	card40 );
		bundle.put( CARD41,	card41 );
		bundle.put( CARD42,	card42 );
		bundle.put( CARD43,	card43 );
		bundle.put( CARD44,	card44 );
		bundle.put( CARD45,	card45 );
		bundle.put( CARD46,	card46 );
		bundle.put( CARD47,	card47 );
		bundle.put( CARD48,	card48 );
		bundle.put( CARD49,	card49 );
		bundle.put( CARD50,	card50 );
		bundle.put( CARD51,	card51 );
		bundle.put( CARD52,	card52 );
		bundle.put( CARD53,	card53 );
		bundle.put( CARD54,	card54 );
		bundle.put( CARD55,	card55 );
		bundle.put( CARD56,	card56 );
		bundle.put( CARD57,	card57 );
		bundle.put( CARD58,	card58 );
		bundle.put( CARD59,	card59 );
		bundle.put( CARD60,	card60 );
		bundle.put( CARD61,	card61 );
		bundle.put( CARD62,	card62 );
		bundle.put( CARD63,	card63 );
		bundle.put( CARD64,	card64 );
		bundle.put( CARD65,	card65 );
		bundle.put( CARD66,	card66 );
		bundle.put( CARD67,	card67 );
		bundle.put( CARD68,	card68 );
		bundle.put( CARD69,	card69 );
		bundle.put( CARD70,	card70 );
	}

	public static void restoreFromBundle( Bundle bundle ) {
		goldCollected	= bundle.getInt( GOLD );
		goldPickedup	= bundle.getInt( GOLDPICKEDUP );
		highestFloor = bundle.getInt( DEEPEST );
		highestAscent   = bundle.getInt( HIGHEST );
		enemiesSlain	= bundle.getInt( SLAIN );
		foodEaten		= bundle.getInt( FOOD );
		itemsCrafted    = bundle.getInt( ALCHEMY );
		toyohimesKilled	= bundle.getInt( TOYOHIMES );
		yorihimesKilled	= bundle.getInt( YORIHIMES );
		kisumesKilled	= bundle.getInt( KISUMES );
		shopkeepersKilled	= bundle.getInt( SHOPKEEPERS );
		murasasKilled = bundle.getInt(MURASAS);
		moon_Count = bundle.getInt( MOON_COUNT );
		trap_act_count = bundle.getInt( TRAP_ACT_COUNT );
		maga_count = bundle.getInt( MAGA_COUNT );

		progressScore   = bundle.getInt( PROG_SCORE );
		heldItemValue   = bundle.getInt( ITEM_VAL );
		treasureScore   = bundle.getInt( TRES_SCORE );
		floorsExplored.clear();
		for (int i = 1; i < 99; i++){
			if (bundle.contains( FLR_EXPL+i )){
				floorsExplored.put(i, bundle.getBoolean( FLR_EXPL+i ));
			}
		}
		exploreScore    = bundle.getInt( EXPL_SCORE );
		chalMultiplier  = bundle.getFloat( CHAL_MULT );
		totalScore      = bundle.getInt( TOTAL_SCORE );

		upgradesUsed    = bundle.getInt( UPGRADES );
		sneakAttacks    = bundle.getInt( SNEAKS );
		thrownAssists   = bundle.getInt( THROWN );

		limitBreak   = bundle.getInt( LIMITBREAK );

		hexcancel = bundle.getInt(HEXCANCEL);
		hourai_cycle = bundle.getInt(HOURAI_CYCLE);

		cardDraw   = bundle.getInt( CARDDRAW );
		cardDrawalt   = bundle.getInt( CARDDRAWALT );

		timeReset   = bundle.getInt( TIMERESET );
		tenshiEarthquake   = bundle.getInt( TENSHIEARTHQUAKE );
		bordercount   = bundle.getInt( BORDERCOUNT );

		maxHP_down = bundle.getInt( MAXHP_DOWN );
		life_count = bundle.getInt(LIFE_COUNT);
		bomb_count = bundle.getInt(BOMB_COUNT);
		dismantle_count = bundle.getInt(DISMANTLE_COUNT);

		seija_roulette = bundle.getInt(SEIJA_ROULETTE);

		duration		= bundle.getFloat( DURATION );

		powerfulres		= bundle.getInt( POWERFULRES );
		coolres		= bundle.getInt( COOLRES );
		pureres		= bundle.getInt( PURERES );
		happyres		= bundle.getInt( HAPPYRES );

		randomencountertrack		= bundle.getInt( RANDOMENCOUNTERTRACK );
		timetrackbuff		= bundle.getInt( TIMETRACKBUFF );
		timetrackstrup		= bundle.getInt( TIMETRACKSTRUP );

		power		= bundle.getInt( POWER );
		value		= bundle.getInt( VALUE );
		life		= bundle.getInt( LIFE );
		lifefragment		= bundle.getInt( LIFEFRAGMENT );
		spellcard		= bundle.getInt( SPELLCARD );
		spellcardfragment		= bundle.getInt( SPELLCARDFRAGMENT );

		tenshiattackstep = bundle.getInt( TENSHIATTACKSTEP );
		eirinelixircount = bundle.getInt( EIRINELIXIRCOUNT );

		nextvalue		= bundle.getInt( NEXTVALUE );

		mood = bundle.getInt( MOOD );
		wand_power_up = bundle.getInt( WANDPOWERUP );

		lifefragmentkill		= bundle.getInt( LIFEFRAGMENTKILL );
		spellcardfragmentkill		= bundle.getInt( SPELLCARDFRAGMENTKILL );

		hitoricount		= bundle.getInt( HITORICOUNT );
		hitorilefttime		= bundle.getInt( HITORILEFTTIME );

		difficulty		= bundle.getInt( DIFFICULTY );

		double_speed_upgrade	= bundle.getInt( DOUBLE_SPEED_UPGRADE );
		extermination_number	= bundle.getInt( EXTERMINATION_NUMBER );

		identify_use	= bundle.getBoolean( IDENTIFY_USE );
		fate_use		= bundle.getBoolean( FATE_USE );
		earth_use		= bundle.getBoolean( EARTH_USE );
		exorcism_use		= bundle.getBoolean( EXORCISM_USE );
		transmute_use		= bundle.getBoolean( TRANSMUTE_USE );

		elixir_trigger = bundle.getBoolean( ELIXIRTRIGGER );

		remi_countdown = bundle.getBoolean( REMI_COUNTDOWN );

		qualifiedForNoKilling = bundle.getBoolean( NO_KILLING_QUALIFIED );

		scorelife1	= bundle.getBoolean( SCORELIFE1 );
		scorelife2	= bundle.getBoolean( SCORELIFE2 );
		scorelife3	= bundle.getBoolean( SCORELIFE3 );
		scorelife4	= bundle.getBoolean( SCORELIFE4 );
		scorelife5	= bundle.getBoolean( SCORELIFE5 );
		scorelife6	= bundle.getBoolean( SCORELIFE6 );
		scorelife7	= bundle.getBoolean( SCORELIFE7 );
		scorelife8	= bundle.getBoolean( SCORELIFE8 );

		lifelose	= bundle.getBoolean( LIFELOSE );
		spellcarduse	= bundle.getBoolean( SPELLCARDUSE );
		torch_use = bundle.getBoolean( TORCH_USE );

		boss1	= bundle.getBoolean( BOSS1 );
		boss2	= bundle.getBoolean( BOSS2 );
		boss3	= bundle.getBoolean( BOSS3 );
		boss4	= bundle.getBoolean( BOSS4 );
		boss5	= bundle.getBoolean( BOSS5 );
		boss6	= bundle.getBoolean( BOSS6 );
		boss7	= bundle.getBoolean( BOSS7 );
		boss8	= bundle.getBoolean( BOSS8 );
		boss9	= bundle.getBoolean( BOSS9 );

		card1	= bundle.getBoolean( CARD1 );
		card2	= bundle.getBoolean( CARD2 );
		card3	= bundle.getBoolean( CARD3 );
		card4	= bundle.getBoolean( CARD4 );
		card5	= bundle.getBoolean( CARD5 );
		card6	= bundle.getBoolean( CARD6 );
		card7	= bundle.getBoolean( CARD7 );
		card8	= bundle.getBoolean( CARD8 );
		card9	= bundle.getBoolean( CARD9 );
		card10  = bundle.getBoolean( CARD10 );
		card11	= bundle.getBoolean( CARD11 );
		card12	= bundle.getBoolean( CARD12 );
		card13	= bundle.getBoolean( CARD13 );
		card14	= bundle.getBoolean( CARD14 );
		card15	= bundle.getBoolean( CARD15 );
		card16	= bundle.getBoolean( CARD16 );
		card17	= bundle.getBoolean( CARD17 );
		card18	= bundle.getBoolean( CARD18 );
		card19	= bundle.getBoolean( CARD19 );
		card20	= bundle.getBoolean( CARD20 );
		card21	= bundle.getBoolean( CARD21 );
		card22	= bundle.getBoolean( CARD22 );
		card23	= bundle.getBoolean( CARD23 );
		card24	= bundle.getBoolean( CARD24 );
		card25	= bundle.getBoolean( CARD25 );
		card26	= bundle.getBoolean( CARD26 );
		card27	= bundle.getBoolean( CARD27 );
		card28	= bundle.getBoolean( CARD28 );
		card29	= bundle.getBoolean( CARD29 );
		card30	= bundle.getBoolean( CARD30 );
		card31	= bundle.getBoolean( CARD31 );
		card32	= bundle.getBoolean( CARD32 );
		card33	= bundle.getBoolean( CARD33 );
		card34	= bundle.getBoolean( CARD34 );
		card35  = bundle.getBoolean( CARD35 );
		card36	= bundle.getBoolean( CARD36 );
		card37	= bundle.getBoolean( CARD37 );
		card38	= bundle.getBoolean( CARD38 );
		card39	= bundle.getBoolean( CARD39 );
		card40	= bundle.getBoolean( CARD40 );
		card41	= bundle.getBoolean( CARD41 );
		card42	= bundle.getBoolean( CARD42 );
		card43	= bundle.getBoolean( CARD43 );
		card44	= bundle.getBoolean( CARD44 );
		card45	= bundle.getBoolean( CARD45 );
		card46	= bundle.getBoolean( CARD46 );
		card47	= bundle.getBoolean( CARD47 );
		card48	= bundle.getBoolean( CARD48 );
		card49	= bundle.getBoolean( CARD49 );
		card50	= bundle.getBoolean( CARD50 );
		card51	= bundle.getBoolean( CARD51 );
		card52	= bundle.getBoolean( CARD52 );
		card53	= bundle.getBoolean( CARD53 );
		card54	= bundle.getBoolean( CARD54 );
		card55  = bundle.getBoolean( CARD55 );
		card56	= bundle.getBoolean( CARD56 );
		card57  = bundle.getBoolean( CARD57 );
		card58	= bundle.getBoolean( CARD58 );
		card59	= bundle.getBoolean( CARD59 );
		card60  = bundle.getBoolean( CARD60 );
		card61	= bundle.getBoolean( CARD61 );
		card62	= bundle.getBoolean( CARD62 );
		card63	= bundle.getBoolean( CARD63 );
		card64	= bundle.getBoolean( CARD64 );
		card65	= bundle.getBoolean( CARD65 );
		card66	= bundle.getBoolean( CARD66 );
		card67	= bundle.getBoolean( CARD67 );
		card68	= bundle.getBoolean( CARD68 );
		card69	= bundle.getBoolean( CARD69 );
		card70  = bundle.getBoolean( CARD70 );
	}

	public static void preview( GamesInProgress.Info info, Bundle bundle ){
		info.goldCollected  = bundle.getInt( GOLD );
		info.maxFloor = bundle.getInt( DEEPEST );
	}
}