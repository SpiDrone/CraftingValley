package net.mcreator.starcraftvalley.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.Map;

public class DisplayConditionsProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency entity for procedure DisplayConditions!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new StarcraftvalleyModVariables.PlayerVariables())).TempOverlay) {
			return true;
		}
		return false;
	}
}
