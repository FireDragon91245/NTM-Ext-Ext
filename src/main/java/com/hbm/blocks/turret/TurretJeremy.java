package com.hbm.blocks.turret;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.turret.TileEntityTurretJeremy;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TurretJeremy extends TurretBaseNT {

	public TurretJeremy(Material materialIn, String s){
		super(materialIn, s);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta){
		if(meta >= 12)
			return new TileEntityTurretJeremy();
		return new TileEntityProxyCombo(true, true, false);
	}

	@Override
	public void openGUI(World world, EntityPlayer player, int x, int y, int z){
		player.openGui(MainRegistry.instance, ModBlocks.guiID_jeremy, world, x, y, z);
	}

}
