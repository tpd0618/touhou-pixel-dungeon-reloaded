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

package com.touhoupixel.touhoupixeldungeongaiden.items.wands;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HomingBlade;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Beam;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.tiles.DungeonTilemap;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfMindburst extends DamageWand {

	{
		image = ItemSpriteSheet.WAND_MIND_BURST;

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

		if (ch != null && !ch.properties().contains(Char.Property.BOSS)){
			wandProc(ch, chargesPerCast());
			ch.die(this);
		}

		if (curUser.HT > 2 && curUser.HP > 2) {
			curUser.HP -= 2;
			curUser.HT -= 2;
			Statistics.maxHP_down -= 2;
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