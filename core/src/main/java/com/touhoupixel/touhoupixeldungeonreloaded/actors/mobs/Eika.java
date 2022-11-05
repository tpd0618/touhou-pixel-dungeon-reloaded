package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Gold;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfParalyticGas;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.RockfallTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.EikaSprite;
import com.watabou.utils.Random;

public class Eika extends Mob {

    {
        spriteClass = EikaSprite.class;

        HP = HT = 28;
        defenseSkill = 10;
        EXP = 8;
        maxLvl = 17;

        loot = Gold.class;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 7);
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
        if (Random.Int(4) == 0) {
            new RockfallTrap().set(pos).activate();
        }
        return damage;
    }
}