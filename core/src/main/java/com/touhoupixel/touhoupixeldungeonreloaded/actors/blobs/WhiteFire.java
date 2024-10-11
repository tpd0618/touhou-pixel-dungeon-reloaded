package com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs;

import static java.lang.Math.min;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AuraReimu;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Healing;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.BlobEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.EnergyParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.FlameParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.WhiteFlameParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class WhiteFire extends Blob{ // now don't used
    @Override
    protected void evolve() {

        int cell = area.left+1;
        affect(cell);

        off[cell] = cur[cell] - 1;
        volume += off[cell];
    }

    public void affect(int pos){ // TODO make cleansing blobs?
        Char ch = Actor.findChar(pos);
        if (ch != null && !(ch instanceof Hero)){

        }
        else if (ch != null && ch instanceof Hero && ch.buff(AuraReimu.class) != null){

        }
    }

    @Override
    public void use( BlobEmitter emitter ) {
        super.use( emitter );
        emitter.pour( WhiteFlameParticle.FACTORY, 0.02f );
    }

    @Override
    public String tileDesc() {
        return Messages.get(this, "desc");
    }
}
