package com.huto.harmonicresonance.capabilities.karma;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class KarmaStorage implements IStorage<IKarma> {

	@Override
	public CompoundNBT writeNBT(Capability<IKarma> capability, IKarma instance, Direction side) {
		CompoundNBT entry = new CompoundNBT();
		entry.putBoolean("Active", instance.isActive());
		entry.putFloat("Amount", instance.getKarma());
		return entry;
	}

	@Override
	public void readNBT(Capability<IKarma> capability, IKarma instance, Direction side, INBT nbt) {
		if (!(instance instanceof Karma))
			throw new IllegalArgumentException(
					"Can not deserialize to an instance that isn't the default implementation");
		if (nbt instanceof CompoundNBT) {
			CompoundNBT entry = (CompoundNBT) nbt;
			if (entry.contains("Amount") && entry.contains("Active")) {
				instance.setActive(entry.getBoolean("Active"));
				instance.setKarma(entry.getInt("Amount"));
			}
		}

	}
}
