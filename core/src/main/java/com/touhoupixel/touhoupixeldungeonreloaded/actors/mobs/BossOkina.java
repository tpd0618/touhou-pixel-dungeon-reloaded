package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AunnSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.OkinaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class BossOkina extends Mob {

    {
        spriteClass = OkinaSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.RINGING_BLOOM) ? 900 : 600;
        defenseSkill = 35;
        EXP = 28;
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
        Dungeon.level.drop(new SkeletonKey(35), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));

        for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
            if (mob instanceof Satono){
                mob.die(null);
            }
            if (mob instanceof Mai){
                mob.die(null);
            }
        }
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(20, 25);
    }

    @Override
    public int attackSkill(Char target) {
        return 40;
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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR) {
            damage *= 3;
            if (this.HP < this.HT / 2){
                damage *= 2;
            }
        } else {
            Mai mai = new Mai();
            mai.state = mai.WANDERING;
            mai.pos = Dungeon.level.randomDestination( mai );
            if (mai.pos != -1) {
                GameScene.add(mai);
                mai.beckon(Dungeon.hero.pos);
            }
            Satono satono = new Satono();
            satono.state = satono.WANDERING;
            satono.pos = Dungeon.level.randomDestination( satono );
            if (satono.pos != -1) {
                GameScene.add(satono);
                satono.beckon(Dungeon.hero.pos);
            }
        }
        if (this.HP < this.HT / 2){
            Mai mai = new Mai();
            mai.state = mai.WANDERING;
            mai.pos = Dungeon.level.randomDestination( mai );
            if (mai.pos != -1) {
                GameScene.add(mai);
                mai.beckon(Dungeon.hero.pos);
            }
            Satono satono = new Satono();
            satono.state = satono.WANDERING;
            satono.pos = Dungeon.level.randomDestination( satono );
            if (satono.pos != -1) {
                GameScene.add(satono);
                satono.beckon(Dungeon.hero.pos);
            }
        }
        return damage;
    }
}