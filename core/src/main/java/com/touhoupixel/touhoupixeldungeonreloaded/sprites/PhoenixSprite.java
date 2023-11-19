package com.touhoupixel.touhoupixeldungeonreloaded.sprites;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.watabou.noosa.TextureFilm;

public class PhoenixSprite extends MobSprite{
    public PhoenixSprite() {
        super();

        texture( Assets.Sprites.PHOENIX );

        TextureFilm frames = new TextureFilm( texture, 12, 15 );

        idle = new Animation( 2, true );
        idle.frames( frames, 0, 0, 0, 1, 0, 0, 1, 1 );

        run = new Animation( 12, true );
        run.frames( frames, 4, 5, 6, 7 );

        attack = new Animation( 12, false );
        attack.frames( frames, 2, 3, 0 );

        die = new Animation( 12, false );
        die.frames( frames, 8, 9, 10 );

        play( idle );
    }
    @Override
    public void update() {
        if (ch != null && ch.isAlive() && burning == null) add(State.BURNING);
        else if (ch == null || !ch.isAlive()) {
            remove(State.BURNING);
        }

        super.update();
    }


}
