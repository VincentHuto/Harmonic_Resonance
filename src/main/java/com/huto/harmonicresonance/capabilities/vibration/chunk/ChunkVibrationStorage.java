package com.huto.harmonicresonance.capabilities.vibration.chunk;

import net.minecraft.nbt.Tag;
import net.minecraft.nbt.IntTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ChunkVibrationStorage implements IStorage<IChunkVibrations> {

	@Override
	public Tag writeNBT(final Capability<IChunkVibrations> capability, final IChunkVibrations instance,
			final Direction side) {
		return IntTag.valueOf(instance.getEnergyStored());
	}

	@Override
	public void readNBT(Capability<IChunkVibrations> capability, IChunkVibrations instance, Direction side, Tag nbt) {
		if (!(instance instanceof ChunkVibrations))
			throw new IllegalArgumentException(
					"Can not deserialize to an instance that isn't the default implementation");
		((ChunkVibrations) instance).setEnergy(((IntTag) nbt).getInt());
	}
}
