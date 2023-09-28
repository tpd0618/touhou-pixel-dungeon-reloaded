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

package com.touhoupixel.touhoupixeldungeongaiden.items;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeongaiden.journal.Catalog;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.CellSelector;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MissileSprite;
import com.touhoupixel.touhoupixeldungeongaiden.ui.QuickSlotButton;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Item implements Bundlable {

	protected static final String TXT_TO_STRING_LVL		= "%s %+d";
	protected static final String TXT_TO_STRING_X		= "%s x%d";
	
	protected static final float TIME_TO_THROW		= 1.0f;
	protected static final float TIME_TO_PICK_UP	= 1.0f;
	protected static final float TIME_TO_DROP		= 1.0f;
	
	public static final String AC_DROP		= "DROP";
	public static final String AC_THROW		= "THROW";

	public static final String AC_DISMANTLE		= "DISMANTLE";
	
	public String defaultAction;
	public boolean usesTargeting;

	//TODO should these be private and accessed through methods?
	public int image = 0;
	public int icon = -1; //used as an identifier for items with randomized images
	
	public boolean stackable = false;
	protected int quantity = 1;
	public boolean dropsDownHeap = false;
	
	private int level = 0;

	public boolean levelKnown = false;
	
	public boolean cursed;
	public boolean cursedKnown;
	
	// Unique items persist through revival
	public boolean unique = false;

	// These items are preserved even if the hero's inventory is lost via unblessed ankh
	public boolean keptThoughLostInvent = false;

	// whether an item can be included in heroes remains
	public boolean bones = false;
	
	public static final Comparator<Item> itemComparator = new Comparator<Item>() {
		@Override
		public int compare( Item lhs, Item rhs ) {
			return Generator.Category.order( lhs ) - Generator.Category.order( rhs );
		}
	};

	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = new ArrayList<>();
		actions.add( AC_DROP );
		actions.add( AC_THROW );
		return actions;
	}

	public String actionName(String action, Hero heroine){
		return Messages.get(this, "ac_" + action);
	}

	public final boolean doPickUp( Hero heroine) {
		return doPickUp(heroine, heroine.pos );
	}

	public boolean doPickUp(Hero heroine, int pos) {
		if (collect( heroine.belongings.backpack )) {
			
			GameScene.pickUp( this, pos );
			Sample.INSTANCE.play( Assets.Sounds.ITEM );
			heroine.spendAndNext( TIME_TO_PICK_UP );
			return true;
			
		} else {
			return false;
		}
	}
	
	public void doDrop( Hero heroine) {
		heroine.spendAndNext(TIME_TO_DROP);
		int pos = heroine.pos;
		Dungeon.level.drop(detachAll(heroine.belongings.backpack), pos).sprite.drop(pos);
	}

	//resets an item's properties, to ensure consistency between runs
	public void reset(){
		keptThoughLostInvent = false;
	}

	public void doThrow(Hero heroine) {
		GameScene.selectCell(thrower);
	}

	public void execute(Hero heroine, String action ) {

		GameScene.cancel();
		curUser = heroine;
		curItem = this;

		if (action.equals( AC_DROP )) {

			if (heroine.belongings.backpack.contains(this) || isEquipped(heroine)) {
				doDrop(heroine);
			}

		} else if (action.equals( AC_THROW )) {

			if (heroine.belongings.backpack.contains(this) || isEquipped(heroine)) {
				doThrow(heroine);
			}

		}
	}

	public void execute( Hero heroine) {
		execute(heroine, defaultAction );
	}
	
	protected void onThrow( int cell ) {
		Heap heap = Dungeon.level.drop( this, cell );
		if (!heap.isEmpty()) {
			heap.sprite.drop( cell );
		}
	}
	
	//takes two items and merges them (if possible)
	public Item merge( Item other ){
		if (isSimilar( other )){
			quantity += other.quantity;
			other.quantity = 0;
		}
		return this;
	}
	
	public boolean collect( Bag container ) {

		if (quantity <= 0){
			return true;
		}

		ArrayList<Item> items = container.items;

		if (items.contains( this )) {
			return true;
		}

		for (Item item:items) {
			if (item instanceof Bag && ((Bag)item).canHold( this )) {
				if (collect( (Bag)item )){
					return true;
				}
			}
		}

		if (!container.canHold(this)){
			return false;
		}
		
		if (stackable) {
			for (Item item:items) {
				if (isSimilar( item )) {
					item.merge( this );
					item.updateQuickslot();
					if (Dungeon.heroine != null && Dungeon.heroine.isAlive()) {
						if (isIdentified()) Catalog.setSeen(getClass());
					}
					return true;
				}
			}
		}

		if (Dungeon.heroine != null && Dungeon.heroine.isAlive()) {
			if (isIdentified()) Catalog.setSeen(getClass());
		}

		items.add( this );
		Dungeon.quickslot.replacePlaceholder(this);
		Collections.sort( items, itemComparator );
		updateQuickslot();
		return true;

	}
	
	public boolean collect() {
		return collect( Dungeon.heroine.belongings.backpack );
	}
	
	//returns a new item if the split was sucessful and there are now 2 items, otherwise null
	public Item split( int amount ){
		if (amount <= 0 || amount >= quantity()) {
			return null;
		} else {
			//pssh, who needs copy constructors?
			Item split = Reflection.newInstance(getClass());
			
			if (split == null){
				return null;
			}
			
			Bundle copy = new Bundle();
			this.storeInBundle(copy);
			split.restoreFromBundle(copy);
			split.quantity(amount);
			quantity -= amount;
			
			return split;
		}
	}
	
	public final Item detach( Bag container ) {
		
		if (quantity <= 0) {
			
			return null;
			
		} else
		if (quantity == 1) {

			if (stackable){
				Dungeon.quickslot.convertToPlaceholder(this);
			}

			return detachAll( container );
			
		} else {
			
			
			Item detached = split(1);
			updateQuickslot();
			if (detached != null) detached.onDetach( );
			return detached;
			
		}
	}
	
	public final Item detachAll( Bag container ) {
		Dungeon.quickslot.clearItem( this );

		for (Item item : container.items) {
			if (item == this) {
				container.items.remove(this);
				item.onDetach();
				container.grabItems(); //try to put more items into the bag as it now has free space
				updateQuickslot();
				return this;
			} else if (item instanceof Bag) {
				Bag bag = (Bag)item;
				if (bag.contains( this )) {
					return detachAll( bag );
				}
			}
		}

		updateQuickslot();
		return this;
	}
	
	public boolean isSimilar( Item item ) {
		return level == item.level && getClass() == item.getClass();
	}

	protected void onDetach(){}

	//returns the true level of the item, ignoring all modifiers aside from upgrades
	public final int trueLevel(){
		return level;
	}

	//returns the persistant level of the item, only affected by modifiers which are persistent (e.g. curse infusion)
	public int level(){
		return level;
	}
	
	//returns the level of the item, after it may have been modified by temporary boosts/reductions
	//note that not all item properties should care about buffs/debuffs! (e.g. str requirement)
	public int buffedLvl() {
		if (Dungeon.heroine.buff(Degrade.class) != null) {
			return Degrade.reduceLevel(level());
		}
		return level();
	}

	public void level( int value ){
		level = value;

		updateQuickslot();
	}
	
	public Item upgrade() {
		
		this.level++;

		updateQuickslot();
		
		return this;
	}

	public Item downgrade() {

		this.level--;

		updateQuickslot();

		return this;
	}
	
	final public Item upgrade( int n ) {
		for (int i=0; i < n; i++) {
			upgrade();
		}
		
		return this;
	}
	
	public Item degrade() {
		
		this.level--;
		
		return this;
	}
	
	final public Item degrade( int n ) {
		for (int i=0; i < n; i++) {
			degrade();
		}
		
		return this;
	}
	
	public int visiblyUpgraded() {
		return levelKnown ? level() : 0;
	}

	public int buffedVisiblyUpgraded() {
		return levelKnown ? buffedLvl() : 0;
	}
	
	public boolean visiblyCursed() {
		return cursed && cursedKnown;
	}

	public boolean isUpgradable() {
		return true;
	}
	
	public boolean isIdentified() {
		return levelKnown && cursedKnown;
	}
	
	public boolean isEquipped( Hero heroine) {
		return false;
	}

	public final Item identify(){
		return identify(true);
	}

	public Item identify( boolean byHero ) {

		if (byHero && Dungeon.heroine != null && Dungeon.heroine.isAlive()){
			Catalog.setSeen(getClass());
		}

		levelKnown = true;
		cursedKnown = true;
		Item.updateQuickslot();

		return this;
	}
	
	public void onHeroGainExp( float levelPercent, Hero heroine){
		//do nothing by default
	}
	
	public static void evoke( Hero heroine) {
		heroine.sprite.emitter().burst( Speck.factory( Speck.EVOKE ), 5 );
	}
	
	@Override
	public String toString() {

		String name = name();

		if (visiblyUpgraded() != 0)
			name = Messages.format( TXT_TO_STRING_LVL, name, visiblyUpgraded()  );

		if (quantity > 1)
			name = Messages.format( TXT_TO_STRING_X, name, quantity );

		return name;

	}
	
	public String name() {
		return trueName();
	}
	
	public final String trueName() {
		return Messages.get(this, "name");
	}
	
	public int image() {
		return image;
	}
	
	public ItemSprite.Glowing glowing() {
		return null;
	}

	public Emitter emitter() { return null; }
	
	public String info() {
		return desc();
	}
	
	public String desc() {
		return Messages.get(this, "desc");
	}
	
	public int quantity() {
		return quantity;
	}
	
	public Item quantity( int value ) {
		quantity = value;
		return this;
	}

	//item's value in gold coins
	public int value() {
		return 0;
	}

	//item's value in energy crystals
	public int energyVal() {
		return 0;
	}
	
	public Item virtual(){
		Item item = Reflection.newInstance(getClass());
		if (item == null) return null;
		
		item.quantity = 0;
		item.level = level;
		return item;
	}
	
	public Item random() {
		return this;
	}
	
	public String status() {
		return quantity != 1 ? Integer.toString( quantity ) : null;
	}

	public static void updateQuickslot() {
		GameScene.updateItemDisplays = true;
	}
	
	private static final String QUANTITY		= "quantity";
	private static final String LEVEL			= "level";
	private static final String LEVEL_KNOWN		= "levelKnown";
	private static final String CURSED			= "cursed";
	private static final String CURSED_KNOWN	= "cursedKnown";
	private static final String QUICKSLOT		= "quickslotpos";
	private static final String KEPT_LOST       = "kept_lost";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		bundle.put( QUANTITY, quantity );
		bundle.put( LEVEL, level );
		bundle.put( LEVEL_KNOWN, levelKnown );
		bundle.put( CURSED, cursed );
		bundle.put( CURSED_KNOWN, cursedKnown );
		if (Dungeon.quickslot.contains(this)) {
			bundle.put( QUICKSLOT, Dungeon.quickslot.getSlot(this) );
		}
		bundle.put( KEPT_LOST, keptThoughLostInvent );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		quantity	= bundle.getInt( QUANTITY );
		levelKnown	= bundle.getBoolean( LEVEL_KNOWN );
		cursedKnown	= bundle.getBoolean( CURSED_KNOWN );
		
		int level = bundle.getInt( LEVEL );
		if (level > 0) {
			upgrade( level );
		} else if (level < 0) {
			degrade( -level );
		}
		
		cursed	= bundle.getBoolean( CURSED );

		//only want to populate slot on first load.
		if (Dungeon.heroine == null) {
			if (bundle.contains(QUICKSLOT)) {
				Dungeon.quickslot.setSlot(bundle.getInt(QUICKSLOT), this);
			}
		}

		keptThoughLostInvent = bundle.getBoolean( KEPT_LOST );
	}

	public int targetingPos(Hero user, int dst ){
		return throwPos( user, dst );
	}

	public int throwPos(Hero user, int dst){
		return new Ballistica( user.pos, dst, Ballistica.PROJECTILE ).collisionPos;
	}

	public void throwSound(){
		Sample.INSTANCE.play(Assets.Sounds.MISS, 0.6f, 0.6f, 1.5f);
	}
	
	public void cast(final Hero user, final int dst ) {
		
		final int cell = throwPos( user, dst );
		user.sprite.zap( cell );
		user.busy();

		throwSound();

		Char enemy = Actor.findChar( cell );
		QuickSlotButton.target(enemy);
		
		final float delay = castDelay(user, dst);

		if (enemy != null) {
			((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
					reset(user.sprite,
							enemy.sprite,
							this,
							new Callback() {
						@Override
						public void call() {
							curUser = user;
							Item i = Item.this.detach(user.belongings.backpack);
							if (i != null) i.onThrow(cell);
							user.spendAndNext(delay);
						}
					});
		} else {
			((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
					reset(user.sprite,
							cell,
							this,
							new Callback() {
						@Override
						public void call() {
							curUser = user;
							Item i = Item.this.detach(user.belongings.backpack);
							if (i != null) i.onThrow(cell);
							user.spendAndNext(delay);
						}
					});
		}
	}
	
	public float castDelay( Char user, int dst ){
		return TIME_TO_THROW;
	}
	
	protected static Hero curUser = null;
	protected static Item curItem = null;
	protected static CellSelector.Listener thrower = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {
				curItem.cast( curUser, target );
			}
		}
		@Override
		public String prompt() {
			return Messages.get(Item.class, "prompt");
		}
	};
}
