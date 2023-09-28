package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HardSearch;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.LilySprite;
import com.watabou.utils.Random;

public class Lily extends Mob {

    {
        spriteClass = LilySprite.class;

        HP = HT = 22;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        flying = true;

        properties.add(Property.WARP);

        loot = Generator.Category.TALISMAN;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 6);
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
            Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
            Buff.prolong(enemy, HardSearch.class, HardSearch.DURATION);
            if (!(Statistics.difficulty > 2)) {
                Buff.prolong(enemy, Haste.class, Haste.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION);
            }
        }
        return damage;
    }
}