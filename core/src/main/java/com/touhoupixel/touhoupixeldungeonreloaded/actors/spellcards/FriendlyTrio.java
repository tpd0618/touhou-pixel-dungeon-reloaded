package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.IronRings;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Kanako;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.KanakoBuffed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Sanae;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.SanaeBuffed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.watabou.utils.PathFinder;

public class FriendlyTrio extends Spellcard{
    {
        left = 30;
    }

    @Override
    public void activate() {
        boolean isSanaeSummoned = false;
        boolean isKanakoSummoned = false;
        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++){
            int cell = user.pos + PathFinder.NEIGHBOURS8[i];
            if (Actor.findChar( cell ) == null && (Dungeon.level.passable[cell] || Dungeon.level.avoid[cell])){
                if (!isSanaeSummoned){
                    Sanae sanae = summonSanae();
                    sanae.pos = cell;
                    GameScene.add(sanae);
                    ScrollOfTeleportation.appear(sanae, sanae.pos);
                    isSanaeSummoned = true;
                    continue;
                }
                if (!isKanakoSummoned){
                    Kanako kanako = summonKanako();
                    kanako.pos = cell;
                    GameScene.add(kanako);
                    ScrollOfTeleportation.appear(kanako, kanako.pos);
                    isKanakoSummoned = true;
                    break;
                }
            }
        }
        if (!isSanaeSummoned){
            int disLimit = 12;
            PathFinder.buildDistanceMap(Dungeon.heroine.pos, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));

            Mob mob = summonSanae();
            mob.state = mob.WANDERING;
            int tries = 30;
            do {
                mob.pos = Dungeon.level.randomRespawnCell(mob);
                tries--;
            } while ((mob.pos == -1 || PathFinder.distance[mob.pos] < disLimit) && tries > 0);

            if (Dungeon.heroine.isAlive() && mob.pos != -1 && PathFinder.distance[mob.pos] >= disLimit) {
                GameScene.add( mob );
            }
        }
        if (!isKanakoSummoned){
            int disLimit = 12;
            PathFinder.buildDistanceMap(Dungeon.heroine.pos, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));

            Mob mob = summonKanako();
            mob.state = mob.WANDERING;
            int tries = 30;
            do {
                mob.pos = Dungeon.level.randomRespawnCell(mob);
                tries--;
            } while ((mob.pos == -1 || PathFinder.distance[mob.pos] < disLimit) && tries > 0);

            if (Dungeon.heroine.isAlive() && mob.pos != -1 && PathFinder.distance[mob.pos] >= disLimit) {
                GameScene.add( mob );
            }
        }
        super.activate();
    }

    @Override
    protected boolean act() {
        if (user != null){
            Buff.prolong(user, DoubleSpeed.class, 2f);
            Buff.prolong(user, IronRings.class, 2f);
        }
        return super.act();
    }

    private Sanae summonSanae(){
        SanaeBuffed sanae = new SanaeBuffed();
        Buff.prolong(sanae, DoubleSpeed.class, left);
        Buff.prolong(sanae, Hisou.class, left);
        sanae.state = sanae.WANDERING;
        return sanae;
    }
    private Kanako summonKanako(){
        KanakoBuffed kanako = new KanakoBuffed();
        Buff.prolong(kanako, DoubleSpeed.class, left);
        Buff.prolong(kanako, Hisou.class, left);
        kanako.state = kanako.WANDERING;
        return kanako;
    }
}
