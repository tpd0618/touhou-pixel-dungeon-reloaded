package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells.MobWithSpellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.HashSet;
import java.util.LinkedList;


public class Spellcard extends Actor {
    protected MobWithSpellcard user;
    protected int left;
    public static boolean isThereActiveCards = false; // for label

    {
        actPriority = SPELLCARD_PRIO;
    }
    @Override
    protected boolean act() {
        left--;
        if (left == 0) deactivate();
        spend(TICK);
        return true;
    }

    public void activate(MobWithSpellcard user) {
        isThereActiveCards = true;
        resume(user);
        activate();
    }
    public void activate(){

    }
    public void deactivate(){
        deactivate(Random.Int(4, 5));
    }
    public void deactivate(int time){
        remove(this);
        isThereActiveCards = false;
        for (Actor a : all()) {
            if (a instanceof Spellcard){
                isThereActiveCards = true;
                break;
            }
        }


        //activeSpellcards.remove(this);
        user.deactivateSpellcard(time);
    }
    public void resume(MobWithSpellcard user){
        this.user = user;
        Actor.add(this);
    }
    public String name(){
        return Messages.get(this, "name");
    }


    private final String LEFT = "left";
    private static final String IS_THERE_ACTIVE_CARDS = "is_there_active_cards";
    public static void storeIsThereActiveCards(Bundle bundle){
        bundle.put(IS_THERE_ACTIVE_CARDS, isThereActiveCards);
    }
    public static void restoreIsThereActiveCards(Bundle bundle){
        isThereActiveCards = bundle.getBoolean(IS_THERE_ACTIVE_CARDS);
    }
    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(LEFT, left);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        left = bundle.getInt(LEFT);
        super.restoreFromBundle(bundle);
    }
}
