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

public class Statistics {

	public static int talismanUsed;
	public static int goldCollected;
	public static int goldPickedup;
	public static int highestFloor;
	public static int highestAscent;
	public static int enemiesSlain;
	public static int foodEaten;
	public static int potionsCooked;
	public static int itemsCrafted;
	public static int murasasKilled;
	public static int kisumesKilled;
	public static int trap_act_count;
	public static int bamboo;
	public static int chimata;

	public static Class exterminatedEnemy;

	public static int upgradesUsed;
	public static int sneakAttacks;
	public static int thrownAssists;

	public static int timeReset;
	public static int tenshiEarthquake;
	public static int bordercount;

	public static int HT_bonus;
	public static int life_count;
	public static int bomb_count;
	public static int dismantle_count;

	public static int power;
	public static int value;
	public static int life;
	public static int lifefragment;
	public static int spellcard;
	public static int spellcardfragment;

	public static int tenshiattackstep;
	public static int eirinelixircount;

	public static int nextvalue;

	public static int lifefragmentkill;
	public static int spellcardfragmentkill;

	public static int difficulty;

	public static int double_speed_upgrade;
	public static float duration;

	public static boolean identify_use;
	public static boolean fate_use;
	public static boolean earth_use;
	public static boolean exorcism_use;
	public static boolean transmute_use;

	public static boolean elixir_trigger = false;

	public static boolean remi_countdown = false;

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
	public static boolean abcarduse = false;
	public static boolean suwakorelic_active = false;

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

	public static boolean cardNazrinGoldMoney = false;
	public static boolean cardNazrinAlchemyMoney = false;
	public static boolean cardLifeCard = false;
	public static boolean cardKeikiCreation = false;
	public static boolean cardShionUchiwa = false;
	public static boolean cardDoremySheep = false;
	public static boolean cardYingYangOrb = false;
	public static boolean cardYingYangOrbNeedle = false;
	public static boolean cardMiniHakkero = false;
	public static boolean cardMiniHakkeroMissile = false;
	public static boolean cardMaidKnife = false;
	public static boolean cardMaidKnifeRicochet = false;
	public static boolean cardSanaeAmulet = false;
	public static boolean cardSanaeAmuletAlt = false;
	public static boolean cardHalfGhost = false;
	public static boolean cardHalfGhostSpare = false;
	public static boolean cardShanghaiDoll = false;
	public static boolean cardIceFairy = false;
	public static boolean cardStarCard = false;
	public static boolean cardLunaCard = false;
	public static boolean cardSunnyCard = false;
	public static boolean cardLarvaScale = false;
	public static boolean cardNemunoKnife = false;
	public static boolean cardSeiranBleedingHammer = false;
	public static boolean cardOkinaBackdoor = false;
	public static boolean cardAnnoyingUfo = false;
	public static boolean cardAncientMagatama = false;
	public static boolean cardShouPagoda = false;
	public static boolean cardSekibankiHead = false;
	public static boolean cardTeacupReimu = false;
	public static boolean cardTeacupMarisa = false;
	public static boolean cardBlankCard = false;
	public static boolean cardNitoriDilemma = false;
	public static boolean cardRingoBrandDango = false;
	public static boolean cardHijiriSutra = false;
	public static boolean cardFlandreDestruction = false;
	public static boolean cardDragonPassage = false;
	public static boolean cardAunnHounds = false;
	public static boolean cardGaleGeta = false;
	public static boolean cardEirinElixir = false;
	public static boolean cardMinorikoCrop = false;
	public static boolean cardPhoenixTail = false;
	public static boolean cardEikiMoney = false;
	public static boolean cardMiserAdvice = false;
	public static boolean cardKanakoOffering = false;
	public static boolean cardYachieThreat = false;
	public static boolean cardKaguyaStash = false;
	public static boolean cardTewiFoot = false;
	public static boolean cardPebbleHat = false;
	public static boolean cardDanmakuGhost = false;
	public static boolean cardSuwakoFrog = false;
	public static boolean cardDragonPipe = false;
	public static boolean cardMomoyoCentipede = false;
	public static boolean cardSuikaGourd = false;
	public static boolean cardLifeBurningTorch = false;
	public static boolean cardIrresistibleFan = false;
	public static boolean cardPristineConfidence = false;
	public static boolean cardScreenBorder = false;
	public static boolean cardMiracleMallet = false;
	public static boolean cardTenshiKeystone = false;
	public static boolean cardClownpieceMoon = false;
	public static boolean cardMikoAuthority = false;
	public static boolean cardVampireFang = false;
	public static boolean cardUndergroundSun = false;
	public static boolean cardItemSeason = false;
	public static boolean cardSpiritBottle = false;
	public static boolean cardMegumuBarleyRice = false;
	public static boolean cardSmeltScale = false;
	public static boolean cardTooHonestSignpost = false;
	public static boolean cardKomachiDetour = false;

