package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Backdoor extends Buff{
    {
        announced = true;
        revivePersists = true;
    }
    private float turnsCounter = 5f;

    @Override
    public boolean act() {
        if (turnsCounter > 0){
            turnsCounter -= TICK;
        }
        else {
            turnsCounter = Random.Int(4,7);
            spawnDoors();
        }

        spend(TICK);

        return true;
    }
    public void spawnDoors(){
        int cell = target.pos;
        for (int i : PathFinder.NEIGHBOURS4){
            if (!Dungeon.level.solid[cell+i] && (!Dungeon.level.avoid[cell+i] || Dungeon.level.map[cell+i] == Terrain.TRAP)){
                if (Dungeon.level.heaps.get(cell + i) == null && Actor.findChar(cell + i) == null){
                    Level.set( cell+i, Terrain.DOOR );
                }
                else {
                    Level.set( cell+i, Terrain.OPEN_DOOR );
                }
                GameScene.updateMap( cell+i );
            }
        }
        Level.set( cell, Terrain.OPEN_DOOR );
        GameScene.updateMap( cell);
        Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
    }

    private static final String TURNS_COUNTER = "turnsCounter";

    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(TURNS_COUNTER, turnsCounter);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        turnsCounter = bundle.getFloat(TURNS_COUNTER);
        super.restoreFromBundle(bundle);
    }

    @Override
    public String heroMessage() {
        return Messages.get(this, "heromsg");
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc");
    }

    @Override
    public int icon() {
        return BuffIndicator.BACKDOOR;
    }
    @Override
    public String iconTextDisplay() {
        return "";
    }
}
