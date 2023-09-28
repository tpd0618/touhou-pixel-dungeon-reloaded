package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SakuyaDaggerSprite;
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
        return 10;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        Buff.affect(enemy, Bleeding.class).set(3);
        destroy();
        sprite.die();
        Sample.INSTANCE.play( Assets.Sounds.BLAST );
        return damage;
    }
}