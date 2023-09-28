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

package com.touhoupixel.touhoupixeldungeongaiden.items.potions.elixirs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.BossKiller;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic.PotionOfAttraction;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.exotic.ScrollOfHeatRiser;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class ElixirOfBossKiller extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_BOSS_KILLER;
	}
	
	@Override
	public void apply(Hero heroine) {
		Buff.prolong(heroine, BossKiller.class, BossKiller.DURATION);
	}
	
	@Override
	public int value() {
		return quantity * (30 + 30);
	}
	
	public static class Recipe extends com.touhoupixel.touhoupixeldungeongaiden.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfAttraction.class, ScrollOfHeatRiser.class};
			inQuantity = new int[]{1, 1};
			
			cost = 8;
			
			output = ElixirOfBossKiller.class;
			outQuantity = 1;
		}
		
	}

}