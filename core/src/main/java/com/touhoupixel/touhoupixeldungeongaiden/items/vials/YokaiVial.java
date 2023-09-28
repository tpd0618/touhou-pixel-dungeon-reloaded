package com.touhoupixel.touhoupixeldungeongaiden.items.vials;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class YokaiVial extends Vial {
    {
        image = ItemSpriteSheet.YOKAI_VIAL;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null){
            if (ch.properties().contains(Char.Property.YOKAI)) {
                ch.damage(3*(Dungeon.heroine.lvl+20), this);
                Buff.affect(curUser, Swiftthistle.TimeBubble.class);
                Buff.prolong(curUser, Might.class, Might.DURATION);
            } else {
                ch.damage(Dungeon.heroine.lvl+20, this);
            }
        }
    }
}