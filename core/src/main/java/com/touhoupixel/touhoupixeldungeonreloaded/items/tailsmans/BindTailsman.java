package com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Pure;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class BindTailsman extends Tailsman {
    {
        image = ItemSpriteSheet.BIND;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null){
            if (Dungeon.hero.buff(Pure.class) == null) {
                Buff.prolong(ch, Paralysis.class, Paralysis.DURATION);
            }
        }
    }
}