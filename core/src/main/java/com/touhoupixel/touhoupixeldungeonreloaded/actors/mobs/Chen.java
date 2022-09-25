package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Chill;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Frost;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ChenSprite;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Chen extends Mob {

    protected ArrayList<Class<? extends Buff>> harmfulBuffs = new ArrayList<>();

    {
        spriteClass = ChenSprite.class;

        HP = HT = 94;
        defenseSkill = 17;
        EXP = 4;
        maxLvl = 25;

        baseSpeed = 2f;

        harmfulBuffs.add( Chill.class );
        harmfulBuffs.add( Frost.class );

        loot = new SpellcardFragment();
        lootChance = 0.125f;
    }

    @Override
    public void add( Buff buff ) {
        if (harmfulBuffs.contains( buff.getClass() )) {
            damage( Random.NormalIntRange( HT/2, HT * 3/5 ), buff );
        } else {
            super.add( buff );
        }
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(14, 18);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}