package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Dewdrop;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlockTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.DoremySprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Doremy extends Mob {

    {
        spriteClass = DoremySprite.class;

        HP = HT = 278;
        defenseSkill = 42;
        EXP = 21;
        maxLvl = 50;

        properties.add(Property.ANIMAL);

        loot = Generator.Category.POTION;
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(32, 38);
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
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            new FlockTrap().set(target).activate();
            if (Statistics.difficulty > 2) {
                Statistics.playercorruption += 1;
            }
            if (Statistics.difficulty > 4) {
                Statistics.playercorruption += 1;
            }
        }
        return damage;
    }
}