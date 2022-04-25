package net.mcreator.starcraftvalley.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.Map;

public class RemoveEnchantmentsProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency itemstack for procedure RemoveEnchantments!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((itemstack).isEnchanted()) {
			{
				Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
				if (_enchantments.containsKey(Enchantments.EFFICIENCY)) {
					_enchantments.remove(Enchantments.EFFICIENCY);
					EnchantmentHelper.setEnchantments(_enchantments, itemstack);
				}
			}
			{
				Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
				if (_enchantments.containsKey(Enchantments.MENDING)) {
					_enchantments.remove(Enchantments.MENDING);
					EnchantmentHelper.setEnchantments(_enchantments, itemstack);
				}
			}
			{
				Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
				if (_enchantments.containsKey(Enchantments.FORTUNE)) {
					_enchantments.remove(Enchantments.FORTUNE);
					EnchantmentHelper.setEnchantments(_enchantments, itemstack);
				}
			}
			{
				Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
				if (_enchantments.containsKey(Enchantments.UNBREAKING)) {
					_enchantments.remove(Enchantments.UNBREAKING);
					EnchantmentHelper.setEnchantments(_enchantments, itemstack);
				}
			}
			{
				Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
				if (_enchantments.containsKey(Enchantments.SILK_TOUCH)) {
					_enchantments.remove(Enchantments.SILK_TOUCH);
					EnchantmentHelper.setEnchantments(_enchantments, itemstack);
				}
			}
		}
	}
}
