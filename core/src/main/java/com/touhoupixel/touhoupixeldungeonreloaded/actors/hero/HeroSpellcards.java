package com.touhoupixel.touhoupixeldungeonreloaded.actors.hero;

import static com.touhoupixel.touhoupixeldungeonreloaded.Dungeon.heroine;
import static com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor.TICK;

import static java.lang.Math.min;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AllyBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AuraReimu;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BossKiller;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Roots;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Terror;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YoumuAbility;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossHecatia;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Marisa;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Beam;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.PurpleParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.WhiteFlameParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.CellSelector;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.BArray;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class HeroSpellcards {
    public static boolean activateSpellcard(){
        if (Statistics.cardSpiritBottle) {
            if (heroine.buff(AnkhInvulnerability.class) != null) {
                GLog.w(Messages.get(Marisa.class, "already_inv"));
            } else if (Statistics.spellcard < 1 && !(heroine.HT > 4) && !(heroine.HP > 4)) {
                GLog.w(Messages.get(Marisa.class, "no_spell_card"));
            } else if (Statistics.spellcard > 0) {
                if (Statistics.cardHijiriSutra) {
                    Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
                } else {
                    Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
                }
                Statistics.spellcard -= 1;
                Statistics.spellcarduse = true;
                GameScene.flash(0x80FFFFFF);
                Sample.INSTANCE.play(Assets.Sounds.BLAST);
                heroine.spendAndNext(1f);
            } else {
                if (Statistics.cardHijiriSutra) {
                    Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
                } else {
                    Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
                }
                heroine.HP -= 4;
                heroine.HT -= 4;
                Statistics.HT_bonus -= 4;
                Statistics.spellcarduse = true;
                GameScene.flash(0x80FFFFFF);
                Sample.INSTANCE.play(Assets.Sounds.BLAST);
                heroine.spendAndNext(1f);
            }
        } else {
            if (Statistics.spellcard < 1) {
                GLog.w(Messages.get(Marisa.class, "no_spell_card"));
            } else if (heroine.buff(AnkhInvulnerability.class) != null) {
                GLog.w(Messages.get(Marisa.class, "already_inv"));
            } else {
                if (Statistics.cardHijiriSutra) {
                    Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
                } else {
                    Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
                }
                for (Mob mob : Dungeon.level.mobs) {
                    if (Dungeon.floor == 40 && mob instanceof BossHecatia && mob.isAlive()) {
                        Buff.prolong(mob, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 3f);
                        GLog.w(Messages.get(Marisa.class, "bomb_barrier"));
                    }
                }
                Statistics.spellcard -= 1;
                Statistics.bomb_count += 1;
                Statistics.spellcarduse = true;
                GameScene.flash(0x80FFFFFF);
                Sample.INSTANCE.play(Assets.Sounds.BLAST);
                heroine.spendAndNext(1f);

                switch (Dungeon.heroine.heroClass) {
                    case PLAYERREIMU:
                        reimuBomb();
                        break;
                    case PLAYERMARISA:
                        GameScene.selectCell(zapper);
                        break;
                    case PLAYERSANAE:
                        sanaeBomb();
                        break;
                    case PLAYERYOUMU:
                        Dungeon.heroine.buff(YoumuAbility.class).bomb();
                        break;
                    case PLAYERSAKUYA:
                        sakuyaBomb();
                        break;
                }
            }
        }
        return true;
    }

    private static void reimuBomb() {
        Blindness bld = heroine.buff(Blindness.class);
        if (bld != null) bld.detach();
        Buff.affect(heroine, ExorcistCircle.class).setup();
    }

    public static CellSelector.Listener zapper = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer target) {
            if (target != null) {
                //FIXME this safety check shouldn't be necessary
                //it would be better to eliminate the curItem static variable.
                final Ballistica[] lasers;
                int w = Dungeon.level.width();
                if (Statistics.cardMiniHakkeroMissile){
                    switch (laserPlacement(heroine.pos, target)) {
                        case VERTICAL:
                            lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos - w, target - w, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos - 2*w, target - 2*w, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos + w, target + w, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos + 2*w, target + 2*w, Ballistica.WONT_STOP)};
                            break;
                        default:
                            lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos - 1, target - 1, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos - 2, target - 2, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos + 1, target + 1, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos + 2, target + 2, Ballistica.WONT_STOP)};
                            break;
                    }
                }
                else {
                    switch (laserPlacement(heroine.pos, target)) {
                        case VERTICAL:
                            lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos - w, target - w, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos + w, target + w, Ballistica.WONT_STOP)};
                            break;
                        default:
                            lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos - 1, target - 1, Ballistica.WONT_STOP),
                                    new Ballistica(heroine.pos + 1, target + 1, Ballistica.WONT_STOP)};
                            break;
                    }
                }
                final Ballistica beam = lasers[0];
                int cell = beam.collisionPos;

                if (target == heroine.pos || cell == heroine.pos) {
                    GLog.i(Messages.get(Wand.class, "self_target"));
                    return;
                }

                heroine.sprite.zap(cell);
                heroine.busy();
                marisaBombFx(beam, new Callback() {
                    public void call() {
                        for (Ballistica laser : lasers) {

                            boolean terrainAffected = false;
                            ArrayList<Char> chars = new ArrayList<>();

                            for (int c : laser.subPath(1, laser.dist)) {

                                Char ch;
                                if ((ch = Actor.findChar(c)) != null) {
                                    chars.add(ch);
                                }

                                if (Dungeon.level.flamable[c]) {

                                    Dungeon.level.destroy(c);
                                    GameScene.updateMap(c);
                                    terrainAffected = true;

                                }

                                CellEmitter.center(c).burst(PurpleParticle.BURST, Random.IntRange(1, 2));
                            }

                            if (terrainAffected) {
                                Dungeon.observe();
                            }
                            for (Char ch : chars) {
                                ch.damage(laserDamageRoll(), this);
                            }
                        }
                        heroine.spendAndNext(TICK);
                    }

                });

            }
        }

        @Override
        public String prompt() {
            return Messages.get(Wand.class, "prompt");
        }
    };

    private static void marisaBombFx(Ballistica beam, Callback callback) {
        int cell = beam.path.get(beam.dist);
        if (Statistics.cardMiniHakkeroMissile){
            heroine.sprite.parent.add(new Beam.MasterSparkWideRay(heroine.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cell )));
        }
        else {
            heroine.sprite.parent.add(new Beam.MasterSparkRay(heroine.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cell )));
        }
        callback.call();
    }

    private static int minLaserDamage(){
        return 4 + Dungeon.floor*2 + ((Dungeon.floor) / 30) * 20; // After the 29th floor there is a sharp increase in damage
    }
    private static int maxLaserDamage(){
        return 8 + (Dungeon.floor + 1)*6 + ((Dungeon.floor) / 30) * 50;
    }
    private static int laserDamageRoll(){
        int dmg = Random.NormalIntRange(minLaserDamage(), maxLaserDamage());

        Char enemy = heroine.enemy();
        if (heroine.buff(WandZeroDamage.class) != null){
            dmg *= 0f;
        }
        if (heroine.buff(MagicBuff.class) != null) {
            dmg *= 1.25f;
        }
        if (Statistics.cardTeacupMarisa) {
            dmg *= 1.25f;
        }

        if (Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.MINIBOSS) ||
                Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.BOSS)){
            dmg *= 2f;
        }

        return dmg;
    }
    private enum LaserPlacement{
        VERTICAL,

        HORIZONTAL;
    }
    private static LaserPlacement laserPlacement(int from, int to){

        int w = Dungeon.level.width();

        int x0 = from % w;
        int x1 = to % w;
        int y0 = from / w;
        int y1 = to / w;

        int dx = x1 - x0;
        int dy = y1 - y0;

        int dxAbs = Math.abs( dx );
        int dyAbs = Math.abs( dy );

        if (dyAbs > dxAbs){
            return LaserPlacement.HORIZONTAL;
        }
        else {
            return LaserPlacement.VERTICAL;
        }
    }
    // Sanae's ability
    public static void sanaeBomb() {
        final int initDamage = 10 + Dungeon.floor*4;
        float damage = initDamage;
        int targets = 0;
        float decreaseFact = 0.8f;
        for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
            if (Dungeon.level.heroFOV[mob.pos] && (Dungeon.level.map[mob.pos] == Terrain.GRASS || Dungeon.level.map[mob.pos] == Terrain.WATER)) {
                targets++;
                damage = damage * (1 + decreaseFact * (targets - 1)) / targets; // if the enemy is one, he will receive 100% damage, if 2, then 90%, if 3, then 78%, etc.
            }
            if (damage < initDamage * 0.2f) {
                damage = initDamage * 0.2f; // Min damage - 20% of the initial value
                break;
            }
        }
        int finalDamage = (int)damage;
        for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
            if (Dungeon.level.heroFOV[mob.pos] && (Dungeon.level.map[mob.pos] == Terrain.GRASS || Dungeon.level.map[mob.pos] == Terrain.WATER)) {
                if (Dungeon.level.map[mob.pos] == Terrain.GRASS){
                    Buff.prolong(mob, Roots.class, 10f);
                    if (Statistics.cardSanaeAmuletAlt){
                        Buff.affect(mob, Poison.class).set(5 + Dungeon.floor*2/3);
                    }
                }
                if (Dungeon.level.map[mob.pos] == Terrain.WATER) {
                    Buff.prolong(mob, Vertigo.class, 10f);
                    if (Statistics.cardSanaeAmuletAlt){
                        Buff.prolong(mob, Terror.class, 10f);
                    }
                }

                mob.damage(finalDamage, heroine);
            }
        }
    }
    public static void sakuyaBomb(){
        TimekeepersHourglass tH = heroine.belongings.getItem(TimekeepersHourglass.class);
        if (tH != null){
            tH.charge = Math.min(tH.charge + 3, tH.getChargeCap());
            Item.updateQuickslot();
        }
        if (Statistics.cardMaidKnifeRicochet) {
            Buff.affect(heroine, Swiftthistle.TimeBubble.class).set(9f);
        }
    }

    public static class ExorcistCircle extends Buff{
        private ArrayList<Integer> firePositions = new ArrayList<>();
        private ArrayList<Emitter> fireEmitters = new ArrayList<>();
        float left = 0;
        {
            type = buffType.POSITIVE;
            actPriority = BUFF_PRIO;
        }
        private static final float DURATION = 10f;
        private static final float DURATION_WITH_ABILITY = 30f;
        @Override
        public int icon() {
            return BuffIndicator.FIRE;
        }

        @Override
        public void tintIcon(Image icon) {
            icon.hardlight(2.4f, 2.4f, 2.4f);
        }

        @Override
        public float iconFadePercent() {
            if (Statistics.cardYingYangOrbNeedle) {
                return Math.max(0, (DURATION_WITH_ABILITY - left) / DURATION_WITH_ABILITY);
            }
            return Math.max(0, (DURATION - left) / DURATION);
        }

        @Override
        public String iconTextDisplay() {
            return Integer.toString((int)left);
        }

        @Override
        public String toString() {
            return Messages.get(this, "name");
        }

        @Override
        public String desc() {
            return Messages.get(this, "desc", left);
        }

        @Override
        public boolean act() {


            if (firePositions.contains(target.pos)){
                if (!Statistics.cardYingYangOrbNeedle || Random.Int(10) > 5) {
                    int rng = Random.Int(20);
                    Char ch = heroine;
                    if (rng > 7) {
                        ArrayList<Buff> candidates = new ArrayList<>();
                        for (Buff b : ch.buffs())
                            if (b.type == Buff.buffType.NEGATIVE && !(b instanceof AllyBuff))
                                candidates.add(b);
                        if (!candidates.isEmpty()) {
                            Random.shuffle(candidates);
                            ch.buff(candidates.get(0).getClass()).detach();
                            ch.sprite.centerEmitter().burst(WhiteFlameParticle.FACTORY, 20);
                        }
                    } else if (rng > 1 && rng <= 7) {
                        ch.HP = min((int) (ch.HP + ch.HT * 0.03f), ch.HT);
                        ch.sprite.add(CharSprite.State.HEALING);
                        ch.sprite.remove(CharSprite.State.HEALING);
                    } else {
                        ch.buff(AuraReimu.class).createSphere();
                    }
                }
            }
            else {
                detach();
            }

            for (Char ch : chars()){
                if (ch instanceof Hero || !firePositions.contains(ch.pos)) continue;
                int damage = (int)(1f * Random.NormalIntRange( 1, 1 + Dungeon.floor*2 ));
                ch.damage(damage, this);
            }
            left--;
            BuffIndicator.refreshHero();
            if (left <= 0){
                detach();
            } else {
                updateFx();
            }
            spend(TICK);
            return true;
        }

        @Override
        public void detach() {
            firePositions.clear();
            super.detach();
        }

        public void setup(){
            left = Statistics.cardYingYangOrbNeedle ? DURATION_WITH_ABILITY : DURATION;
            int dist = Statistics.cardYingYangOrbNeedle ? 7 : 3;

            PathFinder.buildDistanceMap( heroine.pos, BArray.or( Dungeon.level.passable, Dungeon.level.avoid, null ), dist );
            boolean observe = false;
            for (int i = 0; i < PathFinder.distance.length; i++) { // at first destroy...
                if (PathFinder.distance[i] < Integer.MAX_VALUE && !firePositions.contains(i) && Dungeon.level.flamable[i]){
                    Dungeon.level.destroy(i);
                    GameScene.updateMap(i);
                    observe = true;
                }
            }
            if (observe) Dungeon.observe();
            for (int i = 0; i < PathFinder.distance.length; i++) { // then set fire
                if (PathFinder.distance[i] < Integer.MAX_VALUE && !firePositions.contains(i) && Dungeon.level.heroFOV[i]) {
                    firePositions.add(i); //There will be a flaw: We don't set fire to tiles behind the walls, but we destroy them anyway.
                }
            }
        if (target != null) {
            fx(false);
            fx(true);
        }
    }
    public void updateFx(){
            Dungeon.observe();
            fx(false);
            fx(true);
    }
    @Override
    public void fx(boolean on) {
        if (on) {
            for (int i : firePositions) {
                if (!Dungeon.level.heroFOV[i]) continue;
                Emitter e = CellEmitter.get(i);
                e.pour(WhiteFlameParticle.FACTORY, 0.02f);
                fireEmitters.add(e);
            }
        } else {
            for (Emitter e : fireEmitters) {
                e.on = false;
            }
            fireEmitters.clear();
        }
    }


        private static final String FIRE_POSITIONS = "fire_positions";
        private static final String LEFT = "left";

        @Override
        public void storeInBundle(Bundle bundle) {
            super.storeInBundle(bundle);

            int[] values = new int[firePositions.size()];
            for (int i = 0; i < values.length; i ++)
                values[i] = firePositions.get(i);
            bundle.put(FIRE_POSITIONS, values);

            bundle.put(LEFT, left);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            super.restoreFromBundle(bundle);

            int[] values = bundle.getIntArray(FIRE_POSITIONS);
            for (int value : values) {
                firePositions.add(value);
            }

            left = bundle.getInt(LEFT);
        }
    }
}
