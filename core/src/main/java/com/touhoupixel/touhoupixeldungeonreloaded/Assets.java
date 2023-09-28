/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeonreloaded;

public class Assets {

	public static class Effects {
		public static final String EFFECTS = "effects/effects.png";
		public static final String FIREBALL = "effects/fireball.png";
		public static final String SPECKS = "effects/specks.png";
		public static final String SPELL_ICONS = "effects/spell_icons.png";
	}

	public static class Environment {
		public static final String TERRAIN_FEATURES = "environment/terrain_features.png";

		public static final String VISUAL_GRID = "environment/visual_grid.png";
		public static final String WALL_BLOCKING = "environment/wall_blocking.png";

		public static final String TILES_1 = "environment/tiles_1.png";
		public static final String TILES_2 = "environment/tiles_2.png";
		public static final String TILES_3 = "environment/tiles_3.png";
		public static final String TILES_4 = "environment/tiles_4.png";
		public static final String TILES_5 = "environment/tiles_5.png";
		public static final String TILES_6 = "environment/tiles_6.png";
		public static final String TILES_7 = "environment/tiles_7.png";
		public static final String TILES_8 = "environment/tiles_8.png";
		public static final String TILES_9 = "environment/tiles_9.png";
		public static final String TILES_10 = "environment/tiles_10.png";
		public static final String TILES_11 = "environment/tiles_11.png";
		public static final String TILES_12 = "environment/tiles_12.png";
		public static final String TILES_13 = "environment/tiles_13.png";
		public static final String TILES_14 = "environment/tiles_14.png";
		public static final String TILES_15 = "environment/tiles_15.png";
		public static final String TILES_16 = "environment/tiles_16.png";
		public static final String TILES_17 = "environment/tiles_17.png";
		public static final String TILES_18 = "environment/tiles_18.png";
		public static final String TILES_19 = "environment/tiles_19.png";
		public static final String TILES_20 = "environment/tiles_20.png";

		public static final String WATER_1 = "environment/water1.png";
		public static final String WATER_2 = "environment/water2.png";
		public static final String WATER_3 = "environment/water3.png";
		public static final String WATER_4 = "environment/water4.png";
		public static final String WATER_5 = "environment/water5.png";
		public static final String WATER_6 = "environment/water6.png";
		public static final String WATER_7 = "environment/water7.png";
		public static final String WATER_8 = "environment/water8.png";
		public static final String WATER_9 = "environment/water9.png";
		public static final String WATER_10 = "environment/water10.png";
		public static final String WATER_11 = "environment/water11.png";
		public static final String WATER_12 = "environment/water12.png";
		public static final String WATER_13 = "environment/water13.png";
		public static final String WATER_14 = "environment/water14.png";
		public static final String WATER_15 = "environment/water15.png";
		public static final String WATER_16 = "environment/water16.png";
		public static final String WATER_17 = "environment/water17.png";
		public static final String WATER_18 = "environment/water18.png";
		public static final String WATER_19 = "environment/water19.png";
		public static final String WATER_20 = "environment/water20.png";

		public static final String WEAK_FLOOR = "environment/custom_tiles/weak_floor.png";
		public static final String PRISON_QUEST = "environment/custom_tiles/prison_quests.png";
		public static final String PRISON_EXIT = "environment/custom_tiles/prison_exit.png";
		public static final String CAVES_BOSS = "environment/custom_tiles/caves_boss.png";
		public static final String CITY_BOSS = "environment/custom_tiles/city_boss.png";
		public static final String HALLS_SP = "environment/custom_tiles/halls_special.png";
	}

	//TODO include other font assets here? Some are platform specific though...
	public static class Fonts {
		public static final String PIXELFONT = "fonts/pixel_font.png";
	}

	public static class Interfaces {
		public static final String ARCS_BG = "interfaces/arcs1.png";
		public static final String ARCS_FG = "interfaces/arcs2.png";

