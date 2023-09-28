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

package com.touhoupixel.touhoupixeldungeongaiden.levels.traps;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.MagicMissile;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class GrimTrap extends Trap {

	{
		color = GREY;
		shape = LARGE_DOT;
		
		canBeHidden = false;
		avoidsHallways = true;
	}

	@Override
	public void activate() {
		Char target = Actor.findChar(pos);

		//find the closest char that can be aimed at
		if (target == null){
			float closestDist = Float.MAX_VALUE;
			for (Char ch : Actor.chars()){
				float curDist = Dungeon.level.trueDistance(pos, ch.pos);
				if (ch.invisible > 0) curDist += 1000;
				Ballistica bolt = new Ballistica(pos, ch.pos, Ballistica.PROJECTILE);
				if (bolt.collisionPos == ch.pos && curDist < closestDist){
					target = ch;
					closestDist = curDist;
				}
			}
		}

		if (target != null){
			final Char finalTarget = target;
			final GrimTrap trap = this;
			int damage;
			
			//almost kill the player
			if (finalTarget == Dungeon.heroine && ((float)finalTarget.HP/finalTarget.HT) >= 0.9f){
				damage = finalTarget.HP - 1;
			//kill 'em
			} else {
				damage = finalTarget.HP;
			}
			
			final int finalDmg = damage;
			
			Actor.add(new Actor() {
				
				{
					//it's a visual effect, gets priority no matter what
					actPriority = VFX_PRIO;
				}
				
				@Override
				protected boolean act() {
					final Actor toRemove = this;
					((MagicMissile)finalTarget.sprite.parent.recycle(MagicMissile.class)).reset(
							MagicMissile.SHADOW,
							DungeonTilemap.tileCenterToWorld(pos),
							finalTarget.sprite.center(),
							new Callback() {
								@Override
								public void call() {
									finalTarget.damage(finalDmg, trap);
									if (finalTarget == Dungeon.heroine) {
										Sample.INSTANCE.play(Assets.Sounds.CURSED);
										if (!finalTarget.isAlive()) {
											Dungeon.fail( GrimTrap.class );
											GLog.n( Messages.get(GrimTrap.class, "ondeath") );
										}
									} else {
										Sample.INSTANCE.play(Assets.Sounds.BURNING);
									}
									finalTarget.sprite.emitter().burst(ShadowParticle.UP, 10);
									Actor.remove(toRemove);
									next();
								}
							});
					return false;
				}
			});
		} else {
			CellEmitter.get(pos).burst(ShadowParticle.UP, 10);
			Sample.INSTANCE.play(Assets.Sounds.BURNING);
		}
	}
}
