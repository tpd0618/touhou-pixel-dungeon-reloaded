package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MamizouSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class FakeMamizou extends Mob {

    {
        spriteClass = MamizouSprite.class;

        HP = HT = 100;
        defenseSkill = 45;

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(22, 26);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        if (Dungeon.hero.belongings.weapon() instanceof MeleeWeapon) {
            Buff.prolong( enemy, RemiliaFate.class, RemiliaFate.DURATION );
        }
        return super.defenseProc(enemy, damage);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment) {
            Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION);
        }
        return damage;
    }
}