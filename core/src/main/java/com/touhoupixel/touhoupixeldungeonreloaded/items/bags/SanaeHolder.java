package com.touhoupixel.touhoupixeldungeonreloaded.items.bags;

import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Plant;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class SanaeHolder extends Bag {

    {
        image = ItemSpriteSheet.POUCH;
    }

    @Override
    public boolean canHold( Item item ) {
        if (item instanceof Plant.Seed || item instanceof Runestone){
            return super.canHold(item);
        } else {
            return false;
        }
    }

    public int capacity(){
        return 19;
    }

    @Override
    public int value() {
        return 30;
    }

}