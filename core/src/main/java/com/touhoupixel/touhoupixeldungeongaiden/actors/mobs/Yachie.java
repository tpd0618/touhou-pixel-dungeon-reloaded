package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.AyaSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Yachie extends Mob {

    {
        spriteClass = AyaSprite.class;

        HP = HT = 200;
        defenseSkill = 30;
        EXP = 14;
        maxLvl = 30;

        properties.add(Property.YOKAI);

        loot = new LifeFragment();
        lootChance = 0.04f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(34, 39);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (Random.Int(0) == 0) {
            if (HP > 3) {
                HP = HP / 2;
                Sample.INSTANCE.play(Assets.Sounds.CURSED);
                CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
            }
        }
        return damage;
    }
}