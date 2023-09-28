package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.HijiriSprite;
import com.watabou.utils.Random;

public class Hijiri extends Mob {

    {
        spriteClass = HijiriSprite.class;

        HP = HT = 6; //cave mamel
        defenseSkill = 22;
        EXP = 13;
        maxLvl = 30;

        baseSpeed = 2f;

        state = WANDERING;

        properties.add(Property.WARP);

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    protected boolean act() {
        boolean result = super.act();
        if (buff(SuperHard.class) == null) {
            Buff.prolong(this, SuperHard.class, SuperHard.DURATION * 10000f);
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(14, 20);
    }

    @Override
    public int attackSkill(Char target) {
        return 27;
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
                Buff.prolong(enemy, HinaCurse.class, HinaCurse.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION);
            }
        }
        return damage;
    }
}