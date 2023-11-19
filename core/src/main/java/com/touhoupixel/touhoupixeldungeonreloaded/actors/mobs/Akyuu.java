package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AkyuuSprite;
import com.watabou.utils.Random;

public class Akyuu extends Mob {

    {
        spriteClass = AkyuuSprite.class;

        HP = HT = 27;
        defenseSkill = 5;
        EXP = 2;
        maxLvl = 12;

        properties.add(Property.HUMAN);

        loot = new SpellcardFragment();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 12);
    }

    @Override
    public int attackSkill(Char target) {
        return 10;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(1, 3);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0){
            Buff.prolong(enemy, ZeroDexterity.class, ZeroDexterity.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Vertigo.class, Vertigo.DURATION / 2f);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Silence.class, Silence.DURATION);
            }
        }
        return damage;
    }
}