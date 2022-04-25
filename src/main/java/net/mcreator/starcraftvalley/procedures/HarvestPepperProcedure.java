package net.mcreator.starcraftvalley.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.item.PepperItem;
import net.mcreator.starcraftvalley.block.PepperS2Block;
import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.Map;

public class HarvestPepperProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency world for procedure HarvestPepper!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency x for procedure HarvestPepper!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency y for procedure HarvestPepper!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency z for procedure HarvestPepper!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency entity for procedure HarvestPepper!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(PepperItem.block);
			_setstack.setCount((int) 1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
		if (Math.random() < 0.03) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(PepperItem.block);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			{
				double _setval = ((entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new StarcraftvalleyModVariables.PlayerVariables())).FarmingXp + 3);
				entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FarmingXp = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		{
			BlockPos _bp = new BlockPos(x, y, z);
			BlockState _bs = PepperS2Block.block.getDefaultState();
			BlockState _bso = world.getBlockState(_bp);
			TileEntity _te = world.getTileEntity(_bp);
			CompoundNBT _bnbt = null;
			if (_te != null) {
				_bnbt = _te.write(new CompoundNBT());
				_te.remove();
			}
			world.setBlockState(_bp, _bs, 3);
			if (_bnbt != null) {
				_te = world.getTileEntity(_bp);
				if (_te != null) {
					try {
						_te.read(_bso, _bnbt);
					} catch (Exception ignored) {
					}
				}
			}
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos(x, y, z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putDouble("growthStage", 3);
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		{
			double _setval = Math.ceil(((entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new StarcraftvalleyModVariables.PlayerVariables())).FarmingXp + 9)
					* (1 + (entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new StarcraftvalleyModVariables.PlayerVariables())).FarmingPrestige / 20));
			entity.getCapability(StarcraftvalleyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.FarmingXp = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
