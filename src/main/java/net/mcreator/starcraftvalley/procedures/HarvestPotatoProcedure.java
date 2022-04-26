package net.mcreator.starcraftvalley.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;

public class HarvestPotatoProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SproutMod.LOGGER.warn("Failed to load dependency world for procedure HarvestPotato!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SproutMod.LOGGER.warn("Failed to load dependency x for procedure HarvestPotato!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SproutMod.LOGGER.warn("Failed to load dependency y for procedure HarvestPotato!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SproutMod.LOGGER.warn("Failed to load dependency z for procedure HarvestPotato!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure HarvestPotato!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(Items.POTATO);
			_setstack.setCount((int) 1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
		for (int index0 = 0; index0 < (int) (100); index0++) {
			if (Math.random() < 0.2) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.POTATO);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).FarmingXp + 1);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.FarmingXp = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				break;
			}
		}
		if (Math.random() < 0.01) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(Items.POISONOUS_POTATO);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		world.destroyBlock(new BlockPos(x, y, z), false);
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos(x, y, z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putDouble("growthStage", 4);
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		{
			double _setval = Math.ceil(((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new SproutModVariables.PlayerVariables())).FarmingXp + 11)
					* (1 + (entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).FarmingPrestige / 20));
			entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.FarmingXp = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
