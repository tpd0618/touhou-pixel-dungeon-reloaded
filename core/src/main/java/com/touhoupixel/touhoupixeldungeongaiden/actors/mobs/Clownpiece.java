package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HighStress;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ClownpieceSprite;
import com.watabou.utils.Random;

public class Clownpiece extends Mob {

    {
        spriteClass = ClownpieceSprite.class;

        HP = HT = 167;
        defenseSkill = 35;
        EXP = 19;
        maxLvl = 47;

        properties.add(Property.WARP);

        loot = Generator.Category.TALISMAN;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(15, 20);
    }

    @Override
    public int attackSkill(Char target) {
        return 45;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0) {
            Buff.prolong(enemy, HighStress.class, HighStress.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
            }
        }
        return damage;
    }
}