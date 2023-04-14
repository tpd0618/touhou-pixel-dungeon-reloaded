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
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.items.GlassBottle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.NitoChecker;
import com.touhoupixel.touhoupixeldungeonreloaded.items.NitoDismantleHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MagicalContainer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.PotionBandolier;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.SpellcardHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.HerbPouch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.VelvetPouch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Weeds;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Miracle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ReimuExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ToramaruSpear;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;

public enum HeroClass {

	PLAYERREIMU(),
	PLAYERMARISA(),
	PLAYERSANAE(),
	PLAYERYOUMU(),
	PLAYERSAKUYA();

	public void initHero( Hero hero ) {

		//test, too many will trigger the bug for the game

		//test, too many will trigger the bug for the game

		if (Statistics.difficulty == 1){
			PotionOfHealing poh = new PotionOfHealing();
			poh.quantity(3).identify().collect();
		}

		if (Dungeon.isChallenged(Challenges.KEYSTONE_MISSILE)) {
			PotionOfLevitation pol = new PotionOfLevitation();
			pol.quantity(10).identify().collect();
		}

		if (Dungeon.isChallenged(Challenges.DREAM_LOGICAL_WORLD)) {
			Weeds weeds = new Weeds();
			weeds.quantity(100).identify().collect();
		}

		Food food = new Food();
		food.collect();

		hero.heroClass = this;

		ReimuExorcismRod reimuExorcismRod = new ReimuExorcismRod();
		reimuExorcismRod.identify().collect();

		MarisaStaff staff;
		staff = new MarisaStaff(new WandOfMagicMissile());
		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);
		Dungeon.quickslot.setSlot(0, staff);

		Item i = new ReimuArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ReimuArmor)i;

		GlassBottle glassBottle = new GlassBottle();
		glassBottle.collect();
		Dungeon.quickslot.setSlot(1, glassBottle);

		Miracle miracle = new Miracle();
		miracle.identify().collect();

		NitoChecker nitoChecker = new NitoChecker();
		nitoChecker.collect();

		NitoDismantleHammer nitodismantlehammer = new NitoDismantleHammer();
		nitodismantlehammer.collect();

		new MagicalHolster().collect();
		new PotionBandolier().collect();
		new SpellcardHolder().collect();
		new HerbPouch().collect();
		new VelvetPouch().collect();
		new MagicalContainer().collect();
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
}