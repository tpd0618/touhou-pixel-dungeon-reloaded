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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doom;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.KindofMisc;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.HecatiaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bracelets.AntiParryBracelet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bracelets.Bracelet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HecatiaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Hecatia extends Mob {

	{
		spriteClass = HecatiaSprite.class;

		HP = HT = Dungeon.floor*5;
		defenseSkill = 0;

		flying = true;

		properties.add(Property.GOD);
		properties.add(Property.HELL);
		properties.add(Property.NOT_EXTERMINABLE);

		EXP = 0;
		maxLvl = 99;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(Dungeon.floor, Dungeon.floor + 2);
	}

	@Override
	public int attackSkill(Char target) {
		return Dungeon.floor + 5;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	void teleportMobs(int direction) {
		int[] offsets = new int[]{direction, 2 * direction, direction + Dungeon.level.width(), direction - Dungeon.level.width()};

		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			for (int offset : offsets) {
				int newPos = this.pos + offset;
				if (Actor.findChar(newPos) == null && Dungeon.level.map[newPos] != Terrain.CHASM) {
					ScrollOfTeleportation.teleportToLocationHearn(offset == direction ? Dungeon.heroine : mob, newPos);
				}
			}
		}
	}

	void performAction() {
		switch (Random.Int(4)) {
			case 0: teleportMobs(1); break;
			case 1: teleportMobs(-1); break;
			case 2: teleportMobs(Dungeon.level.width()); break;
			case 3: teleportMobs(-Dungeon.level.width()); break;
		}
	}

	@Override
	public int attackProc(Char hero, int damage) {
		damage = super.attackProc(enemy, damage);

		KindofMisc misc = Dungeon.heroine.belongings.misc;
		Bracelet bracelet = Dungeon.heroine.belongings.bracelet;

		if (!(Dungeon.heroine.belongings.armor() instanceof HecatiaArmor) || !(Dungeon.floor == 40)) {
			if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
				Sample.INSTANCE.play(Assets.Sounds.READ);
				CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
				switch (Random.Int(4)) {
					case 0:
					default:
						Armor armor = Dungeon.heroine.belongings.armor();
						if (misc instanceof AntiParryBracelet || bracelet instanceof AntiParryBracelet) {
							//do nothing
						} else {
							if (armor != null) {
								Dungeon.heroine.belongings.armor = null;
								Dungeon.quickslot.clearItem(armor);
								Item.updateQuickslot();

								Ballistica trajectory = new Ballistica(this.pos, enemy.pos, Ballistica.STOP_TARGET);
								trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);

								Char ch = (Char) Actor.findChar(trajectory.collisionPos);
								if (ch != null && ch != Dungeon.heroine && trajectory.collisionPos.equals(ch.pos)) {
									ch.damage(Dungeon.heroine.STR * 2 + armor.level() * 3, armor);
									GLog.w(Messages.get(Kasen.class, "armor_blow_away_and_destroy"));
								} else {
									armor.onThrow(trajectory.collisionPos);
									GLog.w(Messages.get(Kasen.class, "armor_blow_away"));
								}
							}
						}
					case 1:
						performAction();
						break;
					case 2:
						Buff.prolong(enemy, Onigiri.class, Onigiri.DURATION);
						break;
					case 3:
						Buff.prolong(enemy, Slow.class, Slow.DURATION);
						break;
				}
			}
		}
		return damage;
	}
}