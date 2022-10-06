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

package com.touhoupixel.touhoupixeldungeonreloaded.journal;

import com.touhoupixel.touhoupixeldungeonreloaded.Badges;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
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
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.EsteemedAuthority;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.GreatTenguRice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.HeavyBassDrum;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.ItemSeason;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.KeystoneEndurance;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.MiracleMallet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.MoonMadness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.Psychokinesis;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.ScreenBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.SpiritPowerBottle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.UndergroundSun;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.VampireFang;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.AncientMagatama;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.AnnoyingUfo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.BackDoor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.HalfHalfGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.IceFairy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.MaidKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.MaidKnifeRicochet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.MiniHakkero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.MiniHakkeroMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.SafeReturnAmulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.ShanghaiDoll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.ShedSnakeskinAmulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.YingYangOrb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.YingYangOrbNeedle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.BurstingRedFrog;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.DanmakuGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.DeathAvoidanceElixir;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.DragonPipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.GaleGeta;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.GluttonCentipede;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.IdolDefenseCorps;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.KaguyaSecretStash;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.KiketsuThreat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.LuckyCat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.MiserAdvice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.MoneyBest;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.OfferingMountain;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.PebbleHat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.PhoenixTail;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.PhysicalJizo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.RabbitFoot;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.SpellBeforeFall;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.SurvivalFittest;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.SutraPower;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.TanukiApprentice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.YamawaroTech;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.HastyDetourCrossing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.NimbleFabric;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.SmeltScales;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.TooHonestSignpost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.ExuberantPowerless;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.FreewheelingHead;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.LaidBackInvertebrate;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.LunaticSilence;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.OffensiveJeweledPagoda;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.QuietTwinkling;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.RabbitHasLanded;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.TeacupMarisa;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.TeacupReimu;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.YamanbaKitchenKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.BeautyDestruction;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.BumperCrop;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.DilemmaCapitalism;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.DragonPassage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.DrenchedGiantSnake;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.DrunkGourd;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.HundredthBlackMarket;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.IrresistibleFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.LifeBurningTorch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.NoProblemCreator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.PristineApproval;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.ScamRabbitFoot;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.SheepCount;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.SootCoveredUchiwa;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.StimulatingScales;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.UnleashGuardianHounds;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHina;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfReisen;
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
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfKomachi;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfRingoDango;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfStrength;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfToxicGas;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfYingYang;
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
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public enum Catalog {

	WEAPONS,
	ARMORS,
	WANDS,
	CARDS,
	RINGS,
	POTIONS,
	SCROLLS;

	private LinkedHashMap<Class<? extends Item>, Boolean> seen = new LinkedHashMap<>();

	public Collection<Class<? extends Item>> items(){
		return seen.keySet();
	}

	public boolean allSeen(){
		for (Class<?extends Item> item : items()){
			if (!seen.get(item)){
				return false;
			}
		}
		return true;
	}

	static {
		WEAPONS.seen.put( ReimuExorcismRod.class,           true);
		WEAPONS.seen.put( MarisaStaff.class,                true);
		WEAPONS.seen.put( KoishiDagger.class,                     true);
		WEAPONS.seen.put( SanaeExorcismRod.class,                     true);
		WEAPONS.seen.put( TenguShortsword.class,                 true);
		WEAPONS.seen.put( KogasaUmbrella.class,                    true);
		WEAPONS.seen.put( Radio.class,                      true);
		WEAPONS.seen.put( MystiaWing.class,               true);
		WEAPONS.seen.put( KoakumaWing.class,                       true);
		WEAPONS.seen.put( SmallYingYangOrb.class,                      true);
		WEAPONS.seen.put( EikiRod.class,                       true);
		WEAPONS.seen.put( PaleSword.class,                   true);
		WEAPONS.seen.put( YoumuHalfPhantom.class,                true);
		WEAPONS.seen.put( SkySword.class,                        true);
		WEAPONS.seen.put( ShinmyomaruNeedle.class,                       true);
		WEAPONS.seen.put( MediumYingYangOrb.class,                  true);
		WEAPONS.seen.put( UtsuhoRod.class,                  true);
		WEAPONS.seen.put( CirnoWing.class,                      true);
		WEAPONS.seen.put( RemiliaWing.class,                 true);
		WEAPONS.seen.put( RanTail.class,             true);
		WEAPONS.seen.put( YukariUmbrella.class,                   true);
		WEAPONS.seen.put( BigYingYangOrb.class,                 true);
		WEAPONS.seen.put( GoldenExorcismRod.class,                  true);
		WEAPONS.seen.put( TenguSmartphone.class,                     true);
		WEAPONS.seen.put( SeiranHammer.class,                   true);
		WEAPONS.seen.put( JunkoSword.class,                true);
		WEAPONS.seen.put( AyaFan.class,                   true);

		ARMORS.seen.put( ReimuArmor.class,                   true);
		ARMORS.seen.put( MarisaArmor.class,                 true);
		ARMORS.seen.put( SakuyaArmor.class,                    true);
		ARMORS.seen.put( SanaeArmor.class,                   true);
		ARMORS.seen.put( AyaArmor.class,                   true);
		ARMORS.seen.put( PC98ReimuArmor.class,               true);
		ARMORS.seen.put( PC98MarisaArmor.class,              true);
		ARMORS.seen.put( YorihimeArmor.class,                true);
		ARMORS.seen.put( ToyohimeArmor.class,                true);
		ARMORS.seen.put( RumiaArmor.class,                   true);
		ARMORS.seen.put( HanasakigawaArmor.class,            true);
		ARMORS.seen.put( YuyukoArmor.class,                  true);
		ARMORS.seen.put( HecatiaArmor.class,                 true);
		ARMORS.seen.put( KirakiraBandArmor.class,             true);
		ARMORS.seen.put( MaxwellArmor.class,                 true);
		ARMORS.seen.put( GoldenDragonArmor.class,            true);
		ARMORS.seen.put( NitoriArmor.class,             true);
		ARMORS.seen.put( SharkArmor.class,                 true);
		ARMORS.seen.put( MorfonicaArmor.class,            true);

		WANDS.seen.put( WandOfMagicMissile.class,           true);
		WANDS.seen.put( WandOfLightning.class,              true);
		WANDS.seen.put( WandOfDisintegration.class,         true);
		WANDS.seen.put( WandOfFireblast.class,              true);
		WANDS.seen.put( WandOfCorrosion.class,              true);
		WANDS.seen.put( WandOfBlastWave.class,              true);
		WANDS.seen.put( WandOfLivingEarth.class,            true);
		WANDS.seen.put( WandOfFrost.class,                  true);
		WANDS.seen.put( WandOfPrismaticLight.class,         true);
		WANDS.seen.put( WandOfWarding.class,                true);
		WANDS.seen.put( WandOfCorruption.class,             true);
		WANDS.seen.put( WandOfRegrowth.class,               true);

		CARDS.seen.put( EsteemedAuthority.class,           true);
		CARDS.seen.put( GreatTenguRice.class,              true);
		CARDS.seen.put( HeavyBassDrum.class,         true);
		CARDS.seen.put( ItemSeason.class,              true);
		CARDS.seen.put( KeystoneEndurance.class,              true);
		CARDS.seen.put( MiracleMallet.class,              true);
		CARDS.seen.put( MoonMadness.class,            true);
		CARDS.seen.put( Psychokinesis.class,                  true);
		CARDS.seen.put( ScreenBorder.class,         true);
		CARDS.seen.put( SpiritPowerBottle.class,                true);
		CARDS.seen.put( UndergroundSun.class,             true);
		CARDS.seen.put( VampireFang.class,               true);
		CARDS.seen.put( AncientMagatama.class,           true);
		CARDS.seen.put( AnnoyingUfo.class,              true);
		CARDS.seen.put( BackDoor.class,         true);
		CARDS.seen.put( HalfHalfGhost.class,              true);
		CARDS.seen.put( IceFairy.class,              true);
		CARDS.seen.put( MaidKnife.class,              true);
		CARDS.seen.put( MaidKnifeRicochet.class,            true);
		CARDS.seen.put( MiniHakkero.class,                  true);
		CARDS.seen.put( MiniHakkeroMissile.class,         true);
		CARDS.seen.put( SafeReturnAmulet.class,                true);
		CARDS.seen.put( ShanghaiDoll.class,             true);
		CARDS.seen.put( ShedSnakeskinAmulet.class,               true);
		CARDS.seen.put( YingYangOrb.class,             true);
		CARDS.seen.put( YingYangOrbNeedle.class,               true);
		CARDS.seen.put( BurstingRedFrog.class,             true);
		CARDS.seen.put( DanmakuGhost.class,               true);
		CARDS.seen.put( DeathAvoidanceElixir.class,           true);
		CARDS.seen.put( DragonPipe.class,              true);
		CARDS.seen.put( GaleGeta.class,         true);
		CARDS.seen.put( GluttonCentipede.class,              true);
		CARDS.seen.put( IdolDefenseCorps.class,              true);
		CARDS.seen.put( KaguyaSecretStash.class,              true);
		CARDS.seen.put( KiketsuThreat.class,            true);
		CARDS.seen.put( LuckyCat.class,                  true);
		CARDS.seen.put( MiserAdvice.class,         true);
		CARDS.seen.put( MoneyBest.class,                true);
		CARDS.seen.put( OfferingMountain.class,             true);
		CARDS.seen.put( PebbleHat.class,               true);
		CARDS.seen.put( PhoenixTail.class,             true);
		CARDS.seen.put( PhysicalJizo.class,               true);
		CARDS.seen.put( RabbitFoot.class,             true);
		CARDS.seen.put( SpellBeforeFall.class,               true);
		CARDS.seen.put( SurvivalFittest.class,           true);
		CARDS.seen.put( SutraPower.class,              true);
		CARDS.seen.put( TanukiApprentice.class,         true);
		CARDS.seen.put( YamawaroTech.class,              true);
		CARDS.seen.put( HastyDetourCrossing.class,              true);
		CARDS.seen.put( NimbleFabric.class,              true);
		CARDS.seen.put( SmeltScales.class,            true);
		CARDS.seen.put( TooHonestSignpost.class,                  true);
		CARDS.seen.put( ExuberantPowerless.class,         true);
		CARDS.seen.put( FreewheelingHead.class,                true);
		CARDS.seen.put( LaidBackInvertebrate.class,             true);
		CARDS.seen.put( LunaticSilence.class,               true);
		CARDS.seen.put( OffensiveJeweledPagoda.class,             true);
		CARDS.seen.put( QuietTwinkling.class,               true);
		CARDS.seen.put( RabbitHasLanded.class,               true);
		CARDS.seen.put( TeacupMarisa.class,               true);
		CARDS.seen.put( TeacupReimu.class,               true);
		CARDS.seen.put( YamanbaKitchenKnife.class,               true);
		CARDS.seen.put( BeautyDestruction.class,         true);
		CARDS.seen.put( BumperCrop.class,              true);
		CARDS.seen.put( DilemmaCapitalism.class,              true);
		CARDS.seen.put( DragonPassage.class,              true);
		CARDS.seen.put( DrenchedGiantSnake.class,            true);
		CARDS.seen.put( DrunkGourd.class,                  true);
		CARDS.seen.put( HundredthBlackMarket.class,         true);
		CARDS.seen.put( IrresistibleFan.class,                true);
		CARDS.seen.put( LifeBurningTorch.class,             true);
		CARDS.seen.put( NoProblemCreator.class,               true);
		CARDS.seen.put( PristineApproval.class,             true);
		CARDS.seen.put( ScamRabbitFoot.class,               true);
		CARDS.seen.put( SheepCount.class,               true);
		CARDS.seen.put( SootCoveredUchiwa.class,               true);
		CARDS.seen.put( StimulatingScales.class,               true);
		CARDS.seen.put( UnleashGuardianHounds.class,               true);

		RINGS.seen.put( RingOfAccuracy.class,         true);
		RINGS.seen.put( RingOfElements.class,         true);
		RINGS.seen.put( RingOfEnergy.class,         true);
		RINGS.seen.put( RingOfEvasion.class,         true);
		RINGS.seen.put( RingOfForce.class,         true);
		RINGS.seen.put( RingOfFuror.class,         true);
		RINGS.seen.put( RingOfHaste.class,         true);
		RINGS.seen.put( RingOfMight.class,         true);
		RINGS.seen.put( RingOfSharpshooting.class,         true);
		RINGS.seen.put( RingOfTenacity.class,         true);
		RINGS.seen.put( RingOfWealth.class,         true);

		POTIONS.seen.put( PotionOfHealing.class,            true);
		POTIONS.seen.put( PotionOfStrength.class,           true);
		POTIONS.seen.put( PotionOfLiquidFlame.class,        true);
		POTIONS.seen.put( PotionOfFrost.class,              true);
		POTIONS.seen.put( PotionOfToxicGas.class,           true);
		POTIONS.seen.put( PotionOfParalyticGas.class,       true);
		POTIONS.seen.put( PotionOfPurity.class,             true);
		POTIONS.seen.put( PotionOfLevitation.class,         true);
		POTIONS.seen.put( PotionOfMindVision.class,         true);
		POTIONS.seen.put( PotionOfInvisibility.class,       true);
		POTIONS.seen.put( PotionOfExperience.class,         true);
		POTIONS.seen.put( PotionOfHaste.class,              true);
		POTIONS.seen.put( PotionOfRingoDango.class,         true);
		POTIONS.seen.put( PotionOfMight.class,              true);
		POTIONS.seen.put( PotionOfDoublespeed.class,        true);
		POTIONS.seen.put( PotionOfLightHealing.class,       true);
		POTIONS.seen.put( PotionOfCirno.class,              true);
		POTIONS.seen.put( PotionOfHina.class,           true);
		POTIONS.seen.put( PotionOfReisen.class,        true);
		POTIONS.seen.put( PotionOfFlamePower.class,             true);
		POTIONS.seen.put( PotionOfFrostPower.class,         true);
		POTIONS.seen.put( PotionOfLightReverse.class,         true);
		POTIONS.seen.put( PotionOfKomachi.class,              true);
		POTIONS.seen.put( PotionOfYingYang.class,       true);

		SCROLLS.seen.put( ScrollOfIdentify.class,           true);
		SCROLLS.seen.put( ScrollOfUpgrade.class,            true);
		SCROLLS.seen.put( ScrollOfRemoveCurse.class,        true);
		SCROLLS.seen.put( ScrollOfMagicMapping.class,       true);
		SCROLLS.seen.put( ScrollOfTeleportation.class,      true);
		SCROLLS.seen.put( ScrollOfRecharging.class,         true);
		SCROLLS.seen.put( ScrollOfMirrorImage.class,        true);
		SCROLLS.seen.put( ScrollOfTerror.class,             true);
		SCROLLS.seen.put( ScrollOfLullaby.class,            true);
		SCROLLS.seen.put( ScrollOfRage.class,               true);
		SCROLLS.seen.put( ScrollOfRetribution.class,        true);
		SCROLLS.seen.put( ScrollOfTransmutation.class,      true);
	}

	public static boolean isSeen(Class<? extends Item> itemClass){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(itemClass)) {
				return cat.seen.get(itemClass);
			}
		}
		return false;
	}

	public static void setSeen(Class<? extends Item> itemClass){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(itemClass) && !cat.seen.get(itemClass)) {
				cat.seen.put(itemClass, true);
				Journal.saveNeeded = true;
			}
		}
	}

	private static final String CATALOG_ITEMS = "catalog_items";

	public static void store( Bundle bundle ){

		Badges.loadGlobal();

		ArrayList<Class> seen = new ArrayList<>();

		bundle.put( CATALOG_ITEMS, seen.toArray(new Class[0]) );

	}

	public static void restore( Bundle bundle ){

		Badges.loadGlobal();

		//general save/load
		if (bundle.contains(CATALOG_ITEMS)) {
			List<Class> seenClasses = new ArrayList<>();
			if (bundle.contains(CATALOG_ITEMS)) {
				seenClasses = Arrays.asList(bundle.getClassArray(CATALOG_ITEMS));
			}

			for (Catalog cat : values()) {
				for (Class<? extends Item> item : cat.items()) {
					if (seenClasses.contains(item)) {
						cat.seen.put(item, true);
					}
				}
			}
		}
	}

}