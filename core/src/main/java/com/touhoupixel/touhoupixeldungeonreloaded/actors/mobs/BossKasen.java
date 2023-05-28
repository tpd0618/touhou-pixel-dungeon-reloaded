package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LostInventory;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.EnchantEraseTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KasenSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class BossKasen extends Mob {

    {
        spriteClass = KasenSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.RINGING_BLOOM) ? 750 : 500;
        defenseSkill = 20;
        EXP = 22;
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
        Dungeon.level.drop(new SkeletonKey(20), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(10, 13);
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
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
            Buff.prolong(Dungeon.hero, WandZeroDamage.class, WandZeroDamage.DURATION*2f);
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        new EnchantEraseTrap().set(enemy.pos).activate();
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            ArrayList<Item> gazer = new ArrayList<>();
            if (hero.buff(LostInventory.class) == null) {
                for (Item i : Dungeon.hero.belongings) {
                    if (!i.unique && (i instanceof Potion || i instanceof Herb)) {
                        gazer.add(i);
                    }
                }
                if (Random.Int(4) == 0) {
                    if (!gazer.isEmpty()) {
                        Item hypnotize = Random.element(gazer).detach(Dungeon.hero.belongings.backpack);
                        GLog.w(Messages.get(Reisen.class, "gaze"));
                        hero.sprite.emitter().start(Speck.factory(Speck.BUBBLE), 0.2f, 3);
                        Sample.INSTANCE.play(Assets.Sounds.LULLABY);
                        if (hypnotize instanceof Potion) {
                            ((Potion) hypnotize).drink((Hero) hero);
                        }
                        if (hypnotize instanceof Herb) {
                            hypnotize.execute((Hero) hero);
                        }
                    } else {
                        GLog.w(Messages.get(Reisen.class, "failtogaze"));
                    }
                }
            }
            if (this.HP < this.HT/2) {
                Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION);
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
        }
        return damage;
    }
}