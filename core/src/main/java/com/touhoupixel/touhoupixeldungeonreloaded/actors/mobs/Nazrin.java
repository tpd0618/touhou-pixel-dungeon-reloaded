package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Gold;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.NazrinSprite;
import com.watabou.utils.Random;

public class Nazrin extends Mob {

    {
        spriteClass = NazrinSprite.class;

        HP = HT = 11;
        defenseSkill = 2;
        EXP = 1;
        maxLvl = 10;

        properties.add(Property.ANIMAL);

        loot = Gold.class;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Dungeon.gold > 200 ? Random.NormalIntRange(3, 4) :
                Random.NormalIntRange(2, 3);
    }

    @Override
    public int attackSkill(Char target) {
        return 7;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}