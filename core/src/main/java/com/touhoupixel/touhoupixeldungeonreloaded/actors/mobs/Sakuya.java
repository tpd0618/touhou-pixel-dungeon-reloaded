package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FutureSight;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs.ElixirOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SakuyaSprite;
import com.watabou.utils.Random;

public class Sakuya extends Mob {

    {
        spriteClass = SakuyaSprite.class;

        HP = HT = 107;
        defenseSkill = 15;
        EXP = 7;
        maxLvl = 22;

        properties.add(Property.HUMAN);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new LifeFragment();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(31, 49);
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(13, 21);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Dungeon.heroine.buff(FutureSight.class) == null && Random.Int(3) == 0) {
            Buff.prolong(enemy, FutureSight.class, FutureSight.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(this, Haste.class, Haste.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
            }
        }
        return damage;
    }
}