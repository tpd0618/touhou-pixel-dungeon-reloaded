package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SatoriSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Satori extends Mob {

    {
        spriteClass = SatoriSprite.class;

        HP = HT = 259;
        defenseSkill = 99;
        EXP = 23;
        maxLvl = 75;

        loot = new PotionOfMindVision();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(32, 36);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //defenseskill 99 is the special ability
}