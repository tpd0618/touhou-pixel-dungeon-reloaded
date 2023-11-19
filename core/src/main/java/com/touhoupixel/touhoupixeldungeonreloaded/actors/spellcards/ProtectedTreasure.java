package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AlarmTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BalanceTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BlazingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BurningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ChillingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ConfusionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlockTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FrostTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GeyserTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.RockfallTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SlowTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.StorywayTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.Trap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class ProtectedTreasure extends Spellcard{
    {
        left = 2;
    }
    @Override
    public void activate(){
        Char enemy = user.getEnemy();
        (new RockfallTrap()).set(enemy.pos).activate();
        PathFinder.buildDistanceMap( enemy.pos, BArray.not( Dungeon.level.solid, null ), 2 );
        for (int i = 0; i < PathFinder.distance.length; i++) {
            if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                int cellT = Dungeon.level.map[i];
                if (i == enemy.pos || (cellT != Terrain.EMPTY && cellT != Terrain.GRASS && cellT != Terrain.WATER)) continue;
                Dungeon.level.set(i, Terrain.EMPTY);
                Trap trap;
                switch (Random.Int(10)){
                    case 0:
                        trap = new BurningTrap();
                        break;
                    case 1:
                        trap = new ChillingTrap();
                        break;
                    case 2:
                        trap = new FrostTrap();
                        break;
                    case 3:
                        trap = new AlarmTrap();
                        break;
                    case 4:
                        trap = new BlazingTrap();
                        break;
                    case 5:
                        trap = new SummoningTrap();
                        break;
                    case 6:
                        trap = new StorywayTrap();
                        break;
                    case 7:
                        trap = new SlowTrap();
                        break;
                    case 8:
                        trap = new ConfusionTrap();
                        break;
                    default:
                        trap = new FlockTrap();
                        break;
                }
                Dungeon.level.setTrap(trap.reveal(), i);
                Dungeon.level.set(i, Terrain.TRAP);
            }
        }
    }
    public void deactivate(){
        deactivate(Random.Int(13, 14));
    }

}
