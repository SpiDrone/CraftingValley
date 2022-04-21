package net.mcreator.starcraftvalley.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

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
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency world for procedure DayCycle!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (StarcraftvalleyModVariables.MapVariables.get(world).TotalDays < world.getWorldInfo().getDayTime() / 24000) {
			if (StarcraftvalleyModVariables.MapVariables.get(world).day == 28) {
				StarcraftvalleyModVariables.MapVariables.get(world).season = (StarcraftvalleyModVariables.MapVariables.get(world).season + 1);
				StarcraftvalleyModVariables.MapVariables.get(world).syncData(world);
				StarcraftvalleyModVariables.MapVariables.get(world).day = 1;
				StarcraftvalleyModVariables.MapVariables.get(world).syncData(world);
				if (StarcraftvalleyModVariables.MapVariables.get(world).season == 5) {
					StarcraftvalleyModVariables.MapVariables.get(world).year = (StarcraftvalleyModVariables.MapVariables.get(world).year + 1);
					StarcraftvalleyModVariables.MapVariables.get(world).syncData(world);
					StarcraftvalleyModVariables.MapVariables.get(world).season = 0;
					StarcraftvalleyModVariables.MapVariables.get(world).syncData(world);
				}
			} else {
				StarcraftvalleyModVariables.MapVariables.get(world).day = (StarcraftvalleyModVariables.MapVariables.get(world).day + 1);
				StarcraftvalleyModVariables.MapVariables.get(world).syncData(world);
			}
			StarcraftvalleyModVariables.MapVariables.get(world).TotalDays = (StarcraftvalleyModVariables.MapVariables.get(world).TotalDays + 1);
			StarcraftvalleyModVariables.MapVariables.get(world).syncData(world);
		} else if (StarcraftvalleyModVariables.MapVariables.get(world).TotalDays > world.getWorldInfo().getDayTime() / 24000 + 48000) {
			{
				List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
				for (Entity entityiterator : _players) {
					if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
						((PlayerEntity) entityiterator).sendStatusMessage(new StringTextComponent(
								"StarCraftValley : You should not be seeing this message.  Somebody has been tampering with Time.  Because of the way Minecrafts /Time command works.  You've just changed the day count, I suggest you starcraft query the totaldays.  and do /time set xd.  With X being the total days."),
								(false));
					}
				}
			}
		}
	}
}
