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

package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DismantleReady;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.KeyHeal;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeonreloaded.items.journal.Guidebook;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.JoonFan;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.ShionFan;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Document;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public abstract class EquipableItem extends Item {

	public static final String AC_EQUIP		= "EQUIP";
	public static final String AC_UNEQUIP	= "UNEQUIP";
	public static final String AC_DISMANTLE	= "DISMANTLE";

	{
		bones = true;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add( isEquipped(heroine) ? AC_UNEQUIP : AC_EQUIP );
		actions.add( AC_DISMANTLE );
		return actions;
	}

	@Override
	public boolean doPickUp(Hero heroine, int pos) {
		if (super.doPickUp(heroine, pos)){
			if (!isIdentified() && !Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_IDING)){
				GLog.p(Messages.get(Guidebook.class, "hint"));
				GameScene.flashForDocument(Document.GUIDE_IDING);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action.equals( AC_EQUIP )) {

			if (curItem instanceof ShionFan && Statistics.highestFloor < 21){
				Statistics.difficulty = 1;
				GLog.w(Messages.get(EquipableItem.class, "shion"));
			}

			if (curItem instanceof JoonFan && Statistics.highestFloor < 21){
				Statistics.difficulty = 5;
				GLog.w(Messages.get(EquipableItem.class, "joon"));
			}
			//In addition to equipping itself, item reassigns itself to the quickslot
			//This is a special case as the item is being removed from inventory, but is staying with the hero.
			int slot = Dungeon.quickslot.getSlot( this );
			doEquip(heroine);
			if (slot != -1) {
				Dungeon.quickslot.setSlot( slot, this );
				updateQuickslot();
			}
		} else if (action.equals( AC_UNEQUIP )) {
			doUnequip(heroine, true );
		}
		if (action.equals(AC_DISMANTLE)){
			if (heroine.buff(DismantleReady.class) == null) {
				Buff.prolong(heroine, DismantleReady.class, DismantleReady.DURATION);
				GLog.w(Messages.get(this, "dismantle_ready"));
			} else {
				if (heroine.buff(Degrade.class) != null) {
					GLog.w(Messages.get(this, "degrade"));
				} else if (curItem.isEquipped(heroine)) {
					GLog.w(Messages.get(this, "unequip_first"));
				} else if (curItem instanceof Artifact) {
					GLog.w(Messages.get(this, "artifact"));
				} else if (curItem instanceof MissileWeapon) {
					GLog.w(Messages.get(this, "danmaku"));
				} else {
					curItem.detach(curUser.belongings.backpack);
					if (curItem.level() > 0) {
						Dungeon.level.drop(new UpgradeCard().quantity(curItem.level()), curUser.pos).sprite.drop();
					} else {
						Dungeon.energy += 3;
					}
					Buff.detach(curUser, DismantleReady.class);
					Buff.detach(curUser, DismantlePressure.class);
					updateQuickslot();

					heroine.spend(1f);

					Statistics.dismantle_count += 1;

					if (Statistics.card33 && Random.Int(3) == 0) {
						Buff.prolong(heroine, KeyHeal.class, KeyHeal.DURATION / 3f);
					}

					Sample.INSTANCE.play(Assets.Sounds.DRINK);
					curUser.sprite.operate(curUser.pos);
				}
			}
		}
	}

	@Override
	public void doDrop( Hero heroine) {
		if (!isEquipped(heroine) || doUnequip(heroine, false, false )) {
			super.doDrop(heroine);
		}
	}

	@Override
	public void cast(final Hero user, int dst ) {

		if (isEquipped( user )) {
			if (quantity == 1 && !this.doUnequip( user, false, false )) {
				return;
			}
		}

		super.cast( user, dst );
	}

	public static void equipCursed( Hero heroine) {
		heroine.sprite.emitter().burst( ShadowParticle.CURSE, 6 );
		Sample.INSTANCE.play( Assets.Sounds.CURSED );
	}

	protected float time2equip( Hero heroine) {
		return 1;
	}

	public abstract boolean doEquip( Hero heroine);

	public boolean doUnequip(Hero heroine, boolean collect, boolean single ) {

		if (cursed || keptThoughLostInvent) {
			GLog.w(Messages.get(EquipableItem.class, "unequip_cursed"));
			return false;
		}

		if (Dungeon.floor == 20){
			GLog.w(Messages.get(EquipableItem.class, "kasen_floor"));
			return false;
		} //kasen floor

		if (single) {
			heroine.spendAndNext( time2equip(heroine) );
		} else {
			heroine.spend( time2equip(heroine) );
		}

		//temporarily keep this item so it can be collected
		boolean wasKept = keptThoughLostInvent;
		keptThoughLostInvent = true;
		if (!collect || !collect( heroine.belongings.backpack )) {
			onDetach();
			Dungeon.quickslot.clearItem(this);
			updateQuickslot();
			if (collect) Dungeon.level.drop( this, heroine.pos );
		}
		keptThoughLostInvent = wasKept;

		return true;
	}

	final public boolean doUnequip(Hero heroine, boolean collect ) {
		return doUnequip(heroine, collect, true );
	}

	public void activate( Char ch ){}
}
