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

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.ShatteredPixelDungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Icons;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.RenderedTextBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Window;
import com.watabou.noosa.Group;

import java.text.NumberFormat;
import java.util.Locale;

public class WndScoreBreakdown extends Window {

	private static final int WIDTH			= 115;

	private int GAP	= 4;

	public WndScoreBreakdown(){

		IconTitle title = new IconTitle( Icons.get(Icons.INFO), Messages.get(this, "title"));
		title.setRect(0, 0, WIDTH, 16);
		add(title);

		float pos = title.bottom()+2;

		NumberFormat num = NumberFormat.getInstance(Locale.US);
			pos = statSlot(this, Messages.get(this, "progress_title"),
					num.format(Statistics.progressScore), pos, false);
			pos = addInfo(this, Messages.get(this, "progress_desc"), pos);
			pos = statSlot(this, Messages.get(this, "treasure_title"),
					num.format(Statistics.treasureScore), pos, false);
			pos = addInfo(this, Messages.get(this, "treasure_desc"), pos);
			pos = statSlot(this, Messages.get(this, "explore_title"),
					num.format(Statistics.exploreScore), pos, false);
			pos = addInfo(this, Messages.get(this, "explore_desc"), pos);

		if (Statistics.chalMultiplier > 1) {
			pos = statSlot(this, Messages.get(this, "challenge_multiplier"), Statistics.chalMultiplier + "x", pos, false);
		}
		pos = statSlot(this, Messages.get(this, "total"), num.format(Statistics.totalScore), pos, false);

		resize(WIDTH, (int)pos);

	}

	private float statSlot(Group parent, String label, String value, float pos, boolean highlight ) {

		RenderedTextBlock txt = PixelScene.renderTextBlock( label, 7 );
		if (highlight) txt.hardlight(Window.TITLE_COLOR);
		txt.setPos(0, pos);
		parent.add( txt );

		txt = PixelScene.renderTextBlock( value, 7 );
		if (highlight) txt.hardlight(Window.TITLE_COLOR);
		txt.setPos(WIDTH * 0.7f, pos);
		PixelScene.align(txt);
		parent.add( txt );

		return pos + GAP + txt.height();
	}

	private float addInfo(Group parent, String info, float pos){

		RenderedTextBlock txt = PixelScene.renderTextBlock( info, 5 );
		txt.maxWidth(WIDTH);
		txt.hardlight(0x999999);
		txt.setPos(0, pos-2);
		parent.add( txt );

		return pos - 2 + GAP + txt.height();

	}


}
