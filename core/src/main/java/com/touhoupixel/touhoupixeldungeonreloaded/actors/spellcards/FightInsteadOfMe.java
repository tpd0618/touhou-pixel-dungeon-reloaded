package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;

public class FightInsteadOfMe extends Spellcard{
    {
        left = 50;
    }

    @Override
    public void activate() {
        Dungeon.level.isCard_FightInsteadOfMe = true;
    }

    @Override
    protected boolean act() {
        Dungeon.level.isCard_FightInsteadOfMe = true;
        return super.act();
    }

    @Override
    public void deactivate() {
        Dungeon.level.isCard_FightInsteadOfMe = false;
        super.deactivate();
    }
}
