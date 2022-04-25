
package net.mcreator.starcraftvalley.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.starcraftvalley.item.StrawberryItem;
import net.mcreator.starcraftvalley.StarcraftvalleyModElements;

@StarcraftvalleyModElements.ModElement.Tag
public class TabcropsItemGroup extends StarcraftvalleyModElements.ModElement {
	public TabcropsItemGroup(StarcraftvalleyModElements instance) {
		super(instance, 114);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabtabcrops") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(StrawberryItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
