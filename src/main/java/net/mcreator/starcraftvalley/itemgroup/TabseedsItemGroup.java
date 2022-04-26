
package net.mcreator.starcraftvalley.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.starcraftvalley.item.StrawberrySeedsItem;
import net.mcreator.starcraftvalley.SproutModElements;

@SproutModElements.ModElement.Tag
public class TabseedsItemGroup extends SproutModElements.ModElement {
	public TabseedsItemGroup(SproutModElements instance) {
		super(instance, 115);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabtabseeds") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(StrawberrySeedsItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
