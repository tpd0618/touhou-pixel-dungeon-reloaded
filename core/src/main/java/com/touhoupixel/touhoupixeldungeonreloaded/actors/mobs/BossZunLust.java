package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FlandreCooldown;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Paralysis;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tickets.FiveStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tickets.FourStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.items.tickets.ThreeStarTicket;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.FlandreSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ZUNLustSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class BossZunLust extends Mob {

    {
        spriteClass = ZUNLustSprite.class;

        HP = HT = 2000;
        defenseSkill = 99;

        properties.add(Property.HUMAN);
        properties.add(Property.MINIBOSS);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        immunities.add(Paralysis.class);
        immunities.add(Fire.class);

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(50, 70);
    }

    @Override
    public int attackSkill(Char target) {
        return 99;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 10);
    }

    @Override
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && this.state != SLEEPING && this.state != FLEEING) {

        }
        return super.act();
    }
}