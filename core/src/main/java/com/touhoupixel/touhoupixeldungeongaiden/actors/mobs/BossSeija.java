package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Challenges;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.CheatBreak;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ExtremeHunger;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeongaiden.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SeijaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BossHealthBar;
import com.watabou.utils.Random;

public class BossSeija extends Mob {

    {
        spriteClass = SeijaSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.LAST_SURPRISE) ? 3000 : 1500;
        defenseSkill = 30;
        EXP = 26;
        maxLvl = 99;

        baseSpeed = 2f;

        properties.add(Property.BOSS);
        properties.add(Property.YOKAI);

        loot = new Life();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(30), pos).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(34, 39);
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
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && this.buff(CheatBreak.class) == null){
            Buff.prolong(this, SuperHard.class, SuperHard.DURATION);
            if (this.HP < this.HT / 2 && Random.Int(5) == 0) {
                Buff.prolong(enemy, ExtremeHunger.class, ExtremeHunger.DURATION);
            }
        }
        return damage;
    }
}