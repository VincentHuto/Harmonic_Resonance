package com.huto.harmonicresonance.render.tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.huto.harmonicresonance.tile.TileEntityMagicLight;
import com.hutoslib.client.particle.util.ParticleColor;
import com.hutoslib.client.particle.util.ParticleUtils;
import com.hutoslib.client.particle.factory.EmberParticleFactory;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RenderMagicLight extends TileEntityRenderer<TileEntityMagicLight> {
	public RenderMagicLight(TileEntityRendererDispatcher p_i226006_1_) {
		super(p_i226006_1_);
	}

	@Override
	public void render(TileEntityMagicLight lightTile, float v, MatrixStack matrixStack,
			IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
		List<ParticleColor> chakraColors = new ArrayList<ParticleColor>();
		Collections.addAll(chakraColors, new ParticleColor(162, 86, 160), new ParticleColor(96, 96, 186),
				new ParticleColor(66, 184, 212), new ParticleColor(110, 200, 80), new ParticleColor(255, 165, 44),
				new ParticleColor(243, 124, 59), new ParticleColor(229, 60, 81));
		Collections.reverse(chakraColors);

		World world = lightTile.getWorld();
		BlockPos pos = lightTile.getPos();
		double time = world.getGameTime() * 0.2f;
		// Random rand = world.rand;
		for (int j = 0; j < chakraColors.size(); j++) {
			world.addParticle(EmberParticleFactory.createData(chakraColors.get(j), 1, 0.35f, 135),
					pos.getX() + 0.5 + Math.sin(time + j) + ParticleUtils.inRange(-0.1, 0.1),
					pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1),
					pos.getZ() + 0.5 + Math.cos(time + j) + ParticleUtils.inRange(-0.1, 0.1), 0, -0.05, 0);
			world.addParticle(EmberParticleFactory.createData(chakraColors.get(j), 1, 0.35f, 135),
					pos.getX() + 0.5 + Math.sin(time + j) + ParticleUtils.inRange(-0.1, 0.1),
					pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1),
					pos.getZ() + 0.5 + Math.cos(time + j) + ParticleUtils.inRange(-0.1, 0.1), 0, 0.005, 0);

			/*
			 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
			 * pos.getX() + 0.5 + Math.cos(time + j) + ParticleUtils.inRange(-0.1, 0.1),
			 * pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() +
			 * 0.5 + Math.sin(time + j) + ParticleUtils.inRange(-0.1, 0.1), 0, -0.05, 0);
			 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
			 * pos.getX() + 0.5 + Math.cos(time + j) + ParticleUtils.inRange(-0.1, 0.1),
			 * pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() +
			 * 0.5 + Math.sin(time + j) + ParticleUtils.inRange(-0.1, 0.1), 0, 0.01, 0);
			 */
			/*
			 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
			 * pos.getX() + 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1),
			 * pos.getY() + (j * 0.5) + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() + 0.5 +
			 * Math.sin(time - j) + ParticleUtils.inRange(-0.1, 0.1), 0, 0, 0);
			 */

		}
		/*
		 * Collections.reverse(chakraColors);
		 * 
		 * for (int j = 0; j < chakraColors.size(); j++) {
		 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
		 * pos.getX() + 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1),
		 * pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() +
		 * 0.5 + Math.sin(time - j) + ParticleUtils.inRange(-0.1, 0.1), 0, -0.05, 0);
		 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
		 * pos.getX() + 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1),
		 * pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() +
		 * 0.5 + Math.sin(time - j) + ParticleUtils.inRange(-0.1, 0.1), 0, 0.01, 0);
		 * 
		 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
		 * pos.getX() + 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1),
		 * pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() +
		 * 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1), 0, -0.05, 0);
		 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
		 * pos.getX() + 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1),
		 * pos.getY() + (j * 0.5) + 0.1f + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() +
		 * 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1), 0, 0.01, 0);
		 * 
		 * 
		 * world.addParticle(GlowParticleData.createData(chakraColors.get(j)),
		 * pos.getX() + 0.5 + Math.cos(time - j) + ParticleUtils.inRange(-0.1, 0.1),
		 * pos.getY() + (j * 0.5) + ParticleUtils.inRange(-0.1, 0.1), pos.getZ() + 0.5 +
		 * Math.sin(time - j) + ParticleUtils.inRange(-0.1, 0.1), 0, 0, 0);
		 * 
		 * 
		 * }
		 */
	}
}