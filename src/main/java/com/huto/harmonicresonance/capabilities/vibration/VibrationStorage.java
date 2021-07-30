package com.huto.harmonicresonance.capabilities.vibration;

import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class VibrationStorage implements IStorage<IVibrations> {

	@Override
	public Tag writeNBT(Capability<IVibrations> capability, IVibrations instance, Direction side) {
		return FloatTag.valueOf(instance.getVibes());
	}

	@Override
	public void readNBT(Capability<IVibrations> capability, IVibrations instance, Direction side, Tag nbt) {
		   if (!(instance instanceof Vibrations))
	            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
	        instance.setVibes(((FloatTag)nbt).getFloat());
	    }
}
