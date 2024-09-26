package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mai;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Satono;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Invasion extends Spellcard{
    {
        left = 5;
    }
    private static float DELAY = 1f;

    @Override
    public void activate() {
        spawnEnemiesInDoors();
    }

    @Override
    public void deactivate() {
        spawnEnemiesInDoors();
        super.deactivate();
    }

    public void spawnEnemiesInDoors(){
        for (int i = 0; i < Dungeon.level.length(); i++){
            if ((Dungeon.level.map[i] == Terrain.DOOR || Dungeon.level.map[i] == Terrain.OPEN_DOOR) && Actor.findChar(i) == null && Random.Int(4) > 0){
                Mob m;
                int t = Random.Int(3);
                switch (t){
                    case 0:
                        m = Dungeon.level.createMob();
                        break;
                    case 1:
                        m = new Mai();
                        break;
                    default:
                        m = new Satono();
                }
                m.state = m.WANDERING;
                m.pos = i;
                GameScene.add(m, DELAY);
                Dungeon.level.mobs.add(m);
                ScrollOfTeleportation.appear(m, i);
                Dungeon.level.occupyCell(m);
                Buff.prolong(m, SuperHard.class, 1f);
            }
        }
    }
}
