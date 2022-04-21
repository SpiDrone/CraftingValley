package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.Property;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.block.WetEarthBlock;
import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class CropDecayProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency world for procedure CropDecay!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency x for procedure CropDecay!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency y for procedure CropDecay!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency z for procedure CropDecay!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (BlockTags.getCollection().getTagByID(new ResourceLocation("forge:spring_crop"))
				.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:summer_crop"))
						.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:fall_crop"))
						.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:winter_crop"))
						.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())) {
			if (!(StarcraftvalleyModVariables.MapVariables.get(world).season == 0
					&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:spring_crop"))
							.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())
					|| StarcraftvalleyModVariables.MapVariables.get(world).season == 1
							&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:summer_crop"))
									.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())
					|| StarcraftvalleyModVariables.MapVariables.get(world).season == 2
							&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:fall_crop"))
									.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())
					|| StarcraftvalleyModVariables.MapVariables.get(world).season == 3
							&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:winter_crop"))
									.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock()))) {
				{
					BlockPos _bp = new BlockPos((int) x, (int) (y + 1), (int) z);
					BlockState _bs = Blocks.DEAD_BUSH.getDefaultState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
						if (_property != null && _bs.get(_property) != null)
							try {
								_bs = _bs.with(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlockState(_bp, _bs, 3);
				}
			}
		}
		if (world.getWorldInfo().isRaining() && world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z))) {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
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
				world.setBlockState(_bp, _bs, 3);
			}

			PlaceWateredProcedure.executeProcedure(Stream
					.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
							new AbstractMap.SimpleEntry<>("z", z))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
		}
	}
}
