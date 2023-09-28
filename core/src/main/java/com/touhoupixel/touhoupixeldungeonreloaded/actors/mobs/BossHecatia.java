package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HecatiaRule;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeonreloaded.items.EirinJunkoCounterElixir;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HecatiaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class BossHecatia extends Mob {

    {
        spriteClass = HecatiaSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.LAST_SURPRISE) ? 7000 : 3500;
        defenseSkill = 50;
        EXP = 50;
        maxLvl = 99;

        properties.add(Property.BOSS);
        properties.add(Property.GOD);

        immunities.add(Paralysis.class);
        immunities.add(Fire.class);
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(40), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));

        Buff.detach( Dungeon.heroine, HecatiaRule.class );

        for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
            if (mob instanceof Reimu){
                mob.die(null);
            }
            if (mob instanceof Utsuho){
                mob.die(null);
            }
            if (mob instanceof Yuuma){
                mob.die(null);
            }
            if (mob instanceof Eiki){
                mob.die(null);
            }
            if (mob instanceof Junko){
                mob.die(null);
            }
            if (mob instanceof Keiki){
                mob.die(null);
            }
            if (mob instanceof Toyohime){
                mob.die(null);
            }
            if (mob instanceof Yorihime){
                mob.die(null);
            }
            if (mob instanceof Hecatia){
                mob.die(null);
            }
            if (mob instanceof Yukari){
                mob.die(null);
            }
        }
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(50, 60);
    }

    @Override
    public int attackSkill(Char target) {
        return 90;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(10, 20);
    }

    @Override
    public void notice() {
        super.notice();
        if (!BossHealthBar.isAssigned()) {
            BossHealthBar.assignBoss(this);
            yell(Messages.get(this, "boss"));
            Buff.prolong(Dungeon.heroine, HecatiaRule.class, HecatiaRule.DURATION*66666f);
            Statistics.eirinelixircount = 4;
        }
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        new SummoningTrap().set(enemy.pos).activate();
        if (this.HP < this.HT / 2 && !Statistics.elixir_trigger){
            Dungeon.level.drop(new EirinJunkoCounterElixir(), 57).sprite.drop();
            Dungeon.level.drop(new EirinJunkoCounterElixir(), 232).sprite.drop();
            Dungeon.level.drop(new EirinJunkoCounterElixir(), 273).sprite.drop();
            Dungeon.level.drop(new EirinJunkoCounterElixir(), 448).sprite.drop();
            Statistics.eirinelixircount = 0;
            Statistics.elixir_trigger = true;
            GLog.w(Messages.get(this, "hecatia_barrier"));
        }
        return super.defenseProc(enemy, damage);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            switch (Random.Int(9)) {
                case 0:
                default:
                    Buff.affect(enemy, Poison.class).set(Math.round(Statistics.upgradesUsed/6f));
                    break;
                case 1:
                    Buff.affect(enemy, Bleeding.class).set(8);
                    break;
                case 2:
                    Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
                    break;
                case 3:
                    Buff.prolong(enemy, DismantlePressure.class, DismantlePressure.DURATION);
                    break;
                case 4:
                    Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
                    break;
                case 5:
                    Buff.prolong(enemy, CursedBlow.class, CursedBlow.DURATION);
                    break;
                case 6:
                    Buff.prolong(enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION);
                    break;
                case 7:
                    Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
                    break;
                case 8:
                    Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
                    break;
            }
            if (this.HP < this.HT / 2){
                Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 1000f);
                Buff.prolong(this, Hisou.class, Hisou.DURATION * 1000f);
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION * 1000f);
            }
        }
        return damage;
    }
}