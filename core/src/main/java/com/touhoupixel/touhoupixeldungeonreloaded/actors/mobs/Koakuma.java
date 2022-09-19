package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragement;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KoakumaSprite;
import com.watabou.utils.Random;

public class Koakuma extends Mob {

    {
        spriteClass = KoakumaSprite.class;

        HP = HT = 124;
        defenseSkill = 27;
        EXP = 6;
        maxLvl = 35;

        baseSpeed = 2f;

        loot = new SpellcardFragement();
        lootChance = 0.04f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(18, 22);
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //see Mob.attackproc
}