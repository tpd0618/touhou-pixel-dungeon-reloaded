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

package com.touhoupixel.touhoupixeldungeongaiden.items;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HexCancel;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HomingBlade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MagicBuff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.RouletteStop;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Terrain;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class TrickCloak extends Item {

	private static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.ARMOR_JOKER;

		defaultAction = AC_DRINK;

		stackable = true;
		unique = true;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add(AC_DRINK);
		return actions;
	}

	@Override
	public void execute(final Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals(AC_DRINK)) {
			if (Dungeon.level.map[curUser.pos] == Terrain.EMPTY){
				//A safer, low-return bet
				curUser.spendAndNext(1f);
				curItem.detach(curUser.belongings.backpack);
				Buff.prolong(curUser, RouletteStop.class, RouletteStop.DURATION);
				if (Random.Int(2) == 0){
					//bad
					switch (Random.Int(5)) {
						case 0:
						default:
							Buff.prolong(curUser, Weakness.class, Weakness.DURATION);
							GLog.n( Messages.get(this, "bad_1") );
							break;
						case 1:
							Buff.prolong(curUser, Vulnerable.class, Vulnerable.DURATION);
							GLog.n( Messages.get(this, "bad_2") );
							break;
						case 2:
							Buff.prolong(curUser, Hex.class, Hex.DURATION);
							GLog.n( Messages.get(this, "bad_3") );
							break;
						case 3:
							Buff.prolong(curUser, Cripple.class, Cripple.DURATION);
							GLog.n( Messages.get(this, "bad_4") );
							break;
						case 4:
							Buff.prolong(curUser, PotionFreeze.class, PotionFreeze.DURATION);
							GLog.n( Messages.get(this, "bad_5") );
							break;
					}
				} else {
					//good
					switch (Random.Int(5)) {
						case 0:
						default:
							Dungeon.gold += 500;
							Dungeon.energy += 5;
							GLog.p( Messages.get(this, "good_1") );
							break;
						case 1:
							Buff.prolong(curUser, Might.class, Might.DURATION);
							GLog.p( Messages.get(this, "good_2") );
							break;
						case 2:
							Buff.prolong(curUser, Bless.class, Bless.DURATION);
							GLog.p( Messages.get(this, "good_3") );
							break;
						case 3:
							Buff.prolong(curUser, MagicBuff.class, MagicBuff.DURATION);
							GLog.p( Messages.get(this, "good_4") );
							break;
						case 4:
							Buff.prolong(curUser, HexCancel.class, HexCancel.DURATION);
							GLog.p( Messages.get(this, "good_5") );
							break;
					}
				}
			} else if (Dungeon.level.map[curUser.pos] == Terrain.EMPTY_SP){
				//A risky, higher-return bet
				curUser.spendAndNext(1f);
				curItem.detach(curUser.belongings.backpack);
				Buff.prolong(curUser, RouletteStop.class, RouletteStop.DURATION);
				if (Random.Int(2) == 0){
					//bad
					switch (Random.Int(5)) {
						case 0:
						default:
							Dungeon.gold = 0;
							Dungeon.energy = 0;
							GLog.n( Messages.get(this, "bad_6") );
							break;
						case 1:
							Buff.prolong(curUser, ExtremeConfusion.class, ExtremeConfusion.DURATION);
							GLog.n( Messages.get(this, "bad_7") );
							break;
						case 2:
							Buff.prolong(curUser, CursedBlow.class, CursedBlow.DURATION);
							GLog.n( Messages.get(this, "bad_8") );
							break;
						case 3:
							Buff.prolong(curUser, Degrade.class, Degrade.DURATION);
							GLog.n( Messages.get(this, "bad_9") );
							break;
						case 4:
							Buff.prolong(curUser, Inversion.class, Inversion.DURATION);
							GLog.n( Messages.get(this, "bad_10") );
							break;
					}
				} else {
					//good
					switch (Random.Int(5)) {
						case 0:
						default:
							Dungeon.gold += 1500;
							Dungeon.energy += 15;
							GLog.p( Messages.get(this, "good_6") );
							break;
						case 1:
							Buff.prolong(curUser, Hisou.class, Hisou.DURATION);
							GLog.p( Messages.get(this, "good_7") );
							break;
						case 2:
							Buff.prolong(curUser, Doublerainbow.class, Doublerainbow.DURATION);
							GLog.p( Messages.get(this, "good_8") );
							break;
						case 3:
							Buff.prolong(curUser, Haste.class, Haste.DURATION);
							GLog.p( Messages.get(this, "good_9") );
							break;
						case 4:
							Buff.prolong(curUser, HomingBlade.class, HomingBlade.DURATION);
							GLog.p( Messages.get(this, "good_10") );
							break;
					}
				}
			} else {
				GLog.n( Messages.get(this, "no_effect") );
			}
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}
}