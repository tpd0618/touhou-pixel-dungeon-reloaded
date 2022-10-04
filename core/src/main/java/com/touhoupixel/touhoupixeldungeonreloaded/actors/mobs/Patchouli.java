package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bombs.Bomb;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.PatchouliSprite;
import com.watabou.utils.Random;

public class Patchouli extends Mob {

    {
        spriteClass = PatchouliSprite.class;

        HP = HT = 107;
        defenseSkill = 27;
        EXP = 9;
        maxLvl = 35;

        loot = new Bomb();
        lootChance = 0.2f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(13, 16);
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        new ExplosiveTrap().set(this.pos).activate();
    }
}