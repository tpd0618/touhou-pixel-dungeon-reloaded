package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells.Mokou;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.watabou.noosa.Image;

public class FireShield extends Barrier{
    private boolean isDetachedByCard = false; //it doesn't make sense to save it in a bundle
    @Override
    public void fx(boolean on) {
        if (on) target.sprite.add(CharSprite.State.FIRE_SHIELDED);
        else target.sprite.remove(CharSprite.State.FIRE_SHIELDED);
    }
    public void detachByCard(){
        isDetachedByCard = true;
        detach();
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(1.5f, 0.75f, 0.4f);
    }
    @Override
    public void detach() {
        if (target instanceof Mokou) {
            if (((Mokou) target).spellcard != null && !isDetachedByCard) {
                ((Mokou) target).spellcard.deactivate();
            }
        }
        super.detach();
    }
}
