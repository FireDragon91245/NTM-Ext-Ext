package com.hbm.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModelJetPack extends ModelBiped {

	ModelRenderer Tank1;
	ModelRenderer Tank2;
	ModelRenderer Tip1;
	ModelRenderer Tip2;
	ModelRenderer Duct1;
	ModelRenderer Duct2;
	ModelRenderer Thruster1;
	ModelRenderer Thruster2;
	ModelRenderer Pack;
	ModelRenderer JetPack;

	public ModelJetPack() {
		textureWidth = 32;
		textureHeight = 32;

		float x = 0F;
		float y = 0F;
		float z = -2F;
		
		JetPack = new ModelRenderer(this, 0, 0);
		JetPack.setRotationPoint(x, y, z);
		
		Pack = new ModelRenderer(this, 12, 10);
		Pack.addBox(0F, 0F, 0F, 4, 6, 1);
		Pack.setRotationPoint(-2F, 3F, 0F);
		Pack.setTextureSize(32, 32);
		Pack.mirror = true;
		convertToChild(JetPack, Pack);
		setRotation(Pack, 0F, 0F, 0F);
		Tank1 = new ModelRenderer(this, 0, 0);
		Tank1.addBox(0F, 0F, 0F, 3, 8, 3);
		Tank1.setRotationPoint(0.5F, 2F, 0.5F);
		Tank1.setTextureSize(32, 32);
		Tank1.mirror = true;
		setRotation(Tank1, 0F, 0F, 0F);
		convertToChild(JetPack, Tank1);
		Tank2 = new ModelRenderer(this, 0, 11);
		Tank2.addBox(0F, 0F, 0F, 3, 8, 3);
		Tank2.setRotationPoint(-3.5F, 2F, 0.5F);
		Tank2.setTextureSize(32, 32);
		Tank2.mirror = true;
		setRotation(Tank2, 0F, 0F, 0F);
		convertToChild(JetPack, Tank2);
		Tip1 = new ModelRenderer(this, 0, 22);
		Tip1.addBox(0F, 0F, 0F, 2, 1, 2);
		Tip1.setRotationPoint(1F, 1F, 1F);
		Tip1.setTextureSize(32, 32);
		Tip1.mirror = true;
		setRotation(Tip1, 0F, 0F, 0F);
		convertToChild(JetPack, Tip1);
		Tip2 = new ModelRenderer(this, 0, 25);
		Tip2.addBox(0F, 0F, 0F, 2, 1, 2);
		Tip2.setRotationPoint(-3F, 1F, 1F);
		Tip2.setTextureSize(32, 32);
		Tip2.mirror = true;
		setRotation(Tip2, 0F, 0F, 0F);
		convertToChild(JetPack, Tip2);
		Duct1 = new ModelRenderer(this, 8, 22);
		Duct1.addBox(0F, 0F, 0F, 2, 1, 2);
		Duct1.setRotationPoint(1F, 9.5F, 1F);
		Duct1.setTextureSize(32, 32);
		Duct1.mirror = true;
		setRotation(Duct1, 0F, 0F, 0F);
		convertToChild(JetPack, Duct1);
		Duct2 = new ModelRenderer(this, 8, 25);
		Duct2.addBox(0F, 0F, 0F, 2, 1, 2);
		Duct2.setRotationPoint(-3F, 9.5F, 1F);
		Duct2.setTextureSize(32, 32);
		Duct2.mirror = true;
		setRotation(Duct2, 0F, 0F, 0F);
		convertToChild(JetPack, Duct2);
		Thruster1 = new ModelRenderer(this, 12, 0);
		Thruster1.addBox(0F, 0F, 0F, 3, 2, 3);
		Thruster1.setRotationPoint(0.5F, 10.5F, 0.5F);
		Thruster1.setTextureSize(32, 32);
		Thruster1.mirror = true;
		setRotation(Thruster1, 0F, 0F, 0F);
		convertToChild(JetPack, Thruster1);
		Thruster2 = new ModelRenderer(this, 12, 5);
		Thruster2.addBox(0F, 0F, 0F, 3, 2, 3);
		Thruster2.setRotationPoint(-3.5F, 10.5F, 0.5F);
		Thruster2.setTextureSize(32, 32);
		Thruster2.mirror = true;
		setRotation(Thruster2, 0F, 0F, 0F);
		convertToChild(JetPack, Thruster2);
	}

	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		//super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		/*Tank1.render(f5);
		Tank2.render(f5);
		Tip1.render(f5);
		Tip2.render(f5);
		Duct1.render(f5);
		Duct2.render(f5);
		Thruster1.render(f5);
		Thruster2.render(f5);*/
		JetPack.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (player.isSneaking()) {
				this.isSneak = true;
			} else {
				this.isSneak = false;
			}
			/*ItemStack itemstack = player.inventory.getCurrentItem();
			this.heldItemRight = itemstack != null ? 1 : 0;

			if (itemstack != null && player.getItemInUseCount() > 0) {
				EnumAction enumaction = itemstack.getItemUseAction();

				if (enumaction == EnumAction.block) {
					this.heldItemRight = 3;
				} else if (enumaction == EnumAction.bow) {
					this.aimedBow = true;
				}
			}*/
		}
		//float s = 1 / 16;

		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.JetPack.rotationPointX = this.bipedBody.rotationPointX;
		this.JetPack.rotationPointY = this.bipedBody.rotationPointY;
		this.JetPack.rotationPointZ = this.bipedBody.rotationPointZ;
		this.JetPack.rotateAngleX = this.bipedBody.rotateAngleX;
		this.JetPack.rotateAngleY = this.bipedBody.rotateAngleY + (float)Math.toRadians(f3);
		this.JetPack.rotateAngleZ = this.bipedBody.rotateAngleZ;
	}

	protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild) {
		parChild.rotationPointX -= parParent.rotationPointX;
		parChild.rotationPointY -= parParent.rotationPointY;
		parChild.rotationPointZ -= parParent.rotationPointZ;
		parChild.rotateAngleX -= parParent.rotateAngleX;
		parChild.rotateAngleY -= parParent.rotateAngleY;
		parChild.rotateAngleZ -= parParent.rotateAngleZ;
		parParent.addChild(parChild);
	}
}
