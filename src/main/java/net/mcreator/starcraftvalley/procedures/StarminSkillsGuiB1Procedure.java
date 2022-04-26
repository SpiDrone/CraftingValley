package net.mcreator.starcraftvalley.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;
import java.util.HashMap;

public class StarminSkillsGuiB1Procedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure StarminSkillsGuiB1!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				SproutMod.LOGGER.warn("Failed to load dependency guistate for procedure StarminSkillsGuiB1!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		if (!(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:fl");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("")) {
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
						TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:fl");
						if (_tf != null) {
							return _tf.getText();
						}
						return "";
					}
				}.getText());
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FarmingLvl = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (!(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:fx");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("")) {
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
						TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:fx");
						if (_tf != null) {
							return _tf.getText();
						}
						return "";
					}
				}.getText());
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FarmingXp = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (!(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:fp");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("")) {
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
						TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:fp");
						if (_tf != null) {
							return _tf.getText();
						}
						return "";
					}
				}.getText());
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FarmingPrestige = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
