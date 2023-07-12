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

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndOptions;


public abstract class KindofMisc extends EquipableItem {

	private static final float TIME_TO_EQUIP = 1f;

	@Override
	public boolean doEquip(final Hero heroine) {

		boolean equipFull = false;
		if ( this instanceof Artifact
				&& heroine.belongings.artifact != null
				&& heroine.belongings.misc != null){

			//see if we can re-arrange items first
			if (heroine.belongings.misc instanceof Ring && heroine.belongings.ring == null){
				heroine.belongings.ring = (Ring) heroine.belongings.misc;
				heroine.belongings.misc = null;
			} else {
				equipFull = true;
			}
		} else if (this instanceof Ring
				&& heroine.belongings.misc != null
				&& heroine.belongings.ring != null){

			//see if we can re-arrange items first
			if (heroine.belongings.misc instanceof Artifact && heroine.belongings.artifact == null){
				heroine.belongings.artifact = (Artifact) heroine.belongings.misc;
				heroine.belongings.misc = null;
			} else {
				equipFull = true;
			}
		}

		if (equipFull) {

			final KindofMisc[] miscs = new KindofMisc[3];
			miscs[0] = heroine.belongings.artifact;
			miscs[1] = heroine.belongings.misc;
			miscs[2] = heroine.belongings.ring;

			final boolean[] enabled = new boolean[3];
			enabled[0] = miscs[0] != null;
			enabled[1] = miscs[1] != null;
			enabled[2] = miscs[2] != null;

			//force swapping with the same type of item if 2x of that type is already present
			if (this instanceof Ring && heroine.belongings.misc instanceof Ring){
				enabled[0] = false; //disable artifact
			} else if (this instanceof Artifact && heroine.belongings.misc instanceof Artifact){
				enabled[2] = false; //disable ring
			}

			GameScene.show(
					new WndOptions(new ItemSprite(this),
							Messages.get(KindofMisc.class, "unequip_title"),
							Messages.get(KindofMisc.class, "unequip_message"),
							miscs[0] == null ? "---" : Messages.titleCase(miscs[0].toString()),
							miscs[1] == null ? "---" : Messages.titleCase(miscs[1].toString()),
							miscs[2] == null ? "---" : Messages.titleCase(miscs[2].toString())) {

						@Override
						protected void onSelect(int index) {

							KindofMisc equipped = miscs[index];
							//we directly remove the item because we want to have inventory capacity
							// to unequip the equipped one, but don't want to trigger any other
							// item detaching logic
							int slot = Dungeon.quickslot.getSlot(KindofMisc.this);
							Dungeon.heroine.belongings.backpack.items.remove(KindofMisc.this);
							if (equipped.doUnequip(heroine, true, false)) {
								//swap out equip in misc slot if needed
								if (index == 0 && KindofMisc.this instanceof Ring){
									heroine.belongings.artifact = (Artifact) heroine.belongings.misc;
									heroine.belongings.misc = null;
								} else if (index == 2 && KindofMisc.this instanceof Artifact){
									heroine.belongings.ring = (Ring) heroine.belongings.misc;
									heroine.belongings.misc = null;
								}
								Dungeon.heroine.belongings.backpack.items.add(KindofMisc.this);
								doEquip(heroine);
							} else {
								Dungeon.heroine.belongings.backpack.items.add(KindofMisc.this);
							}
							if (slot != -1) Dungeon.quickslot.setSlot(slot, KindofMisc.this);
							updateQuickslot();
						}

						@Override
						protected boolean enabled(int index) {
							return enabled[index];
						}
					});

			return false;

		} else {

			if (this instanceof Artifact){
				if (heroine.belongings.artifact == null)   heroine.belongings.artifact = (Artifact) this;
				else                                    heroine.belongings.misc = (Artifact) this;
			} else if (this instanceof Ring){
				if (heroine.belongings.ring == null)   heroine.belongings.ring = (Ring) this;
				else                                heroine.belongings.misc = (Ring) this;
			}

			detach( heroine.belongings.backpack );

			activate(heroine);

			cursedKnown = true;
			if (cursed) {
				equipCursed(heroine);
				GLog.n( Messages.get(this, "equip_cursed", this) );
			}

			heroine.spendAndNext( TIME_TO_EQUIP );
			return true;

		}

	}

	@Override
	public boolean doUnequip(Hero heroine, boolean collect, boolean single) {
		if (super.doUnequip(heroine, collect, single)){

			if (heroine.belongings.artifact == this) {
				heroine.belongings.artifact = null;
			} else if (heroine.belongings.misc == this) {
				heroine.belongings.misc = null;
			} else if (heroine.belongings.ring == this){
				heroine.belongings.ring = null;
			}

			return true;

		} else {

			return false;

		}
	}

	@Override
	public boolean isEquipped( Hero heroine) {
		return heroine.belongings.artifact() == this
				|| heroine.belongings.misc() == this
				|| heroine.belongings.ring() == this;
	}

}
