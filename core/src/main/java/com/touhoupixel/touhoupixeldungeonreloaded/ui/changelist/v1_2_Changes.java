/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeonreloaded.ui.changelist;

import com.touhoupixel.touhoupixeldungeonreloaded.ui.Icons;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Window;

import java.util.ArrayList;

public class v1_2_Changes {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_v1_1_4_Changes(changeInfos);
		add_v1_1_3_Changes(changeInfos);
		add_v1_1_2_Changes(changeInfos);
		add_v1_1_1_Changes(changeInfos);
		add_v1_1_0_Changes(changeInfos);
		add_v1_0_9_Changes(changeInfos);
		add_v1_0_7_Changes(changeInfos);
		add_v1_0_5_Changes(changeInfos);
		add_v1_0_1_Changes(changeInfos);
	}

	public static void add_v1_1_4_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.1.4", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.4 changes",
				"_-_ THPD: reloaded can now prevent dismantling an item by mistake. It will do double-check.\n" +
						"_-_ Protagonists except reimu now have their special abilities.\n" +
						"_-_ Eiki's judgment damage has been nerfed again.\n" +
						"_-_ Raiko and mayumi have been added.\n" +
						"_-_ Yuuma is remaked, and added hearn to the gensokyo.\n" +
						"_-_ Sagume's minor bug is fixed.\n" +
						"_-_ Telekinetic grab's major bug is fixed.\n" +
						"_-_ Boss seija's penalty is nerfed.\n" +
						"_-_ Enemies' stats will be buffed.\n" +
						"_-_ Minor text improvements.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_3_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.1.3", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.3 changes",
				"_-_ Tewi's death damage is reduced by half.\n" +
						"_-_ Th19's five new enemies are added!\n" +
						"_-_ Boss hecatia now will kill all junko and keiki when defeated.\n" +
						"_-_ Fumo property is added for some enemies such as orin, sagume, etc.\n" +
						"_-_ Spellcard of anchor now creates water on ember tiles.\n" +
						"_-_ Nitori and ichirin's ranged attack power are reduced.\n" +
						"_-_ De-slaying and dismantle pressure's duration are reduced.\n" +
						"_-_ Remilia's fate is nerfed. Now deals damageroll's min damage*2 instead.\n" +
						"_-_ Spyglass price is reduced. Don't hesitate to investigate them all!\n" +
						"_-_ Two early-game melee weapons are added.\n" +
						"_-_ Cursing trap now appears on late-games.\n" +
						"_-_ Kanako's offering card is changed.\n" +
						"_-_ Boss floor bgms are added, and some normal floor bgms are changed a little.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_2_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.1.2", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.2 changes",
				"_-_ Mitama now appears in gensokyo! Throw a spyglass to kill it!\n" +
						"_-_ Tewi and seiga are changed.\n" +
						"_-_ Eiki's judgment damage has been nerfed.\n" +
						"_-_ New item, spyglass is added.\n" +
						"_-_ Potion of enlightenment is heavily nerfed.\n" +
						"_-_ Targeted spellcards are now always say 'choose an item' and don't tell what the spellcard is.\n" +
						"_-_ Spellcard of anchor is now more dangerous.\n" +
						"_-_ Stats checker now shows life/bomb/dismantle used count.\n" +
						"_-_ Keiki's creation and suika's gourd card are changed.\n" +
						"_-_ New debuff, randomizer is added.\n" +
						"_-_ Late game enemies' evasion are nerfed.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_1_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.1.1", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.1 changes",
				"_-_ Play store version is now ABLE to run! Sorry for long waiting!\n" +
						"_-_ Two melee weapons are added.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_0_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.1.0", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.0 changes",
				"_-_ Fallen items now move to down floor.(bug fix)\n" +
						"_-_ Strength cards and upgrade cards now appear on every non-boss floor.\n" +
						"_-_ Equipments can now be thrown and deal damage, like shiren series.\n" +
						"_-_ Preparations for 1.2.0 TLB update are working.(Distorted 7 debuffs and more)\n" +
						"_-_ New artifact, kaguya's hyperdimentional chest is added.\n" +
						"_-_ Various buffs and debuffs are added in the game.\n" +
						"_-_ Some spellcards(anchor, fate, earth, fixer, and n.s.) were added.\n" +
						"_-_ Charge herb now replaces nocturnal herb.\n" +
						"_-_ Inaccurate talisman now replaces defog talisman.\n" +
						"_-_ Many armors(tier 5 armors) were added.\n" +
						"_-_ Difficulty now can be changed via ominous gaps in your inventory.\n" +
						"_-_ Elixir of boss killer now replaces elixir of quad damage.\n" +
						"_-_ Many mobs are changed into, more likely shiren monsters. Abyss dragon is here.\n" +
						"_-_ Warning: Saves prior to version 1.1.0 will not be compatible properly! If there is such a save, please delete it.\n" +
						"_-_ Major text improvements.\n" +
						"_-_ Other things were changed but hard to write since there are too many.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_0_9_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.0.9", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.0.9 changes",
				"_-_ 6 special tiles were completely removed from the game.\n" +
						"_-_ Homunculus and 5 vial items were added.\n" +
						"_-_ Potions and spellcards are changed.\n" +
						"_-_ Some weapons are added.\n" +
						"_-_ Some ability cards are changed.\n" +
						"_-_ Nito dismantle hammer is now located at dismantle command.\n" +
						"_-_ New difficulty, gensokyo extinction is added.\n" +
						"_-_ Total floors are reduced to 40f. Because 50f was too long.\n" +
						"_-_ New buff, brainwash, is added to a certain ability card.\n" +
						"_-_ Bestiary were changed a little.\n" +
						"_-_ Remilia boss and seija boss has slightly changed.\n" +
						"_-_ For some reason, tutorial was removed. Also tutorial in this game was meaningless.\n" +
						"_-_ Warning: Saves prior to version 1.0.9 will not be compatible properly! If there is such a save, please delete it.\n" +
						"_-_ Major text improvements.\n" +
						"_-_ Some bugs have been fixed.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_0_7_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.0.7", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.0.7 changes",
				"_-_ Player corruption feature is removed from the game.\n" +
						"_-_ Doremy, keine, megumu, sannyo, seiran, suwako, yatsuhashi, and yukari are reworked.\n" +
						"_-_ Damage output from enemies above hard difficulties have been nerfed again.(H: 1.15x->1.1x, O: 1.3x->1.2x)\n" +
						"_-_ Damage output from enemies for easy difficulty have been buffed.(0.8x->0.85x)\n" +
						"_-_ Purity herb, sagume's wing, underground sun card, and item season card are reworked.\n" +
						"_-_ Girls blossom project challenge, and cursing trap has been nerfed.\n" +
						"_-_ Potion of enlightenment is reworked, for hakkero users.\n" +
						"_-_ Herb price in shops are reduced.\n" +
						"_-_ Removed spellcard of corrupting reduction, and replaced with spellcard of maiden.\n" +
						"_-_ Fixed danmaku texts that has written the wrong infos.\n" +
						"_-_ Burning traps now don't appear on floor 1~4.\n" +
						"_-_ Added two new badges, no torch badge and learned your lesson badge. \n" +
						"_-_ Added a weapon which is toramaru's spear, can you reach 100 extra defense for that weapon? \n" +
						"_-_ Minor text improvements.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_0_5_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.0.5", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.0.5 changes",
				"_-_ Square root snipe debuff is changed to hina's curse.\n" +
						"_-_ Spellcard of heavenly duel is nerfed.\n" +
						"_-_ Added new challenge, girls blossom project that replaces 2nd challenge.\n" +
						"_-_ Reincarnation apple challenge is nerfed.\n" +
						"_-_ Two melee weapon, akyuu's brush and hecatia's star are added.\n" +
						"_-_ Fixed an issue that stone of madness can instakill a boss.\n" +
						"_-_ Flandre and hitori now have miniboss property, which means now unable to corrupt them.\n" +
						"_-_ Damage output from enemies above hard difficulties have been nerfed.\n" +
						"_-_ Minor text improvements.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_0_1_Changes(ArrayList<ChangeInfo> changeInfos ){
		ChangeInfo changes = new ChangeInfo("v1.0.1", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton( Icons.get(Icons.CHANGES), "v1.0.1 changes",
				"_-_ Initial release\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}
}