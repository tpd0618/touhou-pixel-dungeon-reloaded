package com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs;

import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.BuffIndicator;

public class ExtremeHunger extends FlavourBuff {

	public static final float DURATION = 50f;

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public int icon() {
		return BuffIndicator.EXTREME_HUNGER;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
}