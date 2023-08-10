package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Gold;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SuikaSprite;
import com.watabou.utils.Random;

public class Suika extends Mob {

    {
        spriteClass = SuikaSprite.class;

        HP = HT = 6; //gitan mamel minus 6HP
        defenseSkill = 35;
        EXP = 19;
        maxLvl = 47;

        state = WANDERING;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Gold.class;
        lootChance = 1f;
    }

    @Override
    protected boolean act() {
        boolean result = super.act();
        if (buff(SuperHard.class) == null) {
            Buff.prolong(this, SuperHard.class, SuperHard.DURATION * 10000f);
        }
        if (buff(DoubleSpeed.class) == null) {
            Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 10000f);
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(28, 33);
    }

    @Override
    public int attackSkill(Char target) {
        return 45;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, HinaCurse.class, HinaCurse.DURATION);
            }
        }
        return damage;
    }
}