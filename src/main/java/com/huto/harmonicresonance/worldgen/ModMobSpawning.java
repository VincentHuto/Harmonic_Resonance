package com.huto.harmonicresonance.worldgen;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.init.EntityInit;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID)
public class ModMobSpawning {

	@SubscribeEvent
	public static void onBiomeLoading(BiomeLoadingEvent event) {

		RegistryKey<Biome> biomeRegistryKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
		if (!BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.NETHER)) {
			if (BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.PLAINS)
					|| BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.FOREST)
					|| BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.SAVANNA)
							&& BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.OCEAN)) {
				event.getSpawns().withSpawner(EntityClassification.CREATURE,
						new Spawners(EntityInit.ibis.get(), 6, 1, 6));
			}

		}
	}

}
