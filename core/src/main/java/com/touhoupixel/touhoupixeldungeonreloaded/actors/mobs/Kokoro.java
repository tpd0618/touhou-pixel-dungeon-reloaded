package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiSneakattack;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BalanceBreak;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KokoroSprite;
import com.watabou.utils.Random;

public class Kokoro extends Mob {

    {
        spriteClass = KokoroSprite.class;

        HP = HT = 14;
        defenseSkill = 5;
        EXP = 3;
        maxLvl = 12;

        loot = Generator.Category.WEP_T2;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(1, 5);
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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(4) == 0){
            Buff.prolong(enemy, BalanceBreak.class, BalanceBreak.DURATION);
        }
        return damage;
    }
}