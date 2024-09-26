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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Backdoor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.DivinePower;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Helplessness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Invasion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.OkinaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.talismans.BackdoorTalisman;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfDoor;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.OkinaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Okina extends MobWithSpellcard {

    {
        spriteClass = OkinaSprite.class;

        HP = HT = 500;
        defenseSkill = 30;
        EXP = 15;
        maxLvl = 37;

        spellcardsDefaultList.add(Invasion.class);
        spellcardsDefaultList.add(Helplessness.class);
        spellcardsDefaultList.add(DivinePower.class);

        randomOrderOfSpellcards = false;
        flying = true;

        properties.add(Property.GOD);
        properties.add(Property.MINIBOSS);
        properties.add(Property.NOT_EXTERMINABLE);

        if (Statistics.difficulty <= 2) numberOfCards = 3;
        else if (Statistics.difficulty <= 4) numberOfCards = 4;
        else numberOfCards = 5;

        mobRarity = RARE_RARITY;

        loot = new PotionOfDoor();
        lootChance = 0.1f;
    }
    @Override
    public void die(Object cause) {
        for (Char c : chars()){
            if (c.buff(Backdoor.class) != null) c.buff(Backdoor.class).detach();
        }
        Dungeon.level.drop(new Life(), pos ).sprite.drop();
        Dungeon.level.drop(new com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard(), pos ).sprite.drop();
        Dungeon.level.drop(new OkinaArmor(), pos).sprite.drop();
        Dungeon.level.drop(new BackdoorTalisman().quantity(8), pos ).sprite.drop();
        Dungeon.level.drop(new ScrollOfTeleportation().quantity(2), pos ).sprite.drop();
        super.die(cause);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(95, 145);
    }

    @Override
    public int attackSkill(Char target) {
        return 35;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(53, 77);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR){
            damage *= 3;
            GLog.w(Messages.get(this, "door"));
        }
        return damage;
    }

    @Override
    protected boolean act() {
        if (enemy != null){
            Buff.affect(enemy, Backdoor.class);
        }
        if (!enemySeen && isSpellcardOn) baseSpeed = 4f;
        else baseSpeed = 1f;

        return super.act();
    }
}