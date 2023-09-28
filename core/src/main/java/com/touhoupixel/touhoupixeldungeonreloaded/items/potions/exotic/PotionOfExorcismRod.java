package com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReachIncrease;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

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