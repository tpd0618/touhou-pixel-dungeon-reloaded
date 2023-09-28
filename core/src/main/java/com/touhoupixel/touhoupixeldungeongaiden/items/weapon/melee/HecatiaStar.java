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

package com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doom;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class HecatiaStar extends MeleeWeapon {

    {
        image = ItemSpriteSheet.HECATIA_STAR;
        hitSound = Assets.Sounds.HIT_STRONG;
        hitSoundPitch = 1f;

        tier = 3;
    }

    @Override
    public int HumanFactor( Char owner ) {
        return 1;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        int sqrt = (int) Math.sqrt(attacker.HP);
        if (sqrt * sqrt == attacker.HP){
            Buff.affect(defender, Doom.class);
        }
        return super.proc(attacker, defender, damage);
    }
}