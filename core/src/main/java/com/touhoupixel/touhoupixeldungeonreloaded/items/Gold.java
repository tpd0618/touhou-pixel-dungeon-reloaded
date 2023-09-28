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

package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReachIncrease;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Gold extends Item {

	private static final String TXT_VALUE	= "%+d";
	
	{
		image = ItemSpriteSheet.GOLD;
		stackable = true;
	}
	
	public Gold() {
		this( 1 );
	}
	
	public Gold( int value ) {
		this.quantity = value;
	}
	
	@Override
	public ArrayList<String> actions( Hero heroine) {
		return new ArrayList<>();
	}
	
	@Override
	public boolean doPickUp(Hero heroine, int pos) {

		if (Dungeon.floor > 85){
			Dungeon.heroine.HP += Math.min(quantity, heroine.HT);
		} else {
			Dungeon.gold += quantity;
		}
		Statistics.goldCollected += quantity;

		if (Statistics.card69) {
			Buff.prolong(heroine, DoubleSpeed.class, DoubleSpeed.DURATION);
		}

		if (Statistics.card70) {
			Buff.prolong(heroine, ReachIncrease.class, ReachIncrease.DURATION);
		}

		GameScene.pickUp( this, pos );
		heroine.sprite.showStatus( CharSprite.NEUTRAL, TXT_VALUE, quantity );
		heroine.spendAndNext( TIME_TO_PICK_UP );
		
		Sample.INSTANCE.play( Assets.Sounds.GOLD, 1, 1, Random.Float( 0.9f, 1.1f ) );
		updateQuickslot();
		
		return true;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public Item random() {
		quantity = Random.IntRange( 50, 80 );
		return this;
	}
}