package com.huto.harmonicresonance.item;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.huto.harmonicresonance.capabilities.karma.IKarma;
import com.huto.harmonicresonance.capabilities.karma.KarmaProvider;
import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.karma.KarmaPacketServer;
import com.huto.harmonicresonance.network.vibration.VibrationPacketServer;
import com.hutoslib.math.DimensionalPosition;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;

public class ItemResonanceDestabalizer extends Item {

	public ItemResonanceDestabalizer(Properties properties) {
		super(properties);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

		if (!Screen.hasShiftDown()) {
			tooltip.add(new TranslationTextComponent("Shift to set a home!").mergeStyle(TextFormatting.YELLOW));
			tooltip.add(new TranslationTextComponent("Righ Click to return to it.").mergeStyle(TextFormatting.GOLD));
			tooltip.add(new TranslationTextComponent("But what shall you lose?").mergeStyle(TextFormatting.WHITE));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (hand == Hand.OFF_HAND) {
			return ActionResult.resultFail(stack);
		}
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
			if (player.isSneaking()) {
				setLastPosition(player);
				serverPlayer.sendStatusMessage(
						new TranslationTextComponent("Home Set to: " + getLastPosition(serverPlayer).get().getPosition()
								+ getLastPosition(serverPlayer).get().getDimension()),
						true);

			} else {

				Optional<DimensionalPosition> lastPos = getLastPosition(serverPlayer);
				if (!lastPos.isPresent()) {
					serverPlayer.sendStatusMessage(new TranslationTextComponent("no_prev_position"), true);
				} else {
					DimensionalPosition p = lastPos.get();
					BlockPos bp = p.getPosition();
					ResourceLocation dimRL = p.getDimension();
					RegistryKey<World> key = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, dimRL);
					ServerWorld ovw = world.getServer().getWorld(key);
					serverPlayer.teleport(ovw, bp.getX() + 0.5, bp.getY(), bp.getZ() + 0.5, serverPlayer.rotationYaw,
							serverPlayer.rotationPitch);
					IVibrations vibe = player.getCapability(VibrationProvider.VIBE_CAPA)
							.orElseThrow(NullPointerException::new);
					IKarma karma = player.getCapability(KarmaProvider.KARMA_CAPA)
							.orElseThrow(NullPointerException::new);
					vibe.setVibes(0);
					karma.setKarma(0);
					serverPlayer.setExperienceLevel(0);
					PacketHandler.CHANNELKARMA.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player),
							new KarmaPacketServer(karma));
					PacketHandler.CHANNELVIBES.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player),
							new VibrationPacketServer(vibe.getVibes()));

				}
			}

		}
		player.sendStatusMessage(new StringTextComponent(
				TextFormatting.RED + "Suddenly, you feel as if all you have gained was for naught."), true);

		return ActionResult.resultSuccess(stack);
	}

	public static Optional<DimensionalPosition> getLastPosition(PlayerEntity player) {
		CompoundNBT data = player.getPersistentData();
		if (!data.contains("dim-lastpos")) {
			return Optional.empty();
		}
		CompoundNBT pos = data.getCompound("dim-lastpos");
		return Optional.of(DimensionalPosition.fromNBT(pos));
	}

	public static void setLastPosition(PlayerEntity player) {
		CompoundNBT data = player.getPersistentData();
		BlockPos pos = player.getPosition();
		ResourceLocation dim = player.world.getDimensionKey().getLocation();
		DimensionalPosition dp = new DimensionalPosition(dim, pos);
		CompoundNBT dimNbt = dp.serializeNBT();
		data.put("dim-lastpos", dimNbt);
	}

}
