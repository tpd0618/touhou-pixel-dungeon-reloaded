package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mimic;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Seiran;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class DebilitationTalisman extends Talisman {
    {
        image = ItemSpriteSheet.DEBILITATION;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !(ch instanceof Mimic) && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            if (ch instanceof Seiran && Random.Int(3) == 0){
                Buff.prolong(curUser, MeleeNullify.class, MeleeNullify.DURATION);
                GLog.w(Messages.get(Seiran.class, "tool_reflect"));
            } else {
                Buff.prolong(ch, MeleeNullify.class, MeleeNullify.DURATION);
            }
        }
    }
}