package com.touhoupixel.touhoupixeldungeonreloaded.sprites;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Random;

public class DrumSprite extends MobSprite{
    public DrumSprite() {
        super();

        texture( Assets.Sprites.DRUM );

        TextureFilm frames = new TextureFilm( texture, 12, 15 );

        idle = new Animation( 8, true );
        idle.frames( frames, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0 );

        run = idle.clone();
        attack = idle.clone();

        die = new Animation( 20, false );
        die.frames( frames, 0 );

        play( idle );
        curFrame = Random.Int( curAnim.frames.length );
    }
}
