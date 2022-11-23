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

package com.touhoupixel.touhoupixeldungeonreloaded.items;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class DoremyDreamEater extends Item {

	private static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.DOREMY_DREAM_EATER;

		defaultAction = AC_DRINK;

		unique = true;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.remove(AC_DROP);
		actions.remove(AC_THROW);
		actions.add(AC_DRINK);
		return actions;
	}

	@Override
	public void execute(final Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_DRINK)) {
			Sample.INSTANCE.play(Assets.Sounds.LULLABY);
			curUser.spendAndNext(1f);
			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				mob.beckon(curUser.pos);
			}
		}
		if (Random.Int(4) == 0) {
			GLog.n(Messages.get(this, "doremysleep"));
			hero.sprite.emitter().start(Speck.factory(Speck.BUBBLE), 0.2f, 3);
			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos] && !mob.properties().contains(Char.Property.MINIBOSS) && !mob.properties().contains(Char.Property.BOSS)) {
					Buff.affect(mob, Drowsy.class);
				}
			}
		} else {
			GLog.n(Messages.get(this, "cannotsleep"));
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}
}