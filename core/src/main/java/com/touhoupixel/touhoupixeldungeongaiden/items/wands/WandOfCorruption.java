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

package com.touhoupixel.touhoupixeldungeongaiden.items.wands;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Amok;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Charm;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Chill;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Corrosion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Corruption;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doom;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Dread;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.FlavourBuff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Frost;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MagicalSleep;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Roots;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Terror;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Medicine;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mimic;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Murasa;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Wraith;
import com.touhoupixel.touhoupixeldungeongaiden.effects.MagicMissile;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.HashMap;

public class WandOfCorruption extends Wand {

	{
		image = ItemSpriteSheet.WAND_CORRUPTION;
	}
	
	//Note that some debuffs here have a 0% chance to be applied.
	// This is because the wand of corruption considers them to be a certain level of harmful
	// for the purposes of reducing resistance, but does not actually apply them itself
	
	private static final float MINOR_DEBUFF_WEAKEN = 1/4f;
	private static final HashMap<Class<? extends Buff>, Float> MINOR_DEBUFFS = new HashMap<>();
	static{
		MINOR_DEBUFFS.put(Weakness.class,       2f);
		MINOR_DEBUFFS.put(Vulnerable.class,     2f);
		MINOR_DEBUFFS.put(Cripple.class,        1f);
		MINOR_DEBUFFS.put(Blindness.class,      1f);
		MINOR_DEBUFFS.put(Terror.class,         1f);
		
		MINOR_DEBUFFS.put(Chill.class,          0f);
		MINOR_DEBUFFS.put(Roots.class,          0f);
		MINOR_DEBUFFS.put(Vertigo.class,        0f);
		MINOR_DEBUFFS.put(Drowsy.class,         0f);
		MINOR_DEBUFFS.put(Bleeding.class,       0f);
		MINOR_DEBUFFS.put(Burning.class,        0f);
		MINOR_DEBUFFS.put(Poison.class,         0f);
	}
	
	private static final float MAJOR_DEBUFF_WEAKEN = 1/2f;
	private static final HashMap<Class<? extends Buff>, Float> MAJOR_DEBUFFS = new HashMap<>();
	static{
		MAJOR_DEBUFFS.put(Amok.class,           3f);
		MAJOR_DEBUFFS.put(Slow.class,           2f);
		MAJOR_DEBUFFS.put(Hex.class,            2f);
		MAJOR_DEBUFFS.put(Paralysis.class,      1f);

		MAJOR_DEBUFFS.put(Dread.class,          0f);
		MAJOR_DEBUFFS.put(Charm.class,          0f);
		MAJOR_DEBUFFS.put(MagicalSleep.class,   0f);
		MAJOR_DEBUFFS.put(Corrosion.class,      0f);
		MAJOR_DEBUFFS.put(Frost.class,          0f);
		MAJOR_DEBUFFS.put(Doom.class,           0f);
	}
	
