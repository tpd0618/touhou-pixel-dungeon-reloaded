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

package com.touhoupixel.touhoupixeldungeongaiden.levels;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.levels.painters.Painter;
import com.touhoupixel.touhoupixeldungeongaiden.levels.painters.TouhouPainter;
import com.touhoupixel.touhoupixeldungeongaiden.levels.rooms.Room;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.AlarmTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.InversionTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.ZeroDexterityTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.BalanceTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.BlazingTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.CorrosionTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.CursedBlowTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.DegradeTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.DisintegrationTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.EnchantEraseTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.ExConfusionTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.FlashingTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.FrostTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.GrimTrap;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.RockfallTrap;
import com.touhoupixel.touhoupixeldungeongaiden.tiles.DungeonTilemap;
import com.watabou.glwrap.Blending;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class HellLevel extends RegularLevel {

	{
		viewDistance = 8;
		
		color1 = 0x801500;
		color2 = 0xa68521;
	}

	@Override
	public void playLevelMusic() {
		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.FLOOR_19, Assets.Music.FLOOR_19, Assets.Music.FLOOR_19},
				new float[]{1, 1, 0.5f},
				false);
	}

	@Override
	protected ArrayList<Room> initRooms() {
		ArrayList<Room> rooms = super.initRooms();

		return rooms;
	}

	@Override
	protected int standardRooms(boolean forceMax) {
		if (forceMax) return 9;
		//8 to 9, average 8.33
		return 8+Random.chances(new float[]{2, 1});
	}
	
	@Override
	protected int specialRooms(boolean forceMax) {
		if (forceMax) return 3;
		//2 to 3, average 2.5
		return 2 + Random.chances(new float[]{1, 1});
	}

	@Override
	protected Painter painter() {
		return new TouhouPainter()
				.setWater(feeling == Feeling.WATER ? 0.7f : 0.15f, 5)
				.setGrass(feeling == Feeling.GRASS ? 0.7f : 0.15f, 5)
				.setTraps(nTraps(), trapClasses(), trapChances());
	}

	@Override
	public void create() {
		super.create();
	}
	
	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_19;
	}
	
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_19;
	}

	@Override
	protected Class<?>[] trapClasses() {
		return new Class[]{
				EnchantEraseTrap.class, FrostTrap.class, BlazingTrap.class, AlarmTrap.class, InversionTrap.class,
				DegradeTrap.class, ExConfusionTrap.class, CursedBlowTrap.class, CorrosionTrap.class, RockfallTrap.class,
				DisintegrationTrap.class, CursingTrap.class, BalanceTrap.class, ZeroDexterityTrap.class, FlashingTrap.class, GrimTrap.class};
	}

	@Override
	protected float[] trapChances() {
		return new float[]{
				3, 3, 3, 3, 3,
				2, 2, 2, 2, 2,
				1, 1, 1, 1, 1, 1};
	}

	@Override
	public Group addVisuals() {
		super.addVisuals();
		addHallsVisuals( this, visuals );
		return visuals;
	}
	
	public static void addHallsVisuals( Level level, Group group ) {
		for (int i=0; i < level.length(); i++) {
			if (level.map[i] == Terrain.WATER) {
				group.add( new Stream( i ) );
			}
		}
	}
	
	private static class Stream extends Group {
		
		private int pos;
		
		private float delay;
		
		public Stream( int pos ) {
			super();
			
			this.pos = pos;
			
			delay = Random.Float( 2 );
		}
		
		@Override
		public void update() {

			if (!Dungeon.level.water[pos]){
				killAndErase();
				return;
			}
			
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				
				super.update();
				
				if ((delay -= Game.elapsed) <= 0) {
					
					delay = Random.Float( 2 );
					
					PointF p = DungeonTilemap.tileToWorld( pos );
					((FireParticle)recycle( FireParticle.class )).reset(
						p.x + Random.Float( DungeonTilemap.SIZE ),
						p.y + Random.Float( DungeonTilemap.SIZE ) );
				}
			}
		}
		
		@Override
		public void draw() {
			Blending.setLightMode();
			super.draw();
			Blending.setNormalMode();
		}
	}
	
	public static class FireParticle extends PixelParticle.Shrinking {
		
		public FireParticle() {
			super();
			
			color( 0xEE7722 );
			lifespan = 1f;
			
			acc.set( 0, +80 );
		}
		
		public void reset( float x, float y ) {
			revive();
			
			this.x = x;
			this.y = y;
			
			left = lifespan;
			
			speed.set( 0, -40 );
			size = 4;
		}
		
		@Override
		public void update() {
			super.update();
			float p = left / lifespan;
			am = p > 0.8f ? (1 - p) * 5 : 1;
		}
	}
}
