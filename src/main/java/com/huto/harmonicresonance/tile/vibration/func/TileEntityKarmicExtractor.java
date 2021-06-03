package com.huto.harmonicresonance.tile.vibration.func;

import com.huto.harmonicresonance.capabilities.karma.IKarma;
import com.huto.harmonicresonance.capabilities.karma.KarmaProvider;
import com.huto.harmonicresonance.init.ItemInit;
import com.huto.harmonicresonance.init.TileEntityInit;
import com.huto.harmonicresonance.tile.util.IExportableTile;
import com.huto.harmonicresonance.tile.vibration.TileModVibes;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;
import com.hutoslib.common.VanillaPacketDispatcher;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;

public class TileEntityKarmicExtractor extends TileModVibes implements ITickableTileEntity, IExportableTile {

	public TileEntityKarmicExtractor() {
		super(TileEntityInit.karmic_extractor.get());
	}

	@Override
	public void tick() {

	}

	public void onActivated(PlayerEntity player, ItemStack stack) {
		if (world.isRemote)
			return;

		IKarma karma = player.getCapability(KarmaProvider.KARMA_CAPA).orElseThrow(IllegalStateException::new);
		ItemEntity outputItem = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5,
				new ItemStack(ItemInit.karmic_drop.get()));
		if (karma.getKarma() >= 1) {
			karma.subtractKarma(1);
			vibes.addVibes(20);
			world.addEntity(outputItem);

		}
		if (karma.getKarma() < 0) {
			karma.addKarma(1);
			vibes.addVibes(10);
			world.addEntity(outputItem);

		}
		VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, pos);
	}

	@Override
	public void exportToAbsorber(TileEntityAbsorber exportToIn, float rateIn) {
		if (vibes.getVibes() > 1) {
			this.vibes.subtractVibes(rateIn);
			exportToIn.vibes.addVibes(rateIn);
		}
	}

	public boolean canExport() {
		if (vibes.getVibes() > 10) {
			return true;
		}

		return false;
	}

	@Override
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, getBlockState(), getBlockState());
		world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
		markDirty();
	}
}