	public static void reset() {

		talismanUsed	= 0;
		goldCollected	= 0;
		goldPickedup	= 0;
		highestFloor = 0;
		highestAscent	= 0;
		enemiesSlain	= 0;
		foodEaten		= 0;
		potionsCooked	= 0;
		itemsCrafted    = 0;
		murasasKilled = 0;
		kisumesKilled   = 0;
		trap_act_count = 0;
		bamboo = 0;
		chimata = 0;
		exterminatedEnemy = null;

		upgradesUsed    = 0;
		sneakAttacks    = 0;
		thrownAssists   = 0;

		timeReset = 0;
		tenshiEarthquake = 0;
		bordercount = 0;

		HT_bonus = 0;
		life_count = 0;
		bomb_count = 0;
		dismantle_count = 0;

		power = 100;
		value = 0;
		life = 2;
		lifefragment = 0;
		spellcard = 3;
		spellcardfragment = 0;

		tenshiattackstep = 0;
		eirinelixircount = 0;

		nextvalue = 500;

		lifefragmentkill = 0;
		spellcardfragmentkill = 0;

		difficulty = 1; //easy

		double_speed_upgrade = 0;
		duration = 0;

		identify_use = false;
		fate_use = false;
		earth_use = false;
		exorcism_use = false;
		transmute_use = false;

		elixir_trigger = false;

		remi_countdown = false;

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
		abcarduse = false;
		suwakorelic_active = false;

		boss1 = false;
		boss2 = false;
		boss3 = false;
		boss4 = false;
		boss5 = false;
		boss6 = false;
		boss7 = false;
		boss8 = false;
		boss9 = false;

		cardNazrinGoldMoney = false;
		cardNazrinAlchemyMoney = false;
		cardLifeCard = false;
		cardKeikiCreation = false;
		cardShionUchiwa = false;
		cardDoremySheep = false;
		cardYingYangOrb = false;
		cardYingYangOrbNeedle = false;
		cardMiniHakkero = false;
		cardMiniHakkeroMissile = false;
		cardMaidKnife = false;
		cardMaidKnifeRicochet = false;
		cardSanaeAmulet = false;
		cardSanaeAmuletAlt = false;
		cardHalfGhost = false;
		cardHalfGhostSpare = false;
		cardShanghaiDoll = false;
		cardIceFairy = false;
		cardStarCard = false;
		cardLunaCard = false;
		cardSunnyCard = false;
		cardLarvaScale = false;
		cardNemunoKnife = false;
		cardSeiranBleedingHammer = false;
		cardOkinaBackdoor = false;
		cardAnnoyingUfo = false;
		cardAncientMagatama = false;
		cardShouPagoda = false;
		cardSekibankiHead = false;
		cardTeacupReimu = false;
		cardTeacupMarisa = false;
		cardBlankCard = false;
		cardNitoriDilemma = false;
		cardRingoBrandDango = false;
		cardHijiriSutra = false;
		cardFlandreDestruction = false;
		cardDragonPassage = false;
		cardAunnHounds = false;
		cardGaleGeta = false;
		cardEirinElixir = false;
		cardMinorikoCrop = false;
		cardPhoenixTail = false;
		cardEikiMoney = false;
		cardMiserAdvice = false;
		cardKanakoOffering = false;
		cardYachieThreat = false;
		cardKaguyaStash = false;
		cardTewiFoot = false;
		cardPebbleHat = false;
		cardDanmakuGhost = false;
		cardSuwakoFrog = false;
		cardDragonPipe = false;
		cardMomoyoCentipede = false;
		cardSuikaGourd = false;
		cardLifeBurningTorch = false;
		cardIrresistibleFan = false;
		cardPristineConfidence = false;
		cardScreenBorder = false;
		cardMiracleMallet = false;
		cardTenshiKeystone = false;
		cardClownpieceMoon = false;
		cardMikoAuthority = false;
		cardVampireFang = false;
		cardUndergroundSun = false;
		cardItemSeason = false;
		cardSpiritBottle = false;
		cardMegumuBarleyRice = false;
		cardSmeltScale = false;
		cardTooHonestSignpost = false;
		cardKomachiDetour = false;
	}

