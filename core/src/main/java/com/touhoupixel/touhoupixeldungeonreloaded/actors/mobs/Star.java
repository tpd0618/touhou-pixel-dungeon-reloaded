package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SquareRootSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.StarSprite;
import com.watabou.utils.Random;

public class Star extends Mob {

    {
        spriteClass = StarSprite.class;

        HP = HT = 20;
        defenseSkill = 7;
        EXP = 5;
        maxLvl = 15;

        properties.add(Property.WARP);

        flying = true;

        loot = Generator.Category.HERB;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 7);
    }

    @Override
    public int attackSkill(Char target) {
        return 12;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(4) == 0) {
            Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Hex.class, Hex.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, SquareRootSnipe.class, SquareRootSnipe.DURATION);
            }
        }
        return damage;
    }
}