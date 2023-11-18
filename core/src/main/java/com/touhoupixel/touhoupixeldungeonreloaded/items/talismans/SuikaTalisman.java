package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Empathetic;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class SuikaTalisman extends Talisman {
    {
        image = ItemSpriteSheet.SUIKA;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            Buff.prolong(ch, Empathetic.class, Empathetic.DURATION);
        }
    }
}