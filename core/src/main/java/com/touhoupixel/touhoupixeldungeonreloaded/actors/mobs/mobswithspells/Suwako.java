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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.StormCloud;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.IronRings;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Roots;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.BadWeather;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.FriendlyTrio;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.HitAndRun;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Thickets;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Splash;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.EarthParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.SuwakoRelic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs.ElixirOfDragonsBlood;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.spells.AquaBlast;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfMadness;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Blindweed;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Dreamfoil;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Fadeleaf;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Firebloom;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Icecap;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Sorrowmoss;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Starflower;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Stormvine;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Sungrass;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SuwakoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class Suwako extends MobWithSpellcard {
    private static final float TIME_TO_ZAP	= 0.66f;

    {
        spriteClass = SuwakoSprite.class;

        HP = HT = 800;
        defenseSkill = 27;
        EXP = 16;
        maxLvl = 35;

        spellcardsDefaultList.add(BadWeather.class);
        spellcardsDefaultList.add(FriendlyTrio.class);
        spellcardsDefaultList.add(Thickets.class);

        flying = true;
        properties.add(Property.GOD);
        properties.add(Property.MINIBOSS);
        properties.add(Property.NOT_EXTERMINABLE);
        mobRarity = RARE_RARITY;
        if (Statistics.difficulty <= 2) numberOfCards = 3;
        else if (Statistics.difficulty <= 4) numberOfCards = 4;
        else numberOfCards = 5;

        properties.add(Property.FUMO);
        //used for fumo lover buff
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(93, 141);
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(22, 32);
    }

    @Override
    public void fx(boolean on) {
        if (on) sprite.add(CharSprite.State.SPELLCARD_IS_ON_WATER);
        else sprite.remove(CharSprite.State.SPELLCARD_IS_ON_WATER);
    }
    @Override
    public void die(Object cause){
        Dungeon.level.drop(new AquaBlast().quantity(10), pos ).sprite.drop();
        for (int i = 0; i < 15; i++){
            int seed = Random.Int(11);
            switch (seed){
                case 0:
                    Dungeon.level.drop(new Blindweed.Seed(), pos ).sprite.drop();
                    break;
                case 1:
                    Dungeon.level.drop(new Dreamfoil.Seed(), pos ).sprite.drop();
                    break;
                case 2:
                    Dungeon.level.drop(new Earthroot.Seed(), pos ).sprite.drop();
                    break;
                case 3:
                    Dungeon.level.drop(new Fadeleaf.Seed(), pos ).sprite.drop();
                    break;
                case 4:
                    Dungeon.level.drop(new Firebloom.Seed(), pos ).sprite.drop();
                    break;
                case 5:
                    Dungeon.level.drop(new Icecap.Seed(), pos ).sprite.drop();
                    break;
                case 6:
                    Dungeon.level.drop(new Sorrowmoss.Seed(), pos ).sprite.drop();
                    break;
                case 7:
                    Dungeon.level.drop(new Starflower.Seed(), pos ).sprite.drop();
                    break;
                case 8:
                    Dungeon.level.drop(new Stormvine.Seed(), pos ).sprite.drop();
                    break;
                case 9:
                    Dungeon.level.drop(new Sungrass.Seed(), pos ).sprite.drop();
                    break;
                default:
                    Dungeon.level.drop(new Swiftthistle.Seed(), pos ).sprite.drop();
                    break;
            }
        }
        Dungeon.level.drop(new Life(), pos ).sprite.drop();
        Dungeon.level.drop(new SuwakoRelic(), pos ).sprite.drop();
        super.die(cause);
    }

    @Override
    public void damage(int dmg, Object src) {
        int waterCells = 0;
        for (int i : PathFinder.NEIGHBOURS9) {
            if (Dungeon.level.map[pos + i] == Terrain.WATER) {
                waterCells++;
            }
        }
        if (waterCells > 0) dmg = Math.round(dmg * (6 - waterCells) / 6f);

        super.damage(dmg, src);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (buff(IronRings.class) != null) damage *= 2;
        if (Dungeon.level.water[enemy.pos] && spellcard instanceof BadWeather){
            Buff.prolong(enemy, Hex.class, 5f);
            Buff.prolong(enemy, Vertigo.class, 5f);
            Buff.prolong(enemy, Vulnerable.class, 5f);
        }
        return damage;
    }
    @Override
    protected boolean canAttack( Char enemy ) {
        return (new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos && spellcard instanceof Thickets) || super.canAttack(enemy);
    }
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
    protected void zap() {
        spend( TIME_TO_ZAP ); // 1.5 shots per turn
        if (hit( this, enemy, true )) {
            int type = Random.Int(11);
            switch (type){
                case 0:
                    Blindweed bw = new Blindweed();
                    bw.pos = enemy.pos;
                    bw.activate(enemy);
                    break;
                case 1:
                    Buff.affect(enemy, Drowsy.class);
                    break;
                case 2:
                    Buff.prolong(enemy, Roots.class, Roots.DURATION);
                    CellEmitter.bottom( enemy.pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
                    break;
                case 3:
                    Fadeleaf fl = new Fadeleaf();
                    fl.pos = enemy.pos;
                    fl.activate(enemy);
                    break;
                case 4:
                    Firebloom fb = new Firebloom();
                    fb.pos = enemy.pos;
                    fb.activate(enemy);
                    break;
                case 5:
                    Icecap ic = new Icecap();
                    ic.pos = enemy.pos;
                    ic.activate(enemy);
                    break;
                case 6:
                    Sorrowmoss sm = new Sorrowmoss();
                    sm.pos = enemy.pos;
                    sm.activate(enemy);
                    break;
                case 7:
                    Buff.prolong(enemy, Hex.class, Hex.DURATION);
                    break;
                case 8:
                    Stormvine sv = new Stormvine();
                    sv.pos = enemy.pos;
                    sv.activate(enemy);
                    break;
                case 9:
                    Sungrass sg = new Sungrass();
                    sg.pos = enemy.pos;
                    sg.activate(enemy);
                    break;
                case 10:
                    Buff.prolong(enemy, Slow.class, Slow.DURATION);

            }
            if (enemy == Dungeon.heroine && !enemy.isAlive()) {
                Dungeon.fail( getClass() );
                GLog.n( Messages.get(this, "bolt_kill") );
            }
        } else {
            enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
        }
    }

    public void call() {
        next();
    }
    public void onZapComplete() {
        zap();
        next();
    }

    @Override
    protected boolean act() {
        if (isSpellcardOn && enemy == null) baseSpeed = 4f;
        if (spellcard instanceof BadWeather){
            if (enemy != null && fieldOfView != null && fieldOfView[enemy.pos]){
                if (Random.Int(5) == 0){
                    Splash.at( DungeonTilemap.tileCenterToWorld( enemy.pos ), -PointF.PI/2, PointF.PI/2, 0x5bc1e3, 100, 0.01f);
                    int centerVolume = 240;
                    GameScene.add( Blob.seed( enemy.pos, centerVolume, StormCloud.class ) );
                }
                for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
                    int cell = enemy.pos + PathFinder.NEIGHBOURS8[i];
                    if (Dungeon.level.water[cell] && Actor.findChar(cell) == null){
                        ScrollOfTeleportation.appear(this, cell);
                        Dungeon.level.occupyCell(this);
                        break;
                    }
                }
            }
        }
        if (spellcard instanceof Thickets){
            Buff.prolong(this, Haste.class, 2f);
        }
        return super.act();
    }


}