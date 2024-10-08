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

package com.touhoupixel.touhoupixeldungeonreloaded.scenes;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Chrome;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.GamesInProgress;
import com.touhoupixel.touhoupixeldungeonreloaded.ShatteredPixelDungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.Chasm;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special.SpecialRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.services.updates.Updates;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.GameLog;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Icons;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.RenderedTextBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.StyledButton;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Window;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndError;
import com.watabou.gltextures.TextureCache;
import com.watabou.glwrap.Blending;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.NoosaScript;
import com.watabou.noosa.NoosaScriptNoLighting;
import com.watabou.noosa.SkinnedBlock;
import com.watabou.utils.DeviceCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class InterlevelScene extends PixelScene {
	
	//slow fade on entering a new region
	private static final float SLOW_FADE = 1f; //.33 in, 1.33 steady, .33 out, 2 seconds total
	//norm fade when loading, falling, returning, or descending to a new floor
	private static final float NORM_FADE = 0.67f; //.33 in, .67 steady, .33 out, 1.33 seconds total
	//fast fade when ascending, or descending to a floor you've been on
	private static final float FAST_FADE = 0.50f; //.33 in, .33 steady, .33 out, 1 second total
	
	private static float fadeTime;
	
	public enum Mode {
		ASCEND, DESCEND, CONTINUE, RESURRECT, RETURN, FALL, RESET, NONE
	}
	public static Mode mode;

	public static LevelTransition curTransition = null;
	public static int returnFloor;
	public static int returnBranch;
	public static int returnPos;
	
	public static boolean noStory = false;

	public static boolean fallIntoPit;
	
	private enum Phase {
		FADE_IN, STATIC, FADE_OUT
	}
	private Phase phase;
	private float timeLeft;
	
	private RenderedTextBlock message;
	
	private static Thread thread;
	private static Exception error = null;
	private float waitingTime;

	public static int lastRegion = -1;

	{
		inGameScene = true;
	}
	
	@Override
	public void create() {
		super.create();
		
		String loadingAsset;
		int loadingDepth;
		final float scrollSpeed;
		fadeTime = NORM_FADE;
		switch (mode){
			default:
				loadingDepth = Dungeon.floor;
				scrollSpeed = 0;
				break;
			case CONTINUE:
				loadingDepth = GamesInProgress.check(GamesInProgress.curSlot).floor;
				scrollSpeed = 5;
				break;
			case ASCEND:
				if (Dungeon.heroine == null){
					loadingDepth = 1;
					fadeTime = SLOW_FADE;
				} else {
					if (curTransition != null)  loadingDepth = curTransition.destFloor;
					else                        loadingDepth = Dungeon.floor +1;
					if (Statistics.highestFloor >= loadingDepth) {
						fadeTime = FAST_FADE;
					}
				}
				scrollSpeed = -5;
				break;
			case FALL:
				loadingDepth = Dungeon.floor -1;
				scrollSpeed = 50;
				break;
			case DESCEND:
				fadeTime = FAST_FADE;
				if (curTransition != null)  loadingDepth = curTransition.destFloor;
				else                        loadingDepth = Dungeon.floor -1;
				scrollSpeed = 5;
				break;
			case RETURN:
				loadingDepth = returnFloor;
				scrollSpeed = returnFloor > Dungeon.floor ? 15 : -15;
				break;
		}

		//flush the texture cache whenever moving between regions, helps reduce memory load
		int region = (int)Math.ceil(loadingDepth / 5f);
		if (region != lastRegion){
			TextureCache.clear();
			lastRegion = region;
		}

		if      (lastRegion == 1)    loadingAsset = Assets.Interfaces.LOADING_1;
		else if (lastRegion == 2)    loadingAsset = Assets.Interfaces.LOADING_2;
		else if (lastRegion == 3)    loadingAsset = Assets.Interfaces.LOADING_3;
		else if (lastRegion == 4)    loadingAsset = Assets.Interfaces.LOADING_4;
		else if (lastRegion == 5)    loadingAsset = Assets.Interfaces.LOADING_5;
		else if (lastRegion == 6)    loadingAsset = Assets.Interfaces.LOADING_6;
		else if (lastRegion == 7)    loadingAsset = Assets.Interfaces.LOADING_7;
		else if (lastRegion == 8)    loadingAsset = Assets.Interfaces.LOADING_8;
		else if (lastRegion == 9)    loadingAsset = Assets.Interfaces.LOADING_9;
		else if (lastRegion == 10)   loadingAsset = Assets.Interfaces.LOADING_10;
		else                         loadingAsset = Assets.Interfaces.SHADOW;
		
		//slow down transition when displaying an install prompt
		if (Updates.isInstallable()){
			fadeTime += 0.5f; //adds 1 second total
		//speed up transition when debugging
		} else if (DeviceCompat.isDebug()){
			fadeTime = 0f;
		}
		
		SkinnedBlock bg = new SkinnedBlock(Camera.main.width, Camera.main.height, loadingAsset ){
			@Override
			protected NoosaScript script() {
				return NoosaScriptNoLighting.get();
			}
			
			@Override
			public void draw() {
				Blending.disable();
				super.draw();
				Blending.enable();
			}
			
			@Override
			public void update() {
				super.update();
				offset(0, Game.elapsed * scrollSpeed);
			}
		};
		bg.scale(4, 4);
		bg.autoAdjust = true;
		add(bg);
		
		Image im = new Image(TextureCache.createGradient(0xAA000000, 0xBB000000, 0xCC000000, 0xDD000000, 0xFF000000)){
			@Override
			public void update() {
				super.update();
				if (phase == Phase.FADE_IN)         aa = Math.max( 0, (timeLeft - (fadeTime - 0.333f)));
				else if (phase == Phase.FADE_OUT)   aa = Math.max( 0, (0.333f - timeLeft));
				else                                aa = 0;
			}
		};
		im.angle = 90;
		im.x = Camera.main.width;
		im.scale.x = Camera.main.height/5f;
		im.scale.y = Camera.main.width;
		add(im);

		String text = Messages.get(Mode.class, mode.name());
		
		message = PixelScene.renderTextBlock( text, 9 );
		message.setPos(
				(Camera.main.width - message.width()) / 2,
				(Camera.main.height - message.height()) / 2
		);
		align(message);
		add( message );

		if (Updates.isInstallable()){
			StyledButton install = new StyledButton(Chrome.Type.GREY_BUTTON_TR, Messages.get(this, "install")){
				@Override
				public void update() {
					super.update();
					float p = timeLeft / fadeTime;
					if (phase == Phase.FADE_IN)         alpha(1 - p);
					else if (phase == Phase.FADE_OUT)   alpha(p);
					else                                alpha(1);
				}

				@Override
				protected void onClick() {
					super.onClick();
					Updates.launchInstall();
				}
			};
			install.icon(Icons.get(Icons.CHANGES));
			install.textColor(Window.SHPX_COLOR);
			install.setSize(install.reqWidth()+5, 20);
			install.setPos((Camera.main.width - install.width())/2, (Camera.main.height - message.bottom())/3 + message.bottom());
			add(install);
		}
		
		phase = Phase.FADE_IN;
		timeLeft = fadeTime;
		
		if (thread == null) {
			thread = new Thread() {
				@Override
				public void run() {
					
					try {

						if (Dungeon.heroine != null){
							Dungeon.heroine.spendToWhole();
						}
						Actor.fixTime();

						switch (mode) {
							case ASCEND:
								descend();
								break;
							case DESCEND:
								ascend();
								break;
							case CONTINUE:
								restore();
								break;
							case RESURRECT:
								resurrect();
								break;
							case RETURN:
								returnTo();
								break;
							case FALL:
								fall();
								break;
							case RESET:
								reset();
								break;
						}
						
					} catch (Exception e) {
						
						error = e;
						
					}
					
					if (phase == Phase.STATIC && error == null) {
						phase = Phase.FADE_OUT;
						timeLeft = fadeTime;
					}
				}
			};
			thread.start();
		}
		waitingTime = 0f;
	}
	
	@Override
	public void update() {
		super.update();

		waitingTime += Game.elapsed;
		
		float p = timeLeft / fadeTime;
		
		switch (phase) {
		
		case FADE_IN:
			message.alpha( 1 - p );
			if ((timeLeft -= Game.elapsed) <= 0) {
				if (!thread.isAlive() && error == null) {
					phase = Phase.FADE_OUT;
					timeLeft = fadeTime;
				} else {
					phase = Phase.STATIC;
				}
			}
			break;
			
		case FADE_OUT:
			message.alpha( p );
			
			if ((timeLeft -= Game.elapsed) <= 0) {
				Game.switchScene( GameScene.class );
				thread = null;
				error = null;
			}
			break;
			
		case STATIC:
			if (error != null) {
				String errorMsg;
				if (error instanceof FileNotFoundException)     errorMsg = Messages.get(this, "file_not_found");
				else if (error instanceof IOException)          errorMsg = Messages.get(this, "io_error");
				else if (error.getMessage() != null &&
						error.getMessage().equals("old save")) errorMsg = Messages.get(this, "io_error");

				else throw new RuntimeException("fatal error occured while moving between floors. " +
							"Seed:" + Dungeon.seed + " depth:" + Dungeon.floor, error);

				add( new WndError( errorMsg ) {
					public void onBackPressed() {
						super.onBackPressed();
						Game.switchScene( StartScene.class );
					}
				} );
				thread = null;
				error = null;
			} else if (thread != null && (int)waitingTime == 10){
				waitingTime = 11f;
				String s = "";
				for (StackTraceElement t : thread.getStackTrace()){
					s += "\n";
					s += t.toString();
				}
				ShatteredPixelDungeon.reportException(
						new RuntimeException("waited more than 10 seconds on levelgen. " +
								"Seed:" + Dungeon.seed + " depth:" + Dungeon.floor + " trace:" +
								s)
				);
			}
			break;
		}
	}

	private void descend() throws IOException {

		if (Dungeon.heroine == null) {
			Mob.clearHeldAllies();
			Dungeon.init();
			GameLog.wipe();

			Level level = Dungeon.newLevel();
			Dungeon.switchLevel( level, -1 );
		} else {
			Mob.holdAllies( Dungeon.level );
			Dungeon.saveAll();

			Level level;
			Dungeon.floor = curTransition.destFloor;
			Dungeon.branch = curTransition.destBranch;
			//TODO this is brittle atm, assumes we're always going down in depth 1 at a time
			if (curTransition.destFloor > Statistics.highestFloor) {
				level = Dungeon.newLevel();
				//todo maneater
			} else {
				level = Dungeon.loadLevel( GamesInProgress.curSlot );
			}

			LevelTransition destTransition = level.getTransition(curTransition.destType);
			curTransition = null;
			Dungeon.switchLevel( level, destTransition.cell() );
		}
	}

	//TODO atm falling always just increments depth by 1, do we eventually want to roll it into the transition system?
	private void fall() throws IOException {
		
		Mob.holdAllies( Dungeon.level );
		
		Buff.affect( Dungeon.heroine, Chasm.Falling.class );
		Dungeon.saveAll();

		Level level;
		Dungeon.floor--;
		level = Dungeon.loadLevel( GamesInProgress.curSlot );
		Dungeon.switchLevel( level, level.fallCell( fallIntoPit ));
	}
	
	private void ascend() throws IOException {
		
		Mob.holdAllies( Dungeon.level );

		Dungeon.saveAll();
		Dungeon.floor = curTransition.destFloor;
		Dungeon.branch = curTransition.destBranch;
		Level level = Dungeon.loadLevel( GamesInProgress.curSlot );

		LevelTransition destTransition = level.getTransition(curTransition.destType);
		curTransition = null;
		Dungeon.switchLevel( level, destTransition.cell() );
	}
	
	private void returnTo() throws IOException {
		
		Mob.holdAllies( Dungeon.level );

		Dungeon.saveAll();
		Dungeon.floor = returnFloor;
		Dungeon.branch = returnBranch;
		Level level = Dungeon.loadLevel( GamesInProgress.curSlot );
		Dungeon.switchLevel( level, returnPos );
	}
	
	private void restore() throws IOException {
		
		Mob.clearHeldAllies();

		GameLog.wipe();

		Dungeon.loadGame( GamesInProgress.curSlot );
		if (Dungeon.floor == -1) {
			Dungeon.floor = Statistics.highestFloor;
			Dungeon.switchLevel( Dungeon.loadLevel( GamesInProgress.curSlot ), -1 );
		} else {
			Level level = Dungeon.loadLevel( GamesInProgress.curSlot );
			Dungeon.switchLevel( level, Dungeon.heroine.pos );
		}
	}
	
	private void resurrect() {
		
		Mob.holdAllies( Dungeon.level );

		Level level;
		if (Dungeon.level.locked) {
			ArrayList<Item> preservedItems = Dungeon.level.getItemsToPreserveFromSealedResurrect();

			Dungeon.heroine.resurrect();
			level = Dungeon.newLevel();
			Dungeon.heroine.pos = level.randomRespawnCell(Dungeon.heroine);

			for (Item i : preservedItems){
				level.drop(i, level.randomRespawnCell(null));
			}

		} else {
			level = Dungeon.level;
			BArray.setFalse(level.heroFOV);
			BArray.setFalse(level.visited);
			BArray.setFalse(level.mapped);
			int invPos = Dungeon.heroine.pos;
			int tries = 0;
			do {
				Dungeon.heroine.pos = level.randomRespawnCell(Dungeon.heroine);
				tries++;

			//prevents spawning on traps or plants, prefers farther locations first
			} while (level.traps.get(Dungeon.heroine.pos) != null
					|| (level.plants.get(Dungeon.heroine.pos) != null && tries < 500)
					|| level.trueDistance(invPos, Dungeon.heroine.pos) <= 30 - (tries/10));

			//directly trample grass
			if (level.map[Dungeon.heroine.pos] == Terrain.HIGH_GRASS || level.map[Dungeon.heroine.pos] == Terrain.FURROWED_GRASS){
				level.map[Dungeon.heroine.pos] = Terrain.GRASS;
			}
			Dungeon.heroine.resurrect();
		}

		Dungeon.switchLevel( level, Dungeon.heroine.pos );
	}

	private void reset() throws IOException {
		
		Mob.holdAllies( Dungeon.level );

		SpecialRoom.resetPitRoom(Dungeon.floor +1);

		Level level = Dungeon.newLevel();
		Dungeon.switchLevel( level, level.entrance() );
	}
	
	@Override
	protected void onBackPressed() {
		//Do nothing
	}
}
