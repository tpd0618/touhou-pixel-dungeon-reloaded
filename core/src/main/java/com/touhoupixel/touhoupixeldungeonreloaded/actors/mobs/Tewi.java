package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.spells.ReclaimTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AlarmTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.AntiHealTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BlazingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.BurningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ChillingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ConfusionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CorrosionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DegradeTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DespairTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DestroyArmorTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DisarmingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.DisintegrationTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExConfusionTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlashingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FlockTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.FrostTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GeyserTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.GrippingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.HecatiaTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.OozeTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.PitfallTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.PoisonDartTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.RockfallTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ShockingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SlowTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.StormTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.TeleportationTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.ToxicTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.WarpingTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.traps.WeakeningTrap;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.TewiSprite;
import com.watabou.utils.Random;

public class Tewi extends Mob {

    {
        spriteClass = TewiSprite.class;

        HP = HT = 45;
        defenseSkill = 12;
        EXP = 9;
        maxLvl = 20;

        loot = new ReclaimTrap();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 6);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        if (cause == Dungeon.hero) {
            switch (Random.Int(33)) {
                case 0:
                default:
                    new AlarmTrap().set(enemy.pos).activate();
                    break;
                case 1:
                    new AntiHealTrap().set(enemy.pos).activate();
                    break;
                case 2:
                    new BlazingTrap().set(enemy.pos).activate();
                    break;
                case 3:
                    new BurningTrap().set(enemy.pos).activate();
                    break;
                case 4:
                    new ChillingTrap().set(enemy.pos).activate();
                    break;
                case 5:
                    new ConfusionTrap().set(enemy.pos).activate();
                    break;
                case 6:
                    new CorrosionTrap().set(enemy.pos).activate();
                    break;
                case 7:
                    new CursingTrap().set(enemy.pos).activate();
                    break;
                case 8:
                    new DegradeTrap().set(enemy.pos).activate();
                    break;
                case 9:
                    new DespairTrap().set(enemy.pos).activate();
                    break;
                case 10:
                    new DestroyArmorTrap().set(enemy.pos).activate();
                    break;
                case 11:
                    new DisarmingTrap().set(enemy.pos).activate();
                    break;
                case 12:
                    new DisintegrationTrap().set(enemy.pos).activate();
                    break;
                case 13:
                    new WarpingTrap().set(enemy.pos).activate();
                    break;
                case 14:
                    new ExConfusionTrap().set(enemy.pos).activate();
                    break;
                case 15:
                    new ExplosiveTrap().set(enemy.pos).activate();
                    break;
                case 16:
                    new FlashingTrap().set(enemy.pos).activate();
                    break;
                case 17:
                    new FlockTrap().set(enemy.pos).activate();
                    break;
                case 18:
                    new FrostTrap().set(enemy.pos).activate();
                    break;
                case 19:
                    new WeakeningTrap().set(enemy.pos).activate();
                    break;
                case 20:
                    new GeyserTrap().set(enemy.pos).activate();
                    break;
                case 21:
                    new GrippingTrap().set(enemy.pos).activate();
                    break;
                case 22:
                    new HecatiaTrap().set(enemy.pos).activate();
                    break;
                case 23:
                    new OozeTrap().set(enemy.pos).activate();
                    break;
                case 24:
                    new PitfallTrap().set(enemy.pos).activate();
                    break;
                case 25:
                    new PoisonDartTrap().set(enemy.pos).activate();
                    break;
                case 26:
                    new RockfallTrap().set(enemy.pos).activate();
                    break;
                case 27:
                    new ShockingTrap().set(enemy.pos).activate();
                    break;
                case 28:
                    new SlowTrap().set(enemy.pos).activate();
                    break;
                case 29:
                    new StormTrap().set(enemy.pos).activate();
                    break;
                case 30:
                    new SummoningTrap().set(enemy.pos).activate();
                    break;
                case 31:
                    new TeleportationTrap().set(enemy.pos).activate();
                    break;
                case 32:
                    new ToxicTrap().set(enemy.pos).activate();
                    break;
            }
        }
    }
}