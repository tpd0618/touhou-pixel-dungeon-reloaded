package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiCountdown;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RemiliaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
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