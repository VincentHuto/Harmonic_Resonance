package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.entity.item.EntityManaDustItem;
import com.huto.harmonicresonance.entity.passive.EntityColin;
import com.huto.harmonicresonance.entity.passive.EntityDreamWalker;
import com.huto.harmonicresonance.entity.passive.EntityIbis;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.MOD)
public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			HarmonicResonance.MOD_ID);

	// Passive
	public static final RegistryObject<EntityType<EntityIbis>> ibis = ENTITY_TYPES.register("ibis",
			() -> EntityType.Builder.<EntityIbis>create(EntityIbis::new, EntityClassification.CREATURE).size(0.4F, 0.7F)
					.build(new ResourceLocation(HarmonicResonance.MOD_ID, "ibis").toString()));

	public static final RegistryObject<EntityType<EntityDreamWalker>> dream_walker = ENTITY_TYPES.register(
			"dream_walker",
			() -> EntityType.Builder.<EntityDreamWalker>create(EntityDreamWalker::new, EntityClassification.CREATURE)
					.size(0.9f, 1.3f).build(new ResourceLocation(HarmonicResonance.MOD_ID, "dream_walker").toString()));

	public static final RegistryObject<EntityType<EntityManaDustItem>> vibratorydust = ENTITY_TYPES.register("vibratorydust",
			() -> EntityType.Builder.<EntityManaDustItem>create(EntityManaDustItem::new, EntityClassification.MISC)
					.size(0.25F, 0.25F).build(new ResourceLocation(HarmonicResonance.MOD_ID, "vibratorydust").toString()));

	public static final RegistryObject<EntityType<EntityColin>> colin = ENTITY_TYPES.register("colin",
			() -> EntityType.Builder.<EntityColin>create(EntityColin::new, EntityClassification.CREATURE)
					.size(0.9f, 1.3f).build(new ResourceLocation(HarmonicResonance.MOD_ID, "colin").toString()));

	@SubscribeEvent
	public static void registerAttributes(final FMLCommonSetupEvent event) {
		GlobalEntityTypeAttributes.put(EntityInit.ibis.get(), EntityIbis.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.dream_walker.get(), EntityDreamWalker.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.colin.get(), EntityColin.setAttributes().create());

	}

}
