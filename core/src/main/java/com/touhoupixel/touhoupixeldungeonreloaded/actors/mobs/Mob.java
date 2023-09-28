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
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Adrenaline;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Amok;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiShipBattery;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BrainWash;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Charm;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cool;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Corruption;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Dread;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.GoldCreation;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Happy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HighStress;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HomingBlade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inaccurate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MeleeNullify;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Powerful;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Pure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirthDone;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RouletteStop;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Sleep;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Terror;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YuukaRage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YuumaAbsorb;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.DirectableAlly;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.Sheep;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.Shopkeeper;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Surprise;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Gold;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.KindOfWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Torch;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.MasterThievesArmband;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfAccuracy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfWealth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfNamelessStory;
import com.touhoupixel.touhoupixeldungeonreloaded.items.stones.StoneOfAggression;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tickets.FiveStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tickets.FourStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tickets.ThreeStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.BulletDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.enchantments.Lucky;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku.darts.Dart;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.Chasm;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public abstract class Mob extends Char {

	{
		actPriority = MOB_PRIO;

		alignment = Alignment.ENEMY;
	}

	private static final String	TXT_DIED	= "You hear something died in the distance";

	protected static final String TXT_NOTICE1	= "?!";
	protected static final String TXT_RAGE		= "#$%^";
	protected static final String TXT_EXP		= "%+dEXP";

	public AiState SLEEPING     = new Sleeping();
	public AiState HUNTING		= new Hunting();
	public AiState WANDERING	= new Wandering();
	public AiState FLEEING		= new Fleeing();
	public AiState PASSIVE		= new Passive();
	public AiState state = SLEEPING;

	public Class<? extends CharSprite> spriteClass;

	protected int target = -1;

	public int defenseSkill = 0;

	public int EXP = 1;
	public int maxLvl = Hero.MAX_LEVEL;

	protected Char enemy;
	protected int enemyID = -1; //used for save/restore
	protected boolean enemySeen;
	protected boolean alerted = false;

	protected static final float TIME_TO_WAKE_UP = 1f;

	private static final String STATE	= "state";
	private static final String SEEN	= "seen";
	private static final String TARGET	= "target";
	private static final String MAX_LVL	= "max_lvl";

	private static final String ENEMY_ID	= "enemy_id";

	@Override
	public void storeInBundle( Bundle bundle ) {

		super.storeInBundle( bundle );

		if (state == SLEEPING) {
			bundle.put( STATE, Sleeping.TAG );
		} else if (state == WANDERING) {
			bundle.put( STATE, Wandering.TAG );
		} else if (state == HUNTING) {
			bundle.put( STATE, Hunting.TAG );
		} else if (state == FLEEING) {
			bundle.put( STATE, Fleeing.TAG );
		} else if (state == PASSIVE) {
			bundle.put( STATE, Passive.TAG );
		}
		bundle.put( SEEN, enemySeen );
		bundle.put( TARGET, target );
		bundle.put( MAX_LVL, maxLvl );

		if (enemy != null) {
			bundle.put(ENEMY_ID, enemy.id() );
		}
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {

		super.restoreFromBundle( bundle );

		String state = bundle.getString( STATE );
		if (state.equals( Sleeping.TAG )) {
			this.state = SLEEPING;
		} else if (state.equals( Wandering.TAG )) {
			this.state = WANDERING;
		} else if (state.equals( Hunting.TAG )) {
			this.state = HUNTING;
		} else if (state.equals( Fleeing.TAG )) {
			this.state = FLEEING;
		} else if (state.equals( Passive.TAG )) {
			this.state = PASSIVE;
		}

		enemySeen = bundle.getBoolean( SEEN );

		target = bundle.getInt( TARGET );

		if (bundle.contains(MAX_LVL)) maxLvl = bundle.getInt(MAX_LVL);

		if (bundle.contains(ENEMY_ID)) {
			enemyID = bundle.getInt(ENEMY_ID);
		}
	}

	//mobs need to remember their targets after every actor is added
	public void restoreEnemy(){
		if (enemyID != -1 && enemy == null) enemy = (Char)Actor.findById(enemyID);
	}

	public CharSprite sprite() {
		return Reflection.newInstance(spriteClass);
	}

	@Override
	protected boolean act() {

		if (Dungeon.isChallenged(Challenges.CALL_THE_SHOTS) && buff(Powerful.class) == null && buff(Cool.class) == null && buff(Pure.class) == null && buff(Happy.class) == null && !this.properties().contains(Char.Property.BOSS) && !(this instanceof Shopkeeper)){
			switch (Random.Int(4)) {
				case 0:
				default:
					Buff.prolong(this, Powerful.class, Powerful.DURATION*10000f);
					break;
				case 1:
					Buff.prolong(this, Cool.class, Cool.DURATION*10000f);
					break;
				case 2:
					Buff.prolong(this, Pure.class, Pure.DURATION*10000f);
					break;
				case 3:
					Buff.prolong(this, Happy.class, Happy.DURATION*10000f);
					break;
			}
		}

		super.act();

		boolean justAlerted = alerted;
		alerted = false;

		if (justAlerted){
			sprite.showAlert();
		} else {
			sprite.hideAlert();
			sprite.hideLost();
		}

		if (paralysed > 0) {
			enemySeen = false;
			spend( TICK );
			return true;
		}

		if (buff(Terror.class) != null || buff(Dread.class) != null ){
			state = FLEEING;
		}

		enemy = chooseEnemy();

		boolean enemyInFOV = enemy != null && enemy.isAlive() && fieldOfView[enemy.pos] && enemy.invisible <= 0;

		return state.act( enemyInFOV, justAlerted );
	}

	//FIXME this is sort of a band-aid correction for allies needing more intelligent behaviour
	protected boolean intelligentAlly = false;

	protected Char chooseEnemy() {

		Dread dread = buff( Dread.class );
		if (dread != null) {
			Char source = (Char)Actor.findById( dread.object );
			if (source != null) {
				return source;
			}
		}

		Terror terror = buff( Terror.class );
		if (terror != null) {
			Char source = (Char)Actor.findById( terror.object );
			if (source != null) {
				return source;
			}
		}

		//if we are an alert enemy, auto-hunt a target that is affected by aggression, even another enemy
		if (alignment == Alignment.ENEMY && state != PASSIVE && state != SLEEPING) {
			if (enemy != null && enemy.buff(StoneOfAggression.Aggression.class) != null){
				state = HUNTING;
				return enemy;
			}
			for (Char ch : Actor.chars()) {
				if (ch != this && fieldOfView[ch.pos] &&
						ch.buff(StoneOfAggression.Aggression.class) != null) {
					state = HUNTING;
					return ch;
				}
			}
		}

		//find a new enemy if..
		boolean newEnemy = false;
		//we have no enemy, or the current one is dead/missing
		if ( enemy == null || !enemy.isAlive() || !Actor.chars().contains(enemy) || state == WANDERING) {
			newEnemy = true;
			//We are amoked and current enemy is the hero
		} else if (buff( Amok.class ) != null && enemy == Dungeon.heroine) {
			newEnemy = true;
			//We are charmed and current enemy is what charmed us
		} else if (buff(Charm.class) != null && buff(Charm.class).object == enemy.id()) {
			newEnemy = true;
		}

		//additionally, if we are an ally, find a new enemy if...
		if (!newEnemy && alignment == Alignment.ALLY){
			//current enemy is also an ally
			if (enemy.alignment == Alignment.ALLY){
				newEnemy = true;
				//current enemy is invulnerable
			} else if (enemy.isInvulnerable(getClass())){
				newEnemy = true;
			}
		}

		if ( newEnemy ) {

			HashSet<Char> enemies = new HashSet<>();

			//if we are amoked...
			if ( buff(Amok.class) != null) {
				//try to find an enemy mob to attack first.
				for (Mob mob : Dungeon.level.mobs)
					if (mob.alignment == Alignment.ENEMY && mob != this
							&& fieldOfView[mob.pos] && mob.invisible <= 0) {
						enemies.add(mob);
					}

				if (enemies.isEmpty()) {
					//try to find ally mobs to attack second.
					for (Mob mob : Dungeon.level.mobs)
						if (mob.alignment == Alignment.ALLY && mob != this
								&& fieldOfView[mob.pos] && mob.invisible <= 0) {
							enemies.add(mob);
						}

					if (enemies.isEmpty()) {
						//try to find the hero third
						if (fieldOfView[Dungeon.heroine.pos] && Dungeon.heroine.invisible <= 0) {
							enemies.add(Dungeon.heroine);
						}
					}
				}

				//if we are an ally...
			} else if ( alignment == Alignment.ALLY ) {
				//look for hostile mobs to attack
				for (Mob mob : Dungeon.level.mobs)
					if (mob.alignment == Alignment.ENEMY && fieldOfView[mob.pos]
							&& mob.invisible <= 0 && !mob.isInvulnerable(getClass()))
						//intelligent allies do not target mobs which are passive, wandering, or asleep
						if (!intelligentAlly ||
								(mob.state != mob.SLEEPING && mob.state != mob.PASSIVE && mob.state != mob.WANDERING)) {
							enemies.add(mob);
						}

				//if we are an enemy...
			} else if (alignment == Alignment.ENEMY) {
				//look for ally mobs to attack
				for (Mob mob : Dungeon.level.mobs)
					if (mob.alignment == Alignment.ALLY && fieldOfView[mob.pos] && mob.invisible <= 0)
						enemies.add(mob);

				//and look for the hero
				if (fieldOfView[Dungeon.heroine.pos] && Dungeon.heroine.invisible <= 0) {
					enemies.add(Dungeon.heroine);
				}

			}

			//do not target anything that's charming us
			Charm charm = buff( Charm.class );
			if (charm != null){
				Char source = (Char)Actor.findById( charm.object );
				if (source != null && enemies.contains(source) && enemies.size() > 1){
					enemies.remove(source);
				}
			}

			//neutral characters in particular do not choose enemies.
			if (enemies.isEmpty()){
				return null;
			} else {
				//go after the closest potential enemy, preferring the hero if two are equidistant
				Char closest = null;
				for (Char curr : enemies){
					if (closest == null
							|| Dungeon.level.distance(pos, curr.pos) < Dungeon.level.distance(pos, closest.pos)
							|| Dungeon.level.distance(pos, curr.pos) == Dungeon.level.distance(pos, closest.pos) && curr == Dungeon.heroine){
						closest = curr;
					}
				}
				return closest;
			}

		} else
			return enemy;
	}

	@Override
	public void add( Buff buff ) {
		super.add( buff );
		if (buff instanceof Amok || buff instanceof AllyBuff) {
			state = HUNTING;
		} else if (buff instanceof Terror || buff instanceof Dread) {
			state = FLEEING;
		} else if (buff instanceof Sleep) {
			state = SLEEPING;
			postpone( Sleep.SWS );
		}
	}

	@Override
	public void remove( Buff buff ) {
		super.remove( buff );
		if ((buff instanceof Terror && buff(Dread.class) == null)
				|| (buff instanceof Dread && buff(Terror.class) == null)) {
			if (enemySeen) {
				sprite.showStatus(CharSprite.NEGATIVE, Messages.get(this, "rage"));
				state = HUNTING;
			} else {
				state = WANDERING;
			}
		}
	}

	protected boolean canAttack( Char enemy ) {
		if (this.buff(BrainWash.class) != null){
			return false;
		} else if (Dungeon.level.adjacent( pos, enemy.pos )){
			return true;
		}
		return false;
	}

	protected boolean getCloser( int target ) {

		if (rooted || target == pos) {
			return false;
		}

		int step = -1;

		if (Dungeon.level.adjacent( pos, target )) {

			path = null;

			if (Actor.findChar( target ) == null
					&& (Dungeon.level.passable[target] || (flying && Dungeon.level.avoid[target]))
					&& (!Char.hasProp(this, Property.NONE) || Dungeon.level.openSpace[target])) {
				step = target;
			}

		} else {

			boolean newPath = false;
			//scrap the current path if it's empty, no longer connects to the current location
			//or if it's extremely inefficient and checking again may result in a much better path
			if (path == null || path.isEmpty()
					|| !Dungeon.level.adjacent(pos, path.getFirst())
					|| path.size() > 2*Dungeon.level.distance(pos, target))
				newPath = true;
			else if (path.getLast() != target) {
				//if the new target is adjacent to the end of the path, adjust for that
				//rather than scrapping the whole path.
				if (Dungeon.level.adjacent(target, path.getLast())) {
					int last = path.removeLast();

					if (path.isEmpty()) {

						//shorten for a closer one
						if (Dungeon.level.adjacent(target, pos)) {
							path.add(target);
							//extend the path for a further target
						} else {
							path.add(last);
							path.add(target);
						}

					} else {
						//if the new target is simply 1 earlier in the path shorten the path
						if (path.getLast() == target) {

							//if the new target is closer/same, need to modify end of path
						} else if (Dungeon.level.adjacent(target, path.getLast())) {
							path.add(target);

							//if the new target is further away, need to extend the path
						} else {
							path.add(last);
							path.add(target);
						}
					}

				} else {
					newPath = true;
				}

			}

			//checks if the next cell along the current path can be stepped into
			if (!newPath) {
				int nextCell = path.removeFirst();
				if (!Dungeon.level.passable[nextCell]
						|| (!flying && Dungeon.level.avoid[nextCell])
						|| (Char.hasProp(this, Property.NONE) && !Dungeon.level.openSpace[nextCell])
						|| Actor.findChar(nextCell) != null) {

					newPath = true;
					//If the next cell on the path can't be moved into, see if there is another cell that could replace it
					if (!path.isEmpty()) {
						for (int i : PathFinder.NEIGHBOURS8) {
							if (Dungeon.level.adjacent(pos, nextCell + i) && Dungeon.level.adjacent(nextCell + i, path.getFirst())) {
								if (Dungeon.level.passable[nextCell+i]
										&& (flying || !Dungeon.level.avoid[nextCell+i])
										&& (!Char.hasProp(this, Property.NONE) || Dungeon.level.openSpace[nextCell+i])
										&& Actor.findChar(nextCell+i) == null){
									path.addFirst(nextCell+i);
									newPath = false;
									break;
								}
							}
						}
					}
				} else {
					path.addFirst(nextCell);
				}
			}

			//generate a new path
			if (newPath) {
				//If we aren't hunting, always take a full path
				PathFinder.Path full = Dungeon.findPath(this, target, Dungeon.level.passable, fieldOfView, true);
				if (state != HUNTING){
					path = full;
				} else {
					//otherwise, check if other characters are forcing us to take a very slow route
					// and don't try to go around them yet in response, basically assume their blockage is temporary
					PathFinder.Path ignoreChars = Dungeon.findPath(this, target, Dungeon.level.passable, fieldOfView, false);
					if (ignoreChars != null && (full == null || full.size() > 2*ignoreChars.size())){
						//check if first cell of shorter path is valid. If it is, use new shorter path. Otherwise do nothing and wait.
						path = ignoreChars;
						if (!Dungeon.level.passable[ignoreChars.getFirst()]
								|| (!flying && Dungeon.level.avoid[ignoreChars.getFirst()])
								|| (Char.hasProp(this, Property.NONE) && !Dungeon.level.openSpace[ignoreChars.getFirst()])
								|| Actor.findChar(ignoreChars.getFirst()) != null) {
							return false;
						}
					} else {
						path = full;
					}
				}
			}

			if (path != null) {
				step = path.removeFirst();
			} else {
				return false;
			}
		}
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}

	protected boolean getFurther( int target ) {
		if (rooted || target == pos) {
			return false;
		}

		int step = Dungeon.flee( this, target, Dungeon.level.passable, fieldOfView, true );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateSpriteState() {
		super.updateSpriteState();
		if (Dungeon.heroine.buff(TimekeepersHourglass.timeFreeze.class) != null
				|| Dungeon.heroine.buff(Swiftthistle.TimeBubble.class) != null)
			sprite.add( CharSprite.State.PARALYSED );
	}

	public float attackDelay() {
		float delay = 1f;
		if ( buff(Adrenaline.class) != null) delay /= 1.5f;
		return delay;
	}

	protected boolean doAttack( Char enemy ) {

		if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
			sprite.attack( enemy.pos );
			return false;

		} else {
			attack( enemy );
			spend( attackDelay() );
			return true;
		}
	}

	@Override
	public boolean isInvulnerable(Class effect) {
		return Dungeon.heroine.buff(HinaCurse.class) != null && Dungeon.heroine.justMoved ||
				this instanceof BossHecatia && Statistics.eirinelixircount != 4;
	}

	@Override
	public void onAttackComplete() {
		attack( enemy );
		spend( attackDelay() );
		super.onAttackComplete();
	}

	@Override
	public int defenseSkill( Char enemy ) {
		if ( !surprisedBy(enemy)
				&& paralysed == 0
				&& !(alignment == Alignment.ALLY && enemy == Dungeon.heroine)) {
			return this.defenseSkill;
		} else {
			return 0;
		}
	}

	@Override
	public String defenseVerb() {
		if (Dungeon.isChallenged(Challenges.RE_BIRTH_DAY) && Random.Int(2) == 0 && buff(ReBirthDone.class) == null && !properties().contains(Property.BOSS) && !(this instanceof Wraith)){
			Buff.prolong(this, ReBirth.class, ReBirth.DURATION*10f);
		}

		return Messages.get(this, "def_verb");
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		if (this.buff(MeleeNullify.class) != null) {
			damage = 1;
		}

		if (buff(YuukaRage.class) != null){
			damage += 50;
		}

		if (buff(YuumaAbsorb.class) != null) {
			damage *= 3f;
		}

		if (Dungeon.isChallenged(Challenges.BLESSING_CHORD)) {
			damage += Statistics.goldPickedup/25+Statistics.enemiesSlain/50;
		}

		if (Statistics.difficulty == 1) {
			damage *= 0.85f;
		}

		if (Statistics.difficulty == 4) {
			damage *= 1.1f;
		}

		if (Statistics.difficulty == 5) {
			damage *= 1.2f;
		}

		if (Statistics.difficulty == 6) {
			damage *= 1.25f;
		}

		for (int i : PathFinder.NEIGHBOURS4) {
			if (this instanceof Meiling && enemy.pos == this.pos + i) {
				if (Statistics.difficulty > 4) {
					Buff.prolong(enemy, DismantlePressure.class, DismantlePressure.DURATION);
				} else {
					Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
				}
			}
		}

		damage += Statistics.timetrackstrup/48;

		return damage;
	}

	@Override
	public int defenseProc( Char enemy, int damage ) {

		if (this.buff(HighStress.class) != null){
			this.HP = 1;
		}

		if (this instanceof BossSeija && Dungeon.heroine.buff(RouletteStop.class) == null){
			Dungeon.heroine.HP /= 2;
			GameScene.flash(-65536);
		}

		if (this instanceof BossSeija && Dungeon.heroine.buff(RouletteStop.class) == null){
			damage *= 0.25;
		}

		if (enemy instanceof Hero
				&& ((Hero) enemy).belongings.weapon() instanceof MissileWeapon){
		}

		if (surprisedBy(enemy)) {
			Statistics.sneakAttacks++;
			//TODO this is somewhat messy, it would be nicer to not have to manually handle delays here
			// playing the strong hit sound might work best as another property of weapon?
			if (Dungeon.heroine.belongings.weapon() instanceof Dart){
				Sample.INSTANCE.playDelayed(Assets.Sounds.HIT_STRONG, 0.125f);
			} else {
				Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
			}
			Surprise.hit(this);
		}

		//if attacked by something else than current target, and that thing is closer, switch targets
		if (this.enemy == null
				|| (enemy != this.enemy && (Dungeon.level.distance(pos, enemy.pos) < Dungeon.level.distance(pos, this.enemy.pos)))) {
			aggro(enemy);
			target = enemy.pos;
		}

		if (this.buff(BrainWash.class) != null && enemy instanceof Hero) {
			switch (Random.Int(4)) {
				case 0:
				default:
					Dungeon.level.drop(new Gold().random(), Dungeon.heroine.pos).sprite.drop();
					Dungeon.level.drop(new Gold().random(), Dungeon.heroine.pos).sprite.drop();
					Dungeon.level.drop(new Gold().random(), Dungeon.heroine.pos).sprite.drop();
					//3x amount
					GLog.p(Messages.get(this, "bw_gold"));
					break;
				case 1:
					switch (Random.Int(5)) {
						case 0:
						default:
							Dungeon.level.drop(Generator.random(Generator.Category.POTION), Dungeon.heroine.pos).sprite.drop();
							break;
						case 1:
							Dungeon.level.drop(Generator.random(Generator.Category.SCROLL), Dungeon.heroine.pos).sprite.drop();
							break;
						case 2:
							Dungeon.level.drop(Generator.random(Generator.Category.HERB), Dungeon.heroine.pos).sprite.drop();
							break;
						case 3:
							Dungeon.level.drop(Generator.random(Generator.Category.TALISMAN), Dungeon.heroine.pos).sprite.drop();
							break;
						case 4:
							Dungeon.level.drop(Generator.random(Generator.Category.VIAL), Dungeon.heroine.pos).sprite.drop();
							break;
					}
					GLog.p(Messages.get(this, "bw_item"));
					break;
				case 2:
					Dungeon.heroine.HP = Math.min(Dungeon.heroine.HP + Dungeon.heroine.HT/4, Dungeon.heroine.HT);
					Dungeon.heroine.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.2f, 3 );
					GLog.p(Messages.get(this, "bw_heal"));
					break;
				case 3:
					switch (Random.Int(4)) {
						case 0:
						default:
							Buff.prolong(Dungeon.heroine, Might.class, Might.DURATION);
							break;
						case 1:
							Buff.prolong(Dungeon.heroine, Hisou.class, Hisou.DURATION);
							break;
						case 2:
							Buff.prolong(Dungeon.heroine, DoubleSpeed.class, DoubleSpeed.DURATION);
							break;
						case 3:
							Buff.prolong(Dungeon.heroine, Doublerainbow.class, Doublerainbow.DURATION);
							break;
					}
					GLog.p(Messages.get(this, "bw_buff"));
					break;
			}
		}

		return damage;
	}

	@Override
	public float speed() {
		if (Dungeon.heroine.buff(HeavenSpeed.class) != null){
			return super.speed()*2f;
		}
		return super.speed();
	}

	public final boolean surprisedBy( Char enemy ){
		return surprisedBy( enemy, true);
	}

	public boolean surprisedBy( Char enemy, boolean attacking ){
		return enemy == Dungeon.heroine
				&& (enemy.invisible > 0 || !enemySeen)
				&& (!attacking || ((Hero)enemy).canSurpriseAttack());
	}

	public void aggro( Char ch ) {
		enemy = ch;
		if (state != PASSIVE){
			state = HUNTING;
		}
	}

	public boolean isTargeting( Char ch){
		return enemy == ch;
	}

	@Override
	public void damage( int dmg, Object src ) {

		if (state == SLEEPING) {
			state = WANDERING;
		}
		if (state != HUNTING && !(src instanceof Corruption)) {
			alerted = true;
		}

		super.damage( dmg, src );
	}

	@Override
	public void destroy() {

		super.destroy();

		Dungeon.level.mobs.remove( this );

		if (Dungeon.heroine.buff(MindVision.class) != null){
			Dungeon.observe();
			GameScene.updateFog(pos, 2);
		}

		if (Dungeon.heroine.isAlive()) {

			if (alignment == Alignment.ENEMY) {
				Statistics.enemiesSlain++;
				Statistics.qualifiedForNoKilling = false;

				int exp = Dungeon.heroine.lvl <= maxLvl ? EXP : 0;
				if (exp > 0) {
					Dungeon.heroine.sprite.showStatus(CharSprite.POSITIVE, Messages.get(this, "exp", exp));
				}
				Dungeon.heroine.earnExp(exp, getClass());
			}
		}
	}

	public void exterminate() {

		super.destroy();

		Dungeon.level.mobs.remove(this);

		if (Dungeon.heroine.buff(MindVision.class) != null) {
			Dungeon.observe();
			GameScene.updateFog(pos, 2);
		}

		if (Dungeon.heroine.isAlive()) {

			if (alignment == Alignment.ENEMY) {
				Statistics.qualifiedForNoKilling = false;
			}
		}
	}

	@Override
	public void die( Object cause ) {
		if (!(this instanceof SakuyaDagger) && !(this instanceof WandOfWarding.Ward) && !(this instanceof Sheep)) {
			if (Dungeon.heroine.buff(Pure.class) == null) {
				Statistics.power += 5;
			}
			if (Statistics.card44) {
				Dungeon.gold += 20;
			}
		}

		if (Dungeon.heroine.buff(AntiShipBattery.class) != null){
			Camera.main.shake( 5, 1f );
			Dungeon.heroine.damage(Dungeon.floor +5, Dungeon.heroine);
			Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
			if (enemy == Dungeon.heroine && !Dungeon.heroine.isAlive()) {
				Dungeon.fail(Aya.class);
				GLog.n(Messages.get(Aya.class, "ondeath"));
			}
		}

		if (!(this instanceof SakuyaDagger) && !(this instanceof WandOfWarding.Ward) && !(this instanceof Sheep)) {
			if (Dungeon.heroine.buff(Pure.class) == null) {
				Statistics.value += Random.NormalIntRange(10, 20);
			}
		}

		if (buff(ReBirth.class) != null && !(cause == Chasm.class) && !properties().contains(Property.BOSS) && !(this instanceof Wraith)){
			CellEmitter.get( pos ).start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );
			Buff.detach(this, ReBirth.class);
			Buff.prolong(this, ReBirthDone.class, ReBirthDone.DURATION*10000f);
			this.HP = this.HT;
		} else {
			super.die(cause);
		}

		if (cause == Chasm.class){
			//50% chance to round up, 50% to round down
			if (EXP % 2 == 1) EXP += Random.Int(2);
			EXP /= 2;
		}

		if (Statistics.difficulty == 6){
			//persona series risky difficulty
			if (EXP % 2 == 1) EXP += Random.Int(2);
			EXP /= 2;
		}

		if (Random.Int(400) == 0) {
			switch (Random.Int(3)) {
				case 0:
				default:
					Dungeon.level.drop(new ThreeStarTicket(), pos).sprite.drop();
					break;
				case 1:
					Dungeon.level.drop(new FourStarTicket(), pos).sprite.drop();
					break;
				case 2:
					Dungeon.level.drop(new FiveStarTicket(), pos).sprite.drop();
					break;
			}
		}

		if (alignment == Alignment.ENEMY){
			rollToDropLoot();
			if (Dungeon.heroine.buff(GoldCreation.class) != null){
				Dungeon.level.drop(new Gold().random(), pos ).sprite.drop();
			}
		}

		if (Dungeon.heroine.isAlive() && !Dungeon.level.heroFOV[pos]) {
			GLog.i( Messages.get(this, "died") );
		}
	}

	public float lootChance(){
		float lootChance = this.lootChance;

		lootChance *= RingOfWealth.dropChanceMultiplier( Dungeon.heroine);

		return lootChance;
	}

	public void rollToDropLoot(){
		if (Dungeon.heroine.lvl > maxLvl + 2) return;

		MasterThievesArmband.StolenTracker stolen = buff(MasterThievesArmband.StolenTracker.class);
		if (stolen == null || !stolen.itemWasStolen()) {
			if (Random.Float() < lootChance()) {
				Item loot = createLoot();
				if (loot != null) {
					Dungeon.level.drop(loot, pos).sprite.drop();
				}
			}
		}

		//ring of wealth logic
		if (Ring.getBuffedBonus(Dungeon.heroine, RingOfWealth.Wealth.class) > 0) {
			int rolls = 1;
			if (properties.contains(Property.BOSS)) rolls = 15;
			else if (properties.contains(Property.MINIBOSS)) rolls = 5;
			ArrayList<Item> bonus = RingOfWealth.tryForBonusDrop(Dungeon.heroine, rolls);
			if (bonus != null && !bonus.isEmpty()) {
				for (Item b : bonus) Dungeon.level.drop(b, pos).sprite.drop();
				RingOfWealth.showFlareForBonusDrop(sprite);
			}
		}

		//lucky enchant logic
		if (buff(Lucky.LuckProc.class) != null){
			Dungeon.level.drop(Lucky.genLoot(), pos).sprite.drop();
			Lucky.showFlare(sprite);
		}

	}

	protected Object loot = null;
	protected float lootChance = 0;

	@SuppressWarnings("unchecked")
	public Item createLoot() {
		Item item;
		if (loot instanceof Generator.Category) {

			item = Generator.randomUsingDefaults( (Generator.Category)loot );

		} else if (loot instanceof Class<?>) {

			item = Generator.random( (Class<? extends Item>)loot );

		} else {

			item = (Item)loot;

		}
		return item;
	}

	//how many mobs this one should count as when determining spawning totals
	public float spawningWeight(){
		return 1;
	}

	public boolean reset() {
		return false;
	}

	public void beckon( int cell ) {

		notice();

		if (state != HUNTING && state != FLEEING) {
			state = WANDERING;
		}
		target = cell;
	}

	public String description() {
		return Messages.get(this, "desc");
	}

	public String info(){
		String desc = description();

		return desc;
	}

	public void notice() {
		sprite.showAlert();
	}

	public void yell( String str ) {
		GLog.newLine();
		GLog.n( "%s: \"%s\" ", Messages.titleCase(name()), str );
	}

	//returns true when a mob sees the hero, and is currently targeting them.
	public boolean focusingHero() {
		return enemySeen && (target == Dungeon.heroine.pos);
	}

	public interface AiState {
		boolean act( boolean enemyInFOV, boolean justAlerted );
	}

	protected class Sleeping implements AiState {

		public static final String TAG	= "SLEEPING";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {

			//debuffs cause mobs to wake as well
			for (Buff b : buffs()){
				if (b.type == Buff.buffType.NEGATIVE){
					awaken(enemyInFOV);
					return true;
				}
			}

			if (enemyInFOV) {

				float enemyStealth = enemy.stealth();

				if (Random.Float( distance( enemy ) + enemyStealth ) < 1) {
					awaken(enemyInFOV);
					return true;
				}

			}

			enemySeen = false;
			spend( TICK );

			return true;
		}

		protected void awaken( boolean enemyInFOV ){
			if (enemyInFOV) {
				enemySeen = true;
				notice();
				state = HUNTING;
				target = enemy.pos;
			} else {
				notice();
				state = WANDERING;
				target = Dungeon.level.randomDestination( Mob.this );
			}
			spend(TIME_TO_WAKE_UP);
		}
	}

	protected class Wandering implements AiState {

		public static final String TAG	= "WANDERING";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			if (enemyInFOV && (justAlerted || Random.Float( distance( enemy ) / 2f + enemy.stealth() ) < 1)) {

				return noticeEnemy();

			} else {

				return continueWandering();

			}
		}

		protected boolean noticeEnemy(){
			enemySeen = true;

			notice();
			alerted = true;
			state = HUNTING;
			target = enemy.pos;

			return true;
		}

		protected boolean continueWandering(){
			enemySeen = false;

			int oldPos = pos;
			if (target != -1 && getCloser( target )) {
				spend( 1 / speed() );
				return moveSprite( oldPos, pos );
			} else {
				target = Dungeon.level.randomDestination( Mob.this );
				spend( TICK );
			}

			return true;
		}

	}

	protected class Hunting implements AiState {

		public static final String TAG	= "HUNTING";

		//prevents rare infinite loop cases
		private boolean recursing = false;

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			if (enemyInFOV && !isCharmedBy( enemy ) && canAttack( enemy )) {

				target = enemy.pos;
				return doAttack( enemy );

			} else {

				if (enemyInFOV) {
					target = enemy.pos;
				} else if (enemy == null) {
					sprite.showLost();
					state = WANDERING;
					target = Dungeon.level.randomDestination( Mob.this );
					spend( TICK );
					return true;
				}

				int oldPos = pos;
				if (target != -1 && getCloser( target )) {

					spend( 1 / speed() );
					return moveSprite( oldPos,  pos );

				} else {

					//if moving towards an enemy isn't possible, try to switch targets to another enemy that is closer
					//unless we have already done that and still can't move toward them, then move on.
					if (!recursing) {
						Char oldEnemy = enemy;
						enemy = null;
						enemy = chooseEnemy();
						if (enemy != null && enemy != oldEnemy) {
							recursing = true;
							boolean result = act(enemyInFOV, justAlerted);
							recursing = false;
							return result;
						}
					}

					spend( TICK );
					if (!enemyInFOV) {
						sprite.showLost();
						state = WANDERING;
						target = Dungeon.level.randomDestination( Mob.this );
					}
					return true;
				}
			}
		}
	}

	//FIXME this works fairly well but is coded poorly. Should refactor
	protected class Fleeing implements AiState {

		public static final String TAG	= "FLEEING";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			//loses target when 0-dist rolls a 6 or greater.
			if (enemy == null || !enemyInFOV && 1 + Random.Int(Dungeon.level.distance(pos, target)) >= 6){
				target = -1;

				//if enemy isn't in FOV, keep running from their previous position.
			} else if (enemyInFOV) {
				target = enemy.pos;
			}

			int oldPos = pos;
			if (target != -1 && getFurther( target )) {

				spend( 1 / speed() );
				return moveSprite( oldPos, pos );

			} else {

				spend( TICK );
				nowhereToRun();

				return true;
			}
		}

		protected void nowhereToRun() {
		}
	}

	protected class Passive implements AiState {

		public static final String TAG	= "PASSIVE";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			spend( TICK );
			return true;
		}
	}


	private static ArrayList<Mob> heldAllies = new ArrayList<>();

	public static void holdAllies( Level level ){
		holdAllies(level, Dungeon.heroine.pos);
	}

	public static void holdAllies( Level level, int holdFromPos ){
		heldAllies.clear();
		for (Mob mob : level.mobs.toArray( new Mob[0] )) {
			//preserve directable allies no matter where they are
			if (mob instanceof DirectableAlly) {
				((DirectableAlly) mob).clearDefensingPos();
				level.mobs.remove( mob );
				heldAllies.add(mob);

				//preserve intelligent allies if they are near the hero
			} else if (mob.alignment == Alignment.ALLY
					&& mob.intelligentAlly
					&& Dungeon.level.distance(holdFromPos, mob.pos) <= 5){
				level.mobs.remove( mob );
				heldAllies.add(mob);
			}
		}
	}

	public static void restoreAllies( Level level, int pos ){
		restoreAllies(level, pos, -1);
	}

	public static void restoreAllies( Level level, int pos, int gravitatePos ){
		if (!heldAllies.isEmpty()){

			ArrayList<Integer> candidatePositions = new ArrayList<>();
			for (int i : PathFinder.NEIGHBOURS8) {
				if (!Dungeon.level.solid[i+pos] && level.findMob(i+pos) == null){
					candidatePositions.add(i+pos);
				}
			}

			//gravitate pos sets a preferred location for allies to be closer to
			if (gravitatePos == -1) {
				Collections.shuffle(candidatePositions);
			} else {
				Collections.sort(candidatePositions, new Comparator<Integer>() {
					@Override
					public int compare(Integer t1, Integer t2) {
						return Dungeon.level.distance(gravitatePos, t1) -
								Dungeon.level.distance(gravitatePos, t2);
					}
				});
			}

			for (Mob ally : heldAllies) {
				level.mobs.add(ally);
				ally.state = ally.WANDERING;

				if (!candidatePositions.isEmpty()){
					ally.pos = candidatePositions.remove(0);
				} else {
					ally.pos = pos;
				}
				if (ally.sprite != null) ally.sprite.place(ally.pos);

				if (ally.fieldOfView == null || ally.fieldOfView.length != level.length()){
					ally.fieldOfView = new boolean[level.length()];
				}
				Dungeon.level.updateFieldOfView( ally, ally.fieldOfView );

			}
		}
		heldAllies.clear();
	}

	public static void clearHeldAllies(){
		heldAllies.clear();
	}
}