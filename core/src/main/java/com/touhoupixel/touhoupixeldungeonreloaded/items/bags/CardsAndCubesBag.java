package com.touhoupixel.touhoupixeldungeonreloaded.items.bags;

import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.Abilitycards;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.CubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class CardsAndCubesBag extends Bag{
    {
        image = ItemSpriteSheet.HOLDER;
    }

    @Override
    public boolean canHold( Item item ) {
        if (item instanceof CubeFragment || item instanceof Abilitycards){
            return super.canHold(item);
        } else {
            return false;
        }
    }

    public int capacity(){
        return 19;
    }

    @Override
    public void onDetach( ) {
        super.onDetach();
        for (Item item : items) {
        }
    }

    @Override
    public int value() {
        return 40;
    }

}