package com.touhoupixel.touhoupixeldungeonreloaded.items.tailsmans;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfStrength;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.exotic.PotionOfInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class ChaosTailsman extends Tailsman {
    {
        image = ItemSpriteSheet.CHAOS;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !ch.properties().contains(Char.Property.BOSS)) {
            switch (Random.Int(7)) {
                case 0:
                default:
                    Buff.prolong(ch, Weakness.class, Weakness.DURATION);
                    break;
                case 1:
                    Buff.prolong(ch, Vulnerable.class, Vulnerable.DURATION);
                    break;
                case 2:
                    Buff.prolong(ch, Hex.class, Hex.DURATION);
                    break;
                case 3:
                    Buff.prolong(ch, Slow.class, Slow.DURATION);
                    break;
                case 4:
                    Buff.affect(ch, Drowsy.class);
                    break;
                case 5:
                    new ExplosiveTrap().set(cell).activate();
                    break;
                case 6:
                    GameScene.flash(0x80FFFFFF);
                    Sample.INSTANCE.play( Assets.Sounds.BLAST );
                    ch.die(null);
                    if (ch.properties().contains(Char.Property.ELIXIR)) {
                        switch (Random.Int(2)) {
                            case 0:
                            default:
                                PotionOfInvulnerability Poi = new PotionOfInvulnerability();
                                Poi.collect();
                                break;
                            case 1:
                                PotionOfStrength Pos = new PotionOfStrength();
                                Pos.collect();
                                break;
                        }
                    } else if (!ch.properties().contains(Char.Property.ELIXIR)) {
                        PotionOfLightHealing Polh = new PotionOfLightHealing();
                        Polh.collect();
                    }
            }
        }
    }
}