		public static final String BANNERS = "interfaces/banners.png";
		public static final String BADGES = "interfaces/badges.png";
		public static final String LOCKED = "interfaces/locked_badge.png";

		public static final String CHROME = "interfaces/chrome.png";
		public static final String ICONS = "interfaces/icons.png";
		public static final String STATUS = "interfaces/status_pane.png";
		public static final String MENU = "interfaces/menu_pane.png";
		public static final String MENU_BTN = "interfaces/menu_button.png";
		public static final String TOOLBAR = "interfaces/toolbar.png";
		public static final String SHADOW = "interfaces/shadow.png";
		public static final String BOSSHP = "interfaces/boss_hp.png";

		public static final String SURFACE = "interfaces/surface.png";

		public static final String LOADING_1 = "interfaces/loading_1.png";
		public static final String LOADING_2 = "interfaces/loading_2.png";
		public static final String LOADING_3 = "interfaces/loading_3.png";
		public static final String LOADING_4 = "interfaces/loading_4.png";
		public static final String LOADING_5 = "interfaces/loading_5.png";
		public static final String LOADING_6 = "interfaces/loading_6.png";
		public static final String LOADING_7 = "interfaces/loading_7.png";
		public static final String LOADING_8 = "interfaces/loading_8.png";
		public static final String LOADING_9 = "interfaces/loading_9.png";
		public static final String LOADING_10 = "interfaces/loading_10.png";

		public static final String BUFFS_SMALL = "interfaces/buffs.png";
		public static final String BUFFS_LARGE = "interfaces/large_buffs.png";

		public static final String RADIAL_MENU = "interfaces/radial_menu.png";
	}

	//these points to resource bundles, not raw asset files
	public static class Messages {
		public static final String ACTORS = "messages/actors/actors";
		public static final String ITEMS = "messages/items/items";
		public static final String JOURNAL = "messages/journal/journal";
		public static final String LEVELS = "messages/levels/levels";
		public static final String MISC = "messages/misc/misc";
		public static final String PLANTS = "messages/plants/plants";
		public static final String SCENES = "messages/scenes/scenes";
		public static final String UI = "messages/ui/ui";
		public static final String WINDOWS = "messages/windows/windows";
	}

	public static class Music {
		public static final String THEME_1 = "music/opening.ogg";
		public static final String THEME_2 = "music/ending.ogg";
		public static final String FLOOR_1 = "music/hakureishrine.ogg";
		public static final String BOSS_FLOOR_1 = "music/bosskosuzu.ogg";
		public static final String BOSS_FLOOR_2 = "music/bossmarisa.ogg";
		public static final String BOSS_FLOOR_3 = "music/bossremilia.ogg";
		public static final String BOSS_FLOOR_4 = "music/bosskasen.ogg";
		public static final String BOSS_FLOOR_5 = "music/bosskomachi.ogg";
		public static final String BOSS_FLOOR_6 = "music/bossseija.ogg";
		public static final String BOSS_FLOOR_7 = "music/bosstenshi.ogg";
		public static final String BOSS_FLOOR_8 = "music/bosshecatia.ogg";
		public static final String FLOOR_2 = "music/humanvillage.ogg";
		public static final String FLOOR_3 = "music/mistylake.ogg";
		public static final String FLOOR_4 = "music/forestofmagic.ogg";
		public static final String FLOOR_5 = "music/bambooforest.ogg";
		public static final String FLOOR_6 = "music/scarletdevilmansion.ogg";
		public static final String FLOOR_7 = "music/yokaimountain.ogg";
		public static final String FLOOR_8 = "music/gardenofthesun.ogg";
		public static final String FLOOR_9 = "music/myourentemple.ogg";
		public static final String FLOOR_10 = "music/sanzuriver.ogg";
		public static final String FLOOR_11 = "music/moriyashrine.ogg";
		public static final String FLOOR_12 = "music/shiningneedlecastle.ogg";
		public static final String FLOOR_13 = "music/rainbowdragoncave.ogg";
		public static final String FLOOR_14 = "music/landofthebackdoor.ogg";
		public static final String FLOOR_15 = "music/hakugyokurou.ogg";
		public static final String FLOOR_16 = "music/heaven.ogg";
		public static final String FLOOR_17 = "music/lunarcapital.ogg";
		public static final String FLOOR_18 = "music/primatespiritgarden.ogg";
		public static final String FLOOR_19 = "music/hell.ogg";
	}

