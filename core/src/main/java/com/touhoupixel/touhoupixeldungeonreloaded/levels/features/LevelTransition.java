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

package com.touhoupixel.touhoupixeldungeonreloaded.levels.features;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Point;
import com.watabou.utils.Random;
import com.watabou.utils.Rect;

public class LevelTransition extends Rect implements Bundlable {

    public enum Type {
        SURFACE,
        REGULAR_EXIT,
        REGULAR_ENTRANCE;
    }

    public Type type;
    public int destFloor;
    public int destBranch;
    public Type destType;

    public int centerCell;

    //for bundling
    public LevelTransition(){
        super();
    }

    public LevelTransition(Level level, int cell, Type type, int destFloor, int destBranch, Type destType){
        centerCell = cell;
        Point p = level.cellToPoint(cell);
        set(p.x, p.y, p.x, p.y);
        this.type = type;
        this.destFloor = destFloor;
        this.destBranch = destBranch;
        this.destType = destType;
    }

    //gives default values for common transition types
    public LevelTransition(Level level, int cell, Type type){
        centerCell = cell;
        Point p = level.cellToPoint(cell);
        set(p.x, p.y, p.x, p.y);
        this.type = type;
        switch (type){
            case REGULAR_EXIT: default:
                destFloor = Dungeon.floor -1;
                destBranch = Dungeon.branch;
                destType = Type.REGULAR_ENTRANCE;
                break;
            case REGULAR_ENTRANCE:
                destFloor = Dungeon.floor +1;
                destBranch = Dungeon.branch;
                destType = Type.REGULAR_EXIT;
                break;
            case SURFACE:
                destFloor = 0;
                destBranch = 0;
                destType = null;
                break;
        }
    }

    //note that the center cell isn't always the actual center.
    // It is important when game logic needs to pick a specific cell for some action
    // e.g. where to place the hero
    public int cell(){
        return centerCell;
    }

    //Transitions are inclusive to their right and bottom sides
    @Override
    public int width() {
        return super.width()+1;
    }

    @Override
    public int height() {
        return super.height()+1;
    }

    @Override
    public boolean inside(Point p) {
        return p.x >= left && p.x <= right && p.y >= top && p.y <= bottom;
    }

    public boolean inside(int cell){
        return inside(new Point(Dungeon.level.cellToPoint(cell)));
    }

    public Point center() {
        return new Point(
                (left + right) / 2 + (((right - left) % 2) == 1 ? Random.Int( 2 ) : 0),
                (top + bottom) / 2 + (((bottom - top) % 2) == 1 ? Random.Int( 2 ) : 0) );
    }

    public static final String TYPE = "type";
    public static final String DEST_FLOOR = "dest_floor";
    public static final String DEST_BRANCH = "dest_branch";
    public static final String DEST_TYPE = "dest_type";

    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put( "left", left );
        bundle.put( "top", top );
        bundle.put( "right", right );
        bundle.put( "bottom", bottom );

        bundle.put( "center", centerCell );

        bundle.put(TYPE, type);
        bundle.put(DEST_FLOOR, destFloor);
        bundle.put(DEST_BRANCH, destBranch);
        bundle.put(DEST_TYPE, destType);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        left = bundle.getInt( "left" );
        top = bundle.getInt( "top" );
        right = bundle.getInt( "right" );
        bottom = bundle.getInt( "bottom" );

        centerCell = bundle.getInt( "center" );

        type = bundle.getEnum(TYPE, Type.class);
        destFloor = bundle.getInt(DEST_FLOOR);
        destBranch = bundle.getInt(DEST_BRANCH);
        if (bundle.contains(DEST_TYPE)) destType = bundle.getEnum(DEST_TYPE, Type.class);
    }
}