package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Transmutation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MamizouSprite;
import com.watabou.utils.Random;

public class Mamizou extends MobWithSpellcard {

    {
        spriteClass = MamizouSprite.class;

        HP = HT = 80;
        defenseSkill = 12;
        EXP = 6;
        maxLvl = 20;

        spellcardsDefaultList.add(Transmutation.class);
        numberOfCards = Statistics.difficulty > 4 ? 2 : 1; // on overdrive or risky she has 2 spellcards
        mobRarity = COMMON_RARITY;

        properties.add(Property.YOKAI);
        properties.add(Property.NOT_EXTERMINABLE);


        loot = new PotionOfInvisibility();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(34, 54);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(9, 13);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            Buff.prolong(enemy, CursedBlow.class, CursedBlow.DURATION/2f);
        }
        return damage;
    }
    @Override
    public boolean readyToUseSpellcard(){
        if (enemy != null){
        for (Buff c : enemy.buffs()) {
            if (c.type == Buff.buffType.POSITIVE) {
                return super.readyToUseSpellcard();
            }
        }
        }

        return false;
    }
}