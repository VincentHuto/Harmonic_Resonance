package com.huto.harmonicresonance.model.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelFloatingDial extends Model {
	// fields
	final ModelRenderer Base;
	final ModelRenderer Shape1;

	public ModelFloatingDial() {
		super(RenderType::getEntitySolid);

		textureWidth = 16;
		textureHeight = 16;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 1, 1, 4);
		Base.setRotationPoint(0F, 0F, 0F);
		Base.setTextureSize(16, 16);
		Base.mirror = true;
		Shape1 = new ModelRenderer(this, 0, 0);

		Shape1.addBox(0F, 0F, 0F, 1, 1, 4);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(16, 16);
		Shape1.mirror = true;
		Shape1.rotateAngleX = -0.6246007F;

	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		Base.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		Shape1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}

}
