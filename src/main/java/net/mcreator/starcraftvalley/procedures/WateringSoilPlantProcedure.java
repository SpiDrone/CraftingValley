package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.block.WetEarthFBlock;
import net.mcreator.starcraftvalley.block.WetEarthBlock;
import net.mcreator.starcraftvalley.block.TilledEarthBlock;
import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;

public class WateringSoilPlantProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SproutMod.LOGGER.warn("Failed to load dependency world for procedure WateringSoilPlant!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SproutMod.LOGGER.warn("Failed to load dependency x for procedure WateringSoilPlant!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SproutMod.LOGGER.warn("Failed to load dependency y for procedure WateringSoilPlant!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SproutMod.LOGGER.warn("Failed to load dependency z for procedure WateringSoilPlant!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == TilledEarthBlock.block) {
			if (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos(x, y, z), "fertilizer") == 0) {
				{
					BlockPos _bp = new BlockPos(x, y, z);
					BlockState _bs = WetEarthBlock.block.getDefaultState();
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
			} else {
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
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(x, y, z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("dayWatered", SproutModVariables.MapVariables.get(world).day);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			for (int index0 = 0; index0 < (int) (10); index0++) {
				world.addParticle(ParticleTypes.SPLASH, (x + 0.5), (y + 1), (z + 0.5), 0, 1, 0);
			}
		} else if ((world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == TilledEarthBlock.block) {
			if (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos(x, y - 1, z), "fertilizer") == 0) {
				{
					BlockPos _bp = new BlockPos(x, y - 1, z);
					BlockState _bs = WetEarthBlock.block.getDefaultState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
						if (_property != null && _bs.get(_property) != null)
							try {
								_bs = _bs.with(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
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
			} else {
				{
					BlockPos _bp = new BlockPos(x, y - 1, z);
					BlockState _bs = WetEarthFBlock.block.getDefaultState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
						if (_property != null && _bs.get(_property) != null)
							try {
								_bs = _bs.with(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
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
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(x, y - 1, z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("dayWatered", SproutModVariables.MapVariables.get(world).day);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			for (int index1 = 0; index1 < (int) (10); index1++) {
				world.addParticle(ParticleTypes.SPLASH, (x + 0.5), y, (z + 0.5), 0, 1, 0);
			}
		} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == WetEarthBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == WetEarthFBlock.block) {
			for (int index2 = 0; index2 < (int) (10); index2++) {
				world.addParticle(ParticleTypes.SPLASH, (x + 0.5), (y + 1), (z + 0.5), 0, 1, 0);
			}
		} else if ((world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == WetEarthBlock.block
				|| (world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == WetEarthFBlock.block) {
			for (int index3 = 0; index3 < (int) (10); index3++) {
				world.addParticle(ParticleTypes.SPLASH, (x + 0.5), y, (z + 0.5), 0, 1, 0);
			}
		}
	}
}
