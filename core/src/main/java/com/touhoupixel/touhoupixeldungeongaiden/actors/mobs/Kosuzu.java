package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KosuzuSprite;
import com.watabou.utils.Random;

public class Kosuzu extends Mob {

    {
        spriteClass = KosuzuSprite.class;

        HP = HT = 15;
        defenseSkill = 5;
        EXP = 2;
        maxLvl = 12;

        properties.add(Property.HUMAN);

        loot = new LifeFragment();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 4);
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
        if (Statistics.upgradesUsed < 2) {
            Buff.affect(enemy, Burning.class).reignite(enemy, 3f);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Inversion.class, Inversion.DURATION/3f);
            }
        }
        return damage;
    }
}