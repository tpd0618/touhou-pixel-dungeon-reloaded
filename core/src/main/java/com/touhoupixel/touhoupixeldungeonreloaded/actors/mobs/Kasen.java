package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DestroyArmorTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KasenSprite;
import com.watabou.utils.Random;

public class Kasen extends Mob {

    {
        spriteClass = KasenSprite.class;

        HP = HT = 70;
        defenseSkill = 20;
        EXP = 12;
        maxLvl = 27;

        loot = Generator.Category.ARMOR_T5;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(10, 13);
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
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