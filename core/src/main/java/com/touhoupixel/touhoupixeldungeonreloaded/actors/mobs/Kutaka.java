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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirthDone;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.TargetedCell;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.BlastParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KutakaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Kutaka extends Mob {

    {
        spriteClass = KutakaSprite.class;

        HP = HT = 354;
        defenseSkill = 25;
        EXP = 13;
        maxLvl = 32;

        flying = true;

        properties.add(Property.GOD);

        loot = Generator.Category.POTION;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(82, 126);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(29, 43);
    }

    @Override
    protected boolean act() {
        if (this.state != SLEEPING && this.state != FLEEING) {
            if (Dungeon.heroine.pos < this.pos || Dungeon.heroine.pos > this.pos){
                if (Dungeon.heroine.buff(Paralysis.class) == null && Random.Int(2) == 0) {
                    if (this.pos - 1 == Dungeon.heroine.pos ||
                            this.pos + 1 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() - 1 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() + 1 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() - 1 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() + 1 == Dungeon.heroine.pos ||
                            this.pos - 2 == Dungeon.heroine.pos ||
                            this.pos + 2 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() * 2 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() * 2 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() * 2 - 2 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() * 2 + 2 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() * 2 - 2 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() * 2 + 2 == Dungeon.heroine.pos ||
                            this.pos - 3 == Dungeon.heroine.pos ||
                            this.pos + 3 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() * 3 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() * 3 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() * 3 - 3 == Dungeon.heroine.pos ||
                            this.pos - Dungeon.level.width() * 3 + 3 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() * 3 - 3 == Dungeon.heroine.pos ||
                            this.pos + Dungeon.level.width() * 3 + 3 == Dungeon.heroine.pos) {
                        Buff.prolong(Dungeon.heroine, Paralysis.class, Paralysis.DURATION / 2f);
                        CellEmitter.center(Dungeon.heroine.pos).burst(BlastParticle.FACTORY, 4);
                        GLog.w(Messages.get(this, "dozikon"));

                        sprite.parent.addToBack(new TargetedCell(this.pos - 1, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + 1, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width(), 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width(), 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width() - 1, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width() + 1, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width() - 1, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width() + 1, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - 2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + 2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2 - 2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2 + 2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2 - 2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2 + 2, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - 3, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + 3, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3 - 3, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3 + 3, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3 - 3, 0xFF0000));
                        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3 + 3, 0xFF0000));
                    }
                }
            }
        }
        return super.act();
    }
}