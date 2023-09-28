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

package com.touhoupixel.touhoupixeldungeongaiden.plants;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.LeafParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfRegrowth;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Terrain;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public abstract class Plant implements Bundlable {
	
	public int image;
	public int pos;

	protected Class<? extends Seed> seedClass;

	public void trigger(){

		Char ch = Actor.findChar(pos);

		if (ch instanceof Hero){
			((Hero) ch).interrupt();
		}

		wither();
		activate( ch );
	}

	public abstract void activate( Char ch );

	public void wither() {
		Dungeon.level.uproot( pos );

		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
		}

		float seedChance = 0f;
		for (Char c : Actor.chars()){
			if (c instanceof WandOfRegrowth.Lotus){
				WandOfRegrowth.Lotus l = (WandOfRegrowth.Lotus) c;
				if (l.inRange(pos)){
					seedChance = Math.max(seedChance, l.seedPreservation());
				}
			}
		}

		if (Random.Float() < seedChance){
			if (seedClass != null && seedClass != Rotberry.Seed.class) {
				Dungeon.level.drop(Reflection.newInstance(seedClass), pos).sprite.drop();
			}
		}
		Char ch = Actor.findChar(pos);
	}

	private static final String POS	= "pos";

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		pos = bundle.getInt( POS );
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		bundle.put( POS, pos );
	}

	public String name(){
		return Messages.get(this, "name");
	}

	public String desc() {
		String desc = Messages.get(this, "desc");
		return desc;
	}

	public static class Seed extends Item {

		public static final String AC_PLANT	= "PLANT";

		private static final float TIME_TO_PLANT = 1f;

		{
			stackable = true;
			defaultAction = AC_THROW;
		}

		protected Class<? extends Plant> plantClass;

		@Override
		public ArrayList<String> actions( Hero heroine) {
			ArrayList<String> actions = super.actions(heroine);
			actions.add( AC_PLANT );
			return actions;
		}

		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level.map[cell] == Terrain.ALCHEMY
					|| Dungeon.level.pit[cell]
					|| Dungeon.level.traps.get(cell) != null) {
				super.onThrow( cell );
			} else {
				Dungeon.level.plant( this, cell );
			}
		}

		@Override
		public void execute(Hero heroine, String action ) {

			super.execute (heroine, action );

			if (action.equals( AC_PLANT )) {

				heroine.busy();
				((Seed)detach( heroine.belongings.backpack )).onThrow( heroine.pos );
				heroine.spend( TIME_TO_PLANT );

				heroine.sprite.operate( heroine.pos );

			}
		}

		public Plant couch( int pos, Level level ) {
			if (level != null && level.heroFOV != null && level.heroFOV[pos]) {
				Sample.INSTANCE.play(Assets.Sounds.PLANT);
			}
			Plant plant = Reflection.newInstance(plantClass);
			plant.pos = pos;
			return plant;
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
			return 10 * quantity;
		}

		@Override
		public int energyVal() {
			return 2 * quantity;
		}

		@Override
		public String desc() {
			String desc = Messages.get(plantClass, "desc");
			return desc;
		}

		@Override
		public String info() {
			return Messages.get( Seed.class, "info", desc() );
		}

		public static class PlaceHolder extends Seed {

			{
				image = ItemSpriteSheet.SEED_HOLDER;
			}

			@Override
			public boolean isSimilar(Item item) {
				return item instanceof Seed;
			}
			
			@Override
			public String info() {
				return "";
			}
		}
	}
}
