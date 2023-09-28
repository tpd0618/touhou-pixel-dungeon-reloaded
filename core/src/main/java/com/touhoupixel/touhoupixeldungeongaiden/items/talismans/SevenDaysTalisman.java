package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doom;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic.PotionOfPhilosopher;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class SevenDaysTalisman extends Talisman {
    {
        image = ItemSpriteSheet.SEVEN_DAYS;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {

            switch (Random.Int(7)) {
                case 0:
                default:
                    Buff.affect(ch, Poison.class).set(Dungeon.floor);
                    break;
                case 1:
                    Buff.prolong(ch, Vertigo.class, Vertigo.DURATION);
                    break;
                case 2:
                    Buff.prolong(ch, Slow.class, Slow.DURATION);
                    break;
                case 3:
                    Buff.affect(ch, Drowsy.class);
                    break;
                case 4:
                    Buff.affect(ch, Doom.class);
                    break;
                case 5:
                    ScrollOfTeleportation.teleportChar(ch);
                    break;
                case 6:
                    GameScene.flash(0x80FFFFFF);
                    Sample.INSTANCE.play(Assets.Sounds.BLAST);
                    ch.die(null);
                    if (ch.properties().contains(Char.Property.ELIXIR)) {
                        Dungeon.level.drop(new PotionOfPhilosopher(), ch.pos).sprite.drop();
                    } else {
                        Dungeon.level.drop(new PotionOfLightHealing(), ch.pos).sprite.drop();
                    }
                    break;
            }
        }
    }
}