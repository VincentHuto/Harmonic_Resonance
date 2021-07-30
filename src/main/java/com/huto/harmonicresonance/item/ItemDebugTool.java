package com.huto.harmonicresonance.item;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;
import com.huto.harmonicresonance.tile.vibration.TileModVibes;
import com.huto.harmonicresonance.tile.vibration.TileVibeSimpleInventory;
import com.ibm.icu.text.DecimalFormat;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class ItemDebugTool extends Item {

	public ItemDebugTool(Properties prop) {
		super(prop);
	}

	@Override
	public InteractionResult onItemUse(UseOnContext ctx) {
		Level world = ctx.getWorld();
		if (!world.isRemote) {
			PlayerEntity player = ctx.getPlayer();

			@SuppressWarnings("unused")
			ItemStack stack = ctx.getItem();
			BlockPos blockPos = ctx.getPos();
			TileEntity te = world.getTileEntity(blockPos);
			DecimalFormat df = new DecimalFormat("0.00");
			if (te instanceof TileModVibes) {
				IVibrations vibes = te.getCapability(VibrationProvider.VIBE_CAPA)
						.orElseThrow(IllegalStateException::new);
				if (!world.isRemote) {
					player.sendStatusMessage(new StringTextComponent(
							TextFormatting.GOLD + "Block Contains:" + df.format(vibes.getVibes())), false);
				}
			}

			if (te instanceof TileVibeSimpleInventory) {
				IVibrations vibes = te.getCapability(VibrationProvider.VIBE_CAPA)
						.orElseThrow(IllegalStateException::new);
				if (!world.isRemote) {
					player.sendStatusMessage(new StringTextComponent(
							TextFormatting.GOLD + "Block Contains:" + df.format(vibes.getVibes())), false);

				}
			}

			return super.onItemUse(ctx);
		} else {
			return ActionResultType.FAIL;
		}
	}

}
