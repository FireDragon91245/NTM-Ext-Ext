package com.hbm.handler.crt;

import com.hbm.inventory.PressRecipes;
import com.hbm.inventory.RecipesCommon;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;

@ZenRegister
@ZenClass("mods.ntm.Press")
public class Press {

	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient inputs, int type) {
		NTMCraftTweaker.postInitActions.add(new ActionAddRecipe(output, inputs, type));
	}

	@Nullable
	public static PressRecipes.PressType toType(int type) {
		if (type >= 0 && type < PressRecipes.PressType.values().length) {
			return PressRecipes.PressType.values()[type];
		} else {
			return null;
		}
	}
	private static class ActionAddRecipe implements IAction {

		private RecipesCommon.AStack inputs;

		private ItemStack output;

		private int type;

		public ActionAddRecipe(IItemStack output, IIngredient inputs, int type) {
			this.output = CraftTweakerMC.getItemStack(output);
			this.inputs = NTMCraftTweaker.IIngredientToAStack(inputs);
			this.type = type;
		}

		@Override
		public void apply() {
			PressRecipes.PressType toType = toType(this.type);
			if(toType == null) {
				CraftTweakerAPI.logError("Invalid press type: " + this.type);
				return;
			}
			if(this.output == null) {
				CraftTweakerAPI.logError("Invalid press output" );
				return;
			}
			if(this.inputs == null) {
				CraftTweakerAPI.logError("Invalid press inputs" );
				return;
			}
			PressRecipes.addRecipe(toType(this.type), this.inputs, this.output);
		}

		@Override
		public String describe() {
			return "Adding NTM Press recipe ("+ this.inputs +" -> "+this.output+")";
		}
	}
}
