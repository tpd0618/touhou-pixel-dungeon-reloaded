/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeonreloaded.items.potions;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.KindOfWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class PotionOfInvert extends Potion {
	
	{
		icon = ItemSpriteSheet.Icons.POTION_DOUBLESPEED;
	}
	
	@Override
	public void apply(Hero heroine) {
		identify();

		KindOfWeapon meleeweapon = Dungeon.heroine.belongings.weapon();
		Armor armor = Dungeon.heroine.belongings.armor();

		if (meleeweapon != null && !(meleeweapon instanceof MarisaStaff) && armor != null) {
			int tempLevel = meleeweapon.level();
			meleeweapon.level(armor.level());
			armor.level(tempLevel);
			GLog.w( Messages.get(this, "invert") );
		} else {
			GLog.w( Messages.get(this, "cannot_invert") );
		}

		Sample.INSTANCE.play( Assets.Sounds.DEWDROP );
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}
