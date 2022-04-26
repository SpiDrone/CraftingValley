
package net.mcreator.starcraftvalley.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.starcraftvalley.item.CoffeeItem;
import net.mcreator.starcraftvalley.SproutModElements;

@SproutModElements.ModElement.Tag
public class TabFoodstuffItemGroup extends SproutModElements.ModElement {
	public TabFoodstuffItemGroup(SproutModElements instance) {
		super(instance, 120);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabtab_foodstuff") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CoffeeItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
