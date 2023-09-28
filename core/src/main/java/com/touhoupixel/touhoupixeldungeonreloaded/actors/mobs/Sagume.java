package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SagumeSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Sagume extends Mob {

    {
        spriteClass = SagumeSprite.class;

        HP = HT = 159;
        defenseSkill = 37;
        EXP = 22;
        maxLvl = 50;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new LifeFragment();
        lootChance = 0.08f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(27, 31);
    }

    @Override
    public int attackSkill(Char target) {
        return 47;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        for (int i : PathFinder.NEIGHBOURS8) {
            if (enemy == Dungeon.heroine && enemy.pos == this.pos + i) {
                enemy.HP = 1;
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(this, "reverse"));
            }
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0){
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Slow.class, Slow.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, WandZeroDamage.class, WandZeroDamage.DURATION);
            }
        }
        return damage;
    }
}