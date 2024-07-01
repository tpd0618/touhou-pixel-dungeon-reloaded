package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Chill;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Randomizer;
import com.watabou.utils.Random;

public class Transmutation extends Spellcard {
    {
        left = 2;
    }

    @Override
    public void activate(){
        Char enemy = user.getEnemy();
        int numOfDebuff = Random.Int(7);
        int limitOfDebuff = 4;

        for (Buff c : enemy.buffs()) {
            if (c.type == Buff.buffType.POSITIVE) {
                c.detach();
                switch (numOfDebuff){
                    case 0:
                        Buff.prolong(enemy, Randomizer.class, 15f);
                        break;
                    case 1:
                        Buff.affect(enemy, Poison.class).set();
                        break;
                    case 2:
                        Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
                        break;
                    case 3:
                        Buff.prolong(enemy, Chill.class, Chill.DURATION);
                        break;
                    case 4:
                        Buff.prolong(enemy, HeavenSpeed.class, 15f);
                        break;
                    case 5:
                        Buff.prolong(enemy, Onigiri.class, 30f);
                        break;
                    default:
                        Buff.prolong(enemy, Inversion.class, 20f);
                        break;
                }
                limitOfDebuff--;
                numOfDebuff++;
                if (limitOfDebuff == 0) break;
                if (numOfDebuff > 7) numOfDebuff = 0;
            }
        }
    }
}
