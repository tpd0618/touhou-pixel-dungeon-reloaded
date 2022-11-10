package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LostInventory;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DestroyArmorTrap;
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

        HP = HT = 600;
        defenseSkill = 40;
        EXP = 40;
        maxLvl = 99;

        properties.add(Property.BOSS);

        loot = new Spellcard();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(20), pos ).sprite.drop();
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(21, 28);
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
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            if (Dungeon.hero.belongings.armor != null && Random.Int(3) == 0) {
                new DestroyArmorTrap().set(target).activate();
            }
            ArrayList<Item> gazer = new ArrayList<>();
            if (hero.buff(LostInventory.class) == null) {
                for (Item i : Dungeon.hero.belongings) {
                    if (!i.unique && (i instanceof Potion || i instanceof Herb)) {
                        gazer.add(i);
                    }
                }
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
            if (Dungeon.hero.belongings.armor == null) {
                Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION);
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
        }
        return damage;
    }
}