	public static class Sounds {
		public static final String CLICK = "sounds/click.mp3";
		public static final String BADGE = "sounds/badge.mp3";
		public static final String GOLD = "sounds/gold.mp3";

		public static final String OPEN = "sounds/door_open.mp3";
		public static final String UNLOCK = "sounds/unlock.mp3";
		public static final String ITEM = "sounds/item.mp3";
		public static final String DEWDROP = "sounds/dewdrop.mp3";
		public static final String STEP = "sounds/step.mp3";
		public static final String WATER = "sounds/water.mp3";
		public static final String GRASS = "sounds/grass.mp3";
		public static final String TRAMPLE = "sounds/trample.mp3";
		public static final String STURDY = "sounds/sturdy.mp3";

		public static final String HIT = "sounds/hit.mp3";
		public static final String MISS = "sounds/miss.mp3";
		public static final String HIT_SLASH = "sounds/hit_slash.mp3";
		public static final String HIT_STAB = "sounds/hit_stab.mp3";
		public static final String HIT_CRUSH = "sounds/hit_crush.mp3";
		public static final String HIT_MAGIC = "sounds/hit_magic.mp3";
		public static final String HIT_STRONG = "sounds/hit_strong.mp3";
		public static final String HIT_PARRY = "sounds/hit_parry.mp3";
		public static final String HIT_ARROW = "sounds/hit_arrow.mp3";
		public static final String ATK_SPIRITBOW = "sounds/atk_spiritbow.mp3";
		public static final String ATK_CROSSBOW = "sounds/atk_crossbow.mp3";
		public static final String HEALTH_WARN = "sounds/health_warn.mp3";
		public static final String HEALTH_CRITICAL = "sounds/health_critical.mp3";

		public static final String DESCEND = "sounds/descend.mp3";
		public static final String EAT = "sounds/eat.mp3";
		public static final String READ = "sounds/read.mp3";
		public static final String LULLABY = "sounds/lullaby.mp3";
		public static final String DRINK = "sounds/drink.mp3";
		public static final String SHATTER = "sounds/shatter.mp3";
		public static final String ZAP = "sounds/zap.mp3";
		public static final String LIGHTNING = "sounds/lightning.mp3";
		public static final String LEVELUP = "sounds/levelup.mp3";
		public static final String DEATH = "sounds/death.mp3";
		public static final String CHALLENGE = "sounds/challenge.mp3";
		public static final String CURSED = "sounds/cursed.mp3";
		public static final String TRAP = "sounds/trap.mp3";
		public static final String EVOKE = "sounds/evoke.mp3";
		public static final String TOMB = "sounds/tomb.mp3";
		public static final String ALERT = "sounds/alert.mp3";
		public static final String MELD = "sounds/meld.mp3";
		public static final String BOSS = "sounds/boss.mp3";
		public static final String BLAST = "sounds/blast.mp3";
		public static final String PLANT = "sounds/plant.mp3";
		public static final String RAY = "sounds/ray.mp3";
		public static final String BEACON = "sounds/beacon.mp3";
		public static final String TELEPORT = "sounds/teleport.mp3";
		public static final String CHARMS = "sounds/charms.mp3";
		public static final String MASTERY = "sounds/mastery.mp3";
		public static final String PUFF = "sounds/puff.mp3";
		public static final String ROCKS = "sounds/rocks.mp3";
		public static final String BURNING = "sounds/burning.mp3";
		public static final String FALLING = "sounds/falling.mp3";
		public static final String GHOST = "sounds/ghost.mp3";
		public static final String SECRET = "sounds/secret.mp3";
		public static final String BONES = "sounds/bones.mp3";
		public static final String BEE = "sounds/bee.mp3";
		public static final String DEGRADE = "sounds/degrade.mp3";
		public static final String MIMIC = "sounds/mimic.mp3";
		public static final String DEBUFF = "sounds/debuff.mp3";
		public static final String CHARGEUP = "sounds/chargeup.mp3";
		public static final String GAS = "sounds/gas.mp3";
		public static final String CHAINS = "sounds/chains.mp3";
		public static final String SCAN = "sounds/scan.mp3";
		public static final String SHEEP = "sounds/sheep.mp3";

