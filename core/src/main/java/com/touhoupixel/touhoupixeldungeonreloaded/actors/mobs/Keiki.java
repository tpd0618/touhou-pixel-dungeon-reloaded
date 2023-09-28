package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Homunculus;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeikiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Keiki extends Mob {

    {
        spriteClass = KeikiSprite.class;

        HP = HT = 237;
        defenseSkill = 40;
        EXP = 24;
        maxLvl = 75;

        properties.add(Property.GOD);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.05f;

        immunities.add(Fire.class);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(28, 33);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Dungeon.heroine.buff(Burning.class) == null) {
            if (!(Dungeon.level.map[hero.pos] == Terrain.WATER)) {
                Homunculus homunculus = Dungeon.heroine.belongings.getItem(Homunculus.class);
                if (homunculus != null) {
                    homunculus.detach(Dungeon.heroine.belongings.backpack);
                    Sample.INSTANCE.play(Assets.Sounds.BEACON);
                    GLog.w(Messages.get(Homunculus.class, "block_instakill"));
                    Item.updateQuickslot();
                } else {
                    damage = hero.HP + 1;
                    GLog.w(Messages.get(this, "idolatrize"));
                }
            }
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, Vertigo.class, Vertigo.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Dungeon.heroine.STR--;
                Dungeon.level.drop(new StrengthCard(), Dungeon.level.randomRespawnCell(null)).sprite.drop();
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(Kanako.class, "str_reduce"));
            }
        }
        return damage;
    }
}