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

package com.touhoupixel.touhoupixeldungeongaiden.ui;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.SPDAction;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndKeyBindings;
import com.watabou.input.GameAction;
import com.watabou.noosa.Image;

public class ResumeIndicator extends Tag {

	private Image icon;

	public ResumeIndicator() {
		super(0xCDD5C0);

		setSize( SIZE, SIZE );

		visible = false;

	}
	
	@Override
	public GameAction keyAction() {
		return SPDAction.TAG_RESUME;
	}

	@Override
	protected void createChildren() {
		super.createChildren();

		icon = Icons.get( Icons.ARROW);
		add( icon );
	}

	@Override
	protected void layout() {
		super.layout();

		if (!flipped)   icon.x = x + (SIZE - icon.width()) / 2f + 1;
		else            icon.x = x + width - (SIZE + icon.width()) / 2f - 1;
		icon.y = y + (height - icon.height) / 2f;
		PixelScene.align(icon);
	}

	@Override
	protected void onClick() {
		if (Dungeon.heroine.ready) {
			Dungeon.heroine.resume();
		}
	}

	@Override
	protected String hoverText() {
		return Messages.titleCase(Messages.get(WndKeyBindings.class, "tag_resume"));
	}

	@Override
	public void update() {
		if (!Dungeon.heroine.isAlive())
			visible = false;
		else if (visible != (Dungeon.heroine.lastAction != null)){
			visible = Dungeon.heroine.lastAction != null;
			if (visible)
				flash();
		}
		super.update();
	}
}
