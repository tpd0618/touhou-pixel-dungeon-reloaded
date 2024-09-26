package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class AuraReimu extends Buff{
    {
        type = buffType.NEUTRAL;
    }

    @Override
    public int icon() {
        return BuffIndicator.AURA_REIMU;
    }
    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(2f, 1f, 1f);
    }

    @Override
    public String desc() {
        return super.desc();
    }

    @Override
    public void fx(boolean on) {

    }
}
