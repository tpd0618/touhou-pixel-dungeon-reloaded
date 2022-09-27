/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeonreloaded.items.cards.passivecards;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.Shopkeeper;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.CardHolder;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

abstract public class PassiveCards extends Item {

    {
        stackable = true;

        unique = true;

        bones = true;

        defaultAction = AC_THROW;
        usesTargeting = true;
    }

    public boolean holster;

    public int tier;

    protected MissileWeapon parent;

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.remove(AC_DROP);
        actions.remove(AC_THROW);
        return actions;
    }

    @Override
    public boolean collect(Bag container) {
        if (container instanceof CardHolder) holster = true;
        return super.collect(container);
    }

    @Override
    public boolean doPickUp(Hero hero, int pos) {
        parent = null;

        if (Dungeon.isChallenged(Challenges.HUNDREDTH_BLACK_MARKET)) {
            Statistics.playercorruption += 4;
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            GLog.w(Messages.get(Potion.class, "corruption"));
        }

        Shopkeeper shopkeeper = new Shopkeeper();
        if (Statistics.card66 && Random.Int(4) == 0) {
            GameScene.flash(0x80FFFFFF);
        } else shopkeeper.destroy();
        return super.doPickUp(hero, pos);
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    @Override
    public int value() {
        return 100 * quantity;
    }
}