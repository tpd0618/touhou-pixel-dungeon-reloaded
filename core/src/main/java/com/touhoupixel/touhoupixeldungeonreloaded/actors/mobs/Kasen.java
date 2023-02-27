package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiSneakattack;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SquareRootSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.EnchantEraseTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KasenSprite;
import com.watabou.utils.Random;

public class Kasen extends Mob {

    {
        spriteClass = KasenSprite.class;

        HP = HT = 70;
        defenseSkill = 20;
        EXP = 12;
        maxLvl = 27;

        properties.add(Property.WARP);

        loot = Generator.Category.ARMOR_T5;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 16);
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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            new EnchantEraseTrap().set(enemy.pos).activate();
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, SquareRootSnipe.class, SquareRootSnipe.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, HeavenSpeed.class, HeavenSpeed.DURATION);
            }
        }
        return damage;
    }
}