package com.huto.harmonicresonance.item;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.vibration.VibrationPacketClient;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityCapacitor;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityStorageDrum;
import com.hutoslib.common.VanillaPacketDispatcher;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fml.network.PacketDistributor;

import net.minecraft.world.item.Item.Properties;

public class ItemManaExtractor extends Item {

	public ItemManaExtractor(Properties prop) {
		super(prop);
	}

	@Override
	public InteractionResult onItemUse(UseOnContext context) {
		BlockPos pos = context.getPos();
		if (context.getWorld().getTileEntity(pos) instanceof TileEntityStorageDrum) {
			TileEntityStorageDrum te = (TileEntityStorageDrum) context.getWorld().getTileEntity(pos);
			IVibrations vibes = te.getCapability(VibrationProvider.VIBE_CAPA).orElseThrow(IllegalStateException::new);
			if (!context.getWorld().isRemote) {
				if (vibes.getVibes() > 30) {
					ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();
					IVibrations playerVibes = player.getCapability(VibrationProvider.VIBE_CAPA)
							.orElseThrow(IllegalStateException::new);
					playerVibes.addVibes(30);
					vibes.subtractVibes(30);
					PacketHandler.CHANNELVIBES.send(PacketDistributor.PLAYER.with(() -> player),
							new VibrationPacketClient());
					VanillaPacketDispatcher.dispatchTEToNearbyPlayers(te);
				}
			}
			return ActionResultType.SUCCESS;
		}
		if (context.getWorld().getTileEntity(pos) instanceof TileEntityCapacitor) {
			TileEntityCapacitor te = (TileEntityCapacitor) context.getWorld().getTileEntity(pos);
			IVibrations vibes = te.getCapability(VibrationProvider.VIBE_CAPA).orElseThrow(IllegalStateException::new);
			if (!context.getWorld().isRemote) {
				if (vibes.getVibes() > 10) {
					ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();
					IVibrations playerVibes = player.getCapability(VibrationProvider.VIBE_CAPA)
							.orElseThrow(IllegalStateException::new);
					playerVibes.addVibes(10);
					vibes.subtractVibes(10);
					PacketHandler.CHANNELVIBES.send(PacketDistributor.PLAYER.with(() -> player),
							new VibrationPacketClient());
					VanillaPacketDispatcher.dispatchTEToNearbyPlayers(te);

				}
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

}
