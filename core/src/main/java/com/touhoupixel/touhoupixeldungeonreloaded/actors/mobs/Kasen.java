package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DestroyArmorTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KasenSprite;
import com.watabou.utils.Random;

public class Kasen extends Mob {

    {
        spriteClass = KasenSprite.class;

        HP = HT = 173;
        defenseSkill = 37;
        EXP = 8;
        maxLvl = 45;

        loot = new PotionOfMight();
        lootChance = 0.125f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(27, 32);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            new DestroyArmorTrap().set(target).activate();
        }
        return damage;
    }
}