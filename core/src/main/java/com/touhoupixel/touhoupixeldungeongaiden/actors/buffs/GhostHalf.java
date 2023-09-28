package com.touhoupixel.touhoupixeldungeongaiden.actors.buffs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeongaiden.ui.BuffIndicator;

public class GhostHalf extends FlavourBuff {

    {
        type = buffType.POSITIVE;
        announced = true;
    }

    public static int bonusEvasion(){
        if (Statistics.card53){
            return 8;
        }
        return 5;
    }
    public static float duration(){
        if (Statistics.card53){
            return 10f;
        }
        return 6f;
    }
    public void fx(boolean on) {
        if (Statistics.card53){
            if (on) target.sprite.add( CharSprite.State.HEALING );
            else    target.sprite.remove( CharSprite.State.HEALING );
        }
    }
    private boolean isSourceBomb = false;
    public void setSourceBomb(){
        isSourceBomb = true;
    }
    public boolean isSourceBomb(){
        return isSourceBomb;
    }
    public void detach(){
        if (isSourceBomb()){
            Buff.prolong(Dungeon.heroine, DoubleSpeed.class, 10f);
            Buff.prolong(Dungeon.heroine, Might.class, 10f);
            Buff.prolong(Dungeon.heroine, Bless.class, 10f);
        }
        super.detach();
    }
    @Override
    public int icon() {
        return BuffIndicator.GHOST_HALF;
    }

    @Override
    public float iconFadePercent() {
        return Math.max(0, (duration() - visualcooldown()) / duration());
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String heroMessage() {
        return Messages.get(this, "heromsg");
    }
    @Override
    public String desc() {
        String desc = Messages.get(this, "desc", bonusEvasion(), dispTurns());
        if (Statistics.card53) desc = Messages.get(this, "descwithheal", bonusEvasion(), dispTurns());
        return desc;
    }

}
