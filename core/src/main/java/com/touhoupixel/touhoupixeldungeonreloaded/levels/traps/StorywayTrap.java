package com.touhoupixel.touhoupixeldungeonreloaded.levels.traps;

import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.watabou.utils.Random;

public class StorywayTrap extends Trap {

	private static final float DELAY = 1f;

	{
		color = TEAL;
		shape = LARGE_DOT;

		canBeHidden = false;
		avoidsHallways = true;
	}

	@Override
	public void activate() {

		switch (Random.Int(4)) {
			case 0:
			default:
				new AlarmTrap().set(Dungeon.heroine.pos).activate();
				break;
			case 1:
				new DisarmingTrap().set(Dungeon.heroine.pos).activate();
				break;
			case 2:
				new SlowTrap().set(Dungeon.heroine.pos).activate();
				break;
			case 3:
				new SummoningTrap().set(Dungeon.heroine.pos).activate();
				break;
		}
	}
}