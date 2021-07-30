package com.huto.harmonicresonance.render.entity.layer;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.entity.passive.EntityDreamWalker;
import com.huto.harmonicresonance.model.armor.ModelMysteriousMaskSleep;
import com.huto.harmonicresonance.model.entity.ModelDreamWalker;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class LayerDreamWalkerMask extends RenderLayer<EntityDreamWalker, ModelDreamWalker> {

	ModelMysteriousMaskSleep mask = new ModelMysteriousMaskSleep();
	public static final ResourceLocation texture = new ResourceLocation(
			HarmonicResonance.MOD_ID + ":textures/models/armor/mysterious_mask_layer_1.png");

	public LayerDreamWalkerMask(RenderLayerParent<EntityDreamWalker, ModelDreamWalker> entityRendererIn) {
		super(entityRendererIn);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn,
			EntityDreamWalker entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		Minecraft.getInstance().textureManager.bindTexture(texture);
		for (int j = 0; j < 1; ++j) {
			float f10 = entitylivingbaseIn.prevRotationYawHead
					+ (entitylivingbaseIn.rotationYawHead - entitylivingbaseIn.prevRotationYawHead) * partialTicks
					- (entitylivingbaseIn.prevRenderYawOffset
							+ (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset)
									* partialTicks);
			float f11 = entitylivingbaseIn.prevRotationPitch
					+ (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTicks;
			matrixStackIn.push();
			if (entitylivingbaseIn.isSneaking()) {
				GlStateManager.translatef(0.0F, 0.24F, 0.0F);
				matrixStackIn.translate(0f, 0.24F, 0f);

			}
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f10));
			matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f11));
			matrixStackIn.translate(0f, 0f, -0.01f);
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(mask.getRenderType(texture));
			mask.renderMask(matrixStackIn, ivertexbuilder, 0, packedLightIn, 0, 0, 0, 1f);
			matrixStackIn.pop();
		}

	}

}
