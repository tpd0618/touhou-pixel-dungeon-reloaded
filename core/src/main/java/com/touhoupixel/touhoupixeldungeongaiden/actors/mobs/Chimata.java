package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ChimataSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Chimata extends Mob {

    {
        spriteClass = ChimataSprite.class;

        HP = HT = 229;
        defenseSkill = 35;
        EXP = 17;
        maxLvl = 47;

        properties.add(Property.GOD);

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
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0) {
            MeleeWeapon meleeweapon = Dungeon.heroine.belongings.getItem(MeleeWeapon.class);
            Armor armor = Dungeon.heroine.belongings.getItem(Armor.class);
            if (meleeweapon != null) {
                meleeweapon.downgrade();
                meleeweapon.enchantment = null;
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(this, "weapon_downgrade"));
            }
            if (armor != null) {
                armor.downgrade();
                armor.glyph = null;
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
                GLog.w(Messages.get(this, "armor_downgrade"));
            }
            if (Statistics.difficulty > 2) {
                Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Cripple.class, Cripple.DURATION);
            }
        }
        return damage;
    }
}