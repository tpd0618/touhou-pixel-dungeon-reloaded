package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.LarvaSprite;
import com.watabou.utils.Random;

public class Larva extends Mob {

    {
        spriteClass = LarvaSprite.class;

        HP = HT = 8;
        defenseSkill = 2;
        EXP = 1;
        maxLvl = 10;

        flying = true;

        properties.add(Property.WARP);

        loot = Generator.Category.SCROLL;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(2, 5);
    }

    @Override
    public int attackSkill(Char target) {
        return 7;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            Buff.prolong(enemy, Vertigo.class, Vertigo.DURATION/2f);
        }
        return damage;
    }
}