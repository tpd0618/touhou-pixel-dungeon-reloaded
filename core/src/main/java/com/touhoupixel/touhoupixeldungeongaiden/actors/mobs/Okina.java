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

package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeongaiden.items.Homunculus;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic.PotionOfHexCancel;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Terrain;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.OkinaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
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