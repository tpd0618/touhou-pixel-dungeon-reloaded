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
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.NitoChecker;
import com.touhoupixel.touhoupixeldungeonreloaded.items.NitoDismantleHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.UpgradeCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Waterskin;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.CardHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.PotionBandolier;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.ScrollHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.HakureiHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.VelvetPouch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.MeatPie;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.DragonHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfBlink;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.DebilitationTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfHealWounds;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Miracle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;

public enum HeroClass {

	PLAYERREIMU(),
	PLAYERMARISA(),
	PLAYERSANAE(),
	PLAYERYOUMU(),
	PLAYERSAKUYA();

	public void initHero( Hero hero ) {

		hero.heroClass = this;

		Item i = new ReimuArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ReimuArmor)i;

		i = new Food();
		if (!Challenges.isItemBlocked(i)) i.collect();

		//test, too many will make the bug for the game

		//test, too many will make the bug for the game

		if (Dungeon.hero.heroClass == HeroClass.PLAYERREIMU) {
			PotionOfHealing poh = new PotionOfHealing();
			poh.quantity(3).identify().collect();

			DragonHerb dragonherb = new DragonHerb();
			dragonherb.quantity(3).collect();

			MeatPie meatpie = new MeatPie();
			meatpie.quantity(3).collect();

			StoneOfBlink stoneofblink = new StoneOfBlink();
			stoneofblink.quantity(3).collect();

			DebilitationTailsman dt = new DebilitationTailsman();
			dt.quantity(3).collect();
		}

		MarisaStaff staff;
		staff = new MarisaStaff(new WandOfMagicMissile());
		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);
		Dungeon.quickslot.setSlot(0, staff);

		Waterskin waterskin = new Waterskin();
		waterskin.collect();
		Dungeon.quickslot.setSlot(1, waterskin);

		Miracle miracle = new Miracle();
		miracle.identify().collect();
		Dungeon.quickslot.setSlot(2, miracle);

		NitoChecker nitochecker = new NitoChecker();
		nitochecker.collect();

		NitoDismantleHammer nitodismantlehammer = new NitoDismantleHammer();
		nitodismantlehammer.collect();

		new MagicalHolster().collect();
		new PotionBandolier().collect();
		new ScrollHolder().collect();
		new HakureiHolder().collect();
		new VelvetPouch().collect();
		new CardHolder().collect();
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