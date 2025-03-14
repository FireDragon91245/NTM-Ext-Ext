package com.hbm.inventory;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.items.ModItems;
import com.hbm.items.ModItems.Armory;
import com.hbm.items.ModItems.Materials.Billets;
import com.hbm.items.ModItems.Materials.Ingots;
import com.hbm.items.ModItems.Materials.Nuggies;
import com.hbm.items.ModItems.Materials.Powders;
import com.hbm.items.ModItems.ToolSets;
import com.hbm.items.ModItems.Upgrades;
import crafttweaker.CraftTweakerAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.hbm.inventory.OreDictManager.*;
import static net.minecraft.item.ItemStack.areItemStacksEqual;

public class AnvilRecipes {

	private static List<AnvilSmithingRecipe> smithingRecipes = new ArrayList<>();
	private static List<AnvilConstructionRecipe> constructionRecipes = new ArrayList<>();
	
	public static void register() {
		registerSmithing();
		registerConstruction();
	}
	
	/*
	 *      //////  //      //  //  //////  //  //  //  //    //  //////
	 *     //      ////  ////  //    //    //  //  //  ////  //  //
	 *    //////  //  //  //  //    //    //////  //  //  ////  //  //
	 *       //  //      //  //    //    //  //  //  //    //  //  //
	 *  //////  //      //  //    //    //  //  //  //    //  //////
	 */
	public static void registerSmithing() {
		
		Block[] anvils = new Block[]{ModBlocks.anvil_iron, ModBlocks.anvil_lead};
		
		for(Block anvil : anvils) {
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_bismuth, 1), new ComparableStack(anvil), new ComparableStack(Ingots.ingot_bismuth, 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_dnt, 1), new ComparableStack(anvil), new OreDictStack(DNT.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_osmiridium, 1), new ComparableStack(anvil), new OreDictStack(OSMIRIDIUM.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_ferrouranium, 1), new ComparableStack(anvil), new OreDictStack(FERRO.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_meteorite, 1), new ComparableStack(anvil), new ComparableStack(Ingots.ingot_meteorite, 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_schrabidate, 1), new ComparableStack(anvil), new OreDictStack(SBD.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_starmetal, 1), new ComparableStack(anvil), new OreDictStack(STAR.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(ModBlocks.anvil_steel, 1), new ComparableStack(anvil), new OreDictStack(STEEL.ingot(), 10)));
		}
		
		for(int i = 0; i < 9; i++)
			smithingRecipes.add(new AnvilSmithingHotRecipe(3, new ItemStack(Ingots.ingot_steel_dusted, 1, i + 1),
					new ComparableStack(Ingots.ingot_steel_dusted, 1, i), new ComparableStack(Ingots.ingot_steel_dusted, 1, i)));
		
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, new ItemStack(Ingots.ingot_chainsteel, 1),
				new ComparableStack(Ingots.ingot_steel_dusted, 1, 9), new ComparableStack(Ingots.ingot_steel_dusted, 1, 9)));
		
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, new ItemStack(Ingots.ingot_meteorite_forged, 1), new ComparableStack(Ingots.ingot_meteorite), new ComparableStack(Ingots.ingot_meteorite)));
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, new ItemStack(ModItems.blade_meteorite, 1), new ComparableStack(Ingots.ingot_meteorite_forged), new ComparableStack(Ingots.ingot_meteorite_forged)));
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, new ItemStack(ToolSets.meteorite_sword_reforged, 1), new ComparableStack(ToolSets.meteorite_sword_seared), new ComparableStack(Ingots.ingot_meteorite_forged)));
		smithingRecipes.add(new AnvilSmithingRecipe(1, new ItemStack(Armory.gun_ar15, 1), new ComparableStack(Armory.gun_thompson), new ComparableStack(ModItems.pipe_lead)));
		smithingRecipes.add(new AnvilSmithingRecipe(1916169, new ItemStack(ModItems.wings_murk, 1), new ComparableStack(ModItems.wings_limp), new ComparableStack(ModItems.particle_tachyon)));

		smithingRecipes.add(new AnvilSmithingCyanideRecipe());
		smithingRecipes.add(new AnvilSmithingRenameRecipe());
	}
	
	/*
	 *      //////  //////  //    //  //////  //////  ////    //  //  //////  //////  //  //////  //    //
	 *     //      //  //  ////  //  //        //    //  //  //  //  //        //    //  //  //  ////  //
	 *    //      //  //  //  ////  //////    //    ////    //  //  //        //    //  //  //  //  ////
	 *   //      //  //  //    //      //    //    //  //  //  //  //        //    //  //  //  //    //
	 *  //////  //////  //    //  //////    //    //  //  //////  //////    //    //  //////  //    //
	 */
	public static void registerConstruction() {
		
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(IRON.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_iron))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(GOLD.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_gold))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(TI.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_titanium))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(AL.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_aluminium))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(STEEL.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_steel))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(PB.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_lead))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_copper))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(ALLOY.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_advanced_alloy))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(SA326.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_schrabidium))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CMB.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_combine_steel))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(BIGMT.ingot()), new AnvilOutput(new ItemStack(ModItems.plate_saturnite))).setTier(3));

		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(AL.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_aluminium, 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_copper, 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(W.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_tungsten, 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(MINGRADE.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_red_copper, 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(ALLOY.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_advanced_alloy, 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(GOLD.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_gold, 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(SA326.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_schrabidium, 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(MAGTUNG.ingot()), new AnvilOutput(new ItemStack(ModItems.wire_magnetized_tungsten, 8))).setTier(4));

		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(COAL.dust()), new AnvilOutput(new ItemStack(Items.COAL))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(NETHERQUARTZ.dust()), new AnvilOutput(new ItemStack(Items.QUARTZ))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(LAPIS.dust()), new AnvilOutput(new ItemStack(Items.DYE, 1, 4))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(DIAMOND.dust()), new AnvilOutput(new ItemStack(Items.DIAMOND))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(EMERALD.dust()), new AnvilOutput(new ItemStack(Items.EMERALD))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new OreDictStack(RAREEARTH.gem()),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.fragment_boron)),
						new AnvilOutput(new ItemStack(ModItems.fragment_boron), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.fragment_cobalt)),
						new AnvilOutput(new ItemStack(ModItems.fragment_cobalt), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.fragment_neodymium), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.fragment_niobium), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.fragment_cerium), 0.4F),
						new AnvilOutput(new ItemStack(ModItems.fragment_lanthanium), 0.3F),
						new AnvilOutput(new ItemStack(ModItems.fragment_actinium), 0.3F)
						
				}
		).setTier(2));

		registerConstructionRecipes();
		registerConstructionAmmo();
		registerConstructionUpgrades();
		registerConstructionRecycling();
	}
	
	public static void registerConstructionRecipes() {

		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(AL.ingot(), 1), new AnvilOutput(new ItemStack(ModBlocks.deco_aluminium))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(BE.ingot(), 1), new AnvilOutput(new ItemStack(ModBlocks.deco_beryllium))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(PB.ingot(), 1), new AnvilOutput(new ItemStack(ModBlocks.deco_lead))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(MINGRADE.ingot(), 1), new AnvilOutput(new ItemStack(ModBlocks.deco_red_copper))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(STEEL.ingot(), 1), new AnvilOutput(new ItemStack(ModBlocks.deco_steel))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(TI.ingot(), 1), new AnvilOutput(new ItemStack(ModBlocks.deco_titanium))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(W.ingot(), 1), new AnvilOutput(new ItemStack(ModBlocks.deco_tungsten))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));

		constructionRecipes.add(new AnvilConstructionRecipe(new ComparableStack(ModBlocks.deco_aluminium, 1), new AnvilOutput(new ItemStack(Ingots.ingot_aluminium))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new ComparableStack(ModBlocks.deco_beryllium, 1), new AnvilOutput(new ItemStack(Ingots.ingot_beryllium))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new ComparableStack(ModBlocks.deco_lead, 1), new AnvilOutput(new ItemStack(Ingots.ingot_lead))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new ComparableStack(ModBlocks.deco_red_copper, 1), new AnvilOutput(new ItemStack(Ingots.ingot_red_copper))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new ComparableStack(ModBlocks.deco_steel, 1), new AnvilOutput(new ItemStack(Ingots.ingot_steel))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new ComparableStack(ModBlocks.deco_titanium, 1), new AnvilOutput(new ItemStack(Ingots.ingot_titanium))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new ComparableStack(ModBlocks.deco_tungsten, 1), new AnvilOutput(new ItemStack(Ingots.ingot_tungsten))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(DNT.ingot(), 4), new ComparableStack(ModBlocks.depth_brick)},
				new AnvilOutput(new ItemStack(ModBlocks.depth_dnt))).setTier(1916169));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new OreDictStack(CU.plate(), 4),
				new AnvilOutput(new ItemStack(ModItems.board_copper))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.coil_copper, 2),
				new AnvilOutput(new ItemStack(ModItems.coil_copper_torus))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.coil_advanced_alloy, 2),
				new AnvilOutput(new ItemStack(ModItems.coil_advanced_torus))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.coil_gold, 2),
				new AnvilOutput(new ItemStack(ModItems.coil_gold_torus))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(IRON.plate(), 2), new ComparableStack(ModItems.coil_copper), new ComparableStack(ModItems.coil_copper_torus)},
				new AnvilOutput(new ItemStack(ModItems.motor, 2))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new ComparableStack(ModItems.motor), new OreDictStack(ANY_PLASTIC.ingot(), 2), new OreDictStack(DESH.ingot(), 2), new ComparableStack(ModItems.coil_gold_torus)},
				new AnvilOutput(new ItemStack(ModItems.motor_desh, 1))).setTier(3));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(Blocks.STONEBRICK, 4),
						new ComparableStack(Ingots.ingot_firebrick, 4),
						new ComparableStack(ModItems.board_copper, 2)
				},
				new AnvilOutput(new ItemStack(ModBlocks.machine_difurnace_off))).setTier(1));
		
		int ukModifier = 1;
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack("blockGlassColorless", 4 * ukModifier),
						new OreDictStack(STEEL.ingot(), 8 * ukModifier),
						new OreDictStack(CU.ingot(), 8 * ukModifier),
						new ComparableStack(ModItems.motor, 2 * ukModifier),
						new ComparableStack(ModItems.circuit_aluminium, 1 * ukModifier)
				},
				new AnvilOutput(new ItemStack(ModBlocks.machine_assembler))).setTier(2));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(Blocks.FURNACE),
						new OreDictStack(STEEL.plate(), 8),
						new OreDictStack(CU.ingot(), 8)
				}, new AnvilOutput(new ItemStack(ModBlocks.heater_firebox))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[]{
						new ComparableStack(Ingots.ingot_firebrick, 16),
						new OreDictStack(STEEL.plate(), 4),
						new OreDictStack(CU.ingot(), 8),
				} ,new AnvilOutput(new ItemStack(ModBlocks.heater_oven))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[]{
						new ComparableStack(ModItems.tank_steel, 4),
						new ComparableStack(ModItems.pipes_steel, 1),
						new OreDictStack(TI.ingot(), 12),
						new OreDictStack(CU.ingot(), 8)
				} ,new AnvilOutput(new ItemStack(ModBlocks.heater_oilburner))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(ANY_PLASTIC.ingot(), 4),
						new OreDictStack(CU.ingot(), 8),
						new OreDictStack(STEEL.plate(), 8),
						new ComparableStack(ModItems.coil_tungsten, 8),
						new ComparableStack(ModItems.circuit_copper, 1)
				}, new AnvilOutput(new ItemStack(ModBlocks.heater_electric))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(RUBBER.ingot(), 4),
						new OreDictStack(CU.ingot(), 16),
						new OreDictStack(STEEL.plate(), 16),
						new ComparableStack(ModItems.pipes_steel, 1),
				}, new AnvilOutput(new ItemStack(ModBlocks.heater_heatex))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(ModItems.rtg_unit, 5),
						new OreDictStack(getReflector(), 8),
						new OreDictStack(CU.ingot(), 16),
						new OreDictStack(TCALLOY.ingot(), 6),
						new OreDictStack(STEEL.plate(), 8),
				}, new AnvilOutput(new ItemStack(ModBlocks.heater_rt))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(Blocks.STONEBRICK, 16),
						new OreDictStack(IRON.ingot(), 4),
						new OreDictStack(STEEL.plate(), 16),
						new OreDictStack(CU.ingot(), 8),
						new ComparableStack(ModBlocks.steel_grate, 16)
				}, new AnvilOutput(new ItemStack(ModBlocks.furnace_steel))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(STEEL.ingot(), 4),
						new OreDictStack(CU.plate(), 16),
						new ComparableStack(ModItems.plate_polymer, 8)
				}, new AnvilOutput(new ItemStack(ModBlocks.heat_boiler))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(ModBlocks.brick_concrete, 64),
						new ComparableStack(Blocks.IRON_BARS, 128),
						new ComparableStack(ModBlocks.machine_condenser, 5),
				},
				new AnvilOutput(new ItemStack(ModBlocks.machine_tower_small))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(ModBlocks.concrete_smooth, 128),
						new ComparableStack(ModBlocks.steel_scaffold, 32),
						new ComparableStack(ModBlocks.machine_condenser, 25),
						new ComparableStack(ModItems.pipes_steel, 2)
				},
				new AnvilOutput(new ItemStack(ModBlocks.machine_tower_large))).setTier(4));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(ANY_CONCRETE.any(), 2),
						new ComparableStack(ModBlocks.steel_scaffold, 8),
						new ComparableStack(ModItems.plate_polymer, 8),
						new ComparableStack(ModItems.coil_copper, 4)
				},
				new AnvilOutput(new ItemStack(ModBlocks.red_pylon_large))).setTier(2));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(ANY_CONCRETE.any(), 6),
						new OreDictStack(STEEL.ingot(), 4),
						new ComparableStack(ModBlocks.steel_scaffold, 2),
						new ComparableStack(ModItems.plate_polymer, 8),
						new ComparableStack(ModItems.coil_copper, 2),
						new ComparableStack(ModItems.coil_copper_torus, 2)
				},
				new AnvilOutput(new ItemStack(ModBlocks.substation))).setTier(2));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(ModBlocks.steel_wall, 2),
						new OreDictStack(REDSTONE.dust(), 4),
						new ComparableStack(Blocks.LEVER, 2),
						new ComparableStack(ModItems.wire_advanced_alloy, 3)
				},
				new AnvilOutput(new ItemStack(ModBlocks.bm_power_box))).setTier(5));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(Items.BONE, 16),
						new ComparableStack(Items.LEATHER, 4),
						new ComparableStack(Items.FEATHER, 24)
				},
				new AnvilOutput(new ItemStack(ModItems.wings_limp))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(S.dust(), 8),
						new OreDictStack(STEEL.plate(), 4),
						new OreDictStack(AL.plate(), 2),
						new ComparableStack(ModItems.hull_small_steel, 4),
						new ComparableStack(ModItems.board_copper, 1),
						new ComparableStack(ModItems.turbine_titanium, 1),
						new ComparableStack(ModItems.circuit_aluminium, 1)
				},
				new AnvilOutput(new ItemStack(ModBlocks.machine_deuterium_extractor))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new ComparableStack(ModItems.deuterium_filter, 2),
						new ComparableStack(ModItems.hull_big_steel, 5),
						new ComparableStack(ModBlocks.concrete_smooth, 8),
						new ComparableStack(ModBlocks.concrete_asbestos, 4),
						new ComparableStack(ModBlocks.steel_scaffold, 16),
						new ComparableStack(ModBlocks.deco_pipe_quad, 12),
						new OreDictStack(S.dust(), 32),
				},
				new AnvilOutput(new ItemStack(ModBlocks.machine_deuterium_tower))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(DESH.ingot(), 4), new OreDictStack(ANY_PLASTIC.dust(), 2), new OreDictStack(DURA.ingot(), 1)},
				new AnvilOutput(new ItemStack(ModItems.plate_desh, 4))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(EUPH.ingot(), 4), new OreDictStack(AT.ingot(), 3), new OreDictStack(ANY_BISMOID.dust(), 1), new OreDictStack(VOLCANIC.gem(), 1), new ComparableStack(Ingots.ingot_osmiridium)},
				new AnvilOutput(new ItemStack(ModItems.plate_euphemium, 4))).setTier(6));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(DNT.ingot(), 4), new ComparableStack(Powders.powder_spark_mix, 2), new OreDictStack(DESH.ingot(), 1)},
				new AnvilOutput(new ItemStack(ModItems.plate_dineutronium, 4))).setTier(7));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack("plateTitanium", 2), new OreDictStack(STEEL.ingot(), 1), new ComparableStack(ModItems.bolt_tungsten, 2)},
				new AnvilOutput(new ItemStack(ModItems.plate_armor_titanium))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(IRON.plate(), 4), new OreDictStack(BIGMT.plate(), 2), new ComparableStack(ModItems.plate_armor_titanium, 1)},
				new AnvilOutput(new ItemStack(ModItems.plate_armor_ajr))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new ComparableStack(ModItems.plate_paa, 2), new ComparableStack(ModItems.plate_armor_ajr, 1), new ComparableStack(ModItems.wire_tungsten, 4)},
				new AnvilOutput(new ItemStack(ModItems.plate_armor_hev))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(OreDictManager.getReflector(), 2), new ComparableStack(ModItems.plate_armor_hev, 1), new ComparableStack(ModItems.wire_magnetized_tungsten, 4)},
				new AnvilOutput(new ItemStack(ModItems.plate_armor_lunar))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new ComparableStack(Ingots.ingot_meteorite_forged, 4), new OreDictStack(DESH.ingot(), 1), new ComparableStack(Billets.billet_yharonite, 1)},
				new AnvilOutput(new ItemStack(ModItems.plate_armor_fau))).setTier(6));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new ComparableStack(ModItems.plate_dineutronium, 4), new ComparableStack(ModItems.particle_sparkticle, 1), new ComparableStack(ModItems.plate_armor_fau, 6)},
				new AnvilOutput(new ItemStack(ModItems.plate_armor_dnt))).setTier(7));
		
		pullFromAssembler(new ComparableStack(ModItems.plate_mixed, 4), 3);
		
	}
	
	public static void registerConstructionAmmo() {
		
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(new ItemStack(Armory.casing_357))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(new ItemStack(Armory.casing_44))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(new ItemStack(Armory.casing_9))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(new ItemStack(Armory.casing_50))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(new ItemStack(Armory.casing_buckshot))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), new ComparableStack(Items.REDSTONE)}, new AnvilOutput(new ItemStack(Armory.primer_357))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), new ComparableStack(Items.REDSTONE)}, new AnvilOutput(new ItemStack(Armory.primer_44))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), new ComparableStack(Items.REDSTONE)}, new AnvilOutput(new ItemStack(Armory.primer_9))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), new ComparableStack(Items.REDSTONE)}, new AnvilOutput(new ItemStack(Armory.primer_50))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), new ComparableStack(Items.REDSTONE)}, new AnvilOutput(new ItemStack(Armory.primer_buckshot))).setTier(1));
		
		Object[][] recs = new Object[][] {
			{Armory.ammo_12gauge,	Powders.powder_fire,								Armory.ammo_12gauge_incendiary,	20,		2},
			{Armory.ammo_12gauge,	Item.getItemFromBlock(ModBlocks.gravel_obsidian),	Armory.ammo_12gauge_shrapnel,		20,		2},
			{Armory.ammo_12gauge,	Ingots.ingot_u238,								Armory.ammo_12gauge_du,			20,		3},
			{Armory.ammo_12gauge,	ModItems.coin_maskman,								Armory.ammo_12gauge_sleek,		100,	4},
			
			{Armory.ammo_20gauge,	Powders.powder_fire,								Armory.ammo_20gauge_incendiary,	20,		2},
			{Armory.ammo_20gauge,	Item.getItemFromBlock(ModBlocks.gravel_obsidian),	Armory.ammo_20gauge_shrapnel,		20,		2},
			{Armory.ammo_20gauge,	Powders.powder_poison,								Armory.ammo_20gauge_caustic,		20,		2},
			{Armory.ammo_20gauge,	DIAMOND.dust(),										Armory.ammo_20gauge_shock,		20,		2},
			{Armory.ammo_20gauge,	Item.getItemFromBlock(Blocks.SOUL_SAND),			Armory.ammo_20gauge_wither,		10,		3},
			{Armory.ammo_20gauge,	ModItems.coin_maskman,								Armory.ammo_20gauge_sleek,		100,	4},

			{Armory.ammo_4gauge_flechette,	Ingots.ingot_phosphorus,		Armory.ammo_4gauge_flechette_phosphorus,	20,		2},
			{Armory.ammo_4gauge_explosive,	ModItems.egg_balefire_shard,	Armory.ammo_4gauge_balefire,				10,		4},
			{Armory.ammo_4gauge_explosive,	Armory.ammo_rocket,			Armory.ammo_4gauge_kampf,					4,		2},
			{Armory.ammo_4gauge_kampf,		ModItems.pellet_canister,		Armory.ammo_4gauge_canister,				10,		3},
			{Armory.ammo_4gauge,				ModItems.pellet_claws,			Armory.ammo_4gauge_claw,					4,		5},
			{Armory.ammo_4gauge,				ModItems.toothpicks,			Armory.ammo_4gauge_vampire,				4,		5},
			{Armory.ammo_4gauge,				ModItems.pellet_charged,		Armory.ammo_4gauge_void,					1,		5},
			{Armory.ammo_4gauge,				ModItems.coin_maskman,			Armory.ammo_4gauge_sleek,					100,	4},

			{Armory.ammo_44,		Ingots.ingot_dura_steel,		Armory.ammo_44_ap,			20,	2},
			{Armory.ammo_44,		Ingots.ingot_u238,			Armory.ammo_44_du,			20,	2},
			{Armory.ammo_44,		Ingots.ingot_phosphorus,		Armory.ammo_44_phosphorus,	20,	2},
			{Armory.ammo_44_du,	Ingots.ingot_starmetal,		Armory.ammo_44_star,			10,	3},
			{Armory.ammo_44,		ModItems.pellet_chlorophyte,	Armory.ammo_44_chlorophyte,	10,	3},

			{Armory.ammo_5mm,	ModItems.ingot_semtex,			Armory.ammo_5mm_explosive,	20,	2},
			{Armory.ammo_5mm,	Ingots.ingot_u238,			Armory.ammo_5mm_du,			20,	2},
			{Armory.ammo_5mm,	Ingots.ingot_starmetal,		Armory.ammo_5mm_star,			10,	3},
			{Armory.ammo_5mm,	ModItems.pellet_chlorophyte,	Armory.ammo_5mm_chlorophyte,	10,	3},

			{Armory.ammo_9mm,	Ingots.ingot_dura_steel,		Armory.ammo_9mm_ap,			20,	2},
			{Armory.ammo_9mm,	Ingots.ingot_u238,			Armory.ammo_9mm_du,			20,	2},
			{Armory.ammo_9mm,	ModItems.pellet_chlorophyte,	Armory.ammo_9mm_chlorophyte,	10,	3},
			
			{Armory.ammo_22lr,	Ingots.ingot_dura_steel,		Armory.ammo_22lr_ap,			20,	2},
			{Armory.ammo_22lr,	ModItems.pellet_chlorophyte,	Armory.ammo_22lr_chlorophyte,	10,	3},

			{Armory.ammo_50bmg,			Powders.powder_fire,			Armory.ammo_50bmg_incendiary,		20,		2},
			{Armory.ammo_50bmg,			Ingots.ingot_phosphorus,		Armory.ammo_50bmg_phosphorus,		20,		2},
			{Armory.ammo_50bmg,			ModItems.ingot_semtex,			Armory.ammo_50bmg_explosive,		20,		2},
			{Armory.ammo_50bmg,			Ingots.ingot_dura_steel,		Armory.ammo_50bmg_ap,				20,		2},
			{Armory.ammo_50bmg,			Ingots.ingot_u238,			Armory.ammo_50bmg_du,				20,		2},
			{Armory.ammo_50bmg_du,		Ingots.ingot_starmetal,		Armory.ammo_50bmg_star,			10,		3},
			{Armory.ammo_50bmg,			ModItems.pellet_chlorophyte,	Armory.ammo_50bmg_chlorophyte,	10,		3},
			{Armory.ammo_50bmg,			ModItems.coin_maskman,			Armory.ammo_50bmg_sleek,			100,	4},
			{Armory.ammo_50bmg,			ModItems.pellet_flechette,		Armory.ammo_50bmg_flechette,		20,		2},
			{Armory.ammo_50bmg_flechette,	Nuggies.nugget_am_mix,			Armory.ammo_50bmg_flechette_am,	10,		3},
			{Armory.ammo_50bmg_flechette,	Powders.powder_polonium,		Armory.ammo_50bmg_flechette_po,	20,		3},

			{Armory.ammo_50ae,	Ingots.ingot_dura_steel,		Armory.ammo_50ae_ap,			20,	2},
			{Armory.ammo_50ae,	Ingots.ingot_u238,			Armory.ammo_50ae_du,			20,	2},
			{Armory.ammo_50ae_du,	Ingots.ingot_starmetal,		Armory.ammo_50ae_star,		10,	3},
			{Armory.ammo_50ae,	ModItems.pellet_chlorophyte,	Armory.ammo_50ae_chlorophyte,	10,	3},

			{Armory.ammo_556,				Ingots.ingot_phosphorus,		Armory.ammo_556_phosphorus,				20,		2},
			{Armory.ammo_556,				Ingots.ingot_dura_steel,		Armory.ammo_556_ap,						20,		2},
			{Armory.ammo_556,				Ingots.ingot_u238,			Armory.ammo_556_du,						20,		2},
			{Armory.ammo_556_du,			Ingots.ingot_starmetal,		Armory.ammo_556_star,						10,		3},
			{Armory.ammo_556,				ModItems.pellet_chlorophyte,	Armory.ammo_556_chlorophyte,				10,		3},
			{Armory.ammo_556,				ModItems.coin_maskman,			Armory.ammo_556_sleek,					100,	4},
			{Armory.ammo_556,				Items.REDSTONE,					Armory.ammo_556_tracer,					20,		2},
			{Armory.ammo_556,				ModItems.pellet_flechette,		Armory.ammo_556_flechette,				20,		2},
			{Armory.ammo_556_flechette,	Powders.powder_fire,			Armory.ammo_556_flechette_incendiary,		20,		2},
			{Armory.ammo_556_flechette,	Ingots.ingot_phosphorus,		Armory.ammo_556_flechette_phosphorus,		20,		2},
			{Armory.ammo_556_flechette,	Ingots.ingot_u238,			Armory.ammo_556_flechette_du,				20,		2},
			{Armory.ammo_556_flechette,	ModItems.coin_maskman,			Armory.ammo_556_flechette_sleek,			100,	4},
			{Armory.ammo_556_flechette,	ModItems.pellet_chlorophyte,	Armory.ammo_556_flechette_chlorophyte,	10,		3},
		};
		
		for(Object[] objs : recs) {
			
			if(objs[1] instanceof Item) {
				constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] { new ComparableStack((Item)objs[0], (int)objs[3]), new ComparableStack((Item)objs[1], 1) },
						new AnvilOutput(new ItemStack((Item)objs[2], (int)objs[3]))).setTier((int)objs[4]));
				
			} else if(objs[1] instanceof String) {
				constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] { new ComparableStack((Item)objs[0], (int)objs[3]), new OreDictStack((String)objs[1], 1) },
						new AnvilOutput(new ItemStack((Item)objs[2], (int)objs[3]))).setTier((int)objs[4]));
			}
		}
	}
	
	public static void registerConstructionUpgrades() {
		pullFromAssembler(new ComparableStack(ModItems.upgrade_template), 2);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_speed_1), 2);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_speed_2), 3);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_speed_3), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_power_1), 2);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_power_2), 3);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_power_3), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_effect_1), 2);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_effect_2), 3);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_effect_3), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_fortune_1), 2);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_fortune_2), 3);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_fortune_3), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_afterburn_1), 2);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_afterburn_2), 3);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_afterburn_3), 4);

		pullFromAssembler(new ComparableStack(Upgrades.upgrade_radius), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_health), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_smelter), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_shredder), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_centrifuge), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_crystallizer), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_nullifier), 4);
		pullFromAssembler(new ComparableStack(Upgrades.upgrade_screm), 4);
	}

	public static void registerConstructionRecycling() {
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.solar_mirror),
				new AnvilOutput[] {new AnvilOutput(new ItemStack(Ingots.ingot_steel, 1)), new AnvilOutput(new ItemStack(ModItems.plate_aluminium, 1))}).setTier(3));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.barrel_tcalloy),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(Ingots.ingot_titanium, 2)),
						new AnvilOutput(new ItemStack(Ingots.ingot_tcalloy, 4)),
						new AnvilOutput(new ItemStack(Ingots.ingot_tcalloy, 1), 0.50F),
						new AnvilOutput(new ItemStack(Ingots.ingot_tcalloy, 1), 0.25F)
				}
		).setTier(3));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_raw),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.plate_steel, 1)),
						new AnvilOutput(new ItemStack(ModItems.wire_aluminium, 1)),
						new AnvilOutput(new ItemStack(Items.REDSTONE, 1))
				}
		).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_aluminium),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.plate_steel, 1)),
						new AnvilOutput(new ItemStack(ModItems.wire_aluminium, 1), 0.5F),
						new AnvilOutput(new ItemStack(Items.REDSTONE, 1), 0.25F)
				}
		).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_copper),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.circuit_aluminium, 1)),
						new AnvilOutput(new ItemStack(ModItems.wire_copper, 2)),
						new AnvilOutput(new ItemStack(ModItems.wire_copper, 1), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.wire_copper, 1), 0.25F),
						new AnvilOutput(new ItemStack(Powders.powder_quartz, 1), 0.25F),
						new AnvilOutput(new ItemStack(ModItems.plate_copper, 1), 0.5F)
				}
		).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_red_copper),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.circuit_copper, 1)),
						new AnvilOutput(new ItemStack(ModItems.wire_red_copper, 2)),
						new AnvilOutput(new ItemStack(ModItems.wire_red_copper, 1), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.wire_red_copper, 1), 0.25F),
						new AnvilOutput(new ItemStack(Powders.powder_gold, 1), 0.25F),
						new AnvilOutput(new ItemStack(ModItems.plate_polymer, 1), 0.5F)
				}
		).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_gold),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.circuit_red_copper, 1)),
						new AnvilOutput(new ItemStack(ModItems.wire_gold, 2)),
						new AnvilOutput(new ItemStack(ModItems.wire_gold, 1), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.wire_gold, 1), 0.25F),
						new AnvilOutput(new ItemStack(Powders.powder_lapis, 1), 0.25F),
						new AnvilOutput(new ItemStack(Ingots.ingot_polymer, 1), 0.5F)
				}
		).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_schrabidium),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.circuit_gold, 1)),
						new AnvilOutput(new ItemStack(ModItems.wire_schrabidium, 2)),
						new AnvilOutput(new ItemStack(ModItems.wire_schrabidium, 1), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.wire_schrabidium, 1), 0.25F),
						new AnvilOutput(new ItemStack(Powders.powder_diamond, 1), 0.25F),
						new AnvilOutput(new ItemStack(Ingots.ingot_desh, 1), 0.5F)
				}
		).setTier(6));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_tantalium_raw),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(Items.REDSTONE, 4)),
						new AnvilOutput(new ItemStack(ModItems.wire_gold, 2)),
						new AnvilOutput(new ItemStack(ModItems.plate_copper, 2)),
						new AnvilOutput(new ItemStack(Nuggies.nugget_tantalium, 1))
				}
		).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_tantalium),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(Items.REDSTONE, 2)),
						new AnvilOutput(new ItemStack(ModItems.wire_gold, 1)),
						new AnvilOutput(new ItemStack(ModItems.wire_gold, 1), 0.5F),
						new AnvilOutput(new ItemStack(ModItems.plate_copper, 1)),
						new AnvilOutput(new ItemStack(Nuggies.nugget_tantalium, 1), 0.75F)
				}
		).setTier(4));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_bismuth_raw),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(Items.REDSTONE, 4)),
						new AnvilOutput(new ItemStack(Ingots.ingot_polymer, 2)),
						new AnvilOutput(new ItemStack(Ingots.ingot_asbestos, 2)),
						new AnvilOutput(new ItemStack(Ingots.ingot_bismuth, 1))
				}
		).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.circuit_bismuth),
				new AnvilOutput[] {
						new AnvilOutput(new ItemStack(Items.REDSTONE, 2)),
						new AnvilOutput(new ItemStack(Ingots.ingot_polymer, 1)),
						new AnvilOutput(new ItemStack(Ingots.ingot_polymer, 1), 0.5F),
						new AnvilOutput(new ItemStack(Ingots.ingot_asbestos, 1)),
						new AnvilOutput(new ItemStack(Ingots.ingot_bismuth, 1), 0.75F)
				}
		).setTier(4));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.pile_rod_uranium),
				new AnvilOutput[] {new AnvilOutput(new ItemStack(Billets.billet_uranium, 3)), new AnvilOutput(new ItemStack(ModItems.plate_iron, 2))}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.pile_rod_plutonium),
				new AnvilOutput[] {new AnvilOutput(new ItemStack(Billets.billet_plutonium, 3)), new AnvilOutput(new ItemStack(ModItems.plate_iron, 2))}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.pile_rod_source),
				new AnvilOutput[] {new AnvilOutput(new ItemStack(Billets.billet_ra226be, 3)), new AnvilOutput(new ItemStack(ModItems.plate_iron, 2))}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModItems.pile_rod_boron),
				new AnvilOutput[] {new AnvilOutput(new ItemStack(Ingots.ingot_boron, 2)), new AnvilOutput(new ItemStack(Items.STICK, 2))}).setTier(3));

		//RBMK
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_moderator), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(ModBlocks.block_graphite, 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_absorber), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(Ingots.ingot_boron, 8))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_reflector), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(ModItems.neutron_reflector, 8))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_control), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_absorber, 1)),
						new AnvilOutput(new ItemStack(Ingots.ingot_graphite, 2)),
						new AnvilOutput(new ItemStack(ModItems.motor, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_control_mod), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_control, 1)),
						new AnvilOutput(new ItemStack(ModBlocks.block_graphite, 4)),
						new AnvilOutput(new ItemStack(Nuggies.nugget_bismuth, 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_control_auto), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_control, 1)),
						new AnvilOutput(new ItemStack(ModItems.circuit_targeting_tier1, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_rod_reasim), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(Ingots.ingot_zirconium, 4)),
						new AnvilOutput(new ItemStack(ModItems.hull_small_steel, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_rod_reasim_mod), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_rod_reasim, 1)),
						new AnvilOutput(new ItemStack(ModBlocks.block_graphite, 4)),
						new AnvilOutput(new ItemStack(Ingots.ingot_tcalloy, 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_outgasser), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(ModBlocks.steel_grate, 6)),
						new AnvilOutput(new ItemStack(ModItems.tank_steel, 1)),
						new AnvilOutput(new ItemStack(Blocks.HOPPER, 1))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_storage), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(ModBlocks.crate_steel, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
					new ComparableStack(ModBlocks.rbmk_rod), new AnvilOutput[] {
							new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
							new AnvilOutput(new ItemStack(ModItems.hull_small_steel, 2))
					}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_rod_mod), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_rod, 1)),
						new AnvilOutput(new ItemStack(ModBlocks.block_graphite, 4)),
						new AnvilOutput(new ItemStack(Nuggies.nugget_bismuth, 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_boiler), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(ModItems.board_copper, 6)),
						new AnvilOutput(new ItemStack(ModItems.pipes_steel, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_cooler), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(ModBlocks.steel_grate, 4)),
						new AnvilOutput(new ItemStack(ModItems.plate_polymer, 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.rbmk_heater), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(new ItemStack(ModItems.pipes_steel, 2)),
						new AnvilOutput(new ItemStack(ModItems.plate_polymer, 2))
				}).setTier(4));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.machine_turbine), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.turbine_titanium, 1)),
						new AnvilOutput(new ItemStack(ModItems.coil_copper, 2)),
						new AnvilOutput(new ItemStack(Ingots.ingot_steel, 4))
						}).setTier(3));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.yellow_barrel), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.tank_steel, 1)),
						new AnvilOutput(new ItemStack(ModItems.plate_lead, 2)),
						new AnvilOutput(new ItemStack(ModItems.nuclear_waste, 9))
				}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new ComparableStack(ModBlocks.vitrified_barrel), new AnvilOutput[] {
						new AnvilOutput(new ItemStack(ModItems.tank_steel, 1)),
						new AnvilOutput(new ItemStack(ModItems.plate_lead, 2)),
						new AnvilOutput(new ItemStack(ModItems.nuclear_waste_vitrified, 9))
				}).setTier(3));
	}
	
	public static void pullFromAssembler(ComparableStack result, int tier) {
		
		AStack[] ingredients = AssemblerRecipes.recipes.get(result);
		
		if(ingredients != null) {
			constructionRecipes.add(new AnvilConstructionRecipe(ingredients, new AnvilOutput(result.toStack())).setTier(tier));
		}
	}
	
	public static List<AnvilSmithingRecipe> getSmithing() {
		return smithingRecipes;
	}
	
	public static List<AnvilConstructionRecipe> getConstruction() {
		return constructionRecipes;
	}

	public static boolean removeConstructionRecipe(ItemStack[] outputs) {
		start:
		for(AnvilConstructionRecipe constructionRecipe : constructionRecipes) {
			// check length same
			if(constructionRecipe.output.size() != outputs.length) continue;
			// check outputs same
			for(int i = 0; i < outputs.length; i++) {
				if(!areItemStacksEqual(constructionRecipe.output.get(i).stack,outputs[i])){
					continue start;
				}
			}
			CraftTweakerAPI.logInfo("remove anvil recipe"+ constructionRecipe );
			constructionRecipes.remove(constructionRecipe);
			return true;

		}
		return false;
	}

	public static boolean removeConstructionRecipeByInput(AStack[] inputs) {
		start:
		for(AnvilConstructionRecipe constructionRecipe : constructionRecipes) {
			// check length same
			if(constructionRecipe.input.size() != inputs.length) continue;
			// check outputs same
			for(int i = 0; i < inputs.length; i++) {
				if(!inputs[i].equals(constructionRecipe.input.get(i))){
					continue start;
				}
			}
			CraftTweakerAPI.logInfo("remove anvil recipe"+ constructionRecipe );
			constructionRecipes.remove(constructionRecipe);
			return true;
		}
		return false;
	}

	public static void addConstructionRecipe(AStack[] inputs, ItemStack[] output, int tier) {
		AnvilOutput[] anvilOutputs = new AnvilOutput[output.length];
		for(int i = 0; i < output.length; i++) {
			anvilOutputs[i] = new AnvilOutput(output[i]);
		}
		constructionRecipes.add(new AnvilConstructionRecipe(inputs, anvilOutputs).setTier(tier));
	}

	public static class AnvilConstructionRecipe {
		public List<AStack> input = new ArrayList<>();
		public List<AnvilOutput> output = new ArrayList<>();
		public int tierLower = 0;
		public int tierUpper = -1;
		OverlayType overlay = OverlayType.NONE;
		
		public AnvilConstructionRecipe(AStack input, AnvilOutput output) {
			this.input.add(input);
			this.output.add(output);
			this.setOverlay(OverlayType.SMITHING); //preferred overlay for 1:1 conversions is smithing
		}
		
		public AnvilConstructionRecipe(AStack[] input, AnvilOutput output) {
			for(AStack stack : input) this.input.add(stack);
			this.output.add(output);
			this.setOverlay(OverlayType.CONSTRUCTION); //preferred overlay for many:1 conversions is construction
		}
		
		public AnvilConstructionRecipe(AStack input, AnvilOutput[] output) {
			this.input.add(input);
			for(AnvilOutput out : output) this.output.add(out);
			this.setOverlay(OverlayType.RECYCLING); //preferred overlay for 1:many conversions is recycling
		}
		
		public AnvilConstructionRecipe(AStack[] input, AnvilOutput[] output) {
			for(AStack stack : input) this.input.add(stack);
			for(AnvilOutput out : output) this.output.add(out);
			this.setOverlay(OverlayType.NONE); //no preferred overlay for many:many conversions
		}
		
		public AnvilConstructionRecipe setTier(int tier) {
			this.tierLower = tier;
			if(GeneralConfig.enableBabyMode) this.tierLower = 1;
			return this;
		}
		
		public AnvilConstructionRecipe setTierRange(int lower, int upper) {
			this.tierLower = lower;
			this.tierUpper = upper;
			if(GeneralConfig.enableBabyMode) this.tierLower = this.tierUpper = 1;
			return this;
		}
		
		public boolean isTierValid(int tier) {
			
			if(this.tierUpper == -1)
				return tier >= this.tierLower;
			
			return tier >= this.tierLower && tier <= this.tierUpper;
		}
		
		public AnvilConstructionRecipe setOverlay(OverlayType overlay) {
			this.overlay = overlay;
			return this;
		}
		
		public OverlayType getOverlay() {
			return this.overlay;
		}
		
		public ItemStack getDisplay() {
			switch(this.overlay) {
			case NONE: return this.output.get(0).stack.copy();
			case CONSTRUCTION: return this.output.get(0).stack.copy();
			case SMITHING: return this.output.get(0).stack.copy();
			case RECYCLING:
				for(AStack stack : this.input) {
					if(stack instanceof ComparableStack)
						return ((ComparableStack)stack).toStack();
				}
				return this.output.get(0).stack.copy();
			default: return new ItemStack(Items.IRON_PICKAXE);
			}
		}
	}
	
	public static class AnvilOutput {
		public ItemStack stack;
		public float chance;
		
		public AnvilOutput(ItemStack stack) {
			this(stack, 1F);
		}
		
		public AnvilOutput(ItemStack stack, float chance) {
			this.stack = stack;
			this.chance = chance;
		}
	}
	
	public static enum OverlayType {
		NONE,
		CONSTRUCTION,
		RECYCLING,
		SMITHING;
	}
}