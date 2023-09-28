package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ReBirth;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ReBirthDone;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MokouSprite;
import com.watabou.utils.Random;

public class Mokou extends Mob {

    {
        spriteClass = MokouSprite.class;

        HP = HT = 36;
        defenseSkill = 12;
        EXP = 5;
        maxLvl = 20;

        properties.add(Property.ELIXIR);
        properties.add(Property.HUMAN);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new LifeFragment();
        lootChance = 0.15f;

        immunities.add(Fire.class);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 10);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0 && buff(ReBirthDone.class) == null) {
            Buff.prolong( this, ReBirth.class, ReBirth.DURATION*1000f);
            Buff.affect(enemy, Burning.class).reignite(enemy, 6f);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, ZeroDexterity.class, ZeroDexterity.DURATION/2f);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
            }
        }
        return damage;
    }
}