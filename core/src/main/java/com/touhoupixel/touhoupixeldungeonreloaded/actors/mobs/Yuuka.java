package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Triplespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YuukaRage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YuukaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Yuuka extends Mob {

    {
        spriteClass = YuukaSprite.class;

        HP = HT = 500;
        defenseSkill = 0;
        EXP = 1;
        maxLvl = 45;

        baseSpeed = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(0, 1);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public float attackDelay() { return super.attackDelay()*3f; }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        if (Dungeon.hero.belongings.weapon() instanceof MeleeWeapon) {
            enemy.damage(damage*2, target);
            Buff.prolong(this, Bless.class, Bless.DURATION*10000f);
            Buff.prolong(this, YuukaRage.class, YuukaRage.DURATION*10000f);
            Buff.prolong(this, Haste.class, Haste.DURATION*10000f);
            Buff.prolong(this, Triplespeed.class, Triplespeed.DURATION*10000f);
            GLog.w(Messages.get(this, "reflect"));
        }
        return super.defenseProc(enemy, damage);
    }
}