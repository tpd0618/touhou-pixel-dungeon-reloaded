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

package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ShinmyomaruSprite;
import com.watabou.utils.Random;

public class Shinmyomaru extends Mob {

    {
        spriteClass = ShinmyomaruSprite.class;

        HP = HT = 126;
        defenseSkill = 30;
        EXP = 16;
        maxLvl = 37;

        properties.add(Property.HUMAN);

        loot = Generator.Category.WEP_T5;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(9, 16);
    }

    @Override
    public int attackSkill(Char target) {
        return 35;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.prolong(enemy, MeleeNullify.class, MeleeNullify.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Slow.class, Slow.DURATION);
            }
        }
        return damage;
    }
}