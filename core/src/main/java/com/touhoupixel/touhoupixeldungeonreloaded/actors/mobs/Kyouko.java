package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KyoukoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Kyouko extends Mob {

    {
        spriteClass = KyoukoSprite.class;

        HP = HT = 144;
        defenseSkill = 22;
        EXP = 13;
        maxLvl = 30;

        loot = new ScrollOfSirensSong();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(21, 29);
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
    public int defenseProc(Char enemy, int damage) {
        if (Dungeon.hero.belongings.weapon() instanceof MeleeWeapon) {
            enemy.damage(damage / 2, target);
            GLog.w(Messages.get(this, "reflect"));
        }
        return super.defenseProc(enemy, damage);
    }
}