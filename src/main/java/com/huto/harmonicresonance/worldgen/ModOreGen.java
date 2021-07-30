package com.huto.harmonicresonance.worldgen;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.init.BlockInit;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModOreGen {

	public static final RuleTest SOMNOLENT_STONE_DREAMLANDS = new BlockMatchTest(BlockInit.somnolent_stone.get());

	public static ConfiguredFeature<?, ?> EnchantedOreGen;
	public static ConfiguredFeature<?, ?> SomnolentEnchantedOreGen;
	public static ConfiguredFeature<?, ?> GeodeOreGen;

	public static void registerConfiguredFeatures() {
		Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
		EnchantedOreGen = Feature.ORE
				.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
						BlockInit.somnolent_ore.get().getDefaultState(), 10))
				.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(5, 5, 60))).chance(1);

		Registry.register(registry, new ResourceLocation(HarmonicResonance.MOD_ID, "enchanted_ore_gen"), EnchantedOreGen);

		SomnolentEnchantedOreGen = Feature.ORE
				.withConfiguration(new OreFeatureConfig(SOMNOLENT_STONE_DREAMLANDS,
						BlockInit.somnolent_ore_somnolent.get().getDefaultState(), 10))
				.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(3, 3, 140))).chance(1);
		Registry.register(registry, new ResourceLocation(HarmonicResonance.MOD_ID, "enchanted_ore_mystic_gen"),
				SomnolentEnchantedOreGen);

		GeodeOreGen = Feature.ORE
				.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
						BlockInit.stone_geode.get().getDefaultState(), 7))
				.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(2, 12, 40))).chance(1);
		Registry.register(registry, new ResourceLocation(HarmonicResonance.MOD_ID, "geode_ore_gen"), GeodeOreGen);

	}

	public static void addStuffToBiomes(BiomeLoadingEvent event) {
		RegistryKey<Biome> biome = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
		if (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID) && isValidBiome(event.getCategory())) {
			event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, EnchantedOreGen);
			event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SomnolentEnchantedOreGen);
			event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GeodeOreGen);

		}

	}

	private static boolean isValidBiome(Biome.Category biomeCategory) {
		// If this does weird things to unclassified biomes (Category.NONE), then we
		// should also mark that biome as invalid
		return biomeCategory != Biome.Category.THEEND && biomeCategory != Biome.Category.NETHER;
	}

}