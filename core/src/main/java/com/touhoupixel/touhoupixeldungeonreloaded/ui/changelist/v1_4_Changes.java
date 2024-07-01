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

public class v1_4_Changes {

	public static void addAllChanges(ArrayList<ChangeInfo> changeInfos) {
		add_v1_2_3_Changes(changeInfos);
		add_v1_2_2_Changes(changeInfos);
		add_v1_2_1_Changes(changeInfos);
	}

	public static void add_v1_2_3_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.2.3", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.2.3 changes",
				"_-_ Half of them are bug fixed. For the other half, please wait.(Don't use radial quickslot in windows version!)\n" +
						"_-_ Tickets are removed.\n" +
						"_-_ Herbs are removed. Because they are too simple.\n" +
						"_-_ New five weapons are added.\n" +
						"_-_ Many non-touhou stuffs are removed. Because they are too many.\n" +
						"_-_ Bracelets now replace rings. Since rings are extremely powerful.\n" +
						"_-_ Inflicted onigiri on hero now shows an onigiri sprite.\n" +
						"_-_ Hina has been nerfed.\n" +
						"_-_ New challenge, death moon.\n" +
						"_-_ Any many other changes.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_2_2_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.2.2", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.2.2 changes",
				"_-_ Extra bug fixes.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}

	public static void add_v1_2_1_Changes(ArrayList<ChangeInfo> changeInfos) {
		ChangeInfo changes = new ChangeInfo("v1.2.1", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.CHANGES), "v1.2.1 changes",
				"_-_ Spellcards are now used in the ui button, not in the inventory.\n" +
						"_-_ Most enemies' defense are halved.\n" +
						"_-_ Upgrade cards that gives you in the start was reduced to 10.\n" +
						"_-_ Extra items are now guaranteed to spawn for normal floors.\n" +
						"_-_ Bug report: touhoupixeldungeon@gmail.com"));
	}
}