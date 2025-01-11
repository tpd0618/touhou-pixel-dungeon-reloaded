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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class HalfLevel extends FlavourBuff {

	public static final float DURATION = 30f;

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	public int level_reduction = (int) (Dungeon.heroine.lvl/2f);
	
	@Override
	public int icon() {
		return BuffIndicator.HALF_LEVEL;
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
	public boolean attachTo(Char target) {
		if (super.attachTo(target)){
			if (target instanceof Hero || target.buff(HalfLevel.class) == null) {
				Dungeon.heroine.lvl -= level_reduction;
				Dungeon.heroine.updateHT( true );
			}
			return true;
		}
		return false;
	}

	@Override
	public void detach() {
		super.detach();
		Dungeon.heroine.lvl += level_reduction;
		Dungeon.heroine.updateHT( true );
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
}
