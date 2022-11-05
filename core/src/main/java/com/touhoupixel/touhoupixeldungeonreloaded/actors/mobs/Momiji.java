package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MomijiSprite;
import com.watabou.utils.Random;

public class Momiji extends Mob {

    {
        spriteClass = MomijiSprite.class;

        HP = HT = 65;
        defenseSkill = 17;
        EXP = 11;
        maxLvl = 25;

        viewDistance = 10000;

        loot = Generator.Category.POTION;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 11);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //viewdistance 10000 is the special ability
}