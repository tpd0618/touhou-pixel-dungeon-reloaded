package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Pure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class KameTalisman extends Talisman {
    {
        image = ItemSpriteSheet.KAME;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            Buff.prolong(ch, Slow.class, Slow.DURATION);
        }
    }
}