package com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Pure;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class OkinaTailsman extends Tailsman {
    {
        image = ItemSpriteSheet.OKINA;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS) && ch.pos == Terrain.OPEN_DOOR){
            if (Dungeon.hero.buff(Pure.class) == null) {
                ch.die(null);
            }
        }
    }
}
