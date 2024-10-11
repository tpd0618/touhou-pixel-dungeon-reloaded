package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BalanceBreak;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Randomizer;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MomijiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Momiji extends Mob implements Callback {
    private static final float TIME_TO_ZAP	= 0.8f;
    {
        spriteClass = MomijiSprite.class;

        HP = HT = 63;
        defenseSkill = 17;
        EXP = 8;
        maxLvl = 25;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        viewDistance = 20240125; //you know what it is?

        loot = Generator.Category.POTION;
        lootChance = 0.08f;
    }
    @Override
    protected boolean canAttack( Char enemy ) {
        return !Dungeon.level.adjacent( pos, enemy.pos )
                && (super.canAttack(enemy) || new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos);
    }
    @Override
    protected boolean doAttack( Char enemy ) {

        if (Dungeon.level.adjacent( pos, enemy.pos )) {

            return super.doAttack( enemy );

        } else {

            if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
                sprite.zap( enemy.pos );
                return false;
            } else {
                zap();
                return true;
            }
        }
    }
    //used so resistances can differentiate between melee and magical attacks
    public static class DarkBolt{}
    protected void zap() {
        spend( TIME_TO_ZAP );

        if (hit( this, enemy, true )) {
            //TODO would be nice for this to work on ghost/statues too
            if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(2) == 0) {
                switch (Random.Int(11)){
                    default:
                    case 0:
                        Buff.prolong(enemy, Randomizer.class, 15f);
                        break;
                    case 1:
                        Buff.prolong(enemy, Slow.class, 7f);
                        break;
                    case 2:
                        Buff.prolong(enemy, Inversion.class, 20f);
                        break;
                    case 3:
                        Buff.prolong(enemy, Hex.class, 20f);
                        break;
                    case 4:
                        Buff.prolong(enemy, Vulnerable.class, 20f);
                        break;
                    case 5:
                        Buff.prolong(enemy, Weakness.class, 20f);
                        break;
                    case 6:
                        Buff.prolong(enemy, Paralysis.class, 1f);
                        break;
                    case 7:
                        Buff.prolong(enemy, HeavenSpeed.class, 20f);
                        break;
                    case 9:
                        Buff.prolong(enemy, Blindness.class, 10f);
                    case 10:
                        Buff.prolong(enemy, Cripple.class, 12f);
                }
            }

            int dmg = Random.NormalIntRange( 5, 20 );
            enemy.damage( dmg, new Sanae.DarkBolt() );

            if (enemy == Dungeon.heroine && !enemy.isAlive()) {
                Dungeon.fail( getClass() );
                GLog.n( Messages.get(this, "bolt_kill") );
            }
        } else {
            enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
        }
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 15, 35 );
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(16, 24);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Weakness.class, Weakness.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
            }
        }
        return damage;
    }
    public void onZapComplete() {
        zap();
        next();
    }
    @Override
    protected boolean getCloser( int target ) {
        if (state == HUNTING) {
            return enemySeen && getFurther( target );
        } else {
            return super.getCloser( target );
        }
    }

    @Override
    public void aggro(Char ch) {
        //cannot be aggroed to something it can't see
        if (ch == null || fieldOfView == null || fieldOfView[ch.pos]) {
            super.aggro(ch);
        }
    }

    @Override
    public void call() {
        next();
    }
}