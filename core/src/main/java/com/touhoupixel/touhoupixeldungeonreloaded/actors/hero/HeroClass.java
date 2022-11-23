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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.hero;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.QuickSlot;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HighStress;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Amulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.BrokenSeal;
import com.touhoupixel.touhoupixeldungeonreloaded.items.DoremyDreamEater;
import com.touhoupixel.touhoupixeldungeonreloaded.items.HinaCursedRibbon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.LifeSpellcardChecker;
import com.touhoupixel.touhoupixeldungeonreloaded.items.NitoDismantleHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.SpellcardCaster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Torch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Waterskin;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.MorfonicaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.NitoriArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.CloakOfShadows;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.CardHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.PotionBandolier;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.ScrollHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.HakureiHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.VelvetPouch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.MoonMadness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.HealHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDoublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfFrost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHaste;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLignification;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLiquidFlame;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfParalyticGas;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfPurity;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfRingoDango;
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
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfFixer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfHeavenDuel;
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
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfMassConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfEnchantment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfMadness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.DebilitationTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.DecoyTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.SevenDaysTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.TransientTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfHealWounds;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Miracle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.HinaRibbon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KanonDrumstick;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KoishiDagger;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MystiaWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SanaeExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ReimuExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.YuyukoFoldingFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ReimuTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;

public enum HeroClass {

	PLAYERREIMU(),
	PLAYERMARISA(),
	PLAYERKOISHI(),
	PLAYERSANAE(),
	PLAYERRUMIA(),
	PLAYERKAGUYA(),
	PLAYERKEINE(),
	PLAYERSHOU(),
	PLAYERAYA(),
	PLAYEREIKI(),
	PLAYERCIRNO(),
	PLAYERKEIKI(),
	PLAYERHINA(),
	PLAYERDOREMY();

	public void initHero( Hero hero ) {

		hero.heroClass = this;

		Item i = new ReimuArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ReimuArmor)i;

		i = new Food();
		if (!Challenges.isItemBlocked(i)) i.collect();

		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		LifeSpellcardChecker lifespellcardchecker = new LifeSpellcardChecker();
		lifespellcardchecker.collect();

		NitoDismantleHammer nitofusion = new NitoDismantleHammer();
		nitofusion.collect();

		//test, too many will make the bug for the game

		//test, too many will make the bug for the game

		Torch torch = new Torch();
		torch.quantity(3).collect();

		HealHerb healherb = new HealHerb();
		healherb.collect();
		Dungeon.quickslot.setSlot(2, healherb);

		SpellcardCaster spellcardcaster = new SpellcardCaster();
		spellcardcaster.collect();
		Dungeon.quickslot.setSlot(3, spellcardcaster);

		new MagicalHolster().collect();
		new PotionBandolier().collect();
		new ScrollHolder().collect();
		new HakureiHolder().collect();
		new VelvetPouch().collect();
		new CardHolder().collect();

		switch (this) {
			case PLAYERREIMU:
				initReimu( hero );
				break;

			case PLAYERMARISA:
				initMarisa( hero );
				break;

			case PLAYERKOISHI:
				initKoishi( hero );
				break;

			case PLAYERSANAE:
				initSanae( hero );
				break;

			case PLAYERRUMIA:
				initRumia( hero );
				break;

			case PLAYERKAGUYA:
				initKaguya( hero );
				break;

			case PLAYERKEINE:
				initKeine( hero );
				break;

			case PLAYERSHOU:
				initShou( hero );
				break;

			case PLAYERAYA:
				initAya( hero );
				break;

			case PLAYEREIKI:
				initEiki( hero );
				break;

			case PLAYERCIRNO:
				initCirno( hero );
				break;

			case PLAYERKEIKI:
				initKeiki( hero );
				break;

			case PLAYERHINA:
				initHina( hero );
				break;

			case PLAYERDOREMY:
				initDoremy( hero );
				break;
		}

