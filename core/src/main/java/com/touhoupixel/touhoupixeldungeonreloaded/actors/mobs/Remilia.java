package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.KeyHeal;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SquareRootSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RemiliaSprite;
import com.watabou.utils.Random;

public class Remilia extends Mob {

    {
        spriteClass = RemiliaSprite.class;

        HP = HT = 39;
        defenseSkill = 15;
        EXP = 9;
        maxLvl = 22;

        properties.add(Property.WARP);

        loot = new SpellcardFragment();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(7, 9);
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(8) == 0) {
            Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION/2f);
            if (Statistics.difficulty > 2) {
                Buff.detach(enemy, Light.class);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, SquareRootSnipe.class, SquareRootSnipe.DURATION);
            }
        }
        return damage;
    }
}