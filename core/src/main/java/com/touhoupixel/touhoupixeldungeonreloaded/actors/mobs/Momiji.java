package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MomijiSprite;
import com.watabou.utils.Random;

public class Momiji extends Mob {

    {
        spriteClass = MomijiSprite.class;

        HP = HT = 147;
        defenseSkill = 32;
        EXP = 6;
        maxLvl = 40;

        viewDistance = 10000;

        loot = new PotionOfMindVision();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(26, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 37;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //viewdistance 10000 is the special ability
}