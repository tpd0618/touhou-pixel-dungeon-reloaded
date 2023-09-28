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

package com.touhoupixel.touhoupixeldungeongaiden.items.artifacts;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfLivingEarth;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class ChaliceOfBlood extends Artifact {

	{
		image = ItemSpriteSheet.ARTIFACT_CHALICE1;

		levelCap = 10;
	}

	public static final String AC_PRICK = "PRICK";

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (isEquipped(heroine) && level() < levelCap && !cursed && Dungeon.heroine.buff(SuperHard.class) == null && !heroine.isInvulnerable(getClass()))
			actions.add(AC_PRICK);
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action ) {
		super.execute(heroine, action);

		if (action.equals(AC_PRICK)){

			int damage = 3*(level()*level());

			if (damage > heroine.HP*0.75) {

				GameScene.show(
					new WndOptions(new ItemSprite(this),
							Messages.titleCase(name()),
							Messages.get(this, "prick_warn"),
							Messages.get(this, "yes"),
							Messages.get(this, "no")) {
						@Override
						protected void onSelect(int index) {
							if (index == 0)
								prick(Dungeon.heroine);
						}
					}
				);

			} else {
				prick(heroine);
			}
		}
	}

	private void prick(Hero heroine){
		int damage = 3*(level()*level());

		Earthroot.Armor armor = heroine.buff(Earthroot.Armor.class);
		if (armor != null) {
			damage = armor.absorb(damage);
		}

		WandOfLivingEarth.RockArmor rockArmor = heroine.buff(WandOfLivingEarth.RockArmor.class);
		if (rockArmor != null) {
			damage = rockArmor.absorb(damage);
		}

		damage -= heroine.drRoll();

		heroine.sprite.operate( heroine.pos );
		heroine.busy();
		heroine.spend(3f);
		GLog.w( Messages.get(this, "onprick") );
		if (damage <= 0){
			damage = 1;
		} else {
			Sample.INSTANCE.play(Assets.Sounds.CURSED);
			heroine.sprite.emitter().burst( ShadowParticle.CURSE, 4+(damage/10) );
		}

		heroine.damage(damage, this);

		if (!heroine.isAlive()) {
			Dungeon.fail( getClass() );
			GLog.n( Messages.get(this, "ondeath") );
		} else {
			upgrade();
		}
	}

	@Override
	public Item upgrade() {
		if (level() >= 6)
			image = ItemSpriteSheet.ARTIFACT_CHALICE3;
		else if (level() >= 2)
			image = ItemSpriteSheet.ARTIFACT_CHALICE2;
		return super.upgrade();
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (level() >= 7) image = ItemSpriteSheet.ARTIFACT_CHALICE3;
		else if (level() >= 3) image = ItemSpriteSheet.ARTIFACT_CHALICE2;
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new chaliceRegen();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		//grants 5 turns of healing up-front
		float healDelay = 10f - level()*0.9f;
		healDelay /= amount;
		//effectively 1HP at lvl 0-5, 2HP lvl 6-8, 3HP lvl 9, and 5HP lvl 10.
		target.HP = Math.min( target.HT, target.HP + (int)Math.ceil(5/healDelay));
	}
	
	@Override
	public String desc() {
		String desc = super.desc();

		if (isEquipped (Dungeon.heroine)){
			desc += "\n\n";
			if (cursed)
				desc += Messages.get(this, "desc_cursed");
			else if (level() == 0)
				desc += Messages.get(this, "desc_1");
			else if (level() < levelCap)
				desc += Messages.get(this, "desc_2");
			else
				desc += Messages.get(this, "desc_3");
		}

		return desc;
	}

	public class chaliceRegen extends ArtifactBuff {
		//see Regeneration.class for effect
	}

}
