package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.PatchouliSprite;
import com.watabou.utils.Random;

public class Patchouli extends Mob {

    {
        spriteClass = PatchouliSprite.class;

        HP = HT = 120;
        defenseSkill = 15;
        EXP = 6;
        maxLvl = 22;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        //because of her explosion upon death, no loot
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(31, 49);
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(6, 9);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        new ExplosiveTrap().set(this.pos).activate();
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            if (Statistics.difficulty > 2) {
                //do nothing
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
            }
        }
        return damage;
    }
}