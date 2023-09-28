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

package com.touhoupixel.touhoupixeldungeongaiden.items.wands;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Blizzard;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.ConfusionGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.CorrosiveGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Inferno;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.ParalyticGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.SmokeScreen;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.StormCloud;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.ToxicGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.MagicMissile;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SanaeExorcismRod extends Wand {
	private static String AC_SHOOT_PROJECTILE_1	= "PROJECTILE_1";
	private static String AC_SHOOT_PROJECTILE_2	= "PROJECTILE_2";
	private static String AC_CHOOSE = "CHOOSE"; // action that works if the weapon is in a quick slot
	private Blobs blobContainer[] = {setEffect(), setEffect()};
	private Blobs curProj;
	private static final float ROD_SCALE_FACTOR = 0.75f;


	{
		defaultAction = AC_CHOOSE;
		image = ItemSpriteSheet.SANAE_EXORCISM_ROD;
		collisionProperties = Ballistica.PROJECTILE;
		unique = true;
		bones = false;
		maxCharges = 3;
		curCharges = 3;
	}
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.remove(AC_ZAP);
		actions.add(AC_SHOOT_PROJECTILE_1);
		actions.add(AC_SHOOT_PROJECTILE_2);
		return actions;
	}
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action.equals( AC_SHOOT_PROJECTILE_1 )) {
			curUser = heroine;
			curItem = this;
			curProj = blobContainer[0];
			GameScene.selectCell( zapper );
		}
		if (action.equals( AC_SHOOT_PROJECTILE_2 )) {
			curUser = heroine;
			curItem = this;
			curProj = blobContainer[1];
			GameScene.selectCell( zapper );
		}
		if (action.equals(AC_CHOOSE)){
			SanaeExorcismRod miracleRod = this;
			GameScene.show(
					new WndOptions(
							Messages.get(this, "chooseGas"),
							"",
							getBlobName(blobContainer[0]).toUpperCase(),
							getBlobName(blobContainer[1]).toUpperCase()
					){
						protected void onSelect(int index){
							if (index == 0) {
								curUser = heroine;
								curItem = miracleRod;
								curProj = blobContainer[0];
								GameScene.selectCell( zapper );
							}
							else{
								curUser = heroine;
								curItem = miracleRod;
								curProj = blobContainer[1];
								GameScene.selectCell( zapper );
							}
						}
					}
			);
		}
	}
	public String actionName(String action, Hero heroine){
		if (action.equals(AC_SHOOT_PROJECTILE_1))
			return getBlobName(blobContainer[0]).toUpperCase();
		else if (action.equals(AC_SHOOT_PROJECTILE_2)) {
			return getBlobName(blobContainer[1]).toUpperCase();
		}
		else {
			return super.actionName(action, heroine);
		}
	}
	public enum Blobs{
		BLIZZARD,
		CONFUSION_GAS,
		CORROSIVE_GAS,
		INFERNO,
		PARALITIC_GAS,
		SMOKE,
		STORM_CLOUDS,
		TOXIC_GAS;
	}

	public Blobs setEffect(){
		Blobs blob;
		/* The chance of getting:
		   * storm clouds - 22%
		   * every each gas - 11%
		   * */
		int numbersOfBlobs[] = {1, 1, 1, 1, 1, 1, 2, 1};
		int summNum = 0;
		for (int num : numbersOfBlobs) summNum += num;
		int rngNum = Random.Int(summNum) + 1;
		summNum = 0;
		int indexBlob;
		for (indexBlob = 0; summNum < rngNum; indexBlob++){
			summNum += numbersOfBlobs[indexBlob];
		}
		indexBlob--;
		switch (indexBlob){
			case 0:
				blob = Blobs.BLIZZARD;
				break;
			case 1:
				blob = Blobs.CONFUSION_GAS;
				break;
			case 2:
				blob = Blobs.CORROSIVE_GAS;
				break;
			case 3:
				blob = Blobs.INFERNO;
				break;
			case 4:
				blob = Blobs.PARALITIC_GAS;
				break;
			case 5:
				blob = Blobs.SMOKE;
				break;
			case 6:
				blob = Blobs.STORM_CLOUDS;
				break;
			case 7:
				blob = Blobs.TOXIC_GAS;
				break;
			default:
				blob = Blobs.STORM_CLOUDS;
				break;
		}
		return blob;
	}
	public String getBlobName(Blobs blob){
		String mes;
		switch (blob){
			case BLIZZARD:
				mes = "blizzard";
				break;
			case CONFUSION_GAS:
				mes = "confusion_gas";
				break;
			case CORROSIVE_GAS:
				mes = "corrosive_gas";
				break;
			case INFERNO:
				mes = "inferno";
				break;
			case PARALITIC_GAS:
				mes = "paralitic_gas";
				break;
			case SMOKE:
				mes = "smoke";
				break;
			case STORM_CLOUDS:
				mes = "storm_clouds";
				break;
			case TOXIC_GAS:
				mes = "toxic_gas";
				break;
			default:
				mes = "";
				break;
		}
		return Messages.get(this, mes);
	}
	public String info() {
		String desc = desc();

		desc += "\n\n" + Messages.get(this, "stats_desc_1");
		desc += "\n1. " + getBlobName(blobContainer[0]) + "\n2. " + getBlobName(blobContainer[1]);
		desc += "\n\n" + Messages.get(this, "stats_desc_2");

		return desc;
	}

	@Override
	public void onZap(Ballistica bolt) {
		Blob gas;
		switch (curProj){
			case STORM_CLOUDS: default:
				gas = Blob.seed( bolt.collisionPos, 180, StormCloud.class );
				break;
			case BLIZZARD:
				gas = Blob.seed( bolt.collisionPos, 120, Blizzard.class );
				break;
			case CONFUSION_GAS:
				gas = Blob.seed( bolt.collisionPos, 500, ConfusionGas.class );
				break;
			case CORROSIVE_GAS:
				gas = Blob.seed( bolt.collisionPos, 50, CorrosiveGas.class );
				CellEmitter.get(bolt.collisionPos).burst(Speck.factory(Speck.CORROSION), 10 );
				((CorrosiveGas)gas).setStrength(2 + Dungeon.floor / 2);
				break;
			case INFERNO:
				gas = Blob.seed( bolt.collisionPos, 120, Inferno.class );
				break;
			case PARALITIC_GAS:
				gas = Blob.seed( bolt.collisionPos, 500, ParalyticGas.class );
				break;
			case SMOKE:
				gas = Blob.seed( bolt.collisionPos, 180, SmokeScreen.class );
				break;
			case TOXIC_GAS:
				gas = Blob.seed( bolt.collisionPos, 500, ToxicGas.class );
				break;
		}
		GameScene.add(gas);
		Sample.INSTANCE.play(Assets.Sounds.GAS);

		for (int i : PathFinder.NEIGHBOURS9) {
			Char ch = Actor.findChar(bolt.collisionPos + i);
			if (ch != null) {
				wandProc(ch, chargesPerCast());
			}
		}

		if (Actor.findChar(bolt.collisionPos) == null){
			Dungeon.level.pressCell(bolt.collisionPos);
		}
		for (int id = 0; id < 2; id++){
			if (curProj == blobContainer[id]){
				blobContainer[id] = setEffect();
				break;
			}
		}
	}
	public void fx(Ballistica bolt, Callback callback){
		int projType;
		switch (curProj){
			case STORM_CLOUDS: default:
				projType = MagicMissile.BEACON;
				break;
			case BLIZZARD:
				projType = MagicMissile.FROST;
				break;
			case CONFUSION_GAS:
				projType = MagicMissile.RAINBOW;
				break;
			case CORROSIVE_GAS:
				projType = MagicMissile.CORROSION;
				break;
			case INFERNO:
				projType = MagicMissile.FIRE;
				break;
			case PARALITIC_GAS:
				projType = MagicMissile.EARTH;
				break;
			case SMOKE:
				projType = MagicMissile.SHADOW;
				break;
			case TOXIC_GAS:
				projType = MagicMissile.TOXIC_VENT;
				break;
		}
		MagicMissile.boltFromChar(
				curUser.sprite.parent,
				projType,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play(Assets.Sounds.ZAP);
	}

	private static final String BLOB_CONTAINER_1 = "blob_container_1";
	private static final String BLOB_CONTAINER_2 = "blob_container_2";
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put(BLOB_CONTAINER_1, blobContainer[0]);
		bundle.put(BLOB_CONTAINER_2, blobContainer[1]);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		blobContainer[0] = bundle.getEnum(BLOB_CONTAINER_1, Blobs.class);
		blobContainer[1] = bundle.getEnum(BLOB_CONTAINER_2, Blobs.class);
	}
	@Override
	public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {
		// empty
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public int buffedLvl() {
		return level();
	}

	@Override
	public int level() {
		int level = Dungeon.heroine == null ? 0 : Dungeon.heroine.lvl/5;
		maxCharges = Math.min(3 + level, 10);
		if (Statistics.card46) maxCharges += 2;
		level = Math.min(7, level);

		return level;
	}
	public void activate( Char ch ) {
		applyWandChargeBuff(ch);
	}
	public boolean collect( Bag container ) {
		if (super.collect(container)) {
			if (container.owner != null) {
				applyWandChargeBuff(container.owner);
			}
			return true;
		} else {
			return false;
		}
	}
	public void applyWandChargeBuff(Char owner){
		if (this != null){
			this.charge(owner, ROD_SCALE_FACTOR);
		}
	}
}