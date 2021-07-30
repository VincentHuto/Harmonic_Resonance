package com.huto.harmonicresonance.item;

import javax.annotation.Nonnull;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;
import com.huto.harmonicresonance.init.ItemInit;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.vibration.VibrationPacketServer;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.PacketDistributor;

import net.minecraft.world.item.Item.Properties;

public class ItemNullIngot extends Item {

	public ItemNullIngot(Properties properties) {
		super(properties);
	}

	@Nonnull
	@Override
	public InteractionResultHolder<ItemStack> onItemRightClick(Level worldIn, Player playerIn, @Nonnull InteractionHand handIn) {
		playerIn.setActiveHand(handIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);

	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) entityLiving;
			IVibrations vibes = playerentity.getCapability(VibrationProvider.VIBE_CAPA)
					.orElseThrow(IllegalStateException::new);
			int i = this.getUseDuration(stack) - timeLeft;
			if (i > 21) {
				if (vibes.getVibes() < -10) {
					if (!worldIn.isRemote) {
						vibes.addVibes(10);
						playerentity.sendStatusMessage(
								new StringTextComponent(
										"Reduced Resonance to: " + TextFormatting.BLUE + vibes.getVibes() + "Hz"),
								false);
						// Sync Packet with server
						PacketHandler.CHANNELVIBES.send(
								PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerentity),
								new VibrationPacketServer(vibes.getVibes()));
						ItemStack output = new ItemStack(ItemInit.shattered_ingot.get(), 1);
						ItemEntity outputItem = new ItemEntity(worldIn, entityLiving.getPosX() + 0.5,
								entityLiving.getPosY() + 1.5, entityLiving.getPosZ() + 0.5, output);
						worldIn.addEntity(outputItem);
						stack.shrink(1);

					} else {
					}
				}
			}

		}
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.CROSSBOW;
	}

}
