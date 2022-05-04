package net.mcreator.starcraftvalley.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;

public class CropQualityProcedure {

	public static String executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure CropQuality!");
			return "";
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack item = ItemStack.EMPTY;
		String quality = "";
		if (Math.random() < 0.05 && (entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SproutModVariables.PlayerVariables())).FarmingLvl >= 5) {
			quality = "\u00A7d\u2605\u2605\u2605";
		} else if (Math.random() < 0.08) {
			quality = "\u00A76\u2605\u2605";
		} else if (Math.random() < 0.15) {
			quality = "\u00A77\u2605";
		}
		return quality;
	}
}
