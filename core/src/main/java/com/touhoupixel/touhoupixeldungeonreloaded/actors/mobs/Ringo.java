package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfRingoDango;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RingoSprite;
import com.watabou.utils.Random;

public class Ringo extends Mob {

    {
        spriteClass = RingoSprite.class;

        HP = HT = 16;
        defenseSkill = 5;
        EXP = 2;
        maxLvl = 12;

        baseSpeed = 2f;

        loot = Generator.Category.ARMOR_T2;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 4);
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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment){
            Statistics.power += 10;
            if (Dungeon.hero.heroClass == HeroClass.PLAYERSANAE || Dungeon.hero.heroClass == HeroClass.PLAYERYOUMU || Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
                Buff.prolong(enemy, Vulnerable.class, Vulnerable.DURATION / 5f);
            }
            if (Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
                Buff.prolong(enemy, RegenBlock.class, RegenBlock.DURATION / 2f);
            }
        }
        return damage;
    }
}