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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.KeyHeal;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LostInventory;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.PotionBandolier;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ParseeSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Parsee extends Mob {

    {
        spriteClass = ParseeSprite.class;

        HP = HT = 168;
        defenseSkill = 35;
        EXP = 17;
        maxLvl = 42;

        properties.add(Property.YOKAI);

        loot = Generator.Category.SCROLL;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(20, 28);
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
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            MeleeWeapon meleeweapon = Dungeon.hero.belongings.getItem(MeleeWeapon.class);
            if (meleeweapon != null && meleeweapon.level() > 9) {
                if (Statistics.difficulty > 2) {
                    Buff.prolong(enemy, Silence.class, Silence.DURATION);
                }
                if (Statistics.difficulty > 4) {
                    Buff.detach(enemy, KeyHeal.class);
                }
                meleeweapon.enchantment = null;
                ArrayList<Item> gazer = new ArrayList<>();
                if (hero.buff(LostInventory.class) == null) {
                    for (Item i : Dungeon.hero.belongings) {
                        if (!i.unique && (i instanceof PotionOfHealing)) {
                            gazer.add(i);
                        }
                    }
                    if (Random.Int(4) == 0) {
                        if (!gazer.isEmpty()) {
                            Item hypnotize = Random.element(gazer).detach(Dungeon.hero.belongings.backpack);
                            GLog.w(Messages.get(this, "gaze"));
                            hero.sprite.emitter().start(Speck.factory(Speck.BUBBLE), 0.2f, 3);
                            Sample.INSTANCE.play(Assets.Sounds.LULLABY);
                            if (hypnotize instanceof PotionOfHealing) {
                                hypnotize.detach(Dungeon.hero.belongings.getItem(PotionBandolier.class));
                            }
                        } else {
                            GLog.w(Messages.get(this, "failtogaze"));
                        }
                    }
                }
            }
        }
        return damage;
    }
}