package net.mcreator.starcraftvalley.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.Map;

public class StarminGuiOverlayProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency entity for procedure StarminGuiOverlay!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new StarcraftvalleyModVariables.PlayerVariables())).TempOverlay) {
			{
				boolean _setval = (false);
				entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.TempOverlay = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				boolean _setval = (true);
				entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.TempOverlay = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
