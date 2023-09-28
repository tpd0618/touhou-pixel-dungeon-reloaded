package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MomijiSprite;
import com.watabou.utils.Random;

public class Momiji extends Mob {

    {
        spriteClass = MomijiSprite.class;

        HP = HT = 65;
        defenseSkill = 17;
        EXP = 8;
        maxLvl = 25;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        viewDistance = 10000;

        loot = Generator.Category.POTION;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(8, 12);
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
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Weakness.class, Weakness.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
            }
        }
        return damage;
    }
}