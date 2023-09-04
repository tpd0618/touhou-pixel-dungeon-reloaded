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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Chains;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Pushing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfExorcismRod;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KomachiSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Komachi extends Mob {

    //they can only use their chains once
    private boolean chainsUsed = false;

    {
        spriteClass = KomachiSprite.class;

        HP = HT = 83;
        defenseSkill = 25;
        EXP = 14;
        maxLvl = 32;

        properties.add(Property.GOD);

        loot = PotionOfExorcismRod.class;
        lootChance = 0.08f;

        HUNTING = new Hunting();
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(12, 19);
    }

    private boolean chain(int target){
        if (chainsUsed || enemy.properties().contains(Property.IMMOVABLE))
            return false;

        Ballistica chain = new Ballistica(pos, target, Ballistica.PROJECTILE);

        if (chain.collisionPos != enemy.pos
                || chain.path.size() < 2
                || Dungeon.level.pit[chain.path.get(1)])
            return false;
        else {
            int newPos = -1;
            for (int i : chain.subPath(1, chain.dist)){
                if (!Dungeon.level.solid[i] && Actor.findChar(i) == null){
                    newPos = i;
                    break;
                }
            }

            if (newPos == -1){
                return false;
            } else {
                final int newPosFinal = newPos;
                this.target = newPos;

                if (sprite.visible || enemy.sprite.visible) {
                    yell(Messages.get(this, "distance"));
                    new Item().throwSound();
                    Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
                    if (Statistics.difficulty > 2) {
                        Buff.prolong(this, Hisou.class, Hisou.DURATION);
                    }
                    if (Statistics.difficulty > 4) {
                        Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION);
                    }
                    Sample.INSTANCE.play(Assets.Sounds.CHAINS);
                    sprite.parent.add(new Chains(sprite.center(), enemy.sprite.destinationCenter(), new Callback() {
                        public void call() {
                            Actor.addDelayed(new Pushing(enemy, enemy.pos, newPosFinal, new Callback() {
                                public void call() {
                                    pullEnemy(enemy, newPosFinal);
                                }
                            }), -1);
                            next();
                        }
                    }));
                } else {
                    pullEnemy(enemy, newPos);
                }
            }
        }
        chainsUsed = true;
        return true;
    }

    private void pullEnemy( Char enemy, int pullPos ){
        enemy.pos = pullPos;
        enemy.sprite.place(pullPos);
        Dungeon.level.occupyCell(enemy);
        if (enemy == Dungeon.heroine) {
            Dungeon.heroine.interrupt();
            Dungeon.observe();
            GameScene.updateFog();
        }
    }

    @Override
    public int attackSkill( Char target ) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    private final String CHAINSUSED = "chainsused";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(CHAINSUSED, chainsUsed);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        chainsUsed = bundle.getBoolean(CHAINSUSED);
    }

    private class Hunting extends Mob.Hunting{
        @Override
        public boolean act( boolean enemyInFOV, boolean justAlerted ) {
            enemySeen = enemyInFOV;

            if (!chainsUsed
                    && enemyInFOV
                    && !isCharmedBy( enemy )
                    && !canAttack( enemy )
                    && Dungeon.level.distance( pos, enemy.pos ) < 5


                    && chain(enemy.pos)){
                return !(sprite.visible || enemy.sprite.visible);
            } else {
                return super.act( enemyInFOV, justAlerted );
            }

        }
    }
}