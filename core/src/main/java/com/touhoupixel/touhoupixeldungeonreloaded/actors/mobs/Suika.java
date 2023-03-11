package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.EvasiveCounterattack;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SquareRootSnipe;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfDanmaku;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.ScrollOfFixer;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfAntiMagic;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KyoukoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SuikaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Suika extends Mob {

    {
        spriteClass = SuikaSprite.class;

        HP = HT = 55;
        defenseSkill = 22;
        EXP = 13;
        maxLvl = 30;

        properties.add(Property.WARP);

        loot = new PotionOfDanmaku();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 16);
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
            enemy.damage(damage, target);
            Buff.prolong(this, Might.class, Might.DURATION);
            Buff.prolong(this, Hisou.class, Hisou.DURATION);
            GLog.w(Messages.get(this, "reflect"));
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, EvasiveCounterattack.class, EvasiveCounterattack.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION);
            }
        }
        return super.defenseProc(enemy, damage);
    }
}