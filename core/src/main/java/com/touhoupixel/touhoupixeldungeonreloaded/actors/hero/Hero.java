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

package com.touhoupixel.touhoupixeldungeonreloaded.actors.hero;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.GamesInProgress;
import com.touhoupixel.touhoupixeldungeonreloaded.ShatteredPixelDungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AdrenalineSurge;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Amok;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiHeal;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiSneakattack;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Awareness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Berserk;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Cool;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DanmakuDamageIncrease;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Foresight;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Happy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HighStress;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HomingBlade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hunger;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Calm;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Lignification;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.LostInventory;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MindVision;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.NightTime;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDefDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Powerful;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Pure;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReachIncrease;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Regeneration;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SakiMark;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SupernaturalBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YukariBorder;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Junko;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Reimu;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Tenshi;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Wraith;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.Sheep;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CheckedCell;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Speck;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Amulet;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Dewdrop;
import com.touhoupixel.touhoupixeldungeonreloaded.items.EquipableItem;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Heap.Type;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.KindOfWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.PoppinPartyArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.MorfonicaArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.NitoriArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.SharkArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.ToyohimeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.YorihimeArmor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.glyphs.AntiMagic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.glyphs.Brimstone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.glyphs.Viscosity;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.AlchemistsToolkit;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.CapeOfThorns;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.EtherealChains;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.HornOfPlenty;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.MasterThievesArmband;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TalismanOfForesight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.CrystalKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.GoldenKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.IronKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.Key;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs.ElixirOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfAccuracy;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfEvasion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfForce;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfFuror;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfHaste;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfMight;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.RingOfTenacity;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfMirrorImage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.CursedWand;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfBlastWave;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfLivingEarth;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.CirnoWing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.BulletDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.MissileWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.missiles.YuukaDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.journal.Notes;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Level;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.Chasm;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.Trap;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.ShadowCaster;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.AlchemyScene;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.InterlevelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.SurfaceScene;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.TitleScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.HeroSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.AttackIndicator;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.QuickSlotButton;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndOptions;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndTradeItem;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collections;

public class Hero extends Char {

	{
		actPriority = HERO_PRIO;

		alignment = Alignment.ALLY;
	}

	public static final int MAX_LEVEL = 99; //same as touhou genso wanderer series

	public static final int STARTING_STR = 10; //test ver. 100, original ver. 10

	private static final float TIME_TO_REST		    = 1f;
	private static final float TIME_TO_SEARCH	    = 2f;
	private static final float HUNGER_FOR_SEARCH	= 6f;

	public HeroClass heroClass = HeroClass.PLAYERREIMU;

	private int attackSkill = 10; //test ver. 100, original ver. 10
	private int defenseSkill = 5; //test ver. 0, original ver. 5

	public boolean ready = false;
	private boolean damageInterrupt = true;
	public HeroAction curAction = null;
	public HeroAction lastAction = null;

	private Char enemy;

	public boolean resting = false;

	public Belongings belongings;

	public int STR;

	public float awareness;

	public int lvl = 1;
	public int exp = 0;

	public int HTBoost = 0;

	private ArrayList<Mob> visibleEnemies;

	//This list is maintained so that some logic checks can be skipped
	// for enemies we know we aren't seeing normally, resultign in better performance
	public ArrayList<Mob> mindVisionEnemies = new ArrayList<>();

	public Hero() {
		super();

		HP = HT = 30; //test ver. 5000, original ver. 50
		STR = STARTING_STR;

		belongings = new Belongings( this );

		visibleEnemies = new ArrayList<>();
	}

	public void updateHT( boolean boostHP ){
		int curHT = HT;

		if (Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
			HT = 30 + Statistics.healwoundsHTdown + 3 * (lvl - 1) + HTBoost;
		} else if (Dungeon.hero.heroClass == HeroClass.PLAYERYOUMU) {
			HT = 30 + Statistics.healwoundsHTdown + 4 * (lvl - 1) + HTBoost;
		} else if (Dungeon.hero.heroClass == HeroClass.PLAYERSANAE) {
			HT = 30 + Statistics.healwoundsHTdown + 4 * (lvl - 1) + HTBoost;
		} else if (Dungeon.hero.heroClass == HeroClass.PLAYERMARISA) {
			HT = 30 + Statistics.healwoundsHTdown + 5 * (lvl - 1) + HTBoost;
		} else if (Dungeon.hero.heroClass == HeroClass.PLAYERREIMU) {
			HT = 30 + Statistics.healwoundsHTdown + 5 * (lvl - 1) + HTBoost;
		}
		float multiplier = RingOfMight.HTMultiplier(this);
		HT = Math.round(multiplier * HT);

		if (buff(ElixirOfMight.HTBoost.class) != null){
			HT += buff(ElixirOfMight.HTBoost.class).boost();
		}

		if (boostHP){
			HP += Math.max(HT - curHT, 0);
		}
		HP = Math.min(HP, HT);
	}

	public int STR() {
		int strBonus = 0;

		strBonus += RingOfMight.strengthBonus( this );

		AdrenalineSurge buff = buff(AdrenalineSurge.class);
		if (buff != null){
			strBonus += buff.boost();
		}

		return STR + strBonus;
	}

	private static final String CLASS       = "class";
	private static final String SUBCLASS    = "subClass";
	private static final String ABILITY     = "armorAbility";

	private static final String ATTACK		= "attackSkill";
	private static final String DEFENSE		= "defenseSkill";
	private static final String STRENGTH	= "STR";
	private static final String LEVEL		= "lvl";
	private static final String EXPERIENCE	= "exp";
	private static final String HTBOOST     = "htboost";

