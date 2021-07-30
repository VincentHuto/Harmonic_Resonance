package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.capabilities.karma.IKarma;
import com.huto.harmonicresonance.capabilities.karma.Karma;
import com.huto.harmonicresonance.capabilities.karma.KarmaStorage;
import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationStorage;
import com.huto.harmonicresonance.capabilities.vibration.Vibrations;
import com.huto.harmonicresonance.capabilities.vibration.chunk.ChunkVibrationStorage;
import com.huto.harmonicresonance.capabilities.vibration.chunk.IChunkVibrations;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.MOD)
public class CapabilityInit {

	public static void init() {
		CapabilityManager.INSTANCE.register(IVibrations.class, new VibrationStorage(), Vibrations::new);
		CapabilityManager.INSTANCE.register(IKarma.class, new KarmaStorage(), Karma::new);
		CapabilityManager.INSTANCE.register(IChunkVibrations.class, new ChunkVibrationStorage(), () -> null);
	}

}
