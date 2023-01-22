package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.EikiSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Eiki extends Mob {

    {
        spriteClass = EikiSprite.class;

        HP = HT = 380;
        defenseSkill = 50;
        EXP = 25;
        maxLvl = 99;

        properties.add(Property.GOD);

        loot = new SpellcardFragment();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return (Statistics.difficulty > 2) ? Random.NormalIntRange(Statistics.enemiesSlain/13, Statistics.enemiesSlain/11):
                Random.NormalIntRange(Statistics.enemiesSlain/15, Statistics.enemiesSlain/13);
    }

    @Override
    public int attackSkill(Char target) {
        return 75;
    }

    @Override

    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment){
            if (Statistics.difficulty > 4) {
                Buff.prolong(this, Hisou.class, Hisou.DURATION);
            }
        }
        return damage;
    }
}