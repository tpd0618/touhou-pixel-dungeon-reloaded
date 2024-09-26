package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.NitoriSprite;
import com.watabou.utils.Random;

public class Nitori extends Mob {

    {
        spriteClass = NitoriSprite.class;

        HP = HT = 110;
        HP = HT = 141;
        defenseSkill = 17;
        EXP = 9;
        maxLvl = 25;

        properties.add(Property.YOKAI);
        properties.add(Property.AQUATIC);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new PotionOfDoubleSpeed();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(39, 61);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(7, 11);
    }
    //Nitori cannot be detected by monster detector
}