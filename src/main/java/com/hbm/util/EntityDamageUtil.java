package com.hbm.util;

import com.hbm.handler.ArmorModHandler;
import com.hbm.items.ModItems.Inserts;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

public class EntityDamageUtil {
	
	public static Field lastDamage = null;
	
	@SuppressWarnings("deprecation")
	public static boolean attackEntityFromIgnoreIFrame(Entity victim, DamageSource src, float damage) {

		if(!victim.attackEntityFrom(src, damage)) {
			try {
				if(lastDamage == null)
					lastDamage = ReflectionHelper.findField(EntityLivingBase.class, "lastDamage", "field_110153_bc");
				
				float dmg = (float) damage + lastDamage.getFloat(victim);
				
				return victim.attackEntityFrom(src, dmg);
			} catch (Exception x) {
				return false;
			}
		} else {
			return true;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static float getLastDamage(Entity victim) {
		try {
			if(lastDamage == null)
				lastDamage = ReflectionHelper.findField(EntityLivingBase.class, "lastDamage", "field_110153_bc");

			return lastDamage.getFloat(victim);
		} catch(Exception x) {
			return 0F;
		}
	}
	
	public static boolean wasAttackedByV1(DamageSource source) {

		if(source instanceof EntityDamageSource) {
			Entity attacker = ((EntityDamageSource) source).getImmediateSource();
			
			if(attacker instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) attacker;
				ItemStack chestplate = player.inventory.armorInventory.get(2);
				
				if(chestplate != null && ArmorModHandler.hasMods(chestplate)) {
					ItemStack[] mods = ArmorModHandler.pryMods(chestplate);
					
					if(mods[ArmorModHandler.extra] != null && mods[ArmorModHandler.extra].getItem() == Inserts.v1) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
