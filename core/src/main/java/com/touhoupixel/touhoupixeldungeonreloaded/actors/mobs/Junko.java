package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.BlastParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.JunkoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Junko extends Mob {

    private boolean madanteUsed = false;

    {
        spriteClass = JunkoSprite.class;

        HP = HT = 250;
        defenseSkill = 37;
        EXP = 22;
        maxLvl = 50;

        properties.add(Property.GOD);
        properties.add(Property.HELL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(126, 192);
    }

    @Override
    public int attackSkill(Char target) {
        return 47;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(62, 89);
    }

    @Override
    protected boolean act() {
        if (!madanteUsed && Dungeon.heroine.HP != Dungeon.heroine.HT && Dungeon.level.heroFOV[pos] && this.state != SLEEPING && this.state != FLEEING && Random.Int(8) == 0) {
            if (Dungeon.heroine.HP > 99){
                Dungeon.heroine.HP = Random.NormalIntRange(1, 9);
            } else {
                Dungeon.heroine.die(this);
            }
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            GLog.w(Messages.get(this, "madante"));
            madanteUsed = true;
            CellEmitter.center(Dungeon.heroine.pos).burst(BlastParticle.FACTORY, 30);
            CellEmitter.center(Dungeon.heroine.pos-1-Dungeon.level.width()).burst(BlastParticle.FACTORY, 30);
            CellEmitter.center(Dungeon.heroine.pos-1+Dungeon.level.width()).burst(BlastParticle.FACTORY, 30);
            CellEmitter.center(Dungeon.heroine.pos+1-Dungeon.level.width()).burst(BlastParticle.FACTORY, 30);
            CellEmitter.center(Dungeon.heroine.pos+1+Dungeon.level.width()).burst(BlastParticle.FACTORY, 30);
        }
        return super.act();
    }

    private final String MADANTEUSED = "chainsused";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(MADANTEUSED, madanteUsed);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        madanteUsed = bundle.getBoolean(MADANTEUSED);
    }
}