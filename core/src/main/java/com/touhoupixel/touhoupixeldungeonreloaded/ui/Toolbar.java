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

import static com.touhoupixel.touhoupixeldungeonreloaded.Dungeon.heroine;
import static com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor.TICK;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Challenges;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.QuickSlot;
import com.touhoupixel.touhoupixeldungeonreloaded.SPDAction;
import com.touhoupixel.touhoupixeldungeonreloaded.SPDSettings;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.BossKiller;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.MagicBuff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Roots;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Terror;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.YoumuAbility;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Belongings;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.BossHecatia;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Marisa;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.Beam;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeonreloaded.effects.particles.PurpleParticle;
import com.touhoupixel.touhoupixeldungeonreloaded.items.Item;
import com.touhoupixel.touhoupixeldungeonreloaded.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeonreloaded.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.Terrain;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.CellSelector;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTerrainTilemap;
import com.touhoupixel.touhoupixeldungeonreloaded.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndBag;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndKeyBindings;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndMessage;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndQuickBag;
import com.touhoupixel.touhoupixeldungeonreloaded.windows.WndUseItem;
import com.watabou.input.ControllerHandler;
import com.watabou.input.GameAction;
import com.watabou.input.KeyBindings;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Gizmo;
import com.watabou.noosa.Image;
import com.watabou.noosa.PointerArea;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.Callback;
import com.watabou.utils.Point;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Toolbar extends Component {

	private Tool btnWait;
	private Tool btnSearch;
	private Tool btnInventory;
	private QuickslotTool[] btnQuick;
	private SlotSwapTool btnSwap;

	private PickedUpItem pickedUp;

	private boolean lastEnabled = true;
	public boolean examining = false;

	private static Toolbar instance;

	public enum Mode {
		SPLIT,
		GROUP,
		CENTER
	}

	public Toolbar() {
		super();

		instance = this;

		height = btnInventory.height();
	}

	@Override
	public synchronized void destroy() {
		super.destroy();
		if (instance == this) instance = null;
	}

	@Override
	protected void createChildren() {

		add(btnSwap = new SlotSwapTool(128, 0, 21, 23));

		btnQuick = new QuickslotTool[QuickSlot.SIZE];
		for (int i = 0; i < btnQuick.length; i++){
			add( btnQuick[i] = new QuickslotTool(64, 0, 22, 24, i) );
		}

		//hidden button for quickslot selector keybind
		add(new Button(){
			@Override
			protected void onClick() {
				if (QuickSlotButton.targetingSlot != -1){
					int cell = QuickSlotButton.autoAim(QuickSlotButton.lastTarget, Dungeon.quickslot.getItem(QuickSlotButton.targetingSlot));

					if (cell != -1){
						GameScene.handleCell(cell);
					} else {
						//couldn't auto-aim, just target the position and hope for the best.
						GameScene.handleCell( QuickSlotButton.lastTarget.pos );
					}
					return;
				}

				if (Dungeon.heroine.ready && !GameScene.cancel()) {

					String[] slotNames = new String[6];
					Image[] slotIcons = new Image[6];
					for (int i = 0; i < 6; i++){
						Item item = Dungeon.quickslot.getItem(i);

						if (item != null && !Dungeon.quickslot.isPlaceholder(i)){
							slotNames[i] = Messages.titleCase(item.name());
							slotIcons[i] = new ItemSprite(item);
						} else {
							slotNames[i] = Messages.get(Toolbar.class, "quickslot_assign");
							slotIcons[i] = new ItemSprite(ItemSpriteSheet.SOMETHING);
						}
					}

					String info = "";
					if (ControllerHandler.controllerActive){
						info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.LEFT_CLICK, true)) + ": " + Messages.get(Toolbar.class, "quickslot_select") + "\n";
						info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.RIGHT_CLICK, true)) + ": " + Messages.get(Toolbar.class, "quickslot_assign") + "\n";
						info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.BACK, true)) + ": " + Messages.get(Toolbar.class, "quickslot_cancel");
					} else {
						info += Messages.get(WndKeyBindings.class, SPDAction.LEFT_CLICK.name()) + ": " + Messages.get(Toolbar.class, "quickslot_select") + "\n";
						info += Messages.get(WndKeyBindings.class, SPDAction.RIGHT_CLICK.name()) + ": " + Messages.get(Toolbar.class, "quickslot_assign") + "\n";
						info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.BACK, false)) + ": " + Messages.get(Toolbar.class, "quickslot_cancel");
					}

					GameScene.show(new RadialMenu(Messages.get(Toolbar.class, "quickslot_prompt"), info, slotNames, slotIcons) {
						@Override
						public void onSelect(int idx, boolean alt) {
							Item item = Dungeon.quickslot.getItem(idx);

							if (item == null || Dungeon.quickslot.isPlaceholder(idx)
									|| alt){
								//TODO would be nice to use a radial menu for this too
								// Also a bunch of code could be moved out of here into subclasses of RadialMenu
								GameScene.selectItem(new WndBag.ItemSelector() {
									@Override
									public String textPrompt() {
										return Messages.get(QuickSlotButton.class, "select_item");
									}

									@Override
									public boolean itemSelectable(Item item) {
										return item.defaultAction() != null;
									}

									@Override
									public void onSelect(Item item) {
										if (item != null) {
											Dungeon.quickslot.setSlot( idx , item );
											QuickSlotButton.refresh();
										}
									}
								});
							} else {

								item.execute(Dungeon.heroine);
								if (item.usesTargeting) {
									QuickSlotButton.useTargeting(idx);
								}
							}
							super.onSelect(idx, alt);
						}
					});
				}
			}

			@Override
			public GameAction keyAction() {
				if (btnWait.active) return SPDAction.QUICKSLOT_SELECTOR;
				else				return null;
			}
		});

		add(btnWait = new Tool(24, 0, 20, 26) {
			@Override
			protected void onClick() {
				if (Dungeon.heroine.ready && !GameScene.cancel()) {
					examining = false;
					Dungeon.heroine.rest(false);
				}
			}

			@Override
			public GameAction keyAction() {
				return SPDAction.WAIT;
			}

			@Override
			public GameAction secondaryTooltipAction() {
				return SPDAction.WAIT_OR_PICKUP;
			}

			@Override
			protected String hoverText() {
				return Messages.titleCase(Messages.get(WndKeyBindings.class, "wait"));
			}

			protected boolean onLongClick() {
				if (Dungeon.heroine.ready && !GameScene.cancel()) {
					examining = false;
					Dungeon.heroine.rest(true);
				}
				return true;
			}
		});

		add(new Button(){
			@Override
			protected void onClick() {
				if (Dungeon.heroine.ready && !GameScene.cancel()) {
					examining = false;
					Dungeon.heroine.rest(true);
				}
			}

			@Override
			public GameAction keyAction() {
				if (btnWait.active) return SPDAction.REST;
				else				return null;
			}
		});

		add(new Button(){
			@Override
			protected void onClick() {
				if (Dungeon.heroine.ready && !GameScene.cancel()) {
					if (Dungeon.level.heaps.get(Dungeon.heroine.pos) != null
						&& Dungeon.heroine.handle(Dungeon.heroine.pos)){
						Dungeon.heroine.next();
					} else {
						examining = false;
						Dungeon.heroine.rest(false);
					}
				}
			}

			protected boolean onLongClick() {
				if (Dungeon.heroine.ready && !GameScene.cancel()) {
					examining = false;
					Dungeon.heroine.rest(true);
				}
				return true;
			}

			@Override
			public GameAction keyAction() {
				if (btnWait.active) return SPDAction.WAIT_OR_PICKUP;
				else				return null;
			}
		});

		add(btnSearch = new Tool(44, 0, 20, 26) {
			@Override
			protected void onClick() {
				if (Dungeon.heroine.ready) {
					if (!examining && !GameScene.cancel()) {
						GameScene.selectCell(informer);
						examining = true;
					} else if (examining) {
						informer.onSelect(null);
						Dungeon.heroine.search(true);
					}
				}
			}

			@Override
			public GameAction keyAction() {
				if (btnSearch.active) return SPDAction.SPELL_CARD;
				else				return null;
			}

			@Override
			protected String hoverText() {
				return Messages.titleCase(Messages.get(WndKeyBindings.class, "spell_card"));
			}

			@Override
			protected boolean onLongClick() {
				if (Statistics.card66) {
					if (heroine.buff(AnkhInvulnerability.class) != null) {
						GLog.w(Messages.get(Marisa.class, "already_inv"));
					} else if (Statistics.spellcard < 1 && !(heroine.HT > 4) && !(heroine.HP > 4)) {
						GLog.w(Messages.get(Marisa.class, "no_spell_card"));
					} else if (Statistics.spellcard > 0) {
						if (Statistics.card35) {
							Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
						} else {
							Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
						}
						if (Dungeon.isChallenged(Challenges.HOPELESS)) {
							Statistics.mood += 1;
						}
						Statistics.spellcard -= 1;
						Statistics.spellcarduse = true;
						GameScene.flash(0x80FFFFFF);
						Sample.INSTANCE.play(Assets.Sounds.BLAST);
						heroine.spendAndNext(1f);
					} else {
						if (Statistics.card35) {
							Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
						} else {
							Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
						}
						if (Dungeon.isChallenged(Challenges.HOPELESS)) {
							Statistics.mood += 1;
						}
						heroine.HP -= 4;
						heroine.HT -= 4;
						Statistics.maxHP_down -= 4;
						Statistics.spellcarduse = true;
						GameScene.flash(0x80FFFFFF);
						Sample.INSTANCE.play(Assets.Sounds.BLAST);
						heroine.spendAndNext(1f);
					}
				} else {
					if (Statistics.spellcard < 1) {
						GLog.w(Messages.get(Marisa.class, "no_spell_card"));
					} else if (heroine.buff(AnkhInvulnerability.class) != null) {
						GLog.w(Messages.get(Marisa.class, "already_inv"));
					} else {
						if (Statistics.card35) {
							Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
						} else {
							Buff.prolong(heroine, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
						}
						if (Dungeon.isChallenged(Challenges.HOPELESS)) {
							Statistics.mood += 1;
						}
						for (Mob mob : Dungeon.level.mobs) {
							if (Dungeon.floor == 40 && mob instanceof BossHecatia && mob.isAlive()) {
								Buff.prolong(mob, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 3f);
								GLog.w(Messages.get(Marisa.class, "bomb_barrier"));
							}
						}
						Statistics.spellcard -= 1;
						Statistics.bomb_count += 1;
						Statistics.spellcarduse = true;
						GameScene.flash(0x80FFFFFF);
						Sample.INSTANCE.play(Assets.Sounds.BLAST);
						heroine.spendAndNext(1f);

						switch (Dungeon.heroine.heroClass) {
							case PLAYERREIMU:
								break;
							case PLAYERMARISA:
								GameScene.selectCell(zapper);
								break;
							case PLAYERSANAE:
								sanaeBomb();
								break;
							case PLAYERYOUMU:
								Dungeon.heroine.buff(YoumuAbility.class).bomb();
								break;
							case PLAYERSAKUYA:
								sakuyaBomb();
								break;
						}
					}
				}
				return true;
			}
		});

		add(btnInventory = new Tool(0, 0, 24, 26) {
			private CurrencyIndicator ind;

			private Image arrow;

			@Override
			protected void onClick() {
				if (Dungeon.heroine.ready || !Dungeon.heroine.isAlive()) {
					if (SPDSettings.interfaceSize() == 2) {
						GameScene.toggleInvPane();
					} else {
						if (!GameScene.cancel()) {
							GameScene.show(new WndBag(Dungeon.heroine.belongings.backpack));
						}
					}
				}
			}

			@Override
			public GameAction keyAction() {
				return SPDAction.INVENTORY;
			}

			@Override
			public GameAction secondaryTooltipAction() {
				return SPDAction.INVENTORY_SELECTOR;
			}

			@Override
			protected String hoverText() {
				return Messages.titleCase(Messages.get(WndKeyBindings.class, "inventory"));
			}

			@Override
			protected boolean onLongClick() {
				GameScene.show(new WndQuickBag(null));
				return true;
			}

			@Override
			protected void createChildren() {
				super.createChildren();
				arrow = Icons.get(Icons.COMPASS);
				arrow.originToCenter();
				arrow.visible = SPDSettings.interfaceSize() == 2;
				arrow.tint(0x3D2E18, 1f);
				add(arrow);

				ind = new CurrencyIndicator();
				add(ind);
			}

			@Override
			protected void layout() {
				super.layout();
				ind.fill(this);

				arrow.x = left() + (width - arrow.width())/2;
				arrow.y = bottom()-arrow.height-1;
				arrow.angle = bottom() == camera().height ? 0 : 180;
			}
		});

		//hidden button for inventory selector keybind
		add(new Button(){
			@Override
			protected void onClick() {
				if (Dungeon.heroine.ready && !GameScene.cancel()) {
					ArrayList<Bag> bags = Dungeon.heroine.belongings.getBags();
					String[] names = new String[bags.size()];
					Image[] images = new Image[bags.size()];
					for (int i = 0; i < bags.size(); i++){
						names[i] = Messages.titleCase(bags.get(i).name());
						images[i] = new ItemSprite(bags.get(i));
					}
					String info = "";
					if (ControllerHandler.controllerActive){
						info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.LEFT_CLICK, true)) + ": " + Messages.get(Toolbar.class, "container_select") + "\n";
						info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.BACK, true)) + ": " + Messages.get(Toolbar.class, "container_cancel");
					} else {
						info += Messages.get(WndKeyBindings.class, SPDAction.LEFT_CLICK.name()) + ": " + Messages.get(Toolbar.class, "container_select") + "\n";
						info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.BACK, false)) + ": " + Messages.get(Toolbar.class, "container_cancel");
					}

					GameScene.show(new RadialMenu(Messages.get(Toolbar.class, "container_prompt"), info, names, images){
						@Override
						public void onSelect(int idx, boolean alt) {
							super.onSelect(idx, alt);
							Bag bag = bags.get(idx);
							ArrayList<Item> items = (ArrayList<Item>) bag.items.clone();

							for(Item i : bag.items){
								if (i instanceof Bag) items.remove(i);
							}

							if (idx == 0){
								Belongings b = Dungeon.heroine.belongings;
								if (b.bracelet() != null) items.add(0, b.bracelet());
								if (b.misc() != null) items.add(0, b.misc());
								if (b.artifact() != null) items.add(0, b.artifact());
								if (b.armor() != null) items.add(0, b.armor());
								if (b.weapon() != null) items.add(0, b.weapon());
							}

							if (items.size() == 0){
								GameScene.show(new WndMessage(Messages.get(Toolbar.class, "container_empty")));
								return;
							}

							String[] itemNames = new String[items.size()];
							Image[] itemIcons = new Image[items.size()];
							for (int i = 0; i < items.size(); i++){
								itemNames[i] = Messages.titleCase(items.get(i).name());
								itemIcons[i] = new ItemSprite(items.get(i));
							}

							String info = "";
							if (ControllerHandler.controllerActive){
								info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.LEFT_CLICK, true)) + ": " + Messages.get(Toolbar.class, "item_select") + "\n";
								info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.RIGHT_CLICK, true)) + ": " + Messages.get(Toolbar.class, "item_use") + "\n";
								info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.BACK, false)) + ": " + Messages.get(Toolbar.class, "item_cancel");
							} else {
								info += Messages.get(WndKeyBindings.class, SPDAction.LEFT_CLICK.name()) + ": " + Messages.get(Toolbar.class, "item_select") + "\n";
								info += Messages.get(WndKeyBindings.class, SPDAction.RIGHT_CLICK.name()) + ": " + Messages.get(Toolbar.class, "item_use") + "\n";
								info += KeyBindings.getKeyName(KeyBindings.getFirstKeyForAction(GameAction.BACK, false)) + ": " + Messages.get(Toolbar.class, "item_cancel");
							}

							GameScene.show(new RadialMenu(Messages.get(Toolbar.class, "item_prompt"), info, itemNames, itemIcons){
								@Override
								public void onSelect(int idx, boolean alt) {
									super.onSelect(idx, alt);
									Item item = items.get(idx);
									if (alt && item.defaultAction() != null) {
										item.execute(Dungeon.heroine);
										if (item.usesTargeting) {
											QuickSlotButton.useTargeting(idx);
										}
									} else {
										Game.scene().addToFront(new WndUseItem(null, item));
									}
								}
							});
						}
					});
				}
			}

			@Override
			public GameAction keyAction() {
				if (btnWait.active) return SPDAction.INVENTORY_SELECTOR;
				else				return null;
			}
		});

		add(pickedUp = new PickedUpItem());
	}

	@Override
	protected void layout() {

		float right = width;

		int quickslotsToShow = 4;
		if (PixelScene.uiCamera.width > 152) quickslotsToShow ++;
		if (PixelScene.uiCamera.width > 170) quickslotsToShow ++;

		int startingSlot;
		if (SPDSettings.quickSwapper() && quickslotsToShow < 6){
			quickslotsToShow = 3;
			startingSlot = swappedQuickslots ? 3 : 0;
			btnSwap.visible = true;
			btnSwap.active = lastEnabled;
		} else {
			startingSlot = 0;
			btnSwap.visible = btnSwap.active = false;
			btnSwap.setPos(0, PixelScene.uiCamera.height);
		}
		int endingSlot = startingSlot+quickslotsToShow-1;

		for (int i = 0; i < btnQuick.length; i++){
			btnQuick[i].visible = i >= startingSlot && i <= endingSlot;
			btnQuick[i].enable(btnQuick[i].visible && lastEnabled);
			if (i < startingSlot || i > endingSlot){
				btnQuick[i].setPos(btnQuick[i].left(), PixelScene.uiCamera.height);
			}
		}

		if (SPDSettings.interfaceSize() > 0){
			btnInventory.setPos(right - btnInventory.width(), y);
			btnWait.setPos(btnInventory.left() - btnWait.width(), y);
			btnSearch.setPos(btnWait.left() - btnSearch.width(), y);

			right = btnSearch.left();
			for(int i = endingSlot; i >= startingSlot; i--) {
				if (i == endingSlot){
					btnQuick[i].border(0, 2);
					btnQuick[i].frame(106, 0, 19, 24);
				} else if (i == 0){
					btnQuick[i].border(2, 1);
					btnQuick[i].frame(86, 0, 20, 24);
				} else {
					btnQuick[i].border(0, 1);
					btnQuick[i].frame(88, 0, 18, 24);
				}
				btnQuick[i].setPos(right-btnQuick[i].width(), y+2);
				right = btnQuick[i].left();
			}

			//swap button never appears on larger interface sizes

			return;
		}

		for(int i = startingSlot; i <= endingSlot; i++) {
			if (i == startingSlot && !SPDSettings.flipToolbar() ||
				i == endingSlot && SPDSettings.flipToolbar()){
				btnQuick[i].border(0, 2);
				btnQuick[i].frame(106, 0, 19, 24);
			} else if (i == startingSlot && SPDSettings.flipToolbar() ||
					i == endingSlot && !SPDSettings.flipToolbar()){
				btnQuick[i].border(2, 1);
				btnQuick[i].frame(86, 0, 20, 24);
			} else {
				btnQuick[i].border(0, 1);
				btnQuick[i].frame(88, 0, 18, 24);
			}
		}

		float shift = 0;
		switch(Mode.valueOf(SPDSettings.toolbarMode())){
			case SPLIT:
				btnWait.setPos(x, y);
				btnSearch.setPos(btnWait.right(), y);

				btnInventory.setPos(right - btnInventory.width(), y);

				float left = 0;

				btnQuick[startingSlot].setPos(btnInventory.left() - btnQuick[startingSlot].width(), y + 2);
				for (int i = startingSlot+1; i <= endingSlot; i++) {
					btnQuick[i].setPos(btnQuick[i-1].left() - btnQuick[i].width(), y + 2);
					shift = btnSearch.right() - btnQuick[i].left();
				}

				if (btnSwap.visible){
					btnSwap.setPos(btnQuick[endingSlot].left() - (btnSwap.width()-2), y+3);
					shift = btnSearch.right() - btnSwap.left();
				}

				break;

			//center = group but.. well.. centered, so all we need to do is pre-emptively set the right side further in.
			case CENTER:
				float toolbarWidth = btnWait.width() + btnSearch.width() + btnInventory.width();
				for(Button slot : btnQuick){
					if (slot.visible) toolbarWidth += slot.width();
				}
				if (btnSwap.visible) toolbarWidth += btnSwap.width()-2;
				right = (width + toolbarWidth)/2;

			case GROUP:
				btnWait.setPos(right - btnWait.width(), y);
				btnSearch.setPos(btnWait.left() - btnSearch.width(), y);
				btnInventory.setPos(btnSearch.left() - btnInventory.width(), y);

				btnQuick[startingSlot].setPos(btnInventory.left() - btnQuick[startingSlot].width(), y + 2);
				for (int i = startingSlot+1; i <= endingSlot; i++) {
					btnQuick[i].setPos(btnQuick[i-1].left() - btnQuick[i].width(), y + 2);
					shift = -btnQuick[i].left();
				}

				if (btnSwap.visible){
					btnSwap.setPos(btnQuick[endingSlot].left() - (btnSwap.width()-2), y+3);
					shift = -btnSwap.left();
				}

				break;
		}

		if (shift > 0){
			shift /= 2; //we want to center;
			for (int i = startingSlot; i <= endingSlot; i++) {
				btnQuick[i].setPos(btnQuick[i].left()+shift,  btnQuick[i].top());
			}
			if (btnSwap.visible){
				btnSwap.setPos(btnSwap.left()+shift, btnSwap.top());
			}
		}

		right = width;

		if (SPDSettings.flipToolbar()) {

			btnWait.setPos( (right - btnWait.right()), y);
			btnSearch.setPos( (right - btnSearch.right()), y);
			btnInventory.setPos( (right - btnInventory.right()), y);

			for(int i = startingSlot; i <= endingSlot; i++) {
				btnQuick[i].setPos( right - btnQuick[i].right(), y+2);
			}

			if (btnSwap.visible){
				btnSwap.setPos( right - btnSwap.right(), y+3);
			}

		}

	}

	public static void updateLayout(){
		if (instance != null) instance.layout();
	}

	@Override
	public void update() {
		super.update();

		if (lastEnabled != (Dungeon.heroine.ready && Dungeon.heroine.isAlive())) {
			lastEnabled = (Dungeon.heroine.ready && Dungeon.heroine.isAlive());

			for (Gizmo tool : members.toArray(new Gizmo[0])) {
				if (tool instanceof Tool) {
					((Tool)tool).enable( lastEnabled );
				}
			}
		}

		if (!Dungeon.heroine.isAlive()) {
			btnInventory.enable(true);
		}
	}

	public void alpha( float value ){
		btnWait.alpha( value );
		btnSearch.alpha( value );
		btnInventory.alpha( value );
		for (QuickslotTool tool : btnQuick){
			tool.alpha(value);
		}
		btnSwap.alpha( value );
	}

	public void pickup( Item item, int cell ) {
		pickedUp.reset( item,
			cell,
			btnInventory.centerX(),
			btnInventory.centerY());
	}

	private static CellSelector.Listener informer = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer cell ) {
			if (instance != null) {
				instance.examining = false;
				GameScene.examineCell(cell);
			}
		}
		@Override
		public String prompt() {
			return Messages.get(Toolbar.class, "examine_prompt");
		}
	};

	private static class Tool extends Button {

		private static final int BGCOLOR = 0x7B8073;

		private Image base;

		private Image icon;

		public Tool( int x, int y, int width, int height ) {
			super();

			hotArea.blockLevel = PointerArea.ALWAYS_BLOCK;
			frame(x, y, width, height);
		}

		public void frame( int x, int y, int width, int height) {
			base.frame( x, y, width, height );

			this.width = width;
			this.height = height;
		}

		@Override
		protected void createChildren() {
			super.createChildren();

			base = new Image( Assets.Interfaces.TOOLBAR );
			add( base );
		}

		@Override
		protected void layout() {
			super.layout();

			base.x = x;
			base.y = y;

			if (icon != null){
				icon.x = x + (width()- icon.width())/2f;
				icon.y = y + (height()- icon.height())/2f;
			}
		}

		public void alpha( float value ){
			base.alpha(value);
			if (icon != null) icon.alpha(value);
		}

		@Override
		protected void onPointerDown() {
			base.brightness( 1.4f );
		}

		@Override
		protected void onPointerUp() {
			if (active) {
				base.resetColor();
			} else {
				base.tint( BGCOLOR, 0.7f );
			}
		}

		public void enable( boolean value ) {
			if (value != active) {
				if (value) {
					base.resetColor();
				} else {
					base.tint( BGCOLOR, 0.7f );
				}
				active = value;
			}
		}
	}

	private static class QuickslotTool extends Tool {

		private QuickSlotButton slot;
		private int borderLeft = 2;
		private int borderRight = 2;

		public QuickslotTool( int x, int y, int width, int height, int slotNum ) {
			super( x, y, width, height );

			slot = new QuickSlotButton( slotNum );
			add( slot );
		}

		public void border( int left, int right ){
			borderLeft = left;
			borderRight = right;
			layout();
		}

		@Override
		protected void layout() {
			super.layout();
			slot.setRect( x, y, width, height );
			slot.slotMargins(borderLeft, 2, borderRight, 2);
		}

		@Override
		public void enable( boolean value ) {
			super.enable( value && visible );
			slot.enable( value && visible );
		}
	}

	public static boolean swappedQuickslots = false;
	public static SlotSwapTool SWAP_INSTANCE;

	public static class SlotSwapTool extends Tool {

		private Image[] icons = new Image[4];
		private Item[] items = new Item[4];

		public SlotSwapTool(int x, int y, int width, int height) {
			super(x, y, width, height);
			SWAP_INSTANCE = this;
			updateVisuals();
		}

		@Override
		public synchronized void destroy() {
			super.destroy();
			if (SWAP_INSTANCE == this) SWAP_INSTANCE = null;
		}

		@Override
		protected void onClick() {
			super.onClick();
			swappedQuickslots = !swappedQuickslots;
			updateLayout();
			updateVisuals();
		}

		public void updateVisuals(){
			if (icons[0] == null){
				icons[0] = Icons.get(Icons.CHANGES);
				icons[0].scale.set(PixelScene.align(0.45f));
				add(icons[0]);
			}

			int slot;
			int slotDir;
			if (SPDSettings.flipToolbar()){
				slot = swappedQuickslots ? 0 : 3;
				slotDir = +1;
			} else {
				slot = swappedQuickslots ? 2 : 5;
				slotDir = -1;
			}

			for (int i = 1; i < 4; i++){
				if (items[i] == Dungeon.quickslot.getItem(slot)){
					slot += slotDir;
					continue;
				} else {
					items[i] = Dungeon.quickslot.getItem(slot);
				}
				if (icons[i] != null){
					icons[i].killAndErase();
					icons[i] = null;
				}
				if (items[i] != null){
					icons[i] = new ItemSprite(items[i]);
					icons[i].scale.set(PixelScene.align(0.45f));
					if (Dungeon.quickslot.isPlaceholder(slot)) icons[i].alpha(0.29f);
					add(icons[i]);
				}
				slot += slotDir;
			}

			icons[0].x = x + 2 + (8 - icons[0].width())/2;
			icons[0].y = y + 2 + (9 - icons[0].height())/2;
			PixelScene.align(icons[0]);

			if (icons[1] != null){
				icons[1].x = x + 11 + (8 - icons[1].width())/2;
				icons[1].y = y + 2 + (9 - icons[1].height())/2;
				PixelScene.align(icons[1]);
			}

			if (icons[2] != null){
				icons[2].x = x + 2 + (8 - icons[2].width())/2;
				icons[2].y = y + 12 + (9 - icons[2].height())/2;
				PixelScene.align(icons[2]);
			}

			if (icons[3] != null){
				icons[3].x = x + 11 + (8 - icons[3].width())/2;
				icons[3].y = y + 12 + (9 - icons[3].height())/2;
				PixelScene.align(icons[3]);
			}
		}

		@Override
		protected void layout() {
			super.layout();
			updateVisuals();
		}

		@Override
		public void enable(boolean value) {
			super.enable(value);
			for (Image ic : icons){
				if (ic != null && ic.alpha() >= 0.3f){
					ic.alpha( value ? 1 : 0.3f);
				}
			}
		}

		//private

	}

	public static class PickedUpItem extends ItemSprite {

		private static final float DURATION = 0.5f;

		private float startScale;
		private float startX, startY;
		private float endX, endY;
		private float left;

		public PickedUpItem() {
			super();

			originToCenter();

			active =
			visible =
				false;
		}

		public void reset( Item item, int cell, float endX, float endY ) {
			view( item );

			active =
			visible =
				true;

			PointF tile = DungeonTerrainTilemap.raisedTileCenterToWorld(cell);
			Point screen = Camera.main.cameraToScreen(tile.x, tile.y);
			PointF start = camera().screenToCamera(screen.x, screen.y);

			x = this.startX = start.x - width() / 2;
			y = this.startY = start.y - width() / 2;

			this.endX = endX - width() / 2;
			this.endY = endY - width() / 2;
			left = DURATION;

			scale.set( startScale = Camera.main.zoom / camera().zoom );

		}

		@Override
		public void update() {
			super.update();

			if ((left -= Game.elapsed) <= 0) {

				visible =
				active =
					false;
				if (emitter != null) emitter.on = false;

			} else {
				float p = left / DURATION;
				scale.set( startScale * (float)Math.sqrt( p ) );

				x = startX*p + endX*(1-p);
				y = startY*p + endY*(1-p);
			}
		}
	}

	public static CellSelector.Listener zapper = new CellSelector.Listener() {

		@Override
		public void onSelect(Integer target) {
			if (target != null) {
				//FIXME this safety check shouldn't be necessary
				//it would be better to eliminate the curItem static variable.
				final Ballistica[] lasers;
				int w = Dungeon.level.width();
				if (Statistics.card10){
					switch (laserPlacement(heroine.pos, target)) {
						case VERTICAL:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - w, target - w, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 2*w, target - 2*w, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + w, target + w, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + 2*w, target + 2*w, Ballistica.WONT_STOP)};
							break;
						default:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 1, target - 1, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 2, target - 2, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + 1, target + 1, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + 2, target + 2, Ballistica.WONT_STOP)};
							break;
					}
				}
				else {
					switch (laserPlacement(heroine.pos, target)) {
						case VERTICAL:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - w, target - w, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + w, target + w, Ballistica.WONT_STOP)};
							break;
						default:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 1, target - 1, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + 1, target + 1, Ballistica.WONT_STOP)};
							break;
					}
				}
				final Ballistica beam = lasers[0];
				int cell = beam.collisionPos;

				if (target == heroine.pos || cell == heroine.pos) {
					GLog.i(Messages.get(Wand.class, "self_target"));
					return;
				}

				heroine.sprite.zap(cell);
				heroine.busy();
				marisaBombFx(beam, new Callback() {
					public void call() {
						for (Ballistica laser : lasers) {

							boolean terrainAffected = false;
							ArrayList<Char> chars = new ArrayList<>();

							for (int c : laser.subPath(1, laser.dist)) {

								Char ch;
								if ((ch = Actor.findChar(c)) != null) {
									chars.add(ch);
								}

								if (Dungeon.level.flamable[c]) {

									Dungeon.level.destroy(c);
									GameScene.updateMap(c);
									terrainAffected = true;

								}

								CellEmitter.center(c).burst(PurpleParticle.BURST, Random.IntRange(1, 2));
							}

							if (terrainAffected) {
								Dungeon.observe();
							}
							for (Char ch : chars) {
								ch.damage(laserDamageRoll(), this);
							}
						}
						heroine.spendAndNext(TICK);
					}

				});

			}
		}

		@Override
		public String prompt() {
			return Messages.get(Wand.class, "prompt");
		}
	};

	private static void marisaBombFx(Ballistica beam, Callback callback) {
		int cell = beam.path.get(beam.dist);
		if (Statistics.card10){
			heroine.sprite.parent.add(new Beam.MasterSparkWideRay(heroine.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cell )));
		}
		else {
			heroine.sprite.parent.add(new Beam.MasterSparkRay(heroine.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cell )));
		}
		callback.call();
	}

	private static int minLaserDamage(){
		return 4 + Dungeon.floor*2 + ((Dungeon.floor) / 30) * 20; // After the 29th floor there is a sharp increase in damage
	}
	private static int maxLaserDamage(){
		return 8 + (Dungeon.floor + 1)*6 + ((Dungeon.floor) / 30) * 50;
	}
	private static int laserDamageRoll(){
		int dmg = Random.NormalIntRange(minLaserDamage(), maxLaserDamage());

		Char enemy = heroine.enemy();
		if (heroine.buff(WandZeroDamage.class) != null){
			dmg *= 0f;
		}
		if (heroine.buff(MagicBuff.class) != null) {
			dmg *= 1.25f;
		}
		if (Statistics.card31) {
			dmg *= 1.25f;
		}

		if (Dungeon.isChallenged(Challenges.LUNATIC_PERFECT) && Statistics.lifelose || Dungeon.isChallenged(Challenges.LUNATIC_PERFECT) && Statistics.spellcarduse){
			dmg *= 0.8f;
		}

		if (Statistics.wand_power_up == 1) {
			dmg *= 1.06f;
		}
		if (Statistics.wand_power_up == 2) {
			dmg *= 1.12f;
		}
		if (Statistics.wand_power_up == 3) {
			dmg *= 1.18f;
		}
		if (Statistics.wand_power_up == 4) {
			dmg *= 1.24f;
		}
		if (Statistics.wand_power_up >= 5) {
			dmg *= 1.3f;
		} //potion of enlightenment

		if (Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.MINIBOSS) ||
				Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.BOSS)){
			dmg *= 2f;
		}

		return dmg;
	}
	private enum LaserPlacement{
		VERTICAL,

		HORIZONTAL;
	}
	private static LaserPlacement laserPlacement(int from, int to){

		int w = Dungeon.level.width();

		int x0 = from % w;
		int x1 = to % w;
		int y0 = from / w;
		int y1 = to / w;

		int dx = x1 - x0;
		int dy = y1 - y0;

		int dxAbs = Math.abs( dx );
		int dyAbs = Math.abs( dy );

		if (dyAbs > dxAbs){
			return LaserPlacement.HORIZONTAL;
		}
		else {
			return LaserPlacement.VERTICAL;
		}
	}
	// Sanae's ability
	public static void sanaeBomb() {
		final int initDamage = 10 + Dungeon.floor*4;
		float damage = initDamage;
		int targets = 0;
		float decreaseFact = 0.8f;
		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Dungeon.level.heroFOV[mob.pos] && (Dungeon.level.map[mob.pos] == Terrain.GRASS || Dungeon.level.map[mob.pos] == Terrain.WATER)) {
				targets++;
				damage = damage * (1 + decreaseFact * (targets - 1)) / targets; // if the enemy is one, he will receive 100% damage, if 2, then 90%, if 3, then 78%, etc.
			}
			if (damage < initDamage * 0.2f) {
				damage = initDamage * 0.2f; // Min damage - 20% of the initial value
				break;
			}
		}
		int finalDamage = (int)damage;
		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Dungeon.level.heroFOV[mob.pos] && (Dungeon.level.map[mob.pos] == Terrain.GRASS || Dungeon.level.map[mob.pos] == Terrain.WATER)) {
				if (Dungeon.level.map[mob.pos] == Terrain.GRASS){
					Buff.prolong(mob, Roots.class, 10f);
					if (Statistics.card14){
						Buff.affect(mob, Poison.class).set(5 + Dungeon.floor*2/3);
					}
				}
				if (Dungeon.level.map[mob.pos] == Terrain.WATER) {
					Buff.prolong(mob, Vertigo.class, 10f);
					if (Statistics.card14){
						Buff.prolong(mob, Terror.class, 10f);
					}
				}

				mob.damage(finalDamage, heroine);
			}
		}
	}
	public static void sakuyaBomb(){
		TimekeepersHourglass tH = heroine.belongings.getItem(TimekeepersHourglass.class);
		if (tH != null){
			tH.charge = Math.min(tH.charge + 3, tH.getChargeCap());
			Item.updateQuickslot();
		}
		if (Statistics.card12) {
			Buff.affect(heroine, Swiftthistle.TimeBubble.class).set(9f);
		}
	}
}
