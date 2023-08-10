package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.food.Food;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YuyukoSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Yuyuko extends Mob {

    {
        spriteClass = YuyukoSprite.class;

        HP = HT = 217;
        defenseSkill = 32;
        EXP = 15;
        maxLvl = 45;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new Food();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(22, 28);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Dungeon.heroine.STR--;
            Dungeon.level.drop(new StrengthCard(), Dungeon.level.randomRespawnCell(null)).sprite.drop();
            Sample.INSTANCE.play( Assets.Sounds.CURSED );
            GLog.w(Messages.get(Kanako.class, "str_reduce"));
            if (Statistics.difficulty > 2) {
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
            }
        }
        return damage;
    }
}