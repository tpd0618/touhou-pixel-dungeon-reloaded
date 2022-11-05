package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MeilingSprite;
import com.watabou.utils.Random;

public class Meiling extends Mob {

    {
        spriteClass = MeilingSprite.class;

        HP = HT = 54;
        defenseSkill = 15;
        EXP = 9;
        maxLvl = 22;

        loot = Generator.Category.TAILSMAN;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 9);
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