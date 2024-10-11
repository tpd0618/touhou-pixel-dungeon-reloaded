package com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;

public class PotionOfLunar extends ExoticPotion {

    {
        icon = ItemSpriteSheet.Icons.POTION_EXORCISM_ROD;
    }

    @Override
    public void apply( Hero heroine) {
        identify();
        if (heroine.buff(Inversion.class) != null) {
            heroine.damage(heroine.HT / 2, heroine);
            if (heroine == Dungeon.heroine && !heroine.isAlive()) {
                Dungeon.fail(Inversion.class);
                GLog.n( Messages.get(Inversion.class, "ondeath") );
            }
        } else {
            heroine.HP = Math.min(heroine.HP + 10*Dungeon.level.mobs.size(), heroine.HT);
            GLog.p(Messages.get(this, "lunar"));
        }
    }

}