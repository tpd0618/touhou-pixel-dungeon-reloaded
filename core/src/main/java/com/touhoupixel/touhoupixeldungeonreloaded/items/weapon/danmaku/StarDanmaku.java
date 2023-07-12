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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MissileSprite;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

public class StarDanmaku extends MissileWeapon {
	
	{
		image = ItemSpriteSheet.STAR_DANMAKU;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch = 1f;
		
		tier = 4;
		sticky = false;
	}

	@Override
	public int max(int lvl) {
		return  (6+Dungeon.heroine.lvl/3) * tier +                      //base
				(tier == 1 ? 2*lvl : tier*lvl); //level scaling
	}
	
	@Override
	protected void rangedHit(Char enemy, int cell) {
		decrementDurability();
		if (durability > 0){
			Buff.append(Dungeon.heroine, CircleBack.class).setup(this, cell, Dungeon.heroine.pos, Dungeon.floor);
		}
	}
	
	@Override
	protected void rangedMiss(int cell) {
		parent = null;
		Buff.append(Dungeon.heroine, CircleBack.class).setup(this, cell, Dungeon.heroine.pos, Dungeon.floor);
	}
	
	public static class CircleBack extends Buff {

		{
			revivePersists = true;
		}
		
		private MissileWeapon boomerang;
		private int thrownPos;
		private int returnPos;
		private int returnFloor;
		
		private int left;
		
		public void setup( MissileWeapon boomerang, int thrownPos, int returnPos, int returnDepth){
			this.boomerang = boomerang;
			this.thrownPos = thrownPos;
			this.returnPos = returnPos;
			this.returnFloor = returnDepth;
			left = 3;
		}
		
		public int returnPos(){
			return returnPos;
		}
		
		public MissileWeapon cancel(){
			detach();
			return boomerang;
		}

		public int activeFloor(){
			return returnFloor;
		}
		
		@Override
		public boolean act() {
			if (returnFloor == Dungeon.floor){
				left--;
				if (left <= 0){
					final Char returnTarget = Actor.findChar(returnPos);
					final Char target = this.target;
					MissileSprite visual = ((MissileSprite) Dungeon.heroine.sprite.parent.recycle(MissileSprite.class));
					visual.reset( thrownPos,
									returnPos,
									boomerang,
									new Callback() {
										@Override
										public void call() {
											if (returnTarget == target){
												if (target instanceof Hero && boomerang.doPickUp((Hero) target)) {
													//grabbing the boomerang takes no time
													((Hero) target).spend(-TIME_TO_PICK_UP);
												} else {
													Dungeon.level.drop(boomerang, returnPos).sprite.drop();
												}
												
											} else if (returnTarget != null){
												if (((Hero)target).shoot( returnTarget, boomerang )) {
													boomerang.decrementDurability();
												}
												if (boomerang.durability > 0) {
													Dungeon.level.drop(boomerang, returnPos).sprite.drop();
												}
												
											} else {
												Dungeon.level.drop(boomerang, returnPos).sprite.drop();
											}
											CircleBack.this.next();
										}
									});
					visual.alpha(0f);
					float duration = Dungeon.level.trueDistance(thrownPos, returnPos) / 20f;
					target.sprite.parent.add(new AlphaTweener(visual, 1f, duration));
					detach();
					return false;
				}
			}
			spend( TICK );
			return true;
		}
		
		private static final String BOOMERANG = "boomerang";
		private static final String THROWN_POS = "thrown_pos";
		private static final String RETURN_POS = "return_pos";
		private static final String RETURN_FLOOR = "return_floor";
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(BOOMERANG, boomerang);
			bundle.put(THROWN_POS, thrownPos);
			bundle.put(RETURN_POS, returnPos);
			bundle.put(RETURN_FLOOR, returnFloor);
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			boomerang = (MissileWeapon) bundle.get(BOOMERANG);
			thrownPos = bundle.getInt(THROWN_POS);
			returnPos = bundle.getInt(RETURN_POS);
			returnFloor = bundle.getInt(RETURN_FLOOR);
		}
	}
}