package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Haste;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.YuukaRage;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YuukaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

public class Yuuka extends Mob {

    {
        spriteClass = YuukaSprite.class;

        HP = HT = 450;
        defenseSkill = 0;
        EXP = 1;
        maxLvl = 27;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        baseSpeed = 0.2f;
    }

    @Override
    public int damageRoll() {
        return 1;
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public float attackDelay() { return super.attackDelay()*3f; }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        if (Dungeon.heroine.belongings.weapon() instanceof MeleeWeapon) {
            enemy.damage(damage/2, target);
            Buff.prolong(this, Bless.class, Bless.DURATION*10000f);
            Buff.prolong(this, YuukaRage.class, YuukaRage.DURATION);
            Buff.prolong(this, Haste.class, Haste.DURATION*10000f);
            Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION*10000f);
            GLog.w(Messages.get(this, "reflect"));
        }
        return super.defenseProc(enemy, damage);
    }
}