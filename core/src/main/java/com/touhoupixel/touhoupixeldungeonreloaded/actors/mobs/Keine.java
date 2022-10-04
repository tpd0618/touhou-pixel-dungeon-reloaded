package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeineSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Keine extends Mob {

    {
        spriteClass = KeineSprite.class;

        HP = HT = 107;
        defenseSkill = 22;
        EXP = 9;
        maxLvl = 30;

        loot = new ScrollOfSirensSong();
        lootChance = 0.125f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 15);
    }

    @Override
    public int attackSkill(Char target) {
        return 27;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (hero.HT/2 < hero.HP && Random.Int(4) == 0) {
            Statistics.playercorruption += 1;
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            GLog.w(Messages.get(Potion.class, "corruption"));
        }
        return damage;
    }
}