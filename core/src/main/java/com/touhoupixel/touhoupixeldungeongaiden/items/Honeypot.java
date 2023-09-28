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
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Medicine;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Pushing;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Splash;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Honeypot extends Item {

	public static final String AC_SHATTER	= "SHATTER";

	{
		image = ItemSpriteSheet.HONEYPOT;

		defaultAction = AC_THROW;
		usesTargeting = true;

		stackable = true;
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.add( AC_SHATTER );
		return actions;
	}

	@Override
	public void execute(final Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action.equals( AC_SHATTER )) {

			heroine.sprite.zap( heroine.pos );

			detach( heroine.belongings.backpack );

			Item item = shatter(heroine, heroine.pos );
			if (!item.collect()){
				Dungeon.level.drop(item, heroine.pos);
				if (item instanceof ShatteredPot){
					((ShatteredPot) item).dropPot(heroine, heroine.pos);
				}
			}

			heroine.next();

		}
	}

	@Override
	protected void onThrow( int cell ) {
		if (Dungeon.level.pit[cell]) {
			super.onThrow( cell );
		} else {
			Dungeon.level.drop(shatter( null, cell ), cell);
		}
	}

	public Item shatter( Char owner, int pos ) {

		if (Dungeon.level.heroFOV[pos]) {
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );
			Splash.at( pos, 0xffd500, 5 );
		}

		int newPos = pos;
		if (Actor.findChar( pos ) != null) {
			ArrayList<Integer> candidates = new ArrayList<>();

			for (int n : PathFinder.NEIGHBOURS4) {
				int c = pos + n;
				if (!Dungeon.level.solid[c] && Actor.findChar( c ) == null) {
					candidates.add( c );
				}
			}

			newPos = candidates.size() > 0 ? Random.element( candidates ) : -1;
		}

		if (newPos != -1) {
			Medicine bee = new Medicine();
			bee.spawn( Dungeon.floor);
			bee.setPotInfo( pos, owner );
			bee.HP = bee.HT;
			bee.pos = newPos;

			GameScene.add( bee );
			Actor.addDelayed( new Pushing( bee, pos, newPos ), -1f );

			bee.sprite.alpha( 0 );
			bee.sprite.parent.add( new AlphaTweener( bee.sprite, 1, 0.15f ) );

			Sample.INSTANCE.play( Assets.Sounds.BEE );
			return new ShatteredPot();
		} else {
			return this;
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

	@Override
	public int value() {
		return 30 * quantity;
	}

	//The bee's broken 'home', all this item does is let its bee know where it is, and who owns it (if anyone).
	public static class ShatteredPot extends Item {

		{
			image = ItemSpriteSheet.SHATTPOT;
			stackable = true;
		}

		@Override
		public boolean doPickUp(Hero heroine, int pos) {
			if ( super.doPickUp(heroine, pos) ){
				pickupPot(heroine);
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void doDrop(Hero heroine) {
			super.doDrop(heroine);
			dropPot(heroine, heroine.pos);
		}

		@Override
		protected void onThrow(int cell) {
			super.onThrow(cell);
			dropPot(curUser, cell);
		}

		public void pickupPot(Char holder){
			for (Medicine bee : findBees(holder.pos)){
				updateBee(bee, -1, holder);
			}
		}

		public void dropPot( Char holder, int dropPos ){
			for (Medicine bee : findBees(holder)){
				updateBee(bee, dropPos, null);
			}
		}

		public void destroyPot( int potPos ){
			for (Medicine bee : findBees(potPos)){
				updateBee(bee, -1, null);
			}
		}

		private void updateBee(Medicine bee, int cell, Char holder ){
			if (bee != null && bee.alignment == Char.Alignment.ENEMY)
				bee.setPotInfo( cell, holder );
		}

		//returns up to quantity bees which match the current pot Pos
		private ArrayList<Medicine> findBees(int potPos ){
			ArrayList<Medicine> bees = new ArrayList<>();
			for (Char c : Actor.chars()){
				if (c instanceof Medicine && ((Medicine) c).potPos() == potPos){
					bees.add((Medicine) c);
					if (bees.size() >= quantity) {
						break;
					}
				}
			}

			return bees;
		}

		//returns up to quantity bees which match the current pot holder
		private ArrayList<Medicine> findBees(Char potHolder ){
			ArrayList<Medicine> bees = new ArrayList<>();
			for (Char c : Actor.chars()){
				if (c instanceof Medicine && ((Medicine) c).potHolderID() == potHolder.id()){
					bees.add((Medicine) c);
					if (bees.size() >= quantity) {
						break;
					}
				}
			}

			return bees;
		}

		@Override
		public boolean isUpgradable() {
			return false;
		}

		@Override
		public boolean isIdentified() {
			return true;
		}

		@Override
		public int value() {
			return 5 * quantity;
		}
	}
}