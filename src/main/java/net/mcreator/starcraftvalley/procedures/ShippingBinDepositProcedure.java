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
		double sellPrice = 0;
		if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:sellable"))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem())
				|| ItemTags.getCollection().getTagByID(new ResourceLocation("forge:sellable"))
						.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
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
					sellPrice = 85;
				} else if (CauliflowerItem.block == (item).getItem()) {
					sellPrice = 150;
				} else if (PotatoItem.block == (item).getItem()) {
					sellPrice = 50;
				} else if (ParsnipItem.block == (item).getItem()) {
					sellPrice = 45;
				} else if (CoffeeBeanItem.block == (item).getItem()) {
					sellPrice = 5;
				} else if (WheatItem.block == (item).getItem()) {
					sellPrice = 35;
				} else if (GrapesItem.block == (item).getItem()) {
					sellPrice = 80;
				} else if (TeaLeavesItem.block == (item).getItem()) {
					sellPrice = 10;
				} else if (PepperItem.block == (item).getItem()) {
					sellPrice = 66;
				} else if (CabbageRedItem.block == (item).getItem()) {
					sellPrice = 130;
				}
				if (CoffeeItem.block == (item).getItem()) {
					sellPrice = 20;
				} else if (TeaItem.block == (item).getItem()) {
					sellPrice = 30;
				}
				if (((item).getOrCreateTag().getString("quality")).equals("Quality: \u00A77\u2605")) {
					{
						double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SproutModVariables.PlayerVariables())).shippingBin + Math.ceil(1.2 * sellPrice));
						entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.shippingBin = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else if (((item).getOrCreateTag().getString("quality")).equals("Quality: \u00A76\u2605\u2605")) {
					{
						double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SproutModVariables.PlayerVariables())).shippingBin + Math.ceil(1.4 * sellPrice));
						entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.shippingBin = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else if (((item).getOrCreateTag().getString("quality")).equals("Quality: \u00A7d\u2605\u2605\u2605")) {
					{
						double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SproutModVariables.PlayerVariables())).shippingBin + Math.ceil(1.8 * sellPrice));
						entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.shippingBin = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					{
						double _setval = ((entity.getCapability(SproutModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SproutModVariables.PlayerVariables())).shippingBin + sellPrice);
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
}
