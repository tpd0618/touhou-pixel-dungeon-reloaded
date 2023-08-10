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

package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndBag;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

public abstract class InventoryScroll extends Scroll {

	protected static boolean identifiedByUse = false;

	@Override
	public void doRead() {
		
		if (!isKnown()) {
			identify();
			identifiedByUse = true;
		} else {
			identifiedByUse = false;
		}
		
		GameScene.selectItem( itemSelector );
	}
	
	private void confirmCancelation() {
		GameScene.show( new WndOptions(new ItemSprite(this),
				//Messages.titleCase(name()),
				Messages.get(this, "unknown_scroll"),
				Messages.get(this, "warning"),
				Messages.get(this, "yes"),
				Messages.get(this, "no") ) {
			@Override
			protected void onSelect( int index ) {
				switch (index) {
				case 0:
					curUser.spendAndNext( TIME_TO_READ );
					identifiedByUse = false;
					if (curItem instanceof ScrollOfIdentify){
						Statistics.identify_use = true;
						updateQuickslot();
					}
					if (curItem instanceof ScrollOfFate){
						Statistics.fate_use = true;
						updateQuickslot();
					}
					if (curItem instanceof ScrollOfEarth){
						Statistics.earth_use = true;
						updateQuickslot();
					}
					if (curItem instanceof ScrollOfExorcism){
						Statistics.exorcism_use = true;
						updateQuickslot();
					}
					if (curItem instanceof ScrollOfTransmutation){
						Statistics.transmute_use = true;
						updateQuickslot();
					}
					break;
				case 1:
					GameScene.selectItem( itemSelector );
					break;
				}
			}
			public void onBackPressed() {}
		} );
	}

	private String inventoryTitle(){
		return Messages.get(this, "inv_title");
	}

	protected Class<?extends Bag> preferredBag = null;

	protected boolean usableOnItem( Item item ){
		return true;
	}
	
	protected abstract void onItemSelected( Item item );
	
	protected WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return inventoryTitle();
		}

		@Override
		public Class<? extends Bag> preferredBag() {
			return preferredBag;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return usableOnItem(item);
		}

		@Override
		public void onSelect( Item item ) {
			
			//FIXME this safety check shouldn't be necessary
			//it would be better to eliminate the curItem static variable.
			if (!(curItem instanceof InventoryScroll)){
				return;
			}
			
			if (item != null) {
				
				((InventoryScroll)curItem).onItemSelected( item );
				((InventoryScroll)curItem).readAnimation();
				
				Sample.INSTANCE.play( Assets.Sounds.READ );
				
			} else if (identifiedByUse && !((Scroll)curItem).anonymous) {
				
				((InventoryScroll)curItem).confirmCancelation();
				
			} else if (!((Scroll)curItem).anonymous) {
				
				curItem.collect( curUser.belongings.backpack );
				
			}
		}
	};
}
