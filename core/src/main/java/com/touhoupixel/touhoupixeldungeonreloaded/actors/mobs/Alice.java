package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AliceSprite;
import com.watabou.utils.Random;

public class Alice extends Mob {

    {
        spriteClass = AliceSprite.class;

        HP = HT = 67;
        defenseSkill = 17;
        EXP = 4;
        maxLvl = 25;

        loot = new PotionOfHealing();
        lootChance = 0.125f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(9, 13);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
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