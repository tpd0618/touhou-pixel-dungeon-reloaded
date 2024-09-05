package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeHunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HecatiaRule;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Randomizer;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YuumaAbsorb;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SeijaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class BossSeija extends Mob {

    {
        spriteClass = SeijaSprite.class;

        HP = HT = 720;
        defenseSkill = 30;
        EXP = 26;
        maxLvl = 99;

        properties.add(Property.BOSS);
        properties.add(Property.YOKAI);
        properties.add(Property.HARASSMENT);

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
    protected boolean act() {
        boolean result = super.act();
        for (Buff c : Dungeon.heroine.buffs()) {
            if (c.type == Buff.buffType.POSITIVE) {
                c.detach();
                GLog.w(Messages.get(this, "absorb"));
            }
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(59, 71);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(17, 25);
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
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment){
            if (Dungeon.heroine.HP % 10 == 0){
                Buff.prolong(enemy, HinaCurse.class, HinaCurse.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 1){
                Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 2){
                Buff.prolong(enemy, Slow.class, Slow.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 3){
                Buff.prolong(enemy, Weakness.class, Weakness.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 4){
                Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 5){
                Buff.prolong(enemy, Randomizer.class, Randomizer.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 6){
                Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 7){
                Buff.prolong(enemy, Inversion.class, Inversion.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 8){
                Buff.prolong(enemy, Silence.class, Silence.DURATION);
            }
            if (Dungeon.heroine.HP % 10 == 9){
                Buff.prolong(enemy, ExtremeHunger.class, ExtremeHunger.DURATION);
            }
        }
        return damage;
    }
}