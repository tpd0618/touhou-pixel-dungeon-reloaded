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

package com.touhoupixel.touhoupixeldungeongaiden.items.herbs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaAra;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaKusi;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaNigi;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaSaki;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.effects.TargetedCell;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

public class DragonHerb extends Herb {

	{
		image = ItemSpriteSheet.HERB;
	}

	@Override
	public void execute(Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals( AC_EAT )) {
			Sample.INSTANCE.play(Assets.Sounds.BLAST);
			heroine.sprite.emitter().burst( Speck.factory( Speck.CROWN ), 12 );
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 1, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 1, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 2, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 2, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 3, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 3, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 4, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 4, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 5, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 5, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 6, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 6, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 7, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 7, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 8, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 8, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 9, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 9, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos - 10, 0xFF0000));
			heroine.sprite.parent.addToBack(new TargetedCell(heroine.pos + 10, 0xFF0000));

			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (mob.pos == heroine.pos + 1 || mob.pos == heroine.pos - 1 ||
						mob.pos == heroine.pos + 2 || mob.pos == heroine.pos - 2 ||
						mob.pos == heroine.pos + 3 || mob.pos == heroine.pos - 3 ||
						mob.pos == heroine.pos + 4 || mob.pos == heroine.pos - 4 ||
						mob.pos == heroine.pos + 5 || mob.pos == heroine.pos - 5 ||
						mob.pos == heroine.pos + 6 || mob.pos == heroine.pos - 6 ||
						mob.pos == heroine.pos + 7 || mob.pos == heroine.pos - 7 ||
						mob.pos == heroine.pos + 8 || mob.pos == heroine.pos - 8 ||
						mob.pos == heroine.pos + 9 || mob.pos == heroine.pos - 9 ||
						mob.pos == heroine.pos + 10 || mob.pos == heroine.pos - 10){
					if (mob instanceof MitamaAra || mob instanceof MitamaKusi || mob instanceof MitamaNigi || mob instanceof MitamaSaki) {
						mob.damage(0, heroine);
					} else {
						mob.damage(6 * Dungeon.floor, heroine);
					}
				}
			}
		}
	}
}