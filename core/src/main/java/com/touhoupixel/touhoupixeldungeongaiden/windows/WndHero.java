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

package com.touhoupixel.touhoupixeldungeongaiden.windows;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BuffIcon;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BuffIndicator;
import com.touhoupixel.touhoupixeldungeongaiden.ui.Icons;
import com.touhoupixel.touhoupixeldungeongaiden.ui.RenderedTextBlock;
import com.touhoupixel.touhoupixeldungeongaiden.ui.ScrollPane;
import com.touhoupixel.touhoupixeldungeongaiden.ui.Window;
import com.touhoupixel.touhoupixeldungeongaiden.utils.DungeonSeed;
import com.watabou.noosa.Gizmo;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class WndHero extends WndTabbed {

	private static final int WIDTH		= 120;
	private static final int HEIGHT		= 88;

	private StatsTab stats;
	private BuffsTab buffs;

	public static int lastIdx = 0;

	public WndHero() {

		super();

		resize( WIDTH, HEIGHT );

		stats = new StatsTab();
		add( stats );

		buffs = new BuffsTab();
		add( buffs );
		buffs.setRect(0, 0, WIDTH, HEIGHT);
		buffs.setupList();

		add( new IconTab( Icons.get(Icons.RANKINGS) ) {
			protected void select( boolean value ) {
				super.select( value );
				if (selected) {
					lastIdx = 0;
					if (!stats.visible) {
						stats.initialize();
					}
				}
				stats.visible = stats.active = selected;
			}
		} );
		add( new IconTab( Icons.get(Icons.BUFFS) ) {
			protected void select( boolean value ) {
				super.select( value );
				if (selected) lastIdx = 1;
				buffs.visible = buffs.active = selected;
			}
		} );

		layoutTabs();

		select( lastIdx );
	}

	@Override
	public void offset(int xOffset, int yOffset) {
		super.offset(xOffset, yOffset);
		buffs.layout();
	}

	private class StatsTab extends Group {

		private static final int GAP = 6;

		private float pos;

		public StatsTab() {
			initialize();
		}

		public void initialize(){

			for (Gizmo g : members){
				if (g != null) g.destroy();
			}
			clear();

			Hero heroine = Dungeon.heroine;

			IconTitle title = new IconTitle();
			title.color(Window.TITLE_COLOR);
			title.setRect(0, 0, 0, 0);
			add(title);

			pos = 1;

			int strBonus = heroine.STR() - heroine.STR;
			if (strBonus > 0)           statSlot( Messages.get(this, "str"), heroine.STR + " + " + strBonus );
			else if (strBonus < 0)      statSlot( Messages.get(this, "str"), heroine.STR + " - " + -strBonus );
			else                        statSlot( Messages.get(this, "str"), heroine.STR() );
			if (heroine.shielding() > 0)   statSlot( Messages.get(this, "health"), heroine.HP + "+" + heroine.shielding() + "/" + heroine.HT );
			else                        statSlot( Messages.get(this, "health"), (heroine.HP) + "/" + heroine.HT );
			if (Dungeon.daily){
				statSlot( Messages.get(this, "daily_for"), "_" + Dungeon.customSeedText + "_" );
			} else if (!Dungeon.customSeedText.isEmpty()){
				statSlot( Messages.get(this, "custom_seed"), "_" + Dungeon.customSeedText + "_" );
			} else {
				statSlot( Messages.get(this, "dungeon_seed"), DungeonSeed.convertToCode(Dungeon.seed) );
			}
			statSlot( Messages.get(this, "exp"), heroine.exp + "/" + heroine.maxExp() );

			pos += GAP;

			statSlot( Messages.get(this, "power"), Statistics.power );
			statSlot( Messages.get(this, "value"), Statistics.value );
			statSlot( Messages.get(this, "nextvalue"), Statistics.nextvalue );
		}

		private void statSlot( String label, String value ) {

			RenderedTextBlock txt = PixelScene.renderTextBlock( label, 8 );
			txt.setPos(0, pos);
			add( txt );

			txt = PixelScene.renderTextBlock( value, 8 );
			txt.setPos(WIDTH * 0.55f, pos);
			PixelScene.align(txt);
			add( txt );

			pos += GAP + txt.height();
		}

		private void statSlot( String label, int value ) {
			statSlot( label, Integer.toString( value ) );
		}

		public float height() {
			return pos;
		}
	}

	private class BuffsTab extends Component {

		private static final int GAP = 2;

		private float pos;
		private ScrollPane buffList;
		private ArrayList<BuffSlot> slots = new ArrayList<>();

		@Override
		protected void createChildren() {

			super.createChildren();

			buffList = new ScrollPane( new Component() ){
				@Override
				public void onClick( float x, float y ) {
					int size = slots.size();
					for (int i=0; i < size; i++) {
						if (slots.get( i ).onClick( x, y )) {
							break;
						}
					}
				}
			};
			add(buffList);
		}

		@Override
		protected void layout() {
			super.layout();
			buffList.setRect(0, 0, width, height);
		}

		private void setupList() {
			Component content = buffList.content();
			for (Buff buff : Dungeon.heroine.buffs()) {
				if (buff.icon() != BuffIndicator.NONE) {
					BuffSlot slot = new BuffSlot(buff);
					slot.setRect(0, pos, WIDTH, slot.icon.height());
					content.add(slot);
					slots.add(slot);
					pos += GAP + slot.height();
				}
			}
			content.setSize(buffList.width(), pos);
			buffList.setSize(buffList.width(), buffList.height());
		}

		private class BuffSlot extends Component {

			private Buff buff;

			Image icon;
			RenderedTextBlock txt;

			public BuffSlot( Buff buff ){
				super();
				this.buff = buff;

				icon = new BuffIcon(buff, true);
				icon.y = this.y;
				add( icon );

				txt = PixelScene.renderTextBlock( buff.toString(), 8 );
				txt.setPos(
						icon.width + GAP,
						this.y + (icon.height - txt.height()) / 2
				);
				PixelScene.align(txt);
				add( txt );

			}

			@Override
			protected void layout() {
				super.layout();
				icon.y = this.y;
				txt.maxWidth((int)(width - icon.width()));
				txt.setPos(
						icon.width + GAP,
						this.y + (icon.height - txt.height()) / 2
				);
				PixelScene.align(txt);
			}

			protected boolean onClick ( float x, float y ) {
				if (inside( x, y )) {
					GameScene.show(new WndInfoBuff(buff));
					return true;
				} else {
					return false;
				}
			}
		}
	}
}