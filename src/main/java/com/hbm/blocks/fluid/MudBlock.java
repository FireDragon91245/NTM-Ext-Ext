package com.hbm.blocks.fluid;

import com.hbm.blocks.ModBlocks;
import com.hbm.handler.ArmorUtil;
import com.hbm.lib.ModDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

public class MudBlock extends BlockFluidClassic {

	public static DamageSource damageSource;
	public Random rand = new Random();
	
	public MudBlock(Fluid fluid, Material material, DamageSource d, String s) {
		super(fluid, material);
		damageSource = d;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setQuantaPerBlock(4);
		this.setCreativeTab(null);
		displacements.put(this, false);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, pos);
	}
	
	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}
	
	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		entity.setInWeb();

		if (entity instanceof EntityPlayer && ArmorUtil.checkForHazmat((EntityPlayer) entity)) {} else {
			entity.attackEntityFrom(ModDamageSource.mudPoisoning, 8);
		}
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		reactToBlocks2(world, pos.east());
		reactToBlocks2(world, pos.west());
		reactToBlocks2(world, pos.up());
		reactToBlocks2(world, pos.down());
		reactToBlocks2(world, pos.south());
		reactToBlocks2(world, pos.north());
		super.updateTick(world, pos, state, rand);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos neighbourPos) {
		reactToBlocks(world, pos.east());
		reactToBlocks(world, pos.west());
		reactToBlocks(world, pos.up());
		reactToBlocks(world, pos.down());
		reactToBlocks(world, pos.south());
		reactToBlocks(world, pos.north());
		super.neighborChanged(state, world, pos, neighborBlock, neighbourPos);
	}
	
	public void reactToBlocks(World world, BlockPos pos) {
		if(world.getBlockState(pos).getMaterial() != ModBlocks.fluidmud) {
			IBlockState block = world.getBlockState(pos);
			
			if(block.getMaterial().isLiquid()) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void reactToBlocks2(World world, BlockPos pos) {
		if(world.getBlockState(pos).getMaterial() != ModBlocks.fluidmud) {
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();

			if (block == Blocks.STONE || 
					block == Blocks.STONE_BRICK_STAIRS || 
					block == Blocks.STONEBRICK || 
					block == Blocks.STONE_SLAB || 
					block == Blocks.STONE) {
				if(rand.nextInt(20) == 0)
					world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
			} else if (block == Blocks.COBBLESTONE) {
				if(rand.nextInt(15) == 0)
					world.setBlockState(pos, Blocks.GRAVEL.getDefaultState());
			} else if (block == Blocks.SANDSTONE) {
				if(rand.nextInt(5) == 0)
					world.setBlockState(pos, Blocks.SAND.getDefaultState());
			} else if (block == Blocks.HARDENED_CLAY || 
					block == Blocks.STAINED_HARDENED_CLAY) {
				if(rand.nextInt(10) == 0)
					world.setBlockState(pos, Blocks.CLAY.getDefaultState());
			} else if (state.getMaterial() == Material.WOOD) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.CACTUS) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.CAKE) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.CIRCUITS) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.CLOTH) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.CORAL) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.CRAFTED_SNOW) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.GLASS) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.GOURD) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.ICE) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.LEAVES) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.PACKED_ICE) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.PISTON) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.PLANTS) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.PORTAL) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.REDSTONE_LIGHT) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.SNOW) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.SPONGE) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.VINE) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (state.getMaterial() == Material.WEB) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else if (block.getExplosionResistance(null) < 1.2F) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}
	
	@Override
	public int tickRate(World world) {
		return 15;
	}
	
}
