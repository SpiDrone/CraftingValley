package net.mcreator.starcraftvalley.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;

public class StarminGuiOverlayProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure StarminGuiOverlay!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SproutModVariables.PlayerVariables())).TempOverlay) {
			{
				boolean _setval = (false);
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.TempOverlay = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				boolean _setval = (true);
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.TempOverlay = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
