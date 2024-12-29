package com.touhoupixel.touhoupixeldungeonreloaded.items.pots;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Hina;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Splash;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.RedButton;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Window;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndBag;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class Pot extends Item {

    public static final String AC_INSERT = "INSERT";
    public static final String AC_REMOVE = "REMOVE";

    //no turn used in this game when using pots!

    {
        image = ItemSpriteSheet.POT;
    }

    public int capacity;
    public List<Item> storedItems = new ArrayList<>();

    @Override
    public ArrayList<String> actions(Hero heroine) {
        ArrayList<String> actions = super.actions(heroine);
        if (!(storedItems.size() == capacity)) {
            actions.add(AC_INSERT);
        }
        if (!storedItems.isEmpty()) {
            actions.add(AC_REMOVE);
        }
        return actions;
    }

    public Pot() {
        super();

        Random random = new Random();
        capacity = random.nextInt(3)+3;
    }

    @Override
    public void execute(Hero heroine, String action) {
        super.execute(heroine, action);

        if (cursed){
            GLog.w(Messages.get(Hina.class, "cursed"));
        } else if (action.equals(AC_INSERT)) {
            if (storedItems.size() >= capacity) {
                Messages.get(Pot.class, "pot_full");
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
                            storedItems.add(item.detach(heroine.belongings.backpack));
                            GLog.i(Messages.get(Pot.class, "insert_success", item.name()));
                        }
                    }
                });
            }
        } else if (action.equals(AC_REMOVE)) {
            if (storedItems.isEmpty()) {
                GLog.w(Messages.get(Pot.class, "remove_empty"));
            } else {
                GameScene.show(new WndStoredItems(this));
            }
        }
    }

    @Override
    protected void onThrow( int cell ) {
        if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {

            super.onThrow( cell );

        } else  {
            if (Dungeon.level.heroFOV[cell]) {
                //shatter sounds
                Splash.at( cell, 0xffd500, 5 );
                if(!storedItems.isEmpty()) {
                    for (Item item : storedItems) {
                        Dungeon.level.drop(item, cell).sprite.drop();
                    }
                }
            } else {
                if (!storedItems.isEmpty()) {
                    for (Item item : storedItems) {
                        Dungeon.level.drop(item, cell);
                    }
                }
            }
        }
    }

    public List<Item> getStoredItems() {
        return storedItems;
    }

    public int getCapacity() {
        return capacity;
    }

    public void removeItem(Item item, Hero heroine) {
        if (storedItems.contains(item)) {
            storedItems.remove(item);
            if (Dungeon.heroine.belongings.backpack.items.size() == Dungeon.heroine.belongings.backpack.capacity()){
                item.doDrop(heroine);
            } else {
                item.collect();
            }
            GLog.i(Messages.get(Pot.class, "remove_success", item.name()));
        } else {
            GLog.w(Messages.get(Pot.class, "remove_failed", item.name()));
        }
    }

    public static class WndStoredItems extends Window {

        private static final int BTN_HEIGHT = 24;
        private static final float GAP = 4;
        private static final int MIN_WIDTH = 120;
        private static final int PADDING = 10;

        public WndStoredItems(Pot pot) {
            super();

            float pos = PADDING;
            int maxWidth = MIN_WIDTH;

            for (Item item : new ArrayList<>(pot.getStoredItems())) {
                String itemName = item.name();
                int itemWidth = Math.max(MIN_WIDTH, itemName.length() * 7 + PADDING * 2);

                maxWidth = Math.max(maxWidth, itemWidth);

                RedButton btnItem = new RedButton(itemName) {
                    @Override
                    public void onClick() {
                        pot.removeItem(item, Dungeon.heroine);
                        hide();
                        GameScene.show(new WndStoredItems(pot));
                    }
                };
                btnItem.setRect(PADDING, pos, maxWidth - PADDING * 2, BTN_HEIGHT);
                add(btnItem);

                pos = btnItem.bottom() + GAP;
            }

            resize(maxWidth, (int) pos + PADDING);
        }
    }

    @Override
    public String info() {

        String info = desc();

        info += "\n\n" + Messages.get( this, "capacity", capacity);
        info += "\n\n" + Messages.get( this, "store", capacity) + storedItems;
        info += "\n\n" + Messages.get( this, "throw_to_extract");
        return info;
    }

    private final String CAPACITY = "capacity";
    private final String STOREDITEMS = "storeditems";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(CAPACITY, capacity);
        bundle.put(STOREDITEMS, storedItems);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        capacity = bundle.getInt(CAPACITY);
        for (Bundlable item : bundle.getCollection( STOREDITEMS )) {
            if (item != null) storedItems.add((Item)item);
        }
    }

    @Override
    public boolean isIdentified() {
        return true;
    }
    public boolean isUpgradable(){ return false; }
}