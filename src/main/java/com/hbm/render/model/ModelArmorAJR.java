package com.hbm.render.model;

import com.hbm.main.ResourceManager;
import com.hbm.render.loader.ModelRendererObj;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelArmorAJR extends ModelArmorBase {

	public ModelArmorAJR(int type) {
		super(type);

		head = new ModelRendererObj(ResourceManager.armor_ajr, "Head");
		body = new ModelRendererObj(ResourceManager.armor_ajr, "Body");
		leftArm = new ModelRendererObj(ResourceManager.armor_ajr, "LeftArm").setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightArm = new ModelRendererObj(ResourceManager.armor_ajr, "RightArm").setRotationPoint(5.0F, 2.0F, 0.0F);
		leftLeg = new ModelRendererObj(ResourceManager.armor_ajr, "LeftLeg").setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg = new ModelRendererObj(ResourceManager.armor_ajr, "RightLeg").setRotationPoint(-1.9F, 12.0F, 0.0F);
		leftFoot = new ModelRendererObj(ResourceManager.armor_ajr, "LeftBoot").setRotationPoint(1.9F, 12.0F, 0.0F);
		rightFoot = new ModelRendererObj(ResourceManager.armor_ajr, "RightBoot").setRotationPoint(-1.9F, 12.0F, 0.0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {

		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

		GL11.glPushMatrix();
		GL11.glShadeModel(GL11.GL_SMOOTH);
		if(this.isChild) {
			GL11.glScalef(0.75F, 0.75F, 0.75F);
			GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
		}
		if(type == 0) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.ajr_helmet);
			head.render(par7*1.001F);
		}
		if(this.isChild) {
			GL11.glScalef(0.75F, 0.75F, 0.75F);
		}
		if(type == 1) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.ajr_chest);
			body.render(par7);
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.ajr_arm);
			leftArm.render(par7);
			rightArm.render(par7);
		}
		if(type == 2) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.ajr_leg);
			leftLeg.render(par7);
			rightLeg.render(par7);
		}
		if(type == 3) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.ajr_leg);
			leftFoot.render(par7);
			rightFoot.render(par7);
		}

		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
	}
}