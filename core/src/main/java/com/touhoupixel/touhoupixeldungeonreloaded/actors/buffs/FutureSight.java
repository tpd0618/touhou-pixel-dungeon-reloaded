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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Sakuya;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.SakuyaKnifeDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MissileSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.watabou.utils.Callback;

public class FutureSight extends FlavourBuff {

	public static final float DURATION = 5f;

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public void detach() {
		//Dungeon.heroine.damage(15, Sakuya.class);
		summonKnives(target);

		super.detach();
	}
	private void summonKnives(Char target){
		SakuyaKnifeDanmaku misWeapon = new SakuyaKnifeDanmaku(40, 60, 1f);
		int arr[] = {-2, 2};
		for (int x : arr){
			for (int y : arr){
				int startPos = Dungeon.level.width()*y + x;
				Ballistica bal = new Ballistica(target.pos + startPos, target.pos, Ballistica.PROJECTILE);
				if (Dungeon.heroine.sprite == null) return;
				MissileSprite misSpr = ((MissileSprite) Dungeon.heroine.sprite.parent.recycle(MissileSprite.class));
				misSpr.reset(bal.sourcePos, bal.collisionPos, misWeapon, new Callback() {
					@Override
					public void call() {
						if (misWeapon != null) misWeapon.onThrowByChar(new Sakuya(), bal.collisionPos );
					}
				});
			}
		}
	}

	@Override
	public int icon() {
		return BuffIndicator.FUTURE_SIGHT;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
}