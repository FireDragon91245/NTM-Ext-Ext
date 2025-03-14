package com.hbm.items.special;

import com.hbm.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemSoyuz extends Item {

	public ItemSoyuz(String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if(stack.getItem() == ModItems.missile_soyuz0)
			return EnumRarity.UNCOMMON;
		if(stack.getItem() == ModItems.missile_soyuz1)
			return EnumRarity.RARE;
		if(stack.getItem() == ModItems.missile_soyuz2)
			return EnumRarity.EPIC;
		return EnumRarity.COMMON;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Skin:");
		if(stack.getItem() == ModItems.missile_soyuz0)
			tooltip.add(TextFormatting.GOLD + "Original");
		if(stack.getItem() == ModItems.missile_soyuz1)
			tooltip.add(TextFormatting.BLUE + "Luna Space Center");
		if(stack.getItem() == ModItems.missile_soyuz2)
			tooltip.add(TextFormatting.GREEN + "Post War");
	}
}
