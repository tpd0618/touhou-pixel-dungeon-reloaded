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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossHecatia;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class Miracle extends Item {

	public static final String AC_SHOOT = "SHOOT";

	{
		image = ItemSpriteSheet.MIRACLE_ARROW;

		defaultAction = AC_SHOOT;

		unique = true;
		bones = false;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.remove(AC_DROP);
		actions.remove(AC_THROW);
		actions.add(AC_SHOOT);
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals(AC_SHOOT)) {
			if (Statistics.card66) {
				if (curUser.buff(AnkhInvulnerability.class) != null) {
					GLog.w(Messages.get(this, "alreadyinv"));
				} else if (Statistics.spellcard < 1 && !(curUser.HT > 4) && !(curUser.HP > 4)){
					GLog.w(Messages.get(this, "nospellcard"));
				} else if (Statistics.spellcard > 0){
					if (Statistics.card35) {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
					} else {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION/2f);
					}
					if (Dungeon.isChallenged(Challenges.CALL_THE_SHOTS)) {
						Statistics.mood += 1;
					}
					Statistics.spellcard -= 1;
					Statistics.spellcarduse = true;
					GameScene.flash(0x80FFFFFF);
					Sample.INSTANCE.play(Assets.Sounds.BLAST);
				} else {
					if (Statistics.card35) {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
					} else {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
					}
					if (Dungeon.isChallenged(Challenges.CALL_THE_SHOTS)) {
						Statistics.mood += 1;
					}
					curUser.HP -= 4;
					curUser.HT -= 4;
					Statistics.maxHP_down -= 4;
					Statistics.spellcarduse = true;
					GameScene.flash(0x80FFFFFF);
					Sample.INSTANCE.play(Assets.Sounds.BLAST);
				}
			} else {
				if (Statistics.spellcard < 1) {
					GLog.w(Messages.get(this, "nospellcard"));
				} else if (curUser.buff(AnkhInvulnerability.class) != null) {
					GLog.w(Messages.get(this, "alreadyinv"));
				} else {
					if (Statistics.card35) {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
					} else {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
					}
					if (Dungeon.isChallenged(Challenges.CALL_THE_SHOTS)) {
						Statistics.mood += 1;
					}
					for (Mob mob : Dungeon.level.mobs){
						if (Dungeon.floor == 50 && mob instanceof BossHecatia && mob.isAlive()) {
							Buff.prolong(mob, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 3f);
							GLog.w(Messages.get(this, "bomb_barrier"));
						}
					}
					Statistics.spellcard -= 1;
					Statistics.bomb_count += 1;
					Statistics.spellcarduse = true;
					GameScene.flash(0x80FFFFFF);
					Sample.INSTANCE.play(Assets.Sounds.BLAST);
				}
			}
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}
}