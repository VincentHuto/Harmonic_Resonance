package com.huto.harmonicresonance.render.tile;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.model.block.ModelFloatingCube;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityKarmicExtractor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RenderKarmicExtractor extends BlockEntityRenderer<TileEntityKarmicExtractor> {
	private final ModelFloatingCube cube = new ModelFloatingCube();

	public RenderKarmicExtractor(BlockEntityRenderDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileEntityKarmicExtractor te, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		// Cube
		double ticks = ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks - 1.3 * 0.14;
		float y = (float) Math.cos((ticks + 50) / 5F) / 10F;
		matrixStackIn.translate(0.5, 1.1, 0.5);
		matrixStackIn.scale(0.5f, 0.5f, 0.5f);
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees((float) ticks));
		matrixStackIn.translate(-0.125, y - 0.75, -0.125);
		matrixStackIn.scale(4, 4, 4);
		IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer
				.getImpl(Tessellator.getInstance().getBuffer());
		IVertexBuilder ivertexbuilder = irendertypebuffer$impl.getBuffer(
				cube.getRenderType(new ResourceLocation(HarmonicResonance.MOD_ID + ":textures/block/end_portal.png")));
		cube.render(matrixStackIn, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.5F);
		irendertypebuffer$impl.finish();
		matrixStackIn.pop();

		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YN.rotationDegrees((float) ticks));

		matrixStackIn.translate(-0.125, y - 0.75, -0.125);
		matrixStackIn.scale(4, 4, 4);
		IRenderTypeBuffer.Impl irendertypebuffer$impl1 = IRenderTypeBuffer
				.getImpl(Tessellator.getInstance().getBuffer());
		IVertexBuilder ivertexbuilder1 = irendertypebuffer$impl1.getBuffer(
				cube.getRenderType(new ResourceLocation(HarmonicResonance.MOD_ID + ":textures/block/end_portal.png")));
		cube.render(matrixStackIn, ivertexbuilder1, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.5F);
		irendertypebuffer$impl1.finish();
		matrixStackIn.pop();

	}
}
