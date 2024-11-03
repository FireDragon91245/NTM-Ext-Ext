package com.hbm.inventory;

import com.hbm.inventory.leafia.LeafiaRecipeUnlocker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotMachineOutput extends SlotItemHandler {
	public SlotMachineOutput(IItemHandler inventory, int i, int j, int k) {
		super(inventory, i, j, k);
	}

	@Override
	public ItemStack onTake(EntityPlayer thePlayer,ItemStack stack) {
		LeafiaRecipeUnlocker.machineOutputTake(thePlayer,thePlayer.openContainer.windowId,stack);
		return super.onTake(thePlayer,stack);
	}

	@Override
	public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }
}
