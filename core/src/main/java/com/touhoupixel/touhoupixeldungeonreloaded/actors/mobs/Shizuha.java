package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.OneDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Waterskin;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ShizuhaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Shizuha extends Mob {

    {
        spriteClass = ShizuhaSprite.class;

        HP = HT = 98;
        defenseSkill = 27;
        EXP = 15;
        maxLvl = 35;

        loot = new PotionOfHealing();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(10, 18);
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Waterskin waterskin = new Waterskin();
            waterskin.volume = 0;
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            GLog.w(Messages.get(this, "dry"));
            Item.updateQuickslot();
        }
        return damage;
    }
}