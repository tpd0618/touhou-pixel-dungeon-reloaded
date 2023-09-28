package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ExtremeHunger;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeongaiden.items.Gold;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ShionSprite;
import com.watabou.utils.Random;

public class Shion extends Mob {

    {
        spriteClass = ShionSprite.class;

        HP = HT = 75;
        defenseSkill = 20;
        EXP = 11;
        maxLvl = 27;

        properties.add(Property.GOD);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Gold.class;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(9, 14);
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.prolong(enemy, ExtremeHunger.class, ExtremeHunger.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Silence.class, Silence.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
            }
            return damage;
        }
        return damage;
    }
}