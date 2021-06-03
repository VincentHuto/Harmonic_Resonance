package com.huto.harmonicresonance.event;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.init.ContainerInit;
import com.huto.harmonicresonance.init.EntityInit;
import com.huto.harmonicresonance.init.TileEntityInit;
import com.huto.harmonicresonance.render.entity.RenderColin;
import com.huto.harmonicresonance.render.entity.RenderDreamWalker;
import com.huto.harmonicresonance.render.entity.RenderIbis;
import com.huto.harmonicresonance.render.item.RenderManaDustItem;
import com.huto.harmonicresonance.render.tile.RenderAbsorber;
import com.huto.harmonicresonance.render.tile.RenderAutoInscriber;
import com.huto.harmonicresonance.render.tile.RenderCapacitor;
import com.huto.harmonicresonance.render.tile.RenderCrystalHarmonizer;
import com.huto.harmonicresonance.render.tile.RenderKarmicAltar;
import com.huto.harmonicresonance.render.tile.RenderKarmicExtractor;
import com.huto.harmonicresonance.render.tile.RenderLectorTable;
import com.huto.harmonicresonance.render.tile.RenderResonator;
import com.huto.harmonicresonance.render.tile.RenderStorageDrum;
import com.huto.harmonicresonance.render.tile.RenderTeleporter;
import com.huto.harmonicresonance.render.tile.RenderThermalInfluxer;
import com.huto.harmonicresonance.render.tile.RenderVibeFuser;
import com.huto.harmonicresonance.render.tile.RenderWandMaker;
import com.huto.harmonicresonance.screen.GuiVirtuousEnchanter;
import com.huto.harmonicresonance.tile.vibration.RenderMagicLight;
import com.huto.harmonicresonance.tile.vibration.RenderMagicRingLight;
import com.huto.harmonicresonance.tile.vibration.RenderVirtuousEnchanter;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {

	public static NonNullList<KeyBinding> keyBinds = NonNullList.create();

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {

		ClientRegistry.bindTileEntityRenderer(TileEntityInit.wand_maker.get(), RenderWandMaker::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibe_resonator.get(), RenderResonator::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.karmic_altar.get(), RenderKarmicAltar::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.karmic_extractor.get(), RenderKarmicExtractor::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.virtuous_enchanter.get(), RenderVirtuousEnchanter::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibratory_storage_drum.get(), RenderStorageDrum::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibratory_capacitor.get(), RenderCapacitor::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibratory_fuser.get(), RenderVibeFuser::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibe_absorber.get(), RenderAbsorber::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.thermal_influxer.get(), RenderThermalInfluxer::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.light_block.get(), RenderMagicLight::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.light_ring_block.get(), RenderMagicRingLight::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.auto_inscriber.get(), RenderAutoInscriber::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.teleporter.get(), RenderTeleporter::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.lector_table.get(), RenderLectorTable::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.crystal_harmonizer.get(), RenderCrystalHarmonizer::new);
		ScreenManager.registerFactory(ContainerInit.virtuous_enchanter.get(), GuiVirtuousEnchanter::new);

		// ScreenManager.registerFactory(ContainerInit.runic_chisel_station.get(),
		// GuiChiselStation::new);
		// ScreenManager.registerFactory(ContainerAdornmentBinder.type,
		// GuiAdornmentBinder::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.dream_walker.get(), RenderDreamWalker::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.colin.get(), RenderColin::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.ibis.get(), RenderIbis::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.vibratorydust.get(), RenderManaDustItem::new);

	}

}
