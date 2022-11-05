package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirth;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.ReBirthDone;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs.ElixirOfDragonsBlood;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MokouSprite;
import com.watabou.utils.Random;

public class Mokou extends Mob {

    {
        spriteClass = MokouSprite.class;

        HP = HT = 36;
        defenseSkill = 12;
        EXP = 10;
        maxLvl = 20;

        properties.add(Property.ELIXIR);

        loot = new LifeFragment();
        lootChance = 0.15f;

        immunities.add(Fire.class);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 8);
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
        if (Random.Int(3) == 0 && buff(ReBirthDone.class) == null) {
            Buff.prolong( this, ReBirth.class, ReBirth.DURATION*1000f);
            Buff.affect(enemy, Burning.class).reignite(enemy, 6f);
        }
        return damage;
    }
}