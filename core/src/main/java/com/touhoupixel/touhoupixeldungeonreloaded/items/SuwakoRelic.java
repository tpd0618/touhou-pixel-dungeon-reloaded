package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

import java.util.ArrayList;

public class SuwakoRelic extends Item {
    private static final String AC_ACTIVATE = "ACTIVATE";
    {
        image = ItemSpriteSheet.SUWAKO_RELIC;

        defaultAction = AC_ACTIVATE;

        stackable = true;
        unique = true;
    }
    public ArrayList<String> actions(Hero heroine) {
        ArrayList<String> actions = super.actions(heroine);
        actions.add(AC_ACTIVATE);
        return actions;
    }
    @Override
    public void execute(final Hero heroine, String action) {

        super.execute(heroine, action);

        if (action.equals(AC_ACTIVATE)) {
            if (!Statistics.suwakorelic_active){
                Statistics.suwakorelic_active = true;
                GLog.p(Messages.get(this, "success"));
                curUser.spendAndNext(1f);
                curItem.detach(curUser.belongings.backpack);
            }
            else{
                GLog.w(Messages.get(this, "fail"));
            }
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
    @Override
    public int value() {
        return 300 * quantity;
    }
}
