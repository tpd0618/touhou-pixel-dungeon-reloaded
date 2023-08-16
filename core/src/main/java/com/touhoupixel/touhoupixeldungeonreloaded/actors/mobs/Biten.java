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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeFear;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeHunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTransmutation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.BitenSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HisamiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Biten extends Mob {

    {
        spriteClass = BitenSprite.class;

        HP = HT = 237;
        defenseSkill = 32;
        EXP = 14;
        maxLvl = 75;

        properties.add(Property.YOKAI);

        loot = new ScrollOfTransmutation();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(30, 34);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.prolong(enemy, ExtremeFear.class, ExtremeFear.DURATION);
            Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
            Dungeon.heroine.exp = 0;
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            GLog.w(Messages.get(this, "exp_reset"));
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Silence.class, Silence.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
        }
        return damage;
    }
}