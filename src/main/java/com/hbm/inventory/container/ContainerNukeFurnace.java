package com.hbm.inventory.container;

import com.hbm.inventory.SlotMachineOutput;
import com.hbm.tileentity.machine.TileEntityNukeFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerNukeFurnace extends Container {
	
	private TileEntityNukeFurnace diFurnace;
	private int dualCookTime;
	private int dualPower;
	
	public ContainerNukeFurnace(InventoryPlayer invPlayer, TileEntityNukeFurnace tedf) {
		dualCookTime = 0;
		dualPower = 0;
		
		diFurnace = tedf;
		
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 56, 53));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 1, 56, 17));
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 2, 116, 35));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendWindowProperty(this, 0, this.diFurnace.dualCookTime);
		crafting.sendWindowProperty(this, 1, this.diFurnace.dualPower);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int par2)
    {
		ItemStack var3 = ItemStack.EMPTY;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
            if (par2 <= 2) {
				if (!this.mergeItemStack(var5, 3, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(var5, 1, 2, false))
			{
				if (!this.mergeItemStack(var5, 0, 1, false))
						return ItemStack.EMPTY;
			}
			
			if (var5.isEmpty())
			{
				var4.putStack(ItemStack.EMPTY);
			}
			else
			{
				var4.onSlotChanged();
			}
		}
		
		return var3;
    }

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return diFurnace.isUseableByPlayer(player);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++)
		{
			IContainerListener par1 = (IContainerListener)this.listeners.get(i);
			
			if(this.dualCookTime != this.diFurnace.dualCookTime)
			{
				par1.sendWindowProperty(this, 0, this.diFurnace.dualCookTime);
			}
			
			if(this.dualPower != this.diFurnace.dualPower)
			{
				par1.sendWindowProperty(this, 1, this.diFurnace.dualPower);
			}
		}
		
		this.dualCookTime = this.diFurnace.dualCookTime;
		this.dualPower = this.diFurnace.dualPower;
	}
	
	@Override
	public void updateProgressBar(int i, int j) {
		if(i == 0)
		{
			diFurnace.dualCookTime = j;
		}
		if(i == 1)
		{
			diFurnace.dualPower = j;
		}
	}
}