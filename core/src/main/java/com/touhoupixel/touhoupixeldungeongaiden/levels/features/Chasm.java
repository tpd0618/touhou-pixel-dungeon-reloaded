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

package com.touhoupixel.touhoupixeldungeongaiden.levels.features;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.BalanceBreak;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Tenshi;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeongaiden.items.spells.FeatherFall;
import com.touhoupixel.touhoupixeldungeongaiden.levels.RegularLevel;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.InterlevelScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MobSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Chasm implements Hero.Doom {

	public static boolean jumpConfirmed = false;
	private static int heroPos;

	public static void heroJump( final Hero heroine) {
		heroPos = heroine.pos;
		Game.runOnRenderThread(new Callback() {
			@Override
			public void call() {
				if (Dungeon.heroine.buff(BalanceBreak.class) == null) {
					GameScene.show(
							new WndOptions(new Image(Dungeon.level.tilesTex(), 48, 48, 16, 16),
									Messages.get(Chasm.class, "chasm"),
									Messages.get(Chasm.class, "jump"),
									Messages.get(Chasm.class, "yes"),
									Messages.get(Chasm.class, "no")) {
								@Override
								protected void onSelect(int index) {
									if (index == 0) {
										if (Dungeon.heroine.pos == heroPos) {
											if (Dungeon.floor == 1){
												ScrollOfTeleportation.teleportChar(heroine);
												GLog.n( Messages.get(Tenshi.class, "floor_one_fall") );
											} else {
												jumpConfirmed = true;
												heroine.resume();
											}
										}
									}
								}
							}
					);
				} else if (Dungeon.heroine.pos == heroPos) {
					if (Dungeon.floor == 1){
						ScrollOfTeleportation.teleportChar(heroine);
						GLog.n( Messages.get(Tenshi.class, "floor_one_fall") );
					} else {
						jumpConfirmed = true;
						heroine.resume();
					}
				}
			}
		});
	}

	public static void heroFall( int pos ) {

		jumpConfirmed = false;

		Sample.INSTANCE.play( Assets.Sounds.FALLING );

		TimekeepersHourglass.timeFreeze timeFreeze = Dungeon.heroine.buff(TimekeepersHourglass.timeFreeze.class);
		if (timeFreeze != null) timeFreeze.disarmPressedTraps();
		Swiftthistle.TimeBubble timeBubble = Dungeon.heroine.buff(Swiftthistle.TimeBubble.class);
		if (timeBubble != null) timeBubble.disarmPressedTraps();

		if (Dungeon.heroine.isAlive()) {
			InterlevelScene.mode = InterlevelScene.Mode.FALL;
			if (Dungeon.level instanceof RegularLevel) {
				InterlevelScene.fallIntoPit = false;
			}
			if (Dungeon.floor == 1) {
				ScrollOfTeleportation.teleportChar(Dungeon.heroine);
				GLog.n(Messages.get(Tenshi.class, "floor_one_fall"));
			} else {
				Dungeon.heroine.interrupt();
				Game.switchScene(InterlevelScene.class);
			}
		} else {
			Dungeon.heroine.sprite.visible = false;
		}
	}

	@Override
	public void onDeath() {
		Dungeon.fail( Chasm.class );
		GLog.n( Messages.get(Chasm.class, "ondeath") );
	}

	public static void heroLand() {

		Hero heroine = Dungeon.heroine;

		FeatherFall.FeatherBuff b = heroine.buff(FeatherFall.FeatherBuff.class);

		if (b != null){
			heroine.sprite.emitter().burst( Speck.factory( Speck.JET ), 20);
			b.detach();
			return;
		}

		Camera.main.shake( 4, 1f );

		Dungeon.level.occupyCell(heroine);

		//The lower the hero's HP, the more bleed and the less upfront damage.
		//Hero has a 50% chance to bleed out at 66% HP, and begins to risk instant-death at 25%
		heroine.damage(Math.max(heroine.HP / 2, Random.NormalIntRange(heroine.HP / 2, heroine.HT / 4)), new Chasm());
	}

	public static void mobFall( Mob mob ) {
		if (mob.isAlive()) mob.die( Chasm.class );

		if (mob.sprite != null) ((MobSprite)mob.sprite).fall();
	}

	public static class Falling extends Buff {

		{
			actPriority = VFX_PRIO;
		}

		@Override
		public boolean act() {
			heroLand();
			detach();
			return true;
		}
	}

	public static class FallBleed extends Bleeding implements Hero.Doom {

		@Override
		public void onDeath() {
		}
	}
}
