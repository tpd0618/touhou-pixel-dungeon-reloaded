package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.AunnSprite;
import com.watabou.utils.Random;

public class Aunn extends Mob {

    {
        spriteClass = AunnSprite.class;

        HP = HT = 29;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        properties.add(Property.YOKAI);

        loot = LifeFragment.class;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 7);
    }

    @Override
    public int attackSkill(Char target) {
        return 12;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment){
            Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
        }
        return damage;
    }
}