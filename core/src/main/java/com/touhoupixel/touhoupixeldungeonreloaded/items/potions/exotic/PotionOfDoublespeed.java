package com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class PotionOfDoublespeed extends ExoticPotion {

    {
        icon = ItemSpriteSheet.Icons.POTION_DOUBLESPEED;
    }

    @Override
    public void apply( Hero heroine) {
        identify();
        Buff.prolong(heroine, DoubleSpeed.class, DoubleSpeed.DURATION);
    }

}