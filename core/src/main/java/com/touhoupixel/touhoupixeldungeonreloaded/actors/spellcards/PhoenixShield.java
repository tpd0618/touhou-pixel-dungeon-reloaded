package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FireShield;

public class PhoenixShield extends Spellcard{
    {
        left = 30;
    }

    @Override
    public void activate() {
        Buff.affect(user, FireShield.class).setShield(300);
        super.activate();
    }

    @Override
    public void deactivate() { //if the shield detaches first, it deactivates the card, and the card detaches the shield, but this is acceptable
        if (user.buff(FireShield.class) != null) user.buff(FireShield.class).detachByCard();
        super.deactivate();
    }
}
