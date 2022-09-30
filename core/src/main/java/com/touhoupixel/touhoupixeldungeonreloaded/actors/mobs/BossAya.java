package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Triplespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.watabou.utils.Random;

public class BossAya extends Mob {

	{
		spriteClass = AyaSprite.class;

		HP = HT = 500;
		defenseSkill = 35;
		EXP = 0;
		maxLvl = 99;

		baseSpeed = 5f;

		flying = true;

		properties.add(Property.BOSS);

		loot = new Life();
		lootChance = 1f;
	}

	@Override
	public void die(Object cause) {
		GameScene.bossSlain();
		super.die(cause);
		Dungeon.level.unseal();
		Dungeon.level.drop(new SkeletonKey(35), pos ).sprite.drop();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(19, 25);
	}

	@Override
	public int attackSkill(Char target) {
		return 40;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	@Override
	public void notice() {
		super.notice();
		if (!BossHealthBar.isAssigned()) {
			BossHealthBar.assignBoss(this);
		}
	}

	@Override
	public int attackProc(Char hero, int damage) {
		damage = super.attackProc(enemy, damage);
		Buff.prolong(this, Triplespeed.class, Triplespeed.DURATION);
		return damage;
	}
}