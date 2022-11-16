package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Generator;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeonreloaded.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.AyaSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ChimataSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Chimata extends Mob {

    {
        spriteClass = ChimataSprite.class;

        HP = HT = 229;
        defenseSkill = 40;
        EXP = 19;
        maxLvl = 47;

        loot = Generator.Category.SCROLL;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(23, 28);
    }

    @Override
    public int attackSkill(Char target) {
        return 45;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.hero && enemy.alignment != this.alignment && Random.Int(5) == 0) {
            MeleeWeapon meleeweapon = Dungeon.hero.belongings.getItem(MeleeWeapon.class);
            Armor armor = Dungeon.hero.belongings.getItem(Armor.class);
            if (meleeweapon != null) {
                meleeweapon.downgrade();
                meleeweapon.enchantment = null;
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(this, "weapondowngrade"));
            }
            if (armor != null) {
                armor.downgrade();
                armor.glyph = null;
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(this, "armordowngrade"));
            }
        }
        return damage;
    }
}