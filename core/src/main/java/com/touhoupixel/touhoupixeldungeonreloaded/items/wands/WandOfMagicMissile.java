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

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FlavourBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.RedCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.WhiteCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class WandOfMagicMissile extends DamageWand {

	{
		image = ItemSpriteSheet.WAND_MAGIC_MISSILE;
	}

	public int min(int lvl){
		return 2+2*lvl;
	}

	public int max(int lvl){
		return 4+4*lvl;
	}
	
	@Override
	public void onZap(Ballistica bolt) {
				
		Char ch = Actor.findChar( bolt.collisionPos );
		if (ch != null) {

			wandProc(ch, chargesPerCast());
			ch.damage(damageRoll(), this);
			Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f) );

			ch.sprite.burst(0xFFFFFFFF, buffedLvl() / 2 + 2);

			//apply the magic charge buff if we have another wand in inventory of a lower level, or already have the buff
			for (Charger wandCharger : curUser.buffs(Charger.class)){
				if (wandCharger.wand().buffedLvl() < buffedLvl() || curUser.buff(MagicCharge.class) != null){
					Buff.prolong(curUser, MagicCharge.class, MagicCharge.DURATION).setup(this);
					break;
				}
			}

		} else {
			Dungeon.level.pressCell(bolt.collisionPos);
		}
	}
	public boolean areEnoughCubes(){
		WhiteCubeFragment whiteCubeFragment = Dungeon.heroine.belongings.getItem(WhiteCubeFragment.class);

		if (whiteCubeFragment != null && whiteCubeFragment.quantity() > 0){
			return true;
		}
		else return false;
	}

	protected void spendColourCubes() {
		WhiteCubeFragment whiteCubeFragment = Dungeon.heroine.belongings.getItem(WhiteCubeFragment.class);

		whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
	}

	@Override
	public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {
		SpellSprite.show(attacker, SpellSprite.CHARGE);
		for (Charger c : attacker.buffs(Charger.class)){
			if (c.wand() != this){
				c.gainCharge(0.5f);
			}
		}

	}
	
	protected int initialCharges() {
		return 3;
	}

	public static class MagicCharge extends FlavourBuff {

		{
			type = buffType.POSITIVE;
			announced = true;
		}

		public static float DURATION = 4f;

		private int level = 0;
		private Wand wandJustApplied; //we don't bundle this as it's only used right as the buff is applied

		public void setup(Wand wand){
			if (level < wand.buffedLvl()){
				this.level = wand.buffedLvl();
				this.wandJustApplied = wand;
			}
		}

		@Override
		public void detach() {
			super.detach();
			updateQuickslot();
		}

		public int level(){
			return this.level;
		}

		//this is used briefly so that a wand of magic missile can't clear the buff it just applied
		public Wand wandJustApplied(){
			Wand result = this.wandJustApplied;
			this.wandJustApplied = null;
			return result;
		}

		@Override
		public int icon() {
			return BuffIndicator.UPGRADE;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0.2f, 0.6f, 1f);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

		@Override
		public String toString() {
			return Messages.get(this, "name");
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", level(), dispTurns());
		}

		private static final String LEVEL = "level";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(LEVEL, level);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			level = bundle.getInt(LEVEL);
		}
	}

}
