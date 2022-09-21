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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossAya;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossCirno;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossKosuzu;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossMarisa;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossRemilia;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Hatate;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.RockfallTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.Trap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class YokaiMountainBossLevel extends Level {

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	public void playLevelMusic() {
		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.FLOOR_7, Assets.Music.FLOOR_7, Assets.Music.FLOOR_7},
				new float[]{1, 1, 0.5f},
				false);
	}

	private static int WIDTH = 23;
	private static int HEIGHT = 22;

	private static boolean isCompleted = false;

	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_7;
	}

	@Override
	public String waterTex() {
		return Assets.Environment.WATER_7;
	}

	@Override
	protected boolean build() {
		setSize(WIDTH, HEIGHT);

		transitions.add(new LevelTransition(this, 448, LevelTransition.Type.REGULAR_ENTRANCE));
		transitions.add(new LevelTransition(this, 34, LevelTransition.Type.REGULAR_EXIT));

		buildLevel();

		return true;
	}

	private static final short n = -1;
	private static final short W = Terrain.WALL;
	private static final short e = Terrain.EMPTY;
	private static final short E = Terrain.ENTRANCE;
	private static final short s = Terrain.STATUE;
	private static final short L = Terrain.LOCKED_EXIT;

	private static short[] level = {
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, L, W, W, W, W, W, W, W, W, W, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, s, s, s, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, s, s, s, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, s, s, s, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W,
			W, W, e, e, e, e, e, e, e, e, e, E, e, e, e, e, e, e, e, e, e, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
			W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
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

		BossAya boss = new BossAya();
		boss.state = boss.WANDERING;
		boss.pos = 287;
		GameScene.add( boss );
		boss.beckon(Dungeon.hero.pos);

		Hatate hatate = new Hatate();
		hatate.state = hatate.WANDERING;
		hatate.pos = 288;
		GameScene.add( hatate );
		hatate.beckon(Dungeon.hero.pos);

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

	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HakureiShrineLevel.class, "water_name");
			case Terrain.WALL_DECO:
				return Messages.get(HakureiShrineLevel.class, "wall_deco_name");
			case Terrain.STATUE:
				return Messages.get(HakureiShrineLevel.class, "statue_name");
			case Terrain.LOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "locked_exit_name");
			case Terrain.UNLOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "unlocked_exit_name");
			default:
				return super.tileName( tile );
		}
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

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.ENTRANCE:
				return Messages.get(HakureiShrineLevel.class, "entrance_desc");
			case Terrain.EXIT:
				return Messages.get(HakureiShrineLevel.class, "exit_desc");
			case Terrain.EMPTY_DECO:
				return Messages.get(HakureiShrineLevel.class, "empty_deco_desc");
			case Terrain.WALL_DECO:
				return Messages.get(HakureiShrineLevel.class, "wall_deco_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(HakureiShrineLevel.class, "bookshelf_desc");
			case Terrain.STATUE:
				return Messages.get(HakureiShrineLevel.class, "statue_desc");
			case Terrain.LOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "locked_exit_desc");
			case Terrain.UNLOCKED_EXIT:
				return Messages.get(HakureiShrineLevel.class, "unlocked_exit_desc");
			default:
				return super.tileDesc( tile );
		}
	}
}