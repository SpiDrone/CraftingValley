
package net.mcreator.starcraftvalley.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.itemgroup.TabcropsItemGroup;
import net.mcreator.starcraftvalley.StarcraftvalleyModElements;

@StarcraftvalleyModElements.ModElement.Tag
public class TeaLeavesItem extends StarcraftvalleyModElements.ModElement {
	@ObjectHolder("starcraftvalley:tea_leaves")
	public static final Item block = null;

	public TeaLeavesItem(StarcraftvalleyModElements instance) {
		super(instance, 110);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(TabcropsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("tea_leaves");
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
