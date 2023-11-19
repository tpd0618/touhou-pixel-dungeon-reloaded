package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BlazingTrap;

public class PhoenixEye extends Spellcard{
    {
        left = 20;
    }

    @Override
    public void activate() {
        new BlazingTrap().set(user.pos).activate();
    }
}
