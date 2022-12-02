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

package com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.Shopkeeper;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.EsteemedAuthority;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.GreatTenguRice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.ItemSeason;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.KeystoneEndurance;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.ScreenBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.activecards.SpiritPowerBottle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.HalfHalfGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.IceFairy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.SafeReturnAmulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.ShanghaiDoll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.ShedSnakeskinAmulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.YingYangOrb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.equipmentcards.YingYangOrbNeedle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.BurstingRedFrog;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.DanmakuGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.DeathAvoidanceElixir;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.DragonPipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.GaleGeta;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.GluttonCentipede;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.IdolDefenseCorps;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.KaguyaSecretStash;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.KiketsuThreat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.LuckyCat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.MiserAdvice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.MoneyIsTheBestInHell;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.PebbleHat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.PhoenixTail;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.SurvivalFittest;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.SutraPower;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.TanukiApprentice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards.YamawaroTech;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.HastyDetourCrossing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.NimbleFabric;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.SmeltScales;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.activecards.TooHonestSignpost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.ExuberantPowerless;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.LunaticSilence;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.OffensiveJeweledPagoda;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.QuietTwinkling;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.TeacupMarisa;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.TeacupReimu;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.equipmentcards.YamanbaKitchenKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.BumperCrop;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.DilemmaCapitalism;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.DragonPassage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.HundredthBlackMarket;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.IrresistibleFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.LifeBurningTorch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.PristineApproval;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.ScamRabbitFoot;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.SheepCount;
import com.touhoupixel.touhoupixeldungeonreloaded.items.extracards.passivecards.SootCoveredUchiwa;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.Painter;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class CardShopRoom extends SpecialRoom {

	private ArrayList<Item> itemsToSpawn;

	@Override
	public int minWidth() {
		return Math.max(9, (int)(Math.sqrt(itemCount())+3));
	}

	@Override
	public int minHeight() {
		return Math.max(9, (int)(Math.sqrt(itemCount())+3));
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

		itemsToSpawn.add( new EsteemedAuthority() );
		itemsToSpawn.add( new GreatTenguRice() );
		itemsToSpawn.add( new ItemSeason() );
		itemsToSpawn.add( new KeystoneEndurance() );
		itemsToSpawn.add( new ScreenBorder() );
		itemsToSpawn.add( new SpiritPowerBottle() );
		itemsToSpawn.add( new HalfHalfGhost() );
		itemsToSpawn.add( new IceFairy() );
		itemsToSpawn.add( new SafeReturnAmulet() );
		itemsToSpawn.add( new ShanghaiDoll() );
		itemsToSpawn.add( new ShedSnakeskinAmulet() );
		itemsToSpawn.add( new YingYangOrb() );
		itemsToSpawn.add( new YingYangOrbNeedle() );
		itemsToSpawn.add( new BurstingRedFrog() );
		itemsToSpawn.add( new DanmakuGhost() );
		itemsToSpawn.add( new DeathAvoidanceElixir() );
		itemsToSpawn.add( new DragonPipe() );
		itemsToSpawn.add( new GaleGeta() );
		itemsToSpawn.add( new GluttonCentipede() );
		itemsToSpawn.add( new IdolDefenseCorps() );
		itemsToSpawn.add( new KaguyaSecretStash() );
		itemsToSpawn.add( new KiketsuThreat() );
		itemsToSpawn.add( new LuckyCat() );
		itemsToSpawn.add( new MiserAdvice() );
		itemsToSpawn.add( new MoneyIsTheBestInHell() );
		itemsToSpawn.add( new PebbleHat() );
		itemsToSpawn.add( new PhoenixTail() );
		itemsToSpawn.add( new SurvivalFittest() );
		itemsToSpawn.add( new SutraPower() );
		itemsToSpawn.add( new TanukiApprentice() );
		itemsToSpawn.add( new YamawaroTech() );
		itemsToSpawn.add( new SmeltScales() );
		itemsToSpawn.add( new TooHonestSignpost() );
		itemsToSpawn.add( new ExuberantPowerless() );
		itemsToSpawn.add( new LunaticSilence() );
		itemsToSpawn.add( new OffensiveJeweledPagoda() );
		itemsToSpawn.add( new QuietTwinkling() );
		itemsToSpawn.add( new YamanbaKitchenKnife() );
		itemsToSpawn.add( new BumperCrop() );
		itemsToSpawn.add( new DilemmaCapitalism() );
		itemsToSpawn.add( new DragonPassage() );
		itemsToSpawn.add( new HundredthBlackMarket() );
		itemsToSpawn.add( new LifeBurningTorch() );
		itemsToSpawn.add( new ScamRabbitFoot() );
		itemsToSpawn.add( new SheepCount() );
		itemsToSpawn.add( new NimbleFabric() );
		itemsToSpawn.add( new HastyDetourCrossing() );
		itemsToSpawn.add( new PristineApproval() );
		itemsToSpawn.add( new IrresistibleFan() );
		itemsToSpawn.add( new SootCoveredUchiwa() );
		itemsToSpawn.add( new TeacupReimu() );
		itemsToSpawn.add( new TeacupMarisa() );

		if (itemsToSpawn.size() > 100) {
			throw new RuntimeException("Shop attempted to carry more than 63 items!");
		}

		//use a new generator here to prevent items in shop stock affecting levelgen RNG (e.g. sandbags)
		Random.pushGenerator(Random.Long());
			Random.shuffle(itemsToSpawn);
		Random.popGenerator();

		return itemsToSpawn;
	}
}