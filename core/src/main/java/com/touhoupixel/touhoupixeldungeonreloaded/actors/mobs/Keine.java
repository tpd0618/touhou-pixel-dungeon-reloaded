package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeineSprite;
import com.watabou.utils.Random;

public class Keine extends Mob {

    {
        spriteClass = KeineSprite.class;

        HP = HT = 106;
        defenseSkill = 22;
        EXP = -10;
        maxLvl = 30;

        loot = new ScrollOfSirensSong();
        lootChance = 0.125f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 15);
    }

    @Override
    public int attackSkill(Char target) {
        return 27;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //reduce EXP 10 is the special ability
}