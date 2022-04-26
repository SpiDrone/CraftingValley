
package net.mcreator.starcraftvalley.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.starcraftvalley.procedures.CoffeeFoodEatenProcedure;
import net.mcreator.starcraftvalley.itemgroup.TabFoodstuffItemGroup;
import net.mcreator.starcraftvalley.SproutModElements;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

@SproutModElements.ModElement.Tag
public class TeaItem extends SproutModElements.ModElement {
	@ObjectHolder("sprout:tea")
	public static final Item block = null;

	public TeaItem(SproutModElements instance) {
		super(instance, 111);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(TabFoodstuffItemGroup.tab).maxStackSize(32).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(4).saturation(0.4f)

							.build()));
			setRegistryName("tea");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 27;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.DRINK;
		}

		@Override
		public net.minecraft.util.SoundEvent getEatSound() {
			return net.minecraft.util.SoundEvents.ENTITY_GENERIC_DRINK;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemstack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();

			CoffeeFoodEatenProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
					(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			return retval;
		}
	}
}
