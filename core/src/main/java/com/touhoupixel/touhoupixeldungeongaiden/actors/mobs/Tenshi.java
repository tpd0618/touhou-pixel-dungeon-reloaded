package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.TenshiSprite;
import com.watabou.utils.Random;

public class Tenshi extends Mob {

    {
        spriteClass = TenshiSprite.class;

        HP = HT = 244;
        defenseSkill = 35;
        EXP = 18;
        maxLvl = 47;

        baseSpeed = Statistics.difficulty > 4 ? 1f : 0.5f;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        MeleeWeapon meleeweapon = Dungeon.heroine.belongings.getItem(MeleeWeapon.class);
        return Statistics.difficulty > 2 ? Random.NormalIntRange(meleeweapon.min()*2, meleeweapon.max()):
                Random.NormalIntRange(meleeweapon.min(), meleeweapon.max());
    }

    @Override
    public int attackSkill(Char target) {
        return 45;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}