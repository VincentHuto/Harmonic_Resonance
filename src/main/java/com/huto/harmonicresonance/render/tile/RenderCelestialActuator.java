package com.huto.harmonicresonance.render.tile;

import java.util.Random;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.model.block.ModelFloatingDial;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityCelestialActuator;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class RenderCelestialActuator extends BlockEntityRenderer<TileEntityCelestialActuator> {

	ModelFloatingDial dial = new ModelFloatingDial();

	public RenderCelestialActuator(BlockEntityRenderDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@SuppressWarnings("unused")
	@Override
	public void render(TileEntityCelestialActuator te, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Random rand = te.getWorld().rand;
		double powerLevel = te.getSmoothedNeedlePosition();
		float rot = 0.0F;
		if (te.getWorld() != null) {
			rot = te.getWorld().getDayTime() % 360L;
		}
		final double ZERO_ANGLE = 0;
		final double MAX_ANGLE = -360;
		float needleAngle = (float) interpolate(powerLevel, 0, 1, MAX_ANGLE, ZERO_ANGLE);
		double speedMult = 0.1;
		double ticks = ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks;
		double xMod = (float) Math.sin(ticks * speedMult) / 2F;
		double zMod = (float) Math.cos(ticks * speedMult) / 2F;
		double yMod = (float) Math.cos(ticks * speedMult) / 2F;

		if (te.getWorld() != null) {
			rot = te.getWorld().getDayTime() % 360L;
		}

		matrixStackIn.push();
		matrixStackIn.scale(0.9f, 0.9f, 0.9f);
		matrixStackIn.translate(0.525, 0.7, 0.5);
		IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer
				.getImpl(Tessellator.getInstance().getBuffer());
		IVertexBuilder ivertexbuilder = irendertypebuffer$impl.getBuffer(dial.getRenderType(
				new ResourceLocation(HarmonicResonance.MOD_ID + ":textures/block/somnolent_stone_smooth.png")));
	
		
		
		
		
		

		int rotateDir = 0;
		if (te.state == false) {
			rotateDir = 1;
		} else {
			rotateDir = -1;
			rotateDir = -rotateDir;
		}
		if(te.state) {
		matrixStackIn.rotate(new Quaternion(Vector3f.YP, needleAngle, true));
		}else {
			matrixStackIn.rotate(new Quaternion(Vector3f.YP, needleAngle, true));

		}
		
		
		
		
		
		
		
		dial.render(matrixStackIn, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		irendertypebuffer$impl.finish();
		matrixStackIn.pop();
	}

	public static double interpolate(double x, double x1, double x2, double y1, double y2) {
		if (x1 > x2) {
			double temp = x1;
			x1 = x2;
			x2 = temp;
			temp = y1;
			y1 = y2;
			y2 = temp;
		}

		if (x <= x1)
			return y1;
		if (x >= x2)
			return y2;
		double xFraction = (x - x1) / (x2 - x1);
		return y1 + xFraction * (y2 - y1);
	}
}
