package com.huto.harmonicresonance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huto.harmonicresonance.capabilities.karma.KarmaEvents;
import com.huto.harmonicresonance.capabilities.karma.KarmaHudEventHandler;
import com.huto.harmonicresonance.capabilities.vibration.SeerEventHandler;
import com.huto.harmonicresonance.capabilities.vibration.VibrationEvents;
import com.huto.harmonicresonance.capabilities.vibration.chunk.ChunkVibrationEvents;
import com.huto.harmonicresonance.init.BlockInit;
import com.huto.harmonicresonance.init.CapabilityInit;
import com.huto.harmonicresonance.init.ContainerInit;
import com.huto.harmonicresonance.init.EnchantmentInit;
import com.huto.harmonicresonance.init.EntityInit;
import com.huto.harmonicresonance.init.FeatureInit;
import com.huto.harmonicresonance.init.ItemInit;
import com.huto.harmonicresonance.init.TileEntityInit;
import com.huto.harmonicresonance.init.TreeDecoratorInit;
import com.huto.harmonicresonance.network.PacketHandler;
import com.huto.harmonicresonance.recipe.ModFuserRecipies;
import com.huto.harmonicresonance.recipe.ModHarmonizerRecipes;
import com.huto.harmonicresonance.recipe.ModInscriberRecipes;
import com.huto.harmonicresonance.recipe.ModResonatorRecipies;
import com.huto.harmonicresonance.recipe.ModWandRecipies;
import com.huto.harmonicresonance.screen.TomePageLib;
import com.huto.harmonicresonance.worldgen.ModFeatures;
import com.huto.harmonicresonance.worldgen.ModOreGen;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("harmonicresonance")
@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.MOD)
public class HarmonicResonance {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "harmonicresonance";
	public static HarmonicResonance instance;
	public static IProxy proxy = new IProxy() {
	};

	@SuppressWarnings("deprecation")
	public HarmonicResonance() {
		DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> proxy = new ClientProxy());

		instance = this;
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::clientSetup);

		ItemInit.ITEMS.register(modEventBus);
		ItemInit.MODELEDITEMS.register(modEventBus);
		ItemInit.ADVITEMS.register(modEventBus);
		ItemInit.HANDHELDITEMS.register(modEventBus);
		ItemInit.SPAWNEGGS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		BlockInit.SPECIALBLOCKS.register(modEventBus);
		TileEntityInit.TILES.register(modEventBus);
		ContainerInit.CONTAINERS.register(modEventBus);
		FeatureInit.FEATURES.register(modEventBus);
		EntityInit.ENTITY_TYPES.register(modEventBus);
		EnchantmentInit.ENCHANTS.register(modEventBus);
		TreeDecoratorInit.TREEDECORATORS.register(modEventBus);

		// Register Capability Events
		MinecraftForge.EVENT_BUS.register(ChunkVibrationEvents.class);
		MinecraftForge.EVENT_BUS.register(VibrationEvents.class);
		MinecraftForge.EVENT_BUS.register(KarmaEvents.class);
		MinecraftForge.EVENT_BUS.register(SeerEventHandler.class);
		MinecraftForge.EVENT_BUS.register(KarmaHudEventHandler.class);
		// MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH,
		// ModOreGen::addStuffToBiomes);

	}

	// Creative Tab
	public static class HarmonicResonanceItemGroup extends ItemGroup {
		public static final HarmonicResonanceItemGroup instance = new HarmonicResonanceItemGroup(
				ItemGroup.GROUPS.length, "harmonicresonancetab");

		public HarmonicResonanceItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.absorber_configurer.get());
		}
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		// Automatically Registers BlockItems
		final IForgeRegistry<Item> registry = event.getRegistry();
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(HarmonicResonanceItemGroup.instance);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		CapabilityInit.init();
		ModWandRecipies.init();
		ModResonatorRecipies.init();
		ModFuserRecipies.init();
		ModInscriberRecipes.init();
		ModHarmonizerRecipes.init();
		PacketHandler.registerChannels();
		MinecraftForge.EVENT_BUS.register(new ModFeatures());

		event.enqueueWork(() -> {
			ModOreGen.registerConfiguredFeatures();
		});
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		TomePageLib.registerPages();
		this.addLayers();
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {

	}

	public void setupOnLoaded(FMLLoadCompleteEvent event) {

	}

	@SuppressWarnings("unused")
	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	@SuppressWarnings("unused")
	private void processIMC(final InterModProcessEvent event) {

	}

	@OnlyIn(Dist.CLIENT)
	private void addLayers() {
	}

	@SubscribeEvent
	public static void onRecipeRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
	}

}
