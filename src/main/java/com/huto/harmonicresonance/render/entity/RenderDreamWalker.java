package com.huto.harmonicresonance.render.entity;

import javax.annotation.Nonnull;

import com.huto.harmonicresonance.entity.passive.EntityDreamWalker;
import com.huto.harmonicresonance.model.entity.ModelDreamWalker;
import com.huto.harmonicresonance.render.entity.layer.LayerDreamWalkerMask;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderDreamWalker extends MobRenderer<EntityDreamWalker, ModelDreamWalker> {

	public RenderDreamWalker(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new ModelDreamWalker(), 0.5f);
		this.addLayer(new LayerDreamWalkerMask(this));

	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(@Nonnull EntityDreamWalker entity) {
		Minecraft mc = Minecraft.getInstance();
		if (!(mc.getRenderViewEntity() instanceof AbstractClientPlayerEntity))
			return DefaultPlayerSkin.getDefaultSkinLegacy();
		return ((AbstractClientPlayerEntity) mc.getRenderViewEntity()).getLocationSkin();
	}

}