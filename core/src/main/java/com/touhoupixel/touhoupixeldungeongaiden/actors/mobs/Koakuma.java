package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KoakumaSprite;
import com.watabou.utils.Random;

public class Koakuma extends Mob {

    {
        spriteClass = KoakumaSprite.class;

        HP = HT = 37;
        defenseSkill = 15;
        EXP = 6;
        maxLvl = 22;

        properties.add(Property.WARP);

        loot = Generator.Category.SCROLL;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Statistics.upgradesUsed/2, 1+Statistics.upgradesUsed/2);
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
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, RegenBlock.class, RegenBlock.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Inversion.class, Inversion.DURATION /5f);
            }
        }
        return damage;
    }
}