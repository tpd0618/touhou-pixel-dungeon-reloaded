package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.spells.AquaBlast;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GeyserTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TakaneSprite;
import com.watabou.utils.Random;

public class Takane extends Mob {

    {
        spriteClass = TakaneSprite.class;

        HP = HT = 59;
        defenseSkill = 17;
        EXP = 11;
        maxLvl = 25;

        loot = new AquaBlast();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 11);
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
        new GeyserTrap().set(this.pos).activate();
        return damage;
    }
}