package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SakuyaDaggerTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SakuyaSprite;
import com.watabou.utils.Random;

public class Sakuya extends Mob {

    {
        spriteClass = SakuyaSprite.class;

        HP = HT = 40;
        defenseSkill = 15;
        EXP = 10;
        maxLvl = 22;

        baseSpeed = 3f;

        loot = new LifeFragment();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 10);
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (Random.Int(5) == 0) {
            new SakuyaDaggerTrap().set(enemy.pos).activate();
        }
        return damage;
    }
}