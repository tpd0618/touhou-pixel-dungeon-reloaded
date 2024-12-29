package com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.WaterOfHealth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicalSleep;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.BlastParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;

import java.util.Random;

public class PotionOfRussianRoulette extends ExoticPotion {

    {
        icon = ItemSpriteSheet.Icons.POTION_EXORCISM_ROD;
    }

    private int russian_roulette = 1;

    @Override
    public void apply( Hero heroine) {
        identify();

        Random random = new Random();

        if (random.nextInt(6) >= 6-russian_roulette){
            superUnlucky();
        } else {
            blankBullet();
        }
    }

    public void superUnlucky(){
        GLog.n( Messages.get(this, "super_unlucky") );
        GameScene.flash(-65536);
        curUser.lvl = 1;
        curUser.exp = 0;
        curUser.updateHT( true );
        Statistics.life = 0;
        Statistics.spellcard = 0;
        for (Buff c : Dungeon.heroine.buffs()) {
            c.detach();
        }
        Sample.INSTANCE.play( Assets.Sounds.TIMEOUT );

        russian_roulette = 1;
    }

    public void blankBullet() {
        GLog.p(Messages.get(this, "blank_bullet"));

        for (int i = 0; i < 3; i++) {
            curUser.earnExp(Dungeon.heroine.maxExp(), getClass());
        }
        WaterOfHealth.affectCell(Dungeon.heroine.pos);
        Dungeon.gold += 1000;
        Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION * 5f);
        for (int i : PathFinder.NEIGHBOURS8){
            Char c = Actor.findChar(curUser.pos+i);
            if (c != null) {
                Buff.affect(c, MagicalSleep.class);
                CellEmitter.center(c.pos).burst(BlastParticle.FACTORY, 4);
            }
        }
        Buff.prolong(curUser, DoubleSpeed.class, DoubleSpeed.DURATION*3f);
        for (int i = 0; i < 5; i++) {
            curUser.STR++;
        }

        russian_roulette += 1;
    }

    private final String RUSSIAN_ROULETTE = "russian_roulette";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(RUSSIAN_ROULETTE, russian_roulette);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        russian_roulette = bundle.getInt(RUSSIAN_ROULETTE);
    }

    @Override
    public String info() {

        String info = desc();

        info += "\n\n" + Messages.get( this, "count") + russian_roulette+"/6";
        return info;
    }
}