package com.hbm.inventory.gui;

import com.hbm.inventory.container.ContainerNukeSolinium;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.bomb.TileEntityNukeSolinium;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUINukeSolinium extends GuiContainer {
	
	private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/soliniumSchematic.png");
	private TileEntityNukeSolinium testNuke;
	
	public GUINukeSolinium(InventoryPlayer invPlayer, TileEntityNukeSolinium tedf) {
		super(new ContainerNukeSolinium(invPlayer, tedf));
		testNuke = tedf;
		
		this.xSize = 176;
		this.ySize = 222;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer( int i, int j) {
		String name = this.testNuke.hasCustomInventoryName() ? this.testNuke.getInventoryName() : I18n.format(this.testNuke.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if(this.testNuke.inventory.getStackInSlot(0).getItem() == ModItems.solinium_igniter)
		{
			drawTexturedModalRect(guiLeft + 24, guiTop + 84, 0, 222, 22, 14);
		}
		
		if(this.testNuke.inventory.getStackInSlot(1).getItem() == ModItems.solinium_propellant)
		{
			drawTexturedModalRect(guiLeft + 46, guiTop + 84, 22, 222, 18, 14);
		}
		
		if(this.testNuke.inventory.getStackInSlot(2).getItem() == ModItems.solinium_propellant)
		{
			drawTexturedModalRect(guiLeft + 76, guiTop + 84, 52, 222, 18, 14);
		}
		
		if(this.testNuke.inventory.getStackInSlot(3).getItem() == ModItems.solinium_igniter)
		{
			drawTexturedModalRect(guiLeft + 94, guiTop + 84, 70, 222, 22, 14);
		}
		
		if(this.testNuke.inventory.getStackInSlot(4).getItem() == ModItems.solinium_core)
		{
			drawTexturedModalRect(guiLeft + 64, guiTop + 84, 40, 222, 12, 28);
		}
		
		if(this.testNuke.inventory.getStackInSlot(5).getItem() == ModItems.solinium_igniter)
		{
			drawTexturedModalRect(guiLeft + 24, guiTop + 98, 0, 236, 22, 14);
		}
		
		if(this.testNuke.inventory.getStackInSlot(6).getItem() == ModItems.solinium_propellant)
		{
			drawTexturedModalRect(guiLeft + 46, guiTop + 98, 22, 236, 18, 14);
		}
		
		if(this.testNuke.inventory.getStackInSlot(7).getItem() == ModItems.solinium_propellant)
		{
			drawTexturedModalRect(guiLeft + 76, guiTop + 98, 52, 236, 18, 14);
		}
		
		if(this.testNuke.inventory.getStackInSlot(8).getItem() == ModItems.solinium_igniter)
		{
			drawTexturedModalRect(guiLeft + 94, guiTop + 98, 70, 236, 22, 14);
		}
		
		if(this.testNuke.isReady()) {
			drawTexturedModalRect(guiLeft + 134, guiTop + 90, 176, 0, 16, 16);
		}
	}
}