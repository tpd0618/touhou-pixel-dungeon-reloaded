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
		add_v1_0_9_Changes(changeInfos);
		add_v1_0_7_Changes(changeInfos);
		add_v1_0_5_Changes(changeInfos);
		add_v1_0_1_Changes(changeInfos);
	}

	public static void add_v1_0_9_Changes(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.0.9", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.0.9 changes",
				"_-_ 6 special tiles were completely removed from the game.\n" +
						"_-_ Homunculus and 5 vial items were added.\n" +
						"_-_ Potions and spellcards are massively changed.\n" +
						"_-_ Some weapons are added.\n" +
						"_-_ Some ability cards are changed.\n" +
						"_-_ Nito dismantle hammer is now located at dismantle command.\n" +
						"_-_ New difficulty, gensokyo extinction is added.\n" +
						"_-_ Total floors are reduced to 40f. Because 50f was too long.\n" +
						"_-_ Potions and spellcards are massively changed.\n" +
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