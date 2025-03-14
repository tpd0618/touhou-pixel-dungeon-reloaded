package com.touhoupixel.touhoupixeldungeonreloaded.items.bags;

import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class IcyBag extends Bag{
    {
        image = ItemSpriteSheet.BANDOLIER;
    }

    @Override
    public boolean canHold( Item item ) {
        if (item instanceof Potion || item instanceof Wand){
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