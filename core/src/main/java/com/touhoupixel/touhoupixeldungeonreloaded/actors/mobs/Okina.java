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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Homunculus;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfHexCancel;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.OkinaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Okina extends Mob {

    {
        spriteClass = OkinaSprite.class;

        HP = HT = 127;
        defenseSkill = 30;
        EXP = 15;
        maxLvl = 37;

        properties.add(Property.GOD);

        loot = new PotionOfHexCancel();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(17, 22);
    }

    @Override
    public int attackSkill(Char target) {
        return 35;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR){
            Homunculus homunculus = Dungeon.heroine.belongings.getItem(Homunculus.class);
            if (homunculus != null) {
                homunculus.detach(Dungeon.heroine.belongings.backpack);
                Sample.INSTANCE.play(Assets.Sounds.BEACON);
                GLog.w(Messages.get(Homunculus.class, "block_instakill"));
                Item.updateQuickslot();
            } else {
                damage = hero.HP + 1;
                GLog.w(Messages.get(this, "door"));
            }
            if (Statistics.difficulty > 2) {
                Buff.detach(enemy, Light.class);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION);
            }
        }
        return damage;
    }
}