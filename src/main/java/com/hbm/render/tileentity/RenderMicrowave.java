package com.hbm.render.tileentity;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMicrowave;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderMicrowave extends TileEntitySpecialRenderer<TileEntityMicrowave> {

	@Override
	public void render(TileEntityMicrowave mic, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y - 0.75, z + 0.5D);
        GlStateManager.enableLighting();

		switch(mic.getBlockMetadata()) {
		case 2: GL11.glRotatef(0, 0F, 1F, 0F); break;
		case 4: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 3: GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 5: GL11.glRotatef(270, 0F, 1F, 0F); break;
		}
        GL11.glTranslated(-0.5D, 0.0D, 0.5D);

		bindTexture(ResourceManager.microwave_tex);
        ResourceManager.microwave.renderPart("mainbody_Cube.001");
        ResourceManager.microwave.renderPart("window_Cube.002");

        double rot = (System.currentTimeMillis() * mic.speed / 10D) % 360;

        if(mic.time > 0) {
	        GL11.glTranslated(0.575D, 0.0D, -0.45D);
			GL11.glRotated(rot, 0F, 1F, 0F);
	        GL11.glTranslated(-0.575D, 0.0D, 0.45D);
        }
        ResourceManager.microwave.renderPart("plate_Cylinder");

        GL11.glPopMatrix();
	}
}
