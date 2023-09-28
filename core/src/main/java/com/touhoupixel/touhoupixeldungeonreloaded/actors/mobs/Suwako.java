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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SuwakoSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Suwako extends Mob {

    {
        spriteClass = SuwakoSprite.class;

        HP = HT = 99;
        defenseSkill = 27;
        EXP = 16;
        maxLvl = 35;

        properties.add(Property.GOD);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Generator.Category.POTION;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(13, 21);
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void damage(int dmg, Object src) {
        int waterCells = 0;
        for (int i : PathFinder.NEIGHBOURS9) {
            if (Dungeon.level.map[pos + i] == Terrain.WATER) {
                waterCells++;
            }
        }
        if (waterCells > 0) dmg = Math.round(dmg * (6 - waterCells) / 6f);

        super.damage(dmg, src);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, MeleeNullify.class, MeleeNullify.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Inversion.class, Inversion.DURATION);
            }
        }
        return damage;
    }
}