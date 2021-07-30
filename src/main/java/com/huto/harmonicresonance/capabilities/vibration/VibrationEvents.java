package com.huto.harmonicresonance.capabilities.vibration;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.vibration.VibrationPacketServer;
import com.huto.harmonicresonance.tile.vibration.TileModVibes;
import com.huto.harmonicresonance.tile.vibration.TileVibeSimpleInventory;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class VibrationEvents {
	@SubscribeEvent
	public static void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			event.addCapability(new ResourceLocation(HarmonicResonance.MOD_ID, "vibrations"), new VibrationProvider());
		}
	}

	@SubscribeEvent
	public static void attachCapabilitiesTile(final AttachCapabilitiesEvent<BlockEntity> event) {
		if (event.getObject() instanceof TileVibeSimpleInventory) {
			event.addCapability(new ResourceLocation(HarmonicResonance.MOD_ID, "vibrations"), new VibrationProvider());
		}
		if (event.getObject() instanceof TileModVibes) {
			event.addCapability(new ResourceLocation(HarmonicResonance.MOD_ID, "vibrations"), new VibrationProvider());
		}
	}

	@SubscribeEvent
	public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		ServerPlayer player = (ServerPlayer) event.getPlayer();
		float amount = VibrationProvider.getPlayerVibes(player);
		PacketHandler.CHANNELVIBES.send(PacketDistributor.PLAYER.with(() -> player), new VibrationPacketServer(amount));
		player.sendStatusMessage(
				new StringTextComponent("Welcome! Current Resonance: " + TextFormatting.GOLD + amount + "Hz"), false);
	}

	@SubscribeEvent
	public static void onDimensionChange(PlayerChangedDimensionEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
		float amount = VibrationProvider.getPlayerVibes(player);
		PacketHandler.CHANNELVIBES.send(PacketDistributor.PLAYER.with(() -> player), new VibrationPacketServer(amount));
		player.sendStatusMessage(
				new StringTextComponent("Welcome! Current Resonance: " + TextFormatting.GOLD + amount + "Hz"), false);
	}

	@SubscribeEvent
	public static void playerDeath(PlayerEvent.Clone event) {
		IVibrations vibesOld = event.getOriginal().getCapability(VibrationProvider.VIBE_CAPA)
				.orElseThrow(IllegalStateException::new);
		IVibrations vibesNew = event.getEntity().getCapability(VibrationProvider.VIBE_CAPA)
				.orElseThrow(IllegalStateException::new);
		vibesNew.setVibes(vibesOld.getVibes() - 20f);
		((PlayerEntity) event.getEntity()).sendStatusMessage(
				new StringTextComponent(TextFormatting.ITALIC + "Upon death, your resonance has decreased to: "
						+ TextFormatting.RED + TextFormatting.ITALIC + vibesNew.getVibes() + "Hz"),
				false);
	}

}
