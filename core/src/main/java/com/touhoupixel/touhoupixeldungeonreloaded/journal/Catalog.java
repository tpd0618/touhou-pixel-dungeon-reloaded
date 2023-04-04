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
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.DoremySheep;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.KeikiCreation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.LifeCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.NazrinAlchemyMoney;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.NazrinGoldMoney;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.ShionUchiwa;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.BlankCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.PhoenixTail;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.TeacupMarisa;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.TeacupReimu;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.IceFairy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.LunaCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.ShanghaiDoll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.StarCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.SunnyCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.AunnHounds;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.HijiriSutra;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.DragonPassage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.EikiMoney;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.EirinElixir;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.FlandreDestruction;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.GaleGeta;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.MiniHakkero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.MiniHakkeroMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.MinorikoCrop;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.RingoBrandDango;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.AncientMagatama;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.AnnoyingUfo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.LarvaScale;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.NemunoKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.NitoriDilemma;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.OkinaBackdoor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.SeiranBleedingHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.SekibankiHead;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.ShouPagoda;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.YingYangOrb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.YingYangOrbNeedle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.ItemSeason;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.KomachiDetour;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MaidKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MaidKnifeRicochet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MegumuBarleyRice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MikoAuthority;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.SmeltScale;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.SpiritBottle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.TooHonestSignpost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.UndergroundSun;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.VampireFang;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.DanmakuGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.DragonPipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.KaguyaStash;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.KanakoOffering;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.MiserAdvice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.PebbleHat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.SanaeAmulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.SanaeAmuletAlt;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.SuwakoFrog;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.TewiFoot;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.YachieThreat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.ClownpieceMoon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.HalfGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.HalfGhostSpare;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.IrresistibleFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.LifeBurningTorch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.MiracleMallet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.MomoyoCentipede;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.PristineConfidence;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.ScreenBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.SuikaGourd;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.TenshiKeystone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.KeikiArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.MaiArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.MorfonicaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.NarumiArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.NitoriArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.OkinaArmor;
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
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.PoppinPartyArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.RumiaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.SanaeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.SatonoArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.SharkArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ToyohimeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.YorihimeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.YuyukoArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMagic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDoublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfFrost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHaste;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLiquidFlame;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfParalyticGas;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfPurity;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDango;
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
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfFixer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfHeavenDuel;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfIdentify;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfLullaby;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMirrorImage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRecharging;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfCurseRemoval;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRetribution;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTerror;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTransmutation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfAntiDoor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfBlastWave;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfCorrosion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfCorruption;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfDisintegration;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfFireblast;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfFrost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfHealWounds;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfLightning;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfLivingEarth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMindburst;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfPrismaticLight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfRegrowth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfSetsunatrip;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.AkyuuBrush;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.AlchemySword;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ArisaKeyboard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ChimataCloak;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.EveKeytar;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.FullMoonScythe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.Grayswandir;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.HecatiaStar;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.HinaRibbon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.HisoutenMankind;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.HorouBook;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.HoshigumaHorn;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.JeweledBranch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.JoonFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KanonDrumstick;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KokoroFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KyoukoBroom;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MurasaDipper;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.NazrinRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.PortablePhoenixTail;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.RanTail;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.RandomStar;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.RingoDango;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SagumeWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SevenStarSword;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ShionFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SilkyHair;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SmallSeiranHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.UtsuhoRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.WatermelonSword;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YachieHorn;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.Roukanken;
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
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YukinaMic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YuukaUmbrella;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YuyukoFoldingFan;
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
		//tier 1
		WEAPONS.seen.put( ReimuExorcismRod.class,           true);
		WEAPONS.seen.put( MarisaStaff.class,                true);
		WEAPONS.seen.put( KoishiDagger.class,                     true);
		WEAPONS.seen.put( SanaeExorcismRod.class,                     true);
		WEAPONS.seen.put( SmallSeiranHammer.class,                     true);
		WEAPONS.seen.put( HisoutenMankind.class,                     true);
		//tier 2
		WEAPONS.seen.put( ShionFan.class,                 true);
		WEAPONS.seen.put( JoonFan.class,                    true);

		WEAPONS.seen.put( TenguShortsword.class,                 true);
		WEAPONS.seen.put( KogasaUmbrella.class,                    true);
		WEAPONS.seen.put( Radio.class,                      true);
		WEAPONS.seen.put( MystiaWing.class,               true);
		WEAPONS.seen.put( KoakumaWing.class,                       true);

		WEAPONS.seen.put( RingoDango.class,                       true);
		WEAPONS.seen.put( SilkyHair.class,                       true);
		WEAPONS.seen.put( KyoukoBroom.class,                       true);
		WEAPONS.seen.put( NazrinRod.class,                       true);
		WEAPONS.seen.put( KokoroFan.class,                       true);
		//tier 3
		WEAPONS.seen.put( AkyuuBrush.class,                      true);
		WEAPONS.seen.put( HecatiaStar.class,                       true);

		WEAPONS.seen.put( SmallYingYangOrb.class,                      true);
		WEAPONS.seen.put( EikiRod.class,                       true);
		WEAPONS.seen.put( PaleSword.class,                   true);
		WEAPONS.seen.put( YoumuHalfPhantom.class,                true);
		WEAPONS.seen.put( SkySword.class,                        true);

		WEAPONS.seen.put( ShinmyomaruNeedle.class,                       true);
		WEAPONS.seen.put( AlchemySword.class,                   true);
		WEAPONS.seen.put( WatermelonSword.class,                   true);
		WEAPONS.seen.put( JeweledBranch.class,                   true);
		WEAPONS.seen.put( HinaRibbon.class,                     true);
		//tier 4
		WEAPONS.seen.put( MediumYingYangOrb.class,                  true);
		WEAPONS.seen.put( UtsuhoRod.class,                  true);
		WEAPONS.seen.put( CirnoWing.class,                      true);
		WEAPONS.seen.put( RemiliaWing.class,                 true);
		WEAPONS.seen.put( RanTail.class,             true);

		WEAPONS.seen.put( YukariUmbrella.class,                   true);
		WEAPONS.seen.put( YuukaUmbrella.class,                   true);
		WEAPONS.seen.put( MurasaDipper.class,                   true);
		WEAPONS.seen.put( YachieHorn.class,                   true);
		WEAPONS.seen.put( YuyukoFoldingFan.class,                   true);
		//tier 5
		WEAPONS.seen.put( BigYingYangOrb.class,                 true);
		WEAPONS.seen.put( GoldenExorcismRod.class,                  true);
		WEAPONS.seen.put( TenguSmartphone.class,                     true);
		WEAPONS.seen.put( SeiranHammer.class,                   true);
		WEAPONS.seen.put( JunkoSword.class,                true);

		WEAPONS.seen.put( AyaFan.class,                   true);
		WEAPONS.seen.put( Roukanken.class,                  true);
		WEAPONS.seen.put( SevenStarSword.class,              true);
		WEAPONS.seen.put( PortablePhoenixTail.class,         true);
		WEAPONS.seen.put( ChimataCloak.class,                true);

		WEAPONS.seen.put( Grayswandir.class,                 true);
		WEAPONS.seen.put( FullMoonScythe.class,              true);
		WEAPONS.seen.put( SagumeWing.class,                  true);
		WEAPONS.seen.put( HoshigumaHorn.class,               true);
		WEAPONS.seen.put( HorouBook.class,                   true);

		WEAPONS.seen.put( RandomStar.class,                  true);
		WEAPONS.seen.put( ArisaKeyboard.class,               true);
		WEAPONS.seen.put( EveKeytar.class,                   true);
		WEAPONS.seen.put( YukinaMic.class,                   true);
		WEAPONS.seen.put( KanonDrumstick.class,              true);

		//armor
		ARMORS.seen.put( ReimuArmor.class,                   true);
		//armor each properties
		ARMORS.seen.put( MarisaArmor.class,                  true);
		ARMORS.seen.put( SakuyaArmor.class,                  true);
		ARMORS.seen.put( SanaeArmor.class,                   true);
		ARMORS.seen.put( AyaArmor.class,                     true);

		ARMORS.seen.put( PC98ReimuArmor.class,               true);
		ARMORS.seen.put( NarumiArmor.class,                  true);
		ARMORS.seen.put( YorihimeArmor.class,                true);
		ARMORS.seen.put( ToyohimeArmor.class,                true);

		ARMORS.seen.put( PC98MarisaArmor.class,              true);
		ARMORS.seen.put( RumiaArmor.class,                   true);
		ARMORS.seen.put( HanasakigawaArmor.class,            true);
		ARMORS.seen.put( YuyukoArmor.class,                  true);

		ARMORS.seen.put( PoppinPartyArmor.class,             true);
		ARMORS.seen.put( MaxwellArmor.class,                 true);
		ARMORS.seen.put( GoldenDragonArmor.class,            true);
		ARMORS.seen.put( OkinaArmor.class,                   true);

		ARMORS.seen.put( NitoriArmor.class,                  true);
		ARMORS.seen.put( SharkArmor.class,                   true);
		ARMORS.seen.put( MorfonicaArmor.class,               true);
		ARMORS.seen.put( KeikiArmor.class,                   true);
		//non-res. armor
		ARMORS.seen.put( HecatiaArmor.class,                 true);
		ARMORS.seen.put( SatonoArmor.class,                  true);
		ARMORS.seen.put( MaiArmor.class,                     true);

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
		WANDS.seen.put( WandOfAntiDoor.class,               true);
		WANDS.seen.put( WandOfSetsunatrip.class,            true);
		WANDS.seen.put( WandOfHealWounds.class,             true);
		WANDS.seen.put( WandOfMindburst.class,              true);

		CARDS.seen.put( DoremySheep.class,           true);
		CARDS.seen.put( KeikiCreation.class,           true);
		CARDS.seen.put( LifeCard.class,           true);
		CARDS.seen.put( NazrinAlchemyMoney.class,           true);
		CARDS.seen.put( NazrinGoldMoney.class,           true);
		CARDS.seen.put( ShionUchiwa.class,           true);

		CARDS.seen.put( BlankCard.class,           true);
		CARDS.seen.put( PhoenixTail.class,           true);
		CARDS.seen.put( TeacupReimu.class,           true);
		CARDS.seen.put( TeacupMarisa.class,           true);

		CARDS.seen.put( IceFairy.class,           true);
		CARDS.seen.put( LunaCard.class,           true);
		CARDS.seen.put( ShanghaiDoll.class,           true);
		CARDS.seen.put( StarCard.class,           true);
		CARDS.seen.put( SunnyCard.class,           true);

		CARDS.seen.put( AunnHounds.class,           true);
		CARDS.seen.put( HijiriSutra.class,           true);
		CARDS.seen.put( DragonPassage.class,           true);
		CARDS.seen.put( EikiMoney.class,           true);
		CARDS.seen.put( EirinElixir.class,           true);
		CARDS.seen.put( FlandreDestruction.class,           true);
		CARDS.seen.put( GaleGeta.class,           true);
		CARDS.seen.put( MiniHakkero.class,           true);
		CARDS.seen.put( MiniHakkeroMissile.class,           true);
		CARDS.seen.put( MinorikoCrop.class,           true);
		CARDS.seen.put( RingoBrandDango.class,           true);

		CARDS.seen.put( AncientMagatama.class,           true);
		CARDS.seen.put( AnnoyingUfo.class,           true);
		CARDS.seen.put( LarvaScale.class,           true);
		CARDS.seen.put( NemunoKnife.class,           true);
		CARDS.seen.put( NitoriDilemma.class,           true);
		CARDS.seen.put( OkinaBackdoor.class,           true);
		CARDS.seen.put( SeiranBleedingHammer.class,           true);
		CARDS.seen.put( SekibankiHead.class,           true);
		CARDS.seen.put( ShouPagoda.class,           true);
		CARDS.seen.put( YingYangOrb.class,           true);
		CARDS.seen.put( YingYangOrbNeedle.class,           true);

		CARDS.seen.put( ItemSeason.class,           true);
		CARDS.seen.put( KomachiDetour.class,           true);
		CARDS.seen.put( MaidKnife.class,           true);
		CARDS.seen.put( MaidKnifeRicochet.class,           true);
		CARDS.seen.put( MegumuBarleyRice.class,           true);
		CARDS.seen.put( MikoAuthority.class,           true);
		CARDS.seen.put( SmeltScale.class,           true);
		CARDS.seen.put( SpiritBottle.class,           true);
		CARDS.seen.put( TooHonestSignpost.class,           true);
		CARDS.seen.put( UndergroundSun.class,           true);
		CARDS.seen.put( VampireFang.class,           true);

		CARDS.seen.put( DanmakuGhost.class,           true);
		CARDS.seen.put( DragonPipe.class,           true);
		CARDS.seen.put( KaguyaStash.class,           true);
		CARDS.seen.put( KanakoOffering.class,           true);
		CARDS.seen.put( MiserAdvice.class,           true);
		CARDS.seen.put( PebbleHat.class,           true);
		CARDS.seen.put( SanaeAmulet.class,           true);
		CARDS.seen.put( SanaeAmuletAlt.class,           true);
		CARDS.seen.put( SuwakoFrog.class,           true);
		CARDS.seen.put( TewiFoot.class,           true);
		CARDS.seen.put( YachieThreat.class,           true);

		CARDS.seen.put( ClownpieceMoon.class,           true);
		CARDS.seen.put( HalfGhost.class,           true);
		CARDS.seen.put( HalfGhostSpare.class,           true);
		CARDS.seen.put( IrresistibleFan.class,           true);
		CARDS.seen.put( LifeBurningTorch.class,           true);
		CARDS.seen.put( MiracleMallet.class,           true);
		CARDS.seen.put( MomoyoCentipede.class,           true);
		CARDS.seen.put( PristineConfidence.class,           true);
		CARDS.seen.put( ScreenBorder.class,           true);
		CARDS.seen.put( SuikaGourd.class,           true);
		CARDS.seen.put( TenshiKeystone.class,           true);

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
		POTIONS.seen.put( PotionOfDango.class,         true);
		POTIONS.seen.put( PotionOfMight.class,              true);
		POTIONS.seen.put( PotionOfDoublespeed.class,        true);
		POTIONS.seen.put( PotionOfLightHealing.class,       true);
		POTIONS.seen.put( PotionOfYingYang.class,       true);
		POTIONS.seen.put( PotionOfDanmaku.class,        true);
		POTIONS.seen.put( PotionOfMagic.class,              true);

		SCROLLS.seen.put( ScrollOfIdentify.class,           true);
		SCROLLS.seen.put( ScrollOfFixer.class,              true);
		SCROLLS.seen.put( ScrollOfCurseRemoval.class,        true);
		SCROLLS.seen.put( ScrollOfMagicMapping.class,       true);
		SCROLLS.seen.put( ScrollOfTeleportation.class,      true);
		SCROLLS.seen.put( ScrollOfRecharging.class,         true);
		SCROLLS.seen.put( ScrollOfMirrorImage.class,        true);
		SCROLLS.seen.put( ScrollOfTerror.class,             true);
		SCROLLS.seen.put( ScrollOfLullaby.class,            true);
		SCROLLS.seen.put( ScrollOfRage.class,               true);
		SCROLLS.seen.put( ScrollOfRetribution.class,        true);
		SCROLLS.seen.put( ScrollOfHeavenDuel.class,         true);
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