package com.touhoupixel.touhoupixeldungeongaiden.items.food;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

public class BurntFood extends Food {

	{
		image = ItemSpriteSheet.BURNT_RATION;
		energy = Hunger.HUNGRY/2f;
	}

	public static void effect(Hero heroine){
		switch (Random.Int( 5 )) {
			case 0:
				GLog.w( Messages.get(BurntFood.class, "str_down") );
				Dungeon.heroine.STR--;
				break;
			case 1:
				GLog.i( Messages.get(BurntFood.class, "hp_one") );
				Dungeon.heroine.HP = 1;
				break;
			case 2:
				GLog.i( Messages.get(BurntFood.class, "blind") );
				Buff.prolong(heroine, Blindness.class, Blindness.DURATION*2f);
				break;
			case 3:
				GLog.i( Messages.get(BurntFood.class, "degrade") );
				Buff.prolong(heroine, Degrade.class, Degrade.DURATION*2f);
				break;
			case 4:
				GLog.i( Messages.get(BurntFood.class, "melee_nullify") );
				Buff.prolong(heroine, MeleeNullify.class, MeleeNullify.DURATION*2f);
				break;
		}
	}

	@Override
	public int value() {
		return 10 * quantity;
	}
}