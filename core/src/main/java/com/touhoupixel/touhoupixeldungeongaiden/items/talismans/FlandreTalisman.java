package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class FlandreTalisman extends Talisman {
    {
        image = ItemSpriteSheet.FLANDRE;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            ch.damage(ch.HT / 4, this);
            curUser.HP = Math.min(curUser.HP + 50, curUser.HT);
        }
    }
}