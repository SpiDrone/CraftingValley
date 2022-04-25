
package net.mcreator.starcraftvalley.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.starcraftvalley.item.CoffeeItem;
import net.mcreator.starcraftvalley.StarcraftvalleyModElements;

@StarcraftvalleyModElements.ModElement.Tag
public class TabFoodstuffItemGroup extends StarcraftvalleyModElements.ModElement {
	public TabFoodstuffItemGroup(StarcraftvalleyModElements instance) {
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
