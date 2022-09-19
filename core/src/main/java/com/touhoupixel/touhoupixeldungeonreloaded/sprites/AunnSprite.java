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
import com.watabou.noosa.Game;
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.TextureFilm;

public class AunnSprite extends MobSprite {

	private MovieClip.Animation crumple;

	public AunnSprite() {
		super();

		texture( Assets.Sprites.AUNN );

		TextureFilm frames = new TextureFilm( texture, 12, 14 );

		idle = new MovieClip.Animation( 2, true );
		idle.frames( frames, 0, 0, 0, 0 );

		run = new MovieClip.Animation( 12, true );
		run.frames( frames, 2, 3, 4, 5, 6, 7 );

		attack = new MovieClip.Animation( 12, false );
		attack.frames( frames, 0, 8, 9 );

		crumple = new MovieClip.Animation( 15, false);
		crumple.frames( frames, 1, 1, 1, 1 );

		die = new MovieClip.Animation( 15, false );
		die.frames( frames, 0, 10, 11, 12, 13 );

		play( idle );
	}

	public void crumple(){
		hideEmo();
		play(crumple);
	}

	@Override
	public void die() {
		if (curAnim == crumple){
			//causes the sprite to not rise then fall again when dieing.
			die.frames[0] = die.frames[1] = die.frames[2] = die.frames[3];
		}
		super.die();
	}
}