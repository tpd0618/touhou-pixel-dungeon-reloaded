package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Challenges;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.KomachiCurse;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeongaiden.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KomachiSprite;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BossHealthBar;
import com.watabou.utils.Random;

public class BossKomachi extends Mob {

    {
        spriteClass = KomachiSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.LAST_SURPRISE) ? 1800 : 900;
        defenseSkill = 25;
        EXP = 24;
        maxLvl = 99;

        properties.add(Property.BOSS);
        properties.add(Property.GOD);

        immunities.add(Paralysis.class);

        loot = new Spellcard();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(25), pos).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
        Buff.detach(Dungeon.heroine, KomachiCurse.class);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(8, 12);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
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
            ScrollOfTeleportation.teleportChar(this);
            Buff.prolong(enemy, KomachiCurse.class, KomachiCurse.DURATION);
            if (this.HP < this.HT / 2) {
                Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 1000f);
                Buff.prolong(this, Hisou.class, Hisou.DURATION * 1000f);
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION * 1000f);
            }
        }
        return damage;
    }
}