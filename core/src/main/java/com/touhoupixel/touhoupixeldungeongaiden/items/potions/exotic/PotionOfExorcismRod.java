package com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic;

import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ReachIncrease;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class PotionOfExorcismRod extends ExoticPotion {

    {
        icon = ItemSpriteSheet.Icons.POTION_EXORCISM_ROD;
    }

    @Override
    public void apply( Hero heroine) {
        identify();
        Buff.prolong(heroine, ReachIncrease.class, ReachIncrease.DURATION);
    }

}