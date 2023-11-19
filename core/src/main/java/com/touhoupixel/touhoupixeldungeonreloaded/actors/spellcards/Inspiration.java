package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Barkskin;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FloatSlayer;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Healing;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AlarmTrap;

public class Inspiration extends Spellcard{
    {
        left = 12;
    }

    @Override
    public void activate(){
        Char enemy = user.getEnemy();
        new AlarmTrap().set(enemy.pos).activate();
    }
    @Override
    public boolean act(){
        Buff.prolong(user, DoubleSpeed.class, 3f);
        Buff.prolong(user, Haste.class, 3f);
        Buff.prolong(user, SuperHard.class, 3f);
        Buff.prolong(user, Doublerainbow.class, 3f);
        Buff.prolong(user, Might.class, 3f);
        Buff.prolong(user, Hisou.class, 3f);
        Buff.prolong(user, FloatSlayer.class, 3f);
        Buff.prolong(user, Bless.class, 3f);
        Buff.affect(user, Barkskin.class).set(user.HT/4, 3);
        Buff.affect(user, Healing.class).setHeal((int)(user.HT*0.8), 0.5f, 0);
        return super.act();
    }
}
