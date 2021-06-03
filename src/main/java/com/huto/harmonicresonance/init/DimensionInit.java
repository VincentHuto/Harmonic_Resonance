package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class DimensionInit {
	public static final RegistryKey<World> dreamlands = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
			new ResourceLocation(HarmonicResonance.MOD_ID, "dreamlands"));
}
