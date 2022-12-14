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

package com.touhoupixel.touhoupixeldungeonreloaded.windows;

import com.touhoupixel.touhoupixeldungeonreloaded.Chrome;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.RenderedTextBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Window;
import com.watabou.input.PointerEvent;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.PointerArea;
import com.watabou.utils.SparseArray;

public class WndStory extends Window {

	private static final int WIDTH_P = 125;
	private static final int WIDTH_L = 160;
	private static final int MARGIN = 2;

	private static final float bgR	= 0.77f;
	private static final float bgG	= 0.73f;
	private static final float bgB	= 0.62f;

	public static final int ID_1		= 0;
	public static final int ID_2		= 1;
	public static final int ID_3		= 2;
	public static final int ID_4     	= 3;
	public static final int ID_5		= 4;
	public static final int ID_6		= 5;
	public static final int ID_7		= 6;
	public static final int ID_8		= 7;
	public static final int ID_9     	= 8;
	public static final int ID_10		= 9;
	public static final int ID_11		= 10;
	public static final int ID_12		= 11;
	public static final int ID_13		= 12;
	public static final int ID_14     	= 13;
	public static final int ID_15		= 14;
	public static final int ID_16		= 15;
	public static final int ID_17		= 16;
	public static final int ID_18		= 17;
	public static final int ID_19     	= 18;

	private static final SparseArray<String> CHAPTERS = new SparseArray<>();

	static {
		CHAPTERS.put( ID_1, "id_1" );
		CHAPTERS.put( ID_2, "id_2" );
		CHAPTERS.put( ID_3, "id_3" );
		CHAPTERS.put( ID_4, "id_4" );
		CHAPTERS.put( ID_5, "id_5" );
		CHAPTERS.put( ID_6, "id_6" );
		CHAPTERS.put( ID_7, "id_7" );
		CHAPTERS.put( ID_8, "id_8" );
		CHAPTERS.put( ID_9, "id_9" );
		CHAPTERS.put( ID_10, "id_10" );
		CHAPTERS.put( ID_11, "id_11" );
		CHAPTERS.put( ID_12, "id_12" );
		CHAPTERS.put( ID_13, "id_13" );
		CHAPTERS.put( ID_14, "id_14" );
		CHAPTERS.put( ID_15, "id_15" );
		CHAPTERS.put( ID_16, "id_16" );
		CHAPTERS.put( ID_17, "id_17" );
		CHAPTERS.put( ID_18, "id_18" );
		CHAPTERS.put( ID_19, "id_19" );
	}

	private IconTitle ttl;
	private RenderedTextBlock tf;

	private float delay;

	public WndStory( String text ) {
		this( null, null, text );
	}

	public WndStory(Image icon, String title, String text ) {
		super( 0, 0, Chrome.get( Chrome.Type.SCROLL ) );

		int width = PixelScene.landscape() ? WIDTH_L - MARGIN * 2: WIDTH_P - MARGIN *2;

		float y = MARGIN;
		if (icon != null && title != null){
			ttl = new IconTitle(icon, title);
			ttl.setRect(MARGIN, y, width-2*MARGIN, 0);
			y = ttl.bottom()+MARGIN;
			add(ttl);
			ttl.tfLabel.invert();
		}

		tf = PixelScene.renderTextBlock( text, 6 );
		tf.maxWidth(width);
		tf.invert();
		tf.setPos(MARGIN, y);
		add( tf );

		PointerArea blocker = new PointerArea( 0, 0, PixelScene.uiCamera.width, PixelScene.uiCamera.height ) {
			@Override
			protected void onClick( PointerEvent event ) {
				onBackPressed();
			}
		};
		blocker.camera = PixelScene.uiCamera;
		add(blocker);

		resize( (int)(tf.width() + MARGIN * 2), (int)Math.min( tf.bottom()+MARGIN, 180 ) );
	}

	@Override
	public void update() {
		super.update();

		if (delay > 0 && (delay -= Game.elapsed) <= 0) {
			shadow.visible = chrome.visible = tf.visible = true;
			if (ttl != null) ttl.visible = true;
		}
	}

	public static void showChapter( int id ) {

		if (Dungeon.chapters.contains( id )) {
			return;
		}

		String text = Messages.get(WndStory.class, CHAPTERS.get( id ));
		if (text != null) {
			WndStory wnd = new WndStory( text );
			if ((wnd.delay = 0.6f) > 0) {
				wnd.shadow.visible = wnd.chrome.visible = wnd.tf.visible = false;
				if (wnd.ttl != null) wnd.ttl.visible = false;
			}

			Game.scene().add( wnd );

			Dungeon.chapters.add( id );
		}
	}
}