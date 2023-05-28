package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BalanceBreak;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.items.spells.AquaBlast;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GeyserTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TakaneSprite;
import com.watabou.utils.Random;

public class Takane extends Mob {

    {
        spriteClass = TakaneSprite.class;

        HP = HT = 59;
        defenseSkill = 17;
        EXP = 8;
        maxLvl = 25;

        properties.add(Property.YOKAI);

        loot = new AquaBlast();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(7, 11);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        new GeyserTrap().set(this.pos).activate();
        if (Statistics.difficulty > 2) {
            Buff.prolong(enemy, BalanceBreak.class, BalanceBreak.DURATION);
        }
        if (Statistics.difficulty > 4) {
            Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
        }
        return damage;
    }
}