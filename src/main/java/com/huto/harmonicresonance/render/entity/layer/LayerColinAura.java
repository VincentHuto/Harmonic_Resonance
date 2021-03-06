package com.huto.harmonicresonance.render.entity.layer;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.entity.passive.EntityColin;
import com.huto.harmonicresonance.model.entity.ModelColin;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class LayerColinAura extends LayerRenderer<EntityColin, ModelColin> {
	private static final ResourceLocation COLIN_ARMOR = new ResourceLocation(
			HarmonicResonance.MOD_ID + ":textures/entity/colin/colin_armor2.png");
	private final ModelColin colinModel = new ModelColin();

	public LayerColinAura(IEntityRenderer<EntityColin, ModelColin> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			EntityColin entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		if (entitylivingbaseIn.isArmored()) {
			float f = (float) entitylivingbaseIn.ticksExisted + partialTicks;
			colinModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.getEntityModel().copyModelAttributesTo(colinModel);
			float swirlSpeedMod = 0.01f;
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEnergySwirl(COLIN_ARMOR, f * swirlSpeedMod, f * 0.01F));
			colinModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
					headPitch);
			colinModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F,
					0.5F);
		}
	}

}
