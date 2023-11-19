package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;


public abstract class MobWithSpellcard extends Mob {
    protected ArrayList<Class> spellcardsList = new ArrayList<Class>();
    protected ArrayList<Class> spellcardsDefaultList = new ArrayList<Class>();
    public boolean isSpellcardOn = false;
    public Spellcard spellcard = null;
    protected int cardCooldown = 1;
    protected int numberOfCards;
    public boolean useSpellcard(){
        if(spellcardsList.isEmpty()){
            updateSpellcardList();
            if (spellcardsList.isEmpty()) return false;
        }
        Spellcard sc = (Spellcard)Reflection.newInstance(spellcardsList.get(0));
        spellcardsList.remove(0);

        Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );
        isSpellcardOn = true;
        fx(isSpellcardOn);
        spellcard = sc;
        numberOfCards--;
        sc.activate(this);
        return true;
    }
    public void deactivateSpellcard(int cardCooldown){
        isSpellcardOn = false;
        fx( isSpellcardOn );
        this.cardCooldown = cardCooldown;
        spellcard = null;
    }
    @Override
    public void die( Object cause ){
        if (spellcard != null) spellcard.deactivate();
        super.die(cause);
    }

    public void fx(boolean on) {
        if (on) sprite.add(CharSprite.State.SPELLCARD_IS_ON_DARK);
        else sprite.remove(CharSprite.State.SPELLCARD_IS_ON_DARK);
    }

    public boolean readyToUseSpellcard(){
       if (enemy != null && state == HUNTING && numberOfCards > 0 && !isSpellcardOn){
           if (enemySeen == true)
            return true;
        }
        return false;
    }

    private ArrayList<Class> updateSpellcardList() {
        for (Class c : spellcardsDefaultList){
            spellcardsList.add(c);
        }
        Random.shuffle(spellcardsList);
        return spellcardsList;
    }

    private final String SPELLCARDS_LIST = "spellcards_list";
    private final String IS_SPELLCARD_ON = "is_spellcard_on";
    private final String SPELLCARD = "spellcard";
    private final String CARD_COOLDOWN = "card_cooldown";
    private final String NUMBER_OF_CARDS = "number_of_cards";
    @Override
    public void storeInBundle(Bundle bundle) {
        Class[] spellcardArray = new Class[spellcardsList.size()];
        spellcardsList.toArray(spellcardArray);
        bundle.put(SPELLCARDS_LIST, spellcardArray);
        bundle.put(IS_SPELLCARD_ON, isSpellcardOn);
        bundle.put(SPELLCARD, spellcard);
        bundle.put(CARD_COOLDOWN, cardCooldown);
        bundle.put(NUMBER_OF_CARDS, numberOfCards);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        Class[] spellcardArray = bundle.getClassArray(SPELLCARDS_LIST);
        for (Class c : spellcardArray) spellcardsList.add(c);
        isSpellcardOn = bundle.getBoolean(IS_SPELLCARD_ON);
        spellcard = (Spellcard) bundle.get(SPELLCARD);
        cardCooldown = bundle.getInt(CARD_COOLDOWN);
        numberOfCards = bundle.getInt(NUMBER_OF_CARDS);
        if (spellcard != null) spellcard.resume(this);
        super.restoreFromBundle(bundle);
    }

    @Override
    protected boolean act() {

        if (state == HUNTING && cardCooldown > 0){
            cardCooldown -= 1;
        } else if (readyToUseSpellcard()) {
            if (useSpellcard()) {
            spend( TICK );
            return true;
            }
        }

        return super.act();
    }
    @Override
    public String description() {
        return super.description() + "\n\n" + Messages.get(MobWithSpellcard.class, "num_of_spellcards", numberOfCards);
    }
}