	@Override
	public void storeInBundle( Bundle bundle ) {

		super.storeInBundle( bundle );

		bundle.put( CLASS, heroClass );

		bundle.put( ATTACK, attackSkill );
		bundle.put( DEFENSE, defenseSkill );

		bundle.put( STRENGTH, STR );

		bundle.put( LEVEL, lvl );
		bundle.put( EXPERIENCE, exp );

		bundle.put( HTBOOST, HTBoost );

		belongings.storeInBundle( bundle );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {

		lvl = bundle.getInt( LEVEL );
		exp = bundle.getInt( EXPERIENCE );

		HTBoost = bundle.getInt(HTBOOST);

		super.restoreFromBundle( bundle );

		heroClass = bundle.getEnum( CLASS, HeroClass.class );

		attackSkill = bundle.getInt( ATTACK );
		defenseSkill = bundle.getInt( DEFENSE );

		STR = bundle.getInt( STRENGTH );

		belongings.restoreFromBundle( bundle );
	}

	public static void preview( GamesInProgress.Info info, Bundle bundle ) {
		info.level = bundle.getInt( LEVEL );
		info.str = bundle.getInt( STRENGTH );
		info.exp = bundle.getInt( EXPERIENCE );
		info.hp = bundle.getInt( Char.TAG_HP );
		info.ht = bundle.getInt( Char.TAG_HT );
		info.shld = bundle.getInt( Char.TAG_SHLD );
		info.heroClass = bundle.getEnum( CLASS, HeroClass.class );
		Belongings.preview( info, bundle );
	}

	@Override
	public void hitSound(float pitch) {
		if ( belongings.weapon() != null ){
			belongings.weapon().hitSound(pitch);
		} else if (RingOfForce.getBuffedBonus(this, RingOfForce.Force.class) > 0) {
			//pitch deepens by 2.5% (additive) per point of strength, down to 75%
			super.hitSound( pitch * GameMath.gate( 0.75f, 1.25f - 0.025f*STR(), 1f) );
		} else {
			super.hitSound(pitch * 1.1f);
		}
	}

	@Override
	public boolean blockSound(float pitch) {
		if ( belongings.weapon() != null && belongings.weapon().defenseFactor(this) >= 4 ){
			Sample.INSTANCE.play( Assets.Sounds.HIT_PARRY, 1, pitch);
			return true;
		}
		return super.blockSound(pitch);
	}

	public void live() {
		for (Buff b : buffs()){
			if (!b.revivePersists) b.detach();
		}
		Buff.affect( this, Regeneration.class );
		Buff.affect( this, Hunger.class );
	}

	public int tier() {
		Armor armor = belongings.armor();
		if (armor != null){
			return armor.tier;
		} else {
			return 0;
		}
	}

	public boolean shoot( Char enemy, MissileWeapon wep ) {

		this.enemy = enemy;

		//temporarily set the hero's weapon to the missile weapon being used
		//TODO improve this!
		belongings.thrownWeapon = wep;
		boolean hit = attack( enemy );
		Invisibility.dispel();
		belongings.thrownWeapon = null;

		return hit;
	}

	@Override
	public int attackSkill( Char target ) {
		KindOfWeapon wep = belongings.weapon();

		float accuracy = 1;
		accuracy *= RingOfAccuracy.accuracyMultiplier( this );

		if (Statistics.card16 && enemy instanceof Wraith || buff(HomingBlade.class) != null){
			return INFINITE_ACCURACY;
		}

		if (wep instanceof MissileWeapon){
			if (Dungeon.level.adjacent( pos, target.pos ) && !(Statistics.card27) && !(wep instanceof BulletDanmaku)) {
				accuracy *= 0.5f;
			} else if (Dungeon.level.adjacent( pos, target.pos ) && Statistics.card27) {
				accuracy *= 1.05f;
			} else {
				accuracy *= 1.1f;
			}
		}

		if (wep != null) {
			return (int)(attackSkill * accuracy * wep.accuracyFactor( this ));
		} else {
			return (int)(attackSkill * accuracy);
		}
	}

	@Override
	public int defenseSkill( Char enemy ) {

		float evasion = defenseSkill;

		if (Statistics.card68 && buff(NightTime.class) != null && buff(Light.class) == null && (Random.Int(3) == 0)){
			return INFINITE_EVASION;
		}

		if (Statistics.card63 && this.HP % 2 == 0){
			evasion *= 1.18;
		}

		evasion *= RingOfEvasion.evasionMultiplier( this );

		if (paralysed > 0) {
			evasion /= 2;
		}

		if (Dungeon.hero.belongings.armor() instanceof PoppinPartyArmor){
			evasion *= 1.05;
		}

		if (belongings.armor() != null) {
			evasion = belongings.armor().evasionFactor(this, evasion);
		}

		return Math.round(evasion);
	}

	@Override
	public int drRoll() {
		int dr = 0;

		if (belongings.armor() != null) {
			int armDr = Random.NormalIntRange( belongings.armor().DRMin(), belongings.armor().DRMax());
			if (STR() < belongings.armor().STRReq()){
				armDr -= 2*(belongings.armor().STRReq() - STR());
			}
			if (armDr > 0) dr += armDr;
		}
		if (belongings.weapon() != null)  {
			int wepDr = Random.NormalIntRange( 0 , belongings.weapon().defenseFactor( this ) );
			if (STR() < ((Weapon)belongings.weapon()).STRReq()){
				wepDr -= 2*(((Weapon)belongings.weapon()).STRReq() - STR());
			}
			if (wepDr > 0) dr += wepDr;
		}

		return dr;
	}

	@Override
	public int damageRoll() {
		KindOfWeapon wep = belongings.weapon();
		int dmg;

		if (wep != null) {
			dmg = wep.damageRoll( this );
			if (!(wep instanceof MissileWeapon)) dmg += RingOfForce.armedDamageBonus(this);
		} else {
			dmg = RingOfForce.damageRoll(this);
		}
		if (dmg < 0) dmg = 0;

		if (Dungeon.hero.buff(OneDamage.class) != null) {
			dmg = 1;
		}

		if (Dungeon.hero.buff(RemiliaFate.class) != null){
			dmg *= 2;
		}

		if (Dungeon.level.map[this.pos] == Terrain.PEDESTAL){
			dmg = 1;
			ScrollOfTeleportation.teleportChar(enemy);
		}

		if (Dungeon.hero.buff(Powerful.class) != null && enemy.buff(Powerful.class) != null ||
			Dungeon.hero.buff(Cool.class) != null && enemy.buff(Cool.class) != null ||
			Dungeon.hero.buff(Pure.class) != null && enemy.buff(Pure.class) != null ||
			Dungeon.hero.buff(Happy.class) != null && enemy.buff(Happy.class) != null){
			dmg *= 0;
		}

		if (Statistics.playercorruption == 1 && (Random.Int(100) == 0) ||
			Statistics.playercorruption == 2 && (Random.Int(80) == 0) ||
			Statistics.playercorruption == 3 && (Random.Int(60) == 0) ||
			Statistics.playercorruption == 4 && (Random.Int(40) == 0) ||
			Statistics.playercorruption == 5 && (Random.Int(20) == 0) ||
			Statistics.playercorruption == 6 && (Random.Int(10) == 0) ||
			Statistics.playercorruption == 7 && (Random.Int(7) == 0) ||
			Statistics.playercorruption == 8 && (Random.Int(4) == 0) ||
			Statistics.playercorruption == 9 && (Random.Int(3) == 0) ||
			Statistics.playercorruption == 10 && (Random.Int(2) == 0)) {
			dmg *= 0;
			Sample.INSTANCE.play( Assets.Sounds.CURSED );
		}

		if (Dungeon.hero.buff(DanmakuDamageIncrease.class) != null && Dungeon.hero.belongings.weapon() instanceof MissileWeapon) {
			dmg *= 1.4f;
		}

		if (Statistics.power == 400){
			dmg *= 1.1f;
		}
		if (Statistics.power >= 300){
			dmg *= 1.1f;
		}
		if (Statistics.power >= 200){
			dmg *= 1.1f;
		}

		if (Statistics.card22 && Dungeon.hero.buff(Doublespeed.class) != null){
			dmg *= 1.2f;
		}

		if (Statistics.card25 && Dungeon.hero.HP % 2 == 1){
			dmg *= 1.2f;
		}

		if (Statistics.card26 && Statistics.playercorruption > 5){
			dmg *= 1.3f;
		}

		if (Statistics.card45) {
			dmg *= 1.28f;
		}

		if (Statistics.card67){
			dmg *= 0.9f;
		}

		if (Statistics.card74 && !Statistics.lifelose){
			dmg *= 1.25f;
		}

		return dmg;
	}

	@Override
	public float speed() {

		float speed = super.speed();

		if (Statistics.card31){
			speed *= 1.2;
		}

		if (Dungeon.isChallenged(Challenges.RINGING_BLOOM) && Notes.keyCount(new GoldenKey(Dungeon.depth)) > 0){
			speed *= 0.5;
		}

		speed *= RingOfHaste.speedMultiplier(this);

		if (belongings.armor() != null) {
			speed = belongings.armor().speedFactor(this, speed);
		}

		((HeroSprite)sprite).sprint( 1f );

		return speed;

	}

	public boolean canSurpriseAttack(){
		if (belongings.weapon() == null || !(belongings.weapon() instanceof Weapon))    return true;
		if (STR() < ((Weapon)belongings.weapon()).STRReq())                             return false;
		if (Dungeon.hero.buff(AntiSneakattack.class) != null)                           return false;
		if (belongings.weapon() instanceof CirnoWing)                                   return false;
		if (Dungeon.hero.buff(Powerful.class) != null)                                  return false;
		if (Statistics.card45) return false;

		return true;
	}

	public boolean canAttack(Char enemy){
		if (enemy == null || pos == enemy.pos || !Actor.chars().contains(enemy)) {
			return false;
		}

		//can always attack adjacent enemies
		if (Dungeon.level.adjacent(pos, enemy.pos)) {
			return true;
		}

		KindOfWeapon wep = Dungeon.hero.belongings.weapon();

		if (wep != null){
			return wep.canReach(this, enemy.pos);
		} else {
			return false;
		}
	}

	public float attackDelay() {
		if (belongings.weapon() != null) {

			return belongings.weapon().delayFactor( this );

		} else {
			//Normally putting furor speed on unarmed attacks would be unnecessary
			//But there's going to be that one guy who gets a furor+force ring combo
			//This is for that one guy, you shall get your fists of fury!
			return 1f/RingOfFuror.attackSpeedMultiplier(this);
		}
	}

	@Override
	public void spend( float time ) {
		justMoved = false;
		TimekeepersHourglass.timeFreeze freeze = buff(TimekeepersHourglass.timeFreeze.class);
		if (freeze != null) {
			freeze.processTime(time);
			return;
		}

		if (buff(Calm.class) != null) {
			this.HP = Math.min(this.HP + 1, this.HT);
		}

		Swiftthistle.TimeBubble bubble = buff(Swiftthistle.TimeBubble.class);
		if (bubble != null){
			bubble.processTime(time);
			return;
		}

		if (Dungeon.isChallenged(Challenges.SWORD_OF_HISOU)){
			if (Statistics.tenshiEarthquake > 98) {
				Statistics.tenshiEarthquake = 0;
				Camera.main.shake( 5, 1f );
				if (!this.flying) {
					this.damage(Dungeon.depth/2+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Tenshi.class);
						GLog.n( Messages.get(Tenshi.class, "ondeath") );
					}
				}
				Sample.INSTANCE.play(Assets.Sounds.BLAST);
			} else Statistics.tenshiEarthquake += 1;
		}

		if (Statistics.nighttimecount > 998){
			if (buff(NightTime.class) == null) {
				Statistics.nighttimecount = 0;
				GameScene.flash(0x80FFFFFF);
				Buff.prolong(this, NightTime.class, NightTime.DURATION);
				Sample.INSTANCE.play(Assets.Sounds.LULLABY);
				GLog.p(Messages.get(NightTime.class, "night"));
			} else if (buff(NightTime.class) != null) {
				Statistics.nighttimecount = 0;
				GameScene.flash(0x80FFFFFF);
				Buff.detach(this, NightTime.class);
				Sample.INSTANCE.play(Assets.Sounds.LULLABY);
				GLog.p(Messages.get(NightTime.class, "day"));
			}
		} else Statistics.nighttimecount += 1;

		super.spend(time);
	}

