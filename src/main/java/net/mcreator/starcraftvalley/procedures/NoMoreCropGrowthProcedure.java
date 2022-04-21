package net.mcreator.starcraftvalley.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraft.world.IWorld;

import java.util.Map;
import java.util.HashMap;

public class NoMoreCropGrowthProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onCropGrowPre(BlockEvent.CropGrowEvent.Pre event) {
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", event.getPos().getX());
			dependencies.put("y", event.getPos().getY());
			dependencies.put("z", event.getPos().getZ());
			dependencies.put("blockstate", event.getState());
			dependencies.put("world", world);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("event") != null) {
			Object _obj = dependencies.get("event");
			if (_obj instanceof Event) {
				Event _evt = (Event) _obj;
				if (_evt.hasResult())
					_evt.setResult(Event.Result.DENY);
			}
		}
	}
}
