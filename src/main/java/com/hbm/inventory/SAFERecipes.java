package com.hbm.inventory;

import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SAFERecipes {

	private static HashMap<ComparableStack, ItemStack> recipes = new HashMap<>();
	
	public static void registerRecipes() {
		addRecipe(new ComparableStack(ModItems.tiny_singularity), new ItemStack(ModItems.singularity));
		addRecipe(new ComparableStack(ModItems.tiny_singularity_counter_resonant), new ItemStack(ModItems.singularity_counter_resonant));
		addRecipe(new ComparableStack(ModItems.tiny_singularity_super_heated), new ItemStack(ModItems.singularity_super_heated));
		addRecipe(new ComparableStack(ModItems.tiny_black_hole), new ItemStack(ModItems.black_hole));
		addRecipe(new ComparableStack(ModItems.tiny_singularity_spark), new ItemStack(ModItems.singularity_spark));
	}

	public static void addRecipe(ComparableStack input, ItemStack output){
		recipes.put(input, output);
	}

	public static void removeRecipe(ComparableStack input){
		recipes.remove(input);
	}
	
	public static HashMap<ItemStack, ItemStack> getAllRecipes() {
		
		HashMap<ItemStack, ItemStack> map = new HashMap<>();
		for(Map.Entry<ComparableStack, ItemStack> recipe : recipes.entrySet()) {
			map.put(recipe.getKey().toStack(), recipe.getValue());
		}
		
		return map;
	}
	
	public static ItemStack getOutput(ItemStack stack) {
		if(stack == null)
			return null;
		return recipes.get(new ComparableStack(stack));
	}
}
