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

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Adrenaline;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Drummer;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.FightInsteadOfMe;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.SonicEarthquake;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs.ElixirOfDragonsBlood;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfMadness;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AlarmTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.TeleportationTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RaikoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Raiko extends MobWithSpellcard {

    {
        spriteClass = RaikoSprite.class;

        HP = HT = 600;
        defenseSkill = 28;
        EXP = 20;
        maxLvl = 30;

        flying = true;

        spellcardsDefaultList.add(Drummer.class);
        spellcardsDefaultList.add(FightInsteadOfMe.class);
        spellcardsDefaultList.add(SonicEarthquake.class);

        mobRarity = RARE_RARITY;
        if (Statistics.difficulty <= 2) numberOfCards = 3;
        else if (Statistics.difficulty <= 4) numberOfCards = 4;
        else numberOfCards = 5;

        properties.add(Property.YOKAI);
        properties.add(Property.NOT_EXTERMINABLE);
        properties.add(Property.MINIBOSS);

        loot = new LifeFragment();
        lootChance = 0.1f;

        immunities.add(Paralysis.class);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(95, 125);
    }

    @Override
    public int attackSkill(Char target) {
        return 35;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(18,26);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            damage *= 3f;
            Sample.INSTANCE.play( Assets.Sounds.BLAST );
            GLog.w(Messages.get(this, "punisher"));
            Camera.main.shake( 20, 1f );
        }
        return damage;
    }

    @Override
    public void die(Object cause) {
        Dungeon.level.drop(new PotionOfExperience().quantity(1), pos ).sprite.drop();
        Dungeon.level.drop(new PotionOfExperience().quantity(2), pos ).sprite.drop();
        Dungeon.level.drop(new Life(), pos ).sprite.drop();

        super.die(cause);
    }

    @Override
    public boolean act(){
        if (isSpellcardOn){
            if (spellcard instanceof FightInsteadOfMe){
                if (!enemySeen) baseSpeed = 0.5f;
                else baseSpeed = 1f;
            if (enemy != null && enemy.equals(Dungeon.heroine)){
                    alarm(enemy.pos);
                    teleport();
                }
        }
        if (spellcard instanceof Drummer){
            //baseSpeed = 4f;
            Buff.prolong(this, AnkhInvulnerability.class, 2f);
            Buff.prolong(this, Hisou.class, 2f);
            //Buff.prolong(this, Adrenaline.class, 2f);
            }

        }
        else {

        }

        return super.act();
    }

    private void alarm(int pos){
        for (Mob mob : Dungeon.level.mobs) {
            mob.beckon( pos );
        }
        CellEmitter.center( pos ).start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
        Sample.INSTANCE.play( Assets.Sounds.ALERT );
    }
    private void teleport(){
        if (ScrollOfTeleportation.teleportChar(this)) {
            if (this.state == this.HUNTING) {
                this.state = this.WANDERING;
            }
        }
    }
}