package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KoakumaSprite;
import com.watabou.utils.Random;

public class Koakuma extends Mob {

    {
        spriteClass = KoakumaSprite.class;

        HP = HT = 48;
        defenseSkill = 15;
        EXP = 9;
        maxLvl = 22;

        baseSpeed = 2f;

        loot = Generator.Category.SCROLL;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(1, 3);
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //see Mob.attackproc
}