package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.SagumeSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Sagume extends Mob {

    {
        spriteClass = SagumeSprite.class;

        HP = HT = 159;
        defenseSkill = 42;
        EXP = 22;
        maxLvl = 50;

        loot = new LifeFragment();
        lootChance = 0.05f;
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
            if (cause == Dungeon.hero && enemy.pos == this.pos + i) {
                Dungeon.hero.HP = 1;
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(this, "reverse"));
            }
        }
    }
}