	private static final String TALISMANUSED		= "talismanused";
	private static final String GOLD		= "score";
	private static final String GOLDPICKEDUP		= "goldpickedup";
	private static final String DEEPEST		= "maxDepth";
	private static final String HIGHEST		= "maxAscent";
	private static final String SLAIN		= "enemiesSlain";
	private static final String FOOD		= "foodEaten";
	private static final String ALCHEMY		= "potionsCooked";
	private static final String KISUMES	    = "kisumeskilled";
	private static final String MURASAS = "priranhas";
	private static final String TRAP_ACT_COUNT		= "trap_act_count";
	private static final String BAMBOO		= "bamboo";
	private static final String EXTERMINATED_ENEMY		= "exterminated_enemy";
	private static final String CHIMATA		= "chimata";

	private static final String UPGRADES	= "upgradesUsed";
	private static final String SNEAKS		= "sneakAttacks";
	private static final String THROWN		= "thrownAssists";

	private static final String TIMERESET		= "timeReset";
	private static final String TENSHIEARTHQUAKE		= "tenshiearthquake";
	private static final String BORDERCOUNT		= "bordercount";

	private static final String MAXHP_DOWN		= "maxhp_down";
	private static final String LIFE_COUNT = "life_count";
	private static final String BOMB_COUNT = "bomb_count";
	private static final String DISMANTLE_COUNT = "dismantle_count";

	private static final String POWER	= "power";
	private static final String VALUE	= "value";
	private static final String LIFE	= "life";
	private static final String LIFEFRAGMENT	= "lifefragment";
	private static final String SPELLCARD	= "spellcard";
	private static final String SPELLCARDFRAGMENT	= "spellcardfragment";

	private static final String TENSHIATTACKSTEP	= "tenshiattackstep";
	private static final String EIRINELIXIRCOUNT	= "eirinelixircount";

	private static final String NEXTVALUE	= "nextvalue";

	private static final String LIFEFRAGMENTKILL	= "lifefragmentkill";
	private static final String SPELLCARDFRAGMENTKILL	= "spellcardfragmentkill";

	private static final String DIFFICULTY	= "difficulty";

	private static final String DOUBLE_SPEED_UPGRADE	= "double_speed_upgrade";
	private static final String DURATION	= "duration";

	private static final String IDENTIFY_USE	= "identify_use";
	private static final String FATE_USE	= "fate_use";
	private static final String EARTH_USE	= "earth_use";
	private static final String EXORCISM_USE	= "exorcism_use";
	private static final String TRANSMUTE_USE	= "transmute_use";

	private static final String ELIXIRTRIGGER	= "elixirtrigger";

