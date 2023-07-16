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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AliceCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BrainWash;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FumoLover;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.CrystalKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.GoldenKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.IronKey;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Notes;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class VentoraStick extends MeleeWeapon {

    {
        image = ItemSpriteSheet.VENTORA_STICK;
        hitSound = Assets.Sounds.HIT_STRONG;
        hitSoundPitch = 1f;

        tier = 5;
    }

    @Override
    public int reachFactor(Char owner) {
        int reach = super.reachFactor(owner);
        return reach;
    }

    @Override
    public int YokaiFactor( Char owner ) {
        return 1;
    }
}