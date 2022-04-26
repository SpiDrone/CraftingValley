
package net.mcreator.starcraftvalley.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.itemgroup.TabcropsItemGroup;
import net.mcreator.starcraftvalley.SproutModElements;

@SproutModElements.ModElement.Tag
public class WheatItem extends SproutModElements.ModElement {
	@ObjectHolder("sprout:wheat")
	public static final Item block = null;

	public WheatItem(SproutModElements instance) {
		super(instance, 137);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(TabcropsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("wheat");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
