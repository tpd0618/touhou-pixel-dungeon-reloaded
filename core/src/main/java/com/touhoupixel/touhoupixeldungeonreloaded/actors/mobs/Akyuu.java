package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragement;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfRingoDango;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AkyuuSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Akyuu extends Mob {

    {
        spriteClass = AkyuuSprite.class;

        HP = HT = 60;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        loot = new LifeFragement();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 7);
    }

    @Override
    public int attackSkill(Char target) {
        return 12;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (HP > 3) {
            HP = HP/2;
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
        }
        return damage;
    }
}