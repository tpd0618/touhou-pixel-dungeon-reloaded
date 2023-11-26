package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FireShield;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.PhoenixEye;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.PhoenixFeathers;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.PhoenixShield;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Bomb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs.ElixirOfDragonsBlood;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfMadness;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BurningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MokouSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Mokou extends MobWithSpellcard implements Callback {
    private boolean canRevive = true;
    private int lasersCooldown = 0;
    private static final float TIME_TO_ZAP	= 1f;

    {
        spriteClass = MokouSprite.class;

        HP = HT = 190;
        defenseSkill = 20;
        EXP = 20;
        maxLvl = 99;

        spellcardsDefaultList.add(PhoenixShield.class);
        spellcardsDefaultList.add(PhoenixFeathers.class);
        spellcardsDefaultList.add(PhoenixEye.class);


        properties.add(Property.ELIXIR);
        properties.add(Property.HUMAN);
        properties.add(Property.MINIBOSS);

        mobRarity = RARE_RARITY;
        if (Statistics.difficulty <= 2) numberOfCards = 3;
        else if (Statistics.difficulty <= 4) numberOfCards = 4;
        else numberOfCards = 5;

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new LifeFragment();
        lootChance = 0.15f;

        immunities.add(Fire.class);
    }

    @Override
    protected boolean act() {
        if (buff(FireShield.class) != null) {
            if (Dungeon.level.map[pos] == Terrain.WATER){
                Level.set( pos, Terrain.EMPTY);
                GameScene.updateMap( pos );
                CellEmitter.get( pos ).burst( Speck.factory( Speck.STEAM ), 10 );
            }

            for (int i : PathFinder.NEIGHBOURS9) {
                int vol = Fire.volumeAt(pos + i, Fire.class);
                if (vol < 4 && !Dungeon.level.water[pos + i] && !Dungeon.level.solid[pos + i]){
                    GameScene.add( Blob.seed( pos + i, 4 - vol, Fire.class ) );
                }
            }

        }

        if (spellcard instanceof PhoenixEye) {
            if (lasersCooldown > 0) {
                lasersCooldown--;
            } else {
                lasersAttack();
                lasersCooldown = Random.Int(4, 7);
            }
        }
        else {
            lasersCooldown = 0;
        }

        if (!enemySeen && isSpellcardOn) baseSpeed = 4f;
        else baseSpeed = 1f;
        return super.act();
    }



    @Override
    public int damageRoll() {
        return Random.NormalIntRange(51, 79);
    }

    @Override
    public int attackSkill(Char target) {
        return 23;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(15, 23);
    }

    @Override
    public void fx(boolean on) {
        if (on) sprite.add(CharSprite.State.SPELLCARD_IS_ON_FIRE);
        else sprite.remove(CharSprite.State.SPELLCARD_IS_ON_FIRE);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(2) == 0) {
            Buff.affect(enemy, Burning.class).reignite(enemy, 6f);
        }
        return damage;
    }

    private final String CAN_REVIVE = "can_revive";
    private final String LASERS_COOLDOWN = "lasers_cooldown";
    @Override
    public void storeInBundle(Bundle bundle){
        bundle.put(CAN_REVIVE, canRevive);
        bundle.put(LASERS_COOLDOWN, lasersCooldown);

        super.storeInBundle( bundle );
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        canRevive = bundle.getBoolean(CAN_REVIVE);
        lasersCooldown = bundle.getInt(LASERS_COOLDOWN);

        super.restoreFromBundle( bundle );
    }

    @Override
    public void die(Object cause){
        if (canRevive) {
            for (Buff buff : buffs()){
                if (buff.type == Buff.buffType.NEGATIVE) buff.detach();
            }
            CellEmitter.get( pos ).start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );
            Buff.prolong(this, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
            this.HP = this.HT;
            canRevive = false;
            return;
        }
        Dungeon.level.drop(new StoneOfMadness(), pos ).sprite.drop();
        Dungeon.level.drop(new ElixirOfDragonsBlood(), pos ).sprite.drop();
        Dungeon.level.drop(new Life(), pos ).sprite.drop();
        super.die(cause);
    }

    @Override
    protected boolean canAttack( Char enemy ) {
        boolean canAttack = false;
         canAttack = (super.canAttack(enemy));
         if (!canAttack && new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos && spellcard != null) {
             canAttack = spellcard instanceof PhoenixShield && Random.Int(3) == 0 || spellcard instanceof PhoenixEye && Random.Int(3) == 0;
         }
         return canAttack;
    }

    protected boolean doAttack( Char enemy ) {

        if (Dungeon.level.adjacent( pos, enemy.pos )) {

            return super.doAttack( enemy );

        } else {

            if (sprite != null && (sprite.visible || enemy.sprite.visible)){
                sprite.zap( enemy.pos );
                return false;
            } else {
                zap();
                return true;
            }
        }
    }

    protected void zap() {
        spend( TIME_TO_ZAP );

        new BurningTrap().set(enemy.pos).activate();
        new Bomb().explode(enemy.pos, Random.Int(40, 50));
        if (enemy == Dungeon.heroine && !enemy.isAlive()) {
            Dungeon.fail( getClass() );
            GLog.n( Messages.get(this, "bolt_kill") );
        }
    }
    public void lasersAttack(){ // shoots 6 lasers, the angles between which are 60 degrees.
        int cell = Dungeon.level.randomCell();

        for (int i = 0; i < 6; i++) {
            Ballistica beam = new Ballistica(pos, cell, Ballistica.WONT_STOP);
            beam = beam.targetAtAngle(60 * i); //6 rays are created
            beam = new Ballistica(pos, beam.collisionPos, Ballistica.WONT_STOP);
            if (sprite != null && sprite.visible) {
                ((MokouSprite) sprite).laserZap(beam.collisionPos);
            } else {
                laserZap(beam.collisionPos);
            }
        }
    }
    public void laserZap(int cell){
        Ballistica beam = new Ballistica(pos, cell, Ballistica.WONT_STOP);

        for (int pos : beam.subPath(1, beam.dist)) {

            GameScene.add( Blob.seed( pos, 1, Fire.class ) );

            Char ch = Actor.findChar( pos );
            if (ch == null) {
                continue;
            }

            if (hit( this, ch, true )) {
                ch.damage( Random.NormalIntRange( 50, 80 ), this );
                Buff.affect(ch, Burning.class).reignite(ch, 4f);

                if (!ch.isAlive() && ch == Dungeon.heroine) {
                    Dungeon.fail( getClass() );
                }
            } else {
                ch.sprite.showStatus( CharSprite.NEUTRAL,  ch.defenseVerb() );
            }
        }
    }

    public void onZapComplete() {
        zap();
        next();
    }
    public void onLaserZapComlete(int cell){ // passive action, don't waste mob time
        laserZap( cell );
    }

    @Override
    public void call() {
        next();
    }
}