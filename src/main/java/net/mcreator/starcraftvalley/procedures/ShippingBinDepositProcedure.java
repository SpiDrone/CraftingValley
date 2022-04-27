package net.mcreator.starcraftvalley.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.starcraftvalley.item.WheatItem;
import net.mcreator.starcraftvalley.item.TeaLeavesItem;
import net.mcreator.starcraftvalley.item.TeaItem;
import net.mcreator.starcraftvalley.item.StrawberryItem;
import net.mcreator.starcraftvalley.item.PotatoItem;
import net.mcreator.starcraftvalley.item.PepperItem;
import net.mcreator.starcraftvalley.item.ParsnipItem;
import net.mcreator.starcraftvalley.item.GrapesItem;
import net.mcreator.starcraftvalley.item.CoffeeItem;
import net.mcreator.starcraftvalley.item.CoffeeBeanItem;
import net.mcreator.starcraftvalley.item.CauliflowerItem;
import net.mcreator.starcraftvalley.item.CabbageRedItem;
import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.Map;

public class ShippingBinDepositProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SproutMod.LOGGER.warn("Failed to load dependency entity for procedure ShippingBinDeposit!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack item = ItemStack.EMPTY;
		double hand = 0;
		double repeat = 0;
		if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:sellable"))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
			item = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
			hand = 1;
		} else if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:sellable"))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem())) {
			item = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
			hand = 2;
		}
		for (int index0 = 0; index0 < (int) (2); index0++) {
			if (StrawberryItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 85);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (CauliflowerItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 150);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (PotatoItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 50);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (ParsnipItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 45);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (CoffeeBeanItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 5);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (WheatItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 35);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (GrapesItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 80);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (TeaLeavesItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 10);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (PepperItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 66);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (CabbageRedItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 130);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (CoffeeItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 20);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (TeaItem.block == (item).getItem()) {
				{
					double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SproutModVariables.PlayerVariables())).shippingBin + 30);
					entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.shippingBin = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (hand == 1) {
				(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).shrink((int) 1);
			} else {
				(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)).shrink((int) 1);
				break;
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:sellable"))
					.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem())) {
				item = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
				hand = 2;
			} else {
				break;
			}
		}
	}
}
