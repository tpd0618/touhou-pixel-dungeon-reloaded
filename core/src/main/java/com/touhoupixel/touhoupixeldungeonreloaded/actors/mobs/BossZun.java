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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
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
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ZUNSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class BossZun extends Mob {

    {
        spriteClass = ZUNSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.LAST_SURPRISE) ? 40000 : 20000;
        defenseSkill = 99;
        EXP = 99;
        maxLvl = 99;

        properties.add(Property.BOSS);
        properties.add(Property.HUMAN);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        immunities.add(Paralysis.class);
        immunities.add(Fire.class);
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(98), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));

        Buff.detach( Dungeon.heroine, HecatiaRule.class );

        for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
            if (mob instanceof BossZunAvarice){
                mob.die(null);
            }
            if (mob instanceof BossZunEnvy){
                mob.die(null);
            }
            if (mob instanceof BossZunPride){
                mob.die(null);
            }
            if (mob instanceof BossZunVanity){
                mob.die(null);
            }
            if (mob instanceof BossZunGluttony){
                mob.die(null);
            }
            if (mob instanceof BossZunLust){
                mob.die(null);
            }
            if (mob instanceof BossZunWrath){
                mob.die(null);
            }
        }
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(50, 99);
    }

    @Override
    public int attackSkill(Char target) {
        return 99;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(10, 30);
    }

    @Override
    public void notice() {
        super.notice();
        if (!BossHealthBar.isAssigned()) {
            BossHealthBar.assignBoss(this);
            yell(Messages.get(this, "boss"));
            Buff.prolong(Dungeon.heroine, HecatiaRule.class, HecatiaRule.DURATION*66666f);
        }
    }

    @Override
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && this.state != SLEEPING && this.state != FLEEING) {

        }
        return super.act();
    }
}