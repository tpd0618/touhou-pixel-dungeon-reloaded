package com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Adrenaline;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicImmune;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.NitoriKeyPower;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDefDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Stamina;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Triplespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YuukaRage;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class FogpurgeTailsman extends Tailsman {
    {
        image = ItemSpriteSheet.FOGPURGE;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null){
            Buff.detach(ch, Adrenaline.class);
            Buff.detach(ch, Bless.class);
            Buff.detach(ch, Doublerainbow.class);
            Buff.detach(ch, Might.class);
            Buff.detach(ch, Hisou.class);
            Buff.detach(ch, Doublespeed.class);
            Buff.detach(ch, NitoriKeyPower.class);
            Buff.detach(ch, Triplespeed.class);
            Buff.detach(ch, Haste.class);
            Buff.detach(ch, Stamina.class);
            Buff.detach(ch, MagicImmune.class);
            Buff.detach(ch, ReBirth.class);
            Buff.detach(ch, YuukaRage.class);
            Buff.detach(ch, OneDefDamage.class);
            Buff.detach(ch, AnkhInvulnerability.class);
        }
    }
}