package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells.MobWithSpellcard;

import java.util.ArrayList;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

public class SoulCloning extends Spellcard {
    {
        left = 2;
    }

    @Override
    public void activate() {
        ArrayList<Mob> mobs = new ArrayList<>();
        int nMobs = 3;
        for (Mob mob : Dungeon.level.mobs)
            if (mob.alignment == Char.Alignment.ENEMY && !mob.equals(user)
                    && user.fieldOfView[mob.pos] && mob.invisible <= 0  && !(mob instanceof MobWithSpellcard)
                    && !mob.properties().contains(Char.Property.BOSS) && !mob.properties().contains(Char.Property.MINIBOSS)) {
                mobs.add(mob);
            }
        for (int i = 0; i < mobs.size() && i < nMobs; i++){
            Mob m = Reflection.newInstance(mobs.remove(Random.Int(mobs.size())).getClass());
            Dungeon.level.spawnMob(12, m);
        }
    }
}
