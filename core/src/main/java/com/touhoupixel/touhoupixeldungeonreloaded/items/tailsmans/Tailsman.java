/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.TailsmanHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;

abstract public class Tailsman extends Item {

    {
        stackable = true;

        bones = true;

        defaultAction = AC_THROW;
        usesTargeting = true;
    }

    public boolean holster;

    public int tier;

    protected MissileWeapon parent;

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        return actions;
    }

    @Override
    public boolean collect(Bag container) {
        if (container instanceof TailsmanHolder) holster = true;
        return super.collect(container);
    }

    @Override
    public void doThrow(Hero hero) {
        parent = null; //reset parent before throwing, just incase
        super.doThrow(hero);
    }

    @Override
    protected void onThrow(int cell) {
        Char enemy = Actor.findChar(cell);
        if (enemy == null || enemy == curUser) {
            parent = null;
            super.onThrow(cell);
        }
    }

    @Override
    public Item random() {
        if (!stackable) return this;

        quantity = 3;
        if (Random.Int(2) == 0) {
            quantity++;
            if (Random.Int(3) == 0) {
                quantity++;
                if (Random.Int(4) == 0) {
                    quantity++;
                }
            }
        }
        return this;
    }

    protected void rangedMiss(int cell) {
        parent = null;
        super.onThrow(cell);
    }

    @Override
    public boolean doPickUp(Hero hero, int pos) {
        parent = null;
        return super.doPickUp(hero, pos);
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    @Override
    public int value() {
        return 6 * tier * quantity * (level() + 1);
    }

    public static class PlaceHolder extends Tailsman {

        {
            image = ItemSpriteSheet.TAILSMANHOLD;
        }

        @Override
        public boolean isSimilar(Item item) {
            return item instanceof Tailsman;
        }

        @Override
        public String info() {

            String info = desc();
            info += Messages.get(this, "desc");
            return info;
        }
    }
}