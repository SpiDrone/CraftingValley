package net.mcreator.starcraftvalley.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.button.CheckboxButton;

import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;
import java.util.HashMap;

public class Purchase03Procedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure Purchase03!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				SproutMod.LOGGER.warn("Failed to load dependency guistate for procedure Purchase03!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		if (new Object() {
			public boolean getValue() {
				CheckboxButton checkbox = (CheckboxButton) guistate.get("checkbox:a");
				if (checkbox != null) {
					return checkbox.isChecked();
				}
				return false;
			}
		}.getValue()) {
			{
				double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SproutModVariables.PlayerVariables())).Coins - 60);
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Coins = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SproutModVariables.PlayerVariables())).Coins - 15);
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Coins = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
