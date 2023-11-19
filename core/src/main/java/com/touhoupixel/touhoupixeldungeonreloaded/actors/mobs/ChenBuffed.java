package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.watabou.utils.Random;

public class ChenBuffed extends Chen {
    {
        HT = HP = 400;

        defenseSkill = 37;
        EXP = 15;
        maxLvl = 45;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(82, 126);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(41, 59);
    }
}