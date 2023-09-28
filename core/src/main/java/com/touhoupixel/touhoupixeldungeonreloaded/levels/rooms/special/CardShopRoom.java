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

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.Shopkeeper;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.DoremySheep;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.KeikiCreation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.LifeCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.NazrinAlchemyMoney;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.NazrinGoldMoney;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categoryone.ShionUchiwa;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.BlankCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.PhoenixTail;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.TeacupMarisa;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorythree.TeacupReimu;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.IceFairy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.LunaCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.ShanghaiDoll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.StarCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.categorytwo.SunnyCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.AunnHounds;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.HijiriSutra;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.DragonPassage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.EikiMoney;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.EirinElixir;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.FlandreDestruction;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.GaleGeta;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.MiniHakkero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.MiniHakkeroMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.MinorikoCrop;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.marisaexclusive.RingoBrandDango;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.AncientMagatama;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.AnnoyingUfo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.LarvaScale;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.NemunoKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.NitoriDilemma;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.OkinaBackdoor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.SeiranBleedingHammer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.SekibankiHead;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.ShouPagoda;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.YingYangOrb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.reimuexclusive.YingYangOrbNeedle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.ItemSeason;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.KomachiDetour;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MaidKnife;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MaidKnifeRicochet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MegumuBarleyRice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.MikoAuthority;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.SmeltScale;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.SpiritBottle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.TooHonestSignpost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.UndergroundSun;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.VampireFang;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.DanmakuGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.DragonPipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.KaguyaStash;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.KanakoOffering;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.MiserAdvice;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.PebbleHat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.SanaeAmulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.SanaeAmuletAlt;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.SuwakoFrog;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.TewiFoot;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sanaeexclusive.YachieThreat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.ClownpieceMoon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.HalfGhost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.HalfGhostSpare;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.IrresistibleFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.LifeBurningTorch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.MiracleMallet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.MomoyoCentipede;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.PristineConfidence;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.ScreenBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.SuikaGourd;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.youmuexclusive.TenshiKeystone;
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

		itemsToSpawn.add( new NazrinGoldMoney() );
		itemsToSpawn.add( new NazrinAlchemyMoney() );
		itemsToSpawn.add( new LifeCard() );
		itemsToSpawn.add( new KeikiCreation() );
		itemsToSpawn.add( new ShionUchiwa() );
		itemsToSpawn.add( new DoremySheep() );
		itemsToSpawn.add( new ShanghaiDoll() );
		itemsToSpawn.add( new IceFairy() );
		itemsToSpawn.add( new StarCard() );
		itemsToSpawn.add( new LunaCard() );
		itemsToSpawn.add( new SunnyCard() );
		itemsToSpawn.add( new TeacupReimu() );
		itemsToSpawn.add( new TeacupMarisa() );
		itemsToSpawn.add( new BlankCard() );
		itemsToSpawn.add( new PhoenixTail() );

		if (Dungeon.heroine.heroClass == HeroClass.PLAYERREIMU) {
			itemsToSpawn.add( new AncientMagatama() );
			itemsToSpawn.add( new AnnoyingUfo() );
			itemsToSpawn.add( new LarvaScale() );
			itemsToSpawn.add( new NemunoKnife() );
			itemsToSpawn.add( new NitoriDilemma() );
			itemsToSpawn.add( new OkinaBackdoor() );
			itemsToSpawn.add( new SeiranBleedingHammer() );
			itemsToSpawn.add( new SekibankiHead() );
			itemsToSpawn.add( new ShouPagoda() );
			itemsToSpawn.add( new YingYangOrb() );
			itemsToSpawn.add( new YingYangOrbNeedle() );
		}

		if (Dungeon.heroine.heroClass == HeroClass.PLAYERMARISA) {
			itemsToSpawn.add( new AunnHounds() );
			itemsToSpawn.add( new HijiriSutra() );
			itemsToSpawn.add( new DragonPassage() );
			itemsToSpawn.add( new EikiMoney() );
			itemsToSpawn.add( new EirinElixir() );
			itemsToSpawn.add( new FlandreDestruction() );
			itemsToSpawn.add( new GaleGeta() );
			itemsToSpawn.add( new MiniHakkero() );
			itemsToSpawn.add( new MiniHakkeroMissile() );
			itemsToSpawn.add( new MinorikoCrop() );
			itemsToSpawn.add( new RingoBrandDango() );
		}

		if (Dungeon.heroine.heroClass == HeroClass.PLAYERSANAE) {
			itemsToSpawn.add( new DanmakuGhost() );
			itemsToSpawn.add( new DragonPipe() );
			itemsToSpawn.add( new KaguyaStash() );
			itemsToSpawn.add( new KanakoOffering() );
			itemsToSpawn.add( new MiserAdvice() );
			itemsToSpawn.add( new PebbleHat() );
			itemsToSpawn.add( new SanaeAmulet() );
			itemsToSpawn.add( new SanaeAmuletAlt() );
			itemsToSpawn.add( new SuwakoFrog() );
			itemsToSpawn.add( new TewiFoot() );
			itemsToSpawn.add( new YachieThreat() );
		}

		if (Dungeon.heroine.heroClass == HeroClass.PLAYERYOUMU) {
			itemsToSpawn.add( new ClownpieceMoon() );
			itemsToSpawn.add( new HalfGhost() );
			itemsToSpawn.add( new HalfGhostSpare() );
			itemsToSpawn.add( new IrresistibleFan() );
			itemsToSpawn.add( new LifeBurningTorch() );
			itemsToSpawn.add( new MiracleMallet() );
			itemsToSpawn.add( new MomoyoCentipede() );
			itemsToSpawn.add( new PristineConfidence() );
			itemsToSpawn.add( new ScreenBorder() );
			itemsToSpawn.add( new SuikaGourd() );
			itemsToSpawn.add( new TenshiKeystone() );
		}

		if (Dungeon.heroine.heroClass == HeroClass.PLAYERSAKUYA) {
			itemsToSpawn.add( new ItemSeason() );
			itemsToSpawn.add( new KomachiDetour() );
			itemsToSpawn.add( new MaidKnife() );
			itemsToSpawn.add( new MaidKnifeRicochet() );
			itemsToSpawn.add( new MegumuBarleyRice() );
			itemsToSpawn.add( new MikoAuthority() );
			itemsToSpawn.add( new SmeltScale() );
			itemsToSpawn.add( new SpiritBottle() );
			itemsToSpawn.add( new TooHonestSignpost() );
			itemsToSpawn.add( new UndergroundSun() );
			itemsToSpawn.add( new VampireFang() );
		}

		if (itemsToSpawn.size() > 100) {
			throw new RuntimeException("Shop attempted to carry more than 100 items!");
		}

		//use a new generator here to prevent items in shop stock affecting levelgen RNG (e.g. sandbags)
		Random.pushGenerator(Random.Long());
			Random.shuffle(itemsToSpawn);
		Random.popGenerator();

		return itemsToSpawn;
	}
}