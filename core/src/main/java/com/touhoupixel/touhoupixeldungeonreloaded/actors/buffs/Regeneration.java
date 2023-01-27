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

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.ChaliceOfBlood;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.CrystalKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.GoldenKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.IronKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfEnergy;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Notes;

public class Regeneration extends Buff {
	
	{
		//unlike other buffs, this one acts after the hero and takes priority against other effects
		//healing is much more useful if you get some of it off before taking damage
		actPriority = HERO_PRIO - 1;
	}
	
	private static final float REGENERATION_DELAY = 10;
	
	@Override
	public boolean act() {
		if (target.isAlive()) {

			if (target.HP < regencap() && !((Hero)target).isStarving()) {
				LockedFloor lock = target.buff(LockedFloor.class);
				if (target.HP > 0 && (lock == null || lock.regenOn())) {
					if (Dungeon.isChallenged(Challenges.FLAME_KEYSTONE) && target.HP > 14 && Notes.keyCount(new IronKey(Dungeon.depth)) > 0 || Dungeon.isChallenged(Challenges.FLAME_KEYSTONE) && target.HP > 14 && Notes.keyCount(new GoldenKey(Dungeon.depth)) > 0 || Dungeon.isChallenged(Challenges.FLAME_KEYSTONE) && target.HP > 14 && Notes.keyCount(new CrystalKey(Dungeon.depth)) > 0){
						target.HP -= (Notes.keyCount(new IronKey(Dungeon.depth))*2+Notes.keyCount(new GoldenKey(Dungeon.depth))+Notes.keyCount(new CrystalKey(Dungeon.depth)));
					} else {
						if (target.buff(RegenBlock.class) == null) {
							target.HP += 1;
							if (Statistics.card50 && target.HP + 1 < target.HT) {
								target.HP += 1;
							}
						}
					}
					if (target.HP == regencap()) {
						((Hero) target).resting = false;
					}
				}
			}

			ChaliceOfBlood.chaliceRegen regenBuff = Dungeon.hero.buff( ChaliceOfBlood.chaliceRegen.class);

			float delay = REGENERATION_DELAY;
			if (regenBuff != null) {
				if (regenBuff.isCursed()) {
					delay *= 1.5f;
				} else {
					delay -= regenBuff.itemLevel()*0.9f;
					delay /= RingOfEnergy.artifactChargeMultiplier(target);
				}
			}
			spend( delay );
			
		} else {
			
			diactivate();
			
		}
		
		return true;
	}
	
	public int regencap(){
		return target.HT;
	}
}
