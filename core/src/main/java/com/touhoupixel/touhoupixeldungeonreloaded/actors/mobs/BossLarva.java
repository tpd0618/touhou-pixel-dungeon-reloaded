package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.LarvaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.watabou.utils.Random;

public class BossLarva extends Mob {

    {
        spriteClass = LarvaSprite.class;

        HP = HT = 100;
        defenseSkill = 5;
        EXP = 0;
        maxLvl = 99;

        flying = true;

        properties.add(Property.BOSS);

        loot = new Spellcard();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(5), pos ).sprite.drop();
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 6);
    }

    @Override
    public int attackSkill(Char target) {
        return 10;
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
        if (Random.Int(2) == 0) {
            Buff.prolong(enemy, Vertigo.class, Vertigo.DURATION/5f);
            Buff.affect(enemy, Bleeding.class).set(3);
        }
        return damage;
    }
}