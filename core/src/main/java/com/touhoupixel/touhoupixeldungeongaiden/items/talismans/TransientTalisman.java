package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeongaiden.levels.rooms.special.SentryRoom;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class TransientTalisman extends Talisman {
    {
        image = ItemSpriteSheet.TRANSIENT;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !(ch instanceof SentryRoom.Sentry) && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
            if (ch != Dungeon.heroine) {
                if (Actor.findChar(Dungeon.level.exit()) == null) {
                    ScrollOfTeleportation.teleportToLocation(ch, Dungeon.level.exit());
                    Buff.prolong(ch, Paralysis.class, Paralysis.DURATION * 10f);
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