package com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.SpellSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndTextInput;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlankScroll extends Item {

	public static final String AC_DRINK = "DRINK";

	{
		image = ItemSpriteSheet.SCROLL;

		defaultAction = AC_DRINK;

		stackable = false;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (Dungeon.heroine.buff(Onigiri.class) == null) {
			actions.add(AC_DRINK);
		}
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action) {
		super.execute(heroine, action);
		if (action.equals(AC_DRINK)) {
			GameScene.show(new WndTextInput("Write the spellcard name", "Spellcard name must be English or Korean. Name should be 'name' of spellcard or 'name'의 스펠카드. And in English, name must be all lowercase. That scroll will be identified. Lastly, you cannot write an exotic spellcard.", "", 20, false, "Write", "Cancel") {
				@Override
				public void onSelect(boolean positive, String text) {
					Map<String, Runnable> actions = new HashMap<>();
					actions.put("닻", () -> createScroll(new ScrollOfAnchor(), heroine));
					actions.put("anchor", () -> createScroll(new ScrollOfAnchor(), heroine));

					actions.put("땅", () -> createScroll(new ScrollOfEarth(), heroine));
					actions.put("earth", () -> createScroll(new ScrollOfEarth(), heroine));

					actions.put("해주", () -> createScroll(new ScrollOfExorcism(), heroine));
					actions.put("exorcism", () -> createScroll(new ScrollOfExorcism(), heroine));

					actions.put("근절", () -> createScroll(new ScrollOfExtinction(), heroine));
					actions.put("extinction", () -> createScroll(new ScrollOfExtinction(), heroine));

					actions.put("하늘", () -> createScroll(new ScrollOfFate(), heroine));
					actions.put("fate", () -> createScroll(new ScrollOfFate(), heroine));

					actions.put("고치기", () -> createScroll(new ScrollOfFixer(), heroine));
					actions.put("fixer", () -> createScroll(new ScrollOfFixer(), heroine));

					actions.put("감정", () -> createScroll(new ScrollOfIdentify(), heroine));
					actions.put("identify", () -> createScroll(new ScrollOfIdentify(), heroine));

					actions.put("자장가", () -> createScroll(new ScrollOfLullaby(), heroine));
					actions.put("lullaby", () -> createScroll(new ScrollOfLullaby(), heroine));

					actions.put("거울상", () -> createScroll(new ScrollOfMirrorImage(), heroine));
					actions.put("mirrorimage", () -> createScroll(new ScrollOfMirrorImage(), heroine));

					actions.put("분노", () -> createScroll(new ScrollOfRage(), heroine));
					actions.put("rage", () -> createScroll(new ScrollOfRage(), heroine));

					actions.put("충전", () -> createScroll(new ScrollOfRecharging(), heroine));
					actions.put("recharging", () -> createScroll(new ScrollOfRecharging(), heroine));

					actions.put("징벌", () -> createScroll(new ScrollOfRetribution(), heroine));
					actions.put("retribution", () -> createScroll(new ScrollOfRetribution(), heroine));

					actions.put("공포", () -> createScroll(new ScrollOfTerror(), heroine));
					actions.put("terror", () -> createScroll(new ScrollOfTerror(), heroine));

					actions.put("변환", () -> createScroll(new ScrollOfTransmutation(), heroine));
					actions.put("transmutation", () -> createScroll(new ScrollOfTransmutation(), heroine));

					Runnable selectedAction = actions.get(text);
					if (selectedAction != null) {
						selectedAction.run();
					}
				}

				@Override
				public void onBackPressed() {
					this.hide();
				}
			});
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	private void createScroll(Scroll scroll, Hero heroine) {
		this.detach(heroine.belongings.backpack);
		scroll.collect();
		scroll.identify();

		Sample.INSTANCE.play( Assets.Sounds.READ );
		Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );

		SpellSprite.show( curUser, SpellSprite.CHARGE );

		GLog.w(Messages.get(this, "scroll_created"));
	}
}
