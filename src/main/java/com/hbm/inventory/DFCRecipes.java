package com.hbm.inventory;

import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import com.hbm.items.ModItems.Armory;
import com.hbm.items.ModItems.Foods;
import com.hbm.items.ModItems.Materials.Billets;
import com.hbm.items.ModItems.Materials.Ingots;
import com.hbm.items.ModItems.Materials.Nuggies;
import com.hbm.items.ModItems.Materials.Powders;
import com.hbm.items.ModItems.ToolSets;
import com.hbm.lib.Library;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import static com.hbm.inventory.OreDictManager.*;

public class DFCRecipes {

	public static HashMap<ComparableStack, Object[]> dfcRecipes = new HashMap<ComparableStack, Object[]>();
	public static List<DFCRecipe> jeiDFCRecipes = null;
	
	public static void register() {
		
		DFCRecipes.setRecipe(100000L, Foods.marshmallow, new ItemStack(Foods.marshmallow_roasted));
		
		DFCRecipes.setRecipe(2000000L, REDSTONE.dust(), new ItemStack(Nuggies.nugget_mercury));
		DFCRecipes.setRecipe(2000000L, REDSTONE.block(), new ItemStack(ModItems.bottle_mercury));
		
		DFCRecipes.setRecipe(10000000L, W.dust(), new ItemStack(Powders.powder_magnetized_tungsten));
		DFCRecipes.setRecipe(10000000L, W.ingot(), new ItemStack(Ingots.ingot_magnetized_tungsten));
		
		DFCRecipes.setRecipe(60000000L, MAGTUNG.dust(), new ItemStack(Powders.powder_chlorophyte));
		DFCRecipes.setRecipe(60000000L, MAGTUNG.ingot(), new ItemStack(Powders.powder_chlorophyte));
		
		DFCRecipes.setRecipe(200000000L, Powders.powder_chlorophyte, new ItemStack(Powders.powder_balefire));
		
		DFCRecipes.setRecipe(600000000L, Powders.powder_balefire, new ItemStack(ModItems.egg_balefire_shard));

		DFCRecipes.setRecipe(800000000L, Billets.billet_thorium_fuel, new ItemStack(Billets.billet_zfb_bismuth));

		DFCRecipes.setRecipe(1200000000L, Items.STICK, new ItemStack(Blocks.LOG));
		DFCRecipes.setRecipe(1200000000L, Blocks.STONE, new ItemStack(Blocks.IRON_ORE));
		DFCRecipes.setRecipe(1200000000L, Blocks.GRAVEL, new ItemStack(Blocks.COAL_ORE));
		DFCRecipes.setRecipe(1200000000L, Blocks.NETHERRACK, new ItemStack(Blocks.QUARTZ_ORE));
		
		DFCRecipes.setRecipe(1500000000L, Nuggies.nugget_unobtainium_lesser, new ItemStack(Nuggies.nugget_unobtainium_greater));
		
		DFCRecipes.setRecipe(2000000000L, U.nugget(), new ItemStack(Nuggies.nugget_schrabidium));
		DFCRecipes.setRecipe(2000000000L, U.ingot(), new ItemStack(Ingots.ingot_schrabidium));
		DFCRecipes.setRecipe(2000000000L, U.dust(), new ItemStack(Powders.powder_schrabidium));
		
		DFCRecipes.setRecipe(2500000000L, Powders.powder_nitan_mix, new ItemStack(Powders.powder_spark_mix));
		DFCRecipes.setRecipe(5000000000L, ModItems.particle_hydrogen, new ItemStack(ModItems.particle_amat));
		
		DFCRecipes.setRecipe(20000000000L, PU.nugget(), new ItemStack(Nuggies.nugget_euphemium));
		DFCRecipes.setRecipe(20000000000L, PU.ingot(), new ItemStack(Ingots.ingot_euphemium));
		DFCRecipes.setRecipe(20000000000L, PU.dust(), new ItemStack(Powders.powder_euphemium));

		DFCRecipes.setRecipe(30000000000L, ModItems.particle_amat, new ItemStack(ModItems.particle_aschrab));

		DFCRecipes.setRecipe(40000000000L, VERTICIUM.nugget(), new ItemStack(Nuggies.nugget_radspice));
		DFCRecipes.setRecipe(40000000000L, VERTICIUM.ingot(), new ItemStack(Ingots.ingot_radspice));
		DFCRecipes.setRecipe(40000000000L, VERTICIUM.dust(), new ItemStack(Powders.powder_radspice));
		
		DFCRecipes.setRecipe(50000000000L, Billets.billet_polonium, new ItemStack(Billets.billet_yharonite));
			
		DFCRecipes.setRecipe(100000000000L, ToolSets.meteorite_sword_warped, new ItemStack(ToolSets.meteorite_sword_demonic));
		
		DFCRecipes.setRecipe(200000000000L, SBD.dust(), new ItemStack(Powders.powder_dineutronium));
		DFCRecipes.setRecipe(200000000000L, SBD.ingot(), new ItemStack(Ingots.ingot_dineutronium));
		
		DFCRecipes.setRecipe(400000000000L, U238.ingot(), new ItemStack(Ingots.ingot_u238m2));
		DFCRecipes.setRecipe(420000000000L, U238.nugget(), new ItemStack(Nuggies.nugget_u238m2));
		DFCRecipes.setRecipe(690000000000L, Armory.gun_uboinik, new ItemStack(Armory.gun_supershotgun));
		
		DFCRecipes.setRecipe(8000000000000L, ModItems.undefined, new ItemStack(ModItems.glitch));
		DFCRecipes.setRecipe(69000000000000L, Items.WRITABLE_BOOK, new ItemStack(ModItems.book_of_));
	}

