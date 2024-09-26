package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class SanaeBuffed extends Sanae {
    private static final float TIME_TO_ZAP	= 1f;
    protected void zap() {
        spend( TIME_TO_ZAP );

        if (hit( this, enemy, true )) {
            if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
                Buff.prolong(enemy, Degrade.class, 20f);
            }
            if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(2) == 0) {
                Buff.prolong(enemy, ExtremeConfusion.class, ExtremeConfusion.DURATION/5f);
                if (Statistics.difficulty > 2) {
                    Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
                }
                if (Statistics.difficulty > 4) {
                    Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
                }
            }

            int dmg = Random.NormalIntRange( 93, 141 );
            enemy.damage( dmg, new DarkBolt() );

            if (enemy == Dungeon.heroine && !enemy.isAlive()) {
                Dungeon.fail( getClass() );
                GLog.n( Messages.get(Sanae.class, "bolt_kill") );
            }
        } else {
            enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
        }
    }
}