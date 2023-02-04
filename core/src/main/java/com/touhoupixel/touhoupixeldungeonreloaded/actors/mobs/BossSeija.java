package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.KomachiCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LunaSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.StarSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SunnySnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.Painter;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AunnSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SeijaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.Map;

public class BossSeija extends Mob {

    {
        spriteClass = SeijaSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.RINGING_BLOOM) ? 750 : 500;
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
        Buff.detach(Dungeon.hero, SunnySnipe.class);
        Buff.detach(Dungeon.hero, LunaSnipe.class);
        Buff.detach(Dungeon.hero, StarSnipe.class);
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
            Buff.prolong(Dungeon.hero, WandZeroDamage.class, WandZeroDamage.DURATION * 2f);
            switch (Random.Int(3)) {
                case 0:
                default:
                    Buff.prolong(Dungeon.hero, SunnySnipe.class, SunnySnipe.DURATION * 1000f);
                    break;
                case 1:
                    Buff.prolong(Dungeon.hero, LunaSnipe.class, LunaSnipe.DURATION * 1000f);
                    break;
                case 2:
                    Buff.prolong(Dungeon.hero, StarSnipe.class, StarSnipe.DURATION * 1000f);
                    break;
            }
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (this.HP < this.HT / 2 && enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(5) == 0) {
            damage = Math.max(damage, hero.HP - 1);
            GLog.w(Messages.get(Seija.class, "reverse"));
        }
        if (this.HP < this.HT / 2 && enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            if (Random.Int(2) == 0) {
                if (hero.buff(SunnySnipe.class) != null) {
                    Buff.detach(Dungeon.hero, SunnySnipe.class);
                    switch (Random.Int(2)) {
                        case 0:
                        default:
                            Buff.prolong(enemy, LunaSnipe.class, LunaSnipe.DURATION * 1000f);
                            break;
                        case 1:
                            Buff.prolong(enemy, StarSnipe.class, StarSnipe.DURATION * 1000f);
                            break;
                    }
                }
                if (hero.buff(LunaSnipe.class) != null) {
                    Buff.detach(Dungeon.hero, LunaSnipe.class);
                    switch (Random.Int(2)) {
                        case 0:
                        default:
                            Buff.prolong(enemy, SunnySnipe.class, SunnySnipe.DURATION * 1000f);
                            break;
                        case 1:
                            Buff.prolong(enemy, StarSnipe.class, StarSnipe.DURATION * 1000f);
                            break;
                    }
                }
                if (hero.buff(StarSnipe.class) != null) {
                    Buff.detach(Dungeon.hero, StarSnipe.class);
                    switch (Random.Int(2)) {
                        case 0:
                        default:
                            Buff.prolong(enemy, LunaSnipe.class, LunaSnipe.DURATION * 1000f);
                            break;
                        case 1:
                            Buff.prolong(enemy, SunnySnipe.class, SunnySnipe.DURATION * 1000f);
                            break;
                    }
                }
            }
        }
        return damage;
    }
}