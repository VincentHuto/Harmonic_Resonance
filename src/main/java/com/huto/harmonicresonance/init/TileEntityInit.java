package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.tile.TileEntityMagicLight;
import com.huto.harmonicresonance.tile.TileEntityMagicRingLight;
import com.huto.harmonicresonance.tile.TileEntitySlimeRepelent;
import com.huto.harmonicresonance.tile.vibration.TileEntityTeleporter;
import com.huto.harmonicresonance.tile.vibration.TileEntityVirtuousEnchant;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityAutoInscriber;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityCelestialActuator;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityCrystalHarmonizer;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityKarmicAltar;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityKarmicExtractor;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityLectorTable;
import com.huto.harmonicresonance.tile.vibration.func.TileEntitySomnolentHopper;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityVibeConcetrator;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityVibeFuser;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityVibeResonator;
import com.huto.harmonicresonance.tile.vibration.func.TileEntityWandMaker;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityCapacitor;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityStorageDrum;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityTectonicAbsorber;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityThermalInfluxer;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityVibeGatherer;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityWaveGatherer;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, HarmonicResonance.MOD_ID);

	public static final RegistryObject<TileEntityType<TileEntityTeleporter>> teleporter = TILES.register("teleporter",
			() -> TileEntityType.Builder.create(TileEntityTeleporter::new, BlockInit.teleporter.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityWandMaker>> wand_maker = TILES.register("wand_maker",
			() -> TileEntityType.Builder.create(TileEntityWandMaker::new, BlockInit.wand_maker.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityVibeResonator>> vibe_resonator = TILES
			.register("vibe_resonator", () -> TileEntityType.Builder
					.create(TileEntityVibeResonator::new, BlockInit.vibe_resonator.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityWaveGatherer>> wave_gatherer = TILES
			.register("wave_gatherer", () -> TileEntityType.Builder
					.create(TileEntityWaveGatherer::new, BlockInit.wave_gatherer.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityVibeGatherer>> vibe_gatherer = TILES
			.register("vibe_gatherer", () -> TileEntityType.Builder
					.create(TileEntityVibeGatherer::new, BlockInit.vibe_gatherer.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityThermalInfluxer>> thermal_influxer = TILES
			.register("thermal_influxer", () -> TileEntityType.Builder
					.create(TileEntityThermalInfluxer::new, BlockInit.thermal_influxer.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityKarmicAltar>> karmic_altar = TILES.register(
			"karmic_altar",
			() -> TileEntityType.Builder.create(TileEntityKarmicAltar::new, BlockInit.karmic_altar.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityVirtuousEnchant>> virtuous_enchanter = TILES
			.register("virtuous_enchanter", () -> TileEntityType.Builder
					.create(TileEntityVirtuousEnchant::new, BlockInit.virtuous_enchanter.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityStorageDrum>> vibratory_storage_drum = TILES
			.register("vibratory_storage_drum", () -> TileEntityType.Builder
					.create(TileEntityStorageDrum::new, BlockInit.vibratory_storage_drum.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityCapacitor>> vibratory_capacitor = TILES
			.register("vibratory_capacitor", () -> TileEntityType.Builder
					.create(TileEntityCapacitor::new, BlockInit.vibratory_capacitor.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityKarmicExtractor>> karmic_extractor = TILES
			.register("karmic_extractor", () -> TileEntityType.Builder
					.create(TileEntityKarmicExtractor::new, BlockInit.karmic_extractor.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityVibeFuser>> vibratory_fuser = TILES.register(
			"vibratory_fuser",
			() -> TileEntityType.Builder.create(TileEntityVibeFuser::new, BlockInit.vibratory_fuser.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityCelestialActuator>> celestial_actuator = TILES
			.register("celestial_actuator", () -> TileEntityType.Builder
					.create(TileEntityCelestialActuator::new, BlockInit.celestial_actuator.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityAbsorber>> vibe_absorber = TILES.register(
			"vibe_absorber",
			() -> TileEntityType.Builder.create(TileEntityAbsorber::new, BlockInit.vibe_absorber.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityMagicLight>> light_block = TILES.register("light_block",
			() -> TileEntityType.Builder.create(TileEntityMagicLight::new, BlockInit.light_block.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityMagicRingLight>> light_ring_block = TILES
			.register("light_ring_block", () -> TileEntityType.Builder
					.create(TileEntityMagicRingLight::new, BlockInit.light_ring_block.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityAutoInscriber>> auto_inscriber = TILES
			.register("auto_inscriber", () -> TileEntityType.Builder
					.create(TileEntityAutoInscriber::new, BlockInit.auto_inscriber.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntitySomnolentHopper>> somnolent_hopper = TILES
			.register("somnolent_hopper", () -> TileEntityType.Builder
					.create(TileEntitySomnolentHopper::new, BlockInit.somnolent_hopper.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityLectorTable>> lector_table = TILES.register(
			"lector_table",
			() -> TileEntityType.Builder.create(TileEntityLectorTable::new, BlockInit.lector_table.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityTectonicAbsorber>> tectonic_absorber = TILES
			.register("tectonic_absorber", () -> TileEntityType.Builder
					.create(TileEntityTectonicAbsorber::new, BlockInit.tectonic_absorber.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityCrystalHarmonizer>> crystal_harmonizer = TILES
			.register("crystal_harmonizer", () -> TileEntityType.Builder
					.create(TileEntityCrystalHarmonizer::new, BlockInit.crystal_harmonizer.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntityVibeConcetrator>> vibe_concentrator = TILES
			.register("vibe_concentrator", () -> TileEntityType.Builder
					.create(TileEntityVibeConcetrator::new, BlockInit.vibe_concentrator.get()).build(null));

	public static final RegistryObject<TileEntityType<TileEntitySlimeRepelent>> slime_repelent = TILES
			.register("slime_repelent", () -> TileEntityType.Builder
					.create(TileEntitySlimeRepelent::new, BlockInit.slime_repelent.get()).build(null));

}
