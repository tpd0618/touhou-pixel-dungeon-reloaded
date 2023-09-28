package com.touhoupixel.touhoupixeldungeongaiden.items.scrolls;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.*;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Identification;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfNamelessStory extends Scroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_NS;
	}

	@Override
	public void doRead() {
		curUser.sprite.parent.add(new Identification(curUser.sprite.center().offset(0, -16)));
		GLog.w(Messages.get(this, "throw_to_use"));
		Sample.INSTANCE.play(Assets.Sounds.READ);
		identify();
		readAnimation();
	}

	@Override
	public void onThrow( int cell ) {
		Heap heap = Dungeon.level.drop( this, cell );
		Char ch = (Char) Actor.findChar(cell);
		if (!heap.isEmpty() && ch != null && ch != Dungeon.heroine && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
			GLog.i(Messages.get(this, "target_exterminate"));
			Sample.INSTANCE.play(Assets.Sounds.BLAST);

			if (ch instanceof Wriggle){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Wriggle) {
						Statistics.extermination_number = 1;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Nazrin){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Nazrin) {
						Statistics.extermination_number = 2;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Mike){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Mike) {
						Statistics.extermination_number = 3;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Larva){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Larva) {
						Statistics.extermination_number = 4;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kogasa){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kogasa) {
						Statistics.extermination_number = 5;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Kokoro){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kokoro) {
						Statistics.extermination_number = 6;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Ringo){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Ringo) {
						Statistics.extermination_number = 7;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Sunny){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Sunny) {
						Statistics.extermination_number = 8;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Akyuu){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Akyuu) {
						Statistics.extermination_number = 9;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kosuzu){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kosuzu) {
						Statistics.extermination_number = 10;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Parsee){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Parsee) {
						Statistics.extermination_number = 11;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Miyoi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Miyoi) {
						Statistics.extermination_number = 12;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Aunn){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Aunn) {
						Statistics.extermination_number = 13;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Luna){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Luna) {
						Statistics.extermination_number = 14;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Lily){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Lily) {
						Statistics.extermination_number = 15;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Cirno){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Cirno) {
						Statistics.extermination_number = 16;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Alice){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Alice) {
						Statistics.extermination_number = 17;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Mystia){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Mystia) {
						Statistics.extermination_number = 18;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Chen){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Chen) {
						Statistics.extermination_number = 19;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Star){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Star) {
						Statistics.extermination_number = 20;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Eika){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Eika) {
						Statistics.extermination_number = 21;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Marisa){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Marisa) {
						Statistics.extermination_number = 22;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Reisen){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Reisen) {
						Statistics.extermination_number = 23;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Tewi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Tewi) {
						Statistics.extermination_number = 24;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Keine){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Keine) {
						Statistics.extermination_number = 25;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Mokou){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Mokou) {
						Statistics.extermination_number = 26;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kagerou){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kagerou) {
						Statistics.extermination_number = 27;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Mamizou){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Mamizou) {
						Statistics.extermination_number = 28;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Misumaru){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Misumaru) {
						Statistics.extermination_number = 29;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Koakuma){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Koakuma) {
						Statistics.extermination_number = 30;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Meiling){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Meiling) {
						Statistics.extermination_number = 31;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Patchouli){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Patchouli) {
						Statistics.extermination_number = 32;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Sakuya){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Sakuya) {
						Statistics.extermination_number = 33;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Remilia){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Remilia) {
						Statistics.extermination_number = 34;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Sekibanki){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Sekibanki) {
						Statistics.extermination_number = 35;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Nitori){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Nitori) {
						Statistics.extermination_number = 36;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Takane){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Takane) {
						Statistics.extermination_number = 37;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Momiji){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Momiji) {
						Statistics.extermination_number = 38;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Hatate){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Hatate) {
						Statistics.extermination_number = 39;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Aya){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Aya) {
						Statistics.extermination_number = 40;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Seiga){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Seiga) {
						Statistics.extermination_number = 41;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Yuuka){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yuuka) {
						Statistics.extermination_number = 42;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Nemuno){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Nemuno) {
						Statistics.extermination_number = 43;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Tsukasa){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Tsukasa) {
						Statistics.extermination_number = 44;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Joon){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Joon) {
						Statistics.extermination_number = 45;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Shion){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Shion) {
						Statistics.extermination_number = 46;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kasen){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kasen) {
						Statistics.extermination_number = 47;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Futo){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Futo) {
						Statistics.extermination_number = 48;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kyouko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kyouko) {
						Statistics.extermination_number = 49;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Ichirin){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Ichirin) {
						Statistics.extermination_number = 50;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Momoyo){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Momoyo) {
						Statistics.extermination_number = 51;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Shou){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Shou) {
						Statistics.extermination_number = 52;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Miko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Miko) {
						Statistics.extermination_number = 53;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Hijiri){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Hijiri) {
						Statistics.extermination_number = 54;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Biruko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Biruko) {
						Statistics.extermination_number = 55;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kisume){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kisume) {
						Statistics.extermination_number = 56;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kutaka){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kutaka) {
						Statistics.extermination_number = 57;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Saki){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Saki) {
						Statistics.extermination_number = 58;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Tojiko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Tojiko) {
						Statistics.extermination_number = 59;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Hina){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Hina) {
						Statistics.extermination_number = 60;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Komachi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Komachi) {
						Statistics.extermination_number = 61;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Shizuha){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Shizuha) {
						Statistics.extermination_number = 62;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Minoriko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Minoriko) {
						Statistics.extermination_number = 63;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Sannyo){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Sannyo) {
						Statistics.extermination_number = 64;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Megumu){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Megumu) {
						Statistics.extermination_number = 65;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Sanae){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Sanae) {
						Statistics.extermination_number = 66;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Suwako){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Suwako) {
						Statistics.extermination_number = 67;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kanako){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kanako) {
						Statistics.extermination_number = 68;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Benben){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Benben) {
						Statistics.extermination_number = 69;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Yatsuhashi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yatsuhashi) {
						Statistics.extermination_number = 70;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Shinmyomaru){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Shinmyomaru) {
						Statistics.extermination_number = 71;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Seija){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Seija) {
						Statistics.extermination_number = 72;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Mai){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Mai) {
						Statistics.extermination_number = 73;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Satono){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Satono) {
						Statistics.extermination_number = 74;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Okina){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Okina) {
						Statistics.extermination_number = 75;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Yamame){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yamame) {
						Statistics.extermination_number = 76;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Kaguya){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Kaguya) {
						Statistics.extermination_number = 77;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Ran){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Ran) {
						Statistics.extermination_number = 78;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Youmu){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Youmu) {
						Statistics.extermination_number = 79;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Yuyuko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yuyuko) {
						Statistics.extermination_number = 80;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Satori){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Satori) {
						Statistics.extermination_number = 81;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Koishi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Koishi) {
						Statistics.extermination_number = 82;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Yuugi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yuugi) {
						Statistics.extermination_number = 83;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Suika){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Suika) {
						Statistics.extermination_number = 84;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Clownpiece){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Clownpiece) {
						Statistics.extermination_number = 85;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Chimata){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Chimata) {
						Statistics.extermination_number = 86;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Rumia){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Rumia) {
						Statistics.extermination_number = 87;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Iku){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Iku) {
						Statistics.extermination_number = 88;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Tenshi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Tenshi) {
						Statistics.extermination_number = 89;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Seiran){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Seiran) {
						Statistics.extermination_number = 90;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Eirin){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Eirin) {
						Statistics.extermination_number = 91;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Doremy){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Doremy) {
						Statistics.extermination_number = 92;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Sagume){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Sagume) {
						Statistics.extermination_number = 93;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Sumireko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Sumireko) {
						Statistics.extermination_number = 94;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Renko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Renko) {
						Statistics.extermination_number = 95;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Junko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Junko) {
						Statistics.extermination_number = 96;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Yukari){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yukari) {
						Statistics.extermination_number = 97;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Toyohime){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Toyohime) {
						Statistics.extermination_number = 98;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Yorihime){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yorihime) {
						Statistics.extermination_number = 99;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Reimu){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Reimu) {
						Statistics.extermination_number = 100;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Utsuho){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Utsuho) {
						Statistics.extermination_number = 101;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Yuuma){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Yuuma) {
						Statistics.extermination_number = 102;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Eiki){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Eiki) {
						Statistics.extermination_number = 103;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Keiki){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Keiki) {
						Statistics.extermination_number = 104;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			if (ch instanceof Biten){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Biten) {
						Statistics.extermination_number = 105;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Chiyari){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Chiyari) {
						Statistics.extermination_number = 106;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Enoko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Enoko) {
						Statistics.extermination_number = 107;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Hisami){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Hisami) {
						Statistics.extermination_number = 108;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Zanmu){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Zanmu) {
						Statistics.extermination_number = 109;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Hearn){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Hearn) {
						Statistics.extermination_number = 110;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Raiko){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Raiko) {
						Statistics.extermination_number = 111;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}
			if (ch instanceof Mayumi){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob instanceof Mayumi) {
						Statistics.extermination_number = 112;
						mob.exterminate();
						mob.sprite.killAndErase();
					}
				}
			}

			Heap[] heaps = new Heap[1];
			heaps[0] = Dungeon.level.heaps.get(ch.pos);
			for (Heap h : heaps) {
				for (Item i : h.items.toArray(new Item[0])){
					if (i == this){
						h.remove(i);
					}
				}
			}
		} else {
			heap.sprite.drop( cell );
		}
	}
	
	@Override
	public int value() {
		return isKnown() ? 40 * quantity : super.value();
	}
}
