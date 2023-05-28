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

package com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.QuadDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfAttraction;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfHisou;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class ElixirOfQuadDamage extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_QUAD_DAMAGE;
	}
	
	@Override
	public void apply(Hero hero) {
		if (Dungeon.depth == 5 || Dungeon.depth == 10 || Dungeon.depth == 15 || Dungeon.depth == 20 || Dungeon.depth == 25 ||
			Dungeon.depth == 30 || Dungeon.depth == 35 || Dungeon.depth == 40 || Dungeon.depth == 45) {
			GLog.p(Messages.get(this, "quad_failed"));
		} else {
			Buff.prolong(hero, QuadDamage.class, QuadDamage.DURATION);
			Dungeon.level.seal();
		}
	}
	
	@Override
	public int value() {
		return quantity * (30 + 30);
	}
	
	public static class Recipe extends com.touhoupixel.touhoupixeldungeonreloaded.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfAttraction.class, PotionOfHisou.class};
			inQuantity = new int[]{1, 1};
			
			cost = 16;
			
			output = ElixirOfQuadDamage.class;
			outQuantity = 1;
		}
		
	}

}