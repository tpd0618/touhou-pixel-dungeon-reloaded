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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Amok;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YuumaAbsorb;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.HecatiaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HecatiaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TsukasaSoulSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class TsukasaSoul extends Mob {

	{
		spriteClass = TsukasaSoulSprite.class;

		HP = HT = 3;
		defenseSkill = 0;

		flying = true;

		alignment = Alignment.ALLY;
		properties.add(Property.WARP);
		properties.add(Property.NOT_EXTERMINABLE);

		EXP = 0;
		maxLvl = 99;
	}

	@Override
	protected boolean act() {
		boolean result = super.act();
		if (buff(SuperHard.class) == null) {
			Buff.prolong(this, SuperHard.class, SuperHard.DURATION * 10000f);
		}
		if (buff(DoubleSpeed.class) == null) {
			Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 10000f);
		}
		return result;
	}

	@Override
	public int damageRoll() {
		return 0;
	}

	@Override
	public int attackSkill(Char target) {
		return INFINITE_ACCURACY;
	}

	@Override
	public int drRoll() {
		return 0;
	}

	@Override
	public int attackProc(Char hero, int damage) {
		damage = super.attackProc(enemy, damage);
		Buff.prolong(enemy, DoubleSpeed.class, DoubleSpeed.DURATION * 1000f);
		Buff.prolong(enemy, Might.class, Might.DURATION * 1000f);
		Buff.prolong(enemy, Doublerainbow.class, Doublerainbow.DURATION * 1000f);
		for (Buff b : enemy.buffs()) {
			if (b.type == Buff.buffType.NEGATIVE) {
				b.detach();
				enemy.HP = enemy.HT;
				enemy.sprite.emitter().burst(Speck.factory(Speck.HEALING), 6);
			}
		}
		this.die(this);
		return damage;
	}
}