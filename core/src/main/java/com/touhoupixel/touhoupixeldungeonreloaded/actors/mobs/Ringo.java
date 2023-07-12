package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HardSearch;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RingoSprite;
import com.watabou.utils.Random;

public class Ringo extends Mob {

    {
        spriteClass = RingoSprite.class;

        HP = HT = 16;
        defenseSkill = 5;
        EXP = 2;
        maxLvl = 12;

        properties.add(Property.WARP);

        baseSpeed = 2f;

        loot = Generator.Category.ARMOR_T2;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 5);
    }

    @Override
    public int attackSkill(Char target) {
        return 10;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment){
            Statistics.power += 10;
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Vulnerable.class, Vulnerable.DURATION/5f);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, HardSearch.class, HardSearch.DURATION);
            }
        }
        return damage;
    }
}