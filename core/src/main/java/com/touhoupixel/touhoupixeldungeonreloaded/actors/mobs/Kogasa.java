package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfFear;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfBlastWave;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KogasaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Kogasa extends Mob {

    {
        spriteClass = KogasaSprite.class;

        HP = HT = 9;
        defenseSkill = 2;
        EXP = 1;
        maxLvl = 10;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new StoneOfFear();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 4);
    }

    @Override
    public int attackSkill(Char target) {
        return 7;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Ballistica trajectory = new Ballistica(this.pos, enemy.pos, Ballistica.STOP_TARGET);
            //trim it to just be the part that goes past them
            trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);
            //knock them back along that ballistica
            WandOfBlastWave.throwChar(enemy, trajectory, 3, false, true, getClass());
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
            GLog.w(Messages.get(this, "fear"));
            return damage;
        }
        return damage;
    }
}