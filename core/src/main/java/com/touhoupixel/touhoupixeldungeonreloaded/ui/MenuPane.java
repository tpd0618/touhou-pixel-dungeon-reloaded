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

package com.touhoupixel.touhoupixeldungeonreloaded.ui;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.SPDAction;
import com.touhoupixel.touhoupixeldungeonreloaded.SPDSettings;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Document;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndChallenges;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndGame;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndJournal;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndKeyBindings;
import com.watabou.input.GameAction;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Component;

public class MenuPane extends Component {

	private Image bg;

	private Image floorIcon;
	private BitmapText floorText;
	private Button floorButton;

	private Image challengeIcon;
	private Image sp_icon;
	private BitmapText challengeText;
	private Button challengeButton;

	private JournalButton btnJournal;
	private MenuButton btnMenu;

	private Toolbar.PickedUpItem pickedUp;

	private BitmapText version;

	private DangerIndicator danger;

	public static final int WIDTH = 32;

	@Override
	protected void createChildren() {
		super.createChildren();

		bg = new Image(Assets.Interfaces.MENU);
		add(bg);

		floorIcon = Icons.get(Dungeon.level.feeling);
		add(floorIcon);

		floorText = new BitmapText( Integer.toString( Dungeon.floor), PixelScene.pixelFont);
		floorText.hardlight( 0xCACFC2 );
		floorText.measure();
		add(floorText);

		floorButton = new Button(){
			@Override
			protected String hoverText() {
				switch (Dungeon.level.feeling) {
					case CHASM:     return Messages.get(GameScene.class, "chasm");
					case WATER:     return Messages.get(GameScene.class, "water");
					case GRASS:     return Messages.get(GameScene.class, "grass");
					case DARK:      return Messages.get(GameScene.class, "dark");
					case LARGE:     return Messages.get(GameScene.class, "large");
					case TRAPS:     return Messages.get(GameScene.class, "traps");
					case SECRETS:   return Messages.get(GameScene.class, "secrets");
				}
				return null;
			}

			@Override
			protected void onClick() {
				super.onClick();
				//just open journal for now, maybe have it open landmarks after expanding that page?
				GameScene.show( new WndJournal() );
			}
		};
		add(floorButton);

		if (Dungeon.isChallenged(Challenges.TOWER_OF_FORTUNE)) {
			sp_icon = Icons.get(Icons.SP_CHALLENGE);
			add(sp_icon);
		}

		if (Challenges.activeChallenges() > 0){
			challengeIcon = Icons.get(Icons.CHAL_COUNT);
			add(challengeIcon);

			challengeText = new BitmapText( Integer.toString( Challenges.activeChallenges() ), PixelScene.pixelFont);
			challengeText.hardlight( 0xCACFC2 );
			challengeText.measure();
			add( challengeText );

			challengeButton = new Button(){
				@Override
				protected void onClick() {
					GameScene.show(new WndChallenges(Dungeon.challenges, false));
				}

				@Override
				protected String hoverText() {
					return Messages.get(WndChallenges.class, "title");
				}
			};
			add(challengeButton);
		}

		btnJournal = new JournalButton();
		add( btnJournal );

		btnMenu = new MenuButton();
		add( btnMenu );

		version = new BitmapText( "v" + Game.version, PixelScene.pixelFont);
		version.alpha( 0.5f );
		add(version);

		danger = new DangerIndicator();
		add( danger );

		add( pickedUp = new Toolbar.PickedUpItem());
	}

	@Override
	protected void layout() {
		super.layout();

		bg.x = x;
		bg.y = y;

		btnMenu.setPos( x + WIDTH - btnMenu.width(), y );

		btnJournal.setPos( btnMenu.left() - btnJournal.width() + 2, y );

		floorIcon.x = btnJournal.left() - 7 + (7 - floorIcon.width())/2f - 0.1f;
		floorIcon.y = y + 1;
		if (SPDSettings.interfaceSize() == 0) floorIcon.y++;
		PixelScene.align(floorIcon);

		floorText.scale.set(PixelScene.align(0.67f));
		floorText.x = floorIcon.x + (floorIcon.width() - floorText.width())/2f;
		floorText.y = floorIcon.y + floorIcon.height();
		PixelScene.align(floorText);

		floorButton.setRect(floorIcon.x, floorIcon.y, floorIcon.width(), floorIcon.height() + floorText.height());

		if (challengeIcon != null){
			challengeIcon.x = btnJournal.left() - 14 + (7 - challengeIcon.width())/2f - 0.1f;
			challengeIcon.y = y + 1;
			if (SPDSettings.interfaceSize() == 0) challengeIcon.y++;
			PixelScene.align(challengeIcon);

			challengeText.scale.set(PixelScene.align(0.67f));
			challengeText.x = challengeIcon.x + (challengeIcon.width() - challengeText.width())/2f;
			challengeText.y = challengeIcon.y + challengeIcon.height();
			PixelScene.align(challengeText);

			challengeButton.setRect(challengeIcon.x, challengeIcon.y, challengeIcon.width(), challengeIcon.height() + challengeText.height());
		}

		if (sp_icon != null){
			sp_icon.x = btnJournal.left() - 23 + (7 - sp_icon.width())/2f - 0.1f;
			sp_icon.y = y + 1;
			if (SPDSettings.interfaceSize() == 0) sp_icon.y++;
			PixelScene.align(sp_icon);
		}

		version.scale.set(PixelScene.align(0.5f));
		version.measure();
		version.x = x + WIDTH - version.width();
		version.y = y + bg.height() + (3 - version.baseLine());
		PixelScene.align(version);

		danger.setPos( x + WIDTH - danger.width(), y + bg.height + 3 );
	}

