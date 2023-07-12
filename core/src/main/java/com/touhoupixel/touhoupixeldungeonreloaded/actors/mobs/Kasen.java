package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KasenSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

import java.util.HashSet;

public class Kasen extends Mob {

    {
        spriteClass = KasenSprite.class;

        HP = HT = 70;
        defenseSkill = 20;
        EXP = 11;
        maxLvl = 27;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Generator.Category.ARMOR_T5;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 16);
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Armor armor = Dungeon.heroine.belongings.armor();
            if (armor != null) {
                Dungeon.heroine.belongings.armor = null;
                Dungeon.quickslot.clearItem(armor);
                Item.updateQuickslot();

                Ballistica trajectory = new Ballistica(this.pos, enemy.pos, Ballistica.STOP_TARGET);
                trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);

                Char ch = (Char) Actor.findChar(trajectory.collisionPos);
                if (ch != null && ch != Dungeon.heroine && trajectory.collisionPos.equals(ch.pos)) {
                    ch.damage(Dungeon.heroine.STR*2+armor.level()*3, armor);
                    GLog.w(Messages.get(this, "armor_blow_away_and_destroy"));
                } else {
                    armor.onThrow(trajectory.collisionPos);
                    GLog.w(Messages.get(this, "armor_blow_away"));
                }
            }
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, HinaCurse.class, HinaCurse.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, HeavenSpeed.class, HeavenSpeed.DURATION);
            }
        }
        return damage;
    }
}