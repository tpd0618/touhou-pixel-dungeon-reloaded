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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HitoriSprite;
import com.watabou.utils.Random;

public class Hitori extends Mob {

    {
        spriteClass = HitoriSprite.class;

        HP = HT = Dungeon.floor *4;
        defenseSkill = Dungeon.floor;

        flying = true;

        properties.add(Property.HUMAN);
        properties.add(Property.MINIBOSS);

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    protected boolean act() {
        boolean result = super.act();
        if (buff(DoubleSpeed.class) == null) {
            Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 10000f);
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Dungeon.floor +5+Statistics.hitoricount, Dungeon.floor +6+Statistics.hitoricount);
    }

    @Override
    public int attackSkill(Char target) {
        return Dungeon.floor +5+Statistics.hitoricount;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 10);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        Statistics.hitoricount += 1;
        return damage;
    }
}