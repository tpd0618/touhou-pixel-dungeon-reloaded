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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeineSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndKeine7;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;

public class TutorialKeine7 extends NPC {

    {
        spriteClass = KeineSprite.class;

        properties.add(Property.IMMOVABLE);
    }

    @Override
    public int defenseSkill(Char enemy) {
        return INFINITE_EVASION;
    }

    @Override
    public void damage(int dmg, Object src) {
    }

    @Override
    public void add(Buff buff) {
    }

    @Override
    public boolean reset() {
        return true;
    }

    @Override
    public boolean interact(Char c) {
        sprite.turnTo(pos, Dungeon.hero.pos);

        if (c != Dungeon.hero) {
            return true;
        }

        tell( Messages.get(this, "tutorial") );

        return true;
    }

    private void tell( String text ) {
        Game.runOnRenderThread(new Callback() {
            @Override
            public void call() {
                GameScene.show( new WndKeine7( TutorialKeine7.this, text ) );
            }
        });
    }
}