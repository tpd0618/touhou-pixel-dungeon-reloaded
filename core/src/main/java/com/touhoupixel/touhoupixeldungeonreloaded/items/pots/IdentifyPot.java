package com.touhoupixel.touhoupixeldungeonreloaded.items.pots;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Hina;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Identification;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.IronKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.Talisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Notes;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Plant;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndBag;

import java.util.ArrayList;
import java.util.Random;

public class IdentifyPot extends Pot {

	{
		Random random = new Random();
		capacity = random.nextInt(3)+3;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.remove(AC_REMOVE);
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action) {
		super.execute(heroine, action);

		if (cursed){
			GLog.w(Messages.get(Hina.class, "cursed"));
		} else if (action.equals(AC_INSERT)) {
			if (storedItems.size() >= capacity) {
				GLog.w(Messages.get(Pot.class, "pot_full"));
			} else {
				GameScene.selectItem(new WndBag.ItemSelector() {
					@Override
					public String textPrompt() {
						return Messages.get(Pot.class, "insert_prompt");
					}

					@Override
					public boolean itemSelectable(Item item) {
						return !(item instanceof Pot);
					}

					@Override
					public void onSelect(Item item) {
						if (item != null) {
							item.identify();

							storedItems.add(item.detach(heroine.belongings.backpack));
							GLog.i(Messages.get(Pot.class, "insert_success", item.name()));
						}
					}
				});
			}
		}
	}
}