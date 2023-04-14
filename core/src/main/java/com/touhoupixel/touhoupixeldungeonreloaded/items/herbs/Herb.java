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

package com.touhoupixel.touhoupixeldungeonreloaded.items.herbs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.EquipmentIdentify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HerbDegrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class Herb extends Item {

	public static final float TIME_TO_EAT	= 1f;

	public static final String AC_EAT	= "EAT";

	{
		stackable = true;

		bones = true;

		defaultAction = AC_EAT;
	}

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_EAT );
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_EAT )) {

			detach(hero.belongings.backpack);

			hero.sprite.operate(hero.pos);
			hero.busy();
			SpellSprite.show(hero, SpellSprite.FOOD);
			Sample.INSTANCE.play(Assets.Sounds.EAT);

			if (Dungeon.isChallenged(Challenges.DREAM_LOGICAL_WORLD)) {
				Statistics.mood += 1;
			}

			if (hero.buff(HerbDegrade.class) != null){
				Buff.prolong(curUser, Degrade.class, Degrade.DURATION);
			}

			if (Statistics.card33){
				Buff.prolong(curUser, EquipmentIdentify.class, EquipmentIdentify.DURATION/4f);
			}

			hero.spend(eatingTime());
		}
	}

	protected float eatingTime(){
		return TIME_TO_EAT;
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
		return 15 * quantity;
	}
}