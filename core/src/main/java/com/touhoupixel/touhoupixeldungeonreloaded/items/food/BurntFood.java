package com.touhoupixel.touhoupixeldungeonreloaded.items.food;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Barkskin;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WellFed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

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