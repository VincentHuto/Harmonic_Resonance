package com.huto.harmonicresonance.network.vibration;

import java.util.function.Supplier;

import com.huto.harmonicresonance.tile.util.IExportableTile;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.fml.network.NetworkEvent;

public class ExportVibePacket {

	private BlockPos thisPos;
	private BlockPos otherPos;
	private float transferRate;

	public ExportVibePacket(BlockPos thisPosIn, BlockPos otherPosIn, float transferIn) {
		this.thisPos = thisPosIn;
		this.otherPos = otherPosIn;
		this.transferRate = transferIn;
	}

	public static void encode(ExportVibePacket msg, FriendlyByteBuf buf) {
		buf.writeBlockPos(msg.thisPos);
		buf.writeBlockPos(msg.otherPos);
		buf.writeFloat(msg.transferRate);

	}

	public static ExportVibePacket decode(FriendlyByteBuf buf) {
		return new ExportVibePacket(buf.readBlockPos(), buf.readBlockPos(), buf.readFloat());
	}

	public static class Handler {

		public static void handle(final ExportVibePacket msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				ServerLevel sWorld = ctx.get().getSender().getServerWorld();
				if (sWorld.getTileEntity(msg.thisPos) instanceof TileEntityAbsorber) {
					TileEntityAbsorber abs = (TileEntityAbsorber) sWorld.getTileEntity(msg.thisPos);
					if (sWorld.getTileEntity(msg.otherPos) instanceof IExportableTile) {
						IExportableTile otherTe = (IExportableTile) sWorld.getTileEntity(msg.otherPos);
						if (abs != null & otherTe != null) {
							otherTe.exportToAbsorber(abs, msg.transferRate);
						}
					}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}