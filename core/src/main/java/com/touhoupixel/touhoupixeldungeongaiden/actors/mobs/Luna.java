package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.LunaSprite;
import com.watabou.utils.Random;

public class Luna extends Mob {

    {
        spriteClass = LunaSprite.class;

        HP = HT = 24;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        flying = true;

        properties.add(Property.WARP);

        loot = Generator.Category.SCROLL;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 8);
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
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(4) == 0) {
            Buff.prolong(enemy, Silence.class, Silence.DURATION / 2f);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Vulnerable.class, Vulnerable.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, RegenBlock.class, RegenBlock.DURATION);
            }
        }
        return damage;
    }
}