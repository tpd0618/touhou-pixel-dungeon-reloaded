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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class WatermelonSword extends MeleeWeapon {

    {
        image = ItemSpriteSheet.WATERMELON_SWORD;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1f;

        tier = 3;
    }

    @Override
    public int HumanFactor( Char owner ) {
        return 1;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if (attacker.HP == 9 || attacker.HP == 19 || attacker.HP == 29 || attacker.HP == 39 || attacker.HP == 49 || attacker.HP == 59 || attacker.HP == 69 || attacker.HP == 79 || attacker.HP == 89 || attacker.HP == 90 || attacker.HP == 91 || attacker.HP == 92 || attacker.HP == 93 || attacker.HP == 94 || attacker.HP == 95 || attacker.HP == 96 || attacker.HP == 97 || attacker.HP == 98 || attacker.HP == 109 || attacker.HP == 119 || attacker.HP == 129 || attacker.HP == 139 || attacker.HP == 149 || attacker.HP == 159 || attacker.HP == 169 || attacker.HP == 179 || attacker.HP == 189 || attacker.HP == 190 || attacker.HP == 191 || attacker.HP == 192 || attacker.HP == 193 || attacker.HP == 194 || attacker.HP == 195 || attacker.HP == 196 || attacker.HP == 197 || attacker.HP == 198){
            damage *= 1.5f;
        }
        if (attacker.HP == 99 || attacker.HP == 199){
            damage *= 1.5f;
        }
        return super.proc(attacker, defender, damage);
    }
}