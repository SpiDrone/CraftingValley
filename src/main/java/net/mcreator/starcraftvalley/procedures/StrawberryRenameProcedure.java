package net.mcreator.starcraftvalley.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.item.StrawberrySeedsItem;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;

public class StrawberryRenameProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure StrawberryRename!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SproutMod.LOGGER.warn("Failed to load dependency itemstack for procedure StrawberryRename!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double stackSize = 0;
		if ((itemstack.getDisplayName().getString())
				.equals(new ItemStack(StrawberrySeedsItem.block).getDisplayName().getString() + " \u00A76[32 Coins]")) {
			stackSize = ((itemstack).getCount());
			(itemstack).shrink((int) stackSize);
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(StrawberrySeedsItem.block);
				_setstack.setCount((int) stackSize);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
	}
}
