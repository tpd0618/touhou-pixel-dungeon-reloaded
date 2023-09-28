package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.items.Homunculus;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.JunkoSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Junko extends Mob {

    {
        spriteClass = JunkoSprite.class;

        HP = HT = 269;
        defenseSkill = 37;
        EXP = 22;
        maxLvl = 50;

        properties.add(Property.GOD);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(31, 37);
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
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            if (Statistics.life == 0) {
                Homunculus homunculus = Dungeon.heroine.belongings.getItem(Homunculus.class);
                if (homunculus != null) {
                    homunculus.detach(Dungeon.heroine.belongings.backpack);
                    Sample.INSTANCE.play(Assets.Sounds.BEACON);
                    GLog.w(Messages.get(Homunculus.class, "block_instakill"));
                    Item.updateQuickslot();
                } else {
                    damage = hero.HP + 1;
                    GLog.w(Messages.get(this, "purified"));
                }
            } else {
                Statistics.life -= 1;
                Sample.INSTANCE.play(Assets.Sounds.CURSED);
                GLog.w(Messages.get(this, "lifelose"));
            }
            if (Statistics.difficulty > 2) {
                Statistics.life -= 1;
            }
            if (Statistics.difficulty > 4) {
                Statistics.life -= 1;
            }
        }
        return damage;
    }
}