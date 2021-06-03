package com.huto.harmonicresonance.item;

import com.huto.harmonicresonance.capabilities.karma.IKarma;
import com.huto.harmonicresonance.capabilities.karma.KarmaProvider;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.karma.KarmaPacketServer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class ItemActualizationNode extends Item {

	public ItemActualizationNode(Properties prop) {
		super(prop);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

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
