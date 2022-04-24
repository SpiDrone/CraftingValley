package net.mcreator.starcraftvalley.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.Map;
import java.util.HashMap;

public class StarminGuiB2Procedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency entity for procedure StarminGuiB2!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency guistate for procedure StarminGuiB2!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double day = 0;
		double season = 0;
		{
			double _setval = new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:coins");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText());
			entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.Coins = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
