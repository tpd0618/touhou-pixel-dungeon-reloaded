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

package com.touhoupixel.touhoupixeldungeongaiden.items.herbs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Challenges;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.CheatBreak;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HerbDegrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.BossSeija;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class Herb extends Item {

	public static final float TIME_TO_EAT	= 1f;

	public static final String AC_EAT	= "EAT";

	{
		stackable = true;

		bones = true;

		defaultAction = AC_EAT;
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add( AC_EAT );
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action.equals( AC_EAT )) {

			detach(heroine.belongings.backpack);

			heroine.sprite.operate(heroine.pos);
			heroine.busy();
			SpellSprite.show(heroine, SpellSprite.FOOD);
			Sample.INSTANCE.play(Assets.Sounds.EAT);

			if (Dungeon.isChallenged(Challenges.CALL_THE_SHOTS)) {
				Statistics.mood += 1;
			}

			if (heroine.buff(HerbDegrade.class) != null){
				Buff.prolong(curUser, Degrade.class, Degrade.DURATION);
			}

			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
					if (mob instanceof BossSeija) {
						Buff.prolong(mob, CheatBreak.class, CheatBreak.DURATION);
						Buff.detach(mob, SuperHard.class);
						GLog.p( Messages.get(Potion.class, "cheat_break") );
					}
				}
			} //for boss seija

			heroine.spend(eatingTime());
		}
	}

	protected float eatingTime(){
		return TIME_TO_EAT;
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int value() {
		return 15 * quantity;
	}
}