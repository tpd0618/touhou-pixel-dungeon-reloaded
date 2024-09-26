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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.TargetedCell;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Bomb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DisintegrationTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TsukasaSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Tsukasa extends Mob {

    {
        spriteClass = TsukasaSprite.class;

        HP = HT = 205;
        defenseSkill = 20;
        EXP = 10;
        maxLvl = 27;

        properties.add(Property.YOKAI);

        loot = new PotionOfHealing();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(69, 107);
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(13, 20);
    }

    @Override
    public void die(Object cause) {
        TsukasaSoul tsukasaSoul = new TsukasaSoul();
        tsukasaSoul.state = tsukasaSoul.WANDERING;
        tsukasaSoul.pos = this.pos;
        GameScene.add(tsukasaSoul);
        super.die(cause);
    }
}