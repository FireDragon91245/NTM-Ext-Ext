package com.leafia.contents.network.computers.cable;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class ComputerCableBlock extends BlockContainer {
	public ComputerCableBlock(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.blockTab);

		ModBlocks.ALL_BLOCKS.add(this);
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn,int meta) {
		return new ComputerCableTE();
	}
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
