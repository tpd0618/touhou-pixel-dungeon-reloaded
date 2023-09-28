package com.touhoupixel.touhoupixeldungeongaiden.actors.buffs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.ui.ActionIndicator;
import com.touhoupixel.touhoupixeldungeongaiden.ui.Icons;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndYoumuAbility;
import com.watabou.noosa.Image;

public class YoumuAbility extends Buff implements ActionIndicator.Action {
    public static int powerReq() {
        if (Statistics.card61 == true){
            return 125;
        }
        else {
            return 130;
        }
    }

    {
        type = buffType.NEUTRAL;
        revivePersists = true;
    }
    public boolean act() {
        if (Statistics.power >= powerReq() && Dungeon.heroine.buff(HumanHalf.class) == null && Dungeon.heroine.buff(GhostHalf.class) == null) {
            ActionIndicator.setAction(this);
        }
        else{
            ActionIndicator.setAction(null);
        }
        spend(TICK);
        return true;
    }
    public void bomb() {
        ActionIndicator.setAction(null);
        if (Statistics.card16){
            bombAction();
        }
        else {
            Buff.affect(Dungeon.heroine, HumanHalf.class).bomb();
        }
        act();

    }

    private void bombAction() {
        GameScene.show(
                new WndYoumuAbility(Messages.get(this, "wndname"), "", Messages.get(this, "optionhumanhalfbomb"), Messages.get(this, "optionghosthalfbomb")){
                    @Override
                    protected void onSelect(int index) {
                        switch (index) {
                            case 0:
                                Buff.affect(Dungeon.heroine, HumanHalf.class).bomb();
                                break;
                            case 1:
                                Buff.prolong(Dungeon.heroine, GhostHalf.class, GhostHalf.duration());
                                Dungeon.heroine.buff(GhostHalf.class).setSourceBomb();
                                break;
                        }
                        act();
                    }

                }
        );
    }

    @Override
    public String actionName() {
        return Messages.get(this, "actionname");
    }

    @Override
    public Image actionIcon() {
        return Icons.get(Icons.YOUMU_ABILITY);
    }

    @Override
    public void doAction() {
        GameScene.show(
        new WndYoumuAbility(Messages.get(this, "wndname"), Messages.get(this, "wndmsg", powerReq()-100), Messages.get(this, "optionhumanhalf"), Messages.get(this, "optionghosthalf")){
            @Override
            protected void onSelect(int index) {
                switch (index) {
                    case 0:
                        Buff.affect(Dungeon.heroine, HumanHalf.class).set();
                        break;
                    case 1:
                        Buff.prolong(Dungeon.heroine, GhostHalf.class, GhostHalf.duration());
                        break;
                }
                Statistics.power -= (powerReq() - 100);
                act();
            }

        }
        );
    }
}
