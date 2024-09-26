package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Chill;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Frost;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Dewdrop;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CirnoSprite;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Cirno extends Mob {

    {
        spriteClass = CirnoSprite.class;

        HP = HT = 60;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        properties.add(Property.WARP);
        properties.add(Property.AQUATIC);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new Dewdrop();
        lootChance = 1f;
        immunities.add(Chill.class);
        immunities.add(Frost.class);
    }

    @Override
    public void damage(int dmg, Object src) {
        if (buff(Burning.class) != null) dmg *= 2;
        super.damage(dmg, src);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(18, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 12;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(3, 7);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(2) == 0) {
            Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
            Buff.affect(enemy, Chill.class, 2f);
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