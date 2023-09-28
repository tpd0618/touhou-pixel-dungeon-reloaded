package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SeiranSprite;
import com.watabou.utils.Random;

public class Seiran extends Mob {

    {
        spriteClass = SeiranSprite.class;

        HP = HT = 269;
        defenseSkill = 37;
        EXP = 20;
        maxLvl = 50;

        properties.add(Property.WARP);

        loot = Generator.Category.HERB;
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(30, 34);
    }

    @Override
    public int attackSkill(Char target) {
        return 47;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        if (Dungeon.heroine.belongings.weapon() instanceof MeleeWeapon) {
            Buff.prolong(enemy, Inversion.class, Inversion.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, CursedBlow.class, CursedBlow.DURATION);
            }
        }
        return super.defenseProc(enemy, damage);
    }
}