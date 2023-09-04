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

package com.touhoupixel.touhoupixeldungeonreloaded.items.food;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DistortedAvarice;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeHunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WellFed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.AlchemyHat;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MintChocoSword;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Food extends Item {

	public static final float TIME_TO_EAT	= 3f;

	public static final String AC_EAT	= "EAT";

	public float energy = Hunger.HUNGRY;

	{
		stackable = true;
		image = ItemSpriteSheet.RATION;

		defaultAction = AC_EAT;

		bones = true;
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

			detach( heroine.belongings.backpack );

			satisfy(heroine);
			GLog.i( Messages.get(this, "eat_msg") );

			heroine.sprite.operate( heroine.pos );
			heroine.busy();
			SpellSprite.show(heroine, SpellSprite.FOOD );
			Sample.INSTANCE.play( Assets.Sounds.EAT );

			if (Dungeon.isChallenged(Challenges.CALL_THE_SHOTS)) {
				Statistics.mood += 1;
			}

			Buff.detach(curUser, ExtremeHunger.class);
			Buff.detach(curUser, DistortedAvarice.class);

			heroine.spend( eatingTime() );

			Statistics.foodEaten++;

			if (Statistics.card41){
				Buff.affect(heroine, WellFed.class).set((int)(Hunger.STARVING/4f));
			}

			if (Dungeon.heroine.belongings.weapon() instanceof MintChocoSword && Dungeon.heroine.belongings.weapon().cursed){
				Buff.affect(heroine, WellFed.class).reset();
				curUser.HP = Math.min(curUser.HP + curUser.HT/2, curUser.HT);
				PotionOfHealing.cure(curUser);
				Buff.prolong(curUser, DoubleSpeed.class, DoubleSpeed.DURATION);
			}

			if (Statistics.card67){
				Statistics.power += 100;
			}
		}
	}

	protected float eatingTime(){
		return TIME_TO_EAT;
	}

	protected void satisfy( Hero heroine){
		Buff.affect(heroine, Hunger.class).satisfy(energy);
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
		return 10 * quantity;
	}
}