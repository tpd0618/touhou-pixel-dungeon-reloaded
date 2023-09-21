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

package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cool;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PinCushion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.abilitycards.sakuyaexclusive.SmeltScale;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfSharpshooting;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.enchantments.Projecting;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MissileSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.QuickSlotButton;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.LinkedList;

abstract public class MissileWeapon extends Weapon {

	{
		stackable = true;
		levelKnown = true;

		bones = true;

		defaultAction = AC_THROW;
		usesTargeting = true;
	}

	protected boolean sticky = true;

	public static final float MAX_DURABILITY = 100;
	protected float durability = MAX_DURABILITY;
	protected float baseUses = 10;

	public boolean holster;

	//used to reduce durability from the source weapon stack, rather than the one being thrown.
	protected MissileWeapon parent;

	public int tier;

	// For Sakuya's ability
	private static LinkedList<Integer> startPosContainer = new LinkedList<Integer>();
	private static LinkedList<Integer> targetContainer = new LinkedList<Integer>();
	private static LinkedList<MissileWeapon> projectileContainer = new LinkedList<MissileWeapon>();
	private static LinkedList<MissileSprite> spriteProjContainer = new LinkedList<>();
	@Override
	public int min() {
		return Math.max(0, min( buffedLvl() + RingOfSharpshooting.levelDamageBonus(Dungeon.heroine) + SmeltScale.levelDamageBonus()));
	}

	@Override
	public int min(int lvl) {
		return  3 * tier +                      //base
				(tier == 1 ? lvl : 2*lvl);      //level scaling
	}

	@Override
	public int max() {
		return Math.max(0, max( buffedLvl() + RingOfSharpshooting.levelDamageBonus(Dungeon.heroine) + SmeltScale.levelDamageBonus()));
	}

	@Override
	public int max(int lvl) {
		return  (5+Dungeon.heroine.lvl/3) * tier +                      //base
				(tier == 1 ? 2*lvl : tier*lvl); //level scaling
	}

	public int STRReq(int lvl){
		return Math.max(1,(6 + Math.round(tier * 3)) - lvl);
	}

	@Override
	//FIXME some logic here assumes the items are in the player's inventory. Might need to adjust
	public Item upgrade() {
		if (!bundleRestoring) {
			durability = MAX_DURABILITY;
			if (quantity > 1) {
				MissileWeapon upgraded = (MissileWeapon) split(1);
				upgraded.parent = null;

				upgraded = (MissileWeapon) upgraded.upgrade();

				//try to put the upgraded into inventory, if it didn't already merge
				if (upgraded.quantity() == 1 && !upgraded.collect()) {
					Dungeon.level.drop(upgraded, Dungeon.heroine.pos);
				}
				updateQuickslot();
				return upgraded;
			} else {
				super.upgrade();

				Item similar = Dungeon.heroine.belongings.getSimilar(this);
				if (similar != null){
					detach(Dungeon.heroine.belongings.backpack);
					Item result = similar.merge(this);
					updateQuickslot();
					return result;
				}
				updateQuickslot();
				return this;
			}

		} else {
			return super.upgrade();
		}
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.remove( AC_EQUIP );
		return actions;
	}

	@Override
	public boolean collect(Bag container) {
		if (container instanceof MagicalHolster) holster = true;
		return super.collect(container);
	}

	@Override
	public int throwPos(Hero user, int dst) {

		boolean projecting = hasEnchant(Projecting.class, user);

		if (projecting && !Dungeon.level.solid[dst] && Dungeon.level.distance(user.pos, dst) <= 4){
			return dst;
		} else {
			return super.throwPos(user, dst);
		}
	}
	public int throwAtAngle(Hero user, int dst, float angle){

		Ballistica ballistica = new Ballistica(user.pos, dst, Ballistica.PROJECTILE);
		return ballistica.targetAtAngle(angle).collisionPos;
	}

	@Override
	public float accuracyFactor(Char owner) {
		float accFactor = super.accuracyFactor(owner);
		return accFactor;
	}

