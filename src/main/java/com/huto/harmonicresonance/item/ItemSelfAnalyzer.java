package com.huto.harmonicresonance.item;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSelfAnalyzer extends Item {

	public ItemSelfAnalyzer(Properties prop) {
		super(prop);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		if (!worldIn.isRemote) {
			IVibrations vibes = playerIn.getCapability(VibrationProvider.VIBE_CAPA)
					.orElseThrow(IllegalStateException::new);
			playerIn.sendStatusMessage(new StringTextComponent(
					"Current Resonance: " + TextFormatting.DARK_PURPLE + vibes.getVibes() + "Hz"), false);
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
