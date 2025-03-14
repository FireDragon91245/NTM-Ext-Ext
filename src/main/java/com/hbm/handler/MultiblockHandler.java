package com.hbm.handler;

import com.hbm.interfaces.IDummy;
import com.hbm.tileentity.machine.TileEntityDummy;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class MultiblockHandler {

	public enum EnumDirection { North, East, South, West };
	//                            2      5     3      4
	//                           -z     +x    +z     -x

	public static int EnumToInt(EnumDirection dir) {
		if(dir == EnumDirection.North)
			return 2;
		if(dir == EnumDirection.East)
			return 5;
		if(dir == EnumDirection.South)
			return 3;
		if(dir == EnumDirection.West)
			return 4;
		return 0;
	}
	public static EnumDirection IntToEnum(int dir) {
		if(dir == 2)
			return EnumDirection.North;
		if(dir == 5)
			return EnumDirection.East;
		if(dir == 3)
			return EnumDirection.South;
		if(dir == 4)
			return EnumDirection.West;
		return EnumDirection.North;
	}
	
	public static EnumFacing intToEnumFacing(int dir){
		if(dir == 2)
			return EnumFacing.NORTH;
		if(dir == 5)
			return EnumFacing.EAST;
		if(dir == 3)
			return EnumFacing.SOUTH;
		if(dir == 4)
			return EnumFacing.WEST;
		return EnumFacing.NORTH;
	}

	//Approved!
	//pos x, neg x, pos y, neg y, pos z, neg z
	public static final int[] iGenDimensionNorth = new int[] { 1, 1, 2, 0, 3, 2 };
	public static final int[] iGenDimensionEast  = new int[] { 2, 3, 2, 0, 1, 1 };
	public static final int[] iGenDimensionSouth = new int[] { 1, 1, 2, 0, 2, 3 };
	public static final int[] iGenDimensionWest  = new int[] { 3, 2, 2, 0, 1, 1 };
	public static final int[] centDimension  = new int[] { 0, 0, 3, 0, 0, 0 };
	public static final int[] cyclDimension  = new int[] { 1, 1, 5, 0, 1, 1 };
	public static final int[] wellDimension  = new int[] { 1, 1, 5, 0, 1, 1 };
	public static final int[] flareDimension  = new int[] { 1, 1, 11, 0, 1, 1 };
	public static final int[] drillDimension  = new int[] { 1, 1, 3, 0, 1, 1 };
	public static final int[] assemblerDimensionNorth = new int[] { 2, 1, 1, 0, 1, 2 };
	public static final int[] assemblerDimensionEast  = new int[] { 2, 1, 1, 0, 2, 1 };
	public static final int[] assemblerDimensionSouth = new int[] { 1, 2, 1, 0, 2, 1 };
	public static final int[] assemblerDimensionWest  = new int[] { 1, 2, 1, 0, 1, 2 };
	public static final int[] chemplantDimensionNorth = new int[] { 2, 1, 2, 0, 1, 2 };
	public static final int[] chemplantDimensionEast  = new int[] { 2, 1, 2, 0, 2, 1 };
	public static final int[] chemplantDimensionSouth = new int[] { 1, 2, 2, 0, 2, 1 };
	public static final int[] chemplantDimensionWest  = new int[] { 1, 2, 2, 0, 1, 2 };
	public static final int[] fluidTankDimensionNS = new int[] { 1, 1, 2, 0, 2, 2 };
	public static final int[] fluidTankDimensionEW  = new int[] { 2, 2, 2, 0, 1, 1 };
	public static final int[] refineryDimensions  = new int[] { 1, 1, 8, 0, 1, 1 };
	public static final int[] pumpjackDimensionNorth = new int[] { 1, 1, 4, 0, 6, 0 };
	public static final int[] pumpjackDimensionEast  = new int[] { 0, 6, 4, 0, 1, 1 };
	public static final int[] pumpjackDimensionSouth = new int[] { 1, 1, 4, 0, 0, 6 };
	public static final int[] pumpjackDimensionWest  = new int[] { 6, 0, 4, 0, 1, 1 };
	public static final int[] turbofanDimensionNorth = new int[] { 1, 1, 2, 0, 3, 3 };
	public static final int[] turbofanDimensionEast  = new int[] { 3, 3, 2, 0, 1, 1 };
	public static final int[] turbofanDimensionSouth = new int[] { 1, 1, 2, 0, 3, 3 };
	public static final int[] turbofanDimensionWest  = new int[] { 3, 3, 2, 0, 1, 1 };
	public static final int[] AMSLimiterDimensionNorth = new int[] { 0, 0, 5, 0, 2, 2 };
	public static final int[] AMSLimiterDimensionEast  = new int[] { 2, 2, 5, 0, 0, 0 };
	public static final int[] AMSLimiterDimensionSouth = new int[] { 0, 0, 5, 0, 2, 2 };
	public static final int[] AMSLimiterDimensionWest  = new int[] { 2, 2, 5, 0, 0, 0 };
	public static final int[] AMSEmitterDimension  = new int[] { 2, 2, 5, 0, 2, 2, };
	public static final int[] AMSBaseDimension  = new int[] { 1, 1, 1, 0, 1, 1, };
	public static final int[] radGenDimensionNorth = new int[] { 4, 1, 2, 0, 1, 1 };
	public static final int[] radGenDimensionEast  = new int[] { 1, 1, 2, 0, 4, 1 };
	public static final int[] radGenDimensionSouth = new int[] { 1, 4, 2, 0, 1, 1 };
	public static final int[] radGenDimensionWest  = new int[] { 1, 1, 2, 0, 1, 4 };
	public static final int[] reactorSmallDimension  = new int[] { 0, 0, 2, 0, 0, 0 };
	public static final int[] uf6Dimension  = new int[] { 0, 0, 1, 0, 0, 0 };
	
	//Approved!
	public static boolean checkSpace(World world, BlockPos pos, int[] i) {
		boolean placable = true;
		MutableBlockPos replace = new BlockPos.MutableBlockPos();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for(int a = x - i[1]; a <= x + i[0]; a++) {
			for(int b = y - i[3]; b <= y + i[2]; b++) {
				for(int c = z - i[5]; c <= z + i[4]; c++) {
					if(!(a == x && b == y && c == z)) {
						Block block = world.getBlockState(replace.setPos(a, b, c)).getBlock();
						if(block != Blocks.AIR && !block.isReplaceable(world, replace)) {
							placable = false;
						}
					}
				}
			}
		}
		
		return placable;
	}
	
	public static boolean fillUp(World world, BlockPos pos, int[] i, Block block) {
		boolean placable = true;
		MutableBlockPos replace = new BlockPos.MutableBlockPos();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for(int a = x - i[1]; a <= x + i[0]; a++) {
			for(int b = y - i[3]; b <= y + i[2]; b++) {
				for(int c = z - i[5]; c <= z + i[4]; c++) {
					if(!(a == x && b == y && c == z)) {
						world.setBlockState(replace.setPos(a, b, c), block.getDefaultState());
						TileEntity te = world.getTileEntity(replace.setPos(a, b, c));
						if(te instanceof TileEntityDummy) {
							TileEntityDummy dummy = (TileEntityDummy)te;
							dummy.target = pos;
						}
					}
				}
			}
		}
		
		return placable;
	}
	
	public static boolean removeAll(World world, BlockPos pos, int[] i) {
		boolean placable = true;
		
		MutableBlockPos replace = new BlockPos.MutableBlockPos();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		for(int a = x - i[1]; a <= x + i[0]; a++) {
			for(int b = y - i[3]; b <= y + i[2]; b++) {
				for(int c = z - i[5]; c <= z + i[4]; c++) {
					if(!(a == x && b == y && c == z)) {
						if(world.getBlockState(replace.setPos(a, b, c)).getBlock() instanceof IDummy)
							if(!world.isRemote) {
								world.destroyBlock(replace.setPos(a, b, c), false);
							}
					}
				}
			}
		}
		
		return placable;
	}
}