	public void spendAndNext( float time ) {
		busy();
		spend( time );
		next();

		if (Dungeon.isChallenged(Challenges.INVINCIBLE_GENSOKYO) && Dungeon.level.map[this.pos] == Terrain.OPEN_DOOR){
			Statistics.mood += 1;
			Buff.prolong(this, AntiHeal.class, AntiHeal.DURATION/10f);
		}
	}

	@Override
	public boolean act() {
		//calls to dungeon.observe will also update hero's local FOV.
		fieldOfView = Dungeon.level.heroFOV;

		if (!ready) {
			//do a full observe (including fog update) if not resting.
			if (!resting || buff(MindVision.class) != null || buff(Awareness.class) != null) {
				Dungeon.observe();
			} else {
				//otherwise just directly re-calculate FOV
				Dungeon.level.updateFieldOfView(this, fieldOfView);
			}
		}

		checkVisibleMobs();
		BuffIndicator.refreshHero();

		if (paralysed > 0) {

			curAction = null;

			spendAndNext( TICK );
			return false;
		}

		boolean actResult;
		if (curAction == null) {

			if (resting) {
				spend( TIME_TO_REST );
				next();
			} else {
				ready();
			}

			actResult = false;

		} else {

			resting = false;

			ready = false;

			if (curAction instanceof HeroAction.Move) {
				actResult = actMove( (HeroAction.Move)curAction );

			} else if (curAction instanceof HeroAction.Interact) {
				actResult = actInteract( (HeroAction.Interact)curAction );

			} else if (curAction instanceof HeroAction.Buy) {
				actResult = actBuy( (HeroAction.Buy)curAction );

			}else if (curAction instanceof HeroAction.PickUp) {
				actResult = actPickUp( (HeroAction.PickUp)curAction );

			} else if (curAction instanceof HeroAction.OpenChest) {
				actResult = actOpenChest( (HeroAction.OpenChest)curAction );

			} else if (curAction instanceof HeroAction.Unlock) {
				actResult = actUnlock((HeroAction.Unlock) curAction);

			} else if (curAction instanceof HeroAction.LvlTransition) {
				actResult = actTransition( (HeroAction.LvlTransition)curAction );

			} else if (curAction instanceof HeroAction.Attack) {
				actResult = actAttack( (HeroAction.Attack)curAction );

			} else if (curAction instanceof HeroAction.Alchemy) {
				actResult = actAlchemy( (HeroAction.Alchemy)curAction );

			} else {
				actResult = false;
			}
		}

		return actResult;
	}

	public void busy() {
		ready = false;
	}

