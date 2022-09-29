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

package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.MorfonicaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.NitoriArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.GoldenDragonArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.HanasakigawaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.HecatiaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.MarisaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.SakuyaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.MaxwellArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.PC98MarisaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.PC98ReimuArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.AyaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.KirakiraBandArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.RumiaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.SanaeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.SharkArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ToyohimeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.YorihimeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.YuyukoArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.AlchemistsToolkit;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.CapeOfThorns;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.ChaliceOfBlood;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.CloakOfShadows;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.EtherealChains;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.HornOfPlenty;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.LloydsBeacon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.MasterThievesArmband;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.SandalsOfNature;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TalismanOfForesight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.UnstableSpellbook;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.MysteryMeat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Pasty;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfCursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDanDamageIncrease;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfCirno;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDoublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfFlamePower;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfFrost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfFrostPower;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHaste;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLightReverse;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLiquidFlame;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfParalyticGas;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfPurity;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfReach;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfRingoDango;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfStrength;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfToxicGas;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfYingYang;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfAccuracy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfElements;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfEnergy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfEvasion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfForce;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfFuror;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfHaste;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfSharpshooting;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfTenacity;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfWealth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfEnchantArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfEnchantDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfEnchantHakkero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfEnchantWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfIdentify;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfLullaby;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMirrorImage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRecharging;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRemoveCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRetribution;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTerror;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTransmutation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfUpgrade;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfFear;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfAggression;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfAugmentation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfBlast;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfBlink;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfClairvoyance;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfDeepSleep;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfDisarming;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfEnchantment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfFlock;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfIntuition;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfShock;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.BindTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.BlowawayTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.SevenDaysTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.DebilitationTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.DecoyTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.ExplosionTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.FlandreTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.FogpurgeTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.ImpedeTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.JudgementTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.JunkoTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.MurasaTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.SwapTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.Tailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.HalveTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfBlastWave;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfCorrosion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfCorruption;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfDisintegration;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfFireblast;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfFrost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfLightning;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfLivingEarth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfPrismaticLight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfRegrowth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.RanTail;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.UtsuhoRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YukariUmbrella;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KoishiDagger;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KoakumaWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.CirnoWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.AyaFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.TenguSmartphone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SanaeExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SeiranHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.JunkoSword;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.BigYingYangOrb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KogasaUmbrella;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MediumYingYangOrb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.EikiRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MystiaWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YoumuHalfPhantom;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.RemiliaWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SkySword;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.PaleSword;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.TenguShortsword;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.Radio;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SmallYingYangOrb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.GoldenExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ShinmyomaruNeedle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ReimuExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.AyaDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.KunaiDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.MedicineDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.MerlinDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ReisenDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ScaleDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.EikiDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.StarDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.BulletDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ShardDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.CircleDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.FlameDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.KomachiDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.TewiDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.RiceDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ReimuTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.InvertDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.CirnoDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.YoumuDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.YuukaDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Blindweed;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Dreamfoil;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Fadeleaf;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Firebloom;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Icecap;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Plant;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Rotberry;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Sorrowmoss;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Starflower;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Stormvine;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Sungrass;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 2, 2, MeleeWeapon.class),
		WEP_T1	( 0, 0, MeleeWeapon.class),
		WEP_T2	( 0, 0, MeleeWeapon.class),
		WEP_T3	( 0, 0, MeleeWeapon.class),
		WEP_T4	( 0, 0, MeleeWeapon.class),
		WEP_T5	( 0, 0, MeleeWeapon.class),

		ARMOR	    ( 3, 2, Armor.class ),
		ARMOR_T1	( 0, 0, Armor.class ),
		ARMOR_T2	( 0, 0, Armor.class ),
		ARMOR_T3	( 0, 0, Armor.class ),
		ARMOR_T4	( 0, 0, Armor.class ),
		ARMOR_T5	( 0, 0, Armor.class ),

		MISSILE ( 1, 2, MissileWeapon.class ),
		MIS_T1  ( 0, 0, MissileWeapon.class ),
		MIS_T2  ( 0, 0, MissileWeapon.class ),
		MIS_T3  ( 0, 0, MissileWeapon.class ),
		MIS_T4  ( 0, 0, MissileWeapon.class ),
		MIS_T5  ( 0, 0, MissileWeapon.class ),

		WAND	( 1, 1, Wand.class ),
		RING	( 1, 0, Ring.class ),
		ARTIFACT( 0, 1, Artifact.class),

		FOOD	( 0, 0, Food.class ),

		POTION	( 8, 8, Potion.class ),
		SEED	( 1, 1, Plant.Seed.class ),

		SCROLL	( 8, 8, Scroll.class ),
		STONE   ( 1, 1, Runestone.class),
		TAILSMAN( 6, 6, Tailsman.class),

		GOLD	( 10, 10,   Gold.class );

		public Class<?>[] classes;

		//some item types use a deck-based system, where the probs decrement as items are picked
		// until they are all 0, and then they reset. Those generator classes should define
		// defaultProbs. If defaultProbs is null then a deck system isn't used.
		//Artifacts in particular don't reset, no duplicates!
		public float[] probs;
		public float[] defaultProbs = null;

		//game has two decks of 35 items for overall category probs
		//one deck has a ring and extra armor, the other has an artifact and extra thrown weapon
		public float firstProb;
		public float secondProb;
		public Class<? extends Item> superClass;

		private Category( float firstProb, float secondProb, Class<? extends Item> superClass ) {
			this.firstProb = firstProb;
			this.secondProb = secondProb;
			this.superClass = superClass;
		}

		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}

			//items without a category-defined order are sorted based on the spritesheet
			return Short.MAX_VALUE+item.image();
		}

		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };

			POTION.classes = new Class<?>[]{
					PotionOfStrength.class, //2 drop every chapter, see Dungeon.posNeeded()
					PotionOfHealing.class,
					PotionOfMindVision.class,
					PotionOfFrost.class,
					PotionOfLiquidFlame.class,
					PotionOfToxicGas.class,
					PotionOfHaste.class,
					PotionOfInvisibility.class,
					PotionOfLevitation.class,
					PotionOfParalyticGas.class,
					PotionOfPurity.class,
					PotionOfExperience.class,
					PotionOfRingoDango.class,
					PotionOfMight.class,
					PotionOfDoublespeed.class,
					PotionOfLightHealing.class,
					PotionOfCirno.class,
					PotionOfCursedBlow.class,
					PotionOfDanDamageIncrease.class,
					PotionOfFlamePower.class,
					PotionOfFrostPower.class,
					PotionOfLightReverse.class,
					PotionOfReach.class,
					PotionOfYingYang.class};
			POTION.defaultProbs = new float[]{ 0, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 };
			POTION.probs = POTION.defaultProbs.clone();

			SEED.classes = new Class<?>[]{
					Rotberry.Seed.class, //quest item
					Sungrass.Seed.class,
					Fadeleaf.Seed.class,
					Icecap.Seed.class,
					Firebloom.Seed.class,
					Sorrowmoss.Seed.class,
					Swiftthistle.Seed.class,
					Blindweed.Seed.class,
					Stormvine.Seed.class,
					Earthroot.Seed.class,
					Dreamfoil.Seed.class,
					Starflower.Seed.class};
			SEED.defaultProbs = new float[]{ 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 2 };
			SEED.probs = SEED.defaultProbs.clone();

			SCROLL.classes = new Class<?>[]{
					ScrollOfUpgrade.class, //3 drop every chapter, see Dungeon.souNeeded()
					ScrollOfIdentify.class,
					ScrollOfRemoveCurse.class,
					ScrollOfMirrorImage.class,
					ScrollOfRecharging.class,
					ScrollOfTeleportation.class,
					ScrollOfLullaby.class,
					ScrollOfMagicMapping.class,
					ScrollOfRage.class,
					ScrollOfRetribution.class,
					ScrollOfTerror.class,
					ScrollOfEnchantWeapon.class,
					ScrollOfEnchantArmor.class,
					ScrollOfEnchantHakkero.class,
					ScrollOfEnchantDanmaku.class,
					ScrollOfTransmutation.class
			};
			SCROLL.defaultProbs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 0, 0, 0, 0, 1 };
			SCROLL.probs = SCROLL.defaultProbs.clone();

			STONE.classes = new Class<?>[]{
					StoneOfEnchantment.class,   //1 is guaranteed to drop on floors 6-19
					StoneOfIntuition.class,     //1 additional stone is also dropped on floors 1-3
					StoneOfDisarming.class,
					StoneOfFlock.class,
					StoneOfShock.class,
					StoneOfBlink.class,
					StoneOfDeepSleep.class,
					StoneOfClairvoyance.class,
					StoneOfAggression.class,
					StoneOfBlast.class,
					StoneOfFear.class,
					StoneOfAugmentation.class  //1 is sold in each shop
			};
			STONE.defaultProbs = new float[]{ 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0 };
			STONE.probs = STONE.defaultProbs.clone();

			TAILSMAN.classes = new Class<?>[]{
					BindTailsman.class,
					BlowawayTailsman.class,
					SevenDaysTailsman.class,
					DecoyTailsman.class,
					ExplosionTailsman.class,
					FogpurgeTailsman.class,
					ImpedeTailsman.class,
					SwapTailsman.class,
					DebilitationTailsman.class,
					JudgementTailsman.class,
					FlandreTailsman.class,
					MurasaTailsman.class,
					JunkoTailsman.class,
					HalveTailsman.class
			};
			TAILSMAN.defaultProbs = new float[]{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
			TAILSMAN.probs = TAILSMAN.defaultProbs.clone();

			WAND.classes = new Class<?>[]{
					WandOfMagicMissile.class,
					WandOfLightning.class,
					WandOfDisintegration.class,
					WandOfFireblast.class,
					WandOfCorrosion.class,
					WandOfBlastWave.class,
					WandOfLivingEarth.class,
					WandOfFrost.class,
					WandOfPrismaticLight.class,
					WandOfWarding.class,
					WandOfCorruption.class,
					WandOfRegrowth.class };
			WAND.probs = new float[]{ 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3 };

			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};

			WEP_T1.classes = new Class<?>[]{
					ReimuExorcismRod.class,
					SanaeExorcismRod.class,
					KoishiDagger.class,
					MarisaStaff.class
			};
			WEP_T1.probs = new float[]{ 1, 1, 1, 0 };

			WEP_T2.classes = new Class<?>[]{
					TenguShortsword.class,
					KogasaUmbrella.class,
					Radio.class,
					MystiaWing.class,
					KoakumaWing.class
			};
			WEP_T2.probs = new float[]{ 6, 5, 5, 4, 4 };

			WEP_T3.classes = new Class<?>[]{
					SmallYingYangOrb.class,
					EikiRod.class,
					PaleSword.class,
					YoumuHalfPhantom.class,
					SkySword.class,
					ShinmyomaruNeedle.class
			};
			WEP_T3.probs = new float[]{ 6, 5, 5, 4, 4, 4 };

			WEP_T4.classes = new Class<?>[]{
					MediumYingYangOrb.class,
					UtsuhoRod.class,
					CirnoWing.class,
					RemiliaWing.class,
					RanTail.class,
					YukariUmbrella.class
			};
			WEP_T4.probs = new float[]{ 6, 5, 5, 4, 4, 4 };

			WEP_T5.classes = new Class<?>[]{
					BigYingYangOrb.class,
					GoldenExorcismRod.class,
					TenguSmartphone.class,
					SeiranHammer.class,
					JunkoSword.class,
					AyaFan.class
			};
			WEP_T5.probs = new float[]{ 6, 5, 5, 4, 4, 4 };

			//see Generator.randomArmor
			ARMOR.classes = new Class<?>[]{};
			ARMOR.probs = new float[]{};

			//see Generator.randomArmor
			ARMOR_T1.classes = new Class<?>[]{
					ReimuArmor.class};
			ARMOR_T1.probs = new float[]{ 5 };

			ARMOR_T2.classes = new Class<?>[]{
					MarisaArmor.class,
					PC98ReimuArmor.class,
					RumiaArmor.class};
			ARMOR_T2.probs = new float[]{ 5, 5, 5 };

			ARMOR_T3.classes = new Class<?>[]{
					SakuyaArmor.class,
					PC98MarisaArmor.class,
					HanasakigawaArmor.class,
					NitoriArmor.class,
					KirakiraBandArmor.class};
			ARMOR_T3.probs = new float[]{ 5, 5, 5, 5, 5 };

			ARMOR_T4.classes = new Class<?>[]{
					SanaeArmor.class,
					YorihimeArmor.class,
					YuyukoArmor.class,
					SharkArmor.class,
					MaxwellArmor.class};
			ARMOR_T4.probs = new float[]{ 5, 5, 5, 5, 5 };

			ARMOR_T5.classes = new Class<?>[]{
					AyaArmor.class,
					ToyohimeArmor.class,
					HecatiaArmor.class,
					MorfonicaArmor.class,
					GoldenDragonArmor.class};
			ARMOR_T5.probs = new float[]{ 5, 5, 5, 5, 5 };

			//see Generator.randomMissile
			MISSILE.classes = new Class<?>[]{};
			MISSILE.probs = new float[]{};

			MIS_T1.classes = new Class<?>[]{
					ReimuTailsman.class,
					ThrowingKnife.class
			};
			MIS_T1.probs = new float[]{ 5, 5 };

			MIS_T2.classes = new Class<?>[]{
					CircleDanmaku.class,
					FlameDanmaku.class,
					KunaiDanmaku.class,
					RiceDanmaku.class,
					ScaleDanmaku.class
			};
			MIS_T2.probs = new float[]{ 5, 5, 5, 5, 5 };

			MIS_T3.classes = new Class<?>[]{
					BulletDanmaku.class,
					CirnoDanmaku.class,
					MerlinDanmaku.class,
					ShardDanmaku.class,
					YoumuDanmaku.class
			};
			MIS_T3.probs = new float[]{ 5, 5, 5, 5, 5 };

			MIS_T4.classes = new Class<?>[]{
					AyaDanmaku.class,
					InvertDanmaku.class,
					ReisenDanmaku.class,
					StarDanmaku.class,
					YuukaDanmaku.class
			};
			MIS_T4.probs = new float[]{ 5, 5, 5, 5, 5 };

			MIS_T5.classes = new Class<?>[]{
					EikiDanmaku.class,
					KomachiDanmaku.class,
					MedicineDanmaku.class,
					TewiDanmaku.class
			};
			MIS_T5.probs = new float[]{ 5, 5, 5, 5 };

			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };

			RING.classes = new Class<?>[]{
					RingOfAccuracy.class,
					RingOfEvasion.class,
					RingOfElements.class,
					RingOfForce.class,
					RingOfFuror.class,
					RingOfHaste.class,
					RingOfEnergy.class,
					RingOfMight.class,
					RingOfSharpshooting.class,
					RingOfTenacity.class,
					RingOfWealth.class};
			RING.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

			ARTIFACT.classes = new Class<?>[]{
					CapeOfThorns.class,
					ChaliceOfBlood.class,
					CloakOfShadows.class,
					HornOfPlenty.class,
					MasterThievesArmband.class,
					SandalsOfNature.class,
					TalismanOfForesight.class,
					TimekeepersHourglass.class,
					UnstableSpellbook.class,
					AlchemistsToolkit.class,
					LloydsBeacon.class,
					EtherealChains.class
			};
			ARTIFACT.defaultProbs = new float[]{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1};
			ARTIFACT.probs = ARTIFACT.defaultProbs.clone();
		}
	}

	private static final float[][] floorSetTierProbs = new float[][] {
			{0, 75, 20,  4,  0},
			{0, 75, 20,  4,  0},
			{0, 75, 20,  4,  0},
			{0, 25, 50, 20,  5},
			{0, 25, 50, 20,  5},
			{0, 25, 50, 20,  5},
			{0,  0, 40, 50, 10},
			{0,  0, 40, 50, 10},
			{0,  0, 40, 50, 10},
			{0,  0, 40, 50, 10},
			{0,  0, 20, 40, 40},
			{0,  0, 20, 40, 40},
			{0,  0, 20, 40, 40},
			{0,  0, 20, 40, 40},
			{0,  0, 20, 40, 40},
			{0,  0,  0, 20, 80},
			{0,  0,  0, 20, 80},
			{0,  0,  0, 20, 80},
			{0,  0,  0, 20, 80},
			{0,  0,  0, 20, 80}
	};

	private static boolean usingFirstDeck = false;
	private static HashMap<Category,Float> categoryProbs = new LinkedHashMap<>();

	public static void fullReset() {
		usingFirstDeck = Random.Int(2) == 0;
		generalReset();
		for (Category cat : Category.values()) {
			reset(cat);
		}
	}

	public static void generalReset(){
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, usingFirstDeck ? cat.firstProb : cat.secondProb );
		}
	}

	public static void reset(Category cat){
		if (cat.defaultProbs != null) cat.probs = cat.defaultProbs.clone();
	}

	public static Item random() {
		Category cat = Random.chances( categoryProbs );
		if (cat == null){
			usingFirstDeck = !usingFirstDeck;
			generalReset();
			cat = Random.chances( categoryProbs );
		}
		categoryProbs.put( cat, categoryProbs.get( cat ) - 1);
		return random( cat );
	}

	public static Item random( Category cat ) {
		switch (cat) {
			case ARMOR:
				return randomArmor();
			case WEAPON:
				return randomWeapon();
			case MISSILE:
				return randomMissile();
			case ARTIFACT:
				Item item = randomArtifact();
				//if we're out of artifacts, return a ring instead.
				return item != null ? item : random(Category.RING);
			default:
				int i = Random.chances(cat.probs);
				if (i == -1) {
					reset(cat);
					i = Random.chances(cat.probs);
				}
				if (cat.defaultProbs != null) cat.probs[i]--;
				return ((Item) Reflection.newInstance(cat.classes[i])).random();
		}
	}

	//overrides any deck systems and always uses default probs
	public static Item randomUsingDefaults( Category cat ){
		if (cat.defaultProbs == null) {
			return random(cat); //currently covers weapons/armor/missiles
		} else {
			return ((Item) Reflection.newInstance(cat.classes[Random.chances(cat.defaultProbs)])).random();
		}
	}

	public static Item random( Class<? extends Item> cl ) {
		return Reflection.newInstance(cl).random();
	}

	public static final Category[] armorTiers = new Category[]{
			Category.ARMOR_T1,
			Category.ARMOR_T2,
			Category.ARMOR_T3,
			Category.ARMOR_T4,
			Category.ARMOR_T5
	};

	public static Armor randomArmor(){
		return randomArmor(Dungeon.depth / 10);
	}

	public static Armor randomArmor(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		Category c2 = armorTiers[Random.chances(floorSetTierProbs[floorSet])];
		Armor w2 = (Armor)Reflection.newInstance(c2.classes[Random.chances(c2.probs)]);
		w2.random();
		return w2;
	}

	public static final Category[] wepTiers = new Category[]{
			Category.WEP_T1,
			Category.WEP_T2,
			Category.WEP_T3,
			Category.WEP_T4,
			Category.WEP_T5
	};

	public static MeleeWeapon randomWeapon(){
		return randomWeapon(Dungeon.depth / 10);
	}

	public static MeleeWeapon randomWeapon(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		Category c = wepTiers[Random.chances(floorSetTierProbs[floorSet])];
		MeleeWeapon w = (MeleeWeapon)Reflection.newInstance(c.classes[Random.chances(c.probs)]);
		w.random();
		return w;
	}

	public static final Category[] misTiers = new Category[]{
			Category.MIS_T1,
			Category.MIS_T2,
			Category.MIS_T3,
			Category.MIS_T4,
			Category.MIS_T5
	};

	public static MissileWeapon randomMissile(){
		return randomMissile(Dungeon.depth / 10);
	}

	public static MissileWeapon randomMissile(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		Category c = misTiers[Random.chances(floorSetTierProbs[floorSet])];
		MissileWeapon w = (MissileWeapon)Reflection.newInstance(c.classes[Random.chances(c.probs)]);
		w.random();
		return w;
	}

	//enforces uniqueness of artifacts throughout a run.
	public static Artifact randomArtifact() {

		Category cat = Category.ARTIFACT;
		int i = Random.chances( cat.probs );

		//if no artifacts are left, return null
		if (i == -1){
			return null;
		}

		cat.probs[i]--;
		return (Artifact) Reflection.newInstance((Class<? extends Artifact>) cat.classes[i]).random();

	}

	public static boolean removeArtifact(Class<?extends Artifact> artifact) {
		Category cat = Category.ARTIFACT;
		for (int i = 0; i < cat.classes.length; i++){
			if (cat.classes[i].equals(artifact) && cat.probs[i] > 0) {
				cat.probs[i] = 0;
				return true;
			}
		}
		return false;
	}

	private static final String FIRST_DECK = "first_deck";
	private static final String GENERAL_PROBS = "general_probs";
	private static final String CATEGORY_PROBS = "_probs";

	public static void storeInBundle(Bundle bundle) {
		bundle.put(FIRST_DECK, usingFirstDeck);

		Float[] genProbs = categoryProbs.values().toArray(new Float[0]);
		float[] storeProbs = new float[genProbs.length];
		for (int i = 0; i < storeProbs.length; i++){
			storeProbs[i] = genProbs[i];
		}
		bundle.put( GENERAL_PROBS, storeProbs);

		for (Category cat : Category.values()){
			if (cat.defaultProbs == null) continue;
			boolean needsStore = false;
			for (int i = 0; i < cat.probs.length; i++){
				if (cat.probs[i] != cat.defaultProbs[i]){
					needsStore = true;
					break;
				}
			}

			if (needsStore){
				bundle.put(cat.name().toLowerCase() + CATEGORY_PROBS, cat.probs);
			}
		}
	}

	public static void restoreFromBundle(Bundle bundle) {
		fullReset();

		usingFirstDeck = bundle.getBoolean(FIRST_DECK);

		if (bundle.contains(GENERAL_PROBS)){
			float[] probs = bundle.getFloatArray(GENERAL_PROBS);
			for (int i = 0; i < probs.length; i++){
				categoryProbs.put(Category.values()[i], probs[i]);
			}
		}

		for (Category cat : Category.values()){
			if (bundle.contains(cat.name().toLowerCase() + CATEGORY_PROBS)){
				float[] probs = bundle.getFloatArray(cat.name().toLowerCase() + CATEGORY_PROBS);
				if (cat.defaultProbs != null && probs.length == cat.defaultProbs.length){
					cat.probs = probs;
				}
			}
		}

	}
}