	private static final String REMI_COUNTDOWN	= "remi_countdown";

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
	private static final String ABCARDUSE		= "abcarduse";
	private static final String SUWAKORELIC_ACTIVE = "suwakorelic_active";


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
		bundle.put( TALISMANUSED,		talismanUsed );
		bundle.put( GOLD,		goldCollected );
		bundle.put( GOLDPICKEDUP,		goldPickedup );
		bundle.put( DEEPEST, highestFloor);
		bundle.put( HIGHEST,	highestAscent );
		bundle.put( SLAIN,		enemiesSlain );
		bundle.put( FOOD,		foodEaten );
		bundle.put( ALCHEMY,    itemsCrafted );
		bundle.put( MURASAS, murasasKilled);
		bundle.put( KISUMES,	kisumesKilled );
		bundle.put( TRAP_ACT_COUNT, trap_act_count);
		bundle.put( BAMBOO, bamboo);
		bundle.put( CHIMATA, chimata);
		bundle.put( EXTERMINATED_ENEMY, exterminatedEnemy );

		bundle.put( UPGRADES,   upgradesUsed );
		bundle.put( SNEAKS,		sneakAttacks );
		bundle.put( THROWN,		thrownAssists );

		bundle.put( TIMERESET,		timeReset );
		bundle.put( TENSHIEARTHQUAKE,		tenshiEarthquake );
		bundle.put( BORDERCOUNT,		bordercount );

		bundle.put( MAXHP_DOWN, HT_bonus);
		bundle.put( LIFE_COUNT, life_count);
		bundle.put( BOMB_COUNT, bomb_count);
		bundle.put( DISMANTLE_COUNT, dismantle_count);

		bundle.put( POWER,	power );
		bundle.put( VALUE,	value );
		bundle.put( LIFE,	life );
		bundle.put( LIFEFRAGMENT,	lifefragment );
		bundle.put( SPELLCARD,	spellcard );
		bundle.put( SPELLCARDFRAGMENT,	spellcardfragment );

		bundle.put( TENSHIATTACKSTEP, tenshiattackstep);
		bundle.put( EIRINELIXIRCOUNT, eirinelixircount);

		bundle.put( NEXTVALUE,	nextvalue );

		bundle.put( LIFEFRAGMENTKILL,	lifefragmentkill );
		bundle.put( SPELLCARDFRAGMENTKILL,	spellcardfragmentkill );

		bundle.put( DIFFICULTY,	difficulty );

		bundle.put( IDENTIFY_USE,	identify_use );
		bundle.put( FATE_USE,	fate_use );
		bundle.put( EARTH_USE,	earth_use );
		bundle.put( EXORCISM_USE,	exorcism_use );
		bundle.put( TRANSMUTE_USE,	transmute_use );

		bundle.put( DOUBLE_SPEED_UPGRADE,	double_speed_upgrade );
		bundle.put( DURATION,	duration );

		bundle.put( ELIXIRTRIGGER, elixir_trigger);

		bundle.put( REMI_COUNTDOWN, remi_countdown);

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
		bundle.put( SUWAKORELIC_ACTIVE, suwakorelic_active);
		bundle.put( ABCARDUSE,	abcarduse );

		bundle.put( BOSS1,	boss1 );
		bundle.put( BOSS2,	boss2 );
		bundle.put( BOSS3,	boss3 );
		bundle.put( BOSS4,	boss4 );
		bundle.put( BOSS5,	boss5 );
		bundle.put( BOSS6,	boss6 );
		bundle.put( BOSS7,	boss7 );
		bundle.put( BOSS8,	boss8 );
		bundle.put( BOSS9,	boss9 );

