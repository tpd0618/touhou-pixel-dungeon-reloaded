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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells.MobWithSpellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.SoulCloning;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MisumaruSprite;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Misumaru extends MobWithSpellcard {

    {
        spriteClass = MisumaruSprite.class;

        HP = HT = 169;
        defenseSkill = 15;
        EXP = 6;
        maxLvl = 22;

        spellcardsDefaultList.add(SoulCloning.class);

        properties.add(Property.GOD);
        numberOfCards = Statistics.difficulty >= 4 ? 2 : 1; // on lunatic+ she has 2 spellcards
        mobRarity = UNCOMMON_RARITY;

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(44, 64);
    }

    @Override
    public boolean readyToUseSpellcard() {
        if (super.readyToUseSpellcard()){
            for (Mob mob : Dungeon.level.mobs) {
                if (alignment == Char.Alignment.ENEMY && mob != this
                        && fieldOfView[mob.pos] && mob.invisible <= 0 && !(mob instanceof MobWithSpellcard)
                        && !mob.properties().contains(Property.BOSS) && !mob.properties().contains(Property.MINIBOSS)) {
                    return true; // at least one mob is needed
                }
            }
            return false;
        }
        else return false;
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}