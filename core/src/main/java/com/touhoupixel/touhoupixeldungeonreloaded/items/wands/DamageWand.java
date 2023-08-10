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

package com.touhoupixel.touhoupixeldungeonreloaded.items.wands;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BossKiller;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.watabou.utils.Random;

//for wands that directly damage a target
//wands with AOE effects count here (e.g. fireblast), but wands with indrect damage do not (e.g. venom, transfusion)
public abstract class DamageWand extends Wand{

	public int min(){
		return min(buffedLvl());
	}

	public abstract int min(int lvl);

	public int max(){
		return max(buffedLvl());
	}

	public abstract int max(int lvl);

	public int damageRoll(){
		return damageRoll(buffedLvl());
	}

	public int damageRoll(int lvl){
		int dmg = Random.NormalIntRange(min(lvl), max(lvl));
		Hero heroine = (Hero) curUser;
		Char enemy = heroine.enemy();
		if (heroine.buff(WandZeroDamage.class) != null){
			dmg *= 0f;
		}
		if (heroine.buff(MagicBuff.class) != null) {
			dmg *= 1.25f;
		}
		if (Statistics.card31) {
			dmg *= 1.25f;
		}
		if (Statistics.card32) {
			dmg *= 1.5f;
		} //blank card
		if (Dungeon.isChallenged(Challenges.LUNATIC_PERFECT) && Statistics.lifelose || Dungeon.isChallenged(Challenges.LUNATIC_PERFECT) && Statistics.spellcarduse){
			dmg *= 0.8f;
		}

		if (Statistics.wand_power_up == 1) {
			dmg *= 1.06f;
		}
		if (Statistics.wand_power_up == 2) {
			dmg *= 1.12f;
		}
		if (Statistics.wand_power_up == 3) {
			dmg *= 1.18f;
		}
		if (Statistics.wand_power_up == 4) {
			dmg *= 1.24f;
		}
		if (Statistics.wand_power_up >= 5) {
			dmg *= 1.3f;
		} //potion of enlightenment

		if (Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.MINIBOSS) ||
			Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.BOSS)){
			dmg *= 2f;
		}

		return dmg;
	}

	@Override
	public String statsDesc() {
		if (levelKnown)
			return Messages.get(this, "stats_desc", min(), max());
		else
			return Messages.get(this, "stats_desc", min(0), max(0));
	}
}
