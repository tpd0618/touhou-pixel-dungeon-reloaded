package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MamizouSprite;
import com.watabou.utils.Random;

public class Mamizou extends Mob {

    {
        spriteClass = MamizouSprite.class;

        HP = HT = 47;
        defenseSkill = 12;
        EXP = 6;
        maxLvl = 20;

        properties.add(Property.YOKAI);

        loot = new PotionOfInvisibility();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 9);
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
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            Buff.prolong(enemy, CursedBlow.class, CursedBlow.DURATION/2f);
            if (Statistics.difficulty > 2) {
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, SuperHard.class, SuperHard.DURATION);
            }
        }
        return damage;
    }
}