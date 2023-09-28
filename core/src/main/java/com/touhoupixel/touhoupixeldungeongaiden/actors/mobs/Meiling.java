package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MeilingSprite;
import com.watabou.utils.Random;

public class Meiling extends Mob {

    {
        spriteClass = MeilingSprite.class;

        HP = HT = 54;
        defenseSkill = 15;
        EXP = 6;
        maxLvl = 22;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Generator.Category.TALISMAN;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 8);
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
                Buff.prolong(enemy, Vertigo.class, Vertigo.DURATION);
            }
        }
        return damage;
    } //see mob.attackproc to watch stat.difficulty > 4 for superdegrade
}