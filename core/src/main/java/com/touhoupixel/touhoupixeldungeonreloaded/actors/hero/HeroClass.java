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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AuraReimu;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YoumuAbility;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.GlassBottle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.NitoChecker;
import com.touhoupixel.touhoupixeldungeonreloaded.items.OminousGap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;

import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.CardsAndCubesBag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MarisaHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.ReimuHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.ReisenHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.SanaeHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.ClearCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.WhiteCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;

import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.IdolStick;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KoishiDagger;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ReimuExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.RustyRoukanken;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.SanaeExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;

public enum HeroClass {

	PLAYERREIMU(),
	PLAYERMARISA(),
	PLAYERSANAE(),
	PLAYERYOUMU(),
	PLAYERSAKUYA();

	public void initHero( Hero heroine) {
		//mobs bestiary flavor text todo

		//test, put too many stuffs will trigger a certain bug

		//test, put too many stuffs will trigger a certain bug

		OminousGap ominousGap = new OminousGap();
		ominousGap.quantity(5).collect();

		PotionOfHealing potionOfHealing = new PotionOfHealing();
		potionOfHealing.quantity(3).identify().collect();

		Food food = new Food();
		food.collect();

		heroine.heroClass = this;

		Item i = new ReimuArmor().identify();
		if (!Challenges.isItemBlocked(i)) heroine.belongings.armor = (ReimuArmor)i;

		GlassBottle glassBottle = new GlassBottle();
		glassBottle.collect();
		Dungeon.quickslot.setSlot(1, glassBottle);

		NitoChecker nitoChecker = new NitoChecker();
		nitoChecker.collect();

		//new PotionOfToxicGas().collect();
		//new ScrollOfAnchor().quantity(3).identify().collect();
		//new ScrollOfSubmerge().quantity(3).identify().collect();
		//new IdolStick().collect();

		new MarisaHolder().collect();
		new ReimuHolder().collect();
		new ReisenHolder().collect();
		new SanaeHolder().collect();
		new CardsAndCubesBag().collect();

		switch (this){
			case PLAYERREIMU:
				initReimu(heroine);
				break;
			case PLAYERMARISA:
				initMarisa(heroine);
				break;
			case PLAYERSANAE:
				initSanae(heroine);
				break;
			case PLAYERYOUMU:
				initYoumu(heroine);
				break;
			case PLAYERSAKUYA:
				initSakuya(heroine);
				break;
		}
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
			case PLAYERSANAE:
				return Assets.Sprites.PLAYERSANAE;
			case PLAYERYOUMU:
				return Assets.Sprites.PLAYERYOUMU;
			case PLAYERSAKUYA:
				return Assets.Sprites.PLAYERSAKUYA;
		}
	}

	public String splashArt(){
		switch (this) {
			case PLAYERREIMU: default:
				return Assets.Splashes.PLAYERREIMU;
			case PLAYERMARISA:
				return Assets.Splashes.PLAYERMARISA;
			case PLAYERSANAE:
				return Assets.Splashes.PLAYERSANAE;
			case PLAYERYOUMU:
				return Assets.Splashes.PLAYERYOUMU;
			case PLAYERSAKUYA:
				return Assets.Splashes.PLAYERSAKUYA;
		}
	}

	public boolean isUnlocked(){
		//no unlock system in THPD:reloaded!
		return true;
	}

	public static void initReimu(Hero heroine){
		ReimuExorcismRod reimuExorcismRod = new ReimuExorcismRod();
		(heroine.belongings.weapon = reimuExorcismRod).identify();

		Buff.affect(Dungeon.heroine, AuraReimu.class);
	}
	public static void initMarisa(Hero heroine){
		MarisaStaff staff;
		staff = new MarisaStaff(new WandOfMagicMissile());
		(heroine.belongings.weapon = staff).identify();
		heroine.belongings.weapon.activate(heroine);
		Dungeon.quickslot.setSlot(0, staff);

		ClearCubeFragment clearCubeFragment = new ClearCubeFragment();
		WhiteCubeFragment whiteCubeFragment = new WhiteCubeFragment();
		clearCubeFragment.quantity(200).collect();
		whiteCubeFragment.quantity(5).collect();
	}
	public static void initSanae(Hero heroine){
		SanaeExorcismRod sanaeExorcismRod = new SanaeExorcismRod();
		sanaeExorcismRod.identify().collect();
		Dungeon.quickslot.setSlot(0, sanaeExorcismRod);

		ReimuExorcismRod reimuExorcismRod = new ReimuExorcismRod();
		(heroine.belongings.weapon = reimuExorcismRod).identify();
	}
	public static void initYoumu(Hero heroine){

		RustyRoukanken rustyRoukanken = new RustyRoukanken();
		rustyRoukanken.identify();
		heroine.belongings.weapon = rustyRoukanken;
		Buff.affect(Dungeon.heroine, YoumuAbility.class);

	}
	public static void initSakuya(Hero heroine){
		ThrowingKnife throwingKnife = new ThrowingKnife();
		throwingKnife.quantity(5).collect();
		Dungeon.quickslot.setSlot(0, throwingKnife);

		KoishiDagger koishiDagger = new KoishiDagger();
		(heroine.belongings.weapon = koishiDagger).identify();

		TimekeepersHourglass timekeepersHourglass = new TimekeepersHourglass();
		(heroine.belongings.artifact = timekeepersHourglass).identify();
		heroine.belongings.artifact.activate(Dungeon.heroine);
		Dungeon.quickslot.setSlot(2, timekeepersHourglass);

		Generator.removeTimekeeperHourglass();
	}
}
