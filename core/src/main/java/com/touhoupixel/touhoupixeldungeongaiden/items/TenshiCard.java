package com.touhoupixel.touhoupixeldungeongaiden.items;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic.ExoticPotion;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndBag;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class TenshiCard extends Item {

    private static final String AC_DRINK = "DRINK";

    {
        image = ItemSpriteSheet.TENSHI_CARD;

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
            return
                    (item instanceof MeleeWeapon && !((MeleeWeapon) item).masteryPotionBonus)
                            || (item instanceof Armor && !((Armor) item).masteryPotionBonus);
        }

        @Override
        public void onSelect(Item item) {

            if (item == null) {
                GameScene.show(new WndOptions(new ItemSprite(curItem),
                        Messages.titleCase(name()),
                        Messages.get(TenshiCard.class, "think"),
                        Messages.get(ExoticPotion.class, "yes"),
                        Messages.get(ExoticPotion.class, "no")) {
                    @Override
                    protected void onSelect(int index) {
                        switch (index) {
                            case 0:
                                curUser.spendAndNext(1f);
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

                if (item instanceof Weapon) {
                    ((Weapon) item).masteryPotionBonus = true;
                    GLog.p(Messages.get(TenshiCard.class, "weapon_easier"));
                } else if (item instanceof Armor) {
                    ((Armor) item).masteryPotionBonus = true;
                    GLog.p(Messages.get(TenshiCard.class, "armor_easier"));
                }
                updateQuickslot();

                Sample.INSTANCE.play(Assets.Sounds.DRINK);
                curUser.sprite.operate(curUser.pos);
                curItem.detach(curUser.belongings.backpack);
            }
        }
    };

    @Override
    public int value() {
        return 70 * quantity;
    }
}