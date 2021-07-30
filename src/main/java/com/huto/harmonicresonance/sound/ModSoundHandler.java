package com.huto.harmonicresonance.sound;

import com.huto.harmonicresonance.HarmonicResonance;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSoundHandler {

	public static final SoundEvent ENTITY_COLIN_AMBIENT = makeSoundEvent("entity.colin.ambient");
	public static final SoundEvent ENTITY_COLIN_HURT = makeSoundEvent("entity.colin.hurt");
	public static final SoundEvent ENTITY_COLIN_DEATH = makeSoundEvent("entity.colin.death");

	private static SoundEvent makeSoundEvent(String name) {
		ResourceLocation loc = new ResourceLocation(HarmonicResonance.MOD_ID, name);
		return new SoundEvent(loc).setRegistryName(loc);
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> evt) {
		IForgeRegistry<SoundEvent> r = evt.getRegistry();
		r.register(ENTITY_COLIN_AMBIENT);
		r.register(ENTITY_COLIN_HURT);
		r.register(ENTITY_COLIN_DEATH);

	}

}
