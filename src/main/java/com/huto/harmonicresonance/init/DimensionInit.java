package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.level.Level;

public class DimensionInit {
	public static final ResourceKey<Level> dreamlands = ResourceKey.getOrCreateKey(Registry.WORLD_KEY,
			new ResourceLocation(HarmonicResonance.MOD_ID, "dreamlands"));
}
