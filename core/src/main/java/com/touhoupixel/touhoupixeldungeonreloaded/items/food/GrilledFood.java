package com.touhoupixel.touhoupixeldungeonreloaded.items.food;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class GrilledFood extends Food {

	{
		image = ItemSpriteSheet.GRILLED_RATION;
		energy = Hunger.HUNGRY/2f;
	}

	public static void effect(Hero heroine){
		if (heroine.buff(Inversion.class) != null) {
			heroine.damage(heroine.HT / 2, heroine);
			if (heroine == Dungeon.heroine && !heroine.isAlive()) {
				Dungeon.fail(Inversion.class);
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