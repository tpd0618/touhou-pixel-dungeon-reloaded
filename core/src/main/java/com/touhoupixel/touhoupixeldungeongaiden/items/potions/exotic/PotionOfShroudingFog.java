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

package com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.SmokeScreen;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.BrainWash;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class PotionOfShroudingFog extends ExoticPotion {
	
	{
		icon = ItemSpriteSheet.Icons.POTION_SHROUDFOG;
	}
	
	@Override
	public void shatter( int cell ) {
		
		if (Dungeon.level.heroFOV[cell]) {
			identify();

			if (Statistics.card52){
				GameScene.flash(0x80FFFFFF);
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
						if (!mob.properties().contains(Char.Property.BOSS) || !mob.properties().contains(Char.Property.MINIBOSS)) {
							Buff.prolong(mob, BrainWash.class, BrainWash.DURATION);
						}
					}
				}
			}
			
			splash( cell );
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );
			Sample.INSTANCE.play( Assets.Sounds.GAS );
		}

		int centerVolume = 180;
		for (int i : PathFinder.NEIGHBOURS8){
			if (!Dungeon.level.solid[cell+i]){
				GameScene.add( Blob.seed( cell+i, 180, SmokeScreen.class ) );
			} else {
				centerVolume += 180;
			}
		}

		GameScene.add( Blob.seed( cell, centerVolume, SmokeScreen.class ) );
	}
}