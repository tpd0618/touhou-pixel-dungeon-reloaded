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
import com.touhoupixel.touhoupixeldungeonreloaded.items.BrokenSeal;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.LifeSpellcardChecker;
import com.touhoupixel.touhoupixeldungeonreloaded.items.NitoDismantleHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.SpellcardCaster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Torch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Waterskin;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.CloakOfShadows;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.CardHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.PotionBandolier;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.ScrollHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.HakureiHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.VelvetPouch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.DragonHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.HealHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.StrengthHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.SwiftHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfWealth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfUpgrade;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.MystiaTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans.OkinaTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Miracle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ArisaKeyboard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.EveKeyboard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.KoishiDagger;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.RandomStar;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.SanaeExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ReimuExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.ReimuTailsman;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;

public enum HeroClass {

	PLAYERREIMU(),
	PLAYERMARISA(),
	PLAYERKOISHI(),
	PLAYERSANAE();

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

		//test//
		RandomStar randomstar = new RandomStar();
		randomstar.identify().collect();

		EveKeyboard randomsta2r = new EveKeyboard();
		randomsta2r.identify().collect();

		ArisaKeyboard randomstar3 = new ArisaKeyboard();
		randomstar3.identify().collect();
		//test//

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
		}
	}

	public boolean isUnlocked(){
		//no unlock system in TPD:reloaded!
		return true;
	}
}