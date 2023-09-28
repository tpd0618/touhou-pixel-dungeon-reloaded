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

import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.RenderedTextBlock;
import com.watabou.noosa.ui.Component;

public class WndHeroInfo extends WndTabbed {

	private HeroInfoTab heroInfo;

	private static int WIDTH = 120;
	private static int MIN_HEIGHT = 125;
	private static int MARGIN = 2;

	public WndHeroInfo( HeroClass cl ){
		int finalHeight = MIN_HEIGHT;

		heroInfo = new HeroInfoTab(cl);
		add(heroInfo);
		heroInfo.setSize(WIDTH, MIN_HEIGHT);
		finalHeight = (int)Math.max(finalHeight, heroInfo.height());

		resize(WIDTH, finalHeight);

		layoutTabs();
	}

	private static class HeroInfoTab extends Component {

		private RenderedTextBlock title;
		private RenderedTextBlock[] info;

		public HeroInfoTab(HeroClass cls){
			super();
			title = PixelScene.renderTextBlock(Messages.titleCase(cls.title()), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			String[] desc_entries = cls.desc().split("\n\n");

			info = new RenderedTextBlock[desc_entries.length];

			for (int i = 0; i < desc_entries.length; i++) {
				info[i] = PixelScene.renderTextBlock(desc_entries[i], 6);
				add(info[i]);
			}
		}

		@Override
		protected void layout() {
			super.layout();

			title.setPos((width-title.width())/2, MARGIN);

			float pos = title.bottom()+4*MARGIN;

			for (int i = 0; i < info.length; i++){
				info[i].maxWidth((int)width - 20);
				info[i].setPos(20, pos);

				pos = info[i].bottom() + 4*MARGIN;
			}

			height = Math.max(height, pos - 4*MARGIN);

		}
	}
}