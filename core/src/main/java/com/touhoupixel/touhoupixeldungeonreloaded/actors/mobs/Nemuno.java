package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.NemunoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Nemuno extends Mob {

    {
        spriteClass = NemunoSprite.class;

        HP = HT = 156;
        defenseSkill = 37;
        EXP = 8;
        maxLvl = 45;

        loot = Generator.Category.MIS_T5;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(27, 31);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(7) == 0) {
            damage = Math.max(damage, hero.HP / 2);
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
            GLog.w(Messages.get(this, "torment"));
        }
        return damage;
    }
}