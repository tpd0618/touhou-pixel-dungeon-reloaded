package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inaccurate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.Shopkeeper;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Beam;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.TargetedCell;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.spells.ReclaimTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DisarmingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeineSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ReisenSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TewiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Tewi extends Mob {

    {
        spriteClass = Math.random() < 0.5 ? KeineSprite.class : ReisenSprite.class;

        HP = HT = 72;
        defenseSkill = 12;
        EXP = 5;
        maxLvl = 20;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new ReclaimTrap();
        lootChance = 0.13f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(19, 31);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(8, 14);
    }

    @Override
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && this.state != SLEEPING && this.state != FLEEING) {
            Char closestEnemy = null;
            int closestDistance = Integer.MAX_VALUE;

            for (Char target : Actor.chars()) {
                if (target != this && !(target instanceof Hero) && !(target instanceof Shopkeeper) && target.isAlive() && Dungeon.level.heroFOV[target.pos]) {
                    int distance = Dungeon.level.distance(pos, target.pos);
                    if (distance < closestDistance) {
                        closestEnemy = target;
                        closestDistance = distance;
                    }
                }
            }

            if (closestEnemy instanceof Mob) {
                sprite.parent.add(new Beam.HealthRay(sprite.destinationCenter(), closestEnemy.sprite.destinationCenter()));
                Buff.prolong(closestEnemy, DoubleSpeed.class, DoubleSpeed.DURATION);
            }
        }
        return super.act();
    }
}