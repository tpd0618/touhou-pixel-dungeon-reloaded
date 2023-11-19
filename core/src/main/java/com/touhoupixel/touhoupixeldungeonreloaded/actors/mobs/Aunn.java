package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Empathetic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AunnSprite;
import com.watabou.utils.Random;

public class Aunn extends Mob {

    {
        spriteClass = AunnSprite.class;

        HP = HT = 36;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        properties.add(Property.YOKAI);

        loot = LifeFragment.class;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(18, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 12;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(3, 5);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            Buff.prolong(enemy, Empathetic.class, Empathetic.DURATION/5f);
        }
        return damage;
    }
}