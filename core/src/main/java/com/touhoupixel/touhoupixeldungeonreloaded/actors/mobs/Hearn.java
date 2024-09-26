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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HearnSprite;
import com.watabou.utils.Random;

public class Hearn extends Mob {

    {
        spriteClass = HearnSprite.class;

        HP = HT = 254;
        defenseSkill = 25;
        EXP = 13;
        maxLvl = 32;

        properties.add(Property.HUMAN);
        properties.add(Property.HARASSMENT);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(68, 104);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(26, 38);
    }

    void teleportMobs(int direction) {
        int[] offsets = new int[]{direction, 2 * direction, direction + Dungeon.level.width(), direction - Dungeon.level.width()};

        for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
            for (int offset : offsets) {
                int newPos = this.pos + offset;
                if (Actor.findChar(newPos) == null && Dungeon.level.map[newPos] != Terrain.CHASM) {
                    ScrollOfTeleportation.teleportToLocationHearn(offset == direction ? Dungeon.heroine : mob, newPos);
                }
            }
        }
    }

    void performAction() {
        switch (Random.Int(4)) {
            case 0: teleportMobs(1); break;
            case 1: teleportMobs(-1); break;
            case 2: teleportMobs(Dungeon.level.width()); break;
            case 3: teleportMobs(-Dungeon.level.width()); break;
        }
    }

    @Override
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && this.state != SLEEPING && this.state != FLEEING && Random.Int(5) == 0) {
            performAction();
        }
        return super.act();
    }
}