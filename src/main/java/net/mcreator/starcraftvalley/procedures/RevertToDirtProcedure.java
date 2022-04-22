package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.Map;

public class RevertToDirtProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency world for procedure RevertToDirt!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency x for procedure RevertToDirt!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency y for procedure RevertToDirt!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency z for procedure RevertToDirt!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (world.getBlockState(new BlockPos(x, y + 1, z)).isSolidSide(world, new BlockPos(x, y + 1, z), Direction.DOWN) && !BlockTags.getCollection()
				.getTagByID(new ResourceLocation("forge:trellis_crop")).contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())) {
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = Blocks.DIRT.getDefaultState();
				world.setBlockState(_bp, _bs, 3);
			}
		}
	}
}
