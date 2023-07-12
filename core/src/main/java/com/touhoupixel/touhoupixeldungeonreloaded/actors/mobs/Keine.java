package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Inversion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.GlassBottle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeonreloaded.items.herbs.PurityHerb;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeineSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Keine extends Mob {

    {
        spriteClass = KeineSprite.class;

        HP = HT = 39;
        defenseSkill = 12;
        EXP = 5;
        maxLvl = 20;

        properties.add(Property.ANIMAL);

        loot = new PurityHerb();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 8);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            Artifact artifact = Dungeon.heroine.belongings.getItem(Artifact.class);
            if (artifact != null) {
                artifact.charge = 0;
                Item.updateQuickslot();
                Sample.INSTANCE.play(Assets.Sounds.CURSED);
                GLog.w(Messages.get(Reimu.class, "artifact_charge_lost"));
            }
            if (Statistics.difficulty > 2) {
                GlassBottle waterskin = Dungeon.heroine.belongings.getItem(GlassBottle.class);
                if (waterskin != null) {
                    waterskin.volume = 0;
                    Sample.INSTANCE.play(Assets.Sounds.CURSED);
                    GLog.w(Messages.get(Shizuha.class, "dry"));
                    Item.updateQuickslot();
                }
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Inversion.class, Inversion.DURATION);
            }
        }
        return damage;
    }
}