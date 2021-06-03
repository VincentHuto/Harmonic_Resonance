package com.huto.harmonicresonance.render.entity;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.entity.passive.EntityIbis;
import com.huto.harmonicresonance.model.entity.ModelIbis;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderIbis extends MobRenderer<EntityIbis, ModelIbis> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(HarmonicResonance.MOD_ID,
			"textures/entity/denizen/model_denizen.png");

	public RenderIbis(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ModelIbis(), 0.2f);

	}

	@Override
	public ResourceLocation getEntityTexture(EntityIbis entity) {
		return entity.getIbisTypeName();

	}

	@Override
	protected float handleRotationFloat(EntityIbis livingBase, float partialTicks) {
		float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
		float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

}
