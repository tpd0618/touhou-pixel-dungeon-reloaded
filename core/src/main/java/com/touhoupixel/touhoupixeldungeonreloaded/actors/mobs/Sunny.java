package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLiquidFlame;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SunnySprite;
import com.watabou.utils.Random;

public class Sunny extends Mob {

    {
        spriteClass = SunnySprite.class;

        HP = HT = 41;
        defenseSkill = 12;
        EXP = 3;
        maxLvl = 20;

        flying = true;

        loot = new PotionOfLiquidFlame();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(7, 10);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (Random.Int(3) == 0) {
            Buff.affect(enemy, Burning.class).reignite(enemy, 5f);
            Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
        }
        return damage;
    }
}