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

package com.touhoupixel.touhoupixeldungeonreloaded.items.wands;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.npcs.NPC;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.MagicMissile;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.RedCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.cubes.WhiteCubeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.WardSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfWarding extends Wand {

	{
		image = ItemSpriteSheet.WAND_WARDING;
	}

	@Override
	public int collisionProperties(int target) {
		if (Dungeon.level.heroFOV[target])  return Ballistica.STOP_TARGET;
		else                                return Ballistica.PROJECTILE;
	}

	private boolean wardAvailable = true;
	
	@Override
	public boolean tryToZap(Hero owner, int target) {
		
		int currentWardEnergy = 0;
		for (Char ch : Actor.chars()){
			if (ch instanceof Ward){
				currentWardEnergy += ((Ward) ch).tier;
			}
		}
		
		int maxWardEnergy = 0;
		for (Buff buff : curUser.buffs()){
			if (buff instanceof Charger){
				if (((Charger) buff).wand() instanceof WandOfWarding){
					maxWardEnergy += 2 + ((Charger) buff).wand().level();
				}
			}
		}
		
		wardAvailable = (currentWardEnergy < maxWardEnergy);
		
		Char ch = Actor.findChar(target);
		if (ch instanceof Ward){
			if (!wardAvailable && ((Ward) ch).tier <= 3){
				GLog.w( Messages.get(this, "no_more_wards"));
				return false;
			}
		} else {
			if ((currentWardEnergy + 1) > maxWardEnergy){
				GLog.w( Messages.get(this, "no_more_wards"));
				return false;
			}
		}
		
		return super.tryToZap(owner, target);
	}
	
	@Override
	public void onZap(Ballistica bolt) {

		int target = bolt.collisionPos;
		Char ch = Actor.findChar(target);
		if (ch != null && !(ch instanceof Ward)){
			if (bolt.dist > 1) target = bolt.path.get(bolt.dist-1);

			ch = Actor.findChar(target);
			if (ch != null && !(ch instanceof Ward)){
				GLog.w( Messages.get(this, "bad_location"));
				Dungeon.level.pressCell(bolt.collisionPos);
				return;
			}
		}

		if (!Dungeon.level.passable[target]){
			GLog.w( Messages.get(this, "bad_location"));
			Dungeon.level.pressCell(target);
			
		} else if (ch != null){
			if (ch instanceof Ward){
				if (wardAvailable) {
					((Ward) ch).upgrade( buffedLvl() );
				} else {
					((Ward) ch).wandHeal( buffedLvl() );
				}
				ch.sprite.emitter().burst(MagicMissile.WardParticle.UP, ((Ward) ch).tier);
			} else {
				GLog.w( Messages.get(this, "bad_location"));
				Dungeon.level.pressCell(target);
			}
			
		} else {
			Ward ward = new Ward();
			ward.pos = target;
			ward.wandLevel = buffedLvl();
			GameScene.add(ward, 1f);
			Dungeon.level.occupyCell(ward);
			ward.sprite.emitter().burst(MagicMissile.WardParticle.UP, ward.tier);
			Dungeon.level.pressCell(target);

		}
	}
	public boolean areEnoughCubes(){
		RedCubeFragment redCubeFragment = Dungeon.heroine.belongings.getItem(RedCubeFragment.class);

		if (redCubeFragment != null && redCubeFragment.quantity() > 1){
			return true;
		}
		else return false;
	}

	protected void spendColourCubes() {
		RedCubeFragment redCubeFragment = Dungeon.heroine.belongings.getItem(RedCubeFragment.class);

		redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
		redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
	}

	@Override
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile m = MagicMissile.boltFromChar(curUser.sprite.parent,
				MagicMissile.WARD,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		
		if (bolt.dist > 10){
			m.setSpeed(bolt.dist*20);
		}
		Sample.INSTANCE.play(Assets.Sounds.ZAP);
	}

	@Override
	public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {

		int level = Math.max( 0, staff.buffedLvl() );

		// lvl 0 - 20%
		// lvl 1 - 33%
		// lvl 2 - 43%
		if (Random.Int( level + 5 ) >= 4) {
			for (Char ch : Actor.chars()){
				if (ch instanceof Ward){
					((Ward) ch).wandHeal(staff.buffedLvl());
					ch.sprite.emitter().burst(MagicMissile.WardParticle.UP, ((Ward) ch).tier);
				}
			}
		}
	}

	@Override
	public void staffFx(MarisaStaff.StaffParticle particle) {
		particle.color( 0x8822FF );
		particle.am = 0.3f;
		particle.setLifespan(3f);
		particle.speed.polar(Random.Float(PointF.PI2), 0.3f);
		particle.setSize( 1f, 2f);
		particle.radiateXY(2.5f);
	}

	@Override
	public String statsDesc() {
		if (levelKnown)
			return Messages.get(this, "stats_desc", level()+2);
		else
			return Messages.get(this, "stats_desc", 2);
	}

	public static class Ward extends NPC {

		public int tier = 1;
		private int wandLevel = 1;

		public int totalZaps = 0;

		{
			spriteClass = WardSprite.class;

			alignment = Alignment.ALLY;

			properties.add(Property.IMMOVABLE);

			viewDistance = 4;
			state = WANDERING;
		}

		@Override
		public String name() {
			return Messages.get(this, "name_" + tier );
		}

		public void upgrade(int wandLevel ){
			if (this.wandLevel < wandLevel){
				this.wandLevel = wandLevel;
			}

			switch (tier){
				case 1: case 2: default:
					break; //do nothing
				case 3:
					HT = 35;
					HP = 15 + (5-totalZaps)*4;
					break;
				case 4:
					HT = 54;
					HP += 19;
					break;
				case 5:
					HT = 84;
					HP += 30;
					break;
				case 6:
					wandHeal(wandLevel);
					break;
			}

			if (tier < 6){
				tier++;
				viewDistance++;
				if (sprite != null){
					((WardSprite)sprite).updateTier(tier);
					sprite.place(pos);
				}
				GameScene.updateFog(pos, viewDistance+1);
			}

		}

		public void wandHeal( int wandLevel ){
			wandHeal( wandLevel, 1f );
		}

		public void wandHeal( int wandLevel, float healFactor ){
			if (this.wandLevel < wandLevel){
				this.wandLevel = wandLevel;
			}

			int heal;
			switch(tier){
				default:
					return;
				case 4:
					heal = Math.round(9 * healFactor);
					break;
				case 5:
					heal = Math.round(12 * healFactor);
					break;
				case 6:
					heal = Math.round(16 * healFactor);
					break;
			}

			HP = Math.min(HT, HP+heal);
			if (sprite != null) sprite.showStatus(CharSprite.POSITIVE, Integer.toString(heal));

		}

		@Override
		public int defenseSkill(Char enemy) {
			if (tier > 3){
				defenseSkill = 4 + Dungeon.floor;
			}
			return super.defenseSkill(enemy);
		}

		@Override
		public int drRoll() {
			if (tier > 3){
				return Math.round(Random.NormalIntRange(0, 3 + Dungeon.floor /2) / (7f - tier));
			} else {
				return 0;
			}
		}

		@Override
		protected boolean canAttack( Char enemy ) {
			return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
		}

		@Override
		protected boolean doAttack(Char enemy) {
			boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
			if (visible) {
				sprite.zap( enemy.pos );
			} else {
				zap();
			}

			return !visible;
		}

		private void zap() {
			spend( 1f );

			//always hits
			int dmg = Random.NormalIntRange( Dungeon.scalingFloor(), 3 + 6*(Dungeon.scalingFloor()-1) );
			if (hasProp(enemy, Property.BOSS) || hasProp(enemy, Property.MINIBOSS)) dmg /= 4;
			enemy.damage( dmg, this );
			if (enemy.isAlive()){
				Wand.wandProc(enemy, wandLevel, 1);
			}

			if (!enemy.isAlive() && enemy == Dungeon.heroine) {
				Dungeon.fail( getClass() );
			}

			totalZaps++;
			switch(tier){
				case 1: case 2: case 3: default:
					if (totalZaps >= (2*tier-1)){
						die(this);
					}
					break;
				case 4:
					damage(5, this);
					break;
				case 5:
					damage(6, this);
					break;
				case 6:
					damage(7, this);
					break;
			}
		}

		public void onZapComplete() {
			zap();
			next();
		}

		@Override
		protected boolean getCloser(int target) {
			return false;
		}

		@Override
		protected boolean getFurther(int target) {
			return false;
		}

		@Override
		public CharSprite sprite() {
			WardSprite sprite = (WardSprite) super.sprite();
			sprite.linkVisuals(this);
			return sprite;
		}

		@Override
		public void updateSpriteState() {
			super.updateSpriteState();
			((WardSprite)sprite).updateTier(tier);
			sprite.place(pos);
		}
		
		@Override
		public void destroy() {
			super.destroy();
			Dungeon.observe();
			GameScene.updateFog(pos, viewDistance+1);
		}
		
		@Override
		public boolean canInteract(Char c) {
			return true;
		}

		@Override
		public boolean interact( Char c ) {
			if (c != Dungeon.heroine){
				return true;
			}
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions( sprite(),
							Messages.get(Ward.this, "dismiss_title"),
							Messages.get(Ward.this, "dismiss_body"),
							Messages.get(Ward.this, "dismiss_confirm"),
							Messages.get(Ward.this, "dismiss_cancel") ){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								die(null);
							}
						}
					});
				}
			});
			return true;
		}

		@Override
		public String description() {
			return Messages.get(this, "desc_" + tier, 2+wandLevel, 8 + 4*wandLevel, tier );
		}
		
		{
			immunities.add( AllyBuff.class );
		}

		private static final String TIER = "tier";
		private static final String WAND_LEVEL = "wand_level";
		private static final String TOTAL_ZAPS = "total_zaps";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(TIER, tier);
			bundle.put(WAND_LEVEL, wandLevel);
			bundle.put(TOTAL_ZAPS, totalZaps);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			tier = bundle.getInt(TIER);
			viewDistance = 3 + tier;
			wandLevel = bundle.getInt(WAND_LEVEL);
			totalZaps = bundle.getInt(TOTAL_ZAPS);
		}
		
		{
			properties.add(Property.IMMOVABLE);
		}
	}
}