		for (int s = 0; s < QuickSlot.SIZE; s++){
			if (Dungeon.quickslot.getItem(s) == null){
				Dungeon.quickslot.setSlot(s, waterskin);
				break;
			}
		}

	}

	private static void initReimu( Hero hero ) {
		(hero.belongings.weapon = new ReimuExorcismRod()).identify();
		ReimuTailsman stones = new ReimuTailsman();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}
	}

	private static void initMarisa( Hero hero ) {
		MarisaStaff staff;

		staff = new MarisaStaff(new WandOfMagicMissile());

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);
	}

	private static void initKoishi( Hero hero ) {
		(hero.belongings.weapon = new KoishiDagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.artifact = cloak).identify();
		hero.belongings.artifact.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);
	}

	private static void initSanae( Hero hero ) {
		(hero.belongings.weapon = new SanaeExorcismRod()).identify();
		Miracle bow = new Miracle();
		bow.identify().collect();

		Dungeon.quickslot.setSlot(0, bow);
	}

	private static void initRumia( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();
	}

	private static void initKaguya( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();

		WandOfHealWounds healwounds = new WandOfHealWounds();
		healwounds.identify().collect();

		Dungeon.quickslot.setSlot(0, healwounds);
	}

	private static void initKeine( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();

		PotionOfDanmaku p1 = new PotionOfDanmaku();
		p1.identify();

		PotionOfDoublespeed p2 = new PotionOfDoublespeed();
		p2.identify();

		PotionOfExperience p3 = new PotionOfExperience();
		p3.identify();

		PotionOfFrost p4 = new PotionOfFrost();
		p4.identify();

		PotionOfHaste p5 = new PotionOfHaste();
		p5.identify();

		PotionOfHealing p6 = new PotionOfHealing();
		p6.identify();

		PotionOfInvisibility p7 = new PotionOfInvisibility();
		p7.identify();

		PotionOfLevitation p8 = new PotionOfLevitation();
		p8.identify();

		PotionOfLightHealing p9 = new PotionOfLightHealing();
		p9.identify();

		PotionOfLignification p10 = new PotionOfLignification();
		p10.identify();

		PotionOfLiquidFlame p11 = new PotionOfLiquidFlame();
		p11.identify();

		PotionOfMight p12 = new PotionOfMight();
		p12.identify();

		PotionOfMindVision p13 = new PotionOfMindVision();
		p13.identify();

		PotionOfParalyticGas p14 = new PotionOfParalyticGas();
		p14.identify();

		PotionOfPurity p15 = new PotionOfPurity();
		p15.identify();

		PotionOfRingoDango p16 = new PotionOfRingoDango();
		p16.identify();

		PotionOfToxicGas p17 = new PotionOfToxicGas();
		p17.identify();

		PotionOfYingYang p18 = new PotionOfYingYang();
		p18.identify();

		ScrollOfFixer s1 = new ScrollOfFixer();
		s1.identify();

		ScrollOfHeavenDuel s2 = new ScrollOfHeavenDuel();
		s2.identify();

		ScrollOfIdentify s3 = new ScrollOfIdentify();
		s3.identify();

		ScrollOfLullaby s4 = new ScrollOfLullaby();
		s4.identify();

		ScrollOfMagicMapping s5 = new ScrollOfMagicMapping();
		s5.identify();

		ScrollOfMirrorImage s6 = new ScrollOfMirrorImage();
		s6.identify();

		ScrollOfRage s7 = new ScrollOfRage();
		s7.identify();

		ScrollOfRecharging s8 = new ScrollOfRecharging();
		s8.identify();

		ScrollOfRemoveCurse s9 = new ScrollOfRemoveCurse();
		s9.identify();

		ScrollOfRetribution s10 = new ScrollOfRetribution();
		s10.identify();

		ScrollOfTeleportation s11 = new ScrollOfTeleportation();
		s11.identify();

		ScrollOfTerror s12 = new ScrollOfTerror();
		s12.identify();

		ScrollOfTransmutation s13 = new ScrollOfTransmutation();
		s13.identify();

		RingOfAccuracy r1 = new RingOfAccuracy();
		r1.identify();

		RingOfElements r2 = new RingOfElements();
		r2.identify();

		RingOfEnergy r3 = new RingOfEnergy();
		r3.identify();

		RingOfEvasion r4 = new RingOfEvasion();
		r4.identify();

		RingOfForce r5 = new RingOfForce();
		r5.identify();

		RingOfFuror r6 = new RingOfFuror();
		r6.identify();

		RingOfHaste r7 = new RingOfHaste();
		r7.identify();

		RingOfMight r8 = new RingOfMight();
		r8.identify();

		RingOfSharpshooting r9 = new RingOfSharpshooting();
		r9.identify();

		RingOfTenacity r10 = new RingOfTenacity();
		r10.identify();

		RingOfWealth r11 = new RingOfWealth();
		r11.identify();
	}

	private static void initShou( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();
	}

	private static void initAya( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();
	}

	private static void initEiki( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();
	}

	private static void initCirno( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();
	}

	private static void initKeiki( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();
	}

	private static void initHina( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();

		HinaCursedRibbon hcribbon = new HinaCursedRibbon();
		hcribbon.collect();

		Dungeon.quickslot.setSlot(0, hcribbon);
	}

	private static void initDoremy( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();

		DoremyDreamEater ddeater = new DoremyDreamEater();
		ddeater.collect();

		Dungeon.quickslot.setSlot(0, ddeater);
	}

	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc(){
		return Messages.get(HeroClass.class, name()+"_desc");
	}

	public String spritesheet() {
		switch (this) {
			case PLAYERREIMU: default:
				return Assets.Sprites.PLAYERREIMU;
			case PLAYERMARISA:
				return Assets.Sprites.PLAYERMARISA;
			case PLAYERKOISHI:
				return Assets.Sprites.PLAYERKOISHI;
			case PLAYERSANAE:
				return Assets.Sprites.PLAYERSANAE;
			case PLAYERRUMIA:
				return Assets.Sprites.PLAYERRUMIA;
			case PLAYERKAGUYA:
				return Assets.Sprites.PLAYERKAGUYA;
			case PLAYERKEINE:
				return Assets.Sprites.PLAYERKEINE;
			case PLAYERSHOU:
				return Assets.Sprites.PLAYERSHOU;
			case PLAYERAYA:
				return Assets.Sprites.PLAYERAYA;
			case PLAYEREIKI:
				return Assets.Sprites.PLAYEREIKI;
			case PLAYERCIRNO:
				return Assets.Sprites.PLAYERCIRNO;
			case PLAYERKEIKI:
				return Assets.Sprites.PLAYERKEIKI;
			case PLAYERHINA:
				return Assets.Sprites.PLAYERHINA;
			case PLAYERDOREMY:
				return Assets.Sprites.PLAYERDOREMY;
		}
	}

	public String splashArt(){
		switch (this) {
			case PLAYERREIMU: default:
				return Assets.Splashes.PLAYERREIMU;
			case PLAYERMARISA:
				return Assets.Splashes.PLAYERMARISA;
			case PLAYERKOISHI:
				return Assets.Splashes.PLAYERKOISHI;
			case PLAYERSANAE:
				return Assets.Splashes.PLAYERSANAE;
			case PLAYERRUMIA:
				return Assets.Splashes.PLAYERRUMIA;
			case PLAYERKAGUYA:
				return Assets.Splashes.PLAYERKAGUYA;
			case PLAYERKEINE:
				return Assets.Splashes.PLAYERKEINE;
			case PLAYERSHOU:
				return Assets.Splashes.PLAYERSHOU;
			case PLAYERAYA:
				return Assets.Splashes.PLAYERAYA;
			case PLAYEREIKI:
				return Assets.Splashes.PLAYEREIKI;
			case PLAYERCIRNO:
				return Assets.Splashes.PLAYERCIRNO;
			case PLAYERKEIKI:
				return Assets.Splashes.PLAYERKEIKI;
			case PLAYERHINA:
				return Assets.Splashes.PLAYERHINA;
			case PLAYERDOREMY:
				return Assets.Splashes.PLAYERDOREMY;
		}
	}

	public boolean isUnlocked(){
		//no unlock system in THPD:reloaded!
		return true;
	}
}