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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.UpgradeCard;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class BambooSword extends MeleeWeapon {

    {
        image = ItemSpriteSheet.BAMBOO_SWORD;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1f;

        tier = 3;
    }

    @Override
    public int HumanFactor( Char owner ) {
        return 1;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if (Statistics.bamboo == 199) {
            Dungeon.heroine.STR++;
            attacker.sprite.showStatus(CharSprite.POSITIVE, Messages.get(StrengthCard.class, "msg_1"));
            GLog.p(Messages.get(StrengthCard.class, "msg_2"));
            Statistics.bamboo = 0;
        } else {
            Statistics.bamboo += 1;
        }
        return super.proc(attacker, defender, damage);
    }
}