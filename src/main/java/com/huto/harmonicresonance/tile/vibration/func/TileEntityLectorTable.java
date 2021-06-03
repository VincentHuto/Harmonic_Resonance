package com.huto.harmonicresonance.tile.vibration.func;

import java.util.List;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.init.TileEntityInit;
import com.huto.harmonicresonance.recipe.RecipeAutoInscriber;
import com.huto.harmonicresonance.tile.util.IImportableTile;
import com.huto.harmonicresonance.tile.vibration.TileVibeSimpleInventory;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityLectorTable extends TileVibeSimpleInventory implements ITickableTileEntity, IImportableTile {

	float maxVibes = 200;
	public final String TAG_SIZE = "tankSize";
	List<ItemStack> lastRecipe = null;
	RecipeAutoInscriber currentRecipe;
	public final String TAG_LEVEL = "level";
	public int level = 1;
	public float clientVibes = 0.0f;
	public boolean isActive;

	public TileEntityLectorTable() {
		super(TileEntityInit.lector_table.get());
	}

	@Override
	public void onLoad() {
		super.onLoad();

	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		// TODO Auto-generated method stub
		return super.getRenderBoundingBox().grow(3);
	}

	public int getGridScale() {
		switch (this.level) {
		case 1:
			return 1;
		case 2:
			return 3;
		case 3:
			return 5;
		case 4:
			return 7;
		case 5:
			return 9;
		default:
			return 9;
		}
	}

	public void addLevel(float valIn) {
		this.level += valIn;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int levelIn) {
		this.level = levelIn;
	}

	public IVibrations getVibeCap() {
		return vibes;
	}

	public float getMaxVibes() {
		return maxVibes;
	}

	public void setMaxVibes(float maxVibes) {
		this.maxVibes = maxVibes;
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (vibes.getVibes() > 0.05f) {
				vibes.subtractVibes(0.05f);
			}
		}
	}

	// NBT data
	@Override
	public void readPacketNBT(CompoundNBT tag) {
		super.readPacketNBT(tag);
		itemHandler = createItemHandler();
		itemHandler.deserializeNBT(tag);
		maxVibes = tag.getFloat(TAG_SIZE);
		clientVibes = tag.getFloat(TAG_VIBES);
		level = tag.getInt(TAG_LEVEL);

	}

	@Override
	public void writePacketNBT(CompoundNBT tag) {
		super.writePacketNBT(tag);
		tag.merge(itemHandler.serializeNBT());
		tag.putFloat(TAG_SIZE, maxVibes);
		tag.putFloat(TAG_VIBES, vibes.getVibes());
		tag.putInt(TAG_LEVEL, level);

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		return super.write(compound);
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);

	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		super.getUpdatePacket();
		CompoundNBT nbtTag = new CompoundNBT();
		nbtTag.merge(itemHandler.serializeNBT());
		nbtTag.putFloat(TAG_SIZE, maxVibes);
		nbtTag.putFloat(TAG_VIBES, vibes.getVibes());
		nbtTag.putInt(TAG_LEVEL, level);
		return new SUpdateTileEntityPacket(getPos(), -1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getNbtCompound();
		super.onDataPacket(net, pkt);
		itemHandler = createItemHandler();
		itemHandler.deserializeNBT(tag);
		maxVibes = tag.getFloat(TAG_SIZE);
		clientVibes = tag.getFloat(TAG_VIBES);
		level = tag.getInt(TAG_LEVEL);

	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		super.handleUpdateTag(state, tag);
		maxVibes = tag.getFloat(TAG_SIZE);
		clientVibes = tag.getFloat(TAG_VIBES);
		level = tag.getInt(TAG_LEVEL);

	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	public boolean isEmpty() {
		for (int i = 0; i < getSizeInventory(); i++)
			if (!itemHandler.getStackInSlot(i).isEmpty())
				return false;

		return true;
	}

	public BlockState getState() {
		return world.getBlockState(pos);
	}

	public void setBlockToUpdate() {
		sendUpdates();
	}

	@Override
	public void importFromAbsorber(TileEntityAbsorber importFrom, float rate) {
		this.vibes.addVibes(rate);
		importFrom.vibes.subtractVibes(rate);
	}

	@Override
	public boolean canImport() {
		if (vibes.getVibes() < maxVibes) {
			return true;
		} else {
			return false;
		}
	}
}
