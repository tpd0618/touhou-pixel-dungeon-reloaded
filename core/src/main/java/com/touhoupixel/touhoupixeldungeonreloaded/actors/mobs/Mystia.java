package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MystiaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.watabou.utils.Random;

public class Mystia extends Mob {

    {
        spriteClass = MystiaSprite.class;

        HP = HT = 39;
        defenseSkill = 10;
        EXP = 4;
        maxLvl = 17;

        flying = true;

        properties.add(Property.ANIMAL);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(25, 39);
    }

    @Override
    public int attackSkill(Char target) {
        return 15;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(3, 5);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(8) == 0) {
            Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
            BArray.setFalse(Dungeon.level.visited);
            BArray.setFalse(Dungeon.level.mapped);

            GameScene.updateFog(); //just in case hero wasn't moved
            Dungeon.observe();
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, ZeroDexterity.class, ZeroDexterity.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, HinaCurse.class, HinaCurse.DURATION);
            }
        }
        return damage;
    }
}