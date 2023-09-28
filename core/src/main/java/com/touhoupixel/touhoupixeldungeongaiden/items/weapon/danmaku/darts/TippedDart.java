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

package com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.darts;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.PinCushion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfRegrowth;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Blindweed;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Dreamfoil;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Fadeleaf;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Firebloom;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Icecap;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Plant;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Rotberry;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sorrowmoss;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Starflower;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Stormvine;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sungrass;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class TippedDart extends Dart {

	{
		tier = 2;

		baseUses = 1f;
	}

	private static final String AC_CLEAN = "CLEAN";

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.remove( AC_TIP );
		actions.add( AC_CLEAN );
		return actions;
	}

	@Override
	public void execute(final Hero heroine, String action) {
		super.execute(heroine, action);
		if (action.equals( AC_CLEAN )){

			GameScene.show(new WndOptions(new ItemSprite(this),
					Messages.titleCase(name()),
					Messages.get(this, "clean_desc"),
					Messages.get(this, "clean_all"),
					Messages.get(this, "clean_one"),
					Messages.get(this, "cancel")){
				@Override
				protected void onSelect(int index) {
					if (index == 0){
						detachAll(heroine.belongings.backpack);
						new Dart().quantity(quantity).collect();

						heroine.spend( 1f );
						heroine.busy();
						heroine.sprite.operate(heroine.pos);
					} else if (index == 1){
						detach(heroine.belongings.backpack);
						if (!new Dart().collect()) Dungeon.level.drop(new Dart(), heroine.pos).sprite.drop();

						//reset durability if there are darts left in the stack
						durability = MAX_DURABILITY;

						heroine.spend( 1f );
						heroine.busy();
						heroine.sprite.operate(heroine.pos);
					}
				}
			});

		}
	}

	//exact same damage as regular darts, despite being higher tier.

	@Override
	protected void rangedHit(Char enemy, int cell) {
		targetPos = cell;
		super.rangedHit( enemy, cell);

		//need to spawn a dart
		if (durability <= 0){
			//attempt to stick the dart to the enemy, just drop it if we can't.
			Dart d = new Dart();
			if (sticky && enemy != null && enemy.isAlive() && enemy.alignment != Char.Alignment.ALLY){
				PinCushion p = Buff.affect(enemy, PinCushion.class);
				if (p.target == enemy){
					p.stick(d);
					return;
				}
			}
			Dungeon.level.drop( d, enemy.pos ).sprite.drop();
		}
	}

	private static int targetPos = -1;

	@Override
	public float durabilityPerUse() {
		float use = super.durabilityPerUse();

		use /= 1;

		//checks both destination and source position
		float lotusPreserve = 0f;
		if (targetPos != -1){
			for (Char ch : Actor.chars()){
				if (ch instanceof WandOfRegrowth.Lotus){
					WandOfRegrowth.Lotus l = (WandOfRegrowth.Lotus) ch;
					if (l.inRange(targetPos)){
						lotusPreserve = Math.max(lotusPreserve, l.seedPreservation());
					}
				}
			}
			targetPos = -1;
		}
		int p = curUser == null ? Dungeon.heroine.pos : curUser.pos;
		for (Char ch : Actor.chars()){
			if (ch instanceof WandOfRegrowth.Lotus){
				WandOfRegrowth.Lotus l = (WandOfRegrowth.Lotus) ch;
				if (l.inRange(p)){
					lotusPreserve = Math.max(lotusPreserve, l.seedPreservation());
				}
			}
		}
		use *= (1f - lotusPreserve);

		return use;
	}

	@Override
	public int value() {
		//value of regular dart plus half of the seed
		return 8 * quantity;
	}

	private static HashMap<Class<?extends Plant.Seed>, Class<?extends TippedDart>> types = new HashMap<>();
	static {
		types.put(Blindweed.Seed.class,     BlindingDart.class);
		types.put(Dreamfoil.Seed.class,     CleansingDart.class);
		types.put(Earthroot.Seed.class,     ParalyticDart.class);
		types.put(Fadeleaf.Seed.class,      DisplacingDart.class);
		types.put(Firebloom.Seed.class,     IncendiaryDart.class);
		types.put(Icecap.Seed.class,        ChillingDart.class);
		types.put(Rotberry.Seed.class,      RotDart.class);
		types.put(Sorrowmoss.Seed.class,    PoisonDart.class);
		types.put(Starflower.Seed.class,    HolyDart.class);
		types.put(Stormvine.Seed.class,     ShockingDart.class);
		types.put(Sungrass.Seed.class,      HealingDart.class);
		types.put(Swiftthistle.Seed.class,  AdrenalineDart.class);
	}

	public static TippedDart getTipped( Plant.Seed s, int quantity ){
		return (TippedDart) Reflection.newInstance(types.get(s.getClass())).quantity(quantity);
	}

	public static TippedDart randomTipped( int quantity ){
		Plant.Seed s;
		do{
			s = (Plant.Seed) Generator.randomUsingDefaults(Generator.Category.SEED);
		} while (!types.containsKey(s.getClass()));

		return getTipped(s, quantity );

	}

}