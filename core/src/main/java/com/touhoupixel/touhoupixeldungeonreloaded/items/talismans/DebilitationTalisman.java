package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mimic;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

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