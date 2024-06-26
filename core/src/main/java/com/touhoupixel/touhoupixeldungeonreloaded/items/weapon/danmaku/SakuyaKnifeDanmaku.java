package com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.danmaku;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Sakuya;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.MissileSprite;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class SakuyaKnifeDanmaku extends MissileWeapon{
    private int minDamage = 0;
    private int maxDamage = 0;
    {
        int imageID = Random.Int(3);
        switch (imageID){ // Random color
            case 0:
                image = ItemSpriteSheet.RED_KNIFE;
                break;
            case 1:
                image = ItemSpriteSheet.GREEN_KNIFE;
                break;
            default:
                image = ItemSpriteSheet.BLUE_KNIFE;
        }
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1.2f;

        tier = 1;
    }
    public SakuyaKnifeDanmaku(int minDamage, int maxDamage){
        final float DAMAGE_SCALE = 0.8f;
        new SakuyaKnifeDanmaku(minDamage, maxDamage, DAMAGE_SCALE);
    }
    public SakuyaKnifeDanmaku(int minDamage, int maxDamage, float scaleFactor){
        this.minDamage = (int) (minDamage * scaleFactor);
        this.maxDamage = (int)(maxDamage * scaleFactor);
    }
    public int max(int lvl) {
        if (maxDamage != 0){
            return maxDamage;
        }
        else {
            return super.max(lvl);
        }
    }
    public int min(int lvl){
        if (minDamage != 0){
            return minDamage;
        }
        else {
            return super.min(lvl);
        }
    }


    @Override
    public int damageRoll(Char owner) {
        if (owner instanceof Hero) {
            Hero heroine = (Hero)owner;
            Char enemy = heroine.enemy();
            if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(heroine)) {
                //deals 75% toward max to max on surprise, instead of min to max.
                int diff = max() - min();
                int damage = augment.damageFactor(Random.NormalIntRange(
                        min() + Math.round(diff*0.75f),
                        max()));
                int exStr = heroine.STR() - STRReq();
                if (exStr > 0) {
                    damage += Random.IntRange(0, exStr);
                }
                return damage;
            }
        }
        return super.damageRoll(owner);
    }
    protected void onThrow( int cell ) {
        Char enemy = Actor.findChar( cell );
        if (enemy == null || enemy == curUser) {
            parent = null;
        } else {
            if (!curUser.shoot( enemy, this )) {
                rangedMiss( cell );
            } else {
                rangedHit( enemy, cell );
            }
        }
    }
    protected void rangedMiss( int cell ) {
        parent = null;
    }
    protected void rangedHit( Char enemy, int cell ){
            parent = null;
    }

}