		public static final String MASTER_SPARK_RAY = "sounds/master_spark_ray.mp3";

		public static final String[] all = new String[]{
				CLICK, BADGE, GOLD,

				OPEN, UNLOCK, ITEM, DEWDROP, STEP, WATER, GRASS, TRAMPLE, STURDY,

				HIT, MISS, HIT_SLASH, HIT_STAB, HIT_CRUSH, HIT_MAGIC, HIT_STRONG, HIT_PARRY,
				HIT_ARROW, ATK_SPIRITBOW, ATK_CROSSBOW, HEALTH_WARN, HEALTH_CRITICAL,

				DESCEND, EAT, READ, LULLABY, DRINK, SHATTER, ZAP, LIGHTNING, LEVELUP, DEATH,
				CHALLENGE, CURSED, TRAP, EVOKE, TOMB, ALERT, MELD, BOSS, BLAST, PLANT, RAY, BEACON,
				TELEPORT, CHARMS, MASTERY, PUFF, ROCKS, BURNING, FALLING, GHOST, SECRET, BONES,
				BEE, DEGRADE, MIMIC, DEBUFF, CHARGEUP, GAS, CHAINS, SCAN, SHEEP, MASTER_SPARK_RAY
		};
	}

	public static class Splashes {
		public static final String PLAYERREIMU = "splashes/playerreimu.jpg";
		public static final String PLAYERMARISA = "splashes/playermarisa.jpg";
		public static final String PLAYERSANAE = "splashes/playersanae.jpg";
		public static final String PLAYERYOUMU = "splashes/playeryoumu.jpg";
		public static final String PLAYERSAKUYA = "splashes/playersakuya.jpg";
	}

	public static class Sprites {
		public static final String ITEMS = "sprites/items.png";
		public static final String ITEM_ICONS = "sprites/item_icons.png";

		public static final String PLAYERREIMU = "sprites/players/playerreimu.png";
		public static final String PLAYERMARISA = "sprites/players/playermarisa.png";
		public static final String PLAYERSANAE = "sprites/players/playersanae.png";
		public static final String PLAYERYOUMU = "sprites/players/playeryoumu.png";
		public static final String PLAYERSAKUYA = "sprites/players/playersakuya.png";
		public static final String AVATARS = "sprites/avatars.png";
		public static final String PET = "sprites/pet.png";
		public static final String AMULET = "sprites/itemamulet.png";

