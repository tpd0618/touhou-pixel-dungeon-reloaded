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
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.LockedFloor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.effects.MagicMissile;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.CellSelector;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.InterlevelScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite.Glowing;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.ui.QuickSlotButton;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class LloydsBeacon extends Artifact {

	public static final float TIME_TO_USE = 1;

	public static final String AC_ZAP       = "ZAP";
	public static final String AC_SET		= "SET";
	public static final String AC_RETURN	= "RETURN";
	
	public int returnFloor = -1;
	public int returnPos;
	
	{
		image = ItemSpriteSheet.ARTIFACT_BEACON;

		levelCap = 3;

		charge = 0;
		chargeCap = 3+level();

		defaultAction = AC_ZAP;
		usesTargeting = true;
	}
	
	private static final String FLOOR = "floor";
	private static final String POS		= "pos";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put(FLOOR, returnFloor);
		if (returnFloor != -1) {
			bundle.put( POS, returnPos );
		}
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		returnFloor = bundle.getInt(FLOOR);
		returnPos	= bundle.getInt( POS );
	}
	
	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add( AC_ZAP );
		actions.add( AC_SET );
		if (returnFloor != -1) {
			actions.add( AC_RETURN );
		}
		return actions;
	}
	
	@Override
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action == AC_SET || action == AC_RETURN) {
			
			if (Dungeon.bossLevel() || !Dungeon.interfloorTeleportAllowed()) {
				heroine.spend( LloydsBeacon.TIME_TO_USE );
				GLog.w( Messages.get(this, "preventing") );
				return;
			}
			
			for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
				Char ch = Actor.findChar(heroine.pos + PathFinder.NEIGHBOURS8[i]);
				if (ch != null && ch.alignment == Char.Alignment.ENEMY) {
					GLog.w( Messages.get(this, "creatures") );
					return;
				}
			}
		}

		if (action == AC_ZAP ){

			curUser = heroine;
			int chargesToUse = Dungeon.floor > 20 ? 2 : 1;

			if (!isEquipped(heroine)) {
				GLog.i( Messages.get(Artifact.class, "need_to_equip") );
				QuickSlotButton.cancel();

			} else if (charge < chargesToUse) {
				GLog.i( Messages.get(this, "no_charge") );
				QuickSlotButton.cancel();

			} else {
				GameScene.selectCell(zapper);
			}

		} else if (action == AC_SET) {
			
			returnFloor = Dungeon.floor;
			returnPos = heroine.pos;
			
			heroine.spend( LloydsBeacon.TIME_TO_USE );
			heroine.busy();
			
			heroine.sprite.operate( heroine.pos );
			Sample.INSTANCE.play( Assets.Sounds.BEACON );
			
			GLog.i( Messages.get(this, "return") );
			
		} else if (action == AC_RETURN) {
			
			if (returnFloor == Dungeon.floor) {
				ScrollOfTeleportation.appear(heroine, returnPos );
				for(Mob m : Dungeon.level.mobs){
					if (m.pos == heroine.pos){
						//displace mob
						for(int i : PathFinder.NEIGHBOURS8){
							if (Actor.findChar(m.pos+i) == null && Dungeon.level.passable[m.pos + i]){
								m.pos += i;
								m.sprite.point(m.sprite.worldToCamera(m.pos));
								break;
							}
						}
					}
				}
				Dungeon.level.occupyCell(heroine);
				Dungeon.observe();
				GameScene.updateFog();
			} else {

				TimekeepersHourglass.timeFreeze timeFreeze = Dungeon.heroine.buff(TimekeepersHourglass.timeFreeze.class);
				if (timeFreeze != null) timeFreeze.disarmPressedTraps();
				Swiftthistle.TimeBubble timeBubble = Dungeon.heroine.buff(Swiftthistle.TimeBubble.class);
				if (timeBubble != null) timeBubble.disarmPressedTraps();

				InterlevelScene.mode = InterlevelScene.Mode.RETURN;
				InterlevelScene.returnFloor = returnFloor;
				InterlevelScene.returnPos = returnPos;
				Game.switchScene( InterlevelScene.class );
			}
			
			
		}
	}

	protected CellSelector.Listener zapper = new  CellSelector.Listener() {

		@Override
		public void onSelect(Integer target) {

			if (target == null) return;

			Invisibility.dispel();
			charge -= Dungeon.scalingFloor() > 20 ? 2 : 1;
			updateQuickslot();

			if (Actor.findChar(target) == curUser){
				ScrollOfTeleportation.teleportChar(curUser);
				curUser.spendAndNext(1f);
			} else {
				final Ballistica bolt = new Ballistica( curUser.pos, target, Ballistica.MAGIC_BOLT );
				final Char ch = Actor.findChar(bolt.collisionPos);

				if (ch == curUser){
					ScrollOfTeleportation.teleportChar(curUser);
					curUser.spendAndNext( 1f );
				} else {
					Sample.INSTANCE.play( Assets.Sounds.ZAP );
					curUser.sprite.zap(bolt.collisionPos);
					curUser.busy();

					MagicMissile.boltFromChar(curUser.sprite.parent,
							MagicMissile.BEACON,
							curUser.sprite,
							bolt.collisionPos,
							new Callback() {
								@Override
								public void call() {
									if (ch != null) {

										int count = 10;
										int pos;
										do {
											pos = Dungeon.level.randomRespawnCell( ch );
											if (count-- <= 0) {
												break;
											}
										} while (pos == -1);

										if (pos == -1 || Dungeon.bossLevel()) {

											GLog.w( Messages.get(ScrollOfTeleportation.class, "no_tele") );

										} else if (ch.properties().contains(Char.Property.IMMOVABLE)) {

											GLog.w( Messages.get(LloydsBeacon.class, "tele_fail") );

										} else  {

											ch.pos = pos;
											if (ch instanceof Mob && ((Mob) ch).state == ((Mob) ch).HUNTING){
												((Mob) ch).state = ((Mob) ch).WANDERING;
											}
											ch.sprite.place(ch.pos);
											ch.sprite.visible = Dungeon.level.heroFOV[pos];

										}
									}
									curUser.spendAndNext(1f);
								}
							});

				}


			}

		}

		@Override
		public String prompt() {
			return Messages.get(LloydsBeacon.class, "prompt");
		}
	};

	@Override
	protected ArtifactBuff passiveBuff() {
		return new beaconRecharge();
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
		if (level() == levelCap) return this;
		chargeCap ++;
		GLog.p( Messages.get(this, "levelup") );
		return super.upgrade();
	}

	@Override
	public String desc() {
		String desc = super.desc();
		if (returnFloor != -1){
			desc += "\n\n" + Messages.get(this, "desc_set", returnFloor);
		}
		return desc;
	}
	
	private static final Glowing WHITE = new Glowing( 0xFFFFFF );
	
	@Override
	public Glowing glowing() {
		return returnFloor != -1 ? WHITE : null;
	}

	public class beaconRecharge extends ArtifactBuff{
		@Override
		public boolean act() {
			LockedFloor lock = target.buff(LockedFloor.class);
			if (charge < chargeCap && !cursed && (lock == null || lock.regenOn())) {
				partialCharge += 1 / (100f - (chargeCap - charge)*10f);

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
