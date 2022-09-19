package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragement;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SakuyaDaggerSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class SakuyaDagger extends Mob {

    {
        spriteClass = SakuyaDaggerSprite.class;

        HP = HT = 1;
        defenseSkill = 0;
        EXP = 0;
        maxLvl = 99;

        flying = true;
    }

    @Override
    public int damageRoll() {
        return 0;
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy.HP > 1){
            switch (Random.Int(3)) {
                case 0:
                default:
                    enemy.damage(4, this);
                    break;
                case 1:
                    enemy.damage(5, this);
                    break;
                case 2:
                    enemy.damage(6, this);
                    break;
            }
        }
        super.die(this);
        Statistics.enemiesSlain -= 1;
        Sample.INSTANCE.play( Assets.Sounds.BLAST );
        return damage;
    }
}