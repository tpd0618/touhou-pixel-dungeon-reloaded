package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inaccurate;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class InaccurateTalisman extends Talisman {
    {
        image = ItemSpriteSheet.INACCURATE;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
            Buff.prolong(ch, Inaccurate.class, Inaccurate.DURATION);
        }
    }
}