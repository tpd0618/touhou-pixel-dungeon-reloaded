package com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReachIncrease;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.IronKey;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Notes;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class PotionOfSilver extends ExoticPotion {

    {
        icon = ItemSpriteSheet.Icons.POTION_EXORCISM_ROD;
    }

    @Override
    public void apply( Hero heroine) {
        identify();
        if (Dungeon.level.mobs.size() < Notes.keyCount(new IronKey(Dungeon.floor))*10) {
            for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
                mob.die(this);
                GLog.p(Messages.get(this, "silver"));
            }
        }
    }

}