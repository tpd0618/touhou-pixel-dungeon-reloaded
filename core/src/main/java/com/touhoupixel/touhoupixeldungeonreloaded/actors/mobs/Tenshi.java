package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.SuperDegrade;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TenshiSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Tenshi extends Mob {

    {
        spriteClass = TenshiSprite.class;

        HP = HT = 244;
        defenseSkill = 40;
        EXP = 20;
        maxLvl = 47;

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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && !hero.flying && Random.Int(3) == 0) {
            Buff.prolong(enemy, SuperDegrade.class, SuperDegrade.DURATION);
        }
        return damage;
    }
}