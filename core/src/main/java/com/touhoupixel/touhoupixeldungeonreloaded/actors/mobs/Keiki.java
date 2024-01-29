package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Homunculus;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeikiSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Keiki extends Mob {

    {
        spriteClass = KeikiSprite.class;

        HP = HT = 328;
        defenseSkill = 40;
        EXP = 24;
        maxLvl = 75;

        properties.add(Property.GOD);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.05f;

        immunities.add(Fire.class);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(127, 197);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(67, 97);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(2) == 0) {
            if (this.pos + 1 == Dungeon.heroine.pos && Actor.findChar(this.pos + 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos + 2);
            } else if (this.pos - 1 == Dungeon.heroine.pos && Actor.findChar(this.pos - 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos - 2);
            } else if (this.pos + Dungeon.level.width() == Dungeon.heroine.pos && Actor.findChar(this.pos + Dungeon.level.width() * 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos + Dungeon.level.width() * 2);
            } else if (this.pos - Dungeon.level.width() == Dungeon.heroine.pos && Actor.findChar(this.pos - Dungeon.level.width() * 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos - Dungeon.level.width() * 2);
            } else if (this.pos + 1 + Dungeon.level.width() == Dungeon.heroine.pos && Actor.findChar(this.pos + 2 + Dungeon.level.width() * 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos + 2 + Dungeon.level.width() * 2);
            } else if (this.pos - 1 + Dungeon.level.width() == Dungeon.heroine.pos && Actor.findChar(this.pos - 2 + Dungeon.level.width() * 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos - 2 + Dungeon.level.width() * 2);
            } else if (this.pos + 1 - Dungeon.level.width() == Dungeon.heroine.pos && Actor.findChar(this.pos + 2 - Dungeon.level.width() * 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos + 2 - Dungeon.level.width() * 2);
            } else if (this.pos - 1 - Dungeon.level.width() == Dungeon.heroine.pos && Actor.findChar(this.pos - 2 - Dungeon.level.width() * 2) == null) {
                ScrollOfTeleportation.teleportToLocationHearn(this, this.pos - 2 - Dungeon.level.width() * 2);
            }
            Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION);
        }
        return damage;
    }
}