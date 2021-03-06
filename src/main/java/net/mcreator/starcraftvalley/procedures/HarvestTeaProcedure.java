package net.mcreator.starcraftvalley.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.item.TeaLeavesItem;
import net.mcreator.starcraftvalley.block.TeaS3Block;
import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class HarvestTeaProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SproutMod.LOGGER.warn("Failed to load dependency world for procedure HarvestTea!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SproutMod.LOGGER.warn("Failed to load dependency x for procedure HarvestTea!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SproutMod.LOGGER.warn("Failed to load dependency y for procedure HarvestTea!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SproutMod.LOGGER.warn("Failed to load dependency z for procedure HarvestTea!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure HarvestTea!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack item = ItemStack.EMPTY;
		if (!world.isRemote()) {
			item = new ItemStack(TeaLeavesItem.block);
			(item).getOrCreateTag().putString("quality",
					CropQualityProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
							(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)));
			if (((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
					? ((ServerPlayerEntity) entity).getAdvancements()
							.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
									.getAdvancement(new ResourceLocation("sprout:professionally_picked")))
							.isDone()
					: false) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = (item);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			} else {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (item));
					entityToSpawn.setPickupDelay((int) 2);
					world.addEntity(entityToSpawn);
				}
			}
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = TeaS3Block.block.getDefaultState();
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
					_tileEntity.getTileData().putDouble("growthStage", 18);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			{
				double _setval = Math.ceil(((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SproutModVariables.PlayerVariables())).FarmingXp + 2)
						* (1 + (entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SproutModVariables.PlayerVariables())).FarmingPrestige / 20));
				entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FarmingXp = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity)
						.sendStatusMessage(new StringTextComponent(("" + (entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SproutModVariables.PlayerVariables())).FarmingXp)), (false));
			}
		}
	}
}
