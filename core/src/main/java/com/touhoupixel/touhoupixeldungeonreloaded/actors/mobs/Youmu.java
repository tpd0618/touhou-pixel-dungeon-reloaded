package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfPhilosopher;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YoumuSprite;
import com.watabou.utils.Random;

public class Youmu extends Mob {

    {
        spriteClass = YoumuSprite.class;

        HP = HT = 217;
        defenseSkill = 37;
        EXP = 20;
        maxLvl = 45;

        properties.add(Property.WARP);

        loot = new PotionOfPhilosopher();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(24, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            hero.damage(15, this);
            if (Statistics.difficulty > 2) {
                Buff.affect(enemy, Burning.class).reignite(enemy, 15f);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
            }
        }
        return damage;
    }
}