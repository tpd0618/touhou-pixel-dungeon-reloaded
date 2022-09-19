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

package com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.connection;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.Room;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public abstract class ConnectionRoom extends Room {
	
	@Override
	public int minWidth() { return 3; }
	public int maxWidth() { return 10; }
	
	@Override
	public int minHeight() { return 3; }
	public int maxHeight() { return 10; }
	
	@Override
	public int minConnections(int direction) {
		if (direction == ALL)   return 2;
		else                    return 0;
	}
	
	//FIXME this is a very messy way of handing variable connection rooms
	private static ArrayList<Class<?extends ConnectionRoom>> rooms = new ArrayList<>();
	static {
		rooms.add(TunnelRoom.class);
		rooms.add(BridgeRoom.class);
		
		rooms.add(PerimeterRoom.class);
		rooms.add(WalkwayRoom.class);
		
		rooms.add(RingTunnelRoom.class);
		rooms.add(RingBridgeRoom.class);
	}
	
	private static float[][] chances = new float[105][];
	static {
		chances[1] =  new float[]{20, 1,    0, 2,       2, 1};
		chances[4] =  chances[3] = chances[2] = chances[1];
		chances[5] =  new float[]{20, 0,    0, 0,       0, 0};
		
		chances[6] =  new float[]{0, 0,     22, 3,      0, 0};
		chances[10] = chances[9] = chances[8] = chances[7] = chances[6];
		
		chances[11] = new float[]{12, 0,    0, 5,       5, 3};
		chances[15] = chances[14] = chances[13] = chances[12] = chances[11];
		
		chances[16] = new float[]{0, 0,     18, 3,      3, 1};
		chances[20] = chances[19] = chances[18] = chances[17] = chances[16];
		
		chances[21] = new float[]{15, 4,    0, 2,       3, 2};
		chances[25] = chances[24] = chances[23] = chances[22] = chances[21];

		chances[26] =  new float[]{0, 0,     22, 3,      0, 0};
		chances[30] = chances[29] = chances[28] = chances[27] = chances[26];

		chances[31] = new float[]{12, 0,    0, 5,       5, 3};
		chances[35] = chances[34] = chances[33] = chances[32] = chances[31];

		chances[36] = new float[]{0, 0,     18, 3,      3, 1};
		chances[40] = chances[39] = chances[38] = chances[37] = chances[36];

		chances[41] = new float[]{15, 4,    0, 2,       3, 2};
		chances[45] = chances[44] = chances[43] = chances[42] = chances[41];

		chances[46] =  new float[]{0, 0,     22, 3,      0, 0};
		chances[50] = chances[49] = chances[48] = chances[47] = chances[46];

		chances[51] = new float[]{12, 0,    0, 5,       5, 3};
		chances[55] = chances[54] = chances[53] = chances[52] = chances[51];

		chances[56] = new float[]{0, 0,     18, 3,      3, 1};
		chances[60] = chances[59] = chances[58] = chances[57] = chances[56];

		chances[61] = new float[]{15, 4,    0, 2,       3, 2};
		chances[65] = chances[64] = chances[63] = chances[62] = chances[61];

		chances[66] = new float[]{15, 4,    0, 2,       3, 2};
		chances[70] = chances[69] = chances[68] = chances[67] = chances[66];

		chances[71] =  new float[]{0, 0,     22, 3,      0, 0};
		chances[75] = chances[74] = chances[73] = chances[72] = chances[71];

		chances[76] = new float[]{12, 0,    0, 5,       5, 3};
		chances[80] = chances[79] = chances[78] = chances[77] = chances[76];

		chances[81] = new float[]{15, 4,    0, 2,       3, 2};
		chances[85] = chances[84] = chances[83] = chances[82] = chances[81];

		chances[86] =  new float[]{0, 0,     22, 3,      0, 0};
		chances[90] = chances[89] = chances[88] = chances[87] = chances[86];

		chances[91] = new float[]{12, 0,    0, 5,       5, 3};
		chances[95] = chances[94] = chances[93] = chances[92] = chances[91];

		chances[96] = new float[]{15, 4,    0, 2,       3, 2};
		chances[99] = chances[98] = chances[97] = chances[96];
	}
	
	public static ConnectionRoom createRoom(){
		return Reflection.newInstance(rooms.get(Random.chances(chances[Dungeon.depth])));
	}
}
