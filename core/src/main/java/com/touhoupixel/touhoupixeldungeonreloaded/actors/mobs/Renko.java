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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RenkoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Renko extends Mob implements Callback {

    private static final float TIME_TO_ZAP	= 1f;

    {
        spriteClass = RenkoSprite.class;

        HP = HT = 229;
        defenseSkill = 37;
        EXP = 22;
        maxLvl = 75;

        properties.add(Property.HUMAN);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new LifeFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(25, 31);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    protected boolean canAttack( Char enemy ) {
        return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
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

    //used so resistances can differentiate between melee and magical attacks
    public static class DarkBolt{}

    protected void zap() {
        spend( TIME_TO_ZAP );

        if (hit( this, enemy, true )) {
            //TODO would be nice for this to work on ghost/statues too
            if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
                if (Statistics.difficulty > 2) {
                    Buff.prolong(this, Might.class, Might.DURATION);
                }
                if (Statistics.difficulty > 4) {
                    Buff.prolong(this, SuperHard.class, SuperHard.DURATION);
                }
                switch (Random.Int(5)) {
                    case 0:
                    default:
                        if (enemy.HP > 5) {
                            enemy.HP = enemy.HP / 4;
                            Sample.INSTANCE.play(Assets.Sounds.CURSED);
                            CellEmitter.get(enemy.pos).burst(ShadowParticle.UP, 5);
                            GLog.w(Messages.get(this, "super_torment"));
                        }
                        break;
                    case 1:
                        Buff.prolong(enemy, MeleeNullify.class, MeleeNullify.DURATION);
                        break;
                    case 2:
                        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
                            ArrayList<Item> gazer = new ArrayList<>();
                            for (Item i : Dungeon.heroine.belongings) {
                                if (!i.unique && (i instanceof Potion || i instanceof Herb)) {
                                    gazer.add(i);
                                }
                            }
                            if (!gazer.isEmpty()) {
                                Item hypnotize = Random.element(gazer).detach(Dungeon.heroine.belongings.backpack);
                                GLog.w(Messages.get(Reisen.class, "gaze"));
                                Dungeon.heroine.sprite.emitter().start(Speck.factory(Speck.BUBBLE), 0.2f, 3);
                                Sample.INSTANCE.play(Assets.Sounds.LULLABY);
                                if (hypnotize instanceof Potion) {
                                    ((Potion) hypnotize).drink(Dungeon.heroine);
                                }
                                if (hypnotize instanceof Herb) {
                                    hypnotize.execute(Dungeon.heroine);
                                }
                            } else {
                                GLog.w(Messages.get(Reisen.class, "failtogaze"));
                            }
                        }
                        break;
                    case 3:
                        ScrollOfTeleportation.teleportChar(enemy);
                        break;
                    case 4:
                        Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
                        break;
                }
            }

            if (enemy == Dungeon.heroine && !enemy.isAlive()) {
                Dungeon.fail( getClass() );
                GLog.n( Messages.get(this, "bolt_kill") );
            }
        } else {
            enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
        }
    }

    public void onZapComplete() {
        zap();
        next();
    }

    @Override
    public void call() {
        next();
    }
}