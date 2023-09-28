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

package com.touhoupixel.touhoupixeldungeongaiden.items;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.FutabaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.HollowArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.IroncladArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.JokerArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.KeikiArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.KogasaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MaiArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MorfonicaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MorganaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MurasaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.NarukamiYuArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.NarumiArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.NitoriArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.OkinaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.GoldenDragonArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.HanasakigawaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.HecatiaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MarisaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SakuyaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MaxwellArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.PC98MarisaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.PC98ReimuArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.AyaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.PoppinPartyArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.RumiaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SanaeArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SatonoArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SharkArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.ToyohimeArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.YorihimeArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.YuyukoArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.AlchemistsToolkit;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.CapeOfThorns;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.ChaliceOfBlood;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.CloakOfShadows;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.EtherealChains;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.HornOfPlenty;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.KaguyaHDChest;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.MasterThievesArmband;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.SandalsOfNature;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TalismanOfForesight;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.UnstableSpellbook;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Food;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.MysteryMeat;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Pasty;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.CamouflageHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.CleansingHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.DragonHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.HealingHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.LevitationHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.RejuvenationHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.PurityHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.ReachHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.HeavenHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.PeaceHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.ChargeHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.SwiftnessHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.SpearheadHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfMagic;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfDoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfFrost;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHaste;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfLiquidFlame;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfParalyticGas;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfPurity;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfToxicGas;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfYingYang;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfAccuracy;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfElements;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfEnergy;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfEvasion;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfForce;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfFuror;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfHaste;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfMight;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfSharpshooting;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfTenacity;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfWealth;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfAnchor;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfEarth;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfFate;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfFixer;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfIdentify;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfLullaby;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfMirrorImage;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfNamelessStory;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfRage;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfRecharging;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfExorcism;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfRetribution;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfTerror;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfTransmutation;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfFear;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfAggression;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfAugmentation;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfMadness;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfBlink;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfClairvoyance;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfDeepSleep;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfDisarming;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfEnchantment;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfFlock;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfIntuition;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfShock;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.BindingTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.CirnoTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.KnockbackTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.SevenDaysTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.DebilitationTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.EnragingTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.TransientTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.FlandreTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.InaccurateTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.KameTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.DisorientationTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.BackdoorTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.NightingaleTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.SwapTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.Talisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.CutterTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.vials.AnimalVial;
import com.touhoupixel.touhoupixeldungeongaiden.items.vials.GodVial;
import com.touhoupixel.touhoupixeldungeongaiden.items.vials.HumanVial;
import com.touhoupixel.touhoupixeldungeongaiden.items.vials.Vial;
import com.touhoupixel.touhoupixeldungeongaiden.items.vials.WarpVial;
import com.touhoupixel.touhoupixeldungeongaiden.items.vials.YokaiVial;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfAntiDoor;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfBlastWave;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfCorrosion;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfCorruption;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfDisintegration;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfFireblast;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfFrost;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfHealWounds;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfLightning;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfLivingEarth;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfMindburst;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfPrismaticLight;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfRegrowth;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfSetsunatrip;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.AkyuuBrush;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.AlchemyHat;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.AlchemySword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ArisaKeyboard;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.BlackFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ChimataCloak;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.DenjiChainsaw;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.EveKeytar;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.FullMoonScythe;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.Grayswandir;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.GreenBamboo;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.HecatiaStar;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.HinaRibbon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.HorouBook;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.HoshigumaHorn;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.IdolStick;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.JeweledBranch;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.JoonFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KanonDrumstick;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KokoroFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KyoukoBroom;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MintChocoSword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.QuintessentialFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SuperMiracleMallet;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MurasaDipper;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.NazrinRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.PortablePhoenixTail;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RanTail;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RandomStar;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RingoDango;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SagumeWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SevenStarSword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ShionFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SilkyHair;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ToramaruSpear;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.TurnaboutCloak;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.UtsuhoRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.VentoraStick;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.WatermelonSword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YachieHorn;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YukariUmbrella;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KoishiDagger;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KoakumaWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.CirnoWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.AyaFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.TenguSmartphone;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SeiranHammer;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.JunkoSword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.BigYingYangOrb;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KogasaUmbrella;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MediumYingYangOrb;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.EikiRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MystiaWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YoumuHalfPhantom;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RemiliaWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SkySword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.PaleSword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.TenguShortsword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.Radio;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SmallYingYangOrb;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.GoldenExorcismRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ShinmyomaruNeedle;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ReimuExorcismRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YukinaMic;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YuukaUmbrella;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YuyukoFoldingFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.AyaDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.KunaiDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MedicineDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MerlinDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ReisenDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ScaleDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.EikiDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.StarDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.BulletDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ShardDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.FlameDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.LunaticDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.TewiDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.RiceDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ReimuTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.InvertDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.CirnoDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.YoumuDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.YuukaDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Blindweed;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Dreamfoil;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Fadeleaf;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Firebloom;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Icecap;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Plant;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Rotberry;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sorrowmoss;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Starflower;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Stormvine;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sungrass;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 3, 2, MeleeWeapon.class),
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

		MISSILE ( 2, 2, MissileWeapon.class ),
		MIS_T1  ( 0, 0, MissileWeapon.class ),
		MIS_T2  ( 0, 0, MissileWeapon.class ),
		MIS_T3  ( 0, 0, MissileWeapon.class ),
		MIS_T4  ( 0, 0, MissileWeapon.class ),
		MIS_T5  ( 0, 0, MissileWeapon.class ),

		WAND	( 1, 1, Wand.class ),
		RING	( 1, 0, Ring.class ),
		ARTIFACT( 0, 1, Artifact.class),

		FOOD	( 0, 0, Food.class ),
		HERB	( 4, 4, Herb.class ),

		POTION	( 6, 6, Potion.class ),
		SEED	( 1, 1, Plant.Seed.class ),

		SCROLL	( 6, 6, Scroll.class ),
		STONE   ( 1, 1, Runestone.class),
		TALISMAN( 3, 3, Talisman.class),
		VIAL    ( 1, 1, Vial.class),

		GOLD	( 5, 4, Gold.class );

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
					PotionOfMight.class,
					PotionOfDoubleSpeed.class,
					PotionOfLightHealing.class,
					PotionOfYingYang.class,
					PotionOfMagic.class};
			POTION.defaultProbs = new float[]{ 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3};
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
			SEED.defaultProbs = new float[]{ 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2 };
			SEED.probs = SEED.defaultProbs.clone();

			SCROLL.classes = new Class<?>[]{
					ScrollOfIdentify.class,
					ScrollOfAnchor.class,
					ScrollOfFate.class,
					ScrollOfEarth.class,
					ScrollOfFixer.class,
					ScrollOfNamelessStory.class,
					ScrollOfExorcism.class,
					ScrollOfMirrorImage.class,
					ScrollOfRecharging.class,
					ScrollOfLullaby.class,
					ScrollOfRage.class,
					ScrollOfRetribution.class,
					ScrollOfTerror.class,
					ScrollOfTransmutation.class};
			SCROLL.defaultProbs = new float[]{ 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2};
			SCROLL.probs = SCROLL.defaultProbs.clone();

			STONE.classes = new Class<?>[]{
					StoneOfEnchantment.class,
					StoneOfIntuition.class,
					StoneOfDisarming.class,
					StoneOfFlock.class,
					StoneOfShock.class,
					StoneOfBlink.class,
					StoneOfDeepSleep.class,
					StoneOfClairvoyance.class,
					StoneOfAggression.class,
					StoneOfMadness.class,
					StoneOfFear.class,
					StoneOfAugmentation.class  //1 is sold in each shop
			};
			STONE.defaultProbs = new float[]{ 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0 };
			STONE.probs = STONE.defaultProbs.clone();

			TALISMAN.classes = new Class<?>[]{
					BindingTalisman.class,
					KnockbackTalisman.class,
					SevenDaysTalisman.class,
					EnragingTalisman.class,
					TransientTalisman.class,
					InaccurateTalisman.class,
					KameTalisman.class,
					SwapTalisman.class,
					DebilitationTalisman.class,
					DisorientationTalisman.class,
					FlandreTalisman.class,
					NightingaleTalisman.class,
					BackdoorTalisman.class,
					CutterTalisman.class,
					CirnoTalisman.class
			};
			TALISMAN.defaultProbs = new float[]{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			TALISMAN.probs = TALISMAN.defaultProbs.clone();

			VIAL.classes = new Class<?>[]{
					YokaiVial.class,
					GodVial.class,
					HumanVial.class,
					AnimalVial.class,
					WarpVial.class
			};
			VIAL.defaultProbs = new float[]{ 5, 5, 5, 5, 5 };
			VIAL.probs = VIAL.defaultProbs.clone();

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
					WandOfAntiDoor.class,
					WandOfSetsunatrip.class,
					WandOfHealWounds.class,
					WandOfRegrowth.class,
					WandOfMindburst.class
			};
			WAND.probs = new float[]{ 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 };

			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};

			WEP_T1.classes = new Class<?>[]{
					ReimuExorcismRod.class,
					//SanaeExorcismRod.class,
					KoishiDagger.class,
					//SmallSeiranHammer.class,
					//HinaRibbon.class,
					//HisoutenMankind.class,
					MarisaStaff.class
			};
			WEP_T1.probs = new float[]{ 1, 1, 0 };

			WEP_T2.classes = new Class<?>[]{
					TenguShortsword.class,
					KogasaUmbrella.class,
					Radio.class,
					MystiaWing.class,
					KoakumaWing.class,
					RingoDango.class,
					SilkyHair.class,
					KyoukoBroom.class,
					NazrinRod.class,
					KokoroFan.class,
					ShionFan.class,
					JoonFan.class,
					AlchemyHat.class
			};
			WEP_T2.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4 };

			WEP_T3.classes = new Class<?>[]{
					SmallYingYangOrb.class,
					EikiRod.class,
					PaleSword.class,
					YoumuHalfPhantom.class,
					SkySword.class,
					ShinmyomaruNeedle.class,
					AlchemySword.class,
					WatermelonSword.class,
					JeweledBranch.class,
					HinaRibbon.class,
					AkyuuBrush.class,
					HecatiaStar.class,
					GreenBamboo.class,
					MintChocoSword.class
			};
			WEP_T3.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4 };

			WEP_T4.classes = new Class<?>[]{
					MediumYingYangOrb.class,
					UtsuhoRod.class,
					CirnoWing.class,
					RemiliaWing.class,
					RanTail.class,
					YukariUmbrella.class,
					YuukaUmbrella.class,
					MurasaDipper.class,
					YachieHorn.class,
					YuyukoFoldingFan.class,
					SuperMiracleMallet.class,
					BlackFan.class,
					TurnaboutCloak.class
			};
			WEP_T4.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

			WEP_T5.classes = new Class<?>[]{
					BigYingYangOrb.class,
					GoldenExorcismRod.class,
					TenguSmartphone.class,
					SeiranHammer.class,
					JunkoSword.class,
					AyaFan.class,
					SevenStarSword.class,
					PortablePhoenixTail.class,
					ChimataCloak.class,
					Grayswandir.class,
					FullMoonScythe.class,
					SagumeWing.class,
					HoshigumaHorn.class,
					HorouBook.class,
					ToramaruSpear.class,
					RandomStar.class,
					ArisaKeyboard.class,
					EveKeytar.class,
					YukinaMic.class,
					KanonDrumstick.class,
					IdolStick.class,
					VentoraStick.class,
					QuintessentialFan.class,
					DenjiChainsaw.class
			};
			WEP_T5.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

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
					PC98MarisaArmor.class,
					PoppinPartyArmor.class,
					NitoriArmor.class};
			ARMOR_T2.probs = new float[]{ 5, 5, 5, 5, 5 };

			ARMOR_T3.classes = new Class<?>[]{
					SakuyaArmor.class,
					NarumiArmor.class,
					RumiaArmor.class,
					MaxwellArmor.class,
					SharkArmor.class};
			ARMOR_T3.probs = new float[]{ 5, 5, 5, 5, 5 };

			ARMOR_T4.classes = new Class<?>[]{
					SanaeArmor.class,
					YorihimeArmor.class,
					HanasakigawaArmor.class,
					GoldenDragonArmor.class,
					MorfonicaArmor.class};
			ARMOR_T4.probs = new float[]{ 5, 5, 5, 5, 5 };

			ARMOR_T5.classes = new Class<?>[]{
					HecatiaArmor.class,
					SatonoArmor.class,
					MaiArmor.class,
					AyaArmor.class,
					ToyohimeArmor.class,
					YuyukoArmor.class,
					OkinaArmor.class,
					KeikiArmor.class,
					HollowArmor.class,
					IroncladArmor.class,
					KogasaArmor.class,
					MurasaArmor.class,
					MorganaArmor.class,
					FutabaArmor.class,
					NarukamiYuArmor.class,
					JokerArmor.class
			};
			ARMOR_T5.probs = new float[]{ 2, 4, 4, 5, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3 };

			//see Generator.randomMissile
			MISSILE.classes = new Class<?>[]{};
			MISSILE.probs = new float[]{};

			MIS_T1.classes = new Class<?>[]{
					ThrowingKnife.class
			};
			MIS_T1.probs = new float[]{ 5 };

			MIS_T2.classes = new Class<?>[]{
					ReimuTalisman.class,
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
					LunaticDanmaku.class,
					MedicineDanmaku.class,
					TewiDanmaku.class
			};
			MIS_T5.probs = new float[]{ 5, 5, 5, 5 };

			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };

			HERB.classes = new Class<?>[]{
					CamouflageHerb.class,
					CleansingHerb.class,
					DragonHerb.class,
					HealingHerb.class,
					LevitationHerb.class,
					RejuvenationHerb.class,
					ReachHerb.class,
					HeavenHerb.class,
					PeaceHerb.class,
					ChargeHerb.class,
					SwiftnessHerb.class,
					PurityHerb.class,
					SpearheadHerb.class };
			HERB.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

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
					EtherealChains.class,
					KaguyaHDChest.class
			};
			ARTIFACT.defaultProbs = new float[]{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
			ARTIFACT.probs = ARTIFACT.defaultProbs.clone();
		}
	}

	private static final float[][] floorSetTierProbs = new float[][] {
			{0, 55, 30, 15,  0},
			{0, 25, 50, 20,  5},
			{0,  0, 40, 50, 10},
			{0,  0, 40, 50, 10},
			{0,  0, 20, 40, 40},
			{0,  0, 20, 40, 40},
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
		return randomArmor(Dungeon.floor /10);
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
		return randomWeapon(Dungeon.floor /10);
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
		return randomMissile(Dungeon.floor / 10);
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
	public static void removeTimekeeperHourglass(){
		Category.ARTIFACT.probs[7] = 0;
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