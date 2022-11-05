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

package com.touhoupixel.touhoupixeldungeonreloaded.sprites;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Eirin;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Kagerou;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.MagicMissile;
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class EirinSprite extends MobSprite {

	public EirinSprite() {
		super();

		texture( Assets.Sprites.EIRIN );

		TextureFilm frames = new TextureFilm( texture, 12, 15 );

		idle = new MovieClip.Animation( 2, true );
		idle.frames( frames, 0, 0, 0, 1, 0, 0, 1, 1 );

		run = new MovieClip.Animation( 15, true );
		run.frames( frames, 0, 2, 3, 4 );

		attack = new MovieClip.Animation( 12, false );
		attack.frames( frames, 0, 5, 6 );

		zap = attack.clone();

		die = new MovieClip.Animation( 15, false );
		die.frames( frames, 0, 7, 8, 8, 9, 10 );

		play( idle );
	}

	public void zap( int cell ) {

		turnTo( ch.pos , cell );
		play( zap );

		MagicMissile.boltFromChar( parent,
				MagicMissile.SHADOW,
				this,
				cell,
				new Callback() {
					@Override
					public void call() {
						((Eirin)ch).onZapComplete();
					}
				} );
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
	}

	@Override
	public void onComplete( MovieClip.Animation anim ) {
		if (anim == zap) {
			idle();
		}
		super.onComplete( anim );
	}
}