	@Override
	public void onZap(Ballistica bolt) {
		Char ch = Actor.findChar(bolt.collisionPos);

		if (ch != null){
			
			if (!(ch instanceof Mob)){
				return;
			}

			Mob enemy = (Mob) ch;

			float corruptingPower = 3 + buffedLvl()/2f;
			
			//base enemy resistance is usually based on their exp, but in special cases it is based on other criteria
			float enemyResist = 1 + enemy.EXP;
			if (ch instanceof Mimic){
				enemyResist = 1 + Dungeon.floor;
			} else if (ch instanceof Murasa || ch instanceof Medicine) {
				enemyResist = 1 + Dungeon.floor /2f;
			} else if (ch instanceof Wraith) {
				//divide by 5 as wraiths are always at full HP and are therefore ~5x harder to corrupt
				enemyResist = (1f + Dungeon.floor /3f) / 5f;
			}
			
			//100% health: 5x resist   75%: 3.25x resist   50%: 2x resist   25%: 1.25x resist
			enemyResist *= 1 + 4*Math.pow(enemy.HP/(float)enemy.HT, 2);
			
			//debuffs placed on the enemy reduce their resistance
			for (Buff buff : enemy.buffs()){
				if (MAJOR_DEBUFFS.containsKey(buff.getClass()))         enemyResist *= (1f-MAJOR_DEBUFF_WEAKEN);
				else if (MINOR_DEBUFFS.containsKey(buff.getClass()))    enemyResist *= (1f-MINOR_DEBUFF_WEAKEN);
				else if (buff.type == Buff.buffType.NEGATIVE)           enemyResist *= (1f-MINOR_DEBUFF_WEAKEN);
			}
			
			//cannot re-corrupt or doom an enemy, so give them a major debuff instead
			if(enemy.buff(Corruption.class) != null || enemy.buff(Doom.class) != null){
				corruptingPower = enemyResist - 0.001f;
			}
			
			if (corruptingPower > enemyResist){
				corruptEnemy( enemy );
			} else {
				float debuffChance = corruptingPower / enemyResist;
				if (Random.Float() < debuffChance){
					debuffEnemy( enemy, MAJOR_DEBUFFS);
				} else {
					debuffEnemy( enemy, MINOR_DEBUFFS);
				}
			}

			wandProc(ch, chargesPerCast());
			Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1, 0.8f * Random.Float(0.87f, 1.15f) );
			
		} else {
			Dungeon.level.pressCell(bolt.collisionPos);
		}
	}
	
	private void debuffEnemy( Mob enemy, HashMap<Class<? extends Buff>, Float> category ){
		
		//do not consider buffs which are already assigned, or that the enemy is immune to.
		HashMap<Class<? extends Buff>, Float> debuffs = new HashMap<>(category);
		for (Buff existing : enemy.buffs()){
			if (debuffs.containsKey(existing.getClass())) {
				debuffs.put(existing.getClass(), 0f);
			}
		}
		for (Class<?extends Buff> toAssign : debuffs.keySet()){
			 if (debuffs.get(toAssign) > 0 && enemy.isImmune(toAssign)){
			 	debuffs.put(toAssign, 0f);
			 }
		}
		
		//all buffs with a > 0 chance are flavor buffs
		Class<?extends FlavourBuff> debuffCls = (Class<? extends FlavourBuff>) Random.chances(debuffs);
		
		if (debuffCls != null){
			Buff.append(enemy, debuffCls, 6 + buffedLvl()*3);
		} else {
			//if no debuff can be applied (all are present), then go up one tier
			if (category == MINOR_DEBUFFS)          debuffEnemy( enemy, MAJOR_DEBUFFS);
			else if (category == MAJOR_DEBUFFS)     corruptEnemy( enemy );
		}
	}
	
	private void corruptEnemy( Mob enemy ){
		//cannot re-corrupt or doom an enemy, so give them a major debuff instead
		if(enemy.buff(Corruption.class) != null || enemy.buff(Doom.class) != null){
			GLog.w( Messages.get(this, "already_corrupted") );
			return;
		}
		
		if (!enemy.isImmune(Corruption.class)){
			Corruption.corruptionHeal(enemy);

			AllyBuff.affectAndLoot(enemy, curUser, Corruption.class);
		} else {
			Buff.affect(enemy, Doom.class);
		}
	}

	@Override
	public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {
		// lvl 0 - 25%
		// lvl 1 - 40%
		// lvl 2 - 50%
		if (Random.Int( buffedLvl() + 4 ) >= 3){
			Buff.prolong( defender, Amok.class, 4+ buffedLvl()*2);
		}
	}

	@Override
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar( curUser.sprite.parent,
				MagicMissile.SHADOW,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
	}

	@Override
	public void staffFx(MarisaStaff.StaffParticle particle) {
		particle.color( 0 );
		particle.am = 0.6f;
		particle.setLifespan(2f);
		particle.speed.set(0, 5);
		particle.setSize( 0.5f, 2f);
		particle.shuffleXY(1f);
	}

}
