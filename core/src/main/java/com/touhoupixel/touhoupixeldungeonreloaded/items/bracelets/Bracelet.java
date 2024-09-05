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

package com.touhoupixel.touhoupixeldungeonreloaded.items.bracelets;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.ItemStatusHandler;
import com.touhoupixel.touhoupixeldungeonreloaded.items.KindofMisc;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Catalog;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Bracelet extends KindofMisc {

	protected Buff buff;

	private static final LinkedHashMap<String, Integer> gems = new LinkedHashMap<String, Integer>() {
		{
			put("garnet", ItemSpriteSheet.BRACELET);
			put("ruby", ItemSpriteSheet.BRACELET);
			put("topaz", ItemSpriteSheet.BRACELET);
			put("emerald", ItemSpriteSheet.BRACELET);
			put("onyx", ItemSpriteSheet.BRACELET);
			put("opal", ItemSpriteSheet.BRACELET);
			put("tourmaline", ItemSpriteSheet.BRACELET);
			put("sapphire", ItemSpriteSheet.BRACELET);
			put("amethyst", ItemSpriteSheet.BRACELET);
			put("quartz", ItemSpriteSheet.BRACELET);
			put("agate", ItemSpriteSheet.BRACELET);
			put("diamond", ItemSpriteSheet.BRACELET);
		}
	};

	private static ItemStatusHandler<Bracelet> handler;

	private String gem;

	private float levelsToID = 1;

	@SuppressWarnings("unchecked")
	public static void initGems() {
		handler = new ItemStatusHandler<>((Class<? extends Bracelet>[]) Generator.Category.BRACELET.classes, gems);
	}

	public static void save(Bundle bundle) {
		handler.save(bundle);
	}

	public static void saveSelectively(Bundle bundle, ArrayList<Item> items) {
		handler.saveSelectively(bundle, items);
	}

	@SuppressWarnings("unchecked")
	public static void restore(Bundle bundle) {
		handler = new ItemStatusHandler<>((Class<? extends Bracelet>[]) Generator.Category.BRACELET.classes, gems, bundle);
	}

	public Bracelet() {
		super();
		reset();
	}

	//anonymous rings are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;

	public void anonymize() {
		if (!isKnown()) image = ItemSpriteSheet.BRACELET_HOLDER;
		anonymous = true;
	}

	public void reset() {
		super.reset();
		levelsToID = 1;
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			gem = handler.label(this);
		}
	}

	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown(this));
	}

	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
			}

			if (Dungeon.heroine.isAlive()) {
				Catalog.setSeen(getClass());
			}
		}
	}

	@Override
	public String name() {
		return isKnown() ? super.name() : Messages.get(Bracelet.class, gem);
	}

	@Override
	public String info() {

		String desc = isKnown() ? super.desc() : Messages.get(this, "unknown_desc");

		if (cursed && isEquipped(Dungeon.heroine)) {
			desc += "\n\n" + Messages.get(Bracelet.class, "cursed_worn");

		} else if (cursed && cursedKnown) {
			desc += "\n\n" + Messages.get(Bracelet.class, "curse_known");

		} else if (!isIdentified() && cursedKnown) {
			desc += "\n\n" + Messages.get(Bracelet.class, "not_cursed");

		}

		return desc;
	}

	@Override
	public Item upgrade() {
		super.upgrade();

		cursed = false;

		return this;
	}

	@Override
	public boolean isIdentified() {
		return super.isIdentified() && isKnown();
	}

	@Override
	public Item identify(boolean byHero) {
		setKnown();
		levelsToID = 0;
		return super.identify(byHero);
	}

	@Override
	public Item random() {
		int n = 0;
		level(n);

		return this;
	}

	public static HashSet<Class<? extends Bracelet>> getKnown() {
		return handler.known();
	}

	public static HashSet<Class<? extends Bracelet>> getUnknown() {
		return handler.unknown();
	}

	@Override
	public int value() {
		int price = 75;
		if (cursed && cursedKnown) {
			price /= 2;
		}
		if (levelKnown) {
			if (level() > 0) {
				price *= (level() + 1);
			} else if (level() < 0) {
				price /= (1 - level());
			}
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}
}