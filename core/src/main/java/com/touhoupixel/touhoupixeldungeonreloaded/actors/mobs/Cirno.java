package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfSnapFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CirnoSprite;
import com.watabou.utils.Random;

public class Cirno extends Mob {

    {
        spriteClass = CirnoSprite.class;

        HP = HT = 45;
        defenseSkill = 12;
        EXP = 4;
        maxLvl = 20;

        flying = true;

        loot = new PotionOfSnapFreeze();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(8, 11);
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
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(2) == 0) {
            Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
        }
        return damage;
    }
}