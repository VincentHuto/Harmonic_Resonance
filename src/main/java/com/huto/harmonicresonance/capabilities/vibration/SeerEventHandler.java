package com.huto.harmonicresonance.capabilities.vibration;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.init.ItemInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class SeerEventHandler {

	public SeerEventHandler() {

	}

	static Minecraft mc = Minecraft.getInstance();

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(receiveCanceled = true)
	public static void onEvent(RenderGameOverlayEvent.Pre event) {
		@SuppressWarnings("resource")
		ClientPlayerEntity entityPlayerSP = Minecraft.getInstance().player;

		if (entityPlayerSP == null)
			return; // just in case

		boolean foundOnHead = false;
			ItemStack slotItemStack = entityPlayerSP.inventory.armorItemInSlot(3);
			if (slotItemStack.getItem() == ItemInit.vibrational_seer.get()) {
				foundOnHead = true;
			}
		if (!foundOnHead)
			return;

		switch (event.getType()) {
		case ALL:
			VibrationalSeerHud vibrationalSeerHudIn = new VibrationalSeerHud(entityPlayerSP, mc);
			if (entityPlayerSP.isAlive()) {
				vibrationalSeerHudIn.renderStatusBar(event.getMatrixStack(), event.getWindow().getScaledWidth(),
						event.getWindow().getScaledHeight(), entityPlayerSP.world, entityPlayerSP);
			}
		default:
			break;
		}
	}

}
