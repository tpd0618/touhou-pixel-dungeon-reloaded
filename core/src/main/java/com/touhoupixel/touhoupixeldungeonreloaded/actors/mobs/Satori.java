package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SatoriSprite;
import com.watabou.utils.Random;

public class Satori extends Mob {

    {
        spriteClass = SatoriSprite.class;

        HP = HT = 259;
        defenseSkill = 64;
        EXP = 15;
        maxLvl = 75;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new PotionOfMindVision();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(32, 36);
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