package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;


import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.NPC;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.DrumSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Drummer extends Spellcard{
    {
        left = 100000;
    }
    protected int drumsCounter = 0;

    @Override
    protected boolean act() {
        if (drumsCounter <= 0) deactivate();
        return super.act();
    }

    @Override
    public void activate() {
        for (int i = 0; i < 2; i++){
            if (spawnDrum()) drumsCounter++;
        }
        if (drumsCounter > 0) GLog.w(Messages.get(this,"warning"));
    }
    public void decreaceDrumCounter(){
        drumsCounter--;
        if (drumsCounter <= 0) deactivate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }

    public boolean spawnDrum(){
        int disLimit = 20;
        Drum drum = new Drum();
        drum.source = this;
        drum.state = drum.WANDERING;
        int tries = 30;
        do {
            drum.pos = Dungeon.level.randomRespawnCell(drum);
            tries--;
        } while ((drum.pos == -1 || PathFinder.distance[drum.pos] < disLimit) && tries > 0);

        if (Dungeon.heroine.isAlive() && drum.pos != -1 && PathFinder.distance[drum.pos] >= disLimit) {
            GameScene.add( drum );
            return true;
        } else {
            return false;
        }
    }

    private static final String DRUMS_COUNTER = "drums_counter";
    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(DRUMS_COUNTER, drumsCounter);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        drumsCounter = bundle.getInt(DRUMS_COUNTER);
        super.restoreFromBundle(bundle);
    }

    public static class Drum extends NPC {
        {
            spriteClass = DrumSprite.class;
        }
        public Drummer source;

        @Override
        protected boolean act() {
            if (source == null) {
            for (Actor a : all())
                if (a instanceof Drummer) source = (Drummer) a;
            }
            if (source == null || source.left <= 0){
                destroy();
                sprite.die();
            }
            spend(TICK);
            return true;
        }

        @Override
        public int defenseSkill(Char enemy) {
            return INFINITE_EVASION;
        }

        @Override
        public void damage( int dmg, Object src ) {
        }

        @Override
        public boolean add(Buff buff) {
            return false;
        }

        @Override
        public boolean interact(Char c) {
            if (c == Dungeon.heroine) {
                Dungeon.heroine.spendAndNext(1f);
                Sample.INSTANCE.play(Assets.Sounds.DRUM, 1, Random.Float(0.91f, 1.1f));
                destroy();
                sprite.die();
            }
            return true;
        }

        @Override
        public void destroy() {
            if (source == null) {
                for (Actor a : all())
                    if (a instanceof Drummer) source = (Drummer) a;
            }
            if (source != null) source.decreaceDrumCounter();
            super.destroy();
        }
    }
}