	@Override
	public void doThrow(Hero heroine) {
		parent = null; //reset parent before throwing, just incase
		super.doThrow(heroine);
	}
	public void cast(final Hero user, final int dst ){
		if (Dungeon.heroine.heroClass == HeroClass.PLAYERSAKUYA && (Dungeon.heroine.buff(Swiftthistle.TimeBubble.class) != null || Dungeon.heroine.buff(TimekeepersHourglass.timeFreeze.class) != null)){

			final int cell = throwPos( user, dst );
			user.sprite.zap( cell );
			user.busy();

			throwSound();

			Char enemy = Actor.findChar( cell );
			QuickSlotButton.target(enemy);

			final float delay = castDelay(user, dst);
			curUser = user;
			MissileWeapon i = (MissileWeapon) MissileWeapon.this.detach(user.belongings.backpack);

			MissileSprite misSpr = ((MissileSprite) Dungeon.heroine.sprite.parent.recycle(MissileSprite.class));
			misSpr.reset(user.pos,
							cell,
							i,
							new Callback() {
								@Override
								public void call() {
									if (i != null) i.onThrow( cell );
								}
							});

			startPosContainer.add(user.pos);
			targetContainer.add(cell);
			projectileContainer.add(i);
			spriteProjContainer.add(misSpr);

			if (Statistics.card63){
				float angles[] = {-25, -40, 40, 25};
				for (float angle : angles){
					MissileWeapon mw2 = new SakuyaKnifeDanmaku(i.min(i.level()), i.max(i.level()));

					final int cell2 = throwAtAngle( user, dst, angle );

					misSpr = ((MissileSprite) Dungeon.heroine.sprite.parent.recycle(MissileSprite.class));
					misSpr.reset(user.pos,
							cell2,
							mw2,
							new Callback() {
								@Override
								public void call() {
									if (mw2 != null) mw2.onThrow( cell2 );
								}
							});

					startPosContainer.add(user.pos);
					targetContainer.add(cell2);
					projectileContainer.add(mw2);
					spriteProjContainer.add(misSpr);
				}
			}

			user.spendAndNext(delay);
		}
		else {
			super.cast(user, dst);
		}
	}
	public static void castAfterTimeFreeze(){
		final int numOfProj = projectileContainer.size();
		for (int num = 0; num < numOfProj; num++){
			int startPos = startPosContainer.get(0);
			int targetCell = targetContainer.get(0);
			MissileWeapon mw = projectileContainer.get(0);

				((MissileSprite) Dungeon.heroine.sprite.parent.recycle(MissileSprite.class)).reset(startPos,
								targetCell,
								mw,
								new Callback() {
									@Override
									public void call() {
										if (mw != null) mw.onThrow( targetCell );
									}
								});
			if (spriteProjContainer.size() != 0) {
				MissileSprite misSpr = spriteProjContainer.get(0);
				misSpr.killAndErase();
				spriteProjContainer.removeFirst();
			}
			projectileContainer.removeFirst();
				targetContainer.removeFirst();
				startPosContainer.removeFirst();
		}
	}

	@Override
	protected void onThrow( int cell ) {
		Char enemy = Actor.findChar( cell );
		if (enemy == null || enemy == curUser) {
			parent = null;
			super.onThrow( cell );
		} else {
			if (!curUser.shoot( enemy, this )) {
				rangedMiss( cell );
			} else {
				rangedHit( enemy, cell );
			}
		}
	}
	@Override
	public int proc(Char attacker, Char defender, int damage) {
		if (Dungeon.heroine.buff(Cool.class) != null){
			damage *= 0;
		}

		if (Statistics.card34) {
			Statistics.power -= Random.Int(1,3);
		} else Statistics.power -= 10;
		return super.proc(attacker, defender, damage);
	}

	@Override
	public Item random() {
		if (!stackable) return this;

		//3: 66.67% (2/3)
		//4: 26.67% (4/15)
		//5: 6.67%  (1/15)
		quantity = 3;
		if (Random.Int(3) == 0) {
			quantity++;
			if (Random.Int(5) == 0) {
				quantity++;
			}
		}
		return this;
	}

