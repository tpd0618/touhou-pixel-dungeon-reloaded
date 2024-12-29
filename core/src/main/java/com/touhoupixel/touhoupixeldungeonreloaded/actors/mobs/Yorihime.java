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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YorihimeSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Yorihime extends Mob {

	{
		spriteClass = YorihimeSprite.class;

		HP = HT = 310;
		defenseSkill = 40;
		EXP = 24;
		maxLvl = 99;

		baseSpeed = 3f;

		flying = true;

		properties.add(Property.WARP);
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(127+Dungeon.heroine.belongings.backpack.items.size()*10, 197+Dungeon.heroine.belongings.backpack.items.size()*10);
	}

	@Override
	public int attackSkill(Char target) {
		return 75;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(28, 41);
	}

	@Override
	public int attackProc( Char hero, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
			Buff.prolong(enemy, Onigiri.class, Onigiri.DURATION);
			if (Statistics.difficulty > 2) {
				Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
			}
			if (Statistics.difficulty > 4) {
				Dungeon.heroine.STR--;
				Dungeon.level.drop(new StrengthCard(), Dungeon.level.randomRespawnCell(null)).sprite.drop();
				Sample.INSTANCE.play( Assets.Sounds.CURSED );
				GLog.w(Messages.get(Kanako.class, "str_reduce"));
			}
		}
		return damage;
	}
}
