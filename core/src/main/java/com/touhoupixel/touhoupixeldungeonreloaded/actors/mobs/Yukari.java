package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiShipBattery;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfAttraction;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YukariSprite;
import com.watabou.utils.Random;

public class Yukari extends Mob {

    {
        spriteClass = YukariSprite.class;

        HP = HT = 450;
        defenseSkill = 40;
        EXP = 25;
        maxLvl = 99;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new PotionOfAttraction();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(35, 45);
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
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.prolong(enemy, AntiShipBattery.class, AntiShipBattery.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, SuperHard.class, SuperHard.DURATION);
            }
        }
        return damage;
    }
}