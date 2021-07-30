package com.huto.harmonicresonance.network.vibration;

import java.util.function.Supplier;

import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

public class VibrationPacketServer {
	private float vibes;

	public VibrationPacketServer(float vibesIn) {
		this.vibes = vibesIn;
	}

	// This code only runs on the client
	@SuppressWarnings("unused")
	public static void handle(final VibrationPacketServer msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerPlayer sender = ctx.get().getSender(); // the client that sent this packet
			Minecraft.getInstance().player.getCapability(VibrationProvider.VIBE_CAPA)
					.orElseThrow(IllegalStateException::new).setVibes(msg.vibes);
		});
		ctx.get().setPacketHandled(true);
	}

	public static void encode(final VibrationPacketServer msg, final FriendlyByteBuf packetBuffer) {
		packetBuffer.writeFloat(msg.vibes);
	}

	public static VibrationPacketServer decode(final FriendlyByteBuf packetBuffer) {
		return new VibrationPacketServer(packetBuffer.readFloat());
	}
}