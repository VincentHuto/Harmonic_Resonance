package com.huto.harmonicresonance.render.entity;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.entity.passive.EntityIbis;
import com.huto.harmonicresonance.model.entity.ModelIbis;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RenderIbis extends MobRenderer<EntityIbis, ModelIbis> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(HarmonicResonance.MOD_ID,
			"textures/entity/denizen/model_denizen.png");

	public RenderIbis(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new ModelIbis(), 0.2f);

	}

	@Override
	public ResourceLocation getEntityTexture(EntityIbis entity) {
		return entity.getIbisTypeName();

	}

	@Override
	protected float handleRotationFloat(EntityIbis livingBase, float partialTicks) {
		float f = Mth.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
		float f1 = Mth.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
		return (Mth.sin(f) + 1.0F) * f1;
	}

}
