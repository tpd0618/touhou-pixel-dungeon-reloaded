package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RemiliaFate;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Gold;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ShionSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Shion extends Mob {

    {
        spriteClass = ShionSprite.class;

        HP = HT = 188;
        defenseSkill = 37;
        EXP = 7;
        maxLvl = 45;

        loot = Gold.class;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(25, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(5) == 0) {
            if (Dungeon.gold > 800) {
                Dungeon.gold -= 800;
                Buff.prolong(enemy, RemiliaFate.class, RemiliaFate.DURATION);
                Sample.INSTANCE.play(Assets.Sounds.CURSED);
                CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
                GLog.w(Messages.get(this, "losemoney"));
            }
            return damage;
        }
        return damage;
    }
}