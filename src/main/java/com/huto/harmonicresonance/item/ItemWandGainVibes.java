package com.huto.harmonicresonance.item;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.vibration.VibrationPacketServer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class ItemWandGainVibes extends Item {

	public ItemWandGainVibes(Properties prop) {
		super(prop);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (!worldIn.isRemote) {
			IVibrations vibes = playerIn.getCapability(VibrationProvider.VIBE_CAPA)
					.orElseThrow(IllegalStateException::new);
			vibes.addVibes(10);
			playerIn.sendStatusMessage(
					new StringTextComponent("Increasing Resonance to: " + TextFormatting.RED + vibes.getVibes() + "Hz"),
					false);
			// Sync Packet with server
			PacketHandler.CHANNELVIBES.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn),
					new VibrationPacketServer(vibes.getVibes()));
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
