package com.touhoupixel.touhoupixeldungeonreloaded.items.food;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class RottenFood extends Food {

	{
		image = ItemSpriteSheet.ROTTEN_RATION;
		energy = Hunger.HUNGRY/2f;
	}

	public static void effect(Hero heroine){
		switch (Random.Int(4)) {
			case 0:
				Buff.prolong(heroine, Blindness.class, Blindness.DURATION);
				break;
			case 1:
				Buff.prolong(heroine, Vertigo.class, Vertigo.DURATION);
				break;
			case 2: ;
				Buff.prolong(heroine, Paralysis.class, Paralysis.DURATION);
				break;
			case 3:
				Dungeon.heroine.exp = 0;
				break;
		}
	}

	@Override
	public int value() {
		return 10 * quantity;
	}
}