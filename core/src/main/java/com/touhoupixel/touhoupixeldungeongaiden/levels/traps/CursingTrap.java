/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2023 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeongaiden.levels.traps;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.EquipableItem;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.KindOfWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;
import java.util.Collections;

public class CursingTrap extends Trap {

	{
		color = VIOLET;
		shape = WAVES;
	}

	@Override
	public void activate() {
		if (Dungeon.level.heroFOV[ pos ]) {
			CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
			Sample.INSTANCE.play(Assets.Sounds.CURSED);
		}

		Heap heap = Dungeon.level.heaps.get( pos );
		if (heap != null){
			for (Item item : heap.items){
				if (item.isUpgradable() && !(item instanceof MissileWeapon))
					curse(item);
			}
		}

		if (Dungeon.heroine.pos == pos && !Dungeon.heroine.flying){
			curse(Dungeon.heroine);
		}
	}

	public static void curse(Hero heroine){
		//items the trap wants to curse because it will create a more negative effect
		ArrayList<Item> priorityCurse = new ArrayList<>();
		//items the trap can curse if nothing else is available.
		ArrayList<Item> canCurse = new ArrayList<>();

		KindOfWeapon weapon = heroine.belongings.weapon();
		if (weapon instanceof Weapon && !(weapon instanceof MarisaStaff)){
			if (((Weapon) weapon).enchantment == null)
				priorityCurse.add(weapon);
			else
				canCurse.add(weapon);
		}

		Armor armor = heroine.belongings.armor();
		if (armor != null){
			if (armor.glyph == null)
				priorityCurse.add(armor);
			else
				canCurse.add(armor);
		}

		Collections.shuffle(priorityCurse);
		Collections.shuffle(canCurse);

		if (!priorityCurse.isEmpty()){
			curse(priorityCurse.remove(0));
		} else if (!canCurse.isEmpty()){
			curse(canCurse.remove(0));
		}

		EquipableItem.equipCursed(heroine);
		GLog.n( Messages.get(CursingTrap.class, "curse") );
	}

	private static void curse(Item item){
		item.cursed = item.cursedKnown = true;

		if (item instanceof Weapon){
			Weapon w = (Weapon) item;
			if (w.enchantment == null){
				w.enchant(Weapon.Enchantment.randomCurse());
			}
		}
		if (item instanceof Armor){
			Armor a = (Armor) item;
			if (a.glyph == null){
				a.inscribe(Armor.Glyph.randomCurse());
			}
		}
	}
}