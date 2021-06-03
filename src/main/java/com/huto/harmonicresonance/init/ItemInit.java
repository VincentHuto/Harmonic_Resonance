package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.HarmonicResonance.HarmonicResonanceItemGroup;
import com.huto.harmonicresonance.item.EnumModArmorTiers;
import com.huto.harmonicresonance.item.EnumModToolTiers;
import com.huto.harmonicresonance.item.ItemActualizationNode;
import com.huto.harmonicresonance.item.ItemAttractionCharm;
import com.huto.harmonicresonance.item.ItemDebugTool;
import com.huto.harmonicresonance.item.ItemElderTome;
import com.huto.harmonicresonance.item.ItemFrequencyMatcher;
import com.huto.harmonicresonance.item.ItemGrandPurgingStone;
import com.huto.harmonicresonance.item.ItemHarmonicImprint;
import com.huto.harmonicresonance.item.ItemMakerActivator;
import com.huto.harmonicresonance.item.ItemManaExtractor;
import com.huto.harmonicresonance.item.ItemMysteriousMask;
import com.huto.harmonicresonance.item.ItemPurgingStone;
import com.huto.harmonicresonance.item.ItemRepulsionCharm;
import com.huto.harmonicresonance.item.ItemResonanceDestabalizer;
import com.huto.harmonicresonance.item.ItemSelfAnalyzer;
import com.huto.harmonicresonance.item.ItemSlimeRepulsionCharm;
import com.huto.harmonicresonance.item.ItemSomnolentPowder;
import com.huto.harmonicresonance.item.ItemSomnolentTome;
import com.huto.harmonicresonance.item.ItemUpgrade;
import com.huto.harmonicresonance.item.ItemVibeSeer;
import com.huto.harmonicresonance.item.ItemWandConsumeVibes;
import com.huto.harmonicresonance.item.ItemWandGainVibes;
import com.huto.harmonicresonance.item.ToolVeinPickaxe;
import com.hutoslib.common.item.ItemKnapper;
import com.hutoslib.common.item.ModSpawnEggItem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			HarmonicResonance.MOD_ID);
	public static final DeferredRegister<Item> ADVITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			HarmonicResonance.MOD_ID);
	public static final DeferredRegister<Item> MODELEDITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			HarmonicResonance.MOD_ID);
	public static final DeferredRegister<Item> HANDHELDITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			HarmonicResonance.MOD_ID);
	public static final DeferredRegister<Item> SPAWNEGGS = DeferredRegister.create(ForgeRegistries.ITEMS,
			HarmonicResonance.MOD_ID);

	// Return Adornment
	public static final RegistryObject<Item> resonance_destabalizer = ITEMS.register("resonance_destabalizer",
			() -> new ItemResonanceDestabalizer(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Books
	public static final RegistryObject<Item> somnolent_tome = ITEMS.register("somnolent_tome",
			() -> new ItemSomnolentTome(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> elder_tome = ITEMS.register("elder_tome",
			() -> new ItemElderTome(new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	// Grey
	public static final RegistryObject<Item> grey_ingot = ITEMS.register("grey_ingot",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> grey_powder = ITEMS.register("grey_powder",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> grey_crystal = ITEMS.register("grey_crystal",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Channeling
	public static final RegistryObject<Item> essence_drop = ITEMS.register("essence_drop",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> channeling_ingot = ITEMS.register("channeling_ingot",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> emanating_ingot = ITEMS.register("emanating_ingot",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> somnolent_powder = ITEMS.register("somnolent_powder",
			() -> new ItemSomnolentPowder(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> somnolent_crystal = ITEMS.register("somnolent_crystal",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> channeling_rod = ITEMS.register("channeling_rod",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Anti
	public static final RegistryObject<Item> anti_tear = ITEMS.register("anti_tear",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_ingot = ITEMS.register("null_ingot",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> shattered_ingot = ITEMS.register("shattered_ingot",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> nullifying_powder = ITEMS.register("nullifying_powder",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_crystal = ITEMS.register("null_crystal",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_rod = ITEMS.register("null_rod",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Karma
	public static final RegistryObject<Item> karmic_harvester = HANDHELDITEMS.register("karmic_harvester",
			() -> new SwordItem(ItemTier.STONE, 3, -1.4F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> node_of_actualization = ITEMS.register("node_of_actualization",
			() -> new ItemActualizationNode(new Item.Properties().group(HarmonicResonanceItemGroup.instance)
					.maxStackSize(1).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> karmic_drop = ITEMS.register("karmic_drop",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> karmic_bar = ITEMS.register("karmic_bar",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> purging_stone = ITEMS.register("purging_stone",
			() -> new ItemPurgingStone(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> grand_purging_stone = ITEMS.register("grand_purging_stone",
			() -> new ItemGrandPurgingStone(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Materials
	// Gems
	public static final RegistryObject<Item> gem_hematite = ITEMS.register("gem_hematite",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> gem_ruby = ITEMS.register("gem_ruby",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> gem_onyx = ITEMS.register("gem_onyx",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> gem_sapphire = ITEMS.register("gem_sapphire",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> gem_topaz = ITEMS.register("gem_topaz",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> gem_amethyst = ITEMS.register("gem_amethyst",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> gem_opal = ITEMS.register("gem_opal",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> ibis_beak = ITEMS.register("ibis_beak",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> readied_pane = ITEMS.register("readied_pane",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> resonant_fuel = ITEMS.register("resonant_fuel",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> magatamabead = ITEMS.register("magatamabead",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> enhanced_magatama = ITEMS.register("enhanced_magatama",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> energy_focus = ITEMS.register("energy_focus",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> harmonic_imprint = ADVITEMS.register("harmonic_imprint",
			() -> new ItemHarmonicImprint(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));

	// Food
	public static final RegistryObject<Item> singeri_soup = ITEMS.register("singeri_soup",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> raw_morel_on_a_stick = ITEMS.register("raw_morel_on_a_stick",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	@SuppressWarnings("deprecation")
	public static final RegistryObject<Item> cooked_morel_on_a_stick = ITEMS.register("cooked_morel_on_a_stick",
			() -> new Item(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).food(new Food.Builder().hunger(6)
							.saturation(1.5f).effect(new EffectInstance(Effects.ABSORPTION, 6000, 5), 0.7f).build())));
	public static final RegistryObject<Item> somnolent_bottle = ITEMS.register("somnolent_bottle",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Charms
	public static final RegistryObject<Item> attraction_charm = ADVITEMS.register("attraction_charm",
			() -> new ItemAttractionCharm(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> repulsion_charm = ADVITEMS.register("repulsion_charm",
			() -> new ItemRepulsionCharm(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> slime_charm = ADVITEMS.register("slime_charm",
			() -> new ItemSlimeRepulsionCharm(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	// Armor
	public static final RegistryObject<Item> vibrational_seer = ITEMS.register("vibrational_seer",
			() -> new ItemVibeSeer(EnumModArmorTiers.CIRCLET, EquipmentSlotType.HEAD,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> mysterious_mask = ITEMS.register("mysterious_mask",
			() -> new ItemMysteriousMask(EnumModArmorTiers.MASK, EquipmentSlotType.HEAD,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_helmet = ITEMS.register("null_helmet",
			() -> new ArmorItem(EnumModArmorTiers.NULL, EquipmentSlotType.HEAD,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	public static final RegistryObject<Item> null_chestplate = ITEMS.register("null_chestplate",
			() -> new ArmorItem(EnumModArmorTiers.NULL, EquipmentSlotType.CHEST,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	public static final RegistryObject<Item> null_leggings = ITEMS.register("null_leggings",
			() -> new ArmorItem(EnumModArmorTiers.NULL, EquipmentSlotType.LEGS,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	public static final RegistryObject<Item> null_boots = ITEMS.register("null_boots",
			() -> new ArmorItem(EnumModArmorTiers.NULL, EquipmentSlotType.FEET,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	public static final RegistryObject<Item> elder_helmet = ITEMS.register("elder_helmet",
			() -> new ArmorItem(EnumModArmorTiers.ELDER, EquipmentSlotType.HEAD,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	public static final RegistryObject<Item> elder_chestplate = ITEMS.register("elder_chestplate",
			() -> new ArmorItem(EnumModArmorTiers.ELDER, EquipmentSlotType.CHEST,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	public static final RegistryObject<Item> elder_leggings = ITEMS.register("elder_leggings",
			() -> new ArmorItem(EnumModArmorTiers.ELDER, EquipmentSlotType.LEGS,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	public static final RegistryObject<Item> elder_boots = ITEMS.register("elder_boots",
			() -> new ArmorItem(EnumModArmorTiers.ELDER, EquipmentSlotType.FEET,
					(new Item.Properties()).group(HarmonicResonanceItemGroup.instance).isImmuneToFire()));
	// Tools

	// Null
	public static final RegistryObject<Item> null_pickaxe = HANDHELDITEMS.register("null_pickaxe",
			() -> new PickaxeItem(EnumModToolTiers.NULL, 1, -2.8F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_shovel = HANDHELDITEMS.register("null_shovel",
			() -> new ShovelItem(EnumModToolTiers.NULL, 1.5F, -3.0F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_hoe = HANDHELDITEMS.register("null_hoe",
			() -> new HoeItem(EnumModToolTiers.NULL, -3, 0.0F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_axe = HANDHELDITEMS.register("null_axe",
			() -> new AxeItem(EnumModToolTiers.NULL, 5.0F, -3.0F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> null_sword = HANDHELDITEMS.register("null_sword",
			() -> new SwordItem(EnumModToolTiers.NULL, 3, -2.4F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> duality_pick = HANDHELDITEMS.register("duality_pick",
			() -> new ToolVeinPickaxe(EnumModToolTiers.NULL, 1, -2.8F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> duality_axe = HANDHELDITEMS.register("duality_axe",
			() -> new AxeItem(EnumModToolTiers.NULL, 5.0F, -3.0F,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> absorber_configurer = ADVITEMS.register("absorber_configurer",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> frequency_matcher = ADVITEMS.register("frequency_matcher",
			() -> new ItemFrequencyMatcher(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	// Knappers
	public static final RegistryObject<Item> obsidian_knapper = HANDHELDITEMS.register("obsidian_knapper",
			() -> new ItemKnapper(50f, 1, 0, EnumModToolTiers.NULL,
					new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Hands
	public static final RegistryObject<Item> vibratory_extractor = ITEMS.register("vibratory_extractor",
			() -> new ItemManaExtractor(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> maker_activator = ITEMS.register("maker_activator",
			() -> new ItemMakerActivator(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> vibration_debug_tool = ITEMS.register("vibration_debug_tool",
			() -> new ItemDebugTool(new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> self_analyzer = ITEMS.register("self_analyzer", () -> new ItemSelfAnalyzer(
			new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));

	// Wands
	public static final RegistryObject<Item> wand_consume_vibes = HANDHELDITEMS.register("wand_consume_vibes",
			() -> new ItemWandConsumeVibes(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> wand_gain_vibes = HANDHELDITEMS.register("wand_gain_vibes",
			() -> new ItemWandGainVibes(
					new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));

	// Upgrades
	public static final RegistryObject<Item> upgrade_wrench = HANDHELDITEMS.register("upgrade_wrench",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> upgrade_blank = ITEMS.register("upgrade_blank",
			() -> new Item(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> upgrade_block = ITEMS.register("upgrade_block",
			() -> new ItemUpgrade(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> upgrade_player = ITEMS.register("upgrade_player",
			() -> new ItemUpgrade(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> upgrade_animal = ITEMS.register("upgrade_animal",
			() -> new ItemUpgrade(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> upgrade_mob = ITEMS.register("upgrade_mob",
			() -> new ItemUpgrade(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> upgrade_export = ITEMS.register("upgrade_export",
			() -> new ItemUpgrade(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<Item> upgrade_import = ITEMS.register("upgrade_import",
			() -> new ItemUpgrade(new Item.Properties().group(HarmonicResonanceItemGroup.instance)));

	// Spawn Eggs
	public static final RegistryObject<ModSpawnEggItem> spawn_egg_ibis = SPAWNEGGS.register("spawn_egg_ibis",
			() -> new ModSpawnEggItem(EntityInit.ibis, 9175040, 8672512,
					new Item.Properties().group(ItemGroup.MISC).group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<ModSpawnEggItem> spawn_egg_colin = SPAWNEGGS.register("spawn_egg_colin",
			() -> new ModSpawnEggItem(EntityInit.colin, 0x88008B, 0xFF7F00,
					new Item.Properties().group(ItemGroup.MISC).group(HarmonicResonanceItemGroup.instance)));
	public static final RegistryObject<ModSpawnEggItem> spawn_egg_dream_walker = SPAWNEGGS
			.register("spawn_egg_dream_walker", () -> new ModSpawnEggItem(EntityInit.dream_walker, 0x000000, 0xFFFFFF,
					new Item.Properties().group(ItemGroup.MISC).group(HarmonicResonanceItemGroup.instance)));

	@SubscribeEvent
	public static void registerItemColorHandlers(ColorHandlerEvent.Item event) {
		registerSpawnEggColorHandler(event.getItemColors(), ItemInit.spawn_egg_colin, ItemInit.spawn_egg_ibis,
				ItemInit.spawn_egg_dream_walker);
	}

	@SuppressWarnings("unchecked")
	@SafeVarargs
	public static void registerSpawnEggColorHandler(ItemColors colors, RegistryObject<ModSpawnEggItem>... spawnEggs) {
		for (RegistryObject<ModSpawnEggItem> spawnEgg : spawnEggs) {
			registerItemColorHandler(colors, (stack, tintIndex) -> spawnEgg.get().getColor(tintIndex), spawnEgg);
		}
	}

	@SuppressWarnings("unchecked")
	public static void registerItemColorHandler(ItemColors colors, IItemColor itemColor,
			RegistryObject<ModSpawnEggItem>... items) {
		for (RegistryObject<ModSpawnEggItem> itemProvider : items) {
			colors.register(itemColor, itemProvider.get());
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void itemPropOverrideClient(final FMLClientSetupEvent event) {

		ItemModelsProperties.registerProperty(channeling_ingot.get(), new ResourceLocation("pull"),
				(p_239429_0_, p_239429_1_, p_239429_2_) -> {
					if (p_239429_2_ == null) {
						return 0.0F;
					} else {
						return p_239429_2_.getActiveItemStack() != p_239429_0_ ? 0.0F
								: (float) (p_239429_0_.getUseDuration() - p_239429_2_.getItemInUseCount()) / 20.0F;
					}
				});
		ItemModelsProperties.registerProperty(channeling_ingot.get(), new ResourceLocation("pulling"),
				(p_239428_0_, p_239428_1_, p_239428_2_) -> {
					return p_239428_2_ != null && p_239428_2_.isHandActive()
							&& p_239428_2_.getActiveItemStack() == p_239428_0_ ? 1.0F : 0.0F;
				});
		// Imprint
		ItemModelsProperties.registerProperty(harmonic_imprint.get(),
				new ResourceLocation(HarmonicResonance.MOD_ID, "saved"), new IItemPropertyGetter() {
					@Override
					public float call(ItemStack stack, ClientWorld world, LivingEntity ent) {
						if (stack.hasTag()) {
							if (stack.getTag().getBoolean("state")) {
								return 1;
							} else {
								return 0;
							}
						}
						return 0;
					}
				});

		// Attract Charm
		ItemModelsProperties.registerProperty(attraction_charm.get(),
				new ResourceLocation(HarmonicResonance.MOD_ID, "on"), new IItemPropertyGetter() {
					@Override
					public float call(ItemStack stack, ClientWorld world, LivingEntity ent) {
						if (stack.hasTag()) {
							if (stack.getTag().getBoolean("state")) {
								return 1;
							} else {
								return 0;
							}
						}
						return 0;
					}
				});

		// Slime Charm
		ItemModelsProperties.registerProperty(slime_charm.get(), new ResourceLocation(HarmonicResonance.MOD_ID, "on"),
				new IItemPropertyGetter() {
					@Override
					public float call(ItemStack stack, ClientWorld world, LivingEntity ent) {
						if (stack.hasTag()) {
							if (stack.getTag().getBoolean("state")) {
								return 1;
							} else {
								return 0;
							}
						}
						return 0;
					}
				});

		// Repulsion Charm
		ItemModelsProperties.registerProperty(repulsion_charm.get(),
				new ResourceLocation(HarmonicResonance.MOD_ID, "on"), new IItemPropertyGetter() {
					@Override
					public float call(ItemStack stack, ClientWorld world, LivingEntity ent) {
						if (stack.hasTag()) {
							if (stack.getTag().getBoolean("state")) {
								return 1;
							} else {
								return 0;
							}
						}
						return 0;
					}
				});

		// Frequency Matcher
		ItemModelsProperties.registerProperty(frequency_matcher.get(),
				new ResourceLocation(HarmonicResonance.MOD_ID, "clear"), new IItemPropertyGetter() {
					@Override
					public float call(ItemStack stack, ClientWorld world, LivingEntity ent) {
						if (stack.hasTag()) {
							if (stack.getTag().getBoolean("state")) {
								return 0;
							} else {
								return 1;
							}
						}
						return 0;
					}
				});

	}

}
