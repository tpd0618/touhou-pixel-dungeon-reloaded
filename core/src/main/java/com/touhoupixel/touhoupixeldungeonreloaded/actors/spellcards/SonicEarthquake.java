package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.IcyBag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.CardBag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MysticBag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlockTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.RockfallTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class SonicEarthquake extends Spellcard{
    {
        left = 5;
    }

    @Override
    public void activate() {
        Sample.INSTANCE.play( Assets.Sounds.BLAST );
        Camera.main.shake( 20, 1f );
        Char enemy = user.getEnemy();

        int active = Random.Int(2);
        // 1 out of 2 effects will be activated
        if (active == 0){
            new RockfallTrap().set(enemy.pos).activate();
            Buff.prolong(enemy, Vertigo.class, Vertigo.DURATION);
            Buff.prolong(enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION);
        }
        else if (active == 1){
            ((SummoningTrap)new SummoningTrap().set(enemy.pos)).activate(3);
            new FlockTrap().set(enemy.pos).activate();
        }


            //forget potions or scrolls
            if (Random.Int(2) == 0){
                IcyBag sh = Dungeon.heroine.belongings.getItem(IcyBag.class);
                for (Item sc : sh.items){
                    if (!(sc instanceof Potion) || !sc.isIdentified()) continue;
                    sc.forget();
                }
                GLog.n(Messages.get(this, "forget_spellcards"));
            }
            else {
                MysticBag pb = Dungeon.heroine.belongings.getItem(MysticBag.class);
                for (Item pot : pb.items){
                    if (!(pot instanceof Scroll) || !pot.isIdentified()) continue;
                    pot.forget();
                }
                GLog.n(Messages.get(this, "forget_potions"));
            }
        super.deactivate();
    }
}
