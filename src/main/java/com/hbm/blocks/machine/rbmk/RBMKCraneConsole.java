package com.hbm.blocks.machine.rbmk;

import com.hbm.blocks.BlockDummyable;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.interfaces.IBomb;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKCraneConsole;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class RBMKCraneConsole extends BlockDummyable implements IBomb {

	public RBMKCraneConsole(String s) {
		super(Material.IRON, s);
		this.setHardness(3F);
		this.setResistance(30F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if(meta >= offset)
			return new TileEntityRBMKCraneConsole();
		return null;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {1, 0, 0, 0, 1, 1};
	}

	@Override
	public int getOffset() {
		return 1;
	}

	@Override
	public void fillSpace(World world, int x, int y, int z, ForgeDirection dir, int o) {
		super.fillSpace(world, x, y, z, dir, o);

		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y, z + dir.offsetZ * o, new int[] {0, 0, 0, 1, 1, 1}, this, dir);
	}

	@Override
	protected boolean checkRequirement(World world, int x, int y, int z, ForgeDirection dir, int o) {
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {0, 0, 0, 1, 1, 1}, x, y, z, dir))
			return false;
		
		return super.checkRequirement(world, x, y, z, dir, o);
	}

	@Override
	public void explode(World world, BlockPos pos) {
		if(!world.isRemote) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			world.destroyBlock(pos, false);
			world.spawnEntity(EntityNukeExplosionMK5.statFac(world, BombConfig.fatmanRadius, x + 0.5, y + 0.5, z + 0.5));
			EntityNukeTorex.statFac(world, x + 0.5, y + 0.5, z + 0.5, BombConfig.fatmanRadius);
		}
	}
}
