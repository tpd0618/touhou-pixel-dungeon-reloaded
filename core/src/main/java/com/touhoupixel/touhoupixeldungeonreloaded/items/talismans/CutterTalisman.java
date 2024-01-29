package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Freezing;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Seiran;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class CutterTalisman extends Talisman {
    {
        image = ItemSpriteSheet.CUTTER;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS) && ch.HP > 3) {
            if (ch instanceof Seiran && Random.Int(3) == 0){
                curUser.HP /= 2;
                GLog.w(Messages.get(Seiran.class, "tool_reflect"));
            } else {
                ch.HP /= 2;
            }
        }
    }
}