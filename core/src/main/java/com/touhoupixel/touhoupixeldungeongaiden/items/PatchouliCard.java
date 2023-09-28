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

package com.touhoupixel.touhoupixeldungeongaiden.items;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Belongings;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Enchanting;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.PurpleParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndBag;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class PatchouliCard extends Item {
	
	private static final float TIME_TO_INSCRIBE = 2;
	
	private static final String AC_INSCRIBE = "INSCRIBE";
	
	{
		image = ItemSpriteSheet.PATCHOULI_CARD;
		
		stackable = true;

		defaultAction = AC_INSCRIBE;

		bones = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add( AC_INSCRIBE );
		return actions;
	}
	
	@Override
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action.equals(AC_INSCRIBE)) {

			curUser = heroine;
			GameScene.selectItem( itemSelector );
			
		}
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	private void inscribe( Armor armor ) {

		if (!armor.isIdentified() ){
			GLog.w( Messages.get(this, "identify"));
			return;
		} else if (armor.cursed || armor.hasCurseGlyph()){
			GLog.w( Messages.get(this, "cursed"));
			return;
		}
		
		detach(curUser.belongings.backpack);

		GLog.w( Messages.get(this, "inscribed"));

		armor.inscribe();
		
		curUser.sprite.operate(curUser.pos);
		curUser.sprite.centerEmitter().start(PurpleParticle.BURST, 0.05f, 10);
		Enchanting.show(curUser, armor);
		Sample.INSTANCE.play(Assets.Sounds.BURNING);
		
		curUser.spend(TIME_TO_INSCRIBE);
		curUser.busy();
	}
	
	@Override
	public int value() {
		return 30 * quantity;
	}

	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(PatchouliCard.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return Belongings.Backpack.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Armor;
		}

		@Override
		public void onSelect( Item item ) {
			if (item != null) {
				PatchouliCard.this.inscribe( (Armor)item );
			}
		}
	};
}
