package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Challenges;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.RemiCountdown;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeongaiden.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.RemiliaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BossHealthBar;
import com.watabou.utils.Random;

public class BossRemilia extends Mob {

    {
        spriteClass = RemiliaSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.LAST_SURPRISE) ? 350 : 175;
        defenseSkill = 15;
        EXP = 20;
        maxLvl = 99;

        baseSpeed = 2f;

        flying = true;

        properties.add(Property.BOSS);
        properties.add(Property.WARP);

        loot = new Spellcard();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(15), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
        Statistics.remi_countdown = true;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 9);
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
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
            Buff.prolong(Dungeon.heroine, RemiCountdown.class, RemiCountdown.DURATION);
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION);
        if (this.HP < this.HT/2) {
            Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
        }
        return damage;
    }
}