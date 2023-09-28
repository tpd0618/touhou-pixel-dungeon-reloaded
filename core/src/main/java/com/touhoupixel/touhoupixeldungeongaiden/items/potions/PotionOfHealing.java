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

package com.touhoupixel.touhoupixeldungeongaiden.items.potions;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Healing;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.InversionTrap;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;

public class PotionOfHealing extends Potion {

	{
		icon = ItemSpriteSheet.Icons.POTION_HEALING;

		bones = true;
	}
	
	@Override
	public void apply( Hero heroine) {
		identify();
		cure(heroine);
		heal(heroine);
		if (Statistics.card62){
			GameScene.flash(0x80FFFFFF);
			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
					if (!mob.properties().contains(Char.Property.BOSS) && !mob.properties().contains(Char.Property.MINIBOSS)) {
						mob.HP = Math.max(3, mob.HP/2);
					}
				}
			}
		}
	}

	public static void heal( Char ch ) {
		if (ch.buff(Inversion.class) != null) {
			ch.damage(ch.HT / 2, ch);
			if (ch == Dungeon.heroine && !ch.isAlive()) {
				Dungeon.fail(InversionTrap.class);
				GLog.n( Messages.get(Inversion.class, "ondeath") );
			}
		} else {
			Buff.affect(ch, Healing.class).setHeal((int) (0.8f * ch.HT + 14), 0.25f, 0);
		}
		if (ch == Dungeon.heroine) {
			GLog.p(Messages.get(PotionOfHealing.class, "heal"));
		}
	}
	
	public static void cure( Char ch ) {
		Buff.detach( ch, Poison.class );
		Buff.detach( ch, Cripple.class );
		Buff.detach( ch, Weakness.class );
		Buff.detach( ch, Vulnerable.class );
		Buff.detach( ch, Bleeding.class );
		Buff.detach( ch, Blindness.class );
		Buff.detach( ch, Drowsy.class );
		Buff.detach( ch, Slow.class );
		Buff.detach( ch, Vertigo.class);
		Buff.detach( ch, MeleeNullify.class);
		Buff.detach( ch, WandZeroDamage.class);
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}
