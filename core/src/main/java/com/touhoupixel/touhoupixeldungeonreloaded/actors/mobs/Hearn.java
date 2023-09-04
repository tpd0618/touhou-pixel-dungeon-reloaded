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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Amok;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.BlastParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.BackdoorTalisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.Talisman;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HearnSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MomoyoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Hearn extends Mob {

    {
        spriteClass = HearnSprite.class;

        HP = HT = 88;
        defenseSkill = 25;
        EXP = 13;
        maxLvl = 32;

        properties.add(Property.HUMAN);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 18);
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
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && this.state != SLEEPING && this.state != FLEEING && Random.Int(5) == 0) {
            switch (Random.Int(4)) {
                case 0:
                default:
                    for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
                        if (Actor.findChar(this.pos + 1) == null && Dungeon.level.map[this.pos + 1] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(Dungeon.heroine, this.pos + 1);
                        }
                        if (Actor.findChar(this.pos + 2) == null && Dungeon.level.map[this.pos + 2] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos + 2);
                        }
                        if (Actor.findChar(this.pos + 1 + Dungeon.level.width()) == null && Dungeon.level.map[this.pos + 1 + Dungeon.level.width()] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos + 1 + Dungeon.level.width());
                        }
                        if (Actor.findChar(this.pos + 1 - Dungeon.level.width()) == null && Dungeon.level.map[this.pos + 1 - Dungeon.level.width()] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos + 1 - Dungeon.level.width());
                        }
                        if (Statistics.difficulty > 2) {
                            Buff.prolong(mob, Might.class, Might.DURATION);
                        }
                        if (Statistics.difficulty > 4) {
                            Buff.prolong(mob, DoubleSpeed.class, DoubleSpeed.DURATION);
                        }
                    }
                    break;
                case 1:
                    for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
                        if (Actor.findChar(this.pos - 1) == null && Dungeon.level.map[this.pos - 1] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(Dungeon.heroine, this.pos + 1);
                        }
                        if (Actor.findChar(this.pos - 2) == null && Dungeon.level.map[this.pos - 2] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos - 2);
                        }
                        if (Actor.findChar(this.pos - 1 + Dungeon.level.width()) == null && Dungeon.level.map[this.pos - 1 + Dungeon.level.width()] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos - 1 + Dungeon.level.width());
                        }
                        if (Actor.findChar(this.pos - 1 - Dungeon.level.width()) == null && Dungeon.level.map[this.pos - 1 - Dungeon.level.width()] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos - 1 - Dungeon.level.width());
                        }
                        if (Statistics.difficulty > 2) {
                            Buff.prolong(mob, Might.class, Might.DURATION);
                        }
                        if (Statistics.difficulty > 4) {
                            Buff.prolong(mob, DoubleSpeed.class, DoubleSpeed.DURATION);
                        }
                    }
                    break;
                case 2:
                    for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
                        if (Actor.findChar(this.pos + Dungeon.level.width()) == null && Dungeon.level.map[this.pos + Dungeon.level.width()] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(Dungeon.heroine, this.pos + Dungeon.level.width());
                        }
                        if (Actor.findChar(this.pos + Dungeon.level.width() * 2) == null && Dungeon.level.map[this.pos + Dungeon.level.width() * 2] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos + Dungeon.level.width() * 2);
                        }
                        if (Actor.findChar(this.pos + Dungeon.level.width() + 1) == null && Dungeon.level.map[this.pos + Dungeon.level.width() + 1] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos + Dungeon.level.width() + 1);
                        }
                        if (Actor.findChar(this.pos + Dungeon.level.width() - 1) == null && Dungeon.level.map[this.pos + Dungeon.level.width() - 1] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos + Dungeon.level.width() - 1);
                        }
                        if (Statistics.difficulty > 2) {
                            Buff.prolong(mob, Might.class, Might.DURATION);
                        }
                        if (Statistics.difficulty > 4) {
                            Buff.prolong(mob, DoubleSpeed.class, DoubleSpeed.DURATION);
                        }
                    }
                    break;
                case 3:
                    for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
                        if (Actor.findChar(this.pos - Dungeon.level.width()) == null && Dungeon.level.map[this.pos - Dungeon.level.width()] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(Dungeon.heroine, this.pos - Dungeon.level.width());
                        }
                        if (Actor.findChar(this.pos - Dungeon.level.width() * 2) == null && Dungeon.level.map[this.pos - Dungeon.level.width() * 2] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos - Dungeon.level.width() * 2);
                        }
                        if (Actor.findChar(this.pos - Dungeon.level.width() + 1) == null && Dungeon.level.map[this.pos - Dungeon.level.width() + 1] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos - Dungeon.level.width() + 1);
                        }
                        if (Actor.findChar(this.pos - Dungeon.level.width() - 1) == null && Dungeon.level.map[this.pos - Dungeon.level.width() - 1] != Terrain.CHASM) {
                            ScrollOfTeleportation.teleportToLocationHearn(mob, this.pos - Dungeon.level.width() - 1);
                        }
                        if (Statistics.difficulty > 2) {
                            Buff.prolong(mob, Might.class, Might.DURATION);
                        }
                        if (Statistics.difficulty > 4) {
                            Buff.prolong(mob, DoubleSpeed.class, DoubleSpeed.DURATION);
                        }
                    }
                    break;
            }
        }
        return super.act();
    }
}