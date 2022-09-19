package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MeilingSprite;
import com.watabou.utils.Random;

public class Meiling extends Mob {

    {
        spriteClass = MeilingSprite.class;

        HP = HT = 112;
        defenseSkill = 27;
        EXP = 6;
        maxLvl = 35;

        loot = new PotionOfMight();
        lootChance = 0.04f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(17, 20);
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