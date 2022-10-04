package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfTriplespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.watabou.utils.Random;

public class Aya extends Mob {

    {
        spriteClass = AyaSprite.class;

        HP = HT = 131;
        defenseSkill = 32;
        EXP = 12;
        maxLvl = 40;

        baseSpeed = 5f;

        flying = true;

        loot = new PotionOfTriplespeed();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(24, 29);
    }

    @Override
    public int attackSkill(Char target) {
        return 37;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //basespeed 5f is the special ability
}