package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BalanceBreak;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KokoroSprite;
import com.watabou.utils.Random;

public class Kokoro extends Mob {

    {
        spriteClass = KokoroSprite.class;

        HP = HT = 14;
        defenseSkill = 5;
        EXP = 2;
        maxLvl = 12;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Generator.Category.WEP_T2;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 5);
    }

    @Override
    public int attackSkill(Char target) {
        return 10;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0){
            Buff.prolong(enemy, BalanceBreak.class, BalanceBreak.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Weakness.class, Weakness.DURATION/5f);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION/2f);
            }
        }
        return damage;
    }
}