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

	public static void addAllChanges(ArrayList<ChangeInfo> changeInfos) {
		add_v1_1_8_Changes(changeInfos);
		add_v1_1_7_Changes(changeInfos);
		add_v1_1_6_Changes(changeInfos);
		add_v1_1_5_Changes(changeInfos);
		add_v1_1_4_Changes(changeInfos);
		add_v1_1_3_Changes(changeInfos);
		add_v1_1_2_Changes(changeInfos);
		add_v1_1_1_Changes(changeInfos);
		add_v1_1_0_Changes(changeInfos);
	}

	public static void add_v1_1_8_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.1.8", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.8 changes",
				"_-_ Flandre talisman and hourai talisman are removed from the game.\n" +
						"_-_ Dismantling now gives upgrade cards again, but you need to use it actively.\n" +
						"_-_ For above reason, enemies' stats are reworked.\n" +
						"_-_ Kanako, kutaka, tsukasa, and rumia are reworked.\n" +
						"_-_ Grim trap and decay trap are removed from the game.\n" +
						"_-_ New items, potion of superunlucky and spellcard of hatchet.\n" +
						"_-_ All inventory are extended by 5.\n" +
						"_-_ You now start the game with 30 upgrade cards.\n" +
						"_-_ Various bug fixes.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_7_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.1.7", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.7 changes",
				"_-_ Yuuma now absorbs positive buffs when close to heroine.\n" +
						"_-_ Blank card has been changed.\n" +
						"_-_ Decay trap and onigiri trap are added to the game.\n" +
						"_-_ Not only yuuma but also some enemies have been changed.\n" +
						"_-_ Backdoor talisman has been nerfed to deal 100 damage only.\n" +
						"_-_ New four talismans are added to the game.\n" +
						"_-_ Some melee weapons are changed.\n" +
						"_-_ Fixed an issue when the game crashes if item is thrown at chasm tiles.\n" +
						"_-_ Other misc things are updated.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_6_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.1.6", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.6 changes",
				"_-_ Hisou's damage bonus has been nerfed.\n" +
						"_-_ Futo now shows message that item is changed into heart herb.\n" +
						"_-_ Ring's max level has been nerfed.\n" +
						"_-_ Grayswandir and quintessential fan is nerfed.\n" +
						"_-_ New missile weapon, ego rock is added which deals 28 damage.\n" +
						"_-_ Hakkero now needs clear cube fragments and color cube fragments to fire.\n" +
						"_-_ Vials are removed.\n" +
						"_-_ Equipments discover rate is reduced a little.\n" +
						"_-_ Minor text improvements.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_5_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.1.5", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.5 changes",
				"_-_ Youmu's bomb crash bug fixed.\n" +
						"_-_ Enemy sakuya's ability has been changed.\n" +
						"_-_ New trap, storyway trap is added for late game.\n" +
						"_-_ Eika's bleeding damage has been increased.\n" +
						"_-_ Ran's ranged damage has been nerfed.\n" +
						"_-_ Some weak traps are removed from the game.\n" +
						"_-_ Minor text improvements.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_4_Changes(ArrayList<ChangeInfo> changeInfos) {
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

	public static void add_v1_1_3_Changes(ArrayList<ChangeInfo> changeInfos) {
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

	public static void add_v1_1_2_Changes(ArrayList<ChangeInfo> changeInfos) {
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

	public static void add_v1_1_1_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.1.1", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.1.1 changes",
				"_-_ Play store version is now ABLE to run! Sorry for long waiting!\n" +
						"_-_ Two melee weapons are added.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_1_0_Changes(ArrayList<ChangeInfo> changeInfos) {
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
						"_-_ Major text improvements.\n" +
						"_-_ Other things were changed but hard to write since there are too many.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}
}