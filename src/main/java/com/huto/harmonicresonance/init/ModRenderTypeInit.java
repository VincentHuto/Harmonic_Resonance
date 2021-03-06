package com.huto.harmonicresonance.init;

import java.util.OptionalDouble;

import org.lwjgl.opengl.GL11;

import com.huto.harmonicresonance.HarmonicResonance;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

public class ModRenderTypeInit extends RenderType {
	private final static ResourceLocation laserBeam = new ResourceLocation(
			HarmonicResonance.MOD_ID + ":textures/misc/laser.png");
	private final static ResourceLocation laserBeam2 = new ResourceLocation(
			HarmonicResonance.MOD_ID + ":textures/misc/laser2.png");
	private final static ResourceLocation laserBeamGlow = new ResourceLocation(
			HarmonicResonance.MOD_ID + ":textures/misc/laser_glow.png");
	private final static ResourceLocation orbTrans = new ResourceLocation(
			HarmonicResonance.MOD_ID + ":textures/block/sphere_outside_trans.png");
	private final static ResourceLocation end_trans = new ResourceLocation(
			HarmonicResonance.MOD_ID + ":textures/block/end_portal.png");

	public ModRenderTypeInit(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn,
			boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
	}

	@SuppressWarnings("unused")
	private static final LineState THICK_LINES = new LineState(OptionalDouble.of(3.0D));

	public static final RenderType TRANSLUCENT = makeType("testTrans", DefaultVertexFormats.POSITION_COLOR_TEX, 7, 256,
			false, true, State.getBuilder().texture(new TextureState(orbTrans, false, false))
					.transparency(CRUMBLING_TRANSPARENCY).writeMask(COLOR_DEPTH_WRITE).build(false));

	public static final RenderType SPHEREIN = makeType("sphereTrans", DefaultVertexFormats.POSITION_COLOR_TEX, 7, 256,
			false, true, State.getBuilder().texture(new TextureState(orbTrans, false, false))
					.transparency(CRUMBLING_TRANSPARENCY).writeMask(COLOR_DEPTH_WRITE).build(false));
	public static final RenderType ENDTRANS = makeType("end_trans", DefaultVertexFormats.POSITION_COLOR_TEX, 7, 256,
			false, true, State.getBuilder().texture(new TextureState(end_trans, false, false))
					.transparency(CRUMBLING_TRANSPARENCY).writeMask(COLOR_DEPTH_WRITE).build(false));

	public static final RenderType LASER_MAIN_BEAM = makeType("MiningLaserMainBeam",
			DefaultVertexFormats.POSITION_COLOR_TEX, GL11.GL_QUADS, 256,
			RenderType.State.getBuilder().texture(new TextureState(laserBeam2, false, false))
					.transparency(TRANSLUCENT_TRANSPARENCY).depthTest(DEPTH_ALWAYS).writeMask(COLOR_DEPTH_WRITE)
					.build(false));

	public static final RenderType LASER_MAIN_ADDITIVE = makeType("LaserAdditiveBeam",
			DefaultVertexFormats.POSITION_COLOR_TEX, GL11.GL_QUADS, 256,
			RenderType.State.getBuilder().texture(new TextureState(laserBeamGlow, false, false))
					.transparency(TRANSLUCENT_TRANSPARENCY).depthTest(DEPTH_ALWAYS).writeMask(COLOR_DEPTH_WRITE)
					.build(false));

	public static final RenderType LASER_MAIN_CORE = makeType("LaserCoreBeam", DefaultVertexFormats.POSITION_COLOR_TEX,
			GL11.GL_QUADS, 256,
			RenderType.State.getBuilder().texture(new TextureState(laserBeam, false, false))
					.transparency(TRANSLUCENT_TRANSPARENCY).depthTest(DEPTH_ALWAYS).writeMask(COLOR_DEPTH_WRITE)
					.build(false));

	public static final IParticleRenderType EMBER_RENDER = new IParticleRenderType() {
		@SuppressWarnings("deprecation")
		@Override
		public void beginRender(BufferBuilder buffer, TextureManager textureManager) {

			RenderSystem.disableAlphaTest();
			RenderSystem.enableBlend();
			RenderSystem.alphaFunc(516, 0.3f);
			RenderSystem.enableCull();
			textureManager.bindTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE);
			RenderSystem.depthMask(false);
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE.param);
			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void finishRender(Tessellator tessellator) {
			tessellator.draw();
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();

			RenderSystem.depthMask(true);
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE.param);
			RenderSystem.disableCull();
			RenderSystem.alphaFunc(516, 0.1F);
		}

		@Override
		public String toString() {
			return "HarmonicResonance:em_rend";
		}
	};

}