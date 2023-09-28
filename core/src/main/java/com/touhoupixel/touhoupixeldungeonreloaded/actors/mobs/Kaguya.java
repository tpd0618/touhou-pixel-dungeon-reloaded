package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BalanceBreak;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KaguyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Kaguya extends Mob {

    {
        spriteClass = KaguyaSprite.class;

        HP = HT = 222;
        defenseSkill = 32;
        EXP = 14;
        maxLvl = 45;

        properties.add(Property.ELIXIR);
        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new LifeFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(24, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            if (Dungeon.heroine.HP % 10 == 0 || Dungeon.heroine.HP % 10 == 1) {
                damage *= 2;
                Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
                if (Statistics.difficulty > 2) {
                    Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
                }
                if (Statistics.difficulty > 4) {
                    Buff.prolong(enemy, BalanceBreak.class, BalanceBreak.DURATION);
                }
                Sample.INSTANCE.play(Assets.Sounds.CURSED);
                GLog.w(Messages.get(this, "double_damage"));
            }
        }
        return damage;
    }
}