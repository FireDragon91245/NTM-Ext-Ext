package com.hbm.entity.missile;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityMissileN2 extends EntityMissileBaseAdvanced {

	public EntityMissileN2(World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 11F);
	}

	public EntityMissileN2(World world, float x, float y, float z, int a, int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 11F);
	}

	@Override
	public void onImpact() {
   	
    	this.world.spawnEntity(EntityNukeExplosionMK5.statFacNoRad(world, (int)(BombConfig.n2Radius/12) * 5, posX, posY, posZ));
    	if(BombConfig.enableNukeClouds) {
			EntityNukeTorex.statFac(world, this.posX, this.posY, this.posZ, (int)(BombConfig.n2Radius/12) * 5);
		}
	}

	@Override
	public List<ItemStack> getDebris() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(ModItems.plate_titanium, 16));
		list.add(new ItemStack(ModItems.plate_steel, 20));
		list.add(new ItemStack(ModItems.plate_aluminium, 12));
		list.add(new ItemStack(ModItems.thruster_large, 1));
		list.add(new ItemStack(ModItems.circuit_targeting_tier4, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return new ItemStack(ModItems.warhead_n2);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER4;
	}
}
