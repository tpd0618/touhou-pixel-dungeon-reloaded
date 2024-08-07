package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FlandreCooldown;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.FlandreSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Flandre extends Mob {

    {
        spriteClass = FlandreSprite.class;

        int sF = Dungeon.scalingFloor();
        HP = HT = 8*(Math.min((sF-1)/4+1, 5)*(3+sF) + sF);
        defenseSkill = Dungeon.floor+5;

        state = PASSIVE;

        flying = true;

        properties.add(Property.WARP);
        properties.add(Property.POPULAR);
        properties.add(Property.MINIBOSS);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    public int damageRoll() {
        int level = Dungeon.scalingFloor();
        return Random.NormalIntRange( (int)(0.6*(2.5*level + 3 + Math.min(5, ((level - 1)/4 + 1)*(1+level)))), (int)(1.2*(2.5*level + 3 + Math.min(5, ((level - 1)/4 + 1)*(1+level)))));
    }

    @Override
    public int attackSkill(Char target) {
        return Dungeon.floor+10;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(Dungeon.scalingFloor()/2, 4 + Dungeon.scalingFloor());
    }

    @Override
    public void beckon( int cell ) {
        //Do nothing
    }

    @Override
    public void die( Object cause ) {
        super.die( cause );
    }

    @Override
    public boolean add(Buff buff) {
        boolean added = super.add(buff);
        if (state == PASSIVE && buff.type == Buff.buffType.NEGATIVE){
            state = HUNTING;
        }
        return added;
    }

    @Override
    public void damage( int dmg, Object src ) {

        if (state == PASSIVE) {
            state = HUNTING;
        }

        super.damage( dmg, src );
    }

    @Override
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && this.state != SLEEPING && this.state != FLEEING && this.HP != this.HT && this.buff(FlandreCooldown.class) == null) {
            Dungeon.heroine.damage(Dungeon.floor*5+20, this);
            GameScene.flash(-65536);
            Buff.prolong(this, FlandreCooldown.class, FlandreCooldown.DURATION);
            Sample.INSTANCE.play( Assets.Sounds.BLAST );
            GLog.w(Messages.get(this, "destruction_wave"));
            if (enemy == Dungeon.heroine && !enemy.isAlive()) {
                Dungeon.fail(Flandre.class);
            }
        }
        return super.act();
    }
}