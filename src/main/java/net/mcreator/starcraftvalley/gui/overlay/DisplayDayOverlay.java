
package net.mcreator.starcraftvalley.gui.overlay;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.starcraftvalley.procedures.DisplayConditionsProcedure;
import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

@Mod.EventBusSubscriber
public class DisplayDayOverlay {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGameOverlayEvent.Post event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int w = event.getWindow().getScaledWidth();
			int h = event.getWindow().getScaledHeight();
			int posX = w / 2;
			int posY = h / 2;
			World _world = null;
			double _x = 0;
			double _y = 0;
			double _z = 0;
			PlayerEntity entity = Minecraft.getInstance().player;
			if (entity != null) {
				_world = entity.world;
				_x = entity.getPosX();
				_y = entity.getPosY();
				_z = entity.getPosZ();
			}
			World world = _world;
			double x = _x;
			double y = _y;
			double z = _z;
			if (DisplayConditionsProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
					(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						"D " + (int) (StarcraftvalleyModVariables.MapVariables.get(world).day) + "", posX + -212, posY + -119, -1);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						"Y: " + (int) (StarcraftvalleyModVariables.MapVariables.get(world).year) + "", posX + -212, posY + -97, -6750208);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						"S " + (int) (StarcraftvalleyModVariables.MapVariables.get(world).season) + "", posX + -212, posY + -108, -1);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						"Coins: " + (int) ((entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new StarcraftvalleyModVariables.PlayerVariables())).Coins) + "",
						posX + -212, posY + -86, -1);
			}
		}
	}
}
