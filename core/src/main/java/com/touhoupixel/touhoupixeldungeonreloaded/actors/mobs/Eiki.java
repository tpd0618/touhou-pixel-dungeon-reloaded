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
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.BlastParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.EikiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Eiki extends Mob {

    {
        spriteClass = EikiSprite.class;

        HP = HT = 246;
        defenseSkill = 40;
        EXP = 25;
        maxLvl = 99;

        state = WANDERING;

        properties.add(Property.GOD);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Statistics.difficulty > 2 ? Random.NormalIntRange(Statistics.enemiesSlain/5, Statistics.enemiesSlain/4):
                Random.NormalIntRange(Statistics.enemiesSlain/7, Statistics.enemiesSlain/5);
    }

    @Override
    public int attackSkill(Char target) {
        return 75;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(62, 89);
    }

    @Override
    protected boolean act() {
        if (this.state != SLEEPING && this.state != FLEEING) {
            if (Dungeon.heroine.pos < this.pos || Dungeon.heroine.pos > this.pos){
                if (Random.Int(7) == 0) {
                    Dungeon.heroine.damage(60 - Dungeon.heroine.belongings.armor.DRMin(), this);
                    CellEmitter.center(Dungeon.heroine.pos).burst(BlastParticle.FACTORY, 4);
                    GLog.w(Messages.get(this, "abyss_dragon"));
                }
            }
        }
        return super.act();
    }
}