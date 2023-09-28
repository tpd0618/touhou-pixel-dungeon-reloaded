package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Freezing;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class CirnoTalisman extends Talisman {
    {
        image = ItemSpriteSheet.CIRNO;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            Freezing.affect( ch.pos );
        }
    }
}