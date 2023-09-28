package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfReverseYingYang;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KoishiSprite;
import com.watabou.utils.Random;

public class Koishi extends Mob {

    {
        spriteClass = KoishiSprite.class;

        HP = HT = 249;
        defenseSkill = 32;
        EXP = 15;
        maxLvl = 75;

        state = WANDERING;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new PotionOfReverseYingYang();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(36, 41);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0){
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, HeavenSpeed.class, HeavenSpeed.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, ZeroDexterity.class, ZeroDexterity.DURATION);
            }
        }
        return damage;
    }
}