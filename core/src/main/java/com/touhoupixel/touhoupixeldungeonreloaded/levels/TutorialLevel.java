/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeonreloaded.levels;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Cirno;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Eiki;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Kokoro;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Luna;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Nazrin;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Ringo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Star;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Sunny;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Wriggle;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine1;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine10;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine11;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine12;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine13;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine14;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine2;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine3;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine4;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine5;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine6;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine7;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine8;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.TutorialKeine9;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Amulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Torch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.UpgradeCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.SpearheadHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.RejuvenationHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.NocturnalHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDoublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfFixer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfIdentify;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRecharging;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.EnragingTalisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.FlandreTalisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.KameTalisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.BackdoorTalisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.BulletDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MystiaWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YuyukoFoldingFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.KomachiDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Sungrass;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.watabou.noosa.audio.Music;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class TutorialLevel extends Level {

	{
		viewDistance = 100;

		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	public void playLevelMusic() {
		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.FLOOR_1, Assets.Music.FLOOR_1, Assets.Music.FLOOR_1},
				new float[]{1, 1, 0.5f},
				false);
	}

	private static int WIDTH = 39;
	private static int HEIGHT = 50;

	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_1;
	}

	@Override
	public String waterTex() {
		return Assets.Environment.WATER_1;
	}

	@Override
	protected boolean build() {
		setSize(WIDTH, HEIGHT);

		transitions.add(new LevelTransition(this, 160, LevelTransition.Type.SURFACE));
		transitions.add(new LevelTransition(this, 190, LevelTransition.Type.SURFACE));

		buildLevel();

		return true;
	}

	private static final short n = -1;
	private static final short W = Terrain.WALL;
	private static final short e = Terrain.EMPTY;
	private static final short i = Terrain.EMPTY; //items and npcs
	private static final short D = Terrain.DOOR;
	private static final short P = Terrain.SECRET_DOOR;
	private static final short E = Terrain.ENTRANCE;

	private static short[] level = {
		    W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, i, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, E, i, e, W, e, e, e, e, e, e, e, e, i, e, e, D, e, e, i, e, i, e, i, i, i, i, e, W, e, e, E, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, i, e, e, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, i, e, e, W, W,
			W, W, W, W, D, W, W, W, W, W, D, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, D, W, W, e, e, e, e, e, W, W,
			W, W, e, e, e, i, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, i, e, e, e, i, e, e, e, i, e, e, e, W, e, W, e, e, e, e, e, W, W,
			W, W, e, e, i, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, D, e, W, e, i, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W, W, W, W, D, W, W, W, W,
			W, W, e, i, e, i, e, W, e, e, i, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, i, e, i, e, W, e, e, e, e, e, W, e, e, e, i, e, e, e, i, e, e, e, i, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, W, W, D, W, W, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, i, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, i, e, e, e, i, e, e, e, i, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, i, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, i, e, e, W, W, W, D, W, W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, i, e, e, e, i, e, e, e, i, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, W, W, D, W, W, W, e, e, i, e, e, W, e, e, e, i, e, e, e, i, e, e, e, i, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, i, e, W, e, e, i, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, i, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, i, W, W, W, W, W, W, W, W, W, W, W, W, W, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, i, e, i, e, W, e, e, e, e, e, P, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, i, i, i, e, e, W, W,
			W, W, e, i, e, i, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, D, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, i, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, W, W, D, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, D, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, i, e, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, i, e, e, e, e, W, e, e, e, e, i, i, i, i, i, i, e, e, e, e, i, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, D, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, i, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, i, e, e, W, W,
			W, W, e, e, W, e, e, W, e, e, i, e, e, W, e, e, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, e, e, i, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, i, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, i, e, e, e, e, W, W,
			W, W, e, e, e, e, e, D, e, e, e, e, e, W, e, e, e, e, e, D, e, e, e, e, e, i, e, e, e, e, e, D, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W
	};

	private void buildLevel(){
		int pos = 0 + 0*width();

		short[] levelTiles = level;
		for (int i = 0; i < levelTiles.length; i++){
			if (levelTiles[i] != n) map[pos] = levelTiles[i];

			pos++;
		}
	}

	@Override
	protected void createMobs() {
		TutorialKeine1 tk = new TutorialKeine1();
		tk.pos = 161;
		mobs.add(tk);

		TutorialKeine2 tk2 = new TutorialKeine2();
		tk2.pos = 317;
		mobs.add(tk2);

		TutorialKeine3 tk3 = new TutorialKeine3();
		tk3.pos = 707;
		mobs.add(tk3);

		TutorialKeine4 tk4 = new TutorialKeine4();
		tk4.pos = 1097;
		mobs.add(tk4);

		TutorialKeine5 tk5 = new TutorialKeine5();
		tk5.pos = 1487;
		mobs.add(tk5);

		TutorialKeine6 tk6 = new TutorialKeine6();
		tk6.pos = 1535;
		mobs.add(tk6);

		TutorialKeine7 tk7 = new TutorialKeine7();
		tk7.pos = 1748;
		mobs.add(tk7);

		TutorialKeine8 tk8 = new TutorialKeine8();
		tk8.pos = 1386;
		mobs.add(tk8);

		TutorialKeine9 tk9 = new TutorialKeine9();
		tk9.pos = 1182;
		mobs.add(tk9);

		TutorialKeine10 tk10 = new TutorialKeine10();
		tk10.pos = 830;
		mobs.add(tk10);

		TutorialKeine11 tk11 = new TutorialKeine11();
		tk11.pos = 245;
		mobs.add(tk11);

		TutorialKeine12 tk12 = new TutorialKeine12();
		tk12.pos = 137;
		mobs.add(tk12);

		TutorialKeine13 tk13 = new TutorialKeine13();
		tk13.pos = 1280;
		mobs.add(tk13);

		TutorialKeine14 tk14 = new TutorialKeine14();
		tk14.pos = 501;
		mobs.add(tk14);

		Nazrin nazrin = new Nazrin();
		nazrin.pos = 472;
		mobs.add(nazrin);
		nazrin.state = nazrin.SLEEPING;

		Wriggle wriggle = new Wriggle();
		wriggle.pos = 1648;
		mobs.add(wriggle);
		wriggle.state = wriggle.SLEEPING;

		Cirno cirno = new Cirno();
		cirno.pos = 172;
		mobs.add(cirno);
		cirno.state = cirno.SLEEPING;

		Ringo ringo = new Ringo();
		ringo.pos = 450;
		mobs.add(ringo);
		ringo.state = ringo.SLEEPING;

		Kokoro kokoro = new Kokoro();
		kokoro.pos = 606;
		mobs.add(kokoro);
		kokoro.state = kokoro.SLEEPING;

		Sunny sunny = new Sunny();
		sunny.pos = 762;
		mobs.add(sunny);
		sunny.state = sunny.SLEEPING;

		Luna luna = new Luna();
		luna.pos = 918;
		mobs.add(luna);
		luna.state = luna.SLEEPING;

		Star star = new Star();
		star.pos = 1074;
		mobs.add(star);
		star.state = star.SLEEPING;

		Eiki eiki = new Eiki();
		eiki.pos = 1780;
		mobs.add(eiki);
		eiki.state = eiki.SLEEPING;
	}

	@Override
	protected void createItems() {
		drop( new StrengthCard(), 549 );
		drop( new StrengthCard(), 588 );
		drop( new UpgradeCard(), 551 );
		drop( new UpgradeCard(), 590 );

		drop( new StrengthCard(), 1555 );
		drop( new StrengthCard(), 1594 );
		drop( new StrengthCard(), 1633 );
		drop( new StrengthCard(), 1672 );
		drop( new UpgradeCard(), 1545 );
		drop( new UpgradeCard(), 1546 );
		drop( new UpgradeCard(), 1547 );
		drop( new UpgradeCard(), 1548 );
		drop( new UpgradeCard(), 1549 );
		drop( new UpgradeCard(), 1550 );

		drop( new PotionOfDoublespeed(), 446 );
		drop( new PotionOfDanmaku(), 454 );
		drop( new ScrollOfRecharging(), 602 );
		drop( new ScrollOfIdentify(), 610 );
		drop( new SpearheadHerb(), 758 );
		drop( new NocturnalHerb(), 766 );
		drop( new EnragingTalisman(), 914 );
		drop( new FlandreTalisman(), 922 );
		drop( new Sungrass.Seed(), 1070 );
		drop( new Swiftthistle.Seed(), 1078 );

		drop( new MystiaWing().identify(), 862 );
		drop( new YuyukoFoldingFan().identify(), 556 );

		drop( new Spellcard(), 178 );
		drop( new Life(), 180 );
		drop( new PotionOfExperience().identify(), 182 );
		drop( new PotionOfExperience().identify(), 183 );
		drop( new PotionOfExperience().identify(), 184 );
		drop( new PotionOfExperience().identify(), 185 );

		drop( new Food(), 1063 );
		drop( new Torch(), 1102 );
		drop( new WandOfMagicMissile(), 1141 );

		drop( new BulletDanmaku().quantity(5), 1281 );
		drop( new KomachiDanmaku().quantity(5), 1282 );

		drop( new PotionOfHealing(), 1212 );
		drop( new ScrollOfFixer(), 1214 );
		drop( new RejuvenationHerb(), 1290 );
		drop( new KameTalisman().quantity(5), 1292 );
		drop( new BackdoorTalisman().quantity(5), 1693 );

		drop( new Amulet(), 268 );
	}

	@Override
	public int randomRespawnCell( Char ch ) {
		int cell;
		do {
			cell = entrance() + PathFinder.NEIGHBOURS8[Random.Int(8)];
		} while (!passable[cell]
				|| Actor.findChar(cell) != null);
		return cell;
	}
}