package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AntiHeal;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.RegenBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Roots;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.elixirs.ElixirOfZen;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.PatchouliSprite;
import com.watabou.utils.Random;

public class Patchouli extends Mob {

    {
        spriteClass = PatchouliSprite.class;

        HP = HT = 47;
        defenseSkill = 15;
        EXP = 9;
        maxLvl = 22;

        loot = new ElixirOfZen();
        lootChance = 0.02f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 8);
    }

    @Override
    public int attackSkill(Char target) {
        return 20;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        new ExplosiveTrap().set(this.pos).activate();
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(5) == 0){
            if (Dungeon.hero.heroClass == HeroClass.PLAYERSANAE || Dungeon.hero.heroClass == HeroClass.PLAYERYOUMU || Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
                Buff.prolong(enemy, RegenBlock.class, RegenBlock.DURATION/3f);
            }
            if (Dungeon.hero.heroClass == HeroClass.PLAYERSAKUYA) {
                Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
            }
        }
        return damage;
    }
}