package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AunnSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HecatiaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class BossHecatia extends Mob {

    {
        spriteClass = HecatiaSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.RINGING_BLOOM) ? 4500 : 3000;
        defenseSkill = 50;
        EXP = 50;
        maxLvl = 99;

        properties.add(Property.BOSS);
        properties.add(Property.GOD);

        immunities.add(Paralysis.class);
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(50), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
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
            Toyohime toyohime = new Toyohime();
            toyohime.state = toyohime.WANDERING;
            toyohime.pos = Dungeon.level.randomDestination( toyohime );
            if (toyohime.pos != -1) {
                GameScene.add(toyohime);
                toyohime.beckon(Dungeon.hero.pos);
            }
            Toyohime toyohime2 = new Toyohime();
            toyohime2.state = toyohime2.WANDERING;
            toyohime2.pos = Dungeon.level.randomDestination( toyohime2 );
            if (toyohime2.pos != -1) {
                GameScene.add(toyohime2);
                toyohime2.beckon(Dungeon.hero.pos);
            }
            Toyohime toyohime3 = new Toyohime();
            toyohime3.state = toyohime3.WANDERING;
            toyohime3.pos = Dungeon.level.randomDestination( toyohime3 );
            if (toyohime3.pos != -1) {
                GameScene.add(toyohime3);
                toyohime3.beckon(Dungeon.hero.pos);
            }
            Toyohime toyohime4 = new Toyohime();
            toyohime4.state = toyohime4.WANDERING;
            toyohime4.pos = Dungeon.level.randomDestination( toyohime4 );
            if (toyohime4.pos != -1) {
                GameScene.add(toyohime4);
                toyohime4.beckon(Dungeon.hero.pos);
            }
            Toyohime toyohime5 = new Toyohime();
            toyohime5.state = toyohime5.WANDERING;
            toyohime5.pos = Dungeon.level.randomDestination( toyohime5 );
            if (toyohime5.pos != -1) {
                GameScene.add(toyohime5);
                toyohime5.beckon(Dungeon.hero.pos);
            }
            Yorihime yorihime = new Yorihime();
            yorihime.state = yorihime.WANDERING;
            yorihime.pos = Dungeon.level.randomDestination( yorihime );
            if (yorihime.pos != -1) {
                GameScene.add(yorihime);
                yorihime.beckon(Dungeon.hero.pos);
            }
            Yorihime yorihime2 = new Yorihime();
            yorihime2.state = yorihime2.WANDERING;
            yorihime2.pos = Dungeon.level.randomDestination( yorihime2 );
            if (yorihime2.pos != -1) {
                GameScene.add(yorihime2);
                yorihime2.beckon(Dungeon.hero.pos);
            }
            Yorihime yorihime3 = new Yorihime();
            yorihime3.state = yorihime3.WANDERING;
            yorihime3.pos = Dungeon.level.randomDestination( yorihime3 );
            if (yorihime3.pos != -1) {
                GameScene.add(yorihime3);
                yorihime3.beckon(Dungeon.hero.pos);
            }
            Yorihime yorihime4 = new Yorihime();
            yorihime4.state = yorihime4.WANDERING;
            yorihime4.pos = Dungeon.level.randomDestination( yorihime4 );
            if (yorihime4.pos != -1) {
                GameScene.add(yorihime4);
                yorihime4.beckon(Dungeon.hero.pos);
            }
            Yorihime yorihime5 = new Yorihime();
            yorihime5.state = yorihime5.WANDERING;
            yorihime5.pos = Dungeon.level.randomDestination( yorihime5 );
            if (yorihime5.pos != -1) {
                GameScene.add(yorihime5);
                yorihime5.beckon(Dungeon.hero.pos);
            }
            Buff.prolong(Dungeon.hero, WandZeroDamage.class, WandZeroDamage.DURATION*3f);
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            if (Random.Int(2) == 0 && hero.HT > 3 && hero.HP > 3) {
                hero.HP -= 3;
                hero.HT -= 3;
                Statistics.healwoundsHTdown -= 3;
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