package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.ChenBuffed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.watabou.utils.PathFinder;


public class SummonChen extends Spellcard{
    private static final float DELAY = 1f;
    {
        left = 2;
    }
    @Override
    public void activate(){
        int cell = -1;
        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            int p = user.pos + PathFinder.NEIGHBOURS8[i];
            if (Actor.findChar( p ) == null && (Dungeon.level.passable[p] || Dungeon.level.avoid[p])) {
                cell = p;
                break;
            }
        }
        if (cell == -1) return;

        ChenBuffed chen = new ChenBuffed();
        Buff.prolong(chen, DoubleSpeed.class, DoubleSpeed.DURATION);
        Buff.prolong(chen, Might.class, Might.DURATION);

        chen.state = chen.WANDERING;
        chen.pos = cell;
        GameScene.add(chen, DELAY);
        ScrollOfTeleportation.appear(chen, chen.pos);
        Dungeon.level.occupyCell(chen);
    }
}

