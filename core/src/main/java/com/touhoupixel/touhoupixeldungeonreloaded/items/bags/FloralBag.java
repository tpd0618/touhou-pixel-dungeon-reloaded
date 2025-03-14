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

package com.touhoupixel.touhoupixeldungeonreloaded.items.bags;

import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.LiquidMetal;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Plant;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class FloralBag extends Bag {

    {
        image = ItemSpriteSheet.BANDOLIER;
    }

    @Override
    public boolean canHold( Item item ) {
        if (item instanceof Plant.Seed || item instanceof Runestone || item instanceof LiquidMetal){
            return super.canHold(item);
        } else {
            return false;
        }
    }

    public int capacity(){
        return 19;
    }

    @Override
    public void onDetach( ) {
        super.onDetach();
        for (Item item : items) {
            if (item instanceof Wand) {
                ((Wand)item).stopCharging();
            } else if (item instanceof MissileWeapon){
                ((MissileWeapon) item).holster = false;
            }
        }
    }

    @Override
    public int value() {
        return 60;
    }

}