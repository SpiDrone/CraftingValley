package net.mcreator.starcraftvalley.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.button.CheckboxButton;

import net.mcreator.starcraftvalley.item.StrawberrySeedsItem;
import net.mcreator.starcraftvalley.item.PotatoSeedsItem;
import net.mcreator.starcraftvalley.item.ParsnipSeedsItem;
import net.mcreator.starcraftvalley.item.CauliflowerSeedsItem;
import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class ShopFillProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure ShopFill!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				SproutMod.LOGGER.warn("Failed to load dependency guistate for procedure ShopFill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double mult = 0;
		ItemStack item = ItemStack.EMPTY;
		if (new Object() {
			public boolean getValue() {
				CheckboxButton checkbox = (CheckboxButton) guistate.get("checkbox:a");
				if (checkbox != null) {
					return checkbox.isChecked();
				}
				return false;
			}
		}.getValue()) {
			mult = 5;
		} else {
			mult = 1;
		}
		if ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SproutModVariables.PlayerVariables())).Coins >= 32
				* mult) {
			item = new ItemStack(StrawberrySeedsItem.block);
			((item)).setDisplayName(new StringTextComponent(((item).getDisplayName().getString() + " \u00A76[32 Coins]")));
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = (item);
						_setstack.setCount((int) mult);
						((Slot) ((Map) invobj).get((int) (0))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
		} else {
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (0))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
		}
		if ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SproutModVariables.PlayerVariables())).Coins >= 20
				* mult) {
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = new ItemStack(PotatoSeedsItem.block);
						_setstack.setCount((int) mult);
						((Slot) ((Map) invobj).get((int) (1))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
		} else {
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (1))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
		}
		if ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SproutModVariables.PlayerVariables())).Coins >= 45
				* mult) {
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = new ItemStack(CauliflowerSeedsItem.block);
						_setstack.setCount((int) mult);
						((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
		} else {
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (2))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
		}
		if ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SproutModVariables.PlayerVariables())).Coins >= 15
				* mult) {
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = new ItemStack(ParsnipSeedsItem.block);
						_setstack.setCount((int) mult);
						((Slot) ((Map) invobj).get((int) (3))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
		} else {
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (3))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
		}
	}
}
