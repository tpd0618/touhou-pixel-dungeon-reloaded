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

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.HecatiaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.BirukoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HitoriSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Hitori extends Mob {

    {
        spriteClass = HitoriSprite.class;

        HP = HT = Dungeon.depth*4;
        defenseSkill = Dungeon.depth;

        flying = true;

        properties.add(Property.HUMAN);

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    protected boolean act() {
        boolean result = super.act();
        if (buff(Doublespeed.class) == null) {
            Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION * 10000f);
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Dungeon.depth+5+Statistics.hitoricount, Dungeon.depth+6+Statistics.hitoricount);
    }

    @Override
    public int attackSkill(Char target) {
        return Dungeon.depth+5+Statistics.hitoricount;
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