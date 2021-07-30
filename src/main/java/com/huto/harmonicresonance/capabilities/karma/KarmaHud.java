package com.huto.harmonicresonance.capabilities.karma;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.karma.KarmaPacketClient;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KarmaHud extends Screen {

	public static float karma = 0;
	private Minecraft mc;
	LocalPlayer player;

	public KarmaHud(LocalPlayer playerIn, Minecraft mcI) {
		super(new TextComponent(""));
		this.player = playerIn;
		this.mc = mcI;

	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);

	}

	/* This helper method will render the bar */
	public void renderStatusBar(PoseStack matrix, int screenWidth, int screenHeight, Level world,
			LocalPlayer playerIn) {
		if (playerIn != null) {
			IKarma karmaCap = playerIn.getCapability(KarmaProvider.KARMA_CAPA)
					.orElseThrow(IllegalArgumentException::new);
			if (karmaCap != null) {
				if (karmaCap.isActive()) {

					PacketHandler.CHANNELKARMA.sendToServer(new KarmaPacketClient());
					karma = karmaCap.getKarma();
					String m = String.valueOf(karma);
					DecimalFormat d = new DecimalFormat("0.0");

					Font fr = mc.fontRenderer;
					final int vanillaExpLeftX = screenWidth / 2 - 91; // leftmost edge of the experience bar
					final int vanillaExpTopY = screenHeight - 9; // top of the experience bar

					GL11.glPushMatrix();
					GL11.glTranslatef(vanillaExpLeftX + 320, vanillaExpTopY - 8, 0);

					GL11.glPushMatrix();
					// "karma Value"
					GL11.glTranslatef(-53, 30, 0);
					fr.drawString(matrix, "Karma Value: ", -fr.getStringWidth(d.format(karma)) - 50, -30, 0x000000);
					fr.drawString(matrix, "Karma Value: ", -fr.getStringWidth(d.format(karma)) - 51, -30, 0xFFFFFF);
					GL11.glPopMatrix();

					// Digits
					GL11.glPushMatrix();
					GL11.glTranslatef(0, 21, 0);
					// Shadow String
					fr.drawString(matrix, m, -50, -20, 0x000000);
					// Blue String renders after so its on top
					fr.drawString(matrix, m, -51, -21, 0xFFFFFF);
					GL11.glPopMatrix();

					GL11.glPopMatrix();

				}
			}
		}
	}
}
