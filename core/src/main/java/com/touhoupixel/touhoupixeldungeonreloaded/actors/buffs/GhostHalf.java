package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;

public class GhostHalf extends FlavourBuff {

    {
        type = buffType.POSITIVE;
        announced = true;
    }

    public static int bonusEvasion(){
        if (Statistics.cardMomoyoCentipede){
            return 8;
        }
        return 5;
    }
    public static float duration(){
        if (Statistics.cardMomoyoCentipede){
            return 10f;
        }
        return 6f;
    }
    public void fx(boolean on) {
        if (Statistics.cardMomoyoCentipede){
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
        if (Statistics.cardMomoyoCentipede) desc = Messages.get(this, "descwithheal", bonusEvasion(), dispTurns());
        return desc;
    }

}
