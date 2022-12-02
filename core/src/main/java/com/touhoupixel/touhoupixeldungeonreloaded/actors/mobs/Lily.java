package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfYingYang;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfAntiMagic;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.LilySprite;
import com.watabou.utils.Random;

public class Lily extends Mob {

    {
        spriteClass = LilySprite.class;

        HP = HT = 22;
        defenseSkill = 7;
        EXP = 6;
        maxLvl = 15;

        flying = true;

        loot = Generator.Category.TAILSMAN;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 5);
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
            Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION);
            if (!(Dungeon.hero.heroClass == HeroClass.PLAYERSANAE) && !(Dungeon.hero.heroClass == HeroClass.PLAYERYOUMU) && !(Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA)) {
                Buff.prolong(enemy, Haste.class, Haste.DURATION);
            }
            if (Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
                Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION);
            }
        }
        return damage;
    }
}