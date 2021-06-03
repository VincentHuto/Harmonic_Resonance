package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.MOD)
public class FeatureInit {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
			HarmonicResonance.MOD_ID);

	// Feature

}
