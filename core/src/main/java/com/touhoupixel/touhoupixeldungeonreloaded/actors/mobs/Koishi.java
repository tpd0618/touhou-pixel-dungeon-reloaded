package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfReverseYingYang;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KoishiSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Koishi extends Mob {

    {
        spriteClass = KoishiSprite.class;

        HP = HT = 249;
        defenseSkill = 45;
        EXP = 23;
        maxLvl = 75;

        loot = new PotionOfReverseYingYang();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(36, 41);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    //transparent sprite is the special ability
}