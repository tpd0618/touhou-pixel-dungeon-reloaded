package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TenshiSprite;
import com.watabou.utils.Random;

public class Tenshi extends Mob {

    {
        spriteClass = TenshiSprite.class;

        HP = HT = 244;
        defenseSkill = 40;
        EXP = 20;
        maxLvl = 47;

        properties.add(Property.WARP);

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(25, 30);
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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && !hero.flying) {
            Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.affect(enemy, Bleeding.class).set(7);
            }
            if (Statistics.difficulty > 4) {
                Buff.affect(enemy, Bleeding.class).set(7);
            }
        }
        return damage;
    }
}