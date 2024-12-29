package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfInvert;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.watabou.utils.Random;

public class Aya extends Mob {

    {
        spriteClass = AyaSprite.class;

        HP = HT = 165;
        defenseSkill = 17;
        EXP = 9;
        maxLvl = 25;

        baseSpeed = 5f;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new PotionOfInvert();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(39, 61);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(7, 11);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            if (Statistics.difficulty > 2) {
                //do nothing
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Hex.class, Hex.DURATION);
            }
        }
        return damage;
    }
}