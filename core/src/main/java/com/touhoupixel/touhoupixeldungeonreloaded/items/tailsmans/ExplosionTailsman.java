package com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Pure;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class ExplosionTailsman extends Tailsman {
    {
        image = ItemSpriteSheet.EXPLOSION;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null){
            if (Dungeon.hero.buff(Pure.class) == null) {
                new ExplosiveTrap().set(cell).activate();
            }
        }
    }
}