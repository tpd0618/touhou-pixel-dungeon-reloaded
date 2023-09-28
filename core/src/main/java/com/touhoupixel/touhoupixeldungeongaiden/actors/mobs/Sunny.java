package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HardSearch;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SunnySprite;
import com.watabou.utils.Random;

public class Sunny extends Mob {

    {
        spriteClass = SunnySprite.class;

        HP = HT = 8;
        defenseSkill = 5;
        EXP = 2;
        maxLvl = 12;

        properties.add(Property.WARP);

        flying = true;

        loot = Generator.Category.POTION;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 6);
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
        if (Random.Int(3) == 0) {
            Buff.affect(enemy, Burning.class).reignite(enemy, 2f);
            Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Weakness.class, Weakness.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, HardSearch.class, HardSearch.DURATION);
            }
        }
        return damage;
    }
}