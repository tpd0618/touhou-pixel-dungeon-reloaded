package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Homunculus;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TenshiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Tenshi extends Mob {

    {
        spriteClass = TenshiSprite.class;

        HP = HT = 244;
        defenseSkill = 35;
        EXP = 18;
        maxLvl = 47;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(25, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 45;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && !hero.flying && Random.Int(4) == 0) {
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
            if (Statistics.difficulty > 2) {
                Buff.affect(enemy, Bleeding.class).set(7);
            }
            if (Statistics.difficulty > 4) {
                Buff.affect(enemy, Bleeding.class).set(7);
            }
        }
        return damage;
    }
}