package com.huto.harmonicresonance.capabilities.vibration.chunk;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.IntTag;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ChunkVibrationProvider implements ICapabilitySerializable<IntTag> {
	@CapabilityInject(IChunkVibrations.class)
	public static final Capability<IChunkVibrations> CHUNK_ENERGY_CHUNK_CAPABILITY = null;
	private LazyOptional<IChunkVibrations> instance = LazyOptional
			.of(CHUNK_ENERGY_CHUNK_CAPABILITY::getDefaultInstance);

	public static final Direction DEFAULT_FACING = null;
	public static final int DEFAULT_CAPACITY = 256;

	public static LazyOptional<IChunkVibrations> getChunkEnergy(final Level world, final ChunkPos chunkPos) {
		return getChunkEnergy(world.getChunk(chunkPos.x, chunkPos.z));
	}

	public static LazyOptional<IChunkVibrations> getChunkEnergy(final LevelChunk chunk) {
		return chunk.getCapability(CHUNK_ENERGY_CHUNK_CAPABILITY, DEFAULT_FACING);
	}

	
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return CHUNK_ENERGY_CHUNK_CAPABILITY.orEmpty(cap, instance);

	}

	@Override
	public IntTag serializeNBT() {
		return (IntTag) CHUNK_ENERGY_CHUNK_CAPABILITY.getStorage().writeNBT(CHUNK_ENERGY_CHUNK_CAPABILITY,
				instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
	}

	@Override
	public void deserializeNBT(IntTag nbt) {
		CHUNK_ENERGY_CHUNK_CAPABILITY.getStorage().readNBT(CHUNK_ENERGY_CHUNK_CAPABILITY,
				instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);

	}



}