/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HomingBlade;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Beam;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfSetsunatrip extends DamageWand {

	{
		image = ItemSpriteSheet.WAND_SETSUNA_TRIP;

		collisionProperties = Ballistica.MAGIC_BOLT;
	}

	public int min(int lvl){
		return 1+lvl;
	}

	public int max(int lvl){
		return 5+3*lvl;
	}

	@Override
	public void onZap(Ballistica beam) {

		int cell = beam.collisionPos;

		Char ch = Actor.findChar(cell);

		if (ch != null) {
			wandProc(ch, chargesPerCast());
			ch.move(curUser.pos);
			ch.sprite.move(cell, curUser.pos);
			ScrollOfTeleportation.teleportToLocation(curUser, cell);
			ch.damage(damageRoll(), this);
			}
		}

	@Override
	public void fx(Ballistica beam, Callback callback) {
		curUser.sprite.parent.add(
				new Beam.LightRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(beam.collisionPos)));
		callback.call();
	}

	@Override
	public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {
		Buff.prolong(attacker, HomingBlade.class, 5f);
	}

	@Override
	public void staffFx(MarisaStaff.StaffParticle particle) {
		particle.color( Random.Int( 0x1000000 ) );
		particle.am = 0.5f;
		particle.setLifespan(1f);
		particle.speed.polar(Random.Float(PointF.PI2), 2f);
		particle.setSize( 1f, 2f);
		particle.radiateXY( 0.5f);
	}
}