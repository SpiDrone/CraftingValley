package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.item.Fertilizerspeed1Item;
import net.mcreator.starcraftvalley.item.FertilizerSpeed2Item;
import net.mcreator.starcraftvalley.block.WetEarthFBlock;
import net.mcreator.starcraftvalley.block.WetEarthBlock;
import net.mcreator.starcraftvalley.block.TilledEarthBlock;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;

public class FertilizerUseProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SproutMod.LOGGER.warn("Failed to load dependency world for procedure FertilizerUse!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SproutMod.LOGGER.warn("Failed to load dependency x for procedure FertilizerUse!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SproutMod.LOGGER.warn("Failed to load dependency y for procedure FertilizerUse!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SproutMod.LOGGER.warn("Failed to load dependency z for procedure FertilizerUse!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure FertilizerUse!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SproutMod.LOGGER.warn("Failed to load dependency itemstack for procedure FertilizerUse!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == TilledEarthBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == WetEarthBlock.block) {
			if (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos(x, y, z), "fertilizer") == 0) {
				if (itemstack.getItem() == Fertilizerspeed1Item.block) {
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 5, 0.5, 0.5, 0.5, 1);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos(x, y, z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("fertilizer", 1);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else if (itemstack.getItem() == FertilizerSpeed2Item.block) {
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 5, 0.5, 0.5, 0.5, 1);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos(x, y, z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("fertilizer", 2);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
				if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == WetEarthBlock.block) {
					{
						BlockPos _bp = new BlockPos(x, y, z);
						BlockState _bs = WetEarthFBlock.block.getDefaultState();
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
				}
			}
		} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == WetEarthFBlock.block) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(
						new StringTextComponent("\u00A72[\u00A76Fertilizer\u00A72]\u00A7f : \u00A7eThis Soil Has Already Been Fertizlied"), (false));
			}
		}
	}
}
