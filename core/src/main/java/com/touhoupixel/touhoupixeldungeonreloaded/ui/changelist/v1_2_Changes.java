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
		add_v1_0_5_Changes(changeInfos);
		add_v1_0_1_Changes(changeInfos);
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