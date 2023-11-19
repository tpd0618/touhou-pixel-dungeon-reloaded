package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AliceCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AliceSprite;
import com.watabou.utils.Random;

public class Alice extends Mob {

    {
        spriteClass = AliceSprite.class;

        HP = HT = 40;
        defenseSkill = 10;
        EXP = 4;
        maxLvl = 17;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new PotionOfHealing();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(25, 39);
    }

    @Override
    public int attackSkill(Char target) {
        return 15;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(5, 9);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0){
            ((SummoningTrap)(new SummoningTrap().set(pos))).activate(1);
            Buff.prolong(enemy, AliceCurse.class, AliceCurse.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Blindness.class, Blindness.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, DeSlaying.class, DeSlaying.DURATION);
            }
        }
        return damage;
    }
}