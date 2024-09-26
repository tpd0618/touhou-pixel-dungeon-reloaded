package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.KindOfWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Weapon;

public class Helplessness extends Spellcard {
    {
        left = 40;
    }

    @Override
    public void activate() {
        if (user.getEnemy() != null || user.getEnemy() instanceof Hero){
            KindOfWeapon w = ((Hero) user.getEnemy()).belongings.weapon();
            if (w != null) {
                Dungeon.heroine.belongings.weapon = null;
                Dungeon.quickslot.clearItem(w);
                Item.updateQuickslot();

                Dungeon.level.drop(w, Dungeon.level.randomCell());
            }
        }
        if (user.getEnemy() != null){
            Buff.prolong(user.getEnemy(), PotionFreeze.class, PotionFreeze.DURATION);
            Buff.prolong(user.getEnemy(), WandZeroDamage.class, WandZeroDamage.DURATION);
            Buff.prolong(user.getEnemy(), Slow.class, Slow.DURATION);
        }

        super.activate();
    }
    @Override
    protected boolean act() {
        Dungeon.level.isCard_Helplessness = true;
        return super.act();
    }

    @Override
    public void deactivate() {
        Dungeon.level.isCard_Helplessness = false;
        super.deactivate();
    }
}
