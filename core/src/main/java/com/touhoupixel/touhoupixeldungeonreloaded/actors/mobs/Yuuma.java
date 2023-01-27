package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Doublespeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YuumaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Yuuma extends Mob {

    {
        spriteClass = YuumaSprite.class;

        HP = HT = 358;
        defenseSkill = 50;
        EXP = 25;
        maxLvl = 99;

        properties.add(Property.YOKAI);

        loot = new LifeFragment();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(35, 40);
    }

    @Override
    public int attackSkill(Char target) {
        return 75;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(2) == 0) {
            Dungeon.hero.STR--;
            Buff.prolong(this, Doublespeed.class, Doublespeed.DURATION);
            Buff.prolong(this, Hisou.class, Hisou.DURATION);
            Sample.INSTANCE.play( Assets.Sounds.CURSED );
            GLog.w(Messages.get(this, "str_reduce"));
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, MagicDrain.class, MagicDrain.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Dungeon.hero.STR--;
            }
        }
        return damage;
    }
}