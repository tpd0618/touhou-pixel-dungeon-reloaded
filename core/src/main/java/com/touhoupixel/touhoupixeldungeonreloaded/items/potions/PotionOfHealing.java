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

package com.touhoupixel.touhoupixeldungeonreloaded.items.potions;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiHeal;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Healing;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.NoInvisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class PotionOfHealing extends Potion {

	{
		icon = ItemSpriteSheet.Icons.POTION_HEALING;

		bones = true;
	}
	
	@Override
	public void apply( Hero hero ) {
		identify();
		cure( hero );
		heal( hero );
	}

	public static void heal( Char ch ) {
		if (ch.buff(AntiHeal.class) != null) {
			ch.damage(ch.HT / 2, ch);
		} else Buff.affect(ch, Healing.class).setHeal((int) (0.8f * ch.HT + 14), 0.25f, 0);
		if (ch == Dungeon.hero) {
			GLog.p(Messages.get(PotionOfHealing.class, "heal"));
			if (Dungeon.isChallenged(Challenges.EIRIN_UNHOLY_HEAL)) {
				Buff.prolong(curUser, Paralysis.class, Paralysis.DURATION/5f);
			}
		}
	}
	
	public static void cure( Char ch ) {
		Buff.detach( ch, Poison.class);
		Buff.detach( ch, Cripple.class);
		Buff.detach( ch, Weakness.class);
		Buff.detach( ch, Vulnerable.class);
		Buff.detach( ch, Bleeding.class);
		Buff.detach( ch, Blindness.class);
		Buff.detach( ch, Drowsy.class);
		Buff.detach( ch, Slow.class);
		Buff.detach( ch, Vertigo.class);
		Buff.detach( ch, ExtremeConfusion.class);
		Buff.detach( ch, RemiliaFate.class);
		Buff.detach( ch, OneDamage.class);
		Buff.detach( ch, NoInvisibility.class);
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}
