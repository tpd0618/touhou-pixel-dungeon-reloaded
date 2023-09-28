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

package com.touhoupixel.touhoupixeldungeongaiden.items.abilitycards.sanaeexclusive;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.abilitycards.Abilitycards;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class MiserAdvice extends Abilitycards {

    private static final String AC_DRINK	= "DRINK";

    {
        image = ItemSpriteSheet.CARDS44;

        defaultAction = AC_DRINK;

        stackable = true;
        unique = true;
    }

    @Override
    public boolean doPickUp(Hero heroine, int pos) {
        if (!Statistics.card44) {
            Statistics.card44 = true;
            return super.doPickUp(heroine, pos);
        } else return false;
    }

    @Override
    public ArrayList<String> actions( Hero heroine) {
        ArrayList<String> actions = super.actions(heroine);
        return actions;
    }
}