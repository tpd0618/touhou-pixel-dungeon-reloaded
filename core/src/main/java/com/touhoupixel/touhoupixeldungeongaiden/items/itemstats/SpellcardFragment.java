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

package com.touhoupixel.touhoupixeldungeongaiden.items.itemstats;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SpellcardFragment extends Item {

	private static final String TXT_VALUE	= "%+d";

	{
		image = ItemSpriteSheet.SPELLCARD_FRAGMENT;
	}

	public SpellcardFragment() {
		this( 1 );
	}

	public SpellcardFragment(int value ) {
		this.quantity = value;
	}
	
	@Override
	public ArrayList<String> actions( Hero heroine) {
		return new ArrayList<>();
	}
	
	@Override
	public boolean doPickUp(Hero heroine, int pos) {

		Statistics.spellcardfragment += 1;

		GameScene.pickUp( this, pos );
		heroine.spendAndNext( TIME_TO_PICK_UP );
		
		Sample.INSTANCE.play( Assets.Sounds.GOLD, 1, 1, Random.Float( 0.9f, 1.1f ) );
		updateQuickslot();
		
		return true;
	}
}
