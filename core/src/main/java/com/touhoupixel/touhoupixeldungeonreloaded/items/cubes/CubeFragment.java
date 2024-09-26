package com.touhoupixel.touhoupixeldungeonreloaded.items.cubes;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public abstract class CubeFragment extends Item {
    {
        stackable = true;
        unique = true;
    }

    @Override
    public ArrayList<String> actions(Hero heroine) {
        ArrayList<String> actions = super.actions(heroine);
        return actions;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }
}
