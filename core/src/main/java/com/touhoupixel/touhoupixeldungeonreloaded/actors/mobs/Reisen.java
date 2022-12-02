package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.sun.tools.javac.jvm.Items;
import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LostInventory;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ReisenSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Reisen extends Mob {

    Items items;

    {
        spriteClass = ReisenSprite.class;

        HP = HT = 49;
        defenseSkill = 12;
        EXP = 9;
        maxLvl = 20;

        loot = Generator.Category.POTION;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 9);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            if (Dungeon.hero.heroClass == HeroClass.PLAYERSANAE || Dungeon.hero.heroClass == HeroClass.PLAYERYOUMU || Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION);
            }
            if (Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
                Buff.prolong(enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION/10f);
            }
            ArrayList<Item> gazer = new ArrayList<>();
            if (hero.buff(LostInventory.class) == null) {
                for (Item i : Dungeon.hero.belongings) {
                    if (!i.unique && (i instanceof Potion || i instanceof Herb)) {
                        gazer.add(i);
                    }
                }
                if ((Random.Int(5) == 0)) {
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