	@Override
	public float castDelay(Char user, int dst) {
		return delayFactor( user );
	}

	protected void rangedHit( Char enemy, int cell ){
		decrementDurability();
		if (durability > 0){
			//attempt to stick the missile weapon to the enemy, just drop it if we can't.
			if (sticky && enemy != null && enemy.isAlive() && enemy.alignment != Char.Alignment.ALLY){
				PinCushion p = Buff.affect(enemy, PinCushion.class);
				if (p.target == enemy){
					p.stick(this);
					return;
				}
			}
			Dungeon.level.drop( this, cell ).sprite.drop();
		}
	}

	protected void rangedMiss( int cell ) {
		parent = null;
		super.onThrow(cell);
	}

	public float durabilityLeft(){
		return durability;
	}

	public void repair( float amount ){
		durability += amount;
		durability = Math.min(durability, MAX_DURABILITY);
	}

	public float durabilityPerUse(){
		float usages = baseUses * (float)(Math.pow(3, level()));

		if (holster) {
			usages *= MagicalHolster.HOLSTER_DURABILITY_FACTOR;
		}

		usages *= RingOfSharpshooting.durabilityMultiplier( Dungeon.heroine);

		usages *= SmeltScale.durabilityMultiplier();

		//at 100 uses, items just last forever.
		if (usages >= 100f) return 0;

		usages = Math.round(usages);

		//add a tiny amount to account for rounding error for calculations like 1/3
		return (MAX_DURABILITY/usages) + 0.001f;
	}

	protected void decrementDurability(){
		//if this weapon was thrown from a source stack, degrade that stack.
		//unless a weapon is about to break, then break the one being thrown
		if (parent != null){
			if (parent.durability <= parent.durabilityPerUse()){
				durability = 0;
				parent.durability = MAX_DURABILITY;
			} else {
				parent.durability -= parent.durabilityPerUse();
				if (parent.durability > 0 && parent.durability <= parent.durabilityPerUse()){
					if (level() <= 0)GLog.w(Messages.get(this, "about_to_break"));
					else             GLog.n(Messages.get(this, "about_to_break"));
				}
			}
			parent = null;
		} else {
			durability -= durabilityPerUse();
			if (durability > 0 && durability <= durabilityPerUse()){
				if (level() <= 0)GLog.w(Messages.get(this, "about_to_break"));
				else             GLog.n(Messages.get(this, "about_to_break"));
			}
		}
	}

	@Override
	public int damageRoll(Char owner) {
		int damage = augment.damageFactor(super.damageRoll( owner ));

		if (owner instanceof Hero) {
			int exStr = ((Hero)owner).STR() - STRReq();
			if (exStr > 0) {
				damage += Random.IntRange( 0, exStr );
			}
		}

		return damage;
	}

	@Override
	public void reset() {
		super.reset();
		durability = MAX_DURABILITY;
	}

	@Override
	public Item merge(Item other) {
		super.merge(other);
		if (isSimilar(other)) {
			durability += ((MissileWeapon)other).durability;
			durability -= MAX_DURABILITY;
			while (durability <= 0){
				quantity -= 1;
				durability += MAX_DURABILITY;
			}
		}
		return this;
	}

	@Override
	public Item split(int amount) {
		bundleRestoring = true;
		Item split = super.split(amount);
		bundleRestoring = false;

		//unless the thrown weapon will break, split off a max durability item and
		//have it reduce the durability of the main stack. Cleaner to the player this way
		if (split != null){
			MissileWeapon m = (MissileWeapon)split;
			m.durability = MAX_DURABILITY;
			m.parent = this;
		}

		return split;
	}

