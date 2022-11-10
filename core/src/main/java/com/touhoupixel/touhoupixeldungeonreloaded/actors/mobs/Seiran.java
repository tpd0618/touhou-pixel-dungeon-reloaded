package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LostInventory;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SeiranSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Seiran extends Mob {

    {
        spriteClass = SeiranSprite.class;

        HP = HT = 269;
        defenseSkill = 42;
        EXP = 21;
        maxLvl = 50;

        loot = Generator.Category.HERB;
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(30, 34);
    }

    @Override
    public int attackSkill(Char target) {
        return 47;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            ArrayList<Item> gazer = new ArrayList<>();
            if (hero.buff(LostInventory.class) == null) {
                for (Item i : Dungeon.hero.belongings) {
                    if (!i.unique && (i instanceof Potion || i instanceof Herb)) {
                        gazer.add(i);
                    }
                }
                if ((Random.Int(4) == 0)) {
                    Buff.prolong(enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION/2f);
                    if (!gazer.isEmpty()) {
                        Item hypnotize = Random.element(gazer).detach(Dungeon.hero.belongings.backpack);
                        GLog.w(Messages.get(this, "gaze"));
                        hero.sprite.emitter().start(Speck.factory(Speck.BUBBLE), 0.2f, 3);
                        Sample.INSTANCE.play(Assets.Sounds.LULLABY);
                        if (hypnotize instanceof Potion) {
                            ((Potion) hypnotize).drink((Hero) hero);
                        }
                        if (hypnotize instanceof Herb) {
                            hypnotize.execute((Hero) hero);
                        }
                    } else {
                        GLog.w(Messages.get(this, "failtogaze"));
                    }
                }
            }
        }
        return damage;
    }
}