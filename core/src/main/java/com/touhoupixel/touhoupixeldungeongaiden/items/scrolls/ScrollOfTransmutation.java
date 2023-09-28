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

package com.touhoupixel.touhoupixeldungeongaiden.items.scrolls;

import com.touhoupixel.touhoupixeldungeongaiden.Challenges;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Identification;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Transmuting;
import com.touhoupixel.touhoupixeldungeongaiden.items.EquipableItem;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.AlchemicalCatalyst;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.brews.Brew;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.elixirs.Elixir;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic.ExoticPotion;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.exotic.ExoticScroll;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.Talisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.darts.Dart;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.darts.TippedDart;
import com.touhoupixel.touhoupixeldungeongaiden.journal.Catalog;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Plant;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

public class ScrollOfTransmutation extends InventoryScroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_TRANSMUTE;

		bones = true;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return !item.unique && !(item instanceof Plant.Seed) && !(item instanceof MissileWeapon) && !(item instanceof Herb) && !(item instanceof Talisman);
	}

	@Override
	protected void onItemSelected(Item item) {

		Item result = changeItem(item);

		if (item instanceof MeleeWeapon ||
				(item instanceof MissileWeapon && (!(item instanceof Dart) || item instanceof TippedDart)) ||
				(item instanceof Potion && !(item instanceof Elixir || item instanceof Brew || item instanceof AlchemicalCatalyst)) ||
				item instanceof Scroll ||
				item instanceof Ring ||
				item instanceof Wand ||
				item instanceof Plant.Seed ||
				item instanceof Runestone ||
				item instanceof Artifact) {
			if (result == null) {
				//This shouldn't ever trigger
				GLog.n(Messages.get(this, "nothing"));
				curItem.collect(curUser.belongings.backpack);
			} else {
				if (result != item) {
					int slot = Dungeon.quickslot.getSlot(item);
					if (item.isEquipped(Dungeon.heroine)) {
						item.cursed = false; //to allow it to be unequipped
						((EquipableItem) item).doUnequip(Dungeon.heroine, false);
						((EquipableItem) result).doEquip(Dungeon.heroine);
					} else {
						item.detach(Dungeon.heroine.belongings.backpack);
						if (!result.collect()) {
							Dungeon.level.drop(result, curUser.pos).sprite.drop();
						} else if (Dungeon.heroine.belongings.getSimilar(result) != null) {
							result = Dungeon.heroine.belongings.getSimilar(result);
						}
					}
					if (slot != -1
							&& result.defaultAction != null
							&& !Dungeon.quickslot.isNonePlaceholder(slot)
							&& Dungeon.heroine.belongings.contains(result)) {
						Dungeon.quickslot.setSlot(slot, result);
					}
				}
				if (result.isIdentified()) {
					Catalog.setSeen(result.getClass());
				}
				Transmuting.show(curUser, item, result);
				curUser.sprite.emitter().start(Speck.factory(Speck.CHANGE), 0.2f, 10);
				GLog.p(Messages.get(this, "morph"));
			}
		} else {
			curUser.sprite.parent.add(new Identification(curUser.sprite.center().offset(0, -16)));
			GLog.w(Messages.get(this, "not_transmute_target"));
		}
		Statistics.transmute_use = true;
		updateQuickslot();
	}

	public static Item changeItem(Item item) {
		if (item instanceof MarisaStaff) {
			return changeStaff((MarisaStaff) item);
		} else if (item instanceof TippedDart) {
			return changeTippeDart((TippedDart) item);
		} else if (item instanceof MeleeWeapon || item instanceof MissileWeapon) {
			return changeWeapon((Weapon) item);
		} else if (item instanceof Scroll) {
			return changeScroll((Scroll) item);
		} else if (item instanceof Potion) {
			return changePotion((Potion) item);
		} else if (item instanceof Ring) {
			return changeRing((Ring) item);
		} else if (item instanceof Wand) {
			return changeWand((Wand) item);
		} else if (item instanceof Plant.Seed) {
			return changeSeed((Plant.Seed) item);
		} else if (item instanceof Runestone) {
			return changeStone((Runestone) item);
		} else if (item instanceof Artifact) {
			return changeArtifact((Artifact) item);
		} else {
			return null;
		}
	}

	private static MarisaStaff changeStaff(MarisaStaff staff) {
		Class<? extends Wand> wandClass = staff.wandClass();

		if (wandClass == null) {
			return null;
		} else {
			Wand n;
			do {
				n = (Wand) Generator.random(Generator.Category.WAND);
			} while (Challenges.isItemBlocked(n) || n.getClass() == wandClass);
			n.level(0);
			n.identify();
			staff.imbueWand(n, null);
		}

		return staff;
	}

	private static TippedDart changeTippeDart(TippedDart dart) {
		TippedDart n;
		do {
			n = TippedDart.randomTipped(1);
		} while (n.getClass() == dart.getClass());

		return n;
	}

	private static Weapon changeWeapon(Weapon w) {

		Weapon n;
		Generator.Category c;
		if (w instanceof MeleeWeapon) {
			c = Generator.wepTiers[((MeleeWeapon) w).tier - 1];
		} else {
			c = Generator.misTiers[((MissileWeapon) w).tier - 1];
		}

		do {
			n = (Weapon) Reflection.newInstance(c.classes[Random.chances(c.probs)]);
		} while (Challenges.isItemBlocked(n) || n.getClass() == w.getClass());

		int level = w.trueLevel();
		if (level > 0) {
			n.upgrade(level);
		} else if (level < 0) {
			n.degrade(-level);
		}

		n.enchantment = w.enchantment;
		n.curseInfusionBonus = w.curseInfusionBonus;
		n.masteryPotionBonus = w.masteryPotionBonus;
		n.levelKnown = w.levelKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.augment = w.augment;

		return n;

	}

	private static Ring changeRing(Ring r) {
		Ring n;
		do {
			n = (Ring) Generator.random(Generator.Category.RING);
		} while (Challenges.isItemBlocked(n) || n.getClass() == r.getClass());

		n.level(0);

		int level = r.level();
		if (level > 0) {
			n.upgrade(level);
		} else if (level < 0) {
			n.degrade(-level);
		}

		n.levelKnown = r.levelKnown;
		n.cursedKnown = r.cursedKnown;
		n.cursed = r.cursed;

		return n;
	}

	private static Artifact changeArtifact(Artifact a) {
		Artifact n = Generator.randomArtifact();

		if (n != null && !Challenges.isItemBlocked(n)) {
			n.cursedKnown = a.cursedKnown;
			n.cursed = a.cursed;
			n.levelKnown = a.levelKnown;
			n.transferUpgrade(a.visiblyUpgraded());
			return n;
		}

		return null;
	}

	private static Wand changeWand(Wand w) {

		Wand n;
		do {
			n = (Wand) Generator.random(Generator.Category.WAND);
		} while (Challenges.isItemBlocked(n) || n.getClass() == w.getClass());

		n.level(0);
		int level = w.trueLevel();
		n.upgrade(level);

		n.levelKnown = w.levelKnown;
		n.curChargeKnown = w.curChargeKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.curseInfusionBonus = w.curseInfusionBonus;
		n.resinBonus = w.resinBonus;

		n.curCharges = w.curCharges;
		n.updateLevel();

		return n;
	}

	private static Plant.Seed changeSeed(Plant.Seed s) {

		Plant.Seed n;

		do {
			n = (Plant.Seed) Generator.random(Generator.Category.SEED);
		} while (n.getClass() == s.getClass());

		return n;
	}

	private static Runestone changeStone(Runestone r) {

		Runestone n;

		do {
			n = (Runestone) Generator.random(Generator.Category.STONE);
		} while (n.getClass() == r.getClass());

		return n;
	}

	private static Scroll changeScroll(Scroll s) {
		if (s instanceof ExoticScroll) {
			return Reflection.newInstance(ExoticScroll.exoToReg.get(s.getClass()));
		} else {
			return Reflection.newInstance(ExoticScroll.regToExo.get(s.getClass()));
		}
	}

	private static Potion changePotion(Potion p) {
		if (p instanceof ExoticPotion) {
			return Reflection.newInstance(ExoticPotion.exoToReg.get(p.getClass()));
		} else {
			return Reflection.newInstance(ExoticPotion.regToExo.get(p.getClass()));
		}
	}
}