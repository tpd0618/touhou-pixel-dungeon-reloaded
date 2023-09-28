/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2023 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeongaiden.journal;

import com.touhoupixel.touhoupixeldungeongaiden.Badges;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
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
	RINGS,
	ARTIFACTS,
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
		for (Class weapon : Generator.Category.WEP_T1.classes){
			WEAPONS.seen.put( weapon, true);
		}
		for (Class weapon : Generator.Category.WEP_T2.classes){
			WEAPONS.seen.put( weapon, true);
		}
		for (Class weapon : Generator.Category.WEP_T3.classes){
			WEAPONS.seen.put( weapon, true);
		}
		for (Class weapon : Generator.Category.WEP_T4.classes){
			WEAPONS.seen.put( weapon, true);
		}
		for (Class weapon : Generator.Category.WEP_T5.classes){
			WEAPONS.seen.put( weapon, true);
		}

		for (Class armor : Generator.Category.ARMOR_T1.classes){
			ARMORS.seen.put( armor, true);
		}
		for (Class armor : Generator.Category.ARMOR_T2.classes){
			ARMORS.seen.put( armor, true);
		}
		for (Class armor : Generator.Category.ARMOR_T3.classes){
			ARMORS.seen.put( armor, true);
		}
		for (Class armor : Generator.Category.ARMOR_T4.classes){
			ARMORS.seen.put( armor, true);
		}
		for (Class armor : Generator.Category.ARMOR_T5.classes){
			ARMORS.seen.put( armor, true);
		}

		for (Class wand : Generator.Category.WAND.classes){
			WANDS.seen.put( wand, true);
		}

		for (Class ring : Generator.Category.RING.classes){
			RINGS.seen.put( ring, true);
		}

		for (Class artifact : Generator.Category.ARTIFACT.classes){
			ARTIFACTS.seen.put( artifact, true);
		}

		for (Class potion : Generator.Category.POTION.classes){
			POTIONS.seen.put( potion, true);
		}

		for (Class scroll : Generator.Category.SCROLL.classes){
			SCROLLS.seen.put( scroll, true);
		}
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