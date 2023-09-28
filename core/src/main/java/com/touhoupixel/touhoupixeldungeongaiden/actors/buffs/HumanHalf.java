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

package com.touhoupixel.touhoupixeldungeongaiden.actors.buffs;


import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;


public class HumanHalf extends Buff {

    private float bonusDamage = 0;
    private float bonusCritChance = 0;
    private float increaseBonusDamage;
    private float increaseBonusCritChance;
    private float maxBonusDamage;
    private float maxBonusCritChance;
    protected float left;
    private int pos;
    public static int getBonusReach(){
        if (Statistics.card60){
            return 2;
        }
        return 1;
    }

    private static final String BONUS_DAMAGE	= "bonus_damage";
    private static final String BONUS_CRIT_CHANCE = "bonus_crit_chance";
    private static final String MAX_BONUS_DAMAGE	= "max_bonus_damage";
    private static final String MAX_BONUS_CRIT_CHANCE = "max_bonus_crit_chance";
    private static final String INCREASE_BONUS_DAMAGE	= "increase_bonus_damage";
    private static final String INCREASE_BONUS_CRIT_CHANCE = "increase_bonus_crit_chance";
    private static final String LEFT	= "left";

    {
        type = buffType.POSITIVE;
        announced = true;
    }

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put( BONUS_DAMAGE, bonusDamage);
        bundle.put( BONUS_CRIT_CHANCE, bonusCritChance );
        bundle.put(MAX_BONUS_DAMAGE, maxBonusDamage);
        bundle.put(MAX_BONUS_CRIT_CHANCE, maxBonusCritChance);
        bundle.put(INCREASE_BONUS_DAMAGE, increaseBonusDamage);
        bundle.put(INCREASE_BONUS_CRIT_CHANCE, increaseBonusCritChance);
        bundle.put( LEFT, left );
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        bonusDamage = bundle.getFloat( BONUS_DAMAGE );
        bonusCritChance = bundle.getFloat( BONUS_CRIT_CHANCE);
        maxBonusDamage = bundle.getFloat(MAX_BONUS_DAMAGE);
        maxBonusCritChance = bundle.getFloat(MAX_BONUS_CRIT_CHANCE);
        increaseBonusDamage = bundle.getFloat(INCREASE_BONUS_DAMAGE);
        increaseBonusCritChance = bundle.getFloat(INCREASE_BONUS_CRIT_CHANCE);
        left = bundle.getFloat( LEFT );
    }

    @Override
    public int icon() {
        return BuffIndicator.HUMAN_HALF;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(1f, 0.5f, 0f);
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String heroMessage() {
        return Messages.get(this, "heromsg");
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", (int) (bonusDamage*100), (int) (bonusCritChance*100), (int) (maxBonusDamage*100), (int) (maxBonusCritChance*100), getBonusReach()+1);
    }
    public void set() {
        if (Statistics.card60){
            if (bonusDamage == 0){
                bonusDamage = 0.3f;
                bonusCritChance = 0.2f;
            }
            increaseBonusDamage = 0.1f;
            increaseBonusCritChance = 0.08f;
            maxBonusDamage = 0.6f;
            maxBonusCritChance = 0.44f;
            pos = target.pos;
        }
        else {
            if (bonusDamage == 0){
                bonusDamage = 0.2f;
                bonusCritChance = 0.1f;
            }
            increaseBonusDamage = 0.05f;
            increaseBonusCritChance = 0.04f;
            maxBonusDamage = 0.4f;
            maxBonusCritChance = 0.26f;
            pos = target.pos;
        }

    }
    public void bomb() {
        if (Statistics.card60){
            bonusDamage = 1.5f;
            bonusCritChance = 0.9f;
        } else {
            bonusDamage = 0.8f;
            bonusCritChance = 0.5f;
        }
        set();
    }
    @Override
    public boolean act() {
        if (pos != target.pos){
            Statistics.power += (YoumuAbility.powerReq() - 110);
            detach();
        }
                bonusDamage = Math.min(bonusDamage + increaseBonusDamage, maxBonusDamage);
                bonusCritChance = Math.min(bonusCritChance + increaseBonusCritChance, maxBonusCritChance);

            spend(TICK);
        return true;
    }
    public float getBonusDamage(){ return bonusDamage; }
    public float getBonusCrit(){ return bonusCritChance; }


}
