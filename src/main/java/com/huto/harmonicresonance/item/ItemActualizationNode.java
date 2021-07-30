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
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.PacketDistributor;

import net.minecraft.world.item.Item.Properties;

public class ItemActualizationNode extends Item {

	public ItemActualizationNode(Properties prop) {
		super(prop);
	}

	@Override
	public InteractionResultHolder<ItemStack> onItemRightClick(Level worldIn, Player playerIn, InteractionHand handIn) {

		IKarma karma = playerIn.getCapability(KarmaProvider.KARMA_CAPA).orElseThrow(IllegalStateException::new);
		if (!karma.isActive()) {
			playerIn.sendStatusMessage(new StringTextComponent("Activating Karma!"), false);
			karma.setActive(true);
			if (!worldIn.isRemote) {
				// Sync Packet with server
				PacketHandler.CHANNELKARMA.send(
						PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) playerIn),
						new KarmaPacketServer(karma));
			}
			playerIn.getHeldItemMainhand().shrink(1);
			return super.onItemRightClick(worldIn, playerIn, handIn);

		} else {
			playerIn.sendStatusMessage(new StringTextComponent("Deactivating Karma!"), false);
			karma.setActive(false);
			// Sync Packet with server
			if (!worldIn.isRemote) {
				PacketHandler.CHANNELKARMA.send(
						PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) playerIn),
						new KarmaPacketServer(karma));
				playerIn.getHeldItemMainhand().shrink(1);
			}
			return super.onItemRightClick(worldIn, playerIn, handIn);

		}
	}

}
