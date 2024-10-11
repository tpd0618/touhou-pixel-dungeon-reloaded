package com.touhoupixel.touhoupixeldungeonreloaded.effects.particles;

import com.watabou.noosa.particles.Emitter;

public class WhiteFlameParticle extends FlameParticle {
    public static final Emitter.Factory FACTORY = new Emitter.Factory() {
        @Override
        public void emit( Emitter emitter, int index, float x, float y ) {
            ((WhiteFlameParticle)emitter.recycle( WhiteFlameParticle.class )).reset( x, y );
        }
        @Override
        public boolean lightMode() {
            return true;
        }
    };

    public WhiteFlameParticle() {
        super();

        color( 0xBBBBAA );
        lifespan = 0.7f;

        acc.set( 0, -60 );
    }

    public void reset( float x, float y ) {
        super.reset(x, y);
        size = 2;
    }

    @Override
    public void update() {
        super.update();
        float p = left / lifespan;
        am = p > 0.8f ? (1 - p) * 5 : 1;
    }
}
