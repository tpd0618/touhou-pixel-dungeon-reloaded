package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Regrowth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Flare;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Thickets extends Spellcard{
    {
        left = 30;
    }
    @Override
    public void activate() {
        for (int i = 0; i < Dungeon.level.length(); i++){
            GameScene.add( Blob.seed(i, 15, Regrowth.class));
        }

        Char enemy = user.getEnemy();
        if (enemy != null){
            for (Buff b : enemy.buffs()){
                if (b.type == Buff.buffType.POSITIVE) b.detach();
            }
            Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
        }
    }
}
