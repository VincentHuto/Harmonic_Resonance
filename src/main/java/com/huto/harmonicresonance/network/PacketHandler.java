package com.huto.harmonicresonance.network;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.network.karma.KarmaPacketClient;
import com.huto.harmonicresonance.network.karma.KarmaPacketServer;
import com.huto.harmonicresonance.network.vibration.ExportVibePacket;
import com.huto.harmonicresonance.network.vibration.ImportVibePacket;
import com.huto.harmonicresonance.network.vibration.UpdateChunkEnergyValueMessage;
import com.huto.harmonicresonance.network.vibration.VibrationPacketClient;
import com.huto.harmonicresonance.network.vibration.VibrationPacketServer;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	private static int networkID = 0;
	private static final String PROTOCOL_VERSION = "1";

	public static SimpleChannel INSTANCE;
	public static final SimpleChannel CHANNELVIBES = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(HarmonicResonance.MOD_ID, "vibrationchannel"), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	public static final SimpleChannel CHANNELKARMA = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(HarmonicResonance.MOD_ID, "karmachannel"), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

	public static void registerChannels() {

		// Vibes
		CHANNELVIBES.registerMessage(networkID++, VibrationPacketClient.class, VibrationPacketClient::encode,
				VibrationPacketClient::decode, VibrationPacketClient::handle);
		CHANNELVIBES.registerMessage(networkID++, VibrationPacketServer.class, VibrationPacketServer::encode,
				VibrationPacketServer::decode, VibrationPacketServer::handle);
		CHANNELVIBES.registerMessage(networkID++, UpdateChunkEnergyValueMessage.class,
				UpdateChunkEnergyValueMessage::encode, UpdateChunkEnergyValueMessage::decode,
				UpdateChunkEnergyValueMessage::handle);
		CHANNELVIBES.registerMessage(networkID++, ImportVibePacket.class, ImportVibePacket::encode,
				ImportVibePacket::decode, ImportVibePacket.Handler::handle);
		CHANNELVIBES.registerMessage(networkID++, ExportVibePacket.class, ExportVibePacket::encode,
				ExportVibePacket::decode, ExportVibePacket.Handler::handle);

		// Karma
		CHANNELKARMA.registerMessage(networkID++, KarmaPacketClient.class, KarmaPacketClient::encode,
				KarmaPacketClient::decode, KarmaPacketClient::handle);
		CHANNELKARMA.registerMessage(networkID++, KarmaPacketServer.class, KarmaPacketServer::encode,
				KarmaPacketServer::decode, KarmaPacketServer::handle);

	}
}
