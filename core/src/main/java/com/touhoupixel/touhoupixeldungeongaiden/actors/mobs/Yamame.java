package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeongaiden.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfToxicGas;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YamameSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Yamame extends Mob {

    {
        spriteClass = YamameSprite.class;

        HP = HT = 213;
        defenseSkill = 32;
        EXP = 14;
        maxLvl = 45;

        properties.add(Property.YOKAI);

        loot = new PotionOfToxicGas();
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
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.affect(hero, Poison.class).set(Math.round(Statistics.upgradesUsed/5f));
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Inversion.class, Inversion.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Dungeon.heroine.STR--;
                Dungeon.level.drop(new StrengthCard(), Dungeon.level.randomRespawnCell(null)).sprite.drop();
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(Kanako.class, "str_reduce"));
            }
        }
        return damage;
    }
}