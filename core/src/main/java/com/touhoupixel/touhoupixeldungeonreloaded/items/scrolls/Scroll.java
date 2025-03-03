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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.ItemStatusHandler;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Recipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.UnstableSpellbook;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ExoticScroll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfAggression;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfMadness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfBlink;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfClairvoyance;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfDeepSleep;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfDisarming;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfFear;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfFlock;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfShock;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Catalog;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HeroSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public abstract class Scroll extends Item {

	public static final String AC_READ	= "READ";

	protected static final float TIME_TO_READ	= 1f;

	private static final LinkedHashMap<String, Integer> runes = new LinkedHashMap<String, Integer>() {
		{
			put("KAUNAN",ItemSpriteSheet.SCROLL);
			put("SOWILO",ItemSpriteSheet.SCROLL);
			put("LAGUZ",ItemSpriteSheet.SCROLL);
			put("YNGVI",ItemSpriteSheet.SCROLL);
			put("GYFU",ItemSpriteSheet.SCROLL);
			put("RAIDO",ItemSpriteSheet.SCROLL);
			put("ISAZ",ItemSpriteSheet.SCROLL);
			put("MANNAZ",ItemSpriteSheet.SCROLL);
			put("NAUDIZ",ItemSpriteSheet.SCROLL);
			put("BERKANAN",ItemSpriteSheet.SCROLL);
			put("ODAL",ItemSpriteSheet.SCROLL);
			put("TIWAZ",ItemSpriteSheet.SCROLL);
			put("GENSOKYO",ItemSpriteSheet.SCROLL);
			put("ZUN",ItemSpriteSheet.SCROLL);
		}
	};

	protected static ItemStatusHandler<Scroll> handler;

	protected String rune;

	{
		stackable = true;
		defaultAction = AC_READ;
	}

	@SuppressWarnings("unchecked")
	public static void initLabels() {
		handler = new ItemStatusHandler<>( (Class<? extends Scroll>[])Generator.Category.SCROLL.classes, runes);
	}

	public static void save( Bundle bundle ) {
		handler.save( bundle );
	}

	public static void saveSelectively( Bundle bundle, ArrayList<Item> items ) {
		ArrayList<Class<?extends Item>> classes = new ArrayList<>();
		for (Item i : items){
			if (i instanceof ExoticScroll){
				if (!classes.contains(ExoticScroll.exoToReg.get(i.getClass()))){
					classes.add(ExoticScroll.exoToReg.get(i.getClass()));
				}
			} else if (i instanceof Scroll){
				if (!classes.contains(i.getClass())){
					classes.add(i.getClass());
				}
			}
		}
		handler.saveClassesSelectively( bundle, classes );
	}

	@SuppressWarnings("unchecked")
	public static void restore( Bundle bundle ) {
		handler = new ItemStatusHandler<>( (Class<? extends Scroll>[])Generator.Category.SCROLL.classes, runes, bundle );
	}

	public Scroll() {
		super();
		reset();
	}

	//anonymous scrolls are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;
	public void anonymize(){
		if (!isKnown()) image = ItemSpriteSheet.SCROLL_HOLDER;
		anonymous = true;
	}


	@Override
	public void reset(){
		super.reset();
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			rune = handler.label(this);
		}
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (Dungeon.heroine.buff(Onigiri.class) == null) {
			actions.add(AC_READ);
		}
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action.equals( AC_READ )) {

			if (heroine.buff( Blindness.class ) != null) {
				GLog.w( Messages.get(this, "blinded") );
			} else if (heroine.buff( Silence.class ) != null) {
				GLog.w(Messages.get(this, "silence"));
			} else if (heroine.buff(UnstableSpellbook.bookRecharge.class) != null
					&& heroine.buff(UnstableSpellbook.bookRecharge.class).isCursed()
					&& !(this instanceof ScrollOfExorcism)){
				GLog.n( Messages.get(this, "cursed") );
			} else {
				curUser = heroine;
				curItem = detach( heroine.belongings.backpack );
				doRead();
			}
		}
	}

	public abstract void doRead();

	protected void readAnimation() {
		Invisibility.dispel();
		curUser.spend( TIME_TO_READ );
		curUser.busy();
		((HeroSprite)curUser.sprite).read();
	}

	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( this ));
	}

	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
				updateQuickslot();
			}

			if (Dungeon.heroine.isAlive()) {
				Catalog.setSeen(getClass());
			}
		}
	}
	public void setUnknown(){
		if (isKnown()){
			handler.dontknow(this);
			anonymous =  false;
			updateQuickslot();
		}
	}

	@Override
	public Item identify( boolean byHero ) {
		super.identify(byHero);

		if (!isKnown()) {
			setKnown();
		}
		return this;
	}

	@Override
	public Item forget(boolean byHero) {
		super.forget(byHero);
		if (isKnown()){
			setUnknown();
		}
		return this;
	}

	@Override
	public String name() {
		return isKnown() ? super.name() : Messages.get(this, rune);
	}

	@Override
	public String info() {
		return isKnown() ?
				desc() :
				Messages.get(this, "unknown_desc");
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return isKnown();
	}

	public static HashSet<Class<? extends Scroll>> getKnown() {
		return handler.known();
	}

	public static HashSet<Class<? extends Scroll>> getUnknown() {
		return handler.unknown();
	}

	public static boolean allKnown() {
		return handler.known().size() == Generator.Category.SCROLL.classes.length;
	}

	@Override
	public int value() {
		return 30 * quantity;
	}

	@Override
	public int energyVal() {
		return 6 * quantity;
	}

	public static class PlaceHolder extends Scroll {

		{
			image = ItemSpriteSheet.SCROLL_HOLDER;
		}

		@Override
		public boolean isSimilar(Item item) {
			return ExoticScroll.regToExo.containsKey(item.getClass())
					|| ExoticScroll.regToExo.containsValue(item.getClass());
		}

		@Override
		public void doRead() {}

		@Override
		public String info() {
			return "";
		}
	}

	public static class ScrollToStone extends Recipe {

		private static HashMap<Class<?extends Scroll>, Class<?extends Runestone>> stones = new HashMap<>();
		static {
			stones.put(ScrollOfLullaby.class,       StoneOfDeepSleep.class);
			stones.put(ScrollOfMagicMapping.class,  StoneOfClairvoyance.class);
			stones.put(ScrollOfMirrorImage.class,   StoneOfFlock.class);
			stones.put(ScrollOfRetribution.class,   StoneOfMadness.class);
			stones.put(ScrollOfRage.class,          StoneOfAggression.class);
			stones.put(ScrollOfRecharging.class,    StoneOfShock.class);
			stones.put(ScrollOfExorcism.class,  StoneOfDisarming.class);
			stones.put(ScrollOfTeleportation.class, StoneOfBlink.class);
			stones.put(ScrollOfTerror.class,        StoneOfFear.class);
		}

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 1
					|| !(ingredients.get(0) instanceof Scroll)
					|| !stones.containsKey(ingredients.get(0).getClass())){
				return false;
			}

			return true;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;

			Scroll s = (Scroll) ingredients.get(0);

			s.quantity(s.quantity() - 1);
			s.identify();

			return Reflection.newInstance(stones.get(s.getClass())).quantity(2);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;

			Scroll s = (Scroll) ingredients.get(0);

			if (!s.isKnown()){
				return new Runestone.PlaceHolder().quantity(2);
			} else {
				return Reflection.newInstance(stones.get(s.getClass())).quantity(2);
			}
		}
	}
}