	public static void setRecipe(long requiredFlux, ItemStack in, ItemStack out) {
		dfcRecipes.put(new ComparableStack(in), new Object[] {requiredFlux, out});
	}

	public static void setRecipe(long requiredFlux, Item in, ItemStack out) {
		dfcRecipes.put(new ComparableStack(in), new Object[] {requiredFlux, out});
	}
	
	public static void setRecipe(long requiredFlux, Block in, ItemStack out) {
		dfcRecipes.put(new ComparableStack(in), new Object[] {requiredFlux, out});
	}

	public static void setRecipe(long requiredFlux, String in, ItemStack out) {
		dfcRecipes.put(new ComparableStack(OreDictionary.getOres(in).get(0)), new Object[] {requiredFlux, out});
	}

	public static void removeRecipe(ItemStack in) {
		dfcRecipes.remove(new ComparableStack(in));
	}

	public static long getRequiredFlux(ItemStack stack) {
		
		if(stack == null || stack.isEmpty())
			return -1;
		
		ComparableStack comp = new ComparableStack(stack).makeSingular();
		if(dfcRecipes.containsKey(comp)){
			return (long)dfcRecipes.get(comp)[0];
		}

		String[] dictKeys = comp.getDictKeys();
		
		for(String key : dictKeys) {
			if(dfcRecipes.containsKey(key)){
				return (long)dfcRecipes.get(key)[1];
			}
		}
		return -1;
	}

	public static ItemStack getOutput(ItemStack stack) {
		
		if(stack == null || stack.getItem() == null)
			return null;

		ComparableStack comp = new ComparableStack(stack).makeSingular();
		if(dfcRecipes.containsKey(comp)){
			return (ItemStack)dfcRecipes.get(comp)[1];
		}
		
		String[] dictKeys = comp.getDictKeys();
		
		for(String key : dictKeys) {
			
			if(dfcRecipes.containsKey(key)){
				return (ItemStack)dfcRecipes.get(key)[1];
			}
		}
		return null;
	}

	public static List<DFCRecipe> getDFCRecipes() {
		if(jeiDFCRecipes == null){
			jeiDFCRecipes = new ArrayList<DFCRecipe>();
			for(Entry<ComparableStack, Object[]> e : dfcRecipes.entrySet()){
				jeiDFCRecipes.add(new DFCRecipe(e.getKey().toStack(), (long)e.getValue()[0], (ItemStack)e.getValue()[1]));
			}
		}
		return jeiDFCRecipes;
	}
	
	public static class DFCRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final long requiredFlux;
		private final ItemStack output;
		
		public DFCRecipe(ItemStack input, long requiredFlux, ItemStack output) {
			this.input = input;
			this.requiredFlux = requiredFlux;
			this.output = output;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}

		@Override
		public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
			FontRenderer fontRenderer = minecraft.fontRenderer;
	    	
	    	fontRenderer.drawString("Spark", 8, 8, 4210752);
	    	String number = Library.getShortNumber(requiredFlux);
	    	fontRenderer.drawString(number, 80-fontRenderer.getStringWidth(number), 8, 0xa82a0e);
	    	GlStateManager.color(1, 1, 1, 1);
		}
	}
}