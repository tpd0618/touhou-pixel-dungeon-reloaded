package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperDegrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.ExoticPotion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndBag;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class NitoDismantleHammer extends Item {

    {
        image = ItemSpriteSheet.KOGASA_HAMMER;

        defaultAction = AC_DISMANTLE;

        stackable = true;
        unique = true;
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.remove(AC_DROP);
        actions.remove(AC_THROW);
        actions.add(AC_DISMANTLE);
        return actions;
    }

    @Override
    public void execute(final Hero hero, String action) {

        super.execute(hero, action);

        if (action.equals(AC_DISMANTLE)) {
            if (hero.buff(Degrade.class) != null || hero.buff(SuperDegrade.class) != null) {
                GLog.i( Messages.get(this, "degrade") );
            } else GameScene.selectItem(itemSelector);
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
            return Messages.get(NitoDismantleHammer.class, "prompt");
        }

        @Override
        public boolean itemSelectable(Item item) {
            return item instanceof MeleeWeapon && item.level() > 0 && !item.isEquipped(curUser) && item.isIdentified() ||
                    item instanceof Armor && ((Armor) item).checkSeal() == null && item.level() > 0 && !item.isEquipped(curUser) && item.isIdentified();
        }

        @Override
        public void onSelect(Item item) {

            if (item == null) {
                GameScene.show(new WndOptions(new ItemSprite(curItem),
                        Messages.titleCase(name()),
                        Messages.get(NitoDismantleHammer.class, "think"),
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
            } else {
                item.detach(curUser.belongings.backpack);
                Dungeon.level.drop(new UpgradeCard().quantity(item.level()), curUser.pos).sprite.drop();
                if (Dungeon.isChallenged(Challenges.INVINCIBLE_GENSOKYO)) {
                    Statistics.mood += 1;
                }
                updateQuickslot();

                Sample.INSTANCE.play(Assets.Sounds.DRINK);
                curUser.sprite.operate(curUser.pos);
            }
        }
    };
}