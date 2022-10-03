package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.FlandreCooldown;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Life;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeonreloaded.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeonreloaded.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.FlandreSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.KeineSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Flandre extends Mob {

    {
        spriteClass = FlandreSprite.class;

        HP = HT = Dungeon.depth*10;
        defenseSkill = Dungeon.depth;

        state = PASSIVE;

        flying = true;

        EXP = 0;
        maxLvl = 99;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Dungeon.depth, Dungeon.depth+5);
    }

    @Override
    public int attackSkill(Char target) {
        return Dungeon.depth+5;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void beckon( int cell ) {
        // Do nothing
    }

    @Override
    public void die( Object cause ) {
        Dungeon.level.drop(new Life(), pos ).sprite.drop();
        Dungeon.level.drop(new Spellcard(), pos ).sprite.drop();
        Dungeon.level.drop(new PotionOfHealing(), pos ).sprite.drop();

        if (Dungeon.depth < 10) {
            Dungeon.level.drop((Weapon) Generator.random(Generator.Category.WEP_T2), pos).sprite.drop();
            Dungeon.level.drop((Armor) Generator.random(Generator.Category.ARMOR_T2), pos).sprite.drop();
        } else if (Dungeon.depth < 30) {
            Dungeon.level.drop((Weapon) Generator.random(Generator.Category.WEP_T3), pos).sprite.drop();
            Dungeon.level.drop((Armor) Generator.random(Generator.Category.ARMOR_T3), pos).sprite.drop();
        } else if (Dungeon.depth < 50) {
            Dungeon.level.drop((Weapon) Generator.random(Generator.Category.WEP_T4), pos).sprite.drop();
            Dungeon.level.drop((Armor) Generator.random(Generator.Category.ARMOR_T4), pos).sprite.drop();
        } else {
            Dungeon.level.drop((Weapon) Generator.random(Generator.Category.WEP_T5), pos).sprite.drop();
            Dungeon.level.drop((Armor) Generator.random(Generator.Category.ARMOR_T5), pos).sprite.drop();
        }
        super.die( cause );
    }

    @Override
    public void add(Buff buff) {
        super.add(buff);
        if (state == PASSIVE && buff.type == Buff.buffType.NEGATIVE){
            state = HUNTING;
        }
    }

    @Override
    public void damage( int dmg, Object src ) {

        if (state == PASSIVE) {
            state = HUNTING;
        }

        super.damage( dmg, src );
    }

    @Override
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && this.HP != this.HT && this.buff(FlandreCooldown.class) == null) {
            Dungeon.hero.damage(Dungeon.depth+20, this);
            GameScene.flash(-65536);
            Buff.prolong(this, FlandreCooldown.class, FlandreCooldown.DURATION);
            Sample.INSTANCE.play( Assets.Sounds.BLAST );
            GLog.w(Messages.get(this, "destruction_wave"));
            if (enemy == Dungeon.hero && !enemy.isAlive()) {
                Dungeon.fail(Flandre.class);
            }
        }
        return super.act();
    }
}