package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class KanakoBuffed extends Kanako {
    public int attackProc(Char enemy, int damage) {
        if (enemy.equals(Dungeon.heroine) && Random.Int(5) != 0){ //Can reduce the strength twice
            GLog.n(Messages.get(Kanako.class, "strength_reduced"));
            Dungeon.heroine.STR--;
        }
        return super.attackProc(enemy, damage);
    }
}
