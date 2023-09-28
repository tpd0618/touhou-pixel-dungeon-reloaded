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

package com.touhoupixel.touhoupixeldungeonreloaded.mechanics;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.ShatteredPixelDungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class Ballistica {

	//note that the path is the FULL path of the projectile, including tiles after collision.
	//make sure to generate a subPath for the common case of going source to collision.
	public ArrayList<Integer> path = new ArrayList<>();
	public Integer sourcePos = null;
	public Integer collisionPos = null;
	public Integer collisionProperties = null;
	public Integer dist = 0;

	//parameters to specify the colliding cell
	public static final int STOP_TARGET = 1;    //ballistica will stop at the target cell
	public static final int STOP_CHARS = 2;     //ballistica will stop on first char hit
	public static final int STOP_SOLID = 4;     //ballistica will stop on solid terrain
	public static final int IGNORE_SOFT_SOLID = 8; //ballistica will ignore soft solid terrain, such as doors and webs

	public static final int PROJECTILE =  	STOP_TARGET	| STOP_CHARS	| STOP_SOLID;

	public static final int MAGIC_BOLT =    STOP_CHARS  | STOP_SOLID;

	public static final int WONT_STOP =     0;


	public Ballistica( int from, int to, int params ){
		sourcePos = from;
		collisionProperties = params;
		build(from, to,
				(params & STOP_TARGET) > 0,
				(params & STOP_CHARS) > 0,
				(params & STOP_SOLID) > 0,
				(params & IGNORE_SOFT_SOLID) > 0);

		if (collisionPos != null) {
			dist = path.indexOf(collisionPos);
		} else if (!path.isEmpty()) {
			collisionPos = path.get(dist = path.size() - 1);
		} else {
			path.add(from);
			collisionPos = from;
			dist = 0;
		}
	}

	private void build( int from, int to, boolean stopTarget, boolean stopChars, boolean stopTerrain, boolean ignoreSoftSolid ) {
		int w = Dungeon.level.width();

		int x0 = from % w;
		int x1 = to % w;
		int y0 = from / w;
		int y1 = to / w;

		int dx = x1 - x0;
		int dy = y1 - y0;

		int stepX = dx > 0 ? +1 : -1;
		int stepY = dy > 0 ? +1 : -1;

		dx = Math.abs( dx );
		dy = Math.abs( dy );

		int stepA;
		int stepB;
		int dA;
		int dB;

		if (dx > dy) {

			stepA = stepX;
			stepB = stepY * w;
			dA = dx;
			dB = dy;

		} else {

			stepA = stepY * w;
			stepB = stepX;
			dA = dy;
			dB = dx;

		}

		int cell = from;

		int err = dA / 2;
		while (Dungeon.level.insideMap(cell)) {

			//if we're in solid terrain, and there's no char there, collide with the previous cell.
			// we don't use solid here because we don't want to stop short of closed doors.
			if (stopTerrain
					&& cell != sourcePos
					&& !Dungeon.level.passable[cell]
					&& !Dungeon.level.avoid[cell]
					&& Actor.findChar(cell) == null) {
				collide(path.get(path.size() - 1));
			}

			path.add(cell);

			if (stopTerrain && cell != sourcePos && Dungeon.level.solid[cell]) {
				if (ignoreSoftSolid && (Dungeon.level.passable[cell] || Dungeon.level.avoid[cell])) {
					//do nothing
				} else {
					collide(cell);
				}
			} else if (cell != sourcePos && stopChars && Actor.findChar( cell ) != null) {
				collide(cell);
			} else if  (cell == to && stopTarget){
				collide(cell);
			}

			cell += stepA;

			err += dB;
			if (err >= dA) {
				err = err - dA;
				cell = cell + stepB;
			}
		}
	}

	//we only want to record the first position collision occurs at.
	private void collide(int cell){
		if (collisionPos == null)
			collisionPos = cell;
	}

	//
	public Ballistica targetAtAngle(float angle){
		Ballistica angleBallistica;
		//if the target is close, it will greatly affect the angle of the projectiles
		Ballistica longRangeTarget = new Ballistica(sourcePos, collisionPos, Ballistica.WONT_STOP);
		int sP = longRangeTarget.sourcePos;
		int cP = longRangeTarget.collisionPos;
		// values should not be zero or infinity
		final float MULTI_LIMITER = 1000000f;

		int w = Dungeon.level.width();

		int x0 = sP % w;
		int x1 = cP % w;
		int x2;
		int y0 = sP / w;
		int y1 = cP / w;
		int y2;

		float dx = x1 - x0;
		float dy = y1 - y0;

		int direction = 1;
		if (Math.cos(angle * Math.PI / 180) < 0){
			direction = -1;
		}

		if (dx == 0) { dx = 1/MULTI_LIMITER;
		if (dy < 0) { dx *= -1; }}
		if (dy == 0) { dy = 1/MULTI_LIMITER;
			if (dx < 0) { dy *= -1; }}

		float k1 = dy / dx;


		float k2 = (float) Math.tan(Math.PI*angle/180 + Math.atan(k1));
		if (k2 > MULTI_LIMITER){ k2 = MULTI_LIMITER; }
		else if (k2 < -1*MULTI_LIMITER) { k2 = -1*MULTI_LIMITER; }
		else if (k2 == 0) { k2 = 1 / MULTI_LIMITER;}
		float k3 = -1 / k1;

		float b = (float) dy - k3*dx;

		float dx2 = b / (k2 - k3) * direction;
		float dy2 = b * k2 / (k2 - k3) * direction;

		if (dx2 > MULTI_LIMITER) dx2 = MULTI_LIMITER;
		else if (dx2 < -1*MULTI_LIMITER) dx2 = -1*MULTI_LIMITER;
		if (dy2 > MULTI_LIMITER) dy2 = MULTI_LIMITER;
		else if (dy2 < -1*MULTI_LIMITER) dy2 = -1*MULTI_LIMITER;

		float x2_ = (x0 + dx2);
		float y2_ = (y0 + dy2);
		// then the results are adjusted to the coordinate system of the game
		float scale = 1f;
		if (x2_ > w - 1){
			scale = x2_ / w;
			x2_ = w - 1;
		}
		if (x2_ < 0){
			scale = (-1*x2_ + w) / w;
			x2_ = 0;
		}
		y2_ /= scale;

		scale = 1f;
		if (y2_ > w - 1){
			scale = y2_ / w;
			y2_ = w - 1;
		}
		if (y2_ < 0){
			scale = (-1*y2_ + w) / w;
			y2_ = 0;
		}
		x2_ /= scale;

		x2 = Math.round(x2_);
		y2 = Math.round(y2_);

		int pos2 = x2 + y2*w;

		//GLog.h(Math.round(dx2) + " " + Math.round(dy2) + " " + x2 + " " + y2 + " " + pos2 + " " + Dungeon.heroine.pos % w + " " + Dungeon.heroine.pos / w + " " + w + "\n");

		angleBallistica = new Ballistica(sourcePos, pos2, collisionProperties);
		return angleBallistica;
	}

	//returns a segment of the path from start to end, inclusive.
	//if there is an error, returns an empty arraylist instead.
	public List<Integer> subPath(int start, int end){
		try {
			end = Math.min( end, path.size()-1);
			return path.subList(start, end+1);
		} catch (Exception e){
			ShatteredPixelDungeon.reportException(e);
			return new ArrayList<>();
		}
	}
}
