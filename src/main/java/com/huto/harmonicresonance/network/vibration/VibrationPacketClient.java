package com.huto.harmonicresonance.network.vibration;

import java.util.function.Supplier;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;
import com.huto.harmonicresonance.network.PacketHandler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class VibrationPacketClient {

	public VibrationPacketClient() {

	}

	public static void handle(final VibrationPacketClient msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerPlayer sender = ctx.get().getSender(); // the client that sent this packet
			if (sender != null) {
				IVibrations vibes = sender.getCapability(VibrationProvider.VIBE_CAPA)
						.orElseThrow(IllegalStateException::new);
				// Send message back to the client to set the information
				PacketHandler.CHANNELVIBES.send(PacketDistributor.PLAYER.with(() -> sender),
						new VibrationPacketServer(vibes.getVibes()));
			}
		});
		ctx.get().setPacketHandled(true);
	}

	public static void encode(final VibrationPacketClient msg, final FriendlyByteBuf packetBuffer) {

	}

	public static VibrationPacketClient decode(final FriendlyByteBuf packetBuffer) {
		return new VibrationPacketClient();
	}
}