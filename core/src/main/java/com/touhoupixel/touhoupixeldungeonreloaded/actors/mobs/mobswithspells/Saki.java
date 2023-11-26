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

import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Inspiration;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SakiSprite;
import com.watabou.utils.Random;

public class Saki extends MobWithSpellcard {

    {
        spriteClass = SakiSprite.class;

        HP = HT = 250;
        defenseSkill = 25;
        EXP = 13;
        maxLvl = 32;

        spellcardsDefaultList.add(Inspiration.class);
        numberOfCards = Statistics.difficulty > 4 ? 2 : 1; // on overdrive or risky she has 2 spellcards
        mobRarity = UNCOMMON_RARITY;

        properties.add(Property.YOKAI);
        properties.add(Property.NOT_EXTERMINABLE);

        loot = Generator.Category.TALISMAN;
        lootChance = 0.2f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(95, 125);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(26, 38);
    }

}