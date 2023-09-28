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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KutakaSprite;
import com.watabou.utils.Random;

public class Kutaka extends Mob {

    {
        spriteClass = KutakaSprite.class;

        HP = HT = 85;
        defenseSkill = 25;
        EXP = 13;
        maxLvl = 32;

        flying = true;

        properties.add(Property.GOD);

        loot = Generator.Category.HERB;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(16, 21);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION);
            }
        }
        return damage;
    }
}