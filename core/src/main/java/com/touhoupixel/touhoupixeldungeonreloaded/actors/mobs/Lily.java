package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Triplespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfAntiMagic;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.LilySprite;
import com.watabou.utils.Random;

public class Lily extends Mob {

    {
        spriteClass = LilySprite.class;

        HP = HT = 90;
        defenseSkill = 12;
        EXP = 4;
        maxLvl = 20;

        flying = true;

        loot = new ScrollOfAntiMagic();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(9, 13);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(4) == 0) {
            Buff.prolong(enemy, Haste.class, Haste.DURATION);
            Buff.prolong(this, Triplespeed.class, Triplespeed.DURATION);
        }
        return damage;
    }
}