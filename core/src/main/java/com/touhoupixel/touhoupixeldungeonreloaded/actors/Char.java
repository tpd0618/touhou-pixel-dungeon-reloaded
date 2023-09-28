/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeonreloaded.actors;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Adrenaline;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ArcaneArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Barkskin;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BrainWash;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Charm;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Chill;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doom;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Dread;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FireImbue;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FloatSlayer;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Frost;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FrostImbue;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Fury;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.GhostHalf;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeatRiser;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicalSleep;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Randomizer;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ShieldBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Stamina;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SupernaturalBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Terror;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.glyphs.AntiMagic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfCleansing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfElements;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfRetribution;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfChallenge;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.touhoupixel.touhoupixeldungeonreloaded.items.vials.Vial;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.DamageWand;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.enchantments.Blocking;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.enchantments.Grim;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.Chasm;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.Door;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GrimTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.Trap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.Arrays;
import java.util.HashSet;

public abstract class Char extends Actor {

    public int pos = 0;

    public CharSprite sprite;

    public int HT;
    public int HP;

    protected float baseSpeed	= 1;
    protected PathFinder.Path path;

    public int paralysed	    = 0;
    public boolean rooted		= false;
    public boolean flying		= false;
    public int invisible		= 0;

    //these are relative to the hero
    public enum Alignment{
        ENEMY,
        NEUTRAL,
        ALLY
    }
    public Alignment alignment;

    public int viewDistance	= 8;

    public boolean[] fieldOfView = null;

    private HashSet<Buff> buffs = new HashSet<>();

    @Override
    protected boolean act() {
        if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
            fieldOfView = new boolean[Dungeon.level.length()];
        }
        Dungeon.level.updateFieldOfView( this, fieldOfView );

