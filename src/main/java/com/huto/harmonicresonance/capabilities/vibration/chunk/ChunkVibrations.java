package com.huto.harmonicresonance.capabilities.vibration.chunk;

import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.network.vibration.UpdateChunkEnergyValueMessage;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.PacketDistributor;

public class ChunkVibrations implements IChunkVibrations {
	public World world;
	public ChunkPos chunkPos;
	protected int energy;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;

	public ChunkVibrations(final int capacity, final World world, final ChunkPos chunkPos) {
		this.world = world;
		this.chunkPos = chunkPos;
		this.energy = capacity;
	}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public ChunkPos getChunkPos() {
		return chunkPos;
	}

	@Override
	public void receiveEnergy(int maxReceive) {
		this.energy += maxReceive;
		onEnergyChanged();

	}

	@Override
	public void extractEnergy(int maxExtract) {
		this.energy -= maxExtract;
		onEnergyChanged();

	}

	@Override
	public void setEnergy(final int energyIn) {
		this.energy = energyIn;
		onEnergyChanged();
	}

	@Override
	public void onEnergyChanged() {
		final World world = getWorld();
		final ChunkPos chunkPos = getChunkPos();

		if (world.isRemote)
			return;

		if (world.getChunkProvider().isChunkLoaded(chunkPos)) { // Don't load the chunk when reading from NBT
			final Chunk chunk = world.getChunk(chunkPos.x, chunkPos.z);
			chunk.markDirty();
			PacketHandler.CHANNELVIBES.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk),
					new UpdateChunkEnergyValueMessage(this));
		}
	}

	@Override
	public int getEnergyStored() {
		return energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public boolean canExtract() {
		return this.energy > 0;
	}

	@Override
	public boolean canReceive() {
		return this.energy < 256;
	}
}