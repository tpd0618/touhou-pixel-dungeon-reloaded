package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlashingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HatateSprite;
import com.watabou.utils.Random;

public class Hatate extends Mob {

    {
        spriteClass = HatateSprite.class;

        HP = HT = 62;
        defenseSkill = 17;
        EXP = 12;
        maxLvl = 25;

        flying = true;

        loot = Generator.Category.SCROLL;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(7, 10);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            new FlashingTrap().set(enemy.pos).activate();
        }
        return damage;
    }
}