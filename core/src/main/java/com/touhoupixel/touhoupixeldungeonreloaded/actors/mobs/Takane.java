package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GeyserTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TakaneSprite;
import com.watabou.utils.Random;

public class Takane extends Mob {

    {
        spriteClass = TakaneSprite.class;

        HP = HT = 124;
        defenseSkill = 32;
        EXP = 6;
        maxLvl = 40;

        loot = Generator.Category.POTION;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(22, 27);
    }

    @Override
    public int attackSkill(Char target) {
        return 37;
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