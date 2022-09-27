package com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HighStress;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDefDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class JunkoTailsman extends Tailsman {
    {
        image = ItemSpriteSheet.JUNKO;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null){
            Buff.prolong( ch, HighStress.class, HighStress.DURATION );
        }
    }
}
