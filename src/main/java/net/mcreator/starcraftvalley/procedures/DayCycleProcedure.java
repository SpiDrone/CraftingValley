package net.mcreator.starcraftvalley.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class DayCycleProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SproutMod.LOGGER.warn("Failed to load dependency world for procedure DayCycle!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (SproutModVariables.MapVariables.get(world).TotalDays < world.getWorldInfo().getDayTime() / 24000) {
			if (SproutModVariables.MapVariables.get(world).day >= 28) {
				SproutModVariables.MapVariables.get(world).season = (SproutModVariables.MapVariables.get(world).season + 1);
				SproutModVariables.MapVariables.get(world).syncData(world);
				SproutModVariables.MapVariables.get(world).day = 1;
				SproutModVariables.MapVariables.get(world).syncData(world);
				if (SproutModVariables.MapVariables.get(world).season >= 5) {
					SproutModVariables.MapVariables.get(world).year = (SproutModVariables.MapVariables.get(world).year + 1);
					SproutModVariables.MapVariables.get(world).syncData(world);
					SproutModVariables.MapVariables.get(world).season = 0;
					SproutModVariables.MapVariables.get(world).syncData(world);
				}
			} else {
				SproutModVariables.MapVariables.get(world).day = (SproutModVariables.MapVariables.get(world).day + 1);
				SproutModVariables.MapVariables.get(world).syncData(world);
			}
			SproutModVariables.MapVariables.get(world).TotalDays = (SproutModVariables.MapVariables.get(world).TotalDays + 1);
			SproutModVariables.MapVariables.get(world).syncData(world);
			{
				List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
				for (Entity entityiterator : _players) {
					if ((entityiterator.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin != 0) {
						{
							double _setval = ((entityiterator.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new SproutModVariables.PlayerVariables())).Coins
									+ (entityiterator.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new SproutModVariables.PlayerVariables())).shippingBin);
							entityiterator.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Coins = _setval;
								capability.syncPlayerVariables(entityiterator);
							});
						}
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator)
									.sendStatusMessage(
											new StringTextComponent(("\u00A72[\u00A76Shipping Bin\u00A72]\u00A7f : \u00A7e"
													+ (entityiterator.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
															.orElse(new SproutModVariables.PlayerVariables())).shippingBin
													+ " Coins Added")),
											(false));
						}
						{
							double _setval = 0;
							entityiterator.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.shippingBin = _setval;
								capability.syncPlayerVariables(entityiterator);
							});
						}
					}
				}
			}
		}
	}
}
