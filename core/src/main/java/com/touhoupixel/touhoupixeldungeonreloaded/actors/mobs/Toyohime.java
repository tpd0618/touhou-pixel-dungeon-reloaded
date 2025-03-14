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
import com.touhoupixel.touhoupixeldungeonreloaded.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ToyohimeSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Toyohime extends Mob implements Callback {

	private static final float TIME_TO_ZAP = 1f;

	{
		spriteClass = ToyohimeSprite.class;

		HP = HT = 460;
		defenseSkill = 40;
		EXP = 24;
		maxLvl = 99;

		flying = true;

		properties.add(Property.WARP);
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(63+Dungeon.heroine.belongings.backpack.items.size()*10, 97+Dungeon.heroine.belongings.backpack.items.size()*10);
	}

	@Override
	public int attackSkill(Char target) {
		return 75;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(31, 44);
	}

	@Override
	protected boolean canAttack(Char enemy) {
		return new Ballistica(pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
	}

	protected boolean doAttack(Char enemy) {

		if (Dungeon.level.adjacent(pos, enemy.pos)) {

			return super.doAttack(enemy);

		} else {

			if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
				sprite.zap(enemy.pos);
				return false;
			} else {
				zap();
				return true;
			}
		}
	}

	//used so resistances can differentiate between melee and magical attacks
	public static class DarkBolt {
	}

	private void zap() {
		spend(TIME_TO_ZAP);

		if (hit(this, enemy, true)) {
			//TODO would be nice for this to work on ghost/statues too
			if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
				Buff.prolong(enemy, DismantlePressure.class, DismantlePressure.DURATION);
				Sample.INSTANCE.play(Assets.Sounds.DEBUFF);
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

			int dmg = Random.NormalIntRange(67+Dungeon.heroine.belongings.backpack.items.size()*10, 103+Dungeon.heroine.belongings.backpack.items.size()*10);
			enemy.damage(dmg, new DarkBolt());

			if (enemy == Dungeon.heroine && !enemy.isAlive()) {
				Dungeon.fail(getClass());
				GLog.n(Messages.get(this, "bolt_kill"));
			}
		} else {
			enemy.sprite.showStatus(CharSprite.NEUTRAL, enemy.defenseVerb());
		}
	}

	public void onZapComplete() {
		zap();
		next();
	}

	@Override
	public void call() {
		next();
	}

}
