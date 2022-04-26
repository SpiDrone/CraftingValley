
package net.mcreator.starcraftvalley.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.starcraftvalley.itemgroup.TabcropsItemGroup;
import net.mcreator.starcraftvalley.SproutModElements;

@SproutModElements.ModElement.Tag
public class StrawberryItem extends SproutModElements.ModElement {
	@ObjectHolder("sprout:strawberry")
	public static final Item block = null;

	public StrawberryItem(SproutModElements instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(TabcropsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(3).saturation(0.3f)

							.build()));
			setRegistryName("strawberry");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 25;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}
