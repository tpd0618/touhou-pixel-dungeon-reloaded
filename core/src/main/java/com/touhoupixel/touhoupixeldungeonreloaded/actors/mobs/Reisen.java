package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ReisenSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.watabou.utils.Random;

public class Reisen extends Mob {

    {
        spriteClass = ReisenSprite.class;

        HP = HT = 87;
        defenseSkill = 0;
        EXP = 8;
        maxLvl = 30;

        loot = new ScrollOfMagicMapping();
        lootChance = 0.125f;
    }

    @Override
    public int defenseSkill( Char enemy ) {
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.prolong( enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION/10f);
            BArray.setFalse(Dungeon.level.visited);
            BArray.setFalse(Dungeon.level.mapped);

            GameScene.updateFog(); //just in case hero wasn't moved
            Dungeon.observe();
            return INFINITE_EVASION;
        } else
        return 0;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(10, 14);
    }

    @Override
    public int attackSkill(Char target) {
        return 27;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}