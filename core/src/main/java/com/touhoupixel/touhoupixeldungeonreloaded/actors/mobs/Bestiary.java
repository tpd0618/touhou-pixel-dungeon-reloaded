package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
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
		switch (floor) {
			case 1:
			case 2:
			default:
				if (Statistics.extermination_number == 1) {
					return new ArrayList<>(Arrays.asList(
							Nazrin.class, Mike.class, Larva.class, Kogasa.class));
				} else if (Statistics.extermination_number == 2) {
					return new ArrayList<>(Arrays.asList(
							Wriggle.class, Mike.class, Larva.class, Kogasa.class));
				} else if (Statistics.extermination_number == 3) {
					return new ArrayList<>(Arrays.asList(
							Wriggle.class, Nazrin.class, Larva.class, Kogasa.class));
				} else if (Statistics.extermination_number == 4) {
					return new ArrayList<>(Arrays.asList(
							Wriggle.class, Nazrin.class, Mike.class, Kogasa.class));
				} else if (Statistics.extermination_number == 5) {
					return new ArrayList<>(Arrays.asList(
							Wriggle.class, Nazrin.class, Mike.class, Larva.class));
				} else return new ArrayList<>(Arrays.asList(
						Wriggle.class, Nazrin.class, Mike.class, Larva.class, Kogasa.class));
			case 3:
			case 4:
			case 5:
				if (Statistics.extermination_number == 6) {
					return new ArrayList<>(Arrays.asList(
							Ringo.class, Sunny.class, Akyuu.class, Kosuzu.class));
				} else if (Statistics.extermination_number == 7) {
					return new ArrayList<>(Arrays.asList(
							Kokoro.class, Sunny.class, Akyuu.class, Kosuzu.class));
				} else if (Statistics.extermination_number == 8) {
					return new ArrayList<>(Arrays.asList(
							Kokoro.class, Ringo.class, Akyuu.class, Kosuzu.class));
				} else if (Statistics.extermination_number == 9) {
					return new ArrayList<>(Arrays.asList(
							Kokoro.class, Ringo.class, Sunny.class, Kosuzu.class));
				} else if (Statistics.extermination_number == 10) {
					return new ArrayList<>(Arrays.asList(
							Kokoro.class, Ringo.class, Sunny.class, Akyuu.class));
				} else return new ArrayList<>(Arrays.asList(
						Kokoro.class, Ringo.class, Sunny.class, Akyuu.class, Kosuzu.class));
			case 6:
			case 7:
				if (Statistics.extermination_number == 11) {
					return new ArrayList<>(Arrays.asList(
							Miyoi.class, Aunn.class, Luna.class, Lily.class, Cirno.class));
				} else if (Statistics.extermination_number == 12) {
					return new ArrayList<>(Arrays.asList(
							Parsee.class, Aunn.class, Luna.class, Lily.class, Cirno.class));
				} else if (Statistics.extermination_number == 13) {
					return new ArrayList<>(Arrays.asList(
							Parsee.class, Miyoi.class, Luna.class, Lily.class, Cirno.class));
				} else if (Statistics.extermination_number == 14) {
					return new ArrayList<>(Arrays.asList(
							Parsee.class, Miyoi.class, Aunn.class, Lily.class, Cirno.class));
				} else if (Statistics.extermination_number == 15) {
					return new ArrayList<>(Arrays.asList(
							Parsee.class, Miyoi.class, Aunn.class, Luna.class, Cirno.class));
				} else if (Statistics.extermination_number == 16) {
					return new ArrayList<>(Arrays.asList(
							Parsee.class, Miyoi.class, Aunn.class, Luna.class, Lily.class));
				} else return new ArrayList<>(Arrays.asList(
						Parsee.class, Miyoi.class, Aunn.class, Luna.class, Lily.class, Cirno.class));
			case 8:
			case 9:
			case 10:
				if (Statistics.extermination_number == 17) {
					return new ArrayList<>(Arrays.asList(
							Mystia.class, Chen.class, Star.class, Eika.class, Marisa.class));
				} else if (Statistics.extermination_number == 18) {
					return new ArrayList<>(Arrays.asList(
							Alice.class, Chen.class, Star.class, Eika.class, Marisa.class));
				} else if (Statistics.extermination_number == 19) {
					return new ArrayList<>(Arrays.asList(
							Alice.class, Mystia.class, Star.class, Eika.class, Marisa.class));
				} else if (Statistics.extermination_number == 20) {
					return new ArrayList<>(Arrays.asList(
							Alice.class, Mystia.class, Chen.class, Eika.class, Marisa.class));
				} else if (Statistics.extermination_number == 21) {
					return new ArrayList<>(Arrays.asList(
							Alice.class, Mystia.class, Chen.class, Star.class, Marisa.class));
				} else if (Statistics.extermination_number == 22) {
					return new ArrayList<>(Arrays.asList(
							Alice.class, Mystia.class, Chen.class, Star.class, Eika.class));
				} else return new ArrayList<>(Arrays.asList(
						Alice.class, Mystia.class, Chen.class, Star.class, Eika.class, Marisa.class));
			case 11:
			case 12:
				if (Statistics.extermination_number == 23) {
					return new ArrayList<>(Arrays.asList(
							Tewi.class, Keine.class, Mokou.class, Kagerou.class, Mamizou.class));
				} else if (Statistics.extermination_number == 24) {
					return new ArrayList<>(Arrays.asList(
							Reisen.class, Keine.class, Mokou.class, Kagerou.class, Mamizou.class));
				} else if (Statistics.extermination_number == 25) {
					return new ArrayList<>(Arrays.asList(
							Reisen.class, Tewi.class, Mokou.class, Kagerou.class, Mamizou.class));
				} else if (Statistics.extermination_number == 26) {
					return new ArrayList<>(Arrays.asList(
							Reisen.class, Tewi.class, Keine.class, Kagerou.class, Mamizou.class));
				} else if (Statistics.extermination_number == 27) {
					return new ArrayList<>(Arrays.asList(
							Reisen.class, Tewi.class, Keine.class, Mokou.class, Mamizou.class));
				} else if (Statistics.extermination_number == 28) {
					return new ArrayList<>(Arrays.asList(
							Reisen.class, Tewi.class, Keine.class, Mokou.class, Kagerou.class));
				} else return new ArrayList<>(Arrays.asList(
						Reisen.class, Tewi.class, Keine.class, Mokou.class, Kagerou.class, Mamizou.class));
			case 13:
			case 14:
			case 15:
				if (Statistics.extermination_number == 29) {
					return new ArrayList<>(Arrays.asList(
							Koakuma.class, Meiling.class, Patchouli.class, Sakuya.class, Remilia.class));
				} else if (Statistics.extermination_number == 30) {
					return new ArrayList<>(Arrays.asList(
							Misumaru.class, Meiling.class, Patchouli.class, Sakuya.class, Remilia.class));
				} else if (Statistics.extermination_number == 31) {
					return new ArrayList<>(Arrays.asList(
							Misumaru.class, Koakuma.class, Patchouli.class, Sakuya.class, Remilia.class));
				} else if (Statistics.extermination_number == 32) {
					return new ArrayList<>(Arrays.asList(
							Misumaru.class, Koakuma.class, Meiling.class, Sakuya.class, Remilia.class));
				} else if (Statistics.extermination_number == 33) {
					return new ArrayList<>(Arrays.asList(
							Misumaru.class, Koakuma.class, Meiling.class, Patchouli.class, Remilia.class));
				} else if (Statistics.extermination_number == 34) {
					return new ArrayList<>(Arrays.asList(
							Misumaru.class, Koakuma.class, Meiling.class, Patchouli.class, Sakuya.class));
				} else return new ArrayList<>(Arrays.asList(
						Misumaru.class, Koakuma.class, Meiling.class, Patchouli.class, Sakuya.class, Remilia.class));
			case 16:
			case 17:
				if (Statistics.extermination_number == 35) {
					return new ArrayList<>(Arrays.asList(
							Nitori.class, Takane.class, Momiji.class, Hatate.class, Aya.class));
				} else if (Statistics.extermination_number == 36) {
					return new ArrayList<>(Arrays.asList(
							Sekibanki.class, Takane.class, Momiji.class, Hatate.class, Aya.class));
				} else if (Statistics.extermination_number == 37) {
					return new ArrayList<>(Arrays.asList(
							Sekibanki.class, Nitori.class, Momiji.class, Hatate.class, Aya.class));
				} else if (Statistics.extermination_number == 38) {
					return new ArrayList<>(Arrays.asList(
							Sekibanki.class, Nitori.class, Takane.class, Hatate.class, Aya.class));
				} else if (Statistics.extermination_number == 39) {
					return new ArrayList<>(Arrays.asList(
							Sekibanki.class, Nitori.class, Takane.class, Momiji.class, Aya.class));
				} else if (Statistics.extermination_number == 40) {
					return new ArrayList<>(Arrays.asList(
							Sekibanki.class, Nitori.class, Takane.class, Momiji.class, Hatate.class));
				} else return new ArrayList<>(Arrays.asList(
						Sekibanki.class, Nitori.class, Takane.class, Momiji.class, Hatate.class, Aya.class));
			case 18:
			case 19:
			case 20:
				if (Statistics.extermination_number == 41) {
					return new ArrayList<>(Arrays.asList(
							Yuuka.class, Nemuno.class, Tsukasa.class, Joon.class, Shion.class, Kasen.class, Mayumi.class));
				} else if (Statistics.extermination_number == 42) {
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Nemuno.class, Tsukasa.class, Joon.class, Shion.class, Kasen.class, Mayumi.class));
				} else if (Statistics.extermination_number == 43) {
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Yuuka.class, Tsukasa.class, Joon.class, Shion.class, Kasen.class, Mayumi.class));
				} else if (Statistics.extermination_number == 44) {
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Yuuka.class, Nemuno.class, Joon.class, Shion.class, Kasen.class, Mayumi.class));
				} else if (Statistics.extermination_number == 45) {
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Yuuka.class, Nemuno.class, Tsukasa.class, Shion.class, Kasen.class, Mayumi.class));
				} else if (Statistics.extermination_number == 46) {
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Yuuka.class, Nemuno.class, Tsukasa.class, Joon.class, Kasen.class, Mayumi.class));
				} else if (Statistics.extermination_number == 47) {
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Yuuka.class, Nemuno.class, Tsukasa.class, Joon.class, Shion.class, Mayumi.class));
				} else if (Statistics.extermination_number == 112) {
					return new ArrayList<>(Arrays.asList(
							Seiga.class, Yuuka.class, Nemuno.class, Tsukasa.class, Joon.class, Shion.class, Kasen.class));
				} else return new ArrayList<>(Arrays.asList(
						Seiga.class, Yuuka.class, Nemuno.class, Tsukasa.class, Joon.class, Shion.class, Kasen.class, Mayumi.class));
			case 21:
			case 22:
				if (Statistics.extermination_number == 48) {
					return new ArrayList<>(Arrays.asList(
							Kyouko.class, Ichirin.class, Momoyo.class, Shou.class, Miko.class, Hijiri.class, Raiko.class));
				} else if (Statistics.extermination_number == 49) {
					return new ArrayList<>(Arrays.asList(
							Futo.class, Ichirin.class, Momoyo.class, Shou.class, Miko.class, Hijiri.class, Raiko.class));
				} else if (Statistics.extermination_number == 50) {
					return new ArrayList<>(Arrays.asList(
							Futo.class, Kyouko.class, Momoyo.class, Shou.class, Miko.class, Hijiri.class, Raiko.class));
				} else if (Statistics.extermination_number == 51) {
					return new ArrayList<>(Arrays.asList(
							Futo.class, Kyouko.class, Ichirin.class, Shou.class, Miko.class, Hijiri.class, Raiko.class));
				} else if (Statistics.extermination_number == 52) {
					return new ArrayList<>(Arrays.asList(
							Futo.class, Kyouko.class, Ichirin.class, Momoyo.class, Miko.class, Hijiri.class, Raiko.class));
				} else if (Statistics.extermination_number == 53) {
					return new ArrayList<>(Arrays.asList(
							Futo.class, Kyouko.class, Ichirin.class, Momoyo.class, Shou.class, Hijiri.class, Raiko.class));
				} else if (Statistics.extermination_number == 54) {
					return new ArrayList<>(Arrays.asList(
							Futo.class, Kyouko.class, Ichirin.class, Momoyo.class, Shou.class, Miko.class, Raiko.class));
				} else if (Statistics.extermination_number == 111) {
					return new ArrayList<>(Arrays.asList(
							Futo.class, Kyouko.class, Ichirin.class, Momoyo.class, Shou.class, Miko.class, Hijiri.class));
				} else return new ArrayList<>(Arrays.asList(
						Futo.class, Kyouko.class, Ichirin.class, Momoyo.class, Shou.class, Miko.class, Hijiri.class, Raiko.class));
			case 23:
			case 24:
			case 25:
				if (Statistics.extermination_number == 55) {
					return new ArrayList<>(Arrays.asList(
							Kisume.class, Kutaka.class, Saki.class, Tojiko.class, Hina.class, Komachi.class, Hearn.class));
				} else if (Statistics.extermination_number == 56) {
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kutaka.class, Saki.class, Tojiko.class, Hina.class, Komachi.class, Hearn.class));
				} else if (Statistics.extermination_number == 57) {
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kisume.class, Saki.class, Tojiko.class, Hina.class, Komachi.class, Hearn.class));
				} else if (Statistics.extermination_number == 58) {
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kisume.class, Kutaka.class, Tojiko.class, Hina.class, Komachi.class, Hearn.class));
				} else if (Statistics.extermination_number == 59) {
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kisume.class, Kutaka.class, Saki.class, Hina.class, Komachi.class, Hearn.class));
				} else if (Statistics.extermination_number == 60) {
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kisume.class, Kutaka.class, Saki.class, Tojiko.class, Komachi.class, Hearn.class));
				} else if (Statistics.extermination_number == 61) {
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kisume.class, Kutaka.class, Saki.class, Tojiko.class, Hina.class, Hearn.class));
				} else if (Statistics.extermination_number == 110) {
					return new ArrayList<>(Arrays.asList(
							Biruko.class, Kisume.class, Kutaka.class, Saki.class, Tojiko.class, Hina.class, Komachi.class));
				} else return new ArrayList<>(Arrays.asList(
						Biruko.class, Kisume.class, Kutaka.class, Saki.class, Tojiko.class, Hina.class, Komachi.class, Hearn.class));
			case 26:
			case 27:
				if (Statistics.extermination_number == 62) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Minoriko.class, Sannyo.class, Megumu.class, Sanae.class, Suwako.class, Kanako.class, Hisami.class));
				} else if (Statistics.extermination_number == 63) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Sannyo.class, Megumu.class, Sanae.class, Suwako.class, Kanako.class, Hisami.class));
				} else if (Statistics.extermination_number == 64) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Minoriko.class, Megumu.class, Sanae.class, Suwako.class, Kanako.class, Hisami.class));
				} else if (Statistics.extermination_number == 65) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Minoriko.class, Sannyo.class, Sanae.class, Suwako.class, Kanako.class, Hisami.class));
				} else if (Statistics.extermination_number == 66) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Minoriko.class, Sannyo.class, Megumu.class, Suwako.class, Kanako.class, Hisami.class));
				} else if (Statistics.extermination_number == 67) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Minoriko.class, Sannyo.class, Megumu.class, Sanae.class, Kanako.class, Hisami.class));
				} else if (Statistics.extermination_number == 68) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Minoriko.class, Sannyo.class, Megumu.class, Sanae.class, Suwako.class, Hisami.class));
				} else if (Statistics.extermination_number == 108) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Shizuha.class, Minoriko.class, Sannyo.class, Megumu.class, Sanae.class, Suwako.class, Kanako.class));
				} else return new ArrayList<>(Arrays.asList(
						Hecatia.class, Shizuha.class, Minoriko.class, Sannyo.class, Megumu.class, Sanae.class, Suwako.class, Kanako.class, Hisami.class));
			case 28:
			case 29:
			case 30:
				if (Statistics.extermination_number == 69) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Mai.class, Satono.class, Okina.class, Chiyari.class));
				} else if (Statistics.extermination_number == 70) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Shinmyomaru.class, Seija.class, Mai.class, Satono.class, Okina.class, Chiyari.class));
				} else if (Statistics.extermination_number == 71) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Yatsuhashi.class, Seija.class, Mai.class, Satono.class, Okina.class, Chiyari.class));
				} else if (Statistics.extermination_number == 72) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Mai.class, Satono.class, Okina.class, Chiyari.class));
				} else if (Statistics.extermination_number == 73) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Satono.class, Okina.class, Chiyari.class));
				} else if (Statistics.extermination_number == 74) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Mai.class, Okina.class, Chiyari.class));
				} else if (Statistics.extermination_number == 75) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Mai.class, Satono.class, Chiyari.class));
				} else if (Statistics.extermination_number == 106) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Mai.class, Satono.class, Okina.class));
				} else return new ArrayList<>(Arrays.asList(
						Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Mai.class, Satono.class, Okina.class, Chiyari.class));
			case 31:
			case 32:
				if (Statistics.extermination_number == 76) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Kaguya.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class));
				} else if (Statistics.extermination_number == 77) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class));
				} else if (Statistics.extermination_number == 78) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Kaguya.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class));
				} else if (Statistics.extermination_number == 79) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class));
				} else if (Statistics.extermination_number == 80) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Youmu.class, Satori.class, Koishi.class, Biten.class));
				} else if (Statistics.extermination_number == 81) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Youmu.class, Yuyuko.class, Koishi.class, Biten.class));
				} else if (Statistics.extermination_number == 82) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Biten.class));
				} else if (Statistics.extermination_number == 105) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class));
				} else return new ArrayList<>(Arrays.asList(
						Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class));
			case 33:
			case 34:
			case 35:
				if (Statistics.extermination_number == 83) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Tenshi.class, Enoko.class));
				} else if (Statistics.extermination_number == 84) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Tenshi.class, Enoko.class));
				} else if (Statistics.extermination_number == 85) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Chimata.class, Rumia.class, Iku.class, Tenshi.class, Enoko.class));
				} else if (Statistics.extermination_number == 86) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Rumia.class, Iku.class, Tenshi.class, Enoko.class));
				} else if (Statistics.extermination_number == 87) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Iku.class, Tenshi.class, Enoko.class));
				} else if (Statistics.extermination_number == 88) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Tenshi.class, Enoko.class));
				} else if (Statistics.extermination_number == 89) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Enoko.class));
				} else if (Statistics.extermination_number == 107) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Tenshi.class));
				} else return new ArrayList<>(Arrays.asList(
						Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Tenshi.class, Enoko.class));
			case 36:
			case 37:
				if (Statistics.extermination_number == 90) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Eirin.class, Doremy.class, Sagume.class, Sumireko.class, Renko.class, Junko.class, Zanmu.class));
				} else if (Statistics.extermination_number == 91) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Doremy.class, Sagume.class, Sumireko.class, Renko.class, Junko.class, Zanmu.class));
				} else if (Statistics.extermination_number == 92) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Eirin.class, Sagume.class, Sumireko.class, Renko.class, Junko.class, Zanmu.class));
				} else if (Statistics.extermination_number == 93) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Eirin.class, Doremy.class, Sumireko.class, Renko.class, Junko.class, Zanmu.class));
				} else if (Statistics.extermination_number == 94) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Eirin.class, Doremy.class, Sagume.class, Renko.class, Junko.class, Zanmu.class));
				} else if (Statistics.extermination_number == 95) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Eirin.class, Doremy.class, Sagume.class, Sumireko.class, Junko.class, Zanmu.class));
				} else if (Statistics.extermination_number == 96) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Eirin.class, Doremy.class, Sagume.class, Sumireko.class, Renko.class, Zanmu.class));
				} else if (Statistics.extermination_number == 109) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Seiran.class, Eirin.class, Doremy.class, Sagume.class, Sumireko.class, Renko.class, Junko.class));
				} else return new ArrayList<>(Arrays.asList(
						Hecatia.class, Seiran.class, Eirin.class, Doremy.class, Sagume.class, Sumireko.class, Renko.class, Junko.class, Zanmu.class));
			case 38:
			case 39:
			case 40:
			case 41:
				if (Statistics.extermination_number == 97) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Toyohime.class, Yorihime.class, Reimu.class, Utsuho.class, Yuuma.class, Eiki.class, Keiki.class));
				} else if (Statistics.extermination_number == 98) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Yorihime.class, Reimu.class, Utsuho.class, Yuuma.class, Eiki.class, Keiki.class));
				} else if (Statistics.extermination_number == 99) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Toyohime.class, Reimu.class, Utsuho.class, Yuuma.class, Eiki.class, Keiki.class));
				} else if (Statistics.extermination_number == 100) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Toyohime.class, Yorihime.class, Utsuho.class, Yuuma.class, Eiki.class, Keiki.class));
				} else if (Statistics.extermination_number == 101) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Toyohime.class, Yorihime.class, Reimu.class, Yuuma.class, Eiki.class, Keiki.class));
				} else if (Statistics.extermination_number == 102) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Toyohime.class, Yorihime.class, Reimu.class, Utsuho.class, Eiki.class, Keiki.class));
				} else if (Statistics.extermination_number == 103) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Toyohime.class, Yorihime.class, Reimu.class, Utsuho.class, Yuuma.class, Keiki.class));
				} else if (Statistics.extermination_number == 104) {
					return new ArrayList<>(Arrays.asList(
							Hecatia.class, Yukari.class, Toyohime.class, Yorihime.class, Reimu.class, Utsuho.class, Yuuma.class, Eiki.class));
				} else return new ArrayList<>(Arrays.asList(
						Hecatia.class, Yukari.class, Toyohime.class, Yorihime.class, Reimu.class, Utsuho.class, Yuuma.class, Eiki.class, Keiki.class));
		}
	}
}