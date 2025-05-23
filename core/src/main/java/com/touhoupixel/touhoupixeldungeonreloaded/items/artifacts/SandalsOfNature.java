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

package com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Roots;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.EarthParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.FloralBag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MysticBag;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Plant;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndBag;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class SandalsOfNature extends Artifact {

	{
		image = ItemSpriteSheet.ARTIFACT_SANDALS;

		levelCap = 3;

		charge = 0;

		defaultAction = AC_ROOT;
	}

	public static final String AC_FEED = "FEED";
	public static final String AC_ROOT = "ROOT";

	public ArrayList<Class> seeds = new ArrayList<>();

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (Dungeon.heroine.buff(Onigiri.class) == null) {
			if (isEquipped(heroine) && level() < 3 && !cursed)
				actions.add(AC_FEED);
			if (isEquipped(heroine) && charge > 0)
				actions.add(AC_ROOT);
		}
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action ) {
		super.execute(heroine, action);

		if (action.equals(AC_FEED)){

			GameScene.selectItem(itemSelector);

		} else if (action.equals(AC_ROOT) && level() > 0){

			if (!isEquipped(heroine)) GLog.i( Messages.get(Artifact.class, "need_to_equip") );
			else if (charge == 0)    GLog.i( Messages.get(this, "no_charge") );
			else {
				Buff.prolong(heroine, Roots.class, Roots.DURATION);
				Buff.affect(heroine, Earthroot.Armor.class).level(charge);
				CellEmitter.bottom(heroine.pos).start(EarthParticle.FACTORY, 0.05f, 8);
				Camera.main.shake(1, 0.4f);
				charge = 0;
				updateQuickslot();
			}
		}
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new Naturalism();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		target.buff(Naturalism.class).charge(amount);
	}

	@Override
	public String name() {
		if (level() == 0)   return super.name();
		else                return Messages.get(this, "name_" + level());
	}

	@Override
	public String desc() {
		String desc = Messages.get(this, "desc_" + (level()+1));

		if ( isEquipped ( Dungeon.heroine) ){
			desc += "\n\n";

			if (!cursed)
				desc += Messages.get(this, "desc_hint");
			else
				desc += Messages.get(this, "desc_cursed");

			if (level() > 0)
				desc += "\n\n" + Messages.get(this, "desc_ability");
		}

		if (!seeds.isEmpty()){
			desc += "\n\n" + Messages.get(this, "desc_seeds", seeds.size());
		}

		return desc;
	}

	@Override
	public Item upgrade() {
		if (level() < 0)        image = ItemSpriteSheet.ARTIFACT_SANDALS;
		else if (level() == 0)  image = ItemSpriteSheet.ARTIFACT_SHOES;
		else if (level() == 1)  image = ItemSpriteSheet.ARTIFACT_BOOTS;
		else if (level() >= 2)  image = ItemSpriteSheet.ARTIFACT_GREAVES;
		return super.upgrade();
	}

	public boolean canUseSeed(Item item){
		return item instanceof Plant.Seed && !seeds.contains(item.getClass());
	}


	private static final String SEEDS = "seeds";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put(SEEDS, seeds.toArray(new Class[seeds.size()]));
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(SEEDS))
			Collections.addAll(seeds , bundle.getClassArray(SEEDS));
		if (level() == 1)  image = ItemSpriteSheet.ARTIFACT_SHOES;
		else if (level() == 2)  image = ItemSpriteSheet.ARTIFACT_BOOTS;
		else if (level() >= 3)  image = ItemSpriteSheet.ARTIFACT_GREAVES;
	}

	public class Naturalism extends ArtifactBuff{
		public void charge(float amount) {
			if (level() > 0 && charge < target.HT){
				//gain 1+(1*level)% of the difference between current charge and max HP.
				float chargeGain = (target.HT-charge) * (.01f+ level()*0.01f);
				chargeGain *= amount;
				partialCharge += Math.max(0, chargeGain);
				while (partialCharge > 1){
					charge++;
					partialCharge--;
				}
				updateQuickslot();
			}
		}
	}

	protected WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(SandalsOfNature.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return FloralBag.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return canUseSeed(item);
		}

		@Override
		public void onSelect( Item item ) {
			if (item != null && item instanceof Plant.Seed) {
				if (seeds.contains(item.getClass())){
					GLog.w( Messages.get(SandalsOfNature.class, "already_fed") );
				} else {
					seeds.add(item.getClass());

					Hero heroine = Dungeon.heroine;
					heroine.sprite.operate( heroine.pos );
					Sample.INSTANCE.play( Assets.Sounds.PLANT );
					heroine.busy();
					heroine.spend( 2f );
					if (seeds.size() >= 3+(level()*3)){
						seeds.clear();
						upgrade();
						if (level() >= 1 && level() <= 3) {
							GLog.p( Messages.get(SandalsOfNature.class, "levelup") );
						}

					} else {
						GLog.i( Messages.get(SandalsOfNature.class, "absorb_seed") );
					}
					item.detach(heroine.belongings.backpack);
				}
			}
		}
	};

}
