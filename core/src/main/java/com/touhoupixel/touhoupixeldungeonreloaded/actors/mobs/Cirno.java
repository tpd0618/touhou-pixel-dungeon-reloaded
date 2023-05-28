package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Dewdrop;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfSnapFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CirnoSprite;
import com.watabou.utils.Random;

public class Cirno extends Mob {

    {
        spriteClass = CirnoSprite.class;

        HP = HT = 27;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new Dewdrop();
        lootChance = 1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 6);
    }

    @Override
    public int attackSkill(Char target) {
        return 12;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(2) == 0) {
            Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
            if (Statistics.difficulty > 2) {
                Buff.prolong(this, Might.class, Might.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
        }
        return damage;
    }
}