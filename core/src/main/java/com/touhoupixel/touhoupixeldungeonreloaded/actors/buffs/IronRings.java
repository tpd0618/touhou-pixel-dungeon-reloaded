package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;

public class IronRings extends FlavourBuff{
    {
        type = buffType.POSITIVE;
    }
    public static final float DURATION = 2f;

    @Override
    public int icon() {
        return BuffIndicator.IRON_RINGS;
    }

    public float iconFadePercent() {
        return Math.max(0, (DURATION - visualcooldown()) / DURATION);
    }
    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", dispTurns());
    }

}