	public void pickup(Item item, int cell) {
		pickedUp.reset( item,
				cell,
				btnJournal.centerX(),
				btnJournal.centerY());
	}

	public void flashForPage( String page ){
		btnJournal.flashingPage = page;
	}

	public void updateKeys(){
		btnJournal.updateKeyDisplay();
	}

	private static class JournalButton extends Button {

		private Image bg;
		private Image journalIcon;
		private KeyDisplay keyIcon;

		private String flashingPage = null;

		public JournalButton() {
			super();

			width = bg.width + 4;
			height = bg.height + 4;
		}

		@Override
		public GameAction keyAction() {
			return SPDAction.JOURNAL;
		}

		@Override
		protected void createChildren() {
			super.createChildren();

			bg = new Image( Assets.Interfaces.MENU_BTN, 2, 2, 13, 11 );
			add( bg );

			journalIcon = new Image( Assets.Interfaces.MENU_BTN, 31, 0, 11, 7);
			add( journalIcon );

			keyIcon = new KeyDisplay();
			add(keyIcon);
			updateKeyDisplay();
		}

		@Override
		protected void layout() {
			super.layout();

			bg.x = x + 2;
			bg.y = y + 2;

			journalIcon.x = bg.x + (bg.width() - journalIcon.width())/2f;
			journalIcon.y = bg.y + (bg.height() - journalIcon.height())/2f;
			PixelScene.align(journalIcon);

			keyIcon.x = bg.x + 1;
			keyIcon.y = bg.y + 1;
			keyIcon.width = bg.width - 2;
			keyIcon.height = bg.height - 2;
			PixelScene.align(keyIcon);
		}

		private float time;

		@Override
		public void update() {
			super.update();

			if (flashingPage != null){
				journalIcon.am = (float)Math.abs(Math.cos( StatusPane.FLASH_RATE * (time += Game.elapsed) ));
				keyIcon.am = journalIcon.am;
				bg.brightness(0.5f + journalIcon.am);
				if (time >= Math.PI/StatusPane.FLASH_RATE) {
					time = 0;
				}
			}
		}

		public void updateKeyDisplay() {
			keyIcon.updateKeys();
			keyIcon.visible = keyIcon.keyCount() > 0;
			journalIcon.visible = !keyIcon.visible;
			if (keyIcon.keyCount() > 0) {
				bg.brightness(.8f - (Math.min(6, keyIcon.keyCount()) / 20f));
			} else {
				bg.resetColor();
			}
		}

		@Override
		protected void onPointerDown() {
			bg.brightness( 1.5f );
			Sample.INSTANCE.play( Assets.Sounds.CLICK );
		}

		@Override
		protected void onPointerUp() {
			if (keyIcon.keyCount() > 0) {
				bg.brightness(.8f - (Math.min(6, keyIcon.keyCount()) / 20f));
			} else {
				bg.resetColor();
			}
		}

		@Override
		protected void onClick() {
			time = 0;
			keyIcon.am = journalIcon.am = 1;
			if (flashingPage != null){
				if (Document.ADVENTURERS_GUIDE.pageNames().contains(flashingPage)){
					Document.ADVENTURERS_GUIDE.readPage(flashingPage);
				} else {
					GameScene.show( new WndJournal() );
				}
				flashingPage = null;
			} else {
				GameScene.show( new WndJournal() );
			}
		}

		@Override
		protected String hoverText() {
			return Messages.titleCase(Messages.get(WndKeyBindings.class, "journal"));
		}
	}

	private static class MenuButton extends Button {

		private Image image;

		public MenuButton() {
			super();

			width = image.width + 4;
			height = image.height + 4;
		}

		@Override
		protected void createChildren() {
			super.createChildren();

			image = new Image( Assets.Interfaces.MENU_BTN, 17, 2, 12, 11 );
			add( image );
		}

		@Override
		protected void layout() {
			super.layout();

			image.x = x + 2;
			image.y = y + 2;
		}

		@Override
		protected void onPointerDown() {
			image.brightness( 1.5f );
			Sample.INSTANCE.play( Assets.Sounds.CLICK );
		}

		@Override
		protected void onPointerUp() {
			image.resetColor();
		}

		@Override
		protected void onClick() {
			GameScene.show( new WndGame() );
		}

		@Override
		public GameAction keyAction() {
			return GameAction.BACK;
		}

		@Override
		protected String hoverText() {
			return Messages.titleCase(Messages.get(WndKeyBindings.class, "menu"));
		}
	}
}
