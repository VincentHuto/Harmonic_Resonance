package com.huto.harmonicresonance.capabilities.karma;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class KarmaStorage implements IStorage<IKarma> {

	@Override
	public CompoundTag writeNBT(Capability<IKarma> capability, IKarma instance, Direction side) {
		CompoundTag entry = new CompoundTag();
		entry.putBoolean("Active", instance.isActive());
		entry.putFloat("Amount", instance.getKarma());
		return entry;
	}

	@Override
	public void readNBT(Capability<IKarma> capability, IKarma instance, Direction side, Tag nbt) {
		if (!(instance instanceof Karma))
			throw new IllegalArgumentException(
					"Can not deserialize to an instance that isn't the default implementation");
		if (nbt instanceof CompoundTag) {
			CompoundTag entry = (CompoundTag) nbt;
			if (entry.contains("Amount") && entry.contains("Active")) {
				instance.setActive(entry.getBoolean("Active"));
				instance.setKarma(entry.getInt("Amount"));
			}
		}

	}
}
