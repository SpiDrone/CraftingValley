package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.block.WetEarthFBlock;
import net.mcreator.starcraftvalley.block.WetEarthBlock;
import net.mcreator.starcraftvalley.block.TilledEarthBlock;
import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class CropDecayProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SproutMod.LOGGER.warn("Failed to load dependency world for procedure CropDecay!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SproutMod.LOGGER.warn("Failed to load dependency x for procedure CropDecay!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SproutMod.LOGGER.warn("Failed to load dependency y for procedure CropDecay!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SproutMod.LOGGER.warn("Failed to load dependency z for procedure CropDecay!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (BlockTags.getCollection().getTagByID(new ResourceLocation("forge:spring_crop"))
				.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:summer_crop"))
						.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:fall_crop"))
						.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:winter_crop"))
						.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())) {
			if (!(SproutModVariables.MapVariables.get(world).season == 0
					&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:spring_crop"))
							.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())
					|| SproutModVariables.MapVariables.get(world).season == 1
							&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:summer_crop"))
									.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())
					|| SproutModVariables.MapVariables.get(world).season == 2 && BlockTags.getCollection()
							.getTagByID(new ResourceLocation("forge:fall_crop")).contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())
					|| SproutModVariables.MapVariables.get(world).season == 3
							&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:winter_crop"))
									.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock()))) {
				{
					BlockPos _bp = new BlockPos(x, y + 1, z);
					BlockState _bs = Blocks.DEAD_BUSH.getDefaultState();
					world.setBlockState(_bp, _bs, 3);
				}
			}
		}
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == TilledEarthBlock.block && world.getWorldInfo().isRaining()
				&& world.canBlockSeeSky(new BlockPos(x, y, z))) {
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
			PlaceWateredProcedure.executeProcedure(Stream
					.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
							new AbstractMap.SimpleEntry<>("z", z))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
		}
	}
}
