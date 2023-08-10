package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CorrosionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlockTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.DoremySprite;
import com.watabou.utils.Random;

public class Doremy extends Mob {

    {
        spriteClass = DoremySprite.class;

        HP = HT = 278;
        defenseSkill = 37;
        EXP = 21;
        maxLvl = 50;

        properties.add(Property.ANIMAL);

        loot = Generator.Category.POTION;
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(32, 38);
    }

    @Override
    public int attackSkill(Char target) {
        return 47;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            new FlockTrap().set(target).activate();
            if (Statistics.difficulty > 2) {
                new CorrosionTrap().set(target).activate();
            }
            if (Statistics.difficulty > 4) {
                new CursingTrap().set(target).activate();
            }
        }
        return damage;
    }
}