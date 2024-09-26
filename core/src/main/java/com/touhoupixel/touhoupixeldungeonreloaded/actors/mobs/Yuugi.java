package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.YuugiSprite;
import com.watabou.utils.Random;

public class Yuugi extends Mob {

    {
        spriteClass = YuugiSprite.class;

        HP = HT = 322;
        defenseSkill = 35;
        EXP = 19;
        maxLvl = 47;

        properties.add(Property.WARP);

        loot = new PotionOfHealing();
        lootChance = 0.09f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(125, 182);
    }

    @Override
    public int attackSkill(Char target) {
        return 45;
    }

    @Override
    public float attackDelay() {
        double healthPercentage = (double) this.HP / this.HT * 100;

        if (healthPercentage <= 100 && healthPercentage > 80) {
            return 1f;
        } else if (healthPercentage <= 80 && healthPercentage > 60) {
            return 0.5f;
        } else if (healthPercentage <= 60 && healthPercentage > 40) {
            return 0.25f;
        } else if (healthPercentage <= 40 && healthPercentage > 20) {
            return 0.125f;
        } else if (healthPercentage <= 20 && healthPercentage > 0) {
            return 0.0625f;
        }
        return 1f;
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
        return damage;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(23, 33);
    }
}