package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mimic;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class DebilitationTalisman extends Talisman {
    {
        image = ItemSpriteSheet.DEBILITATION;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !(ch instanceof Mimic) && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            Buff.prolong(ch, MeleeNullify.class, MeleeNullify.DURATION);
        }
    }
}