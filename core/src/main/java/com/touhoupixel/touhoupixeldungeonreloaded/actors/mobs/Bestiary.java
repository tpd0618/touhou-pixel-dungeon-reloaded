package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.mobswithspells.*;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class Bestiary {

	public static ArrayList<Class<? extends Mob>> getMobRotation(int floor) {
		ArrayList<Class<? extends Mob>> mobs = standardMobRotation(floor);
		Random.shuffle(mobs);
		return mobs;
	}

	private static ArrayList<Class<? extends Mob>> standardMobRotation(int floor) {
		if (Dungeon.isChallenged(Challenges.URA_MODE)) {
			switch (floor) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				default:
					return new ArrayList<>(Arrays.asList(
							Wriggle.class, Nazrin.class, Mike.class, Larva.class, Kogasa.class));
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
					return new ArrayList<>(Arrays.asList(
							Kokoro.class, Ringo.class, Sunny.class, Akyuu.class, Kosuzu.class));
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
					return new ArrayList<>(Arrays.asList(
							Parsee.class, Miyoi.class, Aunn.class, Luna.class, Lily.class));
				case 16:
				case 17:
				case 18:
				case 19:
				case 20:
					return new ArrayList<>(Arrays.asList(
							Cirno.class, Alice.class, Mystia.class, Chen.class, Star.class));
				case 21:
				case 22:
				case 23:
				case 24:
				case 25:
					return new ArrayList<>(Arrays.asList(
							Eika.class, Marisa.class, Reisen.class, Tewi.class, Keine.class, Kasen.class));
				case 26:
				case 27:
				case 28:
				case 29:
				case 30:
					return new ArrayList<>(Arrays.asList(
							Mokou.class, Kagerou.class, Mamizou.class, Misumaru.class, Koakuma.class, Kasen.class));
				case 31:
				case 32:
				case 33:
				case 34:
				case 35:
					return new ArrayList<>(Arrays.asList(
							Meiling.class, Patchouli.class, Sakuya.class, Remilia.class, Sekibanki.class, Sannyo.class));
				case 36:
				case 37:
				case 38:
				case 39:
				case 40:
					return new ArrayList<>(Arrays.asList(
							Nitori.class, Takane.class, Momiji.class, Hatate.class, Aya.class, Sannyo.class));
				case 41:
				case 42:
				case 43:
				case 44:
				case 45:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiga.class, Yuuka.class, Nemuno.class, Tsukasa.class, Joon.class, Renko.class));
				case 46:
				case 47:
				case 48:
				case 49:
				case 50:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shion.class, Kasen.class, Mayumi.class, Futo.class, Kyouko.class, Renko.class));
				case 51:
				case 52:
				case 53:
				case 54:
				case 55:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Ichirin.class, Momoyo.class, Shou.class, Miko.class, Hijiri.class, Hearn.class));
				case 56:
				case 57:
				case 58:
				case 59:
				case 60:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Biruko.class, Kisume.class, Kutaka.class, Saki.class, Raiko.class, Hearn.class));
				case 61:
				case 62:
				case 63:
				case 64:
				case 65:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Tojiko.class, Hina.class, Komachi.class, Hearn.class, Shizuha.class, Utsuho.class));
				case 66:
				case 67:
				case 68:
				case 69:
				case 70:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Minoriko.class, Sannyo.class, Megumu.class, Sanae.class, Suwako.class, Utsuho.class));
				case 71:
				case 72:
				case 73:
				case 74:
				case 75:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Kanako.class, Hisami.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Yuuma.class));
				case 76:
				case 77:
				case 78:
				case 79:
				case 80:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Mai.class, Satono.class, Okina.class, Chiyari.class, Yamame.class, Kaguya.class, Yuuma.class));
				case 81:
				case 82:
				case 83:
				case 84:
				case 85:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class, Eiki.class));
				case 86:
				case 87:
				case 88:
				case 89:
				case 90:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Eiki.class));
				case 91:
				case 92:
				case 93:
				case 94:
				case 95:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Tenshi.class, Enoko.class, Seiran.class, Eirin.class, Doremy.class, Sagume.class, Toyohime.class, Eiki.class));
				case 96:
				case 97:
				case 98:
				case 99:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Sumireko.class, Renko.class, Junko.class, Zanmu.class, Reimu.class, Keiki.class, Yorihime.class, Eiki.class));
			}
		} else {
			switch (floor) {
				case 1:
				case 2:
				default:
					return new ArrayList<>(Arrays.asList(
							Wriggle.class, Nazrin.class, Mike.class, Larva.class, Kogasa.class));
				case 3:
				case 4:
				case 5:
					return new ArrayList<>(Arrays.asList(
							Kokoro.class, Ringo.class, Sunny.class, Akyuu.class, Kosuzu.class));
				case 6:
				case 7:
					return new ArrayList<>(Arrays.asList(
							Parsee.class, Miyoi.class, Aunn.class, Luna.class, Lily.class, Cirno.class));
				case 8:
				case 9:
				case 10:
					return new ArrayList<>(Arrays.asList(
							Alice.class, Mystia.class, Chen.class, Star.class, Eika.class, Marisa.class));
				case 11:
				case 12:
					return new ArrayList<>(Arrays.asList(
							Reisen.class, Tewi.class, Keine.class, Mokou.class, Kagerou.class, Mamizou.class));
				case 13:
				case 14:
				case 15:
					return new ArrayList<>(Arrays.asList(
							Misumaru.class, Koakuma.class, Meiling.class, Patchouli.class, Sakuya.class, Remilia.class, Mokou.class));
				case 16:
				case 17:
					return new ArrayList<>(Arrays.asList(
							Sekibanki.class, Nitori.class, Takane.class, Momiji.class, Hatate.class, Aya.class, Mokou.class));
				case 18:
				case 19:
				case 20:
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Yuuka.class, Nemuno.class, Tsukasa.class, Joon.class, Shion.class, Kasen.class, Mayumi.class, Mokou.class));
				case 21:
				case 22:
					return new ArrayList<>(Arrays.asList(
							Futo.class, Kyouko.class, Ichirin.class, Momoyo.class, Shou.class, Miko.class, Hijiri.class, Raiko.class));
				case 23:
				case 24:
				case 25:
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kisume.class, Kutaka.class, Saki.class, Tojiko.class, Hina.class, Komachi.class, Hearn.class, Raiko.class));
				case 26:
				case 27:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Minoriko.class, Sannyo.class, Megumu.class, Sanae.class, Suwako.class, Kanako.class, Hisami.class, Raiko.class));
				case 28:
				case 29:
				case 30:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Mai.class, Satono.class, Okina.class, Chiyari.class, Suwako.class));
				case 31:
				case 32:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class, Suwako.class, Okina.class));
				case 33:
				case 34:
				case 35:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Tenshi.class, Enoko.class, Okina.class));
				case 36:
				case 37:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Eirin.class, Doremy.class, Sagume.class, Sumireko.class, Renko.class, Junko.class, Zanmu.class));
				case 38:
				case 39:
				case 40:
				case 41:
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Toyohime.class, Yorihime.class, Reimu.class, Utsuho.class, Yuuma.class, Eiki.class, Keiki.class));
			}
		}
	}
}