package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Alchemy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Foliage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Freezing;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.StormCloud;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.ToxicGas;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mai;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Satono;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells.Okina;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.BlobEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.watabou.utils.Random;

public class DivinePower extends Spellcard{
    {
        left = 12;
    }

    @Override
    protected boolean act() {
        if (Random.Int(2) == 0){
            int typeBuff = Random.Int(3);
            int frequency = Random.Int(6,10);

            for (int i = 1; i < Dungeon.level.length()-1; i++){
                if (i % frequency != 0 || Dungeon.level.solid[i]) continue;
                Char c = Actor.findChar(i);
                switch (typeBuff){
                    case 0:
                        GameScene.add(Blob.seed(i, 1, ToxicGas.class));
                        buffChar(c, Might.class);
                        break;
                    case 1:
                        GameScene.add(Blob.seed(i, 1, Freezing.class));
                        buffChar(c, SuperHard.class);
                        break;
                    default:
                        GameScene.add(Blob.seed(i, 1, StormCloud.class));
                        buffChar(c, DoubleSpeed.class);
                        break;
                }
            }
        }

        return super.act();
    }
    private void buffChar(Char c, Class buff){
        if (c == null) return;
        else if (c.equals(user) || c instanceof Hero) return;
        else if (c instanceof Satono || c instanceof Mai) {
            Buff.prolong(c, Might.class, 7f);
            Buff.prolong(c, SuperHard.class, 7f);
            Buff.prolong(c, DoubleSpeed.class, 7f);
        }
        else {
            Buff.prolong(c, buff, 5f);
        }
    }
}