        //throw any items that are on top of an immovable char
        if (properties().contains(Property.IMMOVABLE)){
            throwItems();
        }
        return false;
    }

    protected void throwItems(){
        Heap heap = Dungeon.level.heaps.get( pos );
        if (heap != null && heap.type == Heap.Type.HEAP) {
            int n;
            do {
                n = pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
            } while (!Dungeon.level.passable[n] && !Dungeon.level.avoid[n]);
            Dungeon.level.drop( heap.pickUp(), n ).sprite.drop( pos );
        }
    }

    public String name(){
        return Messages.get(this, "name");
    }

    public boolean canInteract(Char c){
        if (Dungeon.level.adjacent( pos, c.pos )){
            return true;
        } else {
            return false;
        }
    }

    //swaps places by default
    public boolean interact(Char c){

        //don't allow char to swap onto hazard unless they're flying
        //you can swap onto a hazard though, as you're not the one instigating the swap
        if (!Dungeon.level.passable[pos] && !c.flying) {
            return true;
        }

        int curPos = pos;

        //can't swap places if one char has restricted movement
        if (rooted || c.rooted || buff(Vertigo.class) != null || c.buff(Vertigo.class) != null){
            return true;
        }

        moveSprite( pos, c.pos );
        move( c.pos );

        c.sprite.move( c.pos, curPos );
        c.move( curPos );

        c.spend( 1 / c.speed() );

        if (c == Dungeon.heroine){
            Dungeon.heroine.busy();
        }

        return true;
    }

    protected boolean moveSprite( int from, int to ) {

        if (sprite.isVisible() && (Dungeon.level.heroFOV[from] || Dungeon.level.heroFOV[to])) {
            sprite.move( from, to );
            return true;
        } else {
            sprite.turnTo(from, to);
            sprite.place( to );
            return true;
        }
    }

    public void hitSound( float pitch ){
        Sample.INSTANCE.play(Assets.Sounds.HIT, 1, pitch);
    }

    public boolean blockSound( float pitch ) {
        return false;
    }

    protected static final String POS       = "pos";
    protected static final String TAG_HP    = "HP";
    protected static final String TAG_HT    = "HT";
    protected static final String TAG_SHLD  = "SHLD";
    protected static final String BUFFS	    = "buffs";

    @Override
    public void storeInBundle( Bundle bundle ) {

        super.storeInBundle( bundle );

        bundle.put( POS, pos );
        bundle.put( TAG_HP, HP );
        bundle.put( TAG_HT, HT );
        bundle.put( BUFFS, buffs );
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {

        super.restoreFromBundle( bundle );

        pos = bundle.getInt( POS );
        HP = bundle.getInt( TAG_HP );
        HT = bundle.getInt( TAG_HT );

        for (Bundlable b : bundle.getCollection( BUFFS )) {
            if (b != null) {
                ((Buff)b).attachTo( this );
            }
        }
    }

    final public boolean attack( Char enemy ){
        return attack(enemy, 1f, 0f, 1f);
    }

    public boolean attack( Char enemy, float dmgMulti, float dmgBonus, float accMulti ) {

        if (enemy == null) return false;

        boolean visibleFight = Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[enemy.pos];

        if (enemy.isInvulnerable(getClass())) {

            if (visibleFight) {
                enemy.sprite.showStatus( CharSprite.POSITIVE, Messages.get(this, "invulnerable") );

                Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1f, Random.Float(0.96f, 1.05f));
            }

            return false;

        } else if (hit( this, enemy, accMulti )) {

            if (enemy.buff(SupernaturalBorder.class) != null){
                Buff.detach(enemy, SupernaturalBorder.class);
                Sample.INSTANCE.play( Assets.Sounds.EVOKE );
                enemy.sprite.flash();
                return false;
            }

            int dr = enemy.drRoll();

            Barkskin bark = enemy.buff(Barkskin.class);
            if (bark != null)   dr += Random.NormalIntRange( 0 , bark.level() );

            Blocking.BlockBuff block = enemy.buff(Blocking.BlockBuff.class);
            if (block != null)  dr += block.blockingRoll();

            if (this instanceof Hero){
                Hero h = (Hero)this;
            }

            int dmg;
            dmg = damageRoll();

            dmg = Math.round(dmg*dmgMulti);

            if (buff( Fury.class ) != null) {
                dmg *= 1.5f;
            }

            dmg += dmgBonus;

            if (enemy.buff(ScrollOfChallenge.ChallengeArena.class) != null){
                dmg *= 0.67f;
            }

            int effectiveDamage = enemy.defenseProc( this, dmg );
            effectiveDamage = Math.max( effectiveDamage - dr, 0 );

            if ( enemy.buff( Vulnerable.class ) != null){
                effectiveDamage *= 1.33f;
            }

            effectiveDamage = attackProc( enemy, effectiveDamage );

            if (visibleFight) {
                if (effectiveDamage > 0 || !enemy.blockSound(Random.Float(0.96f, 1.05f))) {
                    hitSound(Random.Float(0.87f, 1.15f));


                }
            }

            // If the enemy is already dead, interrupt the attack.
            // This matters as defence procs can sometimes inflict self-damage, such as armor glyphs.
            if (!enemy.isAlive()){
                return true;
            }

            enemy.damage( effectiveDamage, this );

            if (buff(FireImbue.class) != null)  buff(FireImbue.class).proc(enemy);
            if (buff(FrostImbue.class) != null) buff(FrostImbue.class).proc(enemy);

            enemy.sprite.bloodBurstA( sprite.center(), effectiveDamage );
            enemy.sprite.flash();

            if (!enemy.isAlive() && visibleFight) {
                if (enemy == Dungeon.heroine) {

                    if (this == Dungeon.heroine) {
                        return true;
                    }

                    Dungeon.fail( getClass() );
                    GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );

                } else if (this == Dungeon.heroine) {
                    GLog.i( Messages.capitalize(Messages.get(Char.class, "defeat", enemy.name())) );
                }
            }

            return true;

        } else {

            if (visibleFight) {
                String defense = enemy.defenseVerb();
                enemy.sprite.showStatus( CharSprite.NEUTRAL, defense );

                //TODO enemy.defenseSound? currently miss plays for monks/crab even when they parry
                if (enemy.buff(GhostHalf.class) != null){
                    if (Dungeon.heroine.buff(GhostHalf.class).isSourceBomb()){
                        Buff.prolong(this, Blindness.class, 10f);
                        Buff.prolong(this, Cripple.class, 10f);
                        Buff.prolong(this, Vulnerable.class, 10f);
                        Buff.prolong(this, Weakness.class, 10f);
                        Buff.prolong(this, Hex.class, 10f);
                    }
                    else{
                        Buff.prolong(this, Blindness.class, Blindness.DURATION/3.33f);
                        Buff.prolong(this, Cripple.class, Cripple.DURATION/3.33f);
                    }
                    if (Statistics.card53){
                        Dungeon.heroine.HP = Math.min((int)(Dungeon.heroine.HP + Dungeon.heroine.HT*0.02f), Dungeon.heroine.HT);
                    }
                }
                Sample.INSTANCE.play(Assets.Sounds.MISS);
            }

            return false;

        }
    }

    public static int INFINITE_ACCURACY = 1_000_000;
    public static int INFINITE_EVASION = 1_000_000;

    final public static boolean hit( Char attacker, Char defender, boolean magic ) {
        return hit(attacker, defender, magic ? 2f : 1f);
    }

    public static boolean hit( Char attacker, Char defender, float accMulti ) {
        float acuStat = attacker.attackSkill( defender );
        float defStat = defender.defenseSkill( attacker );

        //if accuracy or evasion are large enough, treat them as infinite.
        //note that infinite evasion beats infinite accuracy
        if (defStat >= INFINITE_EVASION){
            return false;
        } else if (acuStat >= INFINITE_ACCURACY){
            return true;
        }

        float acuRoll = Random.Float( acuStat );
        if (attacker.buff(Bless.class) != null) acuRoll *= 1.25f;
        if (attacker.buff(Doublerainbow.class) != null) acuRoll *= 1.45f;
        if (attacker.buff(HeatRiser.class) != null) acuRoll *= 1.5f; //heat riser
        if (attacker.buff(Randomizer.class) != null) acuRoll *= 0.5f; //randomizer
        if (attacker.buff(Hex.class) != null) acuRoll *= 0.8f;

        float defRoll = Random.Float( defStat );
        if (defender.buff(Bless.class) != null) defRoll *= 1.25f;
        if (defender.buff(Doublerainbow.class) != null) defRoll *= 1.45f;
        if (attacker.buff(HeatRiser.class) != null) defRoll *= 1.5f; //heat riser
        if (attacker.buff(Randomizer.class) != null) defRoll *= 0.5f; //randomizer
        if (defender.buff(Hex.class) != null) defRoll *= 0.8f;

        return (acuRoll * accMulti) >= defRoll;
    }

    public int attackSkill( Char target ) {
        return 0;
    }

    public int defenseSkill( Char enemy ) {
        return 0;
    }

    public String defenseVerb() {
        return Messages.get(this, "def_verb");
    }

    public int drRoll() {
        return 0;
    }

    public int damageRoll() {
        return 1;
    }

    //TODO it would be nice to have a pre-armor and post-armor proc.
    // atm attack is always post-armor and defence is already pre-armor

    public int attackProc( Char enemy, int damage ) {
        if (buff(Weakness.class) != null ) {
            damage *= 0.67f;
        }
        if (buff(Might.class) != null ) {
            damage *= 1.25f;
        }
        if (buff(Hisou.class) != null && !enemy.flying ){
            damage *= 1.5f;
        }
        if (buff(FloatSlayer.class) != null && enemy.flying ){
            damage *= 1.4f;
        }
        //todo
        return damage;
    }

    public int defenseProc( Char enemy, int damage ) {
        return damage;
    }

    public float speed() {
        float speed = baseSpeed;
        if ( buff( Cripple.class ) != null ) speed /= 2f;
        if ( buff( Stamina.class ) != null) speed *= 1.5f;
        if ( buff( Adrenaline.class ) != null) speed *= 2f;
        if ( buff( Haste.class ) != null) speed *= 3f;
        if ( buff( Dread.class ) != null) speed *= 2f;
        return speed;
    }

    //used so that buffs(Shieldbuff.class) isn't called every time unnecessarily
    private int cachedShield = 0;
    public boolean needsShieldUpdate = true;

    public int shielding(){
        if (!needsShieldUpdate){
            return cachedShield;
        }

        cachedShield = 0;
        for (ShieldBuff s : buffs(ShieldBuff.class)){
            cachedShield += s.shielding();
        }
        needsShieldUpdate = false;
        return cachedShield;
    }

    public void damage( int dmg, Object src ) {

        if (!isAlive() || dmg < 0) {
            return;
        }

        if (buff(SuperHard.class) != null ) {
            dmg = 1;
        }

        if(isInvulnerable(src.getClass())){
            sprite.showStatus(CharSprite.POSITIVE, Messages.get(this, "invulnerable"));
            return;
        }

        Terror t = buff(Terror.class);
        if (t != null){
            t.recover();
        }
        Dread d = buff(Dread.class);
        if (d != null){
            d.recover();
        }
        Charm c = buff(Charm.class);
        if (c != null){
            c.recover(src);
        }
        if (this.buff(Frost.class) != null){
            Buff.detach( this, Frost.class );
        }
        if (this.buff(MagicalSleep.class) != null){
            Buff.detach(this, MagicalSleep.class);
        }
        if (this.buff(Doom.class) != null && !isImmune(Doom.class)){
            dmg *= 2;
        }

        Class<?> srcClass = src.getClass();
        if (isImmune( srcClass )) {
            dmg = 0;
        } else {
            dmg = Math.round( dmg * resist( srcClass ));
        }

        //TODO improve this when I have proper damage source logic
        if (AntiMagic.RESISTS.contains(src.getClass()) && buff(ArcaneArmor.class) != null){
            dmg -= Random.NormalIntRange(0, buff(ArcaneArmor.class).level());
            if (dmg < 0) dmg = 0;
        }

        if (buff( Paralysis.class ) != null) {
            buff( Paralysis.class ).processDamage(dmg);
        }

        int shielded = dmg;
        //FIXME: when I add proper damage properties, should add an IGNORES_SHIELDS property to use here.
        if (!(src instanceof Hunger)){
            for (ShieldBuff s : buffs(ShieldBuff.class)){
                dmg = s.absorbDamage(dmg);
                if (dmg == 0) break;
            }
        }
        shielded -= dmg;
        HP -= dmg;

        if (sprite != null) {
            sprite.showStatus(HP > HT / 2 ?
                            CharSprite.WARNING :
                            CharSprite.NEGATIVE,
                    Integer.toString(dmg + shielded));
        }

        if (HP < 0) HP = 0;

        if (!isAlive()) {
            die(src);
        }
    }

    public void destroy() {
        HP = 0;
        Actor.remove( this );

        for (Char ch : Actor.chars().toArray(new Char[0])){
            if (ch.buff(Charm.class) != null && ch.buff(Charm.class).object == id()){
                ch.buff(Charm.class).detach();
            }
            if (ch.buff(Dread.class) != null && ch.buff(Dread.class).object == id()){
                ch.buff(Dread.class).detach();
            }
            if (ch.buff(Terror.class) != null && ch.buff(Terror.class).object == id()){
                ch.buff(Terror.class).detach();
            }
        }
    }

    public void die( Object src ) {
        destroy();
        if (src != Chasm.class) sprite.die();
    }

    //we cache this info to prevent having to call buff(...) in isAlive.
    //This is relevant because we call isAlive during drawing, which has both performance
    //and thread coordination implications
    public boolean deathMarked = false;

    public boolean isAlive() {
        return HP > 0 || deathMarked;
    }

    @Override
    protected void spend( float time ) {

        float timeScale = 1f;
        if (buff( Slow.class ) != null) {
            timeScale *= 0.5f;
            //slowed and chilled do not stack
        } else if (buff( Chill.class ) != null) {
            timeScale *= buff( Chill.class ).speedFactor();
        }
        if (Dungeon.isChallenged(Challenges.DOUBLE_SPOILER)) {
            if (buff(DoubleSpeed.class) != null && this instanceof Hero) {
                timeScale *= 1.5f;
            }
            if (buff(DoubleSpeed.class) != null && this instanceof Mob) {
                timeScale *= 2.5f;
            }
        } else {
            if (buff(DoubleSpeed.class) != null) {
                timeScale *= 2.0f;
            }
        }

        super.spend( time / timeScale );
    }

    public synchronized HashSet<Buff> buffs() {
        return new HashSet<>(buffs);
    }

    @SuppressWarnings("unchecked")
    //returns all buffs assignable from the given buff class
    public synchronized <T extends Buff> HashSet<T> buffs( Class<T> c ) {
        HashSet<T> filtered = new HashSet<>();
        for (Buff b : buffs) {
            if (c.isInstance( b )) {
                filtered.add( (T)b );
            }
        }
        return filtered;
    }

    @SuppressWarnings("unchecked")
    //returns an instance of the specific buff class, if it exists. Not just assignable
    public synchronized  <T extends Buff> T buff( Class<T> c ) {
        for (Buff b : buffs) {
            if (b.getClass() == c) {
                return (T)b;
            }
        }
        return null;
    }

    public synchronized boolean isCharmedBy( Char ch ) {
        int chID = ch.id();
        for (Buff b : buffs) {
            if (b instanceof Charm && ((Charm)b).object == chID) {
                return true;
            }
        }
        return false;
    }

    public synchronized void add( Buff buff ) {

        if (buff(PotionOfCleansing.Cleanse.class) != null) { //cleansing buff
            if (buff.type == Buff.buffType.NEGATIVE
                    && !(buff instanceof AllyBuff)){
                return;
            }
        }

        buffs.add( buff );
        if (Actor.chars().contains(this)) Actor.add( buff );

        if (sprite != null && buff.announced)
            switch(buff.type){
                case POSITIVE:
                    sprite.showStatus(CharSprite.POSITIVE, buff.toString());
                    break;
                case NEGATIVE:
                    sprite.showStatus(CharSprite.NEGATIVE, buff.toString());
                    break;
                case NEUTRAL: default:
                    sprite.showStatus(CharSprite.NEUTRAL, buff.toString());
                    break;
            }

    }

    public synchronized void remove( Buff buff ) {

        buffs.remove( buff );
        Actor.remove( buff );

    }

    public synchronized void remove( Class<? extends Buff> buffClass ) {
        for (Buff buff : buffs( buffClass )) {
            remove( buff );
        }
    }

    @Override
    protected synchronized void onRemove() {
        for (Buff buff : buffs.toArray(new Buff[buffs.size()])) {
            buff.detach();
        }
    }

    public synchronized void updateSpriteState() {
        for (Buff buff:buffs) {
            buff.fx( true );
        }
    }

    public float stealth() {
        return 0;
    }

    public final void move( int step ) {
        move( step, true );
    }

    //travelling may be false when a character is moving instantaneously, such as via teleportation
    public void move( int step, boolean travelling ) {

        if (travelling && Dungeon.level.adjacent( step, pos ) && buff( Vertigo.class ) != null) {
            sprite.interruptMotion();
            int newPos = pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
            if (!(Dungeon.level.passable[newPos] || Dungeon.level.avoid[newPos])
                    || (properties().contains(Property.NONE) && !Dungeon.level.openSpace[newPos])
                    || Actor.findChar( newPos ) != null)
                return;
            else {
                sprite.move(pos, newPos);
                step = newPos;
            }
        }

        if (Dungeon.level.map[pos] == Terrain.OPEN_DOOR) {
            Door.leave( pos );
        }

        pos = step;

        if (this != Dungeon.heroine) {
            sprite.visible = Dungeon.level.heroFOV[pos];
        }

        Dungeon.level.occupyCell(this );
    }

    public int distance( Char other ) {
        return Dungeon.level.distance( pos, other.pos );
    }

    public void onMotionComplete() {
        //Does nothing by default
        //The main actor thread already accounts for motion,
        // so calling next() here isn't necessary (see Actor.process)
    }

    public void onAttackComplete() {
        next();
    }

    public void onOperateComplete() {
        next();
    }

    protected final HashSet<Class> resistances = new HashSet<>();

    //returns percent effectiveness after resistances
    //TODO currently resistances reduce effectiveness by a static 50%, and do not stack.
    public float resist( Class effect ){
        HashSet<Class> resists = new HashSet<>(resistances);
        for (Property p : properties()){
            resists.addAll(p.resistances());
        }
        for (Buff b : buffs()){
            resists.addAll(b.resistances());
        }

        float result = 1f;
        for (Class c : resists){
            if (c.isAssignableFrom(effect)){
                result *= 0.5f;
            }
        }
        return result * RingOfElements.resist(this, effect);
    }

    protected final HashSet<Class> immunities = new HashSet<>();

    public boolean isImmune(Class effect ){
        HashSet<Class> immunes = new HashSet<>(immunities);
        for (Property p : properties()){
            immunes.addAll(p.immunities());
        }
        for (Buff b : buffs()){
            immunes.addAll(b.immunities());
        }

        for (Class c : immunes){
            if (c.isAssignableFrom(effect)){
                return true;
            }
        }
        return false;
    }

    //similar to isImmune, but only factors in damage.
    //Is used in AI decision-making
    public boolean isInvulnerable( Class effect ){
        return false;
    }

    protected HashSet<Property> properties = new HashSet<>();

    public HashSet<Property> properties() {
        HashSet<Property> props = new HashSet<>(properties);
        //TODO any more of these and we should make it a property of the buff, like with resistances/immunities
        return props;
    }

    public enum Property{
        BOSS ( new HashSet<Class>( Arrays.asList(Drowsy.class, Paralysis.class)),
                new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class, BrainWash.class, Grim.class, GrimTrap.class, ScrollOfRetribution.class, ScrollOfPsionicBlast.class) )),
        MINIBOSS ( new HashSet<Class>(),
                new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class, BrainWash.class) )),
        MITAMA ( new HashSet<Class>(),
                new HashSet<Class>( Arrays.asList(DamageWand.class, Mob.class, Vial.class, Grim.class, Trap.class, ScrollOfRetribution.class, ScrollOfPsionicBlast.class) )),
        NONE,
        ELIXIR,
        IMMOVABLE,
        FUMO,

        //Mob property
        YOKAI,
        GOD,
        HUMAN,
        ANIMAL,
        WARP;

        private HashSet<Class> resistances;
        private HashSet<Class> immunities;

        Property(){
            this(new HashSet<Class>(), new HashSet<Class>());
        }

        Property( HashSet<Class> resistances, HashSet<Class> immunities){
            this.resistances = resistances;
            this.immunities = immunities;
        }

        public HashSet<Class> resistances(){
            return new HashSet<>(resistances);
        }

        public HashSet<Class> immunities(){
            return new HashSet<>(immunities);
        }

    }

    public static boolean hasProp( Char ch, Property p){
        return (ch != null && ch.properties().contains(p));
    }
}