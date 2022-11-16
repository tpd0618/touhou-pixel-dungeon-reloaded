package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.spells.ReclaimTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TewiSprite;
import com.watabou.utils.Random;

public class Tewi extends Mob {

    {
        spriteClass = TewiSprite.class;

        HP = HT = 45;
        defenseSkill = 12;
        EXP = 9;
        maxLvl = 20;

        loot = new ReclaimTrap();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 6);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
    //will replace soon
}