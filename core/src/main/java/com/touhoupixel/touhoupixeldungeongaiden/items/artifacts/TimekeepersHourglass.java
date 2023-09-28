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

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.LockedFloor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfEnergy;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.Trap;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BuffIndicator;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class TimekeepersHourglass extends Artifact {
	private boolean isCard64Purchased = false;

	{
		image = ItemSpriteSheet.ARTIFACT_HOURGLASS;

		levelCap = 5;

		charge = 5+level();
		partialCharge = 0;
		chargeCap = 5+level();

		defaultAction = AC_ACTIVATE;
	}

	public static final String AC_ACTIVATE = "ACTIVATE";

	//keeps track of generated sandbags.
	public int sandBags = 0;

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (isEquipped(heroine) && !cursed && (charge > 0 || activeBuff != null)) {
			actions.add(AC_ACTIVATE);
		}
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action);

		if (action.equals(AC_ACTIVATE)){

			if (!isEquipped(heroine))        GLog.i( Messages.get(Artifact.class, "need_to_equip") );
			else if (activeBuff != null) {
				if (activeBuff instanceof timeStasis) { //do nothing
				} else {
					activeBuff.detach();
					GLog.i( Messages.get(this, "deactivate") );
				}
			} else if (charge <= 0)         GLog.i( Messages.get(this, "no_charge") );
			else if (cursed)                GLog.i( Messages.get(this, "cursed") );
			else GameScene.show(
						new WndOptions(new ItemSprite(this),
								Messages.titleCase(name()),
								Messages.get(this, "prompt"),
								Messages.get(this, "stasis"),
								Messages.get(this, "freeze")) {
							@Override
							protected void onSelect(int index) {
								if (index == 0) {
									GLog.i( Messages.get(TimekeepersHourglass.class, "onstasis") );
									GameScene.flash(0x80FFFFFF);
									Sample.INSTANCE.play(Assets.Sounds.TELEPORT);

									activeBuff = new timeStasis();
									activeBuff.attachTo(Dungeon.heroine);
								} else if (index == 1) {
									GLog.i( Messages.get(TimekeepersHourglass.class, "onfreeze") );
									GameScene.flash(0x80FFFFFF);
									Sample.INSTANCE.play(Assets.Sounds.TELEPORT);

									activeBuff = new timeFreeze();
									activeBuff.attachTo(Dungeon.heroine);
									((timeFreeze)activeBuff).processTime(0f);
								}
							}
						}
				);
		}
	}

	@Override
	public void activate(Char ch) {
		super.activate(ch);
		if (activeBuff != null)
			activeBuff.attachTo(ch);
	}

	@Override
	public boolean doUnequip(Hero heroine, boolean collect, boolean single) {
		if (super.doUnequip(heroine, collect, single)){
			if (activeBuff != null){
				activeBuff.detach();
				activeBuff = null;
			}
			return true;
		} else
			return false;
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new hourglassRecharge();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (charge < chargeCap){
			partialCharge += 0.25f*amount;
			if (partialCharge >= 1){
				partialCharge--;
				charge++;
				updateQuickslot();
			}
		}
	}

	@Override
	public Item upgrade() {
		chargeCap+= 1;

		//for artifact transmutation.
		while (level()+1 > sandBags)
			sandBags ++;

		return super.upgrade();
	}
	public void buyCard64AndAffect(){ //the method is triggered when the card is purchased and when the save is loaded
		chargeCap += 2;

		isCard64Purchased = true;
	}

	@Override
	public String desc() {
		String desc = super.desc();

		if (isEquipped( Dungeon.heroine)){
			if (!cursed) {
				if (level() < levelCap )
					desc += "\n\n" + Messages.get(this, "desc_hint");

			} else
				desc += "\n\n" + Messages.get(this, "desc_cursed");
		}
		return desc;
	}


	private static final String SANDBAGS =  "sandbags";
	private static final String BUFF =      "buff";
	private static final String IS_CARD64_PURCHASED = "is_card64_purchased";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( SANDBAGS, sandBags );
		bundle.put(IS_CARD64_PURCHASED, isCard64Purchased);

		if (activeBuff != null)
			bundle.put( BUFF , activeBuff );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		isCard64Purchased = bundle.getBoolean( IS_CARD64_PURCHASED );
		if (isCard64Purchased) buyCard64AndAffect();
		super.restoreFromBundle(bundle);
		sandBags = bundle.getInt( SANDBAGS );

		//these buffs belong to hourglass, need to handle unbundling within the hourglass class.
		if (bundle.contains( BUFF )){
			Bundle buffBundle = bundle.getBundle( BUFF );

			if (buffBundle.contains( timeFreeze.PRESSES ))
				activeBuff = new timeFreeze();
			else
				activeBuff = new timeStasis();

			activeBuff.restoreFromBundle(buffBundle);
		}
	}

	public class hourglassRecharge extends ArtifactBuff {
		@Override
		public boolean act() {

			LockedFloor lock = target.buff(LockedFloor.class);
			if (charge < chargeCap && !cursed && (lock == null || lock.regenOn())) {
				//90 turns to charge at full, 60 turns to charge at 0/10
				float chargeGain = 1 / (90f - (chargeCap - charge)*3f);
				chargeGain *= RingOfEnergy.artifactChargeMultiplier(target);
				partialCharge += chargeGain;

				if (partialCharge >= 1) {
					partialCharge --;
					charge ++;

					if (charge == chargeCap){
						partialCharge = 0;
					}
				}
			} else if (cursed && Random.Int(10) == 0)
				((Hero) target).spend( TICK );

			updateQuickslot();

			spend( TICK );

			return true;
		}
	}

	public class timeStasis extends ArtifactBuff {
		
		{
			type = buffType.POSITIVE;
			actPriority = BUFF_PRIO-3; //acts after all other buffs, so they are prevented
		}

		@Override
		public boolean attachTo(Char target) {

			if (super.attachTo(target)) {

				Invisibility.dispel();

				int usedCharge = Math.min(charge, 2);
				//buffs always act last, so the stasis buff should end a turn early.
				spend(5*usedCharge);

				//shouldn't punish the player for going into stasis frequently
				Hunger hunger = Buff.affect(target, Hunger.class);
				if (hunger != null && !hunger.isStarving()) {
					hunger.satisfy(5 * usedCharge);
				}

				charge -= usedCharge;

				target.invisible++;
				target.paralysed++;
				target.next();

				updateQuickslot();

				if (Dungeon.heroine != null) {
					Dungeon.observe();
				}

				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean act() {
			detach();
			return true;
		}

		@Override
		public void detach() {
			if (target.invisible > 0) target.invisible--;
			if (target.paralysed > 0) target.paralysed--;
			super.detach();
			activeBuff = null;
			Dungeon.observe();
		}

		@Override
		public void fx(boolean on) {
			if (on) target.sprite.add( CharSprite.State.INVISIBLE );
			else if (target.invisible == 0) target.sprite.remove( CharSprite.State.INVISIBLE );
		}
	}

	public class timeFreeze extends ArtifactBuff {
		
		{
			type = buffType.POSITIVE;
		}

		float turnsToCost = 0f;

		ArrayList<Integer> presses = new ArrayList<>();

		public void processTime(float time){
			turnsToCost -= time;

			//use 1/1,000 to account for rounding errors
			while (turnsToCost < -0.001f){
				turnsToCost += 2f;
				charge --;
			}

			updateQuickslot();

			if (charge < 0){
				charge = 0;
				detach();
			}

		}

		public void setDelayedPress(int cell){
			if (!presses.contains(cell))
				presses.add(cell);
		}

		public void triggerPresses(){
			for (int cell : presses)
				Dungeon.level.pressCell(cell);

			presses = new ArrayList<>();
		}

		public void disarmPressedTraps(){
			for (int cell : presses){
				Trap t = Dungeon.level.traps.get(cell);
				if (t != null && t.disarmedByActivation) t.disarm();
			}

			presses = new ArrayList<>();
		}

		@Override
		public void detach(){
			updateQuickslot();
			super.detach();
			activeBuff = null;
			triggerPresses();
			target.next();
			// for Sakuya
			if (Dungeon.heroine.heroClass == HeroClass.PLAYERSAKUYA && Dungeon.heroine.buff(Swiftthistle.TimeBubble.class) == null){MissileWeapon.castAfterTimeFreeze();}
		}

		@Override
		public void fx(boolean on) {
			Emitter.freezeEmitters = on;
			if (on){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.sprite != null) mob.sprite.add(CharSprite.State.PARALYSED);
				}
			} else {
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.paralysed <= 0) mob.sprite.remove(CharSprite.State.PARALYSED);
				}
			}
		}

		@Override
		public int icon() {
			return BuffIndicator.TIME;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(1f, 0.5f, 0);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (2f - (turnsToCost+1)) / 2f);
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString((int)turnsToCost+1);
		}

		@Override
		public String toString() {
			return Messages.get(this, "name");
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc");
		}

		private static final String PRESSES = "presses";
		private static final String TURNSTOCOST = "turnsToCost";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);

			int[] values = new int[presses.size()];
			for (int i = 0; i < values.length; i ++)
				values[i] = presses.get(i);
			bundle.put( PRESSES , values );

			bundle.put( TURNSTOCOST , turnsToCost);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);

			int[] values = bundle.getIntArray( PRESSES );
			for (int value : values)
				presses.add(value);

			turnsToCost = bundle.getFloat( TURNSTOCOST );
		}
	}

	public static class sandBag extends Item {

		{
			image = ItemSpriteSheet.HOURGLASS_UP;
		}

		@Override
		public boolean doPickUp(Hero heroine, int pos) {
			TimekeepersHourglass hourglass = heroine.belongings.getItem( TimekeepersHourglass.class );
			if (hourglass != null && !hourglass.cursed) {
				hourglass.upgrade();
				Sample.INSTANCE.play( Assets.Sounds.DEWDROP );
				if (hourglass.level() == hourglass.levelCap)
					GLog.p( Messages.get(this, "maxlevel") );
				else
					GLog.i( Messages.get(this, "levelup") );
				GameScene.pickUp(this, pos);
				heroine.spendAndNext(TIME_TO_PICK_UP);
				return true;
			} else {
				GLog.w( Messages.get(this, "no_hourglass") );
				return false;
			}
		}

		@Override
		public int value() {
			return 20;
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


}
