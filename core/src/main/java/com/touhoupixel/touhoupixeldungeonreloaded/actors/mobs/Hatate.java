package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlashingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HatateSprite;
import com.watabou.utils.Random;

public class Hatate extends Mob {

    {
        spriteClass = HatateSprite.class;

        HP = HT = 131;
        defenseSkill = 32;
        EXP = 7;
        maxLvl = 40;

        flying = true;

        loot = Generator.Category.SCROLL;
        lootChance = 0.15f;
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

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(2) == 0) {
            new FlashingTrap().set(enemy.pos).activate();
        }
        return damage;
    }
}