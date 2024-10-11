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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KanakoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Kanako extends Mob {

    {
        spriteClass = KanakoSprite.class;

        HP = HT = 490;
        defenseSkill = 27;
        EXP = 16;
        maxLvl = 35;

        properties.add(Property.GOD);

        loot = Generator.Category.SCROLL;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(93, 141);
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int attackProc(Char enemy, int damage) {
        if (enemy.equals(Dungeon.heroine) && Random.Int(4) == 0 && Statistics.difficulty > 2){
            GLog.n(Messages.get(this, "strength_reduced"));
            Dungeon.heroine.STR--;
        }
        return super.attackProc(enemy, damage);
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(16, 24);
    }

    @Override
    public String defenseVerb(Char attaker) {
        if (attaker == Dungeon.heroine) {
            Buff.prolong(enemy, Onigiri.class, Onigiri.DURATION);
            GLog.w(Messages.get(Kanako.class, "kanako"));
        }
        return super.defenseVerb(attaker);
    }
}