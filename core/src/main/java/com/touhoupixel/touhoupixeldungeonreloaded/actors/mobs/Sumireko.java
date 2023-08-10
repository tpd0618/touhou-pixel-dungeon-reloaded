package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DisarmingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SumirekoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Sumireko extends Mob {

    {
        spriteClass = SumirekoSprite.class;

        HP = HT = 187;
        defenseSkill = 37;
        EXP = 22;
        maxLvl = 75;

        properties.add(Property.HUMAN);

        loot = new ScrollOfTeleportation();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(31, 37);
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
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(7) == 0) {
            GLog.n( Messages.get(this, "disarm") );
            new DisarmingTrap().set(enemy.pos).activate();
            if (Statistics.difficulty > 2) {
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION);
            }
        }
        return damage;
    }
}