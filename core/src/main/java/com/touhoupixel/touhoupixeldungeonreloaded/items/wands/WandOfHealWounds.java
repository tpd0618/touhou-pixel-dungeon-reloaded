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

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Beam;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.BlueCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.WhiteCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfHealWounds extends Wand {

	{
		image = ItemSpriteSheet.WAND_HEAL_WOUNDS;

		collisionProperties = Ballistica.PROJECTILE;
	}

	private boolean freeCharge = false;

	@Override
	public void onZap(Ballistica beam) {
		Char ch = Actor.findChar(beam.collisionPos);
		if (ch != null){
			ch.HP = Math.min(ch.HT, ch.HP + (int)(ch.HT*0.2f));
			//curUser.HP = Math.min(curUser.HT, curUser.HP + (int)(curUser.HT*0.05f));
		}
	}
	public boolean areEnoughCubes(){
		BlueCubeFragment blueCubeFragment = Dungeon.heroine.belongings.getItem(BlueCubeFragment.class);
		WhiteCubeFragment whiteCubeFragment = Dungeon.heroine.belongings.getItem(WhiteCubeFragment.class);

		if (blueCubeFragment != null && blueCubeFragment.quantity() > 1 &&
				whiteCubeFragment != null && whiteCubeFragment.quantity() > 0){
			return true;
		}
		else return false;
	}

	protected void spendColourCubes() {
		BlueCubeFragment blueCubeFragment = Dungeon.heroine.belongings.getItem(BlueCubeFragment.class);
		WhiteCubeFragment whiteCubeFragment = Dungeon.heroine.belongings.getItem(WhiteCubeFragment.class);

		blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
		blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
		whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
	}

	@Override
	public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {
		//well, there is no way to get this wand for marisa
	}

	@Override
	public void fx(Ballistica beam, Callback callback) {
		curUser.sprite.parent.add(
				new Beam.HealthRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(beam.collisionPos)));
		callback.call();
	}

	@Override
	public void staffFx(MarisaStaff.StaffParticle particle) {
		particle.color( 0xCC0000 );
		particle.am = 0.6f;
		particle.setLifespan(1f);
		particle.speed.polar( Random.Float(PointF.PI2), 2f );
		particle.setSize( 1f, 2f);
		particle.radiateXY(0.5f);
	}

	@Override
	public String statsDesc() {
		int selfDMG = Math.round(Dungeon.heroine.HT*0.05f);
		if (levelKnown)
			return Messages.get(this, "stats_desc", selfDMG, selfDMG + 3*buffedLvl(), 5+buffedLvl(), 3+buffedLvl()/2, 6+ buffedLvl());
		else
			return Messages.get(this, "stats_desc", selfDMG, selfDMG, 5, 3, 6);
	}

	private static final String FREECHARGE = "freecharge";

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		freeCharge = bundle.getBoolean( FREECHARGE );
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( FREECHARGE, freeCharge );
	}
}