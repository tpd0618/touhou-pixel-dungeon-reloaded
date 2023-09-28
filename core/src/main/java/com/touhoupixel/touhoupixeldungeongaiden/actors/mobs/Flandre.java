package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.FlandreCooldown;
import com.touhoupixel.touhoupixeldungeongaiden.items.tickets.FiveStarTicket;
import com.touhoupixel.touhoupixeldungeongaiden.items.tickets.FourStarTicket;
import com.touhoupixel.touhoupixeldungeongaiden.items.tickets.ThreeStarTicket;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.FlandreSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Flandre extends Mob {

    {
        spriteClass = FlandreSprite.class;

        HP = HT = Dungeon.floor*10+30;
        defenseSkill = Dungeon.floor+5;

        state = PASSIVE;

        flying = true;

        properties.add(Property.WARP);
        properties.add(Property.MINIBOSS);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Dungeon.floor, Dungeon.floor+10);
    }

    @Override
    public int attackSkill(Char target) {
        return Dungeon.floor+10;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 4);
    }

    @Override
    public void beckon( int cell ) {
        //Do nothing
    }

    @Override
    public void die( Object cause ) {
        Dungeon.level.drop(new ThreeStarTicket(), pos ).sprite.drop();
        Dungeon.level.drop(new FourStarTicket(), pos ).sprite.drop();
        Dungeon.level.drop(new FiveStarTicket(), pos ).sprite.drop();
        super.die( cause );
    }

    @Override
    public void add(Buff buff) {
        super.add(buff);
        if (state == PASSIVE && buff.type == Buff.buffType.NEGATIVE){
            state = HUNTING;
        }
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
            Dungeon.heroine.damage(Dungeon.floor+20, this);
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