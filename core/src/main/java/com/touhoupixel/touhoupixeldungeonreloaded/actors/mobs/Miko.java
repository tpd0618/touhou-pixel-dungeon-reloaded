package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDefDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MikoSprite;
import com.watabou.utils.Random;

public class Miko extends Mob {

    {
        spriteClass = MikoSprite.class;

        HP = HT = 78;
        defenseSkill = 22;
        EXP = 14;
        maxLvl = 30;

        loot = new LifeFragment();
        lootChance = 0.01f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(22, 28);
    }

    @Override
    public int attackSkill(Char target) {
        return 27;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION/2f);
        if (Dungeon.hero.heroClass == HeroClass.PLAYERSANAE || Dungeon.hero.heroClass == HeroClass.PLAYERYOUMU || Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
            Buff.prolong(enemy, RegenBlock.class, RegenBlock.DURATION);
        }
        if (Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
            Buff.prolong(this, OneDefDamage.class, OneDefDamage.DURATION);
        }
        return damage;
    }
}