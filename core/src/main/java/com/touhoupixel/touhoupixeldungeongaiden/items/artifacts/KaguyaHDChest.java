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

package com.touhoupixel.touhoupixeldungeongaiden.items.artifacts;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeedResist;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HeatRiser;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.LockedFloor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfEnergy;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfTransmutation;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collections;

public class KaguyaHDChest extends Artifact {

	{
		image = ItemSpriteSheet.KAGUYA_HD_CHEST;

		levelCap = 10;

		charge = (int)(level()*0.6f)+2;
		partialCharge = 0;
		chargeCap = (int)(level()*0.6f)+2;

		defaultAction = AC_DOUBLE_SPEED;
	}

	public static final String AC_DOUBLE_SPEED = "READ";

	private final ArrayList<Class> scrolls = new ArrayList<>();

	public KaguyaHDChest() {
		super();

		Class<?>[] scrollClasses = Generator.Category.SCROLL.classes;
		float[] probs = Generator.Category.SCROLL.defaultProbs.clone(); //array of primitives, clone gives deep copy.
		int i = Random.chances(probs);

		while (i != -1){
			scrolls.add(scrollClasses[i]);
			probs[i] = 0;

			i = Random.chances(probs);
		}
		scrolls.remove(ScrollOfTransmutation.class);
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (isEquipped(heroine) && charge > 0 && !cursed)
			actions.add(AC_DOUBLE_SPEED);
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action) {
		super.execute(heroine, action);

		if (action.equals(AC_DOUBLE_SPEED)) {
			boolean validMove = false;

			for (int i : PathFinder.NEIGHBOURS4) {
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.pos == curUser.pos + i) {
						validMove = true;
						break;
					}
				}

				if (validMove) {
					break;
				}
			}

			if (!validMove) {
				GLog.w(Messages.get(this, "not_cross"));
			} else if (charge <= 0) {
				GLog.i( Messages.get(this, "no_charge") );
			} else if (!isEquipped(heroine)) {
				GLog.i( Messages.get(Artifact.class, "need_to_equip") );
			} else if (cursed) {
				GLog.i( Messages.get(this, "cursed") );
			} else if (curUser.buff(DoubleSpeed.class) != null){
				GLog.i( Messages.get(this, "already_ds") );
			} else {
				charge--;

				Buff.prolong(curUser, DoubleSpeed.class, DoubleSpeed.DURATION/2f+level()*2);
				if (level() > 4) {
					Buff.prolong(curUser, DoubleSpeedResist.class, DoubleSpeedResist.DURATION);
				}
				if (level() > 8) {
					Buff.prolong(curUser, HeatRiser.class, HeatRiser.DURATION);
				}
				updateQuickslot();

				curUser.spendAndNext(1f);

				Statistics.double_speed_upgrade += 1;

				if (Statistics.double_speed_upgrade > 4) {
					Statistics.double_speed_upgrade = 0;
					if (!(level() == 10)) {
						upgrade();
						GLog.p(Messages.get(this, "ds_upgrade"));
					}
				}
			}
		}
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new bookRecharge();
	}

	@Override
	public void charge(Hero target, float amount) {
		if (charge < chargeCap){
			partialCharge += 0.1f*amount;
			if (partialCharge >= 1){
				partialCharge--;
				charge++;
				updateQuickslot();
			}
		}
	}

	@Override
	public Item upgrade() {
		chargeCap = (int)((level()+1)*0.6f)+2;

		return super.upgrade();
	}

	private static final String SCROLLS =   "scrolls";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( SCROLLS, scrolls.toArray(new Class[scrolls.size()]) );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		scrolls.clear();
		Collections.addAll(scrolls, bundle.getClassArray(SCROLLS));
	}

	public class bookRecharge extends ArtifactBuff{
		@Override
		public boolean act() {
			LockedFloor lock = target.buff(LockedFloor.class);
			if (charge < chargeCap && !cursed && (lock == null || lock.regenOn())) {
				//120 turns to charge at full, 80 turns to charge at 0/8
				float chargeGain = 1 / (120f - (chargeCap - charge)*5f);
				chargeGain *= RingOfEnergy.artifactChargeMultiplier(target);
				partialCharge += chargeGain;

				if (partialCharge >= 1) {
					partialCharge --;
					charge ++;

					if (charge == chargeCap){
						partialCharge = 0;
					}
				}
			}

			updateQuickslot();

			spend( TICK );

			return true;
		}
	}
}
