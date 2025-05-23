package com.hbm.blocks.generic;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockNeoveline extends BlockHazard implements IItemHazard {

	public BlockNeoveline(Material materialIn,String s) {
		super(materialIn,s);
		this.addHydroReactivity(6);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}

	private boolean touchesWater(World world, int x, int y, int z) {

		if(world.isRemote)
			return false;

		return world.getBlockState(new BlockPos(x + 1, y, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x - 1, y, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y + 1, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y, z + 1)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y, z - 1)).getMaterial() == Material.WATER;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(touchesWater(world, pos.getX(), pos.getY(), pos.getZ())) {
			world.destroyBlock(pos, false);
			world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 15, false, true);
		}
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if(touchesWater(world, pos.getX(), pos.getY(), pos.getZ())) {
			world.destroyBlock(pos, false);
			world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 15, false, true);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
		if(world.isRainingAt(pos.up())) {

			float ox = rand.nextFloat();
			float oz = rand.nextFloat();

			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + ox, pos.getY() + 1, pos.getZ() + oz, 0.0D, 0.0D, 0.0D);
		}
	}
}
