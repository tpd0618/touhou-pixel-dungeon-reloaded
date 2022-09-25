package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MoveDetect;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.StarSprite;
import com.watabou.utils.Random;

public class Star extends Mob {

    {
        spriteClass = StarSprite.class;

        HP = HT = 41;
        defenseSkill = 12;
        EXP = 3;
        maxLvl = 20;

        flying = true;

        loot = new ScrollOfTeleportation();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(7, 10);
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
        if (Random.Int(3) == 0) {
            Buff.prolong(enemy, MoveDetect.class, MoveDetect.DURATION/5f);
        }
        return damage;
    }
}