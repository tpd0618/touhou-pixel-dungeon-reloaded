package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.keys.SkeletonKey;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KasenSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BossHealthBar;
import com.watabou.utils.Random;

public class BossKasen extends Mob {

    {
        spriteClass = KasenSprite.class;

        HP = HT = Dungeon.isChallenged(Challenges.LAST_SURPRISE) ? 1400 : 700;
        defenseSkill = 20;
        EXP = 22;
        maxLvl = 99;

        baseSpeed = 2f;

        properties.add(Property.BOSS);
        properties.add(Property.YOKAI);

        loot = new Life();
        lootChance = 1f;
    }

    @Override
    public void die(Object cause) {
        GameScene.bossSlain();
        super.die(cause);
        Dungeon.level.unseal();
        Dungeon.level.drop(new SkeletonKey(20), pos ).sprite.drop();
        yell(Messages.get(this, "bossdefeat"));
    }

    @Override
    public int damageRoll() {
        MeleeWeapon meleeweapon = Dungeon.heroine.belongings.getItem(MeleeWeapon.class);
        if (meleeweapon != null) {
            return Random.NormalIntRange(meleeweapon.min(), meleeweapon.max());
        } else {
            return Random.NormalIntRange(1, 10);
        }
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public int drRoll() {
        Armor armor = Dungeon.heroine.belongings.getItem(Armor.class);
        if (armor != null) {
            return Random.NormalIntRange(0, armor.DRMax());
        } else {
            return Random.NormalIntRange(0, 2);
        }
    }

    @Override
    public void notice() {
        super.notice();
        if (!BossHealthBar.isAssigned()) {
            BossHealthBar.assignBoss(this);
            yell(Messages.get(this, "boss"));
            Buff.prolong(Dungeon.heroine, WandZeroDamage.class, WandZeroDamage.DURATION*2f);
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            if (this.HP < this.HT / 2) {
                Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
        }
        return damage;
    }
}