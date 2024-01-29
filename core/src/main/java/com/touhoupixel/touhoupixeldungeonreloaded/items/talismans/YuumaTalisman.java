package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Empathetic;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Seiran;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class YuumaTalisman extends Talisman {
    {
        image = ItemSpriteSheet.YUUMA;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );
        if (ch != null) {
            if (ch instanceof Seiran && Random.Int(3) == 0) {
                for (Buff bch : curUser.buffs()) {
                    if (bch.type == Buff.buffType.POSITIVE && !curUser.properties().contains(Char.Property.MINIBOSS) && !curUser.properties().contains(Char.Property.BOSS)) {
                        bch.detach();
                        ch.HP = Math.min(ch.HP + 100000, ch.HT);
                        Dungeon.heroine.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.2f, 3);
                        Buff.prolong(ch, DoubleSpeed.class, DoubleSpeed.DURATION);
                    }
                }
                GLog.w(Messages.get(Seiran.class, "tool_reflect"));
            } else {
                for (Buff bch : ch.buffs()) {
                    if (bch.type == Buff.buffType.POSITIVE && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
                        bch.detach();
                        curUser.HP = Math.min(curUser.HP + 100000, curUser.HT);
                        Dungeon.heroine.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.2f, 3);
                        Buff.prolong(curUser, DoubleSpeed.class, DoubleSpeed.DURATION);
                    }
                }
            }
        }
    }
}