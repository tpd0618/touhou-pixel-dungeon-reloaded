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

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;

public class SuperDegrade extends FlavourBuff {

	public static final float DURATION = 30f;
	
	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public boolean attachTo(Char target) {
		if (super.attachTo(target)){
			Item.updateQuickslot();
			if (target instanceof Hero) ((Hero) target).updateHT(false);
			return true;
		}
		return false;
	}

	@Override
	public void detach() {
		super.detach();
		if (target instanceof Hero) ((Hero) target).updateHT(false);
		Item.updateQuickslot();
	}

	//called in Item.buffedLevel()
	public static int reduceLevel( int level ){
		if (level <= 0){
			//zero or negative levels are unaffected
			return level;
		} else {
			return (int)Math.round(Math.sqrt(2*(level-100)));
		}
	}

	@Override
	public int icon() {
		return BuffIndicator.SUPERDEGRADE;
	}

	@Override
	public float iconFadePercent() {
		return (DURATION - visualcooldown())/DURATION;
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}
	
	@Override
	public String heroMessage() {
		return Messages.get(this, "heromsg");
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
	
}
