package com.huto.harmonicresonance.item;

import com.huto.harmonicresonance.capabilities.karma.IKarma;
import com.huto.harmonicresonance.capabilities.karma.KarmaProvider;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.karma.KarmaPacketServer;

import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.PacketDistributor;

import net.minecraft.world.item.Item.Properties;

public class ItemGrandPurgingStone extends Item {

	public ItemGrandPurgingStone(Properties prop) {
		super(prop);
	}

	@Override
	public InteractionResultHolder<ItemStack> onItemRightClick(Level worldIn, Player playerIn, InteractionHand handIn) {
		if (!worldIn.isRemote) {
			IKarma karma = playerIn.getCapability(KarmaProvider.KARMA_CAPA).orElseThrow(IllegalStateException::new);
			if (karma.isActive()) {
				if (karma.getKarma() != 0) {
					karma.setKarma(0);
					playerIn.sendStatusMessage(
							new StringTextComponent("Reduced Karma to: " + TextFormatting.GOLD + karma.getKarma()),
							false);
					// Sync Packet with server
					PacketHandler.CHANNELKARMA.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn),
							new KarmaPacketServer(karma));
					playerIn.getHeldItemMainhand().shrink(1);
				}

			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
