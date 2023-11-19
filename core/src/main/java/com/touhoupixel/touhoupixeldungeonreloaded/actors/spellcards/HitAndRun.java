package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;

public class HitAndRun extends Spellcard{
    {
        left = 20;
    }
    @Override
    public void activate(){
        Buff.prolong(user, Haste.class, Haste.DURATION);
    }

}
