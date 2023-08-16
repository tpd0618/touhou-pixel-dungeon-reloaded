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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Regrowth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossMarisa;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Murasa;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AnchorTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SakuyaDaggerTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import javax.swing.text.Position;

public class ScrollOfAnchor extends Scroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_ANCHOR;
	}

	@Override
	public void doRead() {
		new AnchorTrap().set(curUser.pos).activate();

		//Level.set( curUser.pos - 1, Terrain.WATER );
		//GameScene.updateMap( curUser.pos - 1 );
		//maybe used for digging?

		if (Dungeon.level.map[curUser.pos - 1] == Terrain.EMBERS || Dungeon.level.map[curUser.pos - 1] == Terrain.EMPTY || Dungeon.level.map[curUser.pos - 1] == Terrain.EMPTY_DECO || Dungeon.level.map[curUser.pos - 1] == Terrain.EMPTY_SP || Dungeon.level.map[curUser.pos - 1] == Terrain.GRASS || Dungeon.level.map[curUser.pos - 1] == Terrain.FURROWED_GRASS || Dungeon.level.map[curUser.pos - 1] == Terrain.HIGH_GRASS){
			Level.set( curUser.pos - 1, Terrain.WATER );
			GameScene.updateMap( curUser.pos - 1 );
		}
		if (Dungeon.level.map[curUser.pos + 1] == Terrain.EMBERS || Dungeon.level.map[curUser.pos + 1] == Terrain.EMPTY || Dungeon.level.map[curUser.pos + 1] == Terrain.EMPTY_DECO || Dungeon.level.map[curUser.pos + 1] == Terrain.EMPTY_SP || Dungeon.level.map[curUser.pos + 1] == Terrain.GRASS || Dungeon.level.map[curUser.pos + 1] == Terrain.FURROWED_GRASS || Dungeon.level.map[curUser.pos + 1] == Terrain.HIGH_GRASS){
			Level.set( curUser.pos + 1, Terrain.WATER );
			GameScene.updateMap( curUser.pos + 1 );
		}
		if (Dungeon.level.map[curUser.pos - Dungeon.level.width()] == Terrain.EMBERS || Dungeon.level.map[curUser.pos - Dungeon.level.width()] == Terrain.EMPTY || Dungeon.level.map[curUser.pos - Dungeon.level.width()] == Terrain.EMPTY_DECO || Dungeon.level.map[curUser.pos - Dungeon.level.width()] == Terrain.EMPTY_SP || Dungeon.level.map[curUser.pos - Dungeon.level.width()] == Terrain.GRASS || Dungeon.level.map[curUser.pos - Dungeon.level.width()] == Terrain.FURROWED_GRASS || Dungeon.level.map[curUser.pos - Dungeon.level.width()] == Terrain.HIGH_GRASS){
			Level.set( curUser.pos - Dungeon.level.width(), Terrain.WATER );
			GameScene.updateMap( curUser.pos - Dungeon.level.width() );
		}
		if (Dungeon.level.map[curUser.pos + Dungeon.level.width()] == Terrain.EMBERS || Dungeon.level.map[curUser.pos + Dungeon.level.width()] == Terrain.EMPTY || Dungeon.level.map[curUser.pos + Dungeon.level.width()] == Terrain.EMPTY_DECO || Dungeon.level.map[curUser.pos + Dungeon.level.width()] == Terrain.EMPTY_SP || Dungeon.level.map[curUser.pos + Dungeon.level.width()] == Terrain.GRASS || Dungeon.level.map[curUser.pos + Dungeon.level.width()] == Terrain.FURROWED_GRASS || Dungeon.level.map[curUser.pos + Dungeon.level.width()] == Terrain.HIGH_GRASS){
			Level.set( curUser.pos + Dungeon.level.width(), Terrain.WATER );
			GameScene.updateMap( curUser.pos + Dungeon.level.width() );
		}

		GLog.w(Messages.get(this, "anchor"));
		identify();

		curUser.sprite.centerEmitter().start(Speck.factory(Speck.DISCOVER), 0.3f, 3);
		Sample.INSTANCE.play(Assets.Sounds.ALERT);

		readAnimation();
	}

	@Override
	public int value() {
		return isKnown() ? 40 * quantity : super.value();
	}
}
