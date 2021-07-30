package com.huto.harmonicresonance.network.karma;

import java.util.function.Supplier;

import com.huto.harmonicresonance.capabilities.karma.IKarma;
import com.huto.harmonicresonance.capabilities.karma.KarmaProvider;
import com.huto.harmonicresonance.network.PacketHandler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class KarmaPacketClient {

	public KarmaPacketClient() {

	}

	public static void handle(final KarmaPacketClient msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerPlayer sender = ctx.get().getSender(); // the client that sent this packet
			if (sender != null) {
				// Get the currency
				IKarma karma = sender.getCapability(KarmaProvider.KARMA_CAPA).orElseThrow(IllegalStateException::new);
				// Send message back to the client to set the information
				PacketHandler.CHANNELKARMA.send(PacketDistributor.PLAYER.with(() -> sender),
						new KarmaPacketServer(karma));
			}
		});
		ctx.get().setPacketHandled(true);
	}

	public static void encode(final KarmaPacketClient msg, final FriendlyByteBuf packetBuffer) {

	}

	public static KarmaPacketClient decode(final FriendlyByteBuf packetBuffer) {
		return new KarmaPacketClient();
	}
}