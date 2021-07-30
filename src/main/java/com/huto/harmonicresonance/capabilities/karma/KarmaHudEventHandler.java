package com.huto.harmonicresonance.capabilities.karma;

import com.huto.harmonicresonance.HarmonicResonance;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class KarmaHudEventHandler {

	public KarmaHudEventHandler() {

	}

	static Minecraft mc = Minecraft.getInstance();

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(receiveCanceled = true)
	public static void onEvent(RenderGameOverlayEvent.Pre event) {
		LocalPlayer entityPlayerSP = Minecraft.getInstance().player;
		if (entityPlayerSP == null) {
			return;
		}
		switch (event.getType()) {
		case ALL:
			if (entityPlayerSP.isAlive()) {
				KarmaHud karmaHud = new KarmaHud(entityPlayerSP, mc);
				if (entityPlayerSP.isAlive()) {
					karmaHud.renderStatusBar(event.getMatrixStack(), event.getWindow().getScaledWidth(),
							event.getWindow().getScaledHeight(), entityPlayerSP.world, entityPlayerSP);
				}
			}
		default:
			break;
		}
	}

}
