package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.EikiSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Eiki extends Mob {

    {
        spriteClass = EikiSprite.class;

        HP = HT = 380;
        defenseSkill = 50;
        EXP = 25;
        maxLvl = 99;

        loot = new SpellcardFragment();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Statistics.enemiesSlain/17, Statistics.enemiesSlain/16);
    }

    @Override
    public int attackSkill(Char target) {
        return 75;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}