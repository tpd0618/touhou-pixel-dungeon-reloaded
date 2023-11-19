package com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs;

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
						Hecatia.class, Benben.class, Yatsuhashi.class, Shinmyomaru.class, Seija.class, Mai.class, Satono.class, Okina.class, Chiyari.class));
			case 31:
			case 32:
				return new ArrayList<>(Arrays.asList(
						Hecatia.class, Yamame.class, Kaguya.class, Ran.class, Youmu.class, Yuyuko.class, Satori.class, Koishi.class, Biten.class));
			case 33:
			case 34:
			case 35:
				return new ArrayList<>(Arrays.asList(
						Hecatia.class, Yuugi.class, Suika.class, Clownpiece.class, Chimata.class, Rumia.class, Iku.class, Tenshi.class, Enoko.class));
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