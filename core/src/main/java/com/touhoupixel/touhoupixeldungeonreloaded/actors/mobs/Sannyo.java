/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LostInventory;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SannyoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Sannyo extends Mob {

    {
        spriteClass = SannyoSprite.class;

        HP = HT = 138;
        defenseSkill = 32;
        EXP = 17;
        maxLvl = 40;

        properties.add(Property.YOKAI);

        loot = new ScrollOfSirensSong();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(14, 21);
    }

    @Override
    public int attackSkill(Char target) {
        return 37;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            if (Statistics.difficulty > 2) {
                Statistics.playercorruption += 1;
            }
            if (Statistics.difficulty > 4) {
                Dungeon.hero.STR--;
            }
            ArrayList<Item> gazer = new ArrayList<>();
            if (hero.buff(LostInventory.class) == null) {
                for (Item i : Dungeon.hero.belongings) {
                    if (!i.unique && (i instanceof Potion || i instanceof Herb)) {
                        gazer.add(i);
                    }
                }
                if ((Random.Int(5) == 0)) {
                    damage = Math.max(damage, hero.HP / 2);
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