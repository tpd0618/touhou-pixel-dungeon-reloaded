package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SeiranSprite;
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