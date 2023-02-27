package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.EvasiveCounterattack;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HecatiaRule;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LunaSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionPressure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.StarSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SunnySnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperDegrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AunnSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HecatiaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class BossHecatia extends Mob {

    {
        spriteClass = HecatiaSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.RINGING_BLOOM) ? 7500 : 5000;
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
        Dungeon.level.drop(new SkeletonKey(50), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));

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
            if (mob instanceof Toyohime){
                mob.die(null);
            }
            if (mob instanceof Yorihime){
                mob.die(null);
            }
        }
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        switch (Random.Int(6)) {
            case 0:
            default:
            Toyohime toyohime = new Toyohime();
            toyohime.state = toyohime.WANDERING;
            toyohime.pos = Dungeon.level.randomDestination(toyohime);
            if (toyohime.pos != -1) {
                GameScene.add(toyohime);
                toyohime.beckon(Dungeon.hero.pos);
            }
            break;
            case 1:
            Yorihime yorihime = new Yorihime();
            yorihime.state = yorihime.WANDERING;
            yorihime.pos = Dungeon.level.randomDestination(yorihime);
            if (yorihime.pos != -1) {
                GameScene.add(yorihime);
                yorihime.beckon(Dungeon.hero.pos);
            }
            break;
            case 2:
            Reimu reimu = new Reimu();
            reimu.state = reimu.WANDERING;
            reimu.pos = Dungeon.level.randomDestination(reimu);
            if (reimu.pos != -1) {
                GameScene.add(reimu);
                reimu.beckon(Dungeon.hero.pos);
            }
            break;
            case 3:
            Utsuho utsuho = new Utsuho();
            utsuho.state = utsuho.WANDERING;
            utsuho.pos = Dungeon.level.randomDestination(utsuho);
            if (utsuho.pos != -1) {
                GameScene.add(utsuho);
                utsuho.beckon(Dungeon.hero.pos);
            }
            break;
            case 4:
            Yuuma yuuma = new Yuuma();
            yuuma.state = yuuma.WANDERING;
            yuuma.pos = Dungeon.level.randomDestination(yuuma);
            if (yuuma.pos != -1) {
                GameScene.add(yuuma);
                yuuma.beckon(Dungeon.hero.pos);
            }
            break;
            case 5:
            Eiki eiki = new Eiki();
            eiki.state = eiki.WANDERING;
            eiki.pos = Dungeon.level.randomDestination(eiki);
            if (eiki.pos != -1) {
                GameScene.add(eiki);
                eiki.beckon(Dungeon.hero.pos);
            }
            break;
        }
        return super.defenseProc(enemy, damage);
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
            Buff.prolong(Dungeon.hero, WandZeroDamage.class, WandZeroDamage.DURATION*3f);
            Buff.prolong(Dungeon.hero, EvasiveCounterattack.class, EvasiveCounterattack.DURATION*3f);
            Buff.prolong(Dungeon.hero, HecatiaRule.class, HecatiaRule.DURATION*66666f);
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            if ((Random.Int(5) == 0)) {
                Buff.prolong(enemy, DismantlePressure.class, DismantlePressure.DURATION);
            }
            if (this.HP < this.HT / 2) {
                Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION * 1000f);
                Buff.prolong(this, Hisou.class, Hisou.DURATION * 1000f);
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION * 1000f);
            }
        }
        return damage;
    }
}