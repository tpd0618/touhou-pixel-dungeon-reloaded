package com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class RustyRoukanken extends MeleeWeapon{
    {
        image = ItemSpriteSheet.RUSTY_ROUKANKEN;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1f;

        tier = 1;
    }

    @Override
    public int GodFactor( Char owner ) {
        return 1;
    }
    public int min(int lvl){
        return 1;
    }
    public int max(int lvl){
        return 6*(tier+1) +    // base dmg: 10 >> 12
                lvl*(tier+2); // lvl scaling: +2 >> +3
    }
    public int proc(Char attacker, Char defender, int damage) {
        return super.proc(attacker, defender, damage);
    }
}
