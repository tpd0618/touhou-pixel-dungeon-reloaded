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

package com.touhoupixel.touhoupixeldungeongaiden.levels.rooms.special;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.npcs.Shopkeeper;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.Honeypot;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.PatchouliCard;
import com.touhoupixel.touhoupixeldungeongaiden.items.SpyGlass;
import com.touhoupixel.touhoupixeldungeongaiden.items.TenshiCard;
import com.touhoupixel.touhoupixeldungeongaiden.items.Torch;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.SmallRice;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfIdentify;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfExorcism;
import com.touhoupixel.touhoupixeldungeongaiden.items.spells.Alchemize;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfAugmentation;
import com.touhoupixel.touhoupixeldungeongaiden.items.tickets.ThreeStarTicket;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.darts.TippedDart;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Terrain;
import com.touhoupixel.touhoupixeldungeongaiden.levels.painters.Painter;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShopRoom extends SpecialRoom {

	private ArrayList<Item> itemsToSpawn;

	@Override
	public int minWidth() {
		return 11;
	}

	@Override
	public int minHeight() {
		return 11;
	}

	@Override
	public int maxWidth() {
		return Math.max(11, (int)(Math.sqrt(itemCount())+3));
	}

	@Override
	public int maxHeight() {
		return Math.max(11, (int)(Math.sqrt(itemCount())+3));
	}

	public int itemCount(){
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return itemsToSpawn.size();
	}
	
	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );

		placeShopkeeper( level );

		placeItems( level );
		
		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}

	}

	protected void placeShopkeeper( Level level ) {

		int pos = level.pointToCell(center());

		Mob shopkeeper = new Shopkeeper();
		shopkeeper.pos = pos;
		level.mobs.add( shopkeeper );

	}

	protected void placeItems( Level level ){

		if (itemsToSpawn == null){
			itemsToSpawn = generateItems();
		}

		Point itemPlacement = new Point(entrance());
		if (itemPlacement.y == top){
			itemPlacement.y++;
		} else if (itemPlacement.y == bottom) {
			itemPlacement.y--;
		} else if (itemPlacement.x == left){
			itemPlacement.x++;
		} else {
			itemPlacement.x--;
		}

		for (Item item : itemsToSpawn) {

			if (itemPlacement.x == left+1 && itemPlacement.y != top+1){
				itemPlacement.y--;
			} else if (itemPlacement.y == top+1 && itemPlacement.x != right-1){
				itemPlacement.x++;
			} else if (itemPlacement.x == right-1 && itemPlacement.y != bottom-1){
				itemPlacement.y++;
			} else {
				itemPlacement.x--;
			}

			int cell = level.pointToCell(itemPlacement);

			if (level.heaps.get( cell ) != null) {
				do {
					cell = level.pointToCell(random());
				} while (level.heaps.get( cell ) != null || level.findMob( cell ) != null);
			}

			level.drop( item, cell ).type = Heap.Type.FOR_SALE;
		}

	}
	
	protected static ArrayList<Item> generateItems() {

		ArrayList<Item> itemsToSpawn = new ArrayList<>();

		MeleeWeapon w;
		Armor a;
		switch (Dungeon.floor) {
		default: case 6:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[1]);
			a = (Armor) Generator.random(Generator.armorTiers[1]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[1]).quantity(3).identify(false) );
			itemsToSpawn.add( new ScrollOfIdentify() );
			break;
			
		case 16:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[2]);
			a = (Armor) Generator.random(Generator.armorTiers[2]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[2]).quantity(3).identify(false) );
			itemsToSpawn.add( new ScrollOfExorcism() );
			break;
			
		case 26:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[3]);
			a = (Armor) Generator.random(Generator.armorTiers[3]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[3]).quantity(3).identify(false) );
			itemsToSpawn.add( new ScrollOfIdentify() );
			break;

		case 36:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[4]);
			a = (Armor) Generator.random(Generator.armorTiers[4]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[4]).quantity(3).identify(false) );
			itemsToSpawn.add( new ScrollOfExorcism() );
			break;
		}
		w.cursed = false;
		w.level(0);
		w.identify(false);
		itemsToSpawn.add(w);

		a.cursed = false;
		a.level(0);
		a.identify(false);
		itemsToSpawn.add(a);
		
		itemsToSpawn.add( TippedDart.randomTipped(3) );

		itemsToSpawn.add( new TenshiCard() );

		itemsToSpawn.add( new Torch() );
		itemsToSpawn.add( new SpyGlass().quantity(Random.IntRange(2, 3)));

		itemsToSpawn.add( new Alchemize().quantity(Random.IntRange(2, 3)));

		itemsToSpawn.add( new PotionOfHealing() );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.POTION ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.POTION ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.POTION ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.SCROLL ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.SCROLL ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.SCROLL ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.HERB ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.HERB ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.TALISMAN ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.TALISMAN ) );
		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.VIAL ) );

		for (int i=0; i < 2; i++)
			itemsToSpawn.add( Random.Int(2) == 0 ?
					Generator.randomUsingDefaults( Generator.Category.POTION ) :
					Generator.randomUsingDefaults( Generator.Category.SCROLL ) );


		itemsToSpawn.add( new SmallRice() );
		itemsToSpawn.add( new SmallRice() );

		itemsToSpawn.add( new Honeypot() );

		itemsToSpawn.add( new ThreeStarTicket() );
		itemsToSpawn.add( new ThreeStarTicket() );

		itemsToSpawn.add(new StoneOfAugmentation());
		itemsToSpawn.add(new StoneOfAugmentation());

		TimekeepersHourglass hourglass = Dungeon.heroine.belongings.getItem(TimekeepersHourglass.class);
		if (hourglass != null && hourglass.isIdentified() && !hourglass.cursed){
			int bags = 0;
			//creates the given float percent of the remaining bags to be dropped.
			//this way players who get the hourglass late can still max it, usually.
			switch (Dungeon.floor) {
				case 6:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.20f ); break;
				case 16:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.25f ); break;
				case 26:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.50f ); break;
				case 36: case 45: case 65: case 85:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.80f ); break;
			}

			for(int i = 1; i <= bags; i++){
				itemsToSpawn.add( new TimekeepersHourglass.sandBag());
				hourglass.sandBags ++;
			}
		}

		Item rare;
		switch (Random.Int(10)){
			case 0:
				rare = Generator.random( Generator.Category.WAND );
				rare.level( 0 );
				break;
			case 1:
				rare = Generator.random(Generator.Category.RING);
				rare.level( 0 );
				break;
			case 2:
				rare = Generator.random( Generator.Category.ARTIFACT );
				break;
			default:
				rare = new PatchouliCard();
		}
		rare.cursed = false;
		rare.cursedKnown = true;
		itemsToSpawn.add( rare );

		//hard limit is 63 items + 1 shopkeeper, as shops can't be bigger than 8x8=64 internally
		if (itemsToSpawn.size() > 63) {
			throw new RuntimeException("Shop attempted to carry more than 63 items!");
		}

		//use a new generator here to prevent items in shop stock affecting levelgen RNG (e.g. sandbags)
		Random.pushGenerator(Random.Long());
			Random.shuffle(itemsToSpawn);
		Random.popGenerator();

		return itemsToSpawn;
	}
}
