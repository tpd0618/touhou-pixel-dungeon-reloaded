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

package com.touhoupixel.touhoupixeldungeonreloaded.levels;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Ripple;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Torch;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.Painter;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.painters.SewerPainter;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AlarmTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AntiRingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AntiSneakTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BalanceTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BlazingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BurningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ChillingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CorrosionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CursedBlowTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DegradeTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DespairTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DisintegrationTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.EnchantEraseTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExConfusionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlockTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FrostTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GrippingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.PoisonDartTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.RustTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ShockingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SlowTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.TeleportationTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ToxicTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.WarpingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class SanzuRiverLevel extends RegularLevel {

	{
		viewDistance = 8;

		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	public void playLevelMusic(){
		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.FLOOR_10, Assets.Music.FLOOR_10, Assets.Music.FLOOR_10},
				new float[]{1, 1, 0.5f},
				false);
	}
	
	@Override
	protected int standardRooms(boolean forceMax) {
		if (forceMax) return 6;
		//4 to 6, average 5
		return 4+Random.chances(new float[]{1, 3, 1});
	}
	
	@Override
	protected int specialRooms(boolean forceMax) {
		if (forceMax) return 2;
		//1 to 2, average 1.8
		return 1+Random.chances(new float[]{1, 4});
	}
	
	@Override
	protected Painter painter() {
		return new SewerPainter()
				.setWater(feeling == Feeling.WATER ? 0.85f : 0.30f, 5)
				.setGrass(feeling == Feeling.GRASS ? 0.80f : 0.20f, 4)
				.setTraps(nTraps(), trapClasses(), trapChances());
	}

	@Override
	public void create() {
		itemsToSpawn.add( new Torch() );
		super.create();
	}
	
	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_10;
	}
	
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_10;
	}

	@Override
	protected Class<?>[] trapClasses() {
		return Dungeon.isChallenged(Challenges.PITFALL_OF_LIFE) ?
				new Class<?>[]{
						BlazingTrap.class, FrostTrap.class, ShockingTrap.class, WarpingTrap.class, GrippingTrap.class,
						CursedBlowTrap.class, CorrosionTrap.class, ExConfusionTrap.class, CursingTrap.class, RustTrap.class,
						DespairTrap.class, EnchantEraseTrap.class, AntiRingTrap.class}:
				new Class<?>[]{
						BurningTrap.class, ChillingTrap.class, FlockTrap.class, TeleportationTrap.class, SlowTrap.class,
						BalanceTrap.class, ToxicTrap.class, DegradeTrap.class, AntiSneakTrap.class, SummoningTrap.class,
						PoisonDartTrap.class, DisintegrationTrap.class, AlarmTrap.class};
	}

	@Override
	protected float[] trapChances() {
		return new float[]{
				3, 3, 3, 3, 3,
				2, 2, 2, 2, 2,
				2, 2, 2};
	}
	
	@Override
	public Group addVisuals() {
		super.addVisuals();
		addSewerVisuals(this, visuals);
		return visuals;
	}
	
	public static void addSewerVisuals( Level level, Group group ) {
		for (int i=0; i < level.length(); i++) {
			if (level.map[i] == Terrain.WALL_DECO) {
				group.add( new Sink( i ) );
			}
		}
	}
	
	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(SanzuRiverLevel.class, "water_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.EMPTY_DECO:
				return Messages.get(SanzuRiverLevel.class, "empty_deco_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(SanzuRiverLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}
	
	private static class Sink extends Emitter {
		
		private int pos;
		private float rippleDelay = 0;
		
		private static final Factory factory = new Factory() {
			
			@Override
			public void emit( Emitter emitter, int index, float x, float y ) {
				WaterParticle p = (WaterParticle)emitter.recycle( WaterParticle.class );
				p.reset( x, y );
			}
		};
		
		public Sink( int pos ) {
			super();
			
			this.pos = pos;
			
			PointF p = DungeonTilemap.tileCenterToWorld( pos );
			pos( p.x - 2, p.y + 3, 4, 0 );
			
			pour( factory, 0.1f );
		}
		
		@Override
		public void update() {
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				
				super.update();
				
				if (!isFrozen() && (rippleDelay -= Game.elapsed) <= 0) {
					Ripple ripple = GameScene.ripple( pos + Dungeon.level.width() );
					if (ripple != null) {
						ripple.y -= DungeonTilemap.SIZE / 2;
						rippleDelay = Random.Float(0.4f, 0.6f);
					}
				}
			}
		}
	}
	
	public static final class WaterParticle extends PixelParticle {
		
		public WaterParticle() {
			super();
			
			acc.y = 50;
			am = 0.5f;
			
			color( ColorMath.random( 0xb6ccc2, 0x3b6653 ) );
			size( 2 );
		}
		
		public void reset( float x, float y ) {
			revive();
			
			this.x = x;
			this.y = y;
			
			speed.set( Random.Float( -2, +2 ), 0 );
			
			left = lifespan = 0.4f;
		}
	}
}
