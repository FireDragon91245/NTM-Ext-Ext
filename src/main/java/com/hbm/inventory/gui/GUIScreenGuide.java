package com.hbm.inventory.gui;

import com.hbm.items.tool.ItemGuideBook.BookType;
import com.hbm.items.tool.ItemGuideBook.GuidePage;
import com.hbm.lib.RefStrings;
import com.hbm.util.I18nUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.math.NumberUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GUIScreenGuide extends GuiScreen {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/book/book.png");
	private static final ResourceLocation texture_cover = new ResourceLocation(RefStrings.MODID + ":textures/gui/book/book_cover.png");

	protected int xSize;
	protected int ySize;
	protected int guiLeft;
	protected int guiTop;
	
	private BookType type;
	
	int page;
	int maxPage;

	public GUIScreenGuide(EntityPlayer player) {
		
		type = BookType.getType(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage());
		
		page = -1;
		maxPage = (int)Math.ceil(type.pages.size() / 2D) - 1;

		this.xSize = 272;
		this.ySize = 182;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		this.drawDefaultBackground();
		this.drawGuiContainerBackgroundLayer(f, mouseX, mouseY);
		GlStateManager.disableLighting();
		this.drawGuiContainerForegroundLayer(mouseX, mouseY);
		GlStateManager.enableLighting();
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GlStateManager.color(1, 1, 1, 1);
		
		if(page < 0) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture_cover);
			drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, 512, 512);
			return;
		}
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, 512, 512);

		boolean overLeft = i >= guiLeft + 24 && i < guiLeft + 42 && j >= guiTop + 155 && j < guiTop + 165;
		boolean overRight = i >= guiLeft + 230 && i < guiLeft + 248 && j >= guiTop + 155 && j < guiTop + 165;

		if(this.page > 0) {
			
			if(!overLeft)
				drawModalRectWithCustomSizedTexture(guiLeft + 24, guiTop + 155, 3, 207, 18, 10, 512, 512);
			else
				drawModalRectWithCustomSizedTexture(guiLeft + 24, guiTop + 155, 26, 207, 18, 10, 512, 512);
		}
		
		if(this.page < this.maxPage) {
			
			if(!overRight)
				drawModalRectWithCustomSizedTexture(guiLeft + 230, guiTop + 155, 3, 194, 18, 10, 512, 512);
			else
				drawModalRectWithCustomSizedTexture(guiLeft + 230, guiTop + 155, 26, 194, 18, 10, 512, 512);
		}
	}

	public static void drawImage(int x, int y, int dimX, int dimY) {
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		GlStateManager.color(1, 1, 1, 1);
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buf.pos(x,			y + dimY,	0.0D).tex(0,	1).endVertex();
		buf.pos(x + dimX,	y + dimY,	0.0D).tex(1,	1).endVertex();
		buf.pos(x + dimX,	y,			0.0D).tex(1,	0).endVertex();
		buf.pos(x,			y,			0.0D).tex(0,	0).endVertex();
		tessellator.draw();
	}

	protected void drawGuiContainerForegroundLayer(int x, int y) {
		
		if(this.page < 0) {
			
			float scale = this.type.titleScale;
			String[] coverLines = I18nUtil.resolveKeyArray(this.type.title);
			
			for(int i = 0; i < coverLines.length; i++) {
				
				String cover = coverLines[i];
				
				GL11.glPushMatrix();
				GL11.glScalef(scale, scale, 1F);
				this.fontRenderer.drawString(cover, (int)((guiLeft + ((this.xSize / 2) - (this.fontRenderer.getStringWidth(cover) / 2 * scale))) / scale), (int)((guiTop + 50 + i * 10 * scale) / scale), 0xfece00);
				GL11.glPopMatrix();
			}
			
			return;
		}
		
		int sideOffset = 130;
		
		for(int i = 0; i < 2; i++) {
			
			int defacto = this.page * 2 + i;
			
			if(defacto < this.type.pages.size()) {
				
				GuidePage page = this.type.pages.get(defacto);
				
				float scale = page.scale;
				String text = I18nUtil.resolveKey(page.text);
				int width = 100;
				
				int widthScaled = (int) (width * scale);
				List<String> lines = new ArrayList<>();
				String[] words = text.split(" ");
				
				lines.add(words[0]);
				int indent = this.fontRenderer.getStringWidth(words[0]);
				
				for(int w = 1; w < words.length; w++) {
					
					indent += this.fontRenderer.getStringWidth(" " + words[w]);
					
					if(indent <= widthScaled) {
						String last = lines.get(lines.size() - 1);
						lines.set(lines.size() - 1, last += (" " + words[w]));
					} else {
						lines.add(words[w]);
						indent = this.fontRenderer.getStringWidth(words[w]);
					}
				}
				
				float titleScale = getOverrideScale(page.titleScale, page.title + ".scale");
				
				GL11.glPushMatrix();
				GL11.glScalef(1F/scale, 1F/scale, 1F);
				
				float topOffset = page.title == null ? 0 : 6 / titleScale;
				
				for(int l = 0; l < lines.size(); l++) {
					this.fontRenderer.drawString(lines.get(l), (int)((guiLeft + 20 + i * sideOffset) * scale), (int)((guiTop + 30 + topOffset) * scale + (12 * l)), 4210752);
				}
				
				GL11.glPopMatrix();
				
				if(page.title != null) {
					
					float tScale = titleScale;
					String titleLoc = I18nUtil.resolveKey(page.title);
					
					GL11.glPushMatrix();
					GL11.glScalef(1F/tScale, 1F/tScale, 1F);
					this.fontRenderer.drawString(titleLoc, (int)((guiLeft + 20 + i * sideOffset + ((width / 2) - (this.fontRenderer.getStringWidth(titleLoc) / 2 / tScale))) * tScale), (int)((guiTop + 20) * tScale), page.titleColor);
					
					GL11.glPopMatrix();
				}
				
				if(page.image != null) {
					GL11.glColor4f(1F, 1F, 1F, 1F);
					Minecraft.getMinecraft().getTextureManager().bindTexture(page.image);
					
					int ix = page.x;
					
					if(ix == -1)
						ix = width / 2 - page.sizeX / 2;
					
					drawImage(guiLeft + 20 + ix + sideOffset * i, guiTop + page.y, page.sizeX, page.sizeY);
				}
				
				String pageLabel = (defacto + 1) + "/" + (this.type.pages.size());
				this.fontRenderer.drawString(pageLabel, guiLeft + 44 + i * 185 - i * this.fontRenderer.getStringWidth(pageLabel), guiTop + 156, 4210752);
			}
		}
	}
	
	private float getOverrideScale(float def, String tag) {
		
		String scale = I18nUtil.resolveKey(tag);
		
		if(NumberUtils.isCreatable(scale)) {
			return 1F / NumberUtils.toFloat(scale);
		}
		
		return def;
	}

	@Override
	protected void mouseClicked(int i, int j, int k) {
		
		if(page < 0) {
			page = 0;
			mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			return;
		}
		
		boolean overLeft = i >= guiLeft + 24 && i < guiLeft + 42 && j >= guiTop + 155 && j < guiTop + 165;
		boolean overRight = i >= guiLeft + 230 && i < guiLeft + 248 && j >= guiTop + 155 && j < guiTop + 165;
		
		if(overLeft && page > 0) {
			page--;
			mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
		}
		
		if(overRight && page < maxPage) {
			page++;
			mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
		}
	}

	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		if(p_73869_2_ == 1 || p_73869_2_ == Minecraft.getMinecraft().gameSettings.keyBindInventory.getKeyCode()) {
			Minecraft.getMinecraft().player.closeScreen();
		}
	}
}