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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.EquipmentIdentify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Recharging;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.EnergyParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;

public class ScrollOfFixer extends Scroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_ENCHANT;
	}

	@Override
	public void doRead() {
		if (Dungeon.isChallenged(Challenges.ETERNAL_DREAM)){
			Buff.affect(curUser, Light.class, Light.DURATION/2f);
		} else {
			Buff.affect(curUser, Light.class, Light.DURATION);
		}
		Sample.INSTANCE.play(Assets.Sounds.BURNING);
		curUser.HP = Math.min(curUser.HP + 100000, curUser.HT);
		GLog.p(Messages.get(this, "fixer"));
		if (!Dungeon.isChallenged(Challenges.UNIDENTIFIED_OBJECT)) {
			identify();
		}
		readAnimation();
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}