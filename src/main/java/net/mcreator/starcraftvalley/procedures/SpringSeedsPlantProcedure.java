package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.item.StrawberrySeedsItem;
import net.mcreator.starcraftvalley.item.PotatoSeedsItem;
import net.mcreator.starcraftvalley.item.CauliflowerSeedsItem;
import net.mcreator.starcraftvalley.block.WetEarthBlock;
import net.mcreator.starcraftvalley.block.TilledEarthBlock;
import net.mcreator.starcraftvalley.block.StrawberryS1Block;
import net.mcreator.starcraftvalley.block.PotatoS1Block;
import net.mcreator.starcraftvalley.block.CauliflowerS1Block;
import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class SpringSeedsPlantProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency world for procedure SpringSeedsPlant!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency x for procedure SpringSeedsPlant!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency y for procedure SpringSeedsPlant!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency z for procedure SpringSeedsPlant!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency entity for procedure SpringSeedsPlant!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency itemstack for procedure SpringSeedsPlant!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (StarcraftvalleyModVariables.MapVariables.get(world).season == 0
				&& ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == TilledEarthBlock.block
						|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == WetEarthBlock.block)) {
			if (PotatoSeedsItem.block == itemstack.getItem()) {
				world.setBlockState(new BlockPos(x, y + 1, z), PotatoS1Block.block.getDefaultState(), 3);
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos(x, y + 1, z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("crop", "potato");
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (StrawberrySeedsItem.block == itemstack.getItem()) {
				world.setBlockState(new BlockPos(x, y + 1, z), StrawberryS1Block.block.getDefaultState(), 3);
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos(x, y + 1, z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("crop", "strawberry");
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (CauliflowerSeedsItem.block == itemstack.getItem()) {
				world.setBlockState(new BlockPos(x, y + 1, z), CauliflowerS1Block.block.getDefaultState(), 3);
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos(x, y + 1, z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("crop", "cauliflower");
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayerEntity) {
						return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
					} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
						NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
								.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
						return _npi != null && _npi.getGameType() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				(itemstack).shrink((int) 1);
			}
			CropPlantedProcedure.executeProcedure(Stream
					.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
							new AbstractMap.SimpleEntry<>("y", (y + 1)), new AbstractMap.SimpleEntry<>("z", z))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
		}
	}
}
