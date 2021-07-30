package com.huto.harmonicresonance.network.karma;

import java.util.function.Supplier;

import com.huto.harmonicresonance.capabilities.karma.IKarma;
import com.huto.harmonicresonance.capabilities.karma.KarmaProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

public class KarmaPacketServer {
	private boolean active;
	private int karma;

	public KarmaPacketServer(IKarma karma) {
		this.active = karma.isActive();
		this.karma = karma.getKarma();
	}

	public KarmaPacketServer(boolean readBoolean, int readInt) {
		this.active = readBoolean;
		this.karma = readInt;
	}

	// This code only runs on the client
	public static void handle(final KarmaPacketServer msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			Minecraft.getInstance().player.getCapability(KarmaProvider.KARMA_CAPA)
					.orElseThrow(IllegalStateException::new).setActive(msg.active);
			Minecraft.getInstance().player.getCapability(KarmaProvider.KARMA_CAPA)
					.orElseThrow(IllegalStateException::new).setKarma(msg.karma);
		});
		ctx.get().setPacketHandled(true);
	}

	public static void encode(final KarmaPacketServer msg, final FriendlyByteBuf packetBuffer) {
		packetBuffer.writeBoolean(msg.active);
		packetBuffer.writeInt(msg.karma);
	}

	public static KarmaPacketServer decode(final FriendlyByteBuf packetBuffer) {
		return new KarmaPacketServer(packetBuffer.readBoolean(), packetBuffer.readInt());
	}
}