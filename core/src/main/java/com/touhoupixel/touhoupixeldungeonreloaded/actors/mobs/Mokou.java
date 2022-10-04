package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirthDone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MokouSprite;
import com.watabou.utils.Random;

public class Mokou extends Mob {

    {
        spriteClass = MokouSprite.class;

        HP = HT = 93;
        defenseSkill = 22;
        EXP = 9;
        maxLvl = 30;

        loot = new LifeFragment();
        lootChance = 0.05f;

        properties.add(Property.ELIXIR);

        immunities.add( Fire.class );
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(10, 14);
    }

    @Override
    public int attackSkill(Char target) {
        return 27;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(3) == 0 && buff(ReBirthDone.class) == null) {
            Buff.prolong( this, ReBirth.class, ReBirth.DURATION*1000f);
            Buff.affect(enemy, Burning.class).reignite(enemy, 6f);
        }
        return damage;
    }
}