	private void ready() {
		if (sprite.looping()) sprite.idle();
		curAction = null;
		damageInterrupt = true;
		ready = true;

		AttackIndicator.updateState();
		BuffIndicator.refreshBoss();

		if (Dungeon.isChallenged(Challenges.LUNAR_CAPITAL_CHESS)) {
			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob.pos - Dungeon.level.width() * 2 - 1) {
					this.damage(Dungeon.depth / 3 + 1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n(Messages.get(Junko.class, "ondeath"));
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6);
				}
			}
			for (Mob mob2 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob2.pos - Dungeon.level.width()*2 + 1){
					this.damage(Dungeon.depth/3+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n( Messages.get(Junko.class, "ondeath") );
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob3 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob3.pos - Dungeon.level.width() - 2){
					this.damage(Dungeon.depth/3+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n( Messages.get(Junko.class, "ondeath") );
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob4 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob4.pos - Dungeon.level.width() + 2){
					this.damage(Dungeon.depth/3+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n( Messages.get(Junko.class, "ondeath") );
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob5 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob5.pos + Dungeon.level.width() - 2){
					this.damage(Dungeon.depth/3+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n( Messages.get(Junko.class, "ondeath") );
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob6 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob6.pos + Dungeon.level.width() + 2){
					this.damage(Dungeon.depth/3+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n( Messages.get(Junko.class, "ondeath") );
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
			for (Mob mob7 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob7.pos + Dungeon.level.width()*2 - 1){
					this.damage(Dungeon.depth/3+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n( Messages.get(Junko.class, "ondeath") );
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6);
				}
			}
			for (Mob mob8 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mob8.pos + Dungeon.level.width()*2 + 1){
					this.damage(Dungeon.depth/3+1, this);
					if (this == Dungeon.hero && !this.isAlive()) {
						Dungeon.fail(Junko.class);
						GLog.n( Messages.get(Junko.class, "ondeath") );
					}
					this.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6 );
				}
			}
		}

		if (buff(SakiMark.class) != null) {
			for (Mob mobsaki : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki.pos - Dungeon.level.width()*2 - 1){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
			for (Mob mobsaki2 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki2.pos - Dungeon.level.width()*2 + 1){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
			for (Mob mobsaki3 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki3.pos - Dungeon.level.width() - 2){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
			for (Mob mobsaki4 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki4.pos - Dungeon.level.width() + 2){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
			for (Mob mobsaki5 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki5.pos + Dungeon.level.width() - 2){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
			for (Mob mobsaki6 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki6.pos + Dungeon.level.width() + 2){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
			for (Mob mobsaki7 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki7.pos + Dungeon.level.width()*2 - 1){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
			for (Mob mobsaki8 : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (this.pos == mobsaki8.pos + Dungeon.level.width()*2 + 1){
					Buff.prolong(this, Degrade.class, Degrade.DURATION);
				}
			}
		}

		GameScene.ready();
	}

	public void interrupt() {
		if (isAlive() && curAction != null &&
				((curAction instanceof HeroAction.Move && curAction.dst != pos) ||
						(curAction instanceof HeroAction.LvlTransition))) {
			lastAction = curAction;
		}
		curAction = null;
		GameScene.resetKeyHold();
	}

	public void resume() {
		curAction = lastAction;
		lastAction = null;
		damageInterrupt = false;
		next();
	}

	private boolean actMove( HeroAction.Move action ) {

		if (getCloser( action.dst )) {
			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actInteract( HeroAction.Interact action ) {

		Char ch = action.ch;

		if (ch.canInteract(this)) {

			ready();
			sprite.turnTo( pos, ch.pos );
			return ch.interact(this);

		} else {

			if (fieldOfView[ch.pos] && getCloser( ch.pos )) {

				return true;

			} else {
				ready();
				return false;
			}

		}
	}

	private boolean actBuy( HeroAction.Buy action ) {
		int dst = action.dst;
		if (pos == dst) {

			ready();

			Heap heap = Dungeon.level.heaps.get( dst );
			if (heap != null && heap.type == Type.FOR_SALE && heap.size() == 1) {
				Game.runOnRenderThread(new Callback() {
					@Override
					public void call() {
						GameScene.show( new WndTradeItem( heap ) );
					}
				});
			}

			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actAlchemy( HeroAction.Alchemy action ) {
		int dst = action.dst;
		if (Dungeon.level.distance(dst, pos) <= 1) {

			ready();

			AlchemistsToolkit.kitEnergy kit = buff(AlchemistsToolkit.kitEnergy.class);
			if (kit != null && kit.isCursed()){
				GLog.w( Messages.get(AlchemistsToolkit.class, "cursed"));
				return false;
			}

			AlchemyScene.clearToolkit();
			ShatteredPixelDungeon.switchScene(AlchemyScene.class);
			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actPickUp( HeroAction.PickUp action ) {
		int dst = action.dst;
		if (pos == dst) {

			Heap heap = Dungeon.level.heaps.get( pos );
			if (heap != null) {
				Item item = heap.peek();
				if (item.doPickUp( this )) {
					heap.pickUp();

					if (item instanceof Dewdrop
							|| item instanceof TimekeepersHourglass.sandBag
							|| item instanceof Key) {
						//Do Nothing
					} else {

						//TODO make all unique items important? or just POS / SOU?
						boolean important = item.unique && item.isIdentified() &&
								(item instanceof Scroll || item instanceof Potion);
						if (important) {
							GLog.p( Messages.get(this, "you_now_have", item.name()) );
						} else {
							GLog.i( Messages.get(this, "you_now_have", item.name()) );
						}
					}

					curAction = null;
				} else {

					if (item instanceof Dewdrop
							|| item instanceof TimekeepersHourglass.sandBag
							|| item instanceof Key) {
						//Do Nothing
					} else {
						GLog.newLine();
						GLog.n(Messages.get(this, "you_cant_have", item.name()));
					}

					heap.sprite.drop();
					ready();
				}
			} else {
				ready();
			}

			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actOpenChest( HeroAction.OpenChest action ) {
		int dst = action.dst;
		if (Dungeon.level.adjacent( pos, dst ) || pos == dst) {

			Heap heap = Dungeon.level.heaps.get( dst );
			if (heap != null && (heap.type != Type.HEAP && heap.type != Type.FOR_SALE)) {

				if ((heap.type == Type.LOCKED_CHEST && Notes.keyCount(new GoldenKey(Dungeon.depth)) < 1)
						|| (heap.type == Type.CRYSTAL_CHEST && Notes.keyCount(new CrystalKey(Dungeon.depth)) < 1)){

					GLog.w( Messages.get(this, "locked_chest") );
					ready();
					return false;

				}

				switch (heap.type) {
					case TOMB:
						Sample.INSTANCE.play( Assets.Sounds.TOMB );
						Camera.main.shake( 1, 0.5f );
						break;
					case SKELETON:
					case REMAINS:
						break;
					default:
						Sample.INSTANCE.play( Assets.Sounds.UNLOCK );
				}
				sprite.operate( dst );
			} else {
				ready();
			}

			return false;

		} else if (getCloser( dst )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actUnlock( HeroAction.Unlock action ) {
		int doorCell = action.dst;
		if (Dungeon.level.adjacent( pos, doorCell )) {

			boolean hasKey = false;
			int door = Dungeon.level.map[doorCell];

			if (door == Terrain.LOCKED_DOOR
					&& Notes.keyCount(new IronKey(Dungeon.depth)) > 0) {

				hasKey = true;

			} else if (door == Terrain.CRYSTAL_DOOR
					&& Notes.keyCount(new CrystalKey(Dungeon.depth)) > 0) {

				hasKey = true;

			} else if (door == Terrain.LOCKED_EXIT
					&& Notes.keyCount(new SkeletonKey(Dungeon.depth)) > 0) {

				hasKey = true;

			}

			if (hasKey) {

				sprite.operate( doorCell );

				Sample.INSTANCE.play( Assets.Sounds.UNLOCK );
			} else {
				GLog.w( Messages.get(this, "locked_door") );
				ready();
			}

			return false;

		} else if (getCloser( doorCell )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actTransition(HeroAction.LvlTransition action ) {
		int stairs = action.dst;
		LevelTransition transition = Dungeon.level.getTransition(stairs);

		if (rooted) {
			Camera.main.shake(1, 1f);
			ready();
			return false;
		} else if (transition != null && transition.inside(pos)) {

			if (transition.type == LevelTransition.Type.SURFACE){
				if (belongings.getItem( Amulet.class ) == null) {
					if (Dungeon.depth == 51){
						GLog.n( Messages.get(Reimu.class, "amulet") );
					} else {
						Game.runOnRenderThread(new Callback() {
							@Override
							public void call() {
								GameScene.show(new WndOptions(new Image(Dungeon.level.tilesTex(), 48, 48, 16, 16),
										Messages.get(Reimu.class, "chasm"),
										Messages.get(Reimu.class, "jump"),
										Messages.get(Reimu.class, "yes"),
										Messages.get(Reimu.class, "no")) {
									@Override
									protected void onSelect(int index) {
										if (index == 0) {
											ShatteredPixelDungeon.switchScene(TitleScene.class);
											Dungeon.deleteGame(GamesInProgress.curSlot, true);
										} else {
											// do nothing
										}
									}
								});
							}
						});
					}
					ready();
				} else {
					Dungeon.win( Amulet.class );
					Dungeon.deleteGame( GamesInProgress.curSlot, true );
					Game.switchScene( SurfaceScene.class );
				}

			} else {

				curAction = null;

				TimekeepersHourglass.timeFreeze timeFreeze = buff(TimekeepersHourglass.timeFreeze.class);
				if (timeFreeze != null) timeFreeze.disarmPressedTraps();
				Swiftthistle.TimeBubble timeBubble = buff(Swiftthistle.TimeBubble.class);
				if (timeBubble != null) timeBubble.disarmPressedTraps();

				InterlevelScene.curTransition = transition;
				//TODO probably want to make this more flexible when more types exist
				if (transition.type == LevelTransition.Type.REGULAR_ENTRANCE) {
					InterlevelScene.mode = InterlevelScene.Mode.ASCEND;
				} else {
					InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
				}
				Game.switchScene(InterlevelScene.class);

			}

			return false;

		} else if (getCloser( stairs )) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actAttack( HeroAction.Attack action ) {

		enemy = action.target;

		if (enemy.isAlive() && canAttack( enemy ) && !isCharmedBy( enemy )) {

			sprite.attack( enemy.pos );

			return false;

		} else {

			if (fieldOfView[enemy.pos] && getCloser( enemy.pos )) {

				return true;

			} else {
				ready();
				return false;
			}

		}
	}

	public Char enemy(){
		return enemy;
	}

	public void rest( boolean fullRest ) {
		spendAndNext( TIME_TO_REST );
		if (!fullRest) {
			if (sprite != null) {
				sprite.showStatus(CharSprite.DEFAULT, Messages.get(this, "wait"));
			}
		}
		resting = fullRest;
	}

	@Override
	public int attackProc( final Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );

		if (Dungeon.hero.buff(Powerful.class) != null && enemy.HT/2 > enemy.HP){
			Buff.prolong(enemy, Might.class, Might.DURATION/2f);
		}
		if (Dungeon.hero.buff(Cool.class) != null && enemy.HT/2 > enemy.HP){
			Buff.prolong(enemy, Hisou.class, Hisou.DURATION/2f);
		}
		if (Dungeon.hero.buff(Pure.class) != null && enemy.HT/2 > enemy.HP){
			Buff.prolong(enemy, Doublespeed.class, Doublespeed.DURATION/2f);
		}
		if (Dungeon.hero.buff(Happy.class) != null && enemy.HT/2 > enemy.HP){
			Buff.prolong(enemy, Doublerainbow.class, Doublerainbow.DURATION/4f);
		}

		if (buff(SupernaturalBorder.class) == null) {
			Statistics.bordercount += 1;
		}

		if (Dungeon.isChallenged(Challenges.TIME_EATER)){
			Statistics.timetrackbuff += 1;
		}

		if (Dungeon.hero.buff(YukariBorder.class) != null){
			Statistics.playercorruption += 1;
			Sample.INSTANCE.play(Assets.Sounds.CURSED);
			GLog.w(Messages.get(Potion.class, "corruption"));
		}

		if (Dungeon.hero.belongings.armor() instanceof YorihimeArmor && (Random.Int(75) == 0)){
			Buff.prolong(this, OneDefDamage.class, OneDefDamage.DURATION);
		}

		if (Dungeon.hero.belongings.armor() instanceof ToyohimeArmor && (Random.Int(75) == 0)){
		}

		if (buff(CursedBlow.class) != null){
			CursedWand.cursedEffect(null, this, enemy);
		}

		if (Statistics.card58 && Dungeon.hero.belongings.weapon() instanceof MissileWeapon){
			Buff.prolong(this, ReachIncrease.class, ReachIncrease.DURATION/2f);
		}

		if (Dungeon.hero.belongings.weapon() instanceof YuukaDanmaku){
			Ballistica trajectory = new Ballistica(this.pos, enemy.pos, Ballistica.STOP_TARGET);
			//trim it to just be the part that goes past them
			trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);
			//knock them back along that ballistica
			WandOfBlastWave.throwChar(enemy, trajectory, 5, false, true, getClass());
			Sample.INSTANCE.play(Assets.Sounds.CURSED);
			CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
			GLog.w(Messages.get(this, "fear"));
			return damage;
		}

		if (Statistics.card17) {
			Buff.prolong(enemy, Slow.class, Slow.DURATION/2f);
		}

		if (Statistics.card64 && Dungeon.hero.belongings.weapon() instanceof MissileWeapon && (Random.Int(4) == 0)){
			Buff.prolong(enemy, HighStress.class, HighStress.DURATION);
		}

		if (Statistics.card50 && Dungeon.hero.belongings.weapon() instanceof MissileWeapon){
			PathFinder.buildDistanceMap( enemy.pos, BArray.not( Dungeon.level.solid, null ), 2 );
			ArrayList<Integer> spawnPoints = new ArrayList<>();
			for (int i = 0; i < PathFinder.distance.length; i++) {
				if (PathFinder.distance[i] < Integer.MAX_VALUE) {
					spawnPoints.add(i);
				}
			}

			for (int i : spawnPoints){
				if (Dungeon.level.insideMap(i)
						&& Actor.findChar(i) == null
						&& !(Dungeon.level.pit[i])) {
					Sheep sheep = new Sheep();
					sheep.lifespan = 8;
					sheep.pos = i;
					GameScene.add(sheep);
					Dungeon.level.occupyCell(sheep);
					CellEmitter.get(i).burst(Speck.factory(Speck.WOOL), 4);
				}
			}

			CellEmitter.get(enemy.pos).burst(Speck.factory(Speck.WOOL), 4);
			Sample.INSTANCE.play(Assets.Sounds.PUFF);
			Sample.INSTANCE.play(Assets.Sounds.SHEEP);
		}

		if (Statistics.card51 && (Random.Int(2) == 0)){
			Buff.prolong(enemy, Weakness.class, Weakness.DURATION);
		}

		if (Statistics.card52 && (Random.Int(2) == 0)){
			Buff.prolong(enemy, Vulnerable.class, Vulnerable.DURATION);
		}

		if (Statistics.card53 && (Random.Int(2) == 0)){
			Buff.prolong(enemy, Hex.class, Hex.DURATION);
		}

		if (Statistics.card55 && (Random.Int(6) == 0) && enemy.HP > 3){
			enemy.HP /= 2;
		}

		KindOfWeapon wep = belongings.weapon();

		if (wep != null) damage = wep.proc( this, enemy, damage );

		return damage;
	}

	@Override
	public int defenseProc( Char enemy, int damage ) {

		if (Dungeon.hero.buff(HighStress.class) != null){
			Dungeon.hero.HP = 1;
		}

		if (Statistics.card33 && Random.Int(3) == 0){
			ScrollOfMirrorImage.spawnImages(this, 1);
		}

		if (buff(Lignification.class) != null){
			damage *= 0.75f;
		}

		if (Dungeon.level.map[this.pos] == Terrain.PEDESTAL){
			damage *= 0f;
		}

		if (buff(SupernaturalBorder.class) != null){
			damage *= 0f;
			Buff.detach(this, SupernaturalBorder.class);
			Sample.INSTANCE.play( Assets.Sounds.EVOKE );
		}

		if (belongings.armor() != null) {
			damage = belongings.armor().proc( enemy, this, damage );
		}

		Earthroot.Armor armor = buff( Earthroot.Armor.class );
		if (armor != null) {
			damage = armor.absorb( damage );
		}

		WandOfLivingEarth.RockArmor rockArmor = buff(WandOfLivingEarth.RockArmor.class);
		if (rockArmor != null) {
			damage = rockArmor.absorb(damage);
		}

		if (Dungeon.hero.belongings.armor() instanceof NitoriArmor && Dungeon.level.map[this.pos] == Terrain.WATER) {

		}

		if (Dungeon.hero.belongings.armor() instanceof SharkArmor && Dungeon.level.map[this.pos] == Terrain.WATER) {

		}

		if (Dungeon.hero.belongings.armor() instanceof MorfonicaArmor && Dungeon.level.map[this.pos] == Terrain.WATER) {

		}

		return damage;
	}

	@Override
	public void damage( int dmg, Object src ) {
		if (buff(TimekeepersHourglass.timeStasis.class) != null)
			return;

		if (Statistics.playercorruption == 1 && (Random.Int(100) == 0) ||
			Statistics.playercorruption == 2 && (Random.Int(80) == 0) ||
			Statistics.playercorruption == 3 && (Random.Int(60) == 0) ||
			Statistics.playercorruption == 4 && (Random.Int(40) == 0) ||
			Statistics.playercorruption == 5 && (Random.Int(20) == 0) ||
			Statistics.playercorruption == 6 && (Random.Int(10) == 0) ||
			Statistics.playercorruption == 7 && (Random.Int(7) == 0) ||
			Statistics.playercorruption == 8 && (Random.Int(4) == 0) ||
			Statistics.playercorruption == 9 && (Random.Int(3) == 0) ||
			Statistics.playercorruption == 10 && (Random.Int(2) == 0)) {
			dmg *= 2;
			Sample.INSTANCE.play( Assets.Sounds.CURSED );
		}

		if (!(src instanceof Hunger || src instanceof Viscosity.DeferedDamage) && damageInterrupt) {
			interrupt();
			resting = false;
		}

		if (this.buff(Drowsy.class) != null){
			Buff.detach(this, Drowsy.class);
			GLog.w( Messages.get(this, "pain_resist") );
		}

		CapeOfThorns.Thorns thorns = buff( CapeOfThorns.Thorns.class );
		if (thorns != null) {
			dmg = thorns.proc(dmg, (src instanceof Char ? (Char)src : null),  this);
		}

		dmg = (int)Math.ceil(dmg * RingOfTenacity.damageMultiplier( this ));

		//TODO improve this when I have proper damage source logic
		if (belongings.armor() != null && belongings.armor().hasGlyph(AntiMagic.class, this)
				&& AntiMagic.RESISTS.contains(src.getClass())){
			dmg -= AntiMagic.drRoll(belongings.armor().buffedLvl());
		}

		int preHP = HP + shielding();
		super.damage( dmg, src );
		int postHP = HP + shielding();
		int effectiveDamage = preHP - postHP;

		if (effectiveDamage <= 0) return;

		//flash red when hit for serious damage.
		float percentDMG = effectiveDamage / (float)preHP; //percent of current HP that was taken
		float percentHP = 1 - ((HT - postHP) / (float)HT); //percent health after damage was taken
		// The flash intensity increases primarily based on damage taken and secondarily on missing HP.
		float flashIntensity = 0.25f * (percentDMG * percentDMG) / percentHP;
		//if the intensity is very low don't flash at all
		if (flashIntensity >= 0.05f){
			flashIntensity = Math.min(1/3f, flashIntensity); //cap intensity at 1/3
			GameScene.flash( (int)(0xFF*flashIntensity) << 16 );
			if (isAlive()) {
				if (flashIntensity >= 1/6f) {
					Sample.INSTANCE.play(Assets.Sounds.HEALTH_CRITICAL, 1/3f + flashIntensity * 2f);
				} else {
					Sample.INSTANCE.play(Assets.Sounds.HEALTH_WARN, 1/3f + flashIntensity * 4f);
				}
			}
		}
	}

	public void checkVisibleMobs() {
		ArrayList<Mob> visible = new ArrayList<>();

		boolean newMob = false;

		Mob target = null;
		for (Mob m : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (fieldOfView[ m.pos ] && m.alignment == Alignment.ENEMY) {
				visible.add(m);
				if (!visibleEnemies.contains( m )) {
					newMob = true;
				}

				if (!mindVisionEnemies.contains(m) && QuickSlotButton.autoAim(m) != -1){
					if (target == null){
						target = m;
					} else if (distance(target) > distance(m)) {
						target = m;
					}
				}
			}
		}

		Char lastTarget = QuickSlotButton.lastTarget;
		if (target != null && (lastTarget == null ||
				!lastTarget.isAlive() ||
				lastTarget.alignment == Alignment.ALLY ||
				!fieldOfView[lastTarget.pos])){
			QuickSlotButton.target(target);
		}

		if (newMob) {
			interrupt();
			if (resting){
				Dungeon.observe();
				resting = false;
			}
		}

		visibleEnemies = visible;
	}

	public int visibleEnemies() {
		return visibleEnemies.size();
	}

	public Mob visibleEnemy( int index ) {
		return visibleEnemies.get(index % visibleEnemies.size());
	}

	public ArrayList<Mob> getVisibleEnemies(){
		return new ArrayList<>(visibleEnemies);
	}

	private boolean walkingToVisibleTrapInFog = false;

	//FIXME this is a fairly crude way to track this, really it would be nice to have a short
	//history of hero actions
	public boolean justMoved = false;

	private boolean getCloser( final int target ) {

		if (target == pos)
			return false;

		if (rooted) {
			Camera.main.shake( 1, 1f );
			return false;
		}

		int step = -1;

		if (Dungeon.level.adjacent( pos, target )) {

			path = null;

			if (Actor.findChar( target ) == null) {
				if (Dungeon.level.pit[target] && !flying && !Dungeon.level.solid[target]) {
					if (!Chasm.jumpConfirmed){
						Chasm.heroJump(this);
						interrupt();
					} else {
						Chasm.heroFall(target);
					}
					return false;
				}
				if (Dungeon.level.passable[target] || Dungeon.level.avoid[target]) {
					step = target;
				}
				if (walkingToVisibleTrapInFog
						&& Dungeon.level.traps.get(target) != null
						&& Dungeon.level.traps.get(target).visible){
					return false;
				}
			}

		} else {

			boolean newPath = false;
			if (path == null || path.isEmpty() || !Dungeon.level.adjacent(pos, path.getFirst()))
				newPath = true;
			else if (path.getLast() != target)
				newPath = true;
			else {
				if (!Dungeon.level.passable[path.get(0)] || Actor.findChar(path.get(0)) != null) {
					newPath = true;
				}
			}

			if (newPath) {

				int len = Dungeon.level.length();
				boolean[] p = Dungeon.level.passable;
				boolean[] v = Dungeon.level.visited;
				boolean[] m = Dungeon.level.mapped;
				boolean[] passable = new boolean[len];
				for (int i = 0; i < len; i++) {
					passable[i] = p[i] && (v[i] || m[i]);
				}

				PathFinder.Path newpath = Dungeon.findPath(this, target, passable, fieldOfView, true);
				if (newpath != null && path != null && newpath.size() > 2*path.size()){
					path = null;
				} else {
					path = newpath;
				}
			}

			if (path == null) return false;
			step = path.removeFirst();

		}

		if (step != -1) {
			float speed = speed();

			sprite.move(pos, step);
			move(step);

			spend( 1 / speed );
			justMoved = true;

			search(false);

			return true;

		} else {

			return false;

		}

	}

	public boolean handle( int cell ) {

		if (cell == -1) {
			return false;
		}

		if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
			fieldOfView = new boolean[Dungeon.level.length()];
			Dungeon.level.updateFieldOfView( this, fieldOfView );
		}

		Char ch = Actor.findChar( cell );
		Heap heap = Dungeon.level.heaps.get( cell );

		if (Dungeon.level.map[cell] == Terrain.ALCHEMY && cell != pos) {

			curAction = new HeroAction.Alchemy( cell );

		} else if (fieldOfView[cell] && ch instanceof Mob) {

			if (ch.alignment != Alignment.ENEMY && ch.buff(Amok.class) == null) {
				curAction = new HeroAction.Interact( ch );
			} else {
				curAction = new HeroAction.Attack( ch );
			}

		} else if (heap != null
				//moving to an item doesn't auto-pickup when enemies are near...
				&& (visibleEnemies.size() == 0 || cell == pos ||
				//...but only for standard heaps, chests and similar open as normal.
				(heap.type != Type.HEAP && heap.type != Type.FOR_SALE))) {

			switch (heap.type) {
				case HEAP:
					curAction = new HeroAction.PickUp( cell );
					break;
				case FOR_SALE:
					curAction = heap.size() == 1 && heap.peek().value() > 0 ?
							new HeroAction.Buy( cell ) :
							new HeroAction.PickUp( cell );
					break;
				default:
					curAction = new HeroAction.OpenChest( cell );
			}

		} else if (Dungeon.level.map[cell] == Terrain.LOCKED_DOOR || Dungeon.level.map[cell] == Terrain.CRYSTAL_DOOR || Dungeon.level.map[cell] == Terrain.LOCKED_EXIT) {

			curAction = new HeroAction.Unlock( cell );

		} else if (Dungeon.level.getTransition(cell) != null
				&& !Dungeon.level.locked
				&& (Dungeon.depth < 52 || Dungeon.level.getTransition(cell).type == LevelTransition.Type.REGULAR_EXIT) ) {

			curAction = new HeroAction.LvlTransition( cell );

		}  else {

			if (!Dungeon.level.visited[cell] && !Dungeon.level.mapped[cell]
					&& Dungeon.level.traps.get(cell) != null && Dungeon.level.traps.get(cell).visible) {
				walkingToVisibleTrapInFog = true;
			} else {
				walkingToVisibleTrapInFog = false;
			}

			curAction = new HeroAction.Move( cell );
			lastAction = null;

		}

		return true;
	}

	public void earnExp( int exp, Class source ) {

		this.exp += exp;
		float percent = exp/(float)maxExp();

		EtherealChains.chainsRecharge chains = buff(EtherealChains.chainsRecharge.class);
		if (chains != null) chains.gainExp(percent);

		HornOfPlenty.hornRecharge horn = buff(HornOfPlenty.hornRecharge.class);
		if (horn != null) horn.gainCharge(percent);

		AlchemistsToolkit.kitEnergy kit = buff(AlchemistsToolkit.kitEnergy.class);
		if (kit != null) kit.gainCharge(percent);

		MasterThievesArmband.Thievery armband = buff(MasterThievesArmband.Thievery.class);
		if (armband != null) armband.gainCharge(percent);

		Berserk berserk = buff(Berserk.class);
		if (berserk != null) berserk.recover(percent);

		if (source != PotionOfExperience.class) {
			for (Item i : belongings) {
				i.onHeroGainExp(percent, this);
			}
		}

		boolean levelUp = false;
		while (this.exp >= maxExp()) {
			this.exp -= maxExp();
			if (lvl < MAX_LEVEL) {
				lvl++;
				levelUp = true;

				if (buff(ElixirOfMight.HTBoost.class) != null){
					buff(ElixirOfMight.HTBoost.class).onLevelUp();
				}

				updateHT( true );
				attackSkill++;
				defenseSkill++;

			} else {
				Buff.prolong(this, Bless.class, Bless.DURATION);
				this.exp = 0;

				GLog.newLine();
				GLog.p( Messages.get(this, "level_cap"));
				Sample.INSTANCE.play( Assets.Sounds.LEVELUP );
			}

		}

		if (levelUp) {

			if (sprite != null) {
				GLog.newLine();
				GLog.p( Messages.get(this, "new_level") );
				sprite.showStatus( CharSprite.POSITIVE, Messages.get(Hero.class, "level_up") );
				Sample.INSTANCE.play( Assets.Sounds.LEVELUP );
			}

			Item.updateQuickslot();
		}
	}

	public int maxExp() {
		return maxExp( lvl );
	}

	public static int maxExp( int lvl ){
		return 5 + lvl * 5;
	}

	public boolean isStarving() {
		return Buff.affect(this, Hunger.class).isStarving();
	}

	@Override
	public void add( Buff buff ) {

		if (buff(TimekeepersHourglass.timeStasis.class) != null)
			return;

		super.add( buff );

		if (sprite != null && buffs().contains(buff)) {
			String msg = buff.heroMessage();
			if (msg != null){
				GLog.w(msg);
			}

			if (buff instanceof Paralysis || buff instanceof Vertigo) {
				interrupt();
			}

		}

		BuffIndicator.refreshHero();
	}

	@Override
	public void remove( Buff buff ) {
		super.remove( buff );

		BuffIndicator.refreshHero();
	}

	@Override
	public float stealth() {
		float stealth = super.stealth();

		if (belongings.armor() != null){
			stealth = belongings.armor().stealthFactor(this, stealth);
		}

		return stealth;
	}

	@Override
	public void die( Object cause ) {

		curAction = null;

		Statistics.lifelose = true;

		if (Statistics.card29 && Statistics.spellcard > 1){
			interrupt();
			resting = false;
			Statistics.spellcard -= 2;
			if (Statistics.card34){
				Statistics.spellcard += 3;
			}
			this.HP = HT / 4;
			PotionOfHealing.cure(this);
			Buff.prolong(this, AnkhInvulnerability.class, AnkhInvulnerability.DURATION/2f);

			SpellSprite.show(this, SpellSprite.ANKH);
			GameScene.flash(0x80FFFF40);
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
			GLog.w(Messages.get(this, "revive"));
			Statistics.ankhsUsed++;

			for (Char ch : Actor.chars()) {
			}
			return;
		} else if (Statistics.card38 && Dungeon.gold > 499){
			interrupt();
			resting = false;
			Dungeon.gold -= 500;
			if (Statistics.card34){
				Statistics.spellcard += 3;
			}
			this.HP = HT / 4;
			PotionOfHealing.cure(this);
			Buff.prolong(this, AnkhInvulnerability.class, AnkhInvulnerability.DURATION/2f);

			SpellSprite.show(this, SpellSprite.ANKH);
			GameScene.flash(0x80FFFF40);
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
			GLog.w(Messages.get(this, "revive"));
			Statistics.ankhsUsed++;

			for (Char ch : Actor.chars()) {
			}
			return;
		} else if (Statistics.life > 0) {
			interrupt();
			resting = false;
			Statistics.life -= 1;
			if (Statistics.card34){
				Statistics.spellcard += 3;
			}
			this.HP = HT / 4;
			PotionOfHealing.cure(this);
			Buff.prolong(this, AnkhInvulnerability.class, AnkhInvulnerability.DURATION/2f);

			SpellSprite.show(this, SpellSprite.ANKH);
			GameScene.flash(0x80FFFF40);
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
			GLog.w(Messages.get(this, "revive"));
			Statistics.ankhsUsed++;

			for (Char ch : Actor.chars()) {
			}
			return;
		}

		Actor.fixTime();
		super.die( cause );
		reallyDie( cause );
	}

	public static void reallyDie( Object cause ) {

		int length = Dungeon.level.length();
		int[] map = Dungeon.level.map;
		boolean[] visited = Dungeon.level.visited;
		boolean[] discoverable = Dungeon.level.discoverable;

		for (int i=0; i < length; i++) {

			int terr = map[i];

			if (discoverable[i]) {

				visited[i] = true;
				if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {
					Dungeon.level.discover( i );
				}
			}
		}

		Dungeon.observe();
		GameScene.updateFog();

		Dungeon.hero.belongings.identify();

		int pos = Dungeon.hero.pos;

		ArrayList<Integer> passable = new ArrayList<>();
		for (Integer ofs : PathFinder.NEIGHBOURS8) {
			int cell = pos + ofs;
			if ((Dungeon.level.passable[cell] || Dungeon.level.avoid[cell]) && Dungeon.level.heaps.get( cell ) == null) {
				passable.add( cell );
			}
		}
		Collections.shuffle( passable );

		ArrayList<Item> items = new ArrayList<>(Dungeon.hero.belongings.backpack.items);
		for (Integer cell : passable) {
			if (items.isEmpty()) {
				break;
			}

			Item item = Random.element( items );
			Dungeon.level.drop( item, cell ).sprite.drop( pos );
			items.remove( item );
		}

		for (Char c : Actor.chars()){
		}

		Game.runOnRenderThread(new Callback() {
			@Override
			public void call() {
				GameScene.gameOver();
				Sample.INSTANCE.play( Assets.Sounds.DEATH );
			}
		});

		if (cause instanceof Hero.Doom) {
			((Hero.Doom)cause).onDeath();
		}

		Dungeon.deleteGame( GamesInProgress.curSlot, true );
	}

	//effectively cache this buff to prevent having to call buff(...) a bunch.
	//This is relevant because we call isAlive during drawing, which has both performance
	//and thread coordination implications if that method calls buff(...) frequently
	private Berserk berserk;

	@Override
	public boolean isAlive() {
		if (Dungeon.isChallenged(Challenges.INVINCIBLE_GENSOKYO)) {
			if (Statistics.mood == 0) {
				Buff.prolong(this, Powerful.class, Powerful.DURATION);
				Buff.detach( this, Cool.class);
				Buff.detach( this, Pure.class);
				Buff.detach( this, Happy.class);
			}
			if (Statistics.mood == 1) {
				Buff.prolong(this, Cool.class, Cool.DURATION);
				Buff.detach( this, Powerful.class);
				Buff.detach( this, Pure.class);
				Buff.detach( this, Happy.class);
			}
			if (Statistics.mood == 2) {
				Buff.prolong(this, Pure.class, Pure.DURATION);
				Buff.detach( this, Cool.class);
				Buff.detach( this, Powerful.class);
				Buff.detach( this, Happy.class);
			}
			if (Statistics.mood == 3) {
				Buff.prolong(this, Happy.class, Happy.DURATION);
				Buff.detach( this, Cool.class);
				Buff.detach( this, Powerful.class);
				Buff.detach( this, Pure.class);
			}
		}

		if (Statistics.mood == 4){
			Statistics.mood = 0;
		}

		if (Statistics.bordercount == 50){
			Statistics.bordercount = 0;
			GLog.n(Messages.get(this, "border"));
			Buff.prolong(this, AnkhInvulnerability.class, AnkhInvulnerability.DURATION/6f);
			Buff.prolong(this, SupernaturalBorder.class, SupernaturalBorder.DURATION);
		}

		if (Statistics.value > 499 && !Statistics.scorelife1){
			Statistics.life += 1;
			Statistics.scorelife1 = true;
			Statistics.nextvalue = 1400;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}
		if (Statistics.value > 1399 && !Statistics.scorelife2){
			Statistics.life += 1;
			Statistics.scorelife2 = true;
			Statistics.nextvalue = 2500;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}
		if (Statistics.value > 2499 && !Statistics.scorelife3){
			Statistics.life += 1;
			Statistics.scorelife3 = true;
			Statistics.nextvalue = 4000;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}
		if (Statistics.value > 3999 && !Statistics.scorelife4){
			Statistics.life += 1;
			Statistics.scorelife4 = true;
			Statistics.nextvalue = 5400;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}
		if (Statistics.value > 5399 && !Statistics.scorelife5){
			Statistics.life += 1;
			Statistics.scorelife5 = true;
			Statistics.nextvalue = 6800;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}
		if (Statistics.value > 6799 && !Statistics.scorelife6){
			Statistics.life += 1;
			Statistics.scorelife6 = true;
			Statistics.nextvalue = 8200;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}
		if (Statistics.value > 8199 && !Statistics.scorelife7){
			Statistics.life += 1;
			Statistics.scorelife7 = true;
			Statistics.nextvalue = 9999;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}
		if (Statistics.value > 9998 && !Statistics.scorelife8){
			Statistics.life += 1;
			Statistics.scorelife8 = true;
			Statistics.nextvalue = 0;
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
		}

		if (Statistics.timetrackbuff == 12) {
			Statistics.timetrackbuff = 0;
			Buff.prolong(this, Paralysis.class, Paralysis.DURATION/10f);
			Statistics.timetrackstrup += 1;
		}

		if (Statistics.power < 100){
			Statistics.power = 100;
		}
		if (Statistics.power > 400){
			Statistics.power = 400;
		}
		if (Statistics.lifefragment == 8){
			Statistics.lifefragment = 0;
			Statistics.life += 1;
		}
		if (Statistics.spellcardfragment == 8){
			Statistics.spellcardfragment = 0;
			Statistics.spellcard += 1;
		}
		if (Statistics.life > 8){
			Statistics.life = 8;
		}
		if (Statistics.spellcard > 8){
			Statistics.spellcard = 8;
		}
		if (Statistics.playercorruption > 10){
			Statistics.playercorruption = 10;
		}
		if (Statistics.playercorruption < 0){
			Statistics.playercorruption = 0;
		}

		if (HP <= 0){
			if (berserk == null) berserk = buff(Berserk.class);
			return berserk != null && berserk.berserking();
		} else {
			berserk = null;
			return super.isAlive();
		}
	}

	@Override
	public void move(int step, boolean travelling) {
		boolean wasHighGrass = Dungeon.level.map[step] == Terrain.HIGH_GRASS;

		//if (Dungeon.depth == 5 && !Statistics.boss1) {
			//Dungeon.level.seal();
			//Statistics.boss1 = true;
		//}
		//if (Dungeon.depth == 10 && !Statistics.boss2) {
			//Dungeon.level.seal();
			//Statistics.boss2 = true;
		//}
		//if (Dungeon.depth == 15 && !Statistics.boss3) {
			//Dungeon.level.seal();
			//Statistics.boss3 = true;
		//}
		//if (Dungeon.depth == 20 && !Statistics.boss4) {
			//Dungeon.level.seal();
			//Statistics.boss4 = true;
		//}
		//if (Dungeon.depth == 25 && !Statistics.boss5) {
			//Dungeon.level.seal();
			//Statistics.boss5 = true;
		//}
		//if (Dungeon.depth == 30 && !Statistics.boss6) {
			//Dungeon.level.seal();
			//Statistics.boss6 = true;
		//}
		//if (Dungeon.depth == 35 && !Statistics.boss7) {
			//Dungeon.level.seal();
			//Statistics.boss7 = true;
		//}
		//if (Dungeon.depth == 40 && !Statistics.boss8) {
			//Dungeon.level.seal();
			//Statistics.boss8 = true;
		//}
		//if (Dungeon.depth == 45 && !Statistics.boss9) {
			//Dungeon.level.seal();
			//Statistics.boss9 = true;
		//}
		//if (Dungeon.depth == 50 && !Statistics.boss10) {
			//Dungeon.level.seal();
			//Statistics.boss10 = true;
		//}

		super.move( step, travelling);

		if (!flying && travelling) {
			if (Dungeon.level.water[pos]) {
				Sample.INSTANCE.play( Assets.Sounds.WATER, 1, Random.Float( 0.8f, 1.25f ) );
			} else if (Dungeon.level.map[pos] == Terrain.EMPTY_SP) {
				Sample.INSTANCE.play( Assets.Sounds.STURDY, 1, Random.Float( 0.96f, 1.05f ) );
			} else if (Dungeon.level.map[pos] == Terrain.GRASS
					|| Dungeon.level.map[pos] == Terrain.EMBERS
					|| Dungeon.level.map[pos] == Terrain.FURROWED_GRASS){
				if (step == pos && wasHighGrass) {
					Sample.INSTANCE.play(Assets.Sounds.TRAMPLE, 1, Random.Float( 0.96f, 1.05f ) );
				} else {
					Sample.INSTANCE.play( Assets.Sounds.GRASS, 1, Random.Float( 0.96f, 1.05f ) );
				}
			} else {
				Sample.INSTANCE.play( Assets.Sounds.STEP, 1, Random.Float( 0.96f, 1.05f ) );
			}
		}
	}

	@Override
	public void onAttackComplete() {

		AttackIndicator.target(enemy);

		boolean hit = attack( enemy );

		Buff.detach( this, Calm.class);

		Invisibility.dispel();
		spend( attackDelay() );

		curAction = null;

		super.onAttackComplete();
	}

	@Override
	public void onMotionComplete() {
		GameScene.checkKeyHold();
	}

	@Override
	public void onOperateComplete() {
		if (curAction instanceof HeroAction.Unlock) {

			int doorCell = ((HeroAction.Unlock)curAction).dst;
			int door = Dungeon.level.map[doorCell];

			if (Dungeon.level.distance(pos, doorCell) <= 1) {
				boolean hasKey = true;
				if (door == Terrain.LOCKED_DOOR) {
					hasKey = Notes.remove(new IronKey(Dungeon.depth));
					if (hasKey) Level.set(doorCell, Terrain.DOOR);
				} else if (door == Terrain.CRYSTAL_DOOR) {
					hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
					if (hasKey) {
						Level.set(doorCell, Terrain.EMPTY);
						Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
						CellEmitter.get( doorCell ).start( Speck.factory( Speck.DISCOVER ), 0.025f, 20 );
					}
				} else {
					hasKey = Notes.remove(new SkeletonKey(Dungeon.depth));
					if (hasKey) Level.set(doorCell, Terrain.UNLOCKED_EXIT);
				}

				if (hasKey) {
					GameScene.updateKeyDisplay();
					GameScene.updateMap(doorCell);
					spend(Key.TIME_TO_UNLOCK);
				}
			}

		} else if (curAction instanceof HeroAction.OpenChest) {

			Heap heap = Dungeon.level.heaps.get( ((HeroAction.OpenChest)curAction).dst );

			if (Dungeon.level.distance(pos, heap.pos) <= 1){
				boolean hasKey = true;
				if (heap.type == Type.SKELETON || heap.type == Type.REMAINS) {
					Sample.INSTANCE.play( Assets.Sounds.BONES );
				} else if (heap.type == Type.LOCKED_CHEST){
					hasKey = Notes.remove(new GoldenKey(Dungeon.depth));
				} else if (heap.type == Type.CRYSTAL_CHEST){
					hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
				}

				if (hasKey) {
					GameScene.updateKeyDisplay();
					heap.open(this);
					spend(Key.TIME_TO_UNLOCK);
				}
			}

		}
		curAction = null;

		super.onOperateComplete();
	}

	@Override
	public boolean isImmune(Class effect) {
		if (effect == Burning.class
				&& belongings.armor() != null
				&& belongings.armor().hasGlyph(Brimstone.class, this)){
			return true;
		}
		return super.isImmune(effect);
	}

	@Override
	public boolean isInvulnerable(Class effect) {
		return (buff(AnkhInvulnerability.class) != null || Dungeon.level.map[this.pos] == Terrain.PEDESTAL);
	}

	public boolean search( boolean intentional ) {

		if (!isAlive()) return false;

		boolean smthFound = false;

		int distance = Statistics.card23 ? 3 : 1;

		boolean foresight = buff(Foresight.class) != null;
		boolean foresightScan = foresight && !Dungeon.level.mapped[pos];

		if (foresightScan){
			Dungeon.level.mapped[pos] = true;
		}

		if (foresight) {
			distance = Foresight.DISTANCE;
		}

		Point c = Dungeon.level.cellToPoint(pos);

		TalismanOfForesight.Foresight talisman = buff( TalismanOfForesight.Foresight.class );
		boolean cursed = talisman != null && talisman.isCursed();

		int[] rounding = ShadowCaster.rounding[distance];

		int left, right;
		int curr;
		for (int y = Math.max(0, c.y - distance); y <= Math.min(Dungeon.level.height()-1, c.y + distance); y++) {
			if (rounding[Math.abs(c.y - y)] < Math.abs(c.y - y)) {
				left = c.x - rounding[Math.abs(c.y - y)];
			} else {
				left = distance;
				while (rounding[left] < rounding[Math.abs(c.y - y)]){
					left--;
				}
				left = c.x - left;
			}
			right = Math.min(Dungeon.level.width()-1, c.x + c.x - left);
			left = Math.max(0, left);
			for (curr = left + y * Dungeon.level.width(); curr <= right + y * Dungeon.level.width(); curr++){

				if ((foresight || fieldOfView[curr]) && curr != pos) {

					if ((foresight && (!Dungeon.level.mapped[curr] || foresightScan))){
						GameScene.effectOverFog(new CheckedCell(curr, foresightScan ? pos : curr));
					} else if (intentional) {
						GameScene.effectOverFog(new CheckedCell(curr, pos));
					}

					if (foresight){
						Dungeon.level.mapped[curr] = true;
					}

					if (Dungeon.level.secret[curr]){

						Trap trap = Dungeon.level.traps.get( curr );
						float chance;

						//searches aided by foresight always succeed, even if trap isn't searchable
						if (foresight){
							chance = 1f;

							//otherwise if the trap isn't searchable, searching always fails
						} else if (trap != null && !trap.canBeSearched){
							chance = 0f;

							//intentional searches always succeed against regular traps and doors
						} else if (intentional){
							chance = 1f;

							//unintentional searches always fail with a cursed talisman
						} else if (cursed) {
							chance = 0f;

							//unintentional trap detection scales from 40% at floor 0 to 30% at floor 25
						} else if (Dungeon.level.map[curr] == Terrain.SECRET_TRAP) {
							chance = 0.4f - (Dungeon.depth / 250f);

							//unintentional door detection scales from 20% at floor 0 to 0% at floor 20
						} else {
							chance = 0.2f - (Dungeon.depth / 100f);
						}

						if (Random.Float() < chance) {

							int oldValue = Dungeon.level.map[curr];

							GameScene.discoverTile( curr, oldValue );

							Dungeon.level.discover( curr );

							ScrollOfMagicMapping.discover( curr );

							if (fieldOfView[curr]) smthFound = true;

							if (talisman != null){
								if (oldValue == Terrain.SECRET_TRAP){
									talisman.charge(2);
								} else if (oldValue == Terrain.SECRET_DOOR){
									talisman.charge(10);
								}
							}
						}
					}
				}
			}
		}

		if (intentional) {
			sprite.showStatus( CharSprite.DEFAULT, Messages.get(this, "search") );
			sprite.operate( pos );
			if (!Dungeon.level.locked) {
				if (cursed) {
					GLog.n(Messages.get(this, "search_distracted"));
					Buff.affect(this, Hunger.class).affectHunger(TIME_TO_SEARCH - (2 * HUNGER_FOR_SEARCH));
				} else {
					Buff.affect(this, Hunger.class).affectHunger(TIME_TO_SEARCH - HUNGER_FOR_SEARCH);
				}
			}
			spendAndNext(TIME_TO_SEARCH);

		}

		if (smthFound) {
			GLog.w( Messages.get(this, "noticed_smth") );
			Sample.INSTANCE.play( Assets.Sounds.SECRET );
			interrupt();
		}

		if (foresight){
			GameScene.updateFog(pos, Foresight.DISTANCE+1);
		}

		return smthFound;
	}

	public void resurrect() {
		HP = HT;
		live();

		MagicalHolster holster = belongings.getItem(MagicalHolster.class);

		Buff.affect(this, LostInventory.class);
		Buff.affect(this, Invisibility.class, 3f);
		//lost inventory is dropped in interlevelscene

		//activate items that persist after lost inventory
		//FIXME this is very messy, maybe it would be better to just have one buff that
		// handled all items that recharge over time?
		for (Item i : belongings){
			if (i instanceof EquipableItem && i.isEquipped(this)){
				((EquipableItem) i).activate(this);
			} else if (i instanceof Wand && i.keptThoughLostInvent){
				if (holster != null && holster.contains(i)){
					((Wand) i).charge(this, MagicalHolster.HOLSTER_SCALE_FACTOR);
				} else {
					((Wand) i).charge(this);
				}
			} else if (i instanceof MarisaStaff && i.keptThoughLostInvent){
				((MarisaStaff) i).applyWandChargeBuff(this);
			}
		}

		updateHT(false);
	}

	@Override
	public void next() {
		if (isAlive())
			super.next();
	}

	public static interface Doom {
		public void onDeath();
	}
}