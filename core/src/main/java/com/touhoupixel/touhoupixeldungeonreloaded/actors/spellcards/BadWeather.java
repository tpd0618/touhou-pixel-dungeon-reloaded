package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.StormCloud;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Splash;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class BadWeather extends Spellcard{
    {
        left = 20;
    }
    @Override
    public void activate() {
        PathFinder.buildDistanceMap( user.pos, BArray.not( Dungeon.level.solid, null ), 10 );
        for (int cell = 0; cell < PathFinder.distance.length; cell++) {
            if (PathFinder.distance[cell] <= 10 && Random.Int(15) == 0) {
                int centerVolume = 240;
                GameScene.add( Blob.seed( cell, centerVolume, StormCloud.class ) );
                Splash.at( DungeonTilemap.tileCenterToWorld( cell ), -PointF.PI/2, PointF.PI/2, 0x5bc1e3, 100, 0.01f);
            }
        }
        super.activate();
    }
}
