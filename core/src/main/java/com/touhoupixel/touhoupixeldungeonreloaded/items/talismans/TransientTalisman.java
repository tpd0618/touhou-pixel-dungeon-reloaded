package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Empathetic;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Seiran;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special.SentryRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class TransientTalisman extends Talisman {
    {
        image = ItemSpriteSheet.TRANSIENT;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !(ch instanceof SentryRoom.Sentry) && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
            if (ch instanceof Seiran && Random.Int(3) == 0) {
                if (curUser == Dungeon.heroine) {
                    if (Actor.findChar(Dungeon.level.exit()) == null) {
                        ScrollOfTeleportation.teleportToLocation(curUser, Dungeon.level.exit());
                        Buff.prolong(curUser, Paralysis.class, Paralysis.DURATION * 10f);
                    }
                }
                GLog.w(Messages.get(Seiran.class, "tool_reflect"));
            } else {
                if (ch != Dungeon.heroine) {
                    if (Actor.findChar(Dungeon.level.exit()) == null) {
                        ScrollOfTeleportation.teleportToLocation(ch, Dungeon.level.exit());
                        Buff.prolong(ch, Paralysis.class, Paralysis.DURATION * 10f);
                    }
                }
            }
        }
    }

    @Override
    public String info() {

        String info = desc();

        if (Actor.findChar(Dungeon.level.exit()) != null) {
            info += "\n\n" + Messages.get(this, "feel");
        }
        return info;
    }
}