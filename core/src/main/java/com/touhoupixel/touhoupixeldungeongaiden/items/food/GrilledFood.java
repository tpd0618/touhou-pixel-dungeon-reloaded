package com.touhoupixel.touhoupixeldungeongaiden.items.food;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.InversionTrap;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;

public class GrilledFood extends Food {

	{
		image = ItemSpriteSheet.GRILLED_RATION;
		energy = Hunger.HUNGRY/2f;
	}

	public static void effect(Hero heroine){
		if (heroine.buff(Inversion.class) != null) {
			heroine.damage(heroine.HT / 2, heroine);
			if (heroine == Dungeon.heroine && !heroine.isAlive()) {
				Dungeon.fail(InversionTrap.class);
				GLog.n( Messages.get(Inversion.class, "ondeath") );
			}
		} else {
			heroine.HP = Math.min(heroine.HP + 50, heroine.HT);
			GLog.p(Messages.get(GrilledFood.class, "grilled_food"));
		}
	}

	@Override
	public int value() {
		return 10 * quantity;
	}
}