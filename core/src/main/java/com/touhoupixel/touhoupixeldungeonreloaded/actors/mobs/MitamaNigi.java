package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Blizzard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.CorrosiveGas;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Electricity;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Freezing;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Inferno;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.StenchGas;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.ToxicGas;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Randomizer;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.AlchemyCollection;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tickets.ThreeStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MitamaNigiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class MitamaNigi extends Mob {

    private static final float SPAWN_DELAY	= 4f;

    {
        spriteClass = MitamaNigiSprite.class;

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
        Dungeon.level.drop(new AlchemyCollection(), pos ).sprite.drop();
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

    public static MitamaNigi spawnAt( int pos ) {
        if ((!Dungeon.level.solid[pos] || Dungeon.level.passable[pos]) && Actor.findChar( pos ) == null) {

            MitamaNigi w = new MitamaNigi();
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