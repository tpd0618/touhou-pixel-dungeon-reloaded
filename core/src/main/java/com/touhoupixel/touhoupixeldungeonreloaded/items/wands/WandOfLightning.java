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

package com.touhoupixel.touhoupixeldungeonreloaded.items.wands;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Lightning;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.SparkParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.BlueCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.RedCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.WhiteCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.enchantments.Shocking;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class WandOfLightning extends DamageWand {

	{
		image = ItemSpriteSheet.WAND_LIGHTNING;
	}
	
	private ArrayList<Char> affected = new ArrayList<>();

	private ArrayList<Lightning.Arc> arcs = new ArrayList<>();

	public int min(int lvl){
		return 2+5*lvl;
	}

	public int max(int lvl){
		return (int)(8+7*lvl);
	}
	
	@Override
	public void onZap(Ballistica bolt) {

		//lightning deals less damage per-target, the more targets that are hit.
		float multipler = 0.4f + (0.6f/affected.size());
		//if the main target is in water, all affected take full damage
		if (Dungeon.level.water[bolt.collisionPos]) multipler = 1f;

		for (Char ch : affected){
			if (ch == Dungeon.heroine) Camera.main.shake( 2, 0.3f );
			ch.sprite.centerEmitter().burst( SparkParticle.FACTORY, 3 );
			ch.sprite.flash();

			if (ch != curUser && ch.alignment == curUser.alignment && ch.pos != bolt.collisionPos){
				continue;
			}
			wandProc(ch, chargesPerCast());
			if (ch == curUser) {
				ch.damage(Math.round(damageRoll() * multipler * 0.5f), this);
			} else {
				ch.damage(Math.round(damageRoll() * multipler), this);
			}
		}

		if (!curUser.isAlive()) {
			Dungeon.fail( getClass() );
			GLog.n(Messages.get(this, "ondeath"));
		}
	}

	public boolean areEnoughCubes(){
		WhiteCubeFragment whiteCubeFragment = Dungeon.heroine.belongings.getItem(WhiteCubeFragment.class);
		BlueCubeFragment blueCubeFragment = Dungeon.heroine.belongings.getItem(BlueCubeFragment.class);

		if (whiteCubeFragment != null && whiteCubeFragment.quantity() > 2 &&
				blueCubeFragment != null && blueCubeFragment.quantity() > 0){
			return true;
		}
		else return false;
	}

	protected void spendColourCubes() {
		WhiteCubeFragment whiteCubeFragment = Dungeon.heroine.belongings.getItem(WhiteCubeFragment.class);
		BlueCubeFragment blueCubeFragment = Dungeon.heroine.belongings.getItem(BlueCubeFragment.class);

		for (int i = 0; i < 3; i++) whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
		blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
	}

	@Override
	public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {
		//acts like shocking enchantment
		new Shocking().proc(staff, attacker, defender, damage);
	}

	private void arc( Char ch ) {

		int dist = (Dungeon.level.water[ch.pos] && !ch.flying) ? 2 : 1;

		ArrayList<Char> hitThisArc = new ArrayList<>();
		PathFinder.buildDistanceMap( ch.pos, BArray.not( Dungeon.level.solid, null ), dist );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE){
				Char n = Actor.findChar( i );
				if (n == Dungeon.heroine && PathFinder.distance[i] > 1)
					//the hero is only zapped if they are adjacent
					continue;
				else if (n != null && !affected.contains( n )) {
					hitThisArc.add(n);
				}
			}
		}
		
		affected.addAll(hitThisArc);
		for (Char hit : hitThisArc){
			arcs.add(new Lightning.Arc(ch.sprite.center(), hit.sprite.center()));
			arc(hit);
		}
	}
	
	@Override
	public void fx(Ballistica bolt, Callback callback) {

		affected.clear();
		arcs.clear();

		int cell = bolt.collisionPos;

		Char ch = Actor.findChar( cell );
		if (ch != null) {
			affected.add( ch );
			arcs.add( new Lightning.Arc(curUser.sprite.center(), ch.sprite.center()));
			arc(ch);
		} else {
			arcs.add( new Lightning.Arc(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(bolt.collisionPos)));
			CellEmitter.center( cell ).burst( SparkParticle.FACTORY, 3 );
		}

		//don't want to wait for the effect before processing damage.
		curUser.sprite.parent.addToFront( new Lightning( arcs, null ) );
		Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
		callback.call();
	}

	@Override
	public void staffFx(MarisaStaff.StaffParticle particle) {
		particle.color(0xFFFFFF);
		particle.am = 0.6f;
		particle.setLifespan(0.6f);
		particle.acc.set(0, +10);
		particle.speed.polar(-Random.Float(3.1415926f), 6f);
		particle.setSize(0f, 1.5f);
		particle.sizeJitter = 1f;
		particle.shuffleXY(1f);
		float dst = Random.Float(1f);
		particle.x -= dst;
		particle.y += dst;
	}
	
}
