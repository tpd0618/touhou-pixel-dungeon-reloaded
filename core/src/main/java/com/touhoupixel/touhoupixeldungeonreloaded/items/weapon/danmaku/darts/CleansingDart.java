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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.darts;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FlavourBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfCleansing;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class CleansingDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.CLEANSING_DAST;
	}
	
	@Override
	public int proc(Char attacker, final Char defender, int damage) {

		if (attacker.alignment == defender.alignment){
			PotionOfCleansing.cleanse(defender, PotionOfCleansing.Cleanse.DURATION*2f);
			return 0;
		} else {
			for (Buff b : defender.buffs()){
			}
			//for when cleansed effects were keeping defender alive (e.g. raging brutes)
			if (!defender.isAlive()){
				defender.die(attacker);
				return super.proc(attacker, defender, damage);
			}
			if (defender instanceof Mob) {
				//need to delay this so damage from the dart doesn't break wandering
				new FlavourBuff(){
					{actPriority = VFX_PRIO;}
					public boolean act() {
						if (((Mob) defender).state == ((Mob) defender).HUNTING || ((Mob) defender).state == ((Mob) defender).FLEEING){
							((Mob) defender).state = ((Mob) defender).WANDERING;
						}
						((Mob) defender).beckon(Dungeon.level.randomDestination(defender));
						defender.sprite.showLost();
						return super.act();
					}
				}.attachTo(defender);
			}
		}

		return super.proc(attacker, defender, damage);
	}
}
