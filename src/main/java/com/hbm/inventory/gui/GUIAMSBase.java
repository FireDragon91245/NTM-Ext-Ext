package com.hbm.inventory.gui;

import com.hbm.forgefluid.FFUtils;
import com.hbm.inventory.container.ContainerAMSBase;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityAMSBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIAMSBase extends GuiInfoContainer {

	private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_ams_base.png");
	private TileEntityAMSBase base;
	
	public GUIAMSBase(InventoryPlayer invPlayer, TileEntityAMSBase tedf) {
		super(new ContainerAMSBase(invPlayer, tedf));
		base = tedf;
		
		this.xSize = 176;
		this.ySize = 222;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);

		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 26, guiTop + 70 - 52, 16, 52, base.tanks[0], base.tankTypes[0]);
		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 134, guiTop + 70 - 52, 16, 52, base.tanks[1], base.tankTypes[1]);
		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 26, guiTop + 124 - 52, 16, 52, base.tanks[2], base.tankTypes[2]);
		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 134, guiTop + 124 - 52, 16, 52, base.tanks[3], base.tankTypes[3]);
		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 116, guiTop + 124 - 104, 7, 104, base.power, TileEntityAMSBase.maxPower);
		this.drawCustomInfo(this, mouseX, mouseY, guiLeft + 44, guiTop + 124 - 106, 7, 106, new String[] { "Restriction Field:", base.field + "%" });
		this.drawCustomInfo(this, mouseX, mouseY, guiLeft + 53, guiTop + 124 - 106, 7, 106, new String[] { "Efficiency:", base.efficiency + "%" });
		this.drawCustomInfo(this, mouseX, mouseY, guiLeft + 125, guiTop + 124 - 106, 7, 106, new String[] { "Heat:", base.heat + "/" + TileEntityAMSBase.maxHeat });

		if(!base.hasResonators()) {
			String[] text = new String[] { "Error: Three satellite ID-chips linked",
				"to xenium resonators are required",
				"for this machine to work!" };
			this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36, 16, 16, guiLeft - 8, guiTop + 36 + 16, text);
		}
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer( int i, int j) {
		String name = this.base.hasCustomInventoryName() ? this.base.getInventoryName() : I18n.format(this.base.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int i = (int)base.getPowerScaled(106);
		drawTexturedModalRect(guiLeft + 116, guiTop + 124 - i, 206, 106 - i, 7, i);
		
		int j = base.getFieldScaled(106);
		drawTexturedModalRect(guiLeft + 44, guiTop + 124 - j, 192, 106 - j, 7, j);
		
		int k = base.getEfficiencyScaled(106);
		drawTexturedModalRect(guiLeft + 53, guiTop + 124 - k, 199, 106 - k, 7, k);
		
		int l = base.getHeatScaled(106);
		drawTexturedModalRect(guiLeft + 125, guiTop + 124 - l, 213, 106 - l, 7, l);
		
		int m = base.mode;
		if(m > 0)
			drawTexturedModalRect(guiLeft + /*80*/89, guiTop + /*108*/18, 176, 32 + 16 * m, 16, 16);
		
		int n = base.warning;
		if(n > 0)
			drawTexturedModalRect(guiLeft + /*80*/71, guiTop + 18, 176, /*32*/-16 + 16 * n, 16, 16);
		
		if(base.color > -1) {
			GL11.glColor3ub((byte)((base.color & 0xFF0000) >> 16), (byte)((base.color & 0x00FF00) >> 8), (byte)((base.color & 0x0000FF) >> 0));
			drawTexturedModalRect(guiLeft + 61, guiTop + 44, 176, 160, 54, 54);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(guiLeft + 61, guiTop + 44, 176, 106, 54, 54);
		}

		if(!base.hasResonators())
			this.drawInfoPanel(guiLeft - 16, guiTop + 36, 16, 16, 6);

		FFUtils.drawLiquid(base.tanks[0], guiLeft, guiTop, zLevel, 16, 52, 26, 98);
		FFUtils.drawLiquid(base.tanks[1], guiLeft, guiTop, zLevel, 16, 52, 134, 98);
		FFUtils.drawLiquid(base.tanks[2], guiLeft, guiTop, zLevel, 16, 52, 26, 152);
		FFUtils.drawLiquid(base.tanks[3], guiLeft, guiTop, zLevel, 16, 52, 134, 152);
	}
}