		bundle.put( CARD1, cardNazrinGoldMoney);
		bundle.put( CARD2, cardNazrinAlchemyMoney);
		bundle.put( CARD3, cardLifeCard);
		bundle.put( CARD4, cardKeikiCreation);
		bundle.put( CARD5, cardShionUchiwa);
		bundle.put( CARD6, cardDoremySheep);
		bundle.put( CARD7, cardYingYangOrb);
		bundle.put( CARD8, cardYingYangOrbNeedle);
		bundle.put( CARD9, cardMiniHakkero);
		bundle.put( CARD10, cardMiniHakkeroMissile);
		bundle.put( CARD11, cardMaidKnife);
		bundle.put( CARD12, cardMaidKnifeRicochet);
		bundle.put( CARD13, cardSanaeAmulet);
		bundle.put( CARD14, cardSanaeAmuletAlt);
		bundle.put( CARD15, cardHalfGhost);
		bundle.put( CARD16, cardHalfGhostSpare);
		bundle.put( CARD17, cardShanghaiDoll);
		bundle.put( CARD18, cardIceFairy);
		bundle.put( CARD19, cardStarCard);
		bundle.put( CARD20, cardLunaCard);
		bundle.put( CARD21, cardSunnyCard);
		bundle.put( CARD22, cardLarvaScale);
		bundle.put( CARD23, cardNemunoKnife);
		bundle.put( CARD24, cardSeiranBleedingHammer);
		bundle.put( CARD25, cardOkinaBackdoor);
		bundle.put( CARD26, cardAnnoyingUfo);
		bundle.put( CARD27, cardAncientMagatama);
		bundle.put( CARD28, cardShouPagoda);
		bundle.put( CARD29, cardSekibankiHead);
		bundle.put( CARD30, cardTeacupReimu);
		bundle.put( CARD31, cardTeacupMarisa);
		bundle.put( CARD32, cardBlankCard);
		bundle.put( CARD33, cardNitoriDilemma);
		bundle.put( CARD34, cardRingoBrandDango);
		bundle.put( CARD35, cardHijiriSutra);
		bundle.put( CARD36, cardFlandreDestruction);
		bundle.put( CARD37, cardDragonPassage);
		bundle.put( CARD38, cardAunnHounds);
		bundle.put( CARD39, cardGaleGeta);
		bundle.put( CARD40, cardEirinElixir);
		bundle.put( CARD41, cardMinorikoCrop);
		bundle.put( CARD42, cardPhoenixTail);
		bundle.put( CARD43, cardEikiMoney);
		bundle.put( CARD44, cardMiserAdvice);
		bundle.put( CARD45, cardKanakoOffering);
		bundle.put( CARD46, cardYachieThreat);
		bundle.put( CARD47, cardKaguyaStash);
		bundle.put( CARD48, cardTewiFoot);
		bundle.put( CARD49, cardPebbleHat);
		bundle.put( CARD50, cardDanmakuGhost);
		bundle.put( CARD51, cardSuwakoFrog);
		bundle.put( CARD52, cardDragonPipe);
		bundle.put( CARD53, cardMomoyoCentipede);
		bundle.put( CARD54, cardSuikaGourd);
		bundle.put( CARD55, cardLifeBurningTorch);
		bundle.put( CARD56, cardIrresistibleFan);
		bundle.put( CARD57, cardPristineConfidence);
		bundle.put( CARD58, cardScreenBorder);
		bundle.put( CARD59, cardMiracleMallet);
		bundle.put( CARD60, cardTenshiKeystone);
		bundle.put( CARD61, cardClownpieceMoon);
		bundle.put( CARD62, cardMikoAuthority);
		bundle.put( CARD63, cardVampireFang);
		bundle.put( CARD64, cardUndergroundSun);
		bundle.put( CARD65, cardItemSeason);
		bundle.put( CARD66, cardSpiritBottle);
		bundle.put( CARD67, cardMegumuBarleyRice);
		bundle.put( CARD68, cardSmeltScale);
		bundle.put( CARD69, cardTooHonestSignpost);
		bundle.put( CARD70, cardKomachiDetour);
	}

	public static void restoreFromBundle( Bundle bundle ) {
		talismanUsed	= bundle.getInt( TALISMANUSED );
		goldCollected	= bundle.getInt( GOLD );
		goldPickedup	= bundle.getInt( GOLDPICKEDUP );
		highestFloor = bundle.getInt( DEEPEST );
		highestAscent   = bundle.getInt( HIGHEST );
		enemiesSlain	= bundle.getInt( SLAIN );
		foodEaten		= bundle.getInt( FOOD );
		itemsCrafted    = bundle.getInt( ALCHEMY );
		kisumesKilled	= bundle.getInt( KISUMES );
		murasasKilled = bundle.getInt(MURASAS);
		trap_act_count = bundle.getInt( TRAP_ACT_COUNT );
		bamboo = bundle.getInt( BAMBOO );
		chimata = bundle.getInt( CHIMATA );
		exterminatedEnemy = bundle.getClass( EXTERMINATED_ENEMY );

		upgradesUsed    = bundle.getInt( UPGRADES );
		sneakAttacks    = bundle.getInt( SNEAKS );
		thrownAssists   = bundle.getInt( THROWN );

		timeReset   = bundle.getInt( TIMERESET );
		tenshiEarthquake   = bundle.getInt( TENSHIEARTHQUAKE );
		bordercount   = bundle.getInt( BORDERCOUNT );

		HT_bonus = bundle.getInt( MAXHP_DOWN );
		life_count = bundle.getInt(LIFE_COUNT);
		bomb_count = bundle.getInt(BOMB_COUNT);
		dismantle_count = bundle.getInt(DISMANTLE_COUNT);

		power		= bundle.getInt( POWER );
		value		= bundle.getInt( VALUE );
		life		= bundle.getInt( LIFE );
		lifefragment		= bundle.getInt( LIFEFRAGMENT );
		spellcard		= bundle.getInt( SPELLCARD );
		spellcardfragment		= bundle.getInt( SPELLCARDFRAGMENT );

		tenshiattackstep = bundle.getInt( TENSHIATTACKSTEP );
		eirinelixircount = bundle.getInt( EIRINELIXIRCOUNT );

		nextvalue		= bundle.getInt( NEXTVALUE );

		lifefragmentkill		= bundle.getInt( LIFEFRAGMENTKILL );
		spellcardfragmentkill		= bundle.getInt( SPELLCARDFRAGMENTKILL );

		difficulty		= bundle.getInt( DIFFICULTY );

		double_speed_upgrade	= bundle.getInt( DOUBLE_SPEED_UPGRADE );
		duration		= bundle.getFloat( DURATION );

		identify_use	= bundle.getBoolean( IDENTIFY_USE );
		fate_use		= bundle.getBoolean( FATE_USE );
		earth_use		= bundle.getBoolean( EARTH_USE );
		exorcism_use		= bundle.getBoolean( EXORCISM_USE );
		transmute_use		= bundle.getBoolean( TRANSMUTE_USE );

		elixir_trigger = bundle.getBoolean( ELIXIRTRIGGER );

		remi_countdown = bundle.getBoolean( REMI_COUNTDOWN );

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
		abcarduse	= bundle.getBoolean( ABCARDUSE );
		suwakorelic_active = bundle.getBoolean( SUWAKORELIC_ACTIVE );


		boss1	= bundle.getBoolean( BOSS1 );
		boss2	= bundle.getBoolean( BOSS2 );
		boss3	= bundle.getBoolean( BOSS3 );
		boss4	= bundle.getBoolean( BOSS4 );
		boss5	= bundle.getBoolean( BOSS5 );
		boss6	= bundle.getBoolean( BOSS6 );
		boss7	= bundle.getBoolean( BOSS7 );
		boss8	= bundle.getBoolean( BOSS8 );
		boss9	= bundle.getBoolean( BOSS9 );

		cardNazrinGoldMoney = bundle.getBoolean( CARD1 );
		cardNazrinAlchemyMoney = bundle.getBoolean( CARD2 );
		cardLifeCard = bundle.getBoolean( CARD3 );
		cardKeikiCreation = bundle.getBoolean( CARD4 );
		cardShionUchiwa = bundle.getBoolean( CARD5 );
		cardDoremySheep = bundle.getBoolean( CARD6 );
		cardYingYangOrb = bundle.getBoolean( CARD7 );
		cardYingYangOrbNeedle = bundle.getBoolean( CARD8 );
		cardMiniHakkero = bundle.getBoolean( CARD9 );
		cardMiniHakkeroMissile = bundle.getBoolean( CARD10 );
		cardMaidKnife = bundle.getBoolean( CARD11 );
		cardMaidKnifeRicochet = bundle.getBoolean( CARD12 );
		cardSanaeAmulet = bundle.getBoolean( CARD13 );
		cardSanaeAmuletAlt = bundle.getBoolean( CARD14 );
		cardHalfGhost = bundle.getBoolean( CARD15 );
		cardHalfGhostSpare = bundle.getBoolean( CARD16 );
		cardShanghaiDoll = bundle.getBoolean( CARD17 );
		cardIceFairy = bundle.getBoolean( CARD18 );
		cardStarCard = bundle.getBoolean( CARD19 );
		cardLunaCard = bundle.getBoolean( CARD20 );
		cardSunnyCard = bundle.getBoolean( CARD21 );
		cardLarvaScale = bundle.getBoolean( CARD22 );
		cardNemunoKnife = bundle.getBoolean( CARD23 );
		cardSeiranBleedingHammer = bundle.getBoolean( CARD24 );
		cardOkinaBackdoor = bundle.getBoolean( CARD25 );
		cardAnnoyingUfo = bundle.getBoolean( CARD26 );
		cardAncientMagatama = bundle.getBoolean( CARD27 );
		cardShouPagoda = bundle.getBoolean( CARD28 );
		cardSekibankiHead = bundle.getBoolean( CARD29 );
		cardTeacupReimu = bundle.getBoolean( CARD30 );
		cardTeacupMarisa = bundle.getBoolean( CARD31 );
		cardBlankCard = bundle.getBoolean( CARD32 );
		cardNitoriDilemma = bundle.getBoolean( CARD33 );
		cardRingoBrandDango = bundle.getBoolean( CARD34 );
		cardHijiriSutra = bundle.getBoolean( CARD35 );
		cardFlandreDestruction = bundle.getBoolean( CARD36 );
		cardDragonPassage = bundle.getBoolean( CARD37 );
		cardAunnHounds = bundle.getBoolean( CARD38 );
		cardGaleGeta = bundle.getBoolean( CARD39 );
		cardEirinElixir = bundle.getBoolean( CARD40 );
		cardMinorikoCrop = bundle.getBoolean( CARD41 );
		cardPhoenixTail = bundle.getBoolean( CARD42 );
		cardEikiMoney = bundle.getBoolean( CARD43 );
		cardMiserAdvice = bundle.getBoolean( CARD44 );
		cardKanakoOffering = bundle.getBoolean( CARD45 );
		cardYachieThreat = bundle.getBoolean( CARD46 );
		cardKaguyaStash = bundle.getBoolean( CARD47 );
		cardTewiFoot = bundle.getBoolean( CARD48 );
		cardPebbleHat = bundle.getBoolean( CARD49 );
		cardDanmakuGhost = bundle.getBoolean( CARD50 );
		cardSuwakoFrog = bundle.getBoolean( CARD51 );
		cardDragonPipe = bundle.getBoolean( CARD52 );
		cardMomoyoCentipede = bundle.getBoolean( CARD53 );
		cardSuikaGourd = bundle.getBoolean( CARD54 );
		cardLifeBurningTorch = bundle.getBoolean( CARD55 );
		cardIrresistibleFan = bundle.getBoolean( CARD56 );
		cardPristineConfidence = bundle.getBoolean( CARD57 );
		cardScreenBorder = bundle.getBoolean( CARD58 );
		cardMiracleMallet = bundle.getBoolean( CARD59 );
		cardTenshiKeystone = bundle.getBoolean( CARD60 );
		cardClownpieceMoon = bundle.getBoolean( CARD61 );
		cardMikoAuthority = bundle.getBoolean( CARD62 );
		cardVampireFang = bundle.getBoolean( CARD63 );
		cardUndergroundSun = bundle.getBoolean( CARD64 );
		cardItemSeason = bundle.getBoolean( CARD65 );
		cardSpiritBottle = bundle.getBoolean( CARD66 );
		cardMegumuBarleyRice = bundle.getBoolean( CARD67 );
		cardSmeltScale = bundle.getBoolean( CARD68 );
		cardTooHonestSignpost = bundle.getBoolean( CARD69 );
		cardKomachiDetour = bundle.getBoolean( CARD70 );
	}

	public static void preview( GamesInProgress.Info info, Bundle bundle ){
		info.goldCollected  = bundle.getInt( GOLD );
		info.maxFloor = bundle.getInt( DEEPEST );
	}
}