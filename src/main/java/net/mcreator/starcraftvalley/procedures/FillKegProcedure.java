package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tags.ItemTags;
import net.minecraft.state.Property;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.item.CoffeeBeanItem;
import net.mcreator.starcraftvalley.block.KegProcessingBlock;
import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.Map;

public class FillKegProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency world for procedure FillKeg!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency x for procedure FillKeg!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency y for procedure FillKeg!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency z for procedure FillKeg!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency entity for procedure FillKeg!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack item = ItemStack.EMPTY;
		double hand = 0;
		double removeItem = 0;
		if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:keg"))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
			hand = 1;
			item = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
		} else if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:keg"))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem())) {
			hand = 2;
			item = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
		}
		if (hand != 0) {
			if ((item).getItem() == CoffeeBeanItem.block && ((item)).getCount() >= 5) {
				{
					BlockPos _bp = new BlockPos(x, y, z);
					BlockState _bs = KegProcessingBlock.block.getDefaultState();
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
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos(x, y, z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("crop", "coffee");
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos(x, y, z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("dayFilled", StarcraftvalleyModVariables.MapVariables.get(world).TotalDays);
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				removeItem = 5;
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
				if (hand == 1) {
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).shrink((int) removeItem);
				} else {
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)).shrink((int) removeItem);
				}
			}
		}
	}
}