		public static final String YORIHIME = "sprites/yorihime.png";
		public static final String TOYOHIME = "sprites/toyohime.png";
		public static final String BENBEN = "sprites/benben.png";
		public static final String YATSUHASHI = "sprites/yatsuhashi.png";
		public static final String YUUMA = "sprites/yuuma.png";
		public static final String YUYUKO = "sprites/yuyuko.png";
		public static final String KOSUZU = "sprites/kosuzu.png";
		public static final String JOON = "sprites/joon.png";
		public static final String SHION = "sprites/shion.png";
		public static final String PARSEE = "sprites/parsee.png";
		public static final String TSUKASA = "sprites/tsukasa.png";
		public static final String HECATIA = "sprites/hecatia.png";
		public static final String SUWAKO = "sprites/suwako.png";
		public static final String MISUMARU = "sprites/misumaru.png";
		public static final String MINORIKO = "sprites/minoriko.png";
		public static final String YACHIE = "sprites/yachie.png";
		public static final String CHIMATA = "sprites/chimata.png";
		public static final String EIKA = "sprites/eika.png";
		public static final String MOKOU = "sprites/mokou.png";
		public static final String NAZRIN = "sprites/nazrin.png";
		public static final String RUMIA = "sprites/rumia.png";
		public static final String NITORI = "sprites/nitori.png";
		public static final String TAKANE = "sprites/takane.png";
		public static final String EIKI = "sprites/eiki.png";
		public static final String SEIJA = "sprites/seija.png";
		public static final String KOAKUMA = "sprites/koakuma.png";
		public static final String SUMIREKO = "sprites/sumireko.png";
		public static final String DOREMY = "sprites/doremy.png";
		public static final String YUUKA = "sprites/yuuka.png";
		public static final String KAGUYA = "sprites/kaguya.png";
		public static final String BIRUKO = "sprites/biruko.png";
		public static final String SEKIBANKI = "sprites/sekibanki.png";
		public static final String FUTO = "sprites/futo.png";
		public static final String SEIGA = "sprites/seiga.png";
		public static final String CHEN = "sprites/chen.png";
		public static final String SANNYO = "sprites/sannyo.png";
		public static final String HITORI = "sprites/hitori.png";
		public static final String OKINA = "sprites/okina.png";
		public static final String KEINE = "sprites/keine.png";
		public static final String SHIZUHA = "sprites/shizuha.png";
		public static final String SATORI = "sprites/satori.png";
		public static final String MAYUMI = "sprites/mayumi.png";
		public static final String KEIKI = "sprites/keiki.png";
		public static final String RENKO = "sprites/renko.png";
		public static final String REIMU = "sprites/reimu.png";
		public static final String SANAE = "sprites/sanae.png";
		public static final String KOMACHI = "sprites/komachi.png";
		public static final String SEIRAN = "sprites/seiran.png";
		public static final String IKU = "sprites/iku.png";
		public static final String AYA = "sprites/aya.png";
		public static final String SHINMYOMARU = "sprites/shinmyomaru.png";
		public static final String KAGEROU = "sprites/kagerou.png";
		public static final String YUKARI = "sprites/yukari.png";
		public static final String UTSUHO = "sprites/utsuho.png";
		public static final String MOMOYO = "sprites/momoyo.png";
		public static final String MOMIJI = "sprites/momiji.png";
		public static final String HATATE = "sprites/hatate.png";
		public static final String HINA = "sprites/hina.png";
		public static final String ICHIRIN = "sprites/ichirin.png";
		public static final String SAKUYA = "sprites/sakuya.png";
		public static final String SAKUYADAGGER = "sprites/sakuyadagger.png";
		public static final String SHOU = "sprites/shou.png";
		public static final String WRIGGLE = "sprites/wriggle.png";
		public static final String LARVA = "sprites/larva.png";
		public static final String YAMAME = "sprites/yamame.png";
		public static final String TENSHIBOSS = "sprites/tenshiboss.png";
		public static final String WRAITH = "sprites/wraith.png";
		public static final String MURASA = "sprites/murasa.png";
		public static final String JUNKO = "sprites/junko.png";
		public static final String KISUME = "sprites/kisume.png";
		public static final String MAI = "sprites/mai.png";
		public static final String SATONO = "sprites/satono.png";
		public static final String MEGUMU = "sprites/megumu.png";
		public static final String RINGO = "sprites/ringo.png";
		public static final String CIRNO = "sprites/cirno.png";
		public static final String ALICE = "sprites/alice.png";
		public static final String MARISA = "sprites/marisa.png";
		public static final String BOSSMARISA = "sprites/bossmarisa.png";
		public static final String SUIKA = "sprites/suika.png";
		public static final String SHEEP = "sprites/sheep.png";
		public static final String KEEPER = "sprites/shopkeeper.png";
		public static final String RAN = "sprites/ran.png";
		public static final String KASEN = "sprites/kasen.png";
		public static final String STATUE = "sprites/statue.png";
		public static final String REISEN = "sprites/reisen.png";
		public static final String SAGUME = "sprites/sagume.png";
		public static final String MAKER = "sprites/wandmaker.png";
		public static final String TEWI = "sprites/tewi.png";
		public static final String SHOPKEEPERTEWI = "sprites/shopkeepertewi.png";
		public static final String MEDICINE = "sprites/medicine.png";
		public static final String MIMIC = "sprites/mimic.png";
		public static final String MEILING = "sprites/meiling.png";
		public static final String PATCHOULI = "sprites/patchouli.png";
		public static final String ROT_LASH = "sprites/rot_lasher.png";
		public static final String ROT_HEART = "sprites/rot_heart.png";
		public static final String YUUGI = "sprites/yuugi.png";
		public static final String WARDS = "sprites/wards.png";
		public static final String GUARDIAN = "sprites/guardian.png";
		public static final String SAKI = "sprites/saki.png";
		public static final String MYSTIA = "sprites/mystia.png";
		public static final String REMILIA = "sprites/remilia.png";
		public static final String FLANDRE = "sprites/flandre.png";
		public static final String YOUMU = "sprites/youmu.png";
		public static final String TOJIKO = "sprites/tojiko.png";
		public static final String MAMIZOU = "sprites/mamizou.png";
		public static final String NUE = "sprites/nue.png";
		public static final String KANAKO = "sprites/kanako.png";
		public static final String AKYUU = "sprites/akyuu.png";
		public static final String KUTAKA = "sprites/kutaka.png";
		public static final String KYOUKO = "sprites/kyouko.png";
		public static final String KOGASA = "sprites/kogasa.png";
		public static final String TENSHI = "sprites/tenshi.png";
		public static final String CLOWNPIECE = "sprites/clownpiece.png";
		public static final String EIRIN = "sprites/eirin.png";
		public static final String SUNNY = "sprites/sunny.png";
		public static final String LUNA = "sprites/luna.png";
		public static final String STAR = "sprites/star.png";
		public static final String KOISHI = "sprites/koishi.png";
		public static final String KOKORO = "sprites/kokoro.png";
		public static final String HIJIRI = "sprites/hijiri.png";
		public static final String LILY = "sprites/lily.png";
		public static final String MIYOI = "sprites/miyoi.png";
		public static final String MIKE = "sprites/mike.png";
		public static final String MIKO = "sprites/miko.png";
		public static final String AUNN = "sprites/aunn.png";
		public static final String NEMUNO = "sprites/nemuno.png";
		public static final String HEARN = "sprites/hearn.png";
		public static final String BITEN = "sprites/biten.png";
		public static final String CHIYARI = "sprites/chiyari.png";
		public static final String ENOKO = "sprites/enoko.png";
		public static final String HISAMI = "sprites/hisami.png";
		public static final String ZANMU = "sprites/zanmu.png";
		public static final String RAIKO = "sprites/raiko.png";
		public static final String DESTORB = "sprites/destorb.png";

		public static final String ZUN = "sprites/zun.png";
		public static final String ZUN_LUST = "sprites/zunlust.png";
		public static final String ZUN_VANITY = "sprites/zunvanity.png";
		public static final String ZUN_GLUTTONY = "sprites/zungluttony.png";
		public static final String ZUN_WRATH = "sprites/zunwrath.png";
		public static final String ZUN_AVARICE = "sprites/zunavarice.png";
		public static final String ZUN_ENVY = "sprites/zunenvy.png";
		public static final String ZUN_PRIDE = "sprites/zunpride.png";
		public static final String MITAMA_ARA = "sprites/mitamaara.png";
		public static final String MITAMA_KUSI = "sprites/mitamakusi.png";
		public static final String MITAMA_NIGI = "sprites/mitamanigi.png";
		public static final String MITAMA_SAKI = "sprites/mitamasaki.png";
		public static final String LOTUS = "sprites/lotus.png";
		public static final String RED_SENTRY = "sprites/red_sentry.png";
	}
}