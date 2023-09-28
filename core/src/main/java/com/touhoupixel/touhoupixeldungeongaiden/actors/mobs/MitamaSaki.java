package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Blizzard;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.CorrosiveGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Electricity;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Freezing;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Inferno;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.StenchGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.ToxicGas;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Randomizer;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.tickets.FiveStarTicket;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MitamaSakiSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class MitamaSaki extends Mob {

    private static final float SPAWN_DELAY	= 4f;

    {
        spriteClass = MitamaSakiSprite.class;

        HP = HT = 1;
        defenseSkill = Dungeon.floor;

        flying = true;

        switch (Random.Int(5)) {
            case 0:
            default:
                properties.add(Property.YOKAI);
                break;
            case 1:
                properties.add(Property.GOD);
                break;
            case 2:
                properties.add(Property.HUMAN);
                break;
            case 3:
                properties.add(Property.ANIMAL);
                break;
            case 4:
                properties.add(Property.WARP);
                break;
        }

        immunities.add(Blizzard.class);
        immunities.add(CorrosiveGas.class);
        immunities.add(Electricity.class);
        immunities.add(Fire.class);
        immunities.add(Freezing.class);
        immunities.add(Inferno.class);
        immunities.add(StenchGas.class);
        immunities.add(ToxicGas.class);

        properties.add( Property.MITAMA );

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    protected boolean act() {
        boolean result = super.act();
        if (buff(DoubleSpeed.class) == null) {
            Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 10000f);
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return 1;
    }

    @Override
    public int attackSkill(Char target) {
        return 99;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, Dungeon.floor);
    }

    @Override
    public void die( Object cause ) {
        Dungeon.level.drop(new FiveStarTicket(), pos ).sprite.drop();
        Dungeon.level.drop(new FiveStarTicket(), pos ).sprite.drop();
        super.die( cause );
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(8) == 0) {
            Buff.prolong(enemy, Randomizer.class, Randomizer.DURATION);
        } else {
            this.exterminate();
            this.sprite.die();
            Sample.INSTANCE.play( Assets.Sounds.BURNING );
            GLog.w(Messages.get(this, "run_away"));
        }
        return damage;
    }

    public static void spawnAround( int pos ) {
        for (int n : PathFinder.NEIGHBOURS4) {
            spawnAt( pos + n );
        }
    }

    public static MitamaSaki spawnAt( int pos ) {
        if ((!Dungeon.level.solid[pos] || Dungeon.level.passable[pos]) && Actor.findChar( pos ) == null) {

            MitamaSaki w = new MitamaSaki();
            w.pos = pos;
            w.state = w.HUNTING;
            GameScene.add( w, SPAWN_DELAY );
            Dungeon.level.occupyCell(w);

            w.sprite.alpha( 0 );
            w.sprite.parent.add( new AlphaTweener( w.sprite, 1, 0.5f ) );

            w.sprite.emitter().burst( ShadowParticle.CURSE, 5 );

            return w;
        } else {
            return null;
        }
    }
}