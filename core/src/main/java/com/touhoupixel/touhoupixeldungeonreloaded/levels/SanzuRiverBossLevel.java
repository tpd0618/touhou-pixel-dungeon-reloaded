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

package com.touhoupixel.touhoupixeldungeonreloaded.levels;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossKomachi;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class SanzuRiverBossLevel extends Level {

	{
		viewDistance = 8;

		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	public void playLevelMusic() {
		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.BOSS_FLOOR_5, Assets.Music.BOSS_FLOOR_5, Assets.Music.BOSS_FLOOR_5},
				new float[]{1, 1, 0.5f},
				false);
	}

	private static int WIDTH = 23;
	private static int HEIGHT = 22;

	private static boolean isCompleted = false;

	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_10;
	}

	@Override
	public String waterTex() {
		return Assets.Environment.WATER_10;
	}

	@Override
	protected boolean build() {
		setSize(WIDTH, HEIGHT);

		transitions.add(new LevelTransition(this, 448, LevelTransition.Type.REGULAR_EXIT));
		transitions.add(new LevelTransition(this, 34, LevelTransition.Type.REGULAR_ENTRANCE));

		buildLevel();

		return true;
	}

	private static final short n = -1;
	private static final short W = Terrain.WALL;
	private static final short e = Terrain.EMPTY;
	private static final short E = Terrain.ENTRANCE;
	private static final short s = Terrain.WATER;
	private static final short L = Terrain.LOCKED_EXIT;

	private static short[] level = {
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, L, W, W, W, W, W, W, W, W, W, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, W, e, W, W, W, W, W, W, W, s, W, W, W, W, W, W, W, e, W, W, W,
			W, W, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, W, W,
			W, W, e, W, W, e, W, W, W, W, W, s, W, W, W, W, W, e, W, W, e, W, W,
			W, W, e, e, W, e, W, e, e, e, e, e, e, e, e, e, W, e, W, e, e, W, W,
			W, W, W, e, W, e, W, e, W, W, W, s, W, W, W, e, W, e, W, e, W, W, W,
			W, W, e, e, W, e, W, e, W, e, e, e, e, e, W, e, W, e, W, e, e, W, W,
			W, W, e, W, W, e, W, e, W, e, W, W, W, e, W, e, W, e, W, W, e, W, W,
			W, W, e, e, s, e, s, e, s, e, W, W, W, e, s, e, s, e, s, e, e, W, W,
			W, W, W, e, W, e, W, e, W, e, W, W, W, e, W, e, W, e, W, e, W, W, W,
			W, W, e, e, W, e, W, e, W, e, e, e, e, e, W, e, W, e, W, e, e, W, W,
			W, W, e, W, W, e, W, e, W, W, W, s, W, W, W, e, W, e, W, W, e, W, W,
			W, W, e, e, W, e, W, e, e, e, e, e, e, e, e, e, W, e, W, e, e, W, W,
			W, W, W, e, W, e, W, W, W, W, W, s, W, W, W, W, W, e, W, e, W, W, W,
			W, W, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, W, W,
			W, W, e, W, W, W, W, W, W, W, W, s, W, W, W, W, W, W, W, W, e, W, W,
			W, W, e, e, W, e, e, e, W, e, e, e, e, e, W, e, e, e, W, e, e, W, W,
			W, W, W, e, e, e, W, e, e, e, W, E, W, e, e, e, W, e, e, e, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W
	};

	private void buildLevel(){
		int pos = 0 + 0*width();

		short[] levelTiles = level;
		for (int i = 0; i < levelTiles.length; i++){
			if (levelTiles[i] != n) map[pos] = levelTiles[i];

			pos++;
		}
	}

	@Override
	protected void createMobs() {
	}

	@Override
	protected void createItems() {
	}

	@Override
	public int randomRespawnCell( Char ch ) {
		int cell;
		do {
			cell = entrance() + PathFinder.NEIGHBOURS8[Random.Int(8)];
		} while (!passable[cell]
				|| Actor.findChar(cell) != null);
		return cell;
	}

	@Override
	public void seal() {
		super.seal();

		set( 448, Terrain.EMPTY );
		GameScene.updateMap( 448 );

		BossKomachi boss = new BossKomachi();
		boss.state = boss.WANDERING;
		boss.pos = 287;
		GameScene.add( boss );
		boss.beckon(Dungeon.heroine.pos);

		if (heroFOV[boss.pos]) {
			boss.notice();
			boss.sprite.alpha( 0 );
			boss.sprite.parent.add( new AlphaTweener( boss.sprite, 1, 0.1f ) );
		}
	}

	@Override
	public void unseal() {
		super.unseal();

		set( 448, Terrain.ENTRANCE );
		GameScene.updateMap( 448 );

		isCompleted = true;

		Dungeon.observe();
	}

	private static final String ISCOMPLETED = "iscompleted";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put(ISCOMPLETED, isCompleted);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		isCompleted = bundle.getBoolean( ISCOMPLETED );
	}
}