	@Override
	public boolean doPickUp(Hero heroine, int pos) {
		parent = null;
		return super.doPickUp(heroine, pos);
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public String info() {

		String info = desc();

		info += "\n\n" + Messages.get( MissileWeapon.class, "stats",
				tier,
				Math.round(augment.damageFactor(min())),
				Math.round(augment.damageFactor(max())),
				STRReq());

		if (STRReq() > Dungeon.heroine.STR()) {
			info += " " + Messages.get(Weapon.class, "too_heavy");
		} else if (Dungeon.heroine.STR() > STRReq()){
			info += " " + Messages.get(Weapon.class, "excess_str", Dungeon.heroine.STR() - STRReq());
		}

		if (enchantment != null && (cursedKnown || !enchantment.curse())){
			info += "\n\n" + Messages.get(Weapon.class, "enchanted", enchantment.name());
			info += " " + Messages.get(enchantment, "desc");
		}

		if (cursed && isEquipped( Dungeon.heroine)) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed_worn");
		} else if (cursedKnown && cursed) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed");
		} else if (!isIdentified() && cursedKnown){
			info += "\n\n" + Messages.get(Weapon.class, "not_cursed");
		}

		info += "\n\n" + Messages.get(MissileWeapon.class, "distance");

		info += "\n\n" + Messages.get(this, "durability");

		if (durabilityPerUse() > 0){
			info += " " + Messages.get(this, "uses_left",
					(int)Math.ceil(durability/durabilityPerUse()),
					(int)Math.ceil(MAX_DURABILITY/durabilityPerUse()));
		} else {
			info += " " + Messages.get(this, "unlimited_uses");
		}


		return info;
	}

	@Override
	public int value() {
		return 6 * tier * quantity * (level() + 1);
	}

	private static final String DURABILITY = "durability";
	private static final String START_POSITION_CONT = "start_position_cont";
	private static final String TARGET_CONT = "target_cont";
	private static final String PROJECTILE_CONT = "projectile_cont";
	private static final String CONTAINER_SIZE = "container_size";
	private static Integer containerSize;
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(DURABILITY, durability);
	}

	private static boolean bundleRestoring = false;

	@Override
	public void restoreFromBundle(Bundle bundle) {
		bundleRestoring = true;
		super.restoreFromBundle(bundle);
		bundleRestoring = false;
		durability = bundle.getFloat(DURABILITY);
	}
	public static void saveTimeFreezeContainer(Bundle bundle){
		containerSize = projectileContainer.size();
		bundle.put(CONTAINER_SIZE, containerSize);
		for (Integer index = 0; index < containerSize; index++) {
			bundle.put(START_POSITION_CONT + index.toString(), startPosContainer.get(index));
			bundle.put(TARGET_CONT + index.toString(), targetContainer.get(index));
			bundle.put(PROJECTILE_CONT + index.toString(), projectileContainer.get(index));
		}
	}

	public static void restoreTimeFreezeContainer(Bundle bundle){
		if (projectileContainer.size() != 0){
			return;
		}
		containerSize = bundle.getInt(CONTAINER_SIZE);
		for (Integer index = 0; index < containerSize; index++){
			startPosContainer.addFirst(bundle.getInt(START_POSITION_CONT + index.toString()));
			targetContainer.addFirst(bundle.getInt(TARGET_CONT + index.toString()));
			projectileContainer.addFirst((MissileWeapon) (bundle.get(PROJECTILE_CONT + index.toString())));

			/*MissileSprite misSpr = ((MissileSprite) Dungeon.heroine.sprite.parent.recycle(MissileSprite.class));
			spriteProjContainer.addFirst(misSpr);
			MissileWeapon mw = projectileContainer.get(0);
			misSpr.reset(startPosContainer.get(0),
					targetContainer.get(0),
					mw,
					new Callback() {
						@Override
						public void call() {
							if (mw != null) mw.onThrow( targetContainer.get(0) );
						}
					});*/
		}
	}
	public static class PlaceHolder extends MissileWeapon {

		{
			image = ItemSpriteSheet.MISSILE_HOLDER;
		}

		@Override
		public boolean isSimilar(Item item) {
			return item instanceof MissileWeapon;
		}

		@Override
		public String info() {
			return "";
		}
	}
}