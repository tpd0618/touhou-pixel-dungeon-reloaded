package com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.HardSearch;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.ChenBuffed;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Bomb;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BlazingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.PhoenixSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.RingoSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class PhoenixFeathers extends Spellcard{
    private static final float DELAY = 1f;
    private int turnsToSummoning = 0;
    {
        left = 15;
    }

    @Override
    protected boolean act() {
        if (turnsToSummoning <= 0) {
            if (summonPhoenix()) turnsToSummoning = Random.Int(4,6);
        }
        else {
            turnsToSummoning--;
        }

        return super.act();
    }
    private boolean summonPhoenix(){
        for (int j = 0; j < 1 + Random.Int(2 + Statistics.difficulty*2) / 7; j++) { //at high and higher difficulty, 2 birds can be summoned at a time with chances 12%, 30%, 41%, 50%
            int cell = -1;
            for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
                int p = user.pos + PathFinder.NEIGHBOURS8[i];
                if (Actor.findChar(p) == null && Dungeon.level.passable[p]) {
                    cell = p;
                    break;
                }
            }
            if (cell == -1 && j == 0) return false;
            else if (cell == -1 && j > 0) return true;

            Phoenix phoenix = new Phoenix();

            phoenix.state = phoenix.WANDERING;
            phoenix.pos = cell;
            GameScene.add(phoenix, DELAY);
            ScrollOfTeleportation.appear(phoenix, phoenix.pos);
            Dungeon.level.occupyCell(phoenix);
        }

        return true;
    }

    @Override
    public void activate() {
        new BlazingTrap().set(user.pos).activate();
    }

    private final String TURNS_TO_SUMMONING = "turns_to_summoning";
    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(TURNS_TO_SUMMONING, turnsToSummoning);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        turnsToSummoning = bundle.getInt(TURNS_TO_SUMMONING);
        super.restoreFromBundle(bundle);
    }


    public static class Phoenix extends Mob {
        {
            spriteClass = PhoenixSprite.class;

            HP = HT = 30;
            defenseSkill = 5;
            EXP = 2;
            maxLvl = 12;

            properties.add(Property.WARP);

            baseSpeed = 2.5f;

            flying = true;
            immunities.add(Fire.class);
        }

        @Override
        public int damageRoll() {
            return Random.NormalIntRange(10, 20);
        }

        @Override
        public int attackSkill(Char target) {
            return 25;
        }

        @Override
        public int drRoll() {
            return Random.NormalIntRange(1, 3);
        }

        @Override
        public int attackProc(Char hero, int damage) {
            damage = super.attackProc(enemy, damage);
            Buff.affect(hero, Burning.class).reignite(hero, 5f);
            new ExplosiveTrap().set(enemy.pos).activate();
            die(new Bomb());

            return damage;
        }
    }
}