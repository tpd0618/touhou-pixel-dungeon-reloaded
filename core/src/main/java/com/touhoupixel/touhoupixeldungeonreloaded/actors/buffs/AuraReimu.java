package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import static java.lang.Math.max;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.TsukasaSoul;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Wraith;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells.MobWithSpellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.PhoenixFeathers;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class AuraReimu extends Buff {
    {
        type = buffType.NEUTRAL;
    }

    public static final float CREATING_TIME = 30f;
    public float leftTime = 0;
    public int numberOfSpheres = 0;

    public int maxNumberOfSpheres() {
        int charLvl = 0;
        if (target instanceof Hero) {
            charLvl = ((Hero) target).lvl;
        } else charLvl = Dungeon.floor;

        int bonusByCard = Statistics.cardSekibankiHead ? 1 + charLvl / 25 : 0;

        return 2 + charLvl / 10 + bonusByCard;
    }

    public boolean createSphere() {
        if (numberOfSpheres < maxNumberOfSpheres()) {
            numberOfSpheres++;
            if (numberOfSpheres == maxNumberOfSpheres()) leftTime = CREATING_TIME;
            return true;
        } else {
            return false;
        }
    }

    public int protectionValue() {
        int spheres = numberOfSpheres;
        int protVal = 0;
        for (int sp = 0; sp < spheres; sp++) {
            protVal += max(10 - sp / 2, 5); // +10%, +10%, +9%, +9%, +8% ... +5%, +5%, +5%, +5% ...
        }
        protVal = protVal > 100 ? 100 : protVal;
        return protVal;
    }

    public int takeDamage(Char attacker, int damage) {
        int blockedDmg = damage * protectionValue() / 100;

        if (numberOfSpheres > 0 && blockedDmg > 0) {
            numberOfSpheres--;

            int primReflDmg = blockedDmg > 10 + Dungeon.floor * 3 ? 10 + Dungeon.floor * 3 : blockedDmg; // limit of reflected damage
            int reflDmg = attacker.defenseProc(target, primReflDmg);
            if (Statistics.cardShouPagoda) reflDmg *= 1.2f;
            if (Dungeon.level.adjacent(target.pos, attacker.pos) || Statistics.cardShouPagoda) {
                attacker.damage(reflDmg, this);
                // TODO fx refl damage
            }
            if (Statistics.cardSeiranBleedingHammer){ // also damage random mob in FOV
                ArrayList<Char> candidates = new ArrayList<>();
                for (Char ch : chars()){
                    if (!(ch instanceof Hero) && !ch.equals(attacker) && Dungeon.level.heroFOV[ch.pos]) candidates.add(ch);
                }
                if (candidates != null){
                    int rngIndex = Random.Int(candidates.size());
                    Char ch = candidates.get(rngIndex);
                    reflDmg = ch.defenseProc(target, primReflDmg);
                    ch.damage(reflDmg, target);
                }
            }
        }

        return damage - blockedDmg;
    }

    public void reduceTime(float value) {
        leftTime -= value;
    }

    public void reduceTime(Object obj) {
        if (obj instanceof AuraReimu) reduceTime(TICK);
        else if (obj instanceof Char) {
            Char ch = (Char) obj;
            if (ch.properties().contains(Char.Property.BOSS)) {
                reduceTime(CREATING_TIME * 10.0f);
            } else if (ch.properties().contains(Char.Property.MINIBOSS)) {
                reduceTime(CREATING_TIME * 3.0f);
            } else if (ch instanceof MobWithSpellcard) {
                reduceTime(CREATING_TIME * 1.6f);
            } else if (ch instanceof Wraith || ch instanceof PhoenixFeathers.Phoenix || ch instanceof WandOfWarding.Ward || ch instanceof TsukasaSoul) {
                reduceTime(CREATING_TIME * 0.1f);
            } else {
                reduceTime(CREATING_TIME * 0.25f);
            }
        } else reduceTime(TICK);
    }

    @Override
    public boolean act() {
        if (leftTime <= 0 && numberOfSpheres < maxNumberOfSpheres()) {
            leftTime += CREATING_TIME;
            createSphere();
        } else if (leftTime > 0 && numberOfSpheres < maxNumberOfSpheres()) {
            reduceTime(this);
        }

        if (Statistics.cardNemunoKnife && numberOfSpheres < maxNumberOfSpheres()/3)
        {
            spend(TICK / 10);
        }
        else {
            spend(TICK);
        }
        return true;
    }

    @Override
    public int icon() {
        return BuffIndicator.AURA_REIMU;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(2f, 1f, 1f);
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", numberOfSpheres, maxNumberOfSpheres(), (int) protectionValue());
    }

    @Override
    public void fx(boolean on) {
        // TODO cool fx
    }

    private static final String LEFT_TIME = "left_time";
    private static final String NUMBER_OF_SPHERES = "number_of_spheres";

    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(LEFT_TIME, leftTime);
        bundle.put(NUMBER_OF_SPHERES, numberOfSpheres);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        leftTime = bundle.getFloat(LEFT_TIME);
        numberOfSpheres = bundle.getInt(NUMBER_OF_SPHERES);
        super.restoreFromBundle(bundle);
    }
}
