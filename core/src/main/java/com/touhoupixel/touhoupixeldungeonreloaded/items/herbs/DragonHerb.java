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

package com.touhoupixel.touhoupixeldungeonreloaded.items.herbs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDefDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

public class DragonHerb extends Herb {

	{
		image = ItemSpriteSheet.HERB;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals( AC_EAT )) {
			Sample.INSTANCE.play(Assets.Sounds.BLAST);
			hero.sprite.emitter().burst( Speck.factory( Speck.CROWN), 12 );

			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob.pos == hero.pos + 1){
					mob.damage(6 * Dungeon.depth, hero);
					mob.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob2 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob2.pos == hero.pos - 1){
					mob2.damage(6 * Dungeon.depth, hero);
					mob2.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob3 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob3.pos == hero.pos + 2){
					mob3.damage(6 * Dungeon.depth, hero);
					mob3.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob4 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob4.pos == hero.pos - 2){
					mob4.damage(6 * Dungeon.depth, hero);
					mob4.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob5 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob5.pos == hero.pos + 3){
					mob5.damage(6 * Dungeon.depth, hero);
					mob5.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob6 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob6.pos == hero.pos - 3){
					mob6.damage(6 * Dungeon.depth, hero);
					mob6.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob7 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob7.pos == hero.pos + 4) {
					mob7.damage(6 * Dungeon.depth, hero);
					mob7.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6);
				}
			}
			for (Mob mob8 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob8.pos == hero.pos - 4){
					mob8.damage(6 * Dungeon.depth, hero);
					mob8.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob9 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob9.pos == hero.pos + 5){
					mob9.damage(6 * Dungeon.depth, hero);
					mob9.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob10 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob10.pos == hero.pos - 5){
					mob10.damage(6 * Dungeon.depth, hero);
					mob10.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob11 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob11.pos == hero.pos + 6){
					mob11.damage(6 * Dungeon.depth, hero);
					mob11.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob12 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob12.pos == hero.pos - 6){
					mob12.damage(6 * Dungeon.depth, hero);
					mob12.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob13 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob13.pos == hero.pos + 7){
					mob13.damage(6 * Dungeon.depth, hero);
					mob13.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob14 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob14.pos == hero.pos - 7){
					mob14.damage(6 * Dungeon.depth, hero);
					mob14.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob15 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob15.pos == hero.pos + 8){
					mob15.damage(6 * Dungeon.depth, hero);
					mob15.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob16 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob16.pos == hero.pos - 8){
					mob16.damage(6 * Dungeon.depth, hero);
					mob16.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob17 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob17.pos == hero.pos + 9){
					mob17.damage(6 * Dungeon.depth, hero);
					mob17.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob16 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob16.pos == hero.pos - 9){
					mob16.damage(6 * Dungeon.depth, hero);
					mob16.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}

			for (Mob mob19 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob19.pos == hero.pos + 10){
					mob19.damage(6 * Dungeon.depth, hero);
					mob19.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob20 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob20.pos == hero.pos - 10){
					mob20.damage(6 * Dungeon.depth, hero);
					mob20.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
		}
	}
}