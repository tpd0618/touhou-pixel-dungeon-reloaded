package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragement;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MikoSprite;
import com.watabou.utils.Random;

public class Miko extends Mob {

    {
        spriteClass = MikoSprite.class;

        HP = HT = 191;
        defenseSkill = 42;
        EXP = 9;
        maxLvl = 50;

        loot = new LifeFragement();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(22, 28);
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
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION/2f);
        return damage;
    }
}