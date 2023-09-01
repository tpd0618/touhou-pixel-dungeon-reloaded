package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.TargetedCell;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.BlastParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.BloodParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.SacrificialParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.spells.ReclaimTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TewiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.utils.Random;

public class Tewi extends Mob {

    {
        spriteClass = TewiSprite.class;

        HP = HT = 45;
        defenseSkill = 12;
        EXP = 5;
        maxLvl = 20;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new ReclaimTrap();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 6);
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
    public void die(Object cause) {
        if (this.pos - 1 == Dungeon.heroine.pos ||
            this.pos + 1 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width() == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width() == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width() - 1 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width() + 1 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width() - 1 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width() + 1 == Dungeon.heroine.pos ||
            this.pos - 2 == Dungeon.heroine.pos ||
            this.pos + 2 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*2 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*2 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*2 - 2 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*2 + 2 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*2 - 2 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*2 + 2 == Dungeon.heroine.pos ||
            this.pos - 3 == Dungeon.heroine.pos ||
            this.pos + 3 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*3 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*3 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*3 - 3 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*3 + 3 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*3 - 3 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*3 + 3 == Dungeon.heroine.pos) {
            Dungeon.heroine.damage(8, this);
            GLog.w(Messages.get(this, "star_damage"));
        }

        sprite.parent.addToBack(new TargetedCell(this.pos - 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width(), 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width(), 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width() - 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width() + 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width() - 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width() + 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2 - 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2 + 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2 - 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2 + 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3 - 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3 + 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3 - 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3 + 3, 0xFF0000));
        super.die(cause);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, HinaCurse.class, HinaCurse.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
            }
        }
        return damage;
    }
}