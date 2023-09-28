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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.SparkParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfParalyticGas;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TojikoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Tojiko extends Mob implements Callback {

	private static final float TIME_TO_ZAP	= 1f;

	{
		spriteClass = TojikoSprite.class;

		HP = HT = 79;
		defenseSkill = 25;
		EXP = 14;
		maxLvl = 32;

		properties.add(Property.WARP);

		loot = new PotionOfParalyticGas();
		lootChance = 0.1f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(10, 17);
	}

	@Override
	public int attackSkill( Char target ) {
		return 30;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 4);
	}

	@Override
	protected boolean canAttack( Char enemy ) {
		return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
	}

	//used so resistances can differentiate between melee and magical attacks
	public static class LightningBolt{}

	@Override
	protected boolean doAttack( Char enemy ) {

		if (Dungeon.level.distance( pos, enemy.pos ) <= 1) {

			return super.doAttack( enemy );

		} else {

			spend( TIME_TO_ZAP );

			if (hit( this, enemy, true )) {
				int dmg = Random.NormalIntRange(10, 15);
				enemy.damage( dmg, new LightningBolt() );
				if (Dungeon.gold > 100) {
					Dungeon.gold -= 100;
				}
				if (Statistics.difficulty > 2) {
					Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
				}
				if (Statistics.difficulty > 4) {
					Buff.prolong(enemy, Inversion.class, Inversion.DURATION);
				}

				if (enemy.sprite.visible) {
					enemy.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
					enemy.sprite.flash();
				}

				if (enemy == Dungeon.heroine) {

					Camera.main.shake( 2, 0.3f );

					if (!enemy.isAlive()) {
						Dungeon.fail( getClass() );
						GLog.n( Messages.get(this, "zap_kill") );
					}
				}
			} else {
				enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
			}

			if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
				sprite.zap( enemy.pos );
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public void call() {
		next();
	}

}