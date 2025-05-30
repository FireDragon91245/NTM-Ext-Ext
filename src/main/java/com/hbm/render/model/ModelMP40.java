package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelMP40 extends ModelBase {

	ModelRenderer Body;
	ModelRenderer Barrel;
	ModelRenderer Scope;
	ModelRenderer ClipPivot;
	ModelRenderer ClipPivotBack;
	ModelRenderer Clip;
	ModelRenderer BarrelBottom;
	ModelRenderer BodyStock;
	ModelRenderer BodyStockBottom;
	ModelRenderer BodyBack;
	ModelRenderer Handle;
	ModelRenderer TriggerFrame;
	ModelRenderer Trigger;
	ModelRenderer Bar;
	ModelRenderer BarFront;

	public ModelMP40() {
		textureWidth = 128;
		textureHeight = 64;

		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(0F, 0F, 0F, 40, 4, 4);
		Body.setRotationPoint(-10F, 0F, -2F);
		Body.setTextureSize(128, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Barrel = new ModelRenderer(this, 88, 0);
		Barrel.addBox(0F, 0F, 0F, 17, 2, 2);
		Barrel.setRotationPoint(-27F, 1F, -1F);
		Barrel.setTextureSize(128, 64);
		Barrel.mirror = true;
		setRotation(Barrel, 0F, 0F, 0F);
		Scope = new ModelRenderer(this, 88, 4);
		Scope.addBox(0F, 0F, 0F, 2, 3, 2);
		Scope.setRotationPoint(-25F, -2F, -1F);
		Scope.setTextureSize(128, 64);
		Scope.mirror = true;
		setRotation(Scope, 0F, 0F, 0F);
		ClipPivot = new ModelRenderer(this, 0, 17);
		ClipPivot.addBox(0F, 0F, 0F, 4, 5, 3);
		ClipPivot.setRotationPoint(-3F, 4F, -1.5F);
		ClipPivot.setTextureSize(128, 64);
		ClipPivot.mirror = true;
		setRotation(ClipPivot, 0F, 0F, 0F);
		ClipPivotBack = new ModelRenderer(this, 14, 17);
		ClipPivotBack.addBox(0F, 0F, 0F, 3, 3, 3);
		ClipPivotBack.setRotationPoint(1F, 4F, -1.5F);
		ClipPivotBack.setTextureSize(128, 64);
		ClipPivotBack.mirror = true;
		setRotation(ClipPivotBack, 0F, 0F, 0F);
		Clip = new ModelRenderer(this, 0, 25);
		Clip.addBox(0F, 0F, 0F, 3, 18, 2);
		Clip.setRotationPoint(-2.5F, 9F, -1F);
		Clip.setTextureSize(128, 64);
		Clip.mirror = true;
		setRotation(Clip, 0F, 0F, 0F);
		BarrelBottom = new ModelRenderer(this, 96, 4);
		BarrelBottom.addBox(0F, 0F, 0F, 14, 1, 1);
		BarrelBottom.setRotationPoint(-24F, 2.5F, -0.5F);
		BarrelBottom.setTextureSize(128, 64);
		BarrelBottom.mirror = true;
		setRotation(BarrelBottom, 0F, 0F, 0F);
		BodyStock = new ModelRenderer(this, 0, 8);
		BodyStock.addBox(0F, 0F, 0F, 26, 4, 5);
		BodyStock.setRotationPoint(4F, 3F, -2.5F);
		BodyStock.setTextureSize(128, 64);
		BodyStock.mirror = true;
		setRotation(BodyStock, 0F, 0F, 0F);
		BodyStockBottom = new ModelRenderer(this, 62, 11);
		BodyStockBottom.addBox(0F, 0F, 0F, 26, 3, 3);
		BodyStockBottom.setRotationPoint(4F, 7F, -1.5F);
		BodyStockBottom.setTextureSize(128, 64);
		BodyStockBottom.mirror = true;
		setRotation(BodyStockBottom, 0F, 0F, 0F);
		BodyBack = new ModelRenderer(this, 10, 25);
		BodyBack.addBox(0F, 0F, 0F, 7, 7, 3);
		BodyBack.setRotationPoint(30F, 0F, -1.5F);
		BodyBack.setTextureSize(128, 64);
		BodyBack.mirror = true;
		setRotation(BodyBack, 0F, 0F, 0.7853982F);
		Handle = new ModelRenderer(this, 30, 17);
		Handle.addBox(0F, 0F, 0F, 4, 10, 3);
		Handle.setRotationPoint(27F, 10F, -1.5F);
		Handle.setTextureSize(128, 64);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, -0.4363323F);
		TriggerFrame = new ModelRenderer(this, 44, 17);
		TriggerFrame.addBox(0F, 0F, 0F, 6, 4, 2);
		TriggerFrame.setRotationPoint(23F, 10F, -1F);
		TriggerFrame.setTextureSize(128, 64);
		TriggerFrame.mirror = true;
		setRotation(TriggerFrame, 0F, 0F, 0F);
		Trigger = new ModelRenderer(this, 26, 17);
		Trigger.addBox(-1F, 0F, 0F, 1, 3, 1);
		Trigger.setRotationPoint(27F, 10F, -0.5F);
		Trigger.setTextureSize(128, 64);
		Trigger.mirror = true;
		setRotation(Trigger, 0F, 0F, 0.4363323F);
		Bar = new ModelRenderer(this, 60, 17);
		Bar.addBox(0F, 0F, 0F, 23, 1, 1);
		Bar.setRotationPoint(7F, 7.5F, -3F);
		Bar.setTextureSize(128, 64);
		Bar.mirror = true;
		setRotation(Bar, 0F, 0F, 0F);
		BarFront = new ModelRenderer(this, 0, 45);
		BarFront.addBox(-2.5F, -0.5F, 0F, 5, 1, 1);
		BarFront.setRotationPoint(7F, 8F, -3F);
		BarFront.setTextureSize(128, 64);
		BarFront.mirror = true;
		setRotation(BarFront, 0F, 0F, 0.7853982F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		Barrel.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Scope.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		ClipPivot.render(f5);
		ClipPivotBack.render(f5);
		Clip.render(f5);
		BarrelBottom.render(f5);
		BodyStock.render(f5);
		BodyStockBottom.render(f5);
		BodyBack.render(f5);
		Handle.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		TriggerFrame.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		Trigger.render(f5);
		Bar.render(f5);
		BarFront.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
