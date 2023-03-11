package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HerbDegrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeikiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.watabou.utils.Random;

public class BossKeiki extends Mob {

    {
        spriteClass = KeikiSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.RINGING_BLOOM) ? 1800 : 1200;
        defenseSkill = 45;
        EXP = 34;
        maxLvl = 99;

        baseSpeed = 2f;

        properties.add(Property.BOSS);
        properties.add(Property.GOD);

        immunities.add(Paralysis.class);

        loot = new Life();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(45), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(28, 33);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            Buff.prolong(enemy, Weakness.class, Weakness.DURATION);
            Buff.prolong(enemy, Vulnerable.class, Vulnerable.DURATION);
            Buff.prolong(enemy, Hex.class, Hex.DURATION);
            Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION);
            Buff.prolong(enemy, Slow.class, Slow.DURATION);
            Buff.prolong(enemy, HerbDegrade.class, HerbDegrade.DURATION);
            Buff.prolong(enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION);
            Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
            Buff.prolong(enemy, ZeroDexterity.class, ZeroDexterity.DURATION);
            Buff.prolong(enemy, RegenBlock.class, RegenBlock.DURATION);
            if (this.HP < this.HT / 2) {
                Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION * 1000f);
                Buff.prolong(this, Hisou.class, Hisou.DURATION * 1000f);
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION * 1000f);
            }
        }
        return damage;
    }
}