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

package com.touhoupixel.touhoupixeldungeonreloaded.ui;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndInfoBuff;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class BuffIndicator extends Component {

	//transparent icon
	public static final int NONE    = 139;

	//FIXME this is becoming a mess, should do a big cleaning pass on all of these
	//and think about tinting options
	public static final int MIND_VISION = 0;
	public static final int LEVITATION  = 1;
	public static final int FIRE        = 2;
	public static final int POISON      = 3;
	public static final int PARALYSIS   = 4;
	public static final int HUNGER      = 5;
	public static final int STARVATION  = 6;
	public static final int TIME        = 7;
	public static final int AMOK        = 9;
	public static final int TERROR      = 10;
	public static final int ROOTS       = 11;
	public static final int INVISIBLE   = 12;
	public static final int SHADOWS     = 13;
	public static final int WEAKNESS    = 14;
	public static final int FROST       = 15;
	public static final int BLINDNESS   = 16;
	public static final int EXTREME_HUNGER = 17;
	public static final int FURY           = 18;
	public static final int HERB_HEALING   = 19;
	public static final int ARMOR          = 20;
	public static final int HEART          = 21;
	public static final int LIGHT          = 22;
	public static final int CRIPPLE     = 23;
	public static final int BARKSKIN    = 24;
	public static final int IMMUNITY    = 25;
	public static final int BLEEDING    = 26;
	public static final int MARK        = 27;
	public static final int DEFERRED    = 28;
	public static final int DROWSY      = 29;
	public static final int MAGIC_SLEEP = 30;
	public static final int EMPATHETIC  = 31;
	public static final int FORESIGHT   = 32;
	public static final int VERTIGO     = 33;
	public static final int RECHARGING  = 34;
	public static final int LOCKED_FLOOR  = 35;
	public static final int CORRUPT       = 36;
	public static final int BLESS         = 37;
	public static final int HOMING_BLADE  = 38;
	public static final int CRIT_CHANCE_UP = 39;
	public static final int FUTURE_SIGHT  = 40;
	public static final int HASTE         = 41;
	public static final int HEAVEN_SPEED  = 42;
	public static final int WELL_FED      = 43;
	public static final int HEALING       = 44;
	public static final int WEAPON        = 45;
	public static final int VULNERABLE    = 46;
	public static final int HEX           = 47;
	public static final int DEGRADE       = 48;
	public static final int PINCUSHION    = 49;
	public static final int UPGRADE       = 50;
	public static final int CALM          = 51;
	public static final int ANKH          = 52;
	public static final int FLOAT_SLAYER  = 53;
	public static final int MIGHT         = 54;
	public static final int DOUBLE_SPEED  = 55;
	public static final int HINA_CURSE    = 56;
	public static final int REGEN_BLOCK   = 57;
	public static final int ZEN           = 58;
	public static final int SILENCE       = 59;
	public static final int HISOU         = 60;
	public static final int HIGH_STRESS   = 61;
	public static final int YUUMA_ABSORB  = 62;
	public static final int INVERSION     = 63;
	public static final int GOLD_CREATION = 64;
	public static final int DANMAKU_POWER_UP  = 65;
	public static final int KOMACHI_CURSE     = 66;
	public static final int ZERO_DEXTERITY    = 67;
	public static final int KEY_HEAL          = 68;
	public static final int STEALTH           = 69;
	public static final int HALF_LEVEL        = 70;
	public static final int YOKAI_BORDER      = 73;
	public static final int MELEE_NULLIFY     = 74;
	public static final int SUPER_HARD        = 75;
	public static final int ANTI_SHIP_BATTERY = 76;
	public static final int BOSS_KILLER       = 77;
	public static final int CELESTIAL_BODY    = 78;
	public static final int REACH_INCREASE    = 79;
	public static final int YUUKA_RAGE        = 80;
	public static final int MAGIC_SLUMBER     = 81;
	public static final int DOUBLE_RAINBOW    = 82;
	public static final int POTION_FREEZE     = 83;
	public static final int DE_SLAYING        = 84;
	public static final int ALICE_CURSE       = 85;
	public static final int DISMANTLE_PRESSURE = 86;
	public static final int HECATIA_RULE       = 87;
	public static final int AURA_REIMU         = 88;
	public static final int HARD_SEARCH        = 89;
	public static final int EXTREME_FEAR       = 90;
	public static final int REBIRTH            = 91;
	public static final int REBIRTH_DONE        = 92;
	public static final int DOUBLE_SPEED_RESIST = 93;
	public static final int WAND_ZERO_DAMAGE    = 94;
	public static final int MAGIC_BUFF          = 95;
	public static final int MAGIC_DRAIN         = 96;
	public static final int BALANCE_BREAK       = 98;
	public static final int EXTREME_CONFUSION   = 99;
	public static final int CURSED_BLOW         = 101;
	public static final int REMI_COUNTDOWN      = 104;
	public static final int SUPERNATURAL_BORDER = 105;
	public static final int REMILIA_FATE        = 106;
	public static final int FLANDRE_COOLDOWN    = 107;
	public static final int FUMO_LOVER          = 111;
	public static final int INACCURATE          = 112;
	public static final int DOOMED_ZONE         = 113;
	public static final int HEAT_RISER          = 115;
	public static final int RANDOMIZER          = 116;
	public static final int HUMAN_HALF			= 117;
	public static final int GHOST_HALF 			= 118;
	public static final int DISMANTLE_READY     = 119;
	public static final int ONIGIRI             = 120;
	public static final int IRON_RINGS 			= 121;
	public static final int BACKDOOR			= 122;

	public static final int SIZE_SMALL  = 7;
	public static final int SIZE_LARGE  = 16;

	private static BuffIndicator heroInstance;
	private static BuffIndicator bossInstance;

	private LinkedHashMap<Buff, BuffButton> buffButtons = new LinkedHashMap<>();
	private boolean needsRefresh;
	private Char ch;

	private boolean large = false;

	public BuffIndicator( Char ch, boolean large ) {
		super();

		this.ch = ch;
		this.large = large;
		if (ch == Dungeon.heroine) {
			heroInstance = this;
		}
	}

	@Override
	public void destroy() {
		super.destroy();

		if (this == heroInstance) {
			heroInstance = null;
		}
	}

	@Override
	public synchronized void update() {
		super.update();
		if (needsRefresh){
			needsRefresh = false;
			layout();
		}
	}

	private boolean buffsHidden = false;

	@Override
	protected void layout() {

		ArrayList<Buff> newBuffs = new ArrayList<>();
		for (Buff buff : ch.buffs()) {
			if (buff.icon() != NONE) {
				newBuffs.add(buff);
			}
		}

		int size = large ? SIZE_LARGE : SIZE_SMALL;

		//remove any icons no longer present
		for (Buff buff : buffButtons.keySet().toArray(new Buff[0])){
			if (!newBuffs.contains(buff)){
				Image icon = buffButtons.get( buff ).icon;
				icon.originToCenter();
				icon.alpha(0.6f);
				add( icon );
				add( new AlphaTweener( icon, 0, 0.6f ) {
					@Override
					protected void updateValues( float progress ) {
						super.updateValues( progress );
						image.scale.set( 1 + 5 * progress );
					}

					@Override
					protected void onComplete() {
						image.killAndErase();
					}
				} );

				buffButtons.get( buff ).destroy();
				remove(buffButtons.get( buff ));
				buffButtons.remove( buff );
			}
		}

		//add new icons
		for (Buff buff : newBuffs) {
			if (!buffButtons.containsKey(buff)) {
				BuffButton icon = new BuffButton(buff, large);
				add(icon);
				buffButtons.put( buff, icon );
			}
		}

		//layout
		int pos = 0;
		for (BuffButton icon : buffButtons.values()){
			icon.updateIcon();
			//button areas are slightly oversized, especially on small buttons
			icon.setRect(x + pos * (size + 1), y, size + 1, size + (large ? 0 : 5));
			PixelScene.align(icon);
			pos++;

		}
	}

	public boolean allBuffsVisible(){
		return !buffsHidden;
	}

	private static class BuffButton extends IconButton {

		private Buff buff;

		private boolean large;

		public Image grey; //only for small
		public BitmapText text; //only for large

		public BuffButton( Buff buff, boolean large ){
			super( new BuffIcon(buff, large));
			this.buff = buff;
			this.large = large;

			bringToFront(grey);
			bringToFront(text);
		}

		@Override
		protected void createChildren() {
			super.createChildren();
			grey = new Image( TextureCache.createSolid(0xCC666666));
			add( grey );

			text = new BitmapText(PixelScene.pixelFont);
			add( text );
		}

		public void updateIcon(){
			((BuffIcon)icon).refresh(buff);
			//round up to the nearest pixel if <50% faded, otherwise round down
			if (!large || buff.iconTextDisplay().isEmpty()) {
				text.visible = false;
				grey.visible = true;
				float fadeHeight = buff.iconFadePercent() * icon.height();
				float zoom = (camera() != null) ? camera().zoom : 1;
				if (fadeHeight < icon.height() / 2f) {
					grey.scale.set(icon.width(), (float) Math.ceil(zoom * fadeHeight) / zoom);
				} else {
					grey.scale.set(icon.width(), (float) Math.floor(zoom * fadeHeight) / zoom);
				}
			} else if (!buff.iconTextDisplay().isEmpty()) {
				text.visible = true;
				grey.visible = false;
				if (buff.type == Buff.buffType.POSITIVE)        text.hardlight(CharSprite.POSITIVE);
				else if (buff.type == Buff.buffType.NEGATIVE)   text.hardlight(CharSprite.NEGATIVE);
				text.alpha(0.7f);

				text.text(buff.iconTextDisplay());
				text.measure();
			}
		}

		@Override
		protected void layout() {
			super.layout();
			grey.x = icon.x = this.x + (large ? 0 : 1);
			grey.y = icon.y = this.y + (large ? 0 : 2);

			if (text.width > width()){
				text.scale.set(PixelScene.align(0.5f));
			} else {
				text.scale.set(1f);
			}
			text.x = this.x + width() - text.width() - 1;
			text.y = this.y + width() - text.baseLine() - 2;
		}

		@Override
		protected void onClick() {
			if (buff.icon() != NONE) GameScene.show(new WndInfoBuff(buff));
		}

		@Override
		protected void onPointerDown() {
			//don't affect buff color
			Sample.INSTANCE.play( Assets.Sounds.CLICK );
		}

		@Override
		protected void onPointerUp() {
			//don't affect buff color
		}

		@Override
		protected String hoverText() {
			return Messages.titleCase(buff.name());
		}
	}

	public static void refreshHero() {
		if (heroInstance != null) {
			heroInstance.needsRefresh = true;
		}
	}

	public static void refreshBoss(){
		if (bossInstance != null) {
			bossInstance.needsRefresh = true;
		}
	}

	public static void setBossInstance(BuffIndicator boss){
		bossInstance = boss;
	}
}