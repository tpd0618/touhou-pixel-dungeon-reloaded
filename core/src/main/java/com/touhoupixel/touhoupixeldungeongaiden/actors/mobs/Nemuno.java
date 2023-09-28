package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.NemunoSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Nemuno extends Mob {

    {
        spriteClass = NemunoSprite.class;

        HP = HT = 72;
        defenseSkill = 20;
        EXP = 10;
        maxLvl = 27;

        properties.add(Property.YOKAI);

        loot = Generator.Category.WEP_T4;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 14);
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            if (Random.Int(5) == 0 && hero.HP > 3 && enemy instanceof Hero) {
                damage = Math.max(damage, hero.HP / 2);
                Sample.INSTANCE.play(Assets.Sounds.CURSED);
                CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
                GLog.w(Messages.get(this, "torment"));
            }
        }
        return damage;
    }
}