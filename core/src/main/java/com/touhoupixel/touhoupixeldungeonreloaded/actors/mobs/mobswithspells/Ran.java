/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.HitAndRun;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.SummonChen;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RanSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Ran extends MobWithSpellcard implements Callback {

    private static final float TIME_TO_ZAP	= 1f;

    {
        spriteClass = RanSprite.class;

        HP = HT = 630;
        defenseSkill = 32;
        EXP = 15;
        maxLvl = 45;

        spellcardsDefaultList.add(SummonChen.class);
        spellcardsDefaultList.add(HitAndRun.class);
        if (Statistics.difficulty <= 2) numberOfCards = 2;
        else if (Statistics.difficulty <= 4) numberOfCards = 3;
        else numberOfCards = 4;
        mobRarity = UNCOMMON_RARITY;

        properties.add(Property.ANIMAL);
        properties.add(Property.NOT_EXTERMINABLE);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(24, 30);
    } // doesn't attack at melee

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(17, 26);
    }
    @Override
    protected boolean canAttack( Char enemy ) {
        return !Dungeon.level.adjacent( pos, enemy.pos )
                && (super.canAttack(enemy) || new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos);
    }

    protected boolean doAttack( Char enemy ) {

        if (Dungeon.level.adjacent( pos, enemy.pos )) {

            return super.doAttack( enemy );

        } else {

            if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
                sprite.zap( enemy.pos );
                return false;
            } else {
                zap();
                return true;
            }
        }
    }

    //used so resistances can differentiate between melee and magical attacks
    public static class DarkBolt{}

    protected void zap() {
        spend( TIME_TO_ZAP );

        if (hit( this, enemy, true )) {
            //TODO would be nice for this to work on ghost/statues too
            int dmg = Random.NormalIntRange( 110, 150 );
            if (spellcard instanceof HitAndRun) dmg *= 1.3;
            enemy.damage( dmg, new DarkBolt() );

            if (enemy == Dungeon.heroine && !enemy.isAlive()) {
                Dungeon.fail( getClass() );
                GLog.n( Messages.get(this, "bolt_kill") );
            }
        } else {
            enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
        }
    }

    public void onZapComplete() {
        zap();
        next();
    }

    @Override
    protected boolean getCloser( int target ) {
        if (state == HUNTING) {
            return enemySeen && getFurther( target );
        } else {
            return super.getCloser( target );
        }
    }

    @Override
    public void aggro(Char ch) {
        //cannot be aggroed to something it can't see
        if (ch == null || fieldOfView == null || fieldOfView[ch.pos]) {
            super.aggro(ch);
        }
    }
    @Override
    public void call() {
        next();
    }
}