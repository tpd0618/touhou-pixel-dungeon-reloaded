package com.touhoupixel.touhoupixeldungeongaiden.items;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic.ExoticPotion;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndBag;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class UpgradeCard extends Item {

	private static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.UPGRADE_CARD;

		defaultAction = AC_DRINK;

		stackable = true;
		unique = true;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add(AC_DRINK);
		return actions;
	}

	@Override
	public void execute(final Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals(AC_DRINK)) {
			GameScene.selectItem(itemSelector);
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	protected WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(TenshiCard.class, "prompt");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item.isUpgradable();
		}

		@Override
		public void onSelect(Item item) {

			if (item == null) {
				GameScene.show(new WndOptions(new ItemSprite(curItem),
						Messages.titleCase(name()),
						Messages.get(UpgradeCard.class, "think"),
						Messages.get(ExoticPotion.class, "yes"),
						Messages.get(ExoticPotion.class, "no")) {
					@Override
					protected void onSelect(int index) {
						switch (index) {
							case 0:
								curUser.spendAndNext(0f);
								break;
							case 1:
								GameScene.selectItem(itemSelector);
								break;
						}
					}

					public void onBackPressed() {
					}
				});
			} else if (item != null) {

				upgrade(curUser);

				Degrade.detach(curUser, Degrade.class);

				//logic for telling the user when item properties change from upgrades
				//...yes this is rather messy
				if (item instanceof Weapon) {
					Weapon w = (Weapon) item;
					boolean wasCursed = w.cursed;
					boolean hadCursedEnchant = w.hasCurseEnchant();
					boolean hadGoodEnchant = w.hasGoodEnchant();

					w.upgrade();

					if (w.cursedKnown && hadCursedEnchant && !w.hasCurseEnchant()) {
						removeCurse(curUser);
					} else if (w.cursedKnown && wasCursed && !w.cursed) {
						weakenCurse(curUser);
					}
					if (hadGoodEnchant && !w.hasGoodEnchant()) {
						GLog.w(Messages.get(Weapon.class, "incompatible"));
					}

				} else if (item instanceof Armor) {
					Armor a = (Armor) item;
					boolean wasCursed = a.cursed;
					boolean hadCursedGlyph = a.hasCurseGlyph();
					boolean hadGoodGlyph = a.hasGoodGlyph();

					a.upgrade();

					if (a.cursedKnown && hadCursedGlyph && !a.hasCurseGlyph()) {
						removeCurse(curUser);
					} else if (a.cursedKnown && wasCursed && !a.cursed) {
						weakenCurse(curUser);
					}
					if (hadGoodGlyph && !a.hasGoodGlyph()) {
						GLog.w(Messages.get(Armor.class, "incompatible"));
					}

				} else if (item instanceof Wand || item instanceof Ring) {
					boolean wasCursed = item.cursed;

					item.upgrade();

					if (item.cursedKnown && wasCursed && !item.cursed) {
						removeCurse(curUser);
					}

				} else {
					item.upgrade();
				}
				Statistics.upgradesUsed++;
				curUser.spendAndNext(1f);

				if (Statistics.card30 && Random.Int(2) == 0) {
					curUser.HP = Math.min(curUser.HP + Dungeon.floor, curUser.HT);
					Dungeon.heroine.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.2f, 3 );
				}

				curItem.detach(curUser.belongings.backpack);
			}
		}
	};
	public static void upgrade( Hero heroine) {
		heroine.sprite.emitter().start( Speck.factory( Speck.UP ), 0.2f, 3 );
	}

	public static void weakenCurse( Hero heroine){
		GLog.p( Messages.get(UpgradeCard.class, "weaken_curse") );
		heroine.sprite.emitter().start( ShadowParticle.UP, 0.05f, 5 );
	}

	public static void removeCurse( Hero heroine){
		GLog.p( Messages.get(UpgradeCard.class, "remove_curse") );
		heroine.sprite.emitter().start( ShadowParticle.UP, 0.05f, 10 );
	}
}