package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Challenges;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeongaiden.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.TenshiSprite;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BossHealthBar;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class BossTenshi extends Mob {

    {
        spriteClass = TenshiSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.LAST_SURPRISE) ? 3600 : 1800;
        defenseSkill = 40;
        EXP = 30;
        maxLvl = 99;

        properties.add(Property.BOSS);
        properties.add(Property.WARP);

        immunities.add(Paralysis.class);

        loot = new Life();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(35), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(25, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 45;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void notice() {
        super.notice();
        if (!BossHealthBar.isAssigned()) {
            BossHealthBar.assignBoss(this);
            yell(Messages.get(this, "boss"));
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            if (Statistics.tenshiattackstep == 1){
                GLog.w(Messages.get(this, "charge"));
            }
            if (Statistics.tenshiattackstep > 3){
                Statistics.tenshiattackstep = -1;
                Camera.main.shake( 30, 1f );
                if (!Dungeon.heroine.flying) {
                    Dungeon.heroine.damage(50, this);
                    if (!Dungeon.heroine.isAlive()) {
                        Dungeon.fail(BossTenshi.class);
                        GLog.n( Messages.get(BossTenshi.class, "ondeath") );
                    }
                }
                Sample.INSTANCE.play(Assets.Sounds.BLAST);
                GLog.w(Messages.get(this, "earthquake"));
            }

            Statistics.tenshiattackstep += 1;

            if (this.HP < this.HT / 2){
                Buff.prolong(enemy, Inversion.class, Inversion.DURATION);
            }
        }
        return damage;
    }
}