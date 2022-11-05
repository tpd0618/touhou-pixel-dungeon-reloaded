package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AliceSprite;
import com.watabou.utils.Random;

public class Alice extends Mob {

    {
        spriteClass = AliceSprite.class;

        HP = HT = 31;
        defenseSkill = 10;
        EXP = 7;
        maxLvl = 17;

        loot = new PotionOfHealing();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 8);
    }

    @Override
    public int attackSkill(Char target) {
        return 15;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(5) == 0) {
            new SummoningTrap().set(pos).activate();
        }
        return damage;
    }
}