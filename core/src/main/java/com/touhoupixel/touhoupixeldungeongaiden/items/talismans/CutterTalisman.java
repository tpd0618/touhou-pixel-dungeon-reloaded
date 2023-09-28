package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class CutterTalisman extends Talisman {
    {
        image = ItemSpriteSheet.CUTTER;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS) && ch.HP > 3) {
            ch.HP /= 